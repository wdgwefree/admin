import axios from 'axios'
import {ElMessage, ElNotification} from 'element-plus';

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

// 创建axios实例
const service = axios.create({
  // URL请求公共部分
  baseURL: '/admin',
  // 超时
  timeout: 60000
})

// 请求拦截器
service.interceptors.request.use(config => {
  console.log("config", config)
  config.headers['Authorization'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改

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
