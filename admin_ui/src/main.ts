import '@/assets/scss/index.scss'
import 'element-plus/dist/index.css'
import 'nprogress/nprogress.css';


import App from './App.vue'
import router from './router'
import {createApp} from 'vue'
import pinia from "@/stores";
import ElementPlus from 'element-plus'


const app = createApp(App)


app.use(router)
app.use(pinia)
app.use(ElementPlus)
app.mount('#app')
