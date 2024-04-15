import {defineStore} from "pinia"

const useSideBarStore = defineStore('sideBar', {
  state: () => {
    return {
      collapse: false
    };
  },
  actions: {
    handleCollapse() {
      this.collapse = !this.collapse;
    }
  },
  persist: true,
});

export {useSideBarStore};