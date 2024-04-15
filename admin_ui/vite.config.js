import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path';

export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, 'src')
    },
  },
  server: {
    port: 5174,
    proxy: {
      '/admin': {
        target: 'http://127.0.0.1:8188/admin',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/admin/, ''),
      },
      //如果有多个后端，可以把下面配置放开
      // '/temp':{
      //   target: 'http://127.0.0.1:8189/wdgTemp',
      //   changeOrigin: true,
      //   rewrite: (path) => path.replace(/^\/temp/, ''),
      // },
    }
  },
  build: {
    chunkSizeWarningLimit: 1500, // 设置为更大的值，以减少警告的出现
  },
  define: {
    // 添加生产环境下的特性标志() 识别和删除不被使用的代码，以减小最终生成的 JavaScript 文件的大小。
    __VUE_PROD_HYDRATION_MISMATCH_DETAILS__: false,
  },
})
