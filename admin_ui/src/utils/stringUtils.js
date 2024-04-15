//字符串工具类
export class stringUtils {
  static isBlank(str) {
    return !str || /^\s*$/.test(str) || str === "undefined";
  }

  static isNotBlank(str) {
    return !(!str || /^\s*$/.test(str) || str === "undefined");
  }
}