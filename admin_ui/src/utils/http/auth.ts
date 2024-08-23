import Cookies from "js-cookie";

const tokenKey = 'admin-token'

//密码记住时间，单位为天
const expires = 30;

export function getToken() {
  return Cookies.get(tokenKey)
}

export function setToken(token: string) {
  //如果不指定 expires 属性，Cookie 将成为一个会话 Cookie，这意味着它将在浏览器关闭时过期。
  Cookies.set(tokenKey, token)
}

export function removeToken() {
  return Cookies.remove(tokenKey)
}

export function setRememberMe(userAccount: string, password: string, rememberMe: boolean) {
  Cookies.set("userAccount", userAccount, {expires: expires});
  Cookies.set("password", password, {expires: expires});
  Cookies.set('rememberMe', rememberMe.toString(), {expires: expires});
}

export function getRememberMe() {
  return {
    userAccount: Cookies.get("userAccount"),
    password: Cookies.get("password"),
    rememberMe: Cookies.get('rememberMe') === 'true'
  }
}

export function removeRememberMe() {
  Cookies.remove("userAccount");
  Cookies.remove("password");
  Cookies.remove('rememberMe');
}