import {createPinia} from "pinia"
import piniaPlugin from 'pinia-plugin-persistedstate'


//创建pinia实例
const pinia = createPinia()

// 使用插件
pinia.use(piniaPlugin)

//导出pinia用于main.js注册
export default pinia