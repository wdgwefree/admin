import {defineStore} from "pinia"

const useUserInfoStore = defineStore('userInfo', {
  state: () => ({
    userId: "",
    userName: "",
    userAccount: "",
    userType: "",
    avatar:"",
    deptId: null,
    sex: "",
    phone: "",
    email: "",
    remark: "",
    createTime: "",
    updateTime: "",
  }),
  actions: {
    setUserInfoStore(userInfo) {
      Object.assign(this, userInfo);
    }
  },
  persist: true,
})
export {useUserInfoStore};
