import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import pinia from './stores'


import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'nprogress/nprogress.css';
import './assets/scss/index.scss'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import {stringUtils} from '@/utils/stringUtils';
import GlobalConstantsPlugin from '@/plugins/globalConstantsPlugin';

const app = createApp(App)

// 全局混入
app.mixin({
  methods: {
    // 将 StringUtils 添加到全局变量
    $stringUtils: stringUtils
  }
});

// 使用全局常量插件
app.use(GlobalConstantsPlugin);
app.use(ElementPlus)

// 注册elementPlus图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.use(router)
app.use(pinia)
app.mount('#app')
