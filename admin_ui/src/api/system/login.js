import request from '@/utils/request'

// 登录方法
export function loginP(data) {
  return request({
    url: '/system/login',
    headers: {
      isToken: false
    },
    method: 'post',
    data: data
  })
}

//注销方法
export function logoutG() {
  return request({
    url: '/system/logout',
    method: 'get'
  })
}

//获取当前登录的用户信息
export function getLoginInfoG() {
  return request({
    url: '/system/getLoginInfo',
    method: 'get'
  })
}
