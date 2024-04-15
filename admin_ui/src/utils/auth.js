import Cookies from 'js-cookie'

const tokenKey = 'admin-token'

//记住密码过期时间
const expires = 30;

export function getToken() {
  return Cookies.get(tokenKey)
}

export function setToken(token) {
  return Cookies.set(tokenKey, token)
}

export function removeToken() {
  return Cookies.remove(tokenKey)
}

export function setRememberMe(userAccount, password, rememberMe) {
  Cookies.set("userAccount", userAccount, {expires: expires});
  Cookies.set("password", password, {expires: expires});
  Cookies.set('rememberMe', rememberMe, {expires: expires});
}

export function getRememberMe() {
  return {
    userAccount: Cookies.get("userAccount"),
    password: Cookies.get("password"),
    rememberMe: Cookies.get('rememberMe')
  }
}

export function removeRememberMe() {
  Cookies.remove("userAccount");
  Cookies.remove("password");
  Cookies.remove('rememberMe');
}

