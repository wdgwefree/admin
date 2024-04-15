import {defineStore} from 'pinia';

export const useMenuStore = defineStore('menuStore', {
  state: {
    menuList: [],
  },
  getters: {
    nameList: state => {
      return state.menuList.map(item => item.name);
    }
  },
  actions: {
    async setMenuList(data) {
      Object.assign(this.menuList, data);
    },
  },
  persist: true,
});
