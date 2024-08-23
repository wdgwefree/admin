import '@/assets/scss/index.scss'
import 'element-plus/dist/index.css'
import 'nprogress/nprogress.css';
import { getPlatformConfig } from "@/config";

import App from './App.vue'
import router from './router'
import {createApp} from 'vue'
import pinia from "@/stores"
import ElementPlus from 'element-plus'

const app = createApp(App)

app.use(ElementPlus)
app.use(router)
app.use(pinia)
app.mount('#app')

getPlatformConfig(app).then(async config => {
  // await router.isReady();
  // injectResponsiveStorage(app, config);
  // app.use(MotionPlugin).use(useElementPlus).use(Table);
  // // .use(PureDescriptions)
  // // .use(useEcharts);
  // app.mount("#app");
});
