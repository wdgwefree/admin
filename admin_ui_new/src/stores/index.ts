import {createPinia} from 'pinia' // 引入 pinia
import piniaPersist from "pinia-plugin-persistedstate"; // 引入 pinia 插件(持久化)
import SecureLS from "secure-ls"; // 加密 pinia

// 创建 pinia 实例
const pinia = createPinia();

// 创建 SecureLS 实例
export const ls = new SecureLS({
  encodingType: "aes",
  isCompression: false,
});

// 在 Pinia 中注册插件
pinia.use(piniaPersist);
pinia.use(({store}) => {
  // 加密状态并存储到 SecureLS 中
  const encryptAndStoreState = () => {
    ls.set(store.$id, store.$state);
  };
  // 解密状态并恢复到 Pinia 中
  const decryptAndRestoreState = () => {
    const data = ls.get(store.$id);
    store.$state = data;
  };

  store.$subscribe(encryptAndStoreState); // 在每次状态变更时调用加密函数

  // 在初始化时调用解密函数
  decryptAndRestoreState()
});
export default pinia;