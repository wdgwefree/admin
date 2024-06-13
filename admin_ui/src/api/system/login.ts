import request from '@/utils/request'

// 登录方法
export function login_P(data) {
  return request({
    url: '/system/login',
    method: 'post',
    data: data,
    headers: {
      'openApi': true,
    }
  })
}

//注销方法
export function logout_G() {
  return request({
    url: '/system/logout',
    method: 'get'
  })
}

//获取当前登录的用户信息
export function getLoginInfo_G() {
  return request({
    url: '/system/getLoginInfo',
    method: 'get'
  })
}
