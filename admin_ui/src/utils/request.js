import axios from 'axios'
import {ElMessage, ElNotification, ElMessageBox} from 'element-plus';
import {getToken} from '@/utils/auth'
import {tansParams} from '@/utils/common'
import cache from '@/plugins/cache'
import errorCode from '@/utils/errorCode'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: '/admin',
  // 超时
  timeout: 60000
})

// request拦截器
service.interceptors.request.use(config => {

  console.log("config",config)
  const url = config.url;
  // 根据 URL 动态修改 baseURL
  // if (url.startsWith('/temp')) {
  //   config.baseURL = '';
  // } else {
  //   config.baseURL = '/admin';
  // }

  // 是否需要设置 token
  const isToken = (config.headers || {}).isToken === false

  // 是否需要防止数据重复提交
  const isRepeatSubmit = (config.headers || {}).repeatSubmit === false
  if (getToken() && !isToken) {
    config.headers['Authorization'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
  }
  // get请求映射params参数
  if (config.method === 'get' && config.params) {
    let url = config.url + '?' + tansParams(config.params);
    url = url.slice(0, -1);
    config.params = {};
    // 不使用缓存通过添加时间戳来强制刷新。
    let time = new Date().getTime()
    url += '&t=' + time
    config.url = url;
  }
  if (!isRepeatSubmit && (config.method === 'post' || config.method === 'put')) {
    const requestObj = {
      url: config.url,
      data: typeof config.data === 'object' ? JSON.stringify(config.data) : config.data,
      time: new Date().getTime()
    }
    const sessionObj = cache.session.getJSON('sessionObj')
    if (sessionObj === undefined || sessionObj === null || sessionObj === '') {
      cache.session.setJSON('sessionObj', requestObj)
    } else {
      const s_url = sessionObj.url;                  // 请求地址
      const s_data = sessionObj.data;                // 请求数据
      const s_time = sessionObj.time;                // 请求时间
      const interval = 1000;                         // 间隔时间(ms)，小于此时间视为重复提交
      if (s_data === requestObj.data && requestObj.time - s_time < interval && s_url === requestObj.url) {
        const message = '数据正在处理，请勿重复提交';
        console.warn(`[${s_url}]: ` + message)
        return Promise.reject(new Error(message))
      } else {
        cache.session.setJSON('sessionObj', requestObj)
      }
    }
  }
  return config
}, error => {
  console.log(error)
  Promise.reject(error)
})


// 响应拦截器
service.interceptors.response.use(res => {

  console.log("res", res)
  // 二进制数据则直接返回
  if (res.request.responseType === 'blob' || res.request.responseType === 'arraybuffer') {
    return res.data
  }
  // 未设置状态码则默认成功状态
  const code = res.data.code ? res.data.code : 0;
  // 获取错误信息
  const msg = errorCode[code] || res.data.msg || errorCode['default'];

  if (code !== 0) {
    ElNotification.error({
      title: msg,
      duration: 3000, // 持续显示时间，单位为毫秒
      // offset: 100, // 距离顶部的偏移量，单位为像素
      // position: 'top-right', // 通知的位置，可以是 'top-right', 'top-left', 'bottom-right', 'bottom-left'
      showClose: true, // 是否显示关闭按钮
    })
  }
  return res.data;

}, error => {
  console.log('拦截器进入error' + error)
  let {message} = error;
  if (message == "Network Error") {
    message = "后端接口连接异常";
  } else if (message.includes("timeout")) {
    message = "系统接口请求超时";
  } else if (message.includes("Request failed with status code")) {
    message = "系统接口" + message.substr(message.length - 3) + "异常";
  }
  ElMessage({
    message: message,
    type: 'error',
    duration: 3 * 1000
  });
  return Promise.reject(error)
})

export default service
