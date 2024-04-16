import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
// 导入path 用于设置别名
import * as path from "path";

export default defineConfig({
  
  plugins: [
    vue(),
  ],
  resolve: {
    // 需要在tsconfig.json的compilerOptions中配置paths
    alias: {
      "@": path.resolve("./src"), // @代替src
      "~": path.resolve("./src/components"), // @代替src/components
      "-": path.resolve("./src/api"), // @代替src/api
    }
  },
  server: {
    //使用IP能访问
    host: "0.0.0.0",
    port: 5173,
    //是否自动打开浏览器
    open: true,
    // 热更新
    hmr: true,
    //设为 true 时若端口已被占用则会直接退出，而不是尝试下一个可用端口
    strictPort: true,
    //自定义代理规则
    proxy: {
      '/admin': {
        target: 'http://127.0.0.1:8188/admin',
        changeOrigin: true, //允许跨域
        rewrite: (path) => path.replace(/^\/admin/, ""),  //重写路径
      },
      //如果有多个后端，可以把下面配置放开
      // '/temp':{
      //   target: 'http://127.0.0.1:8189/wdgTemp',
      //   changeOrigin: true,
      //   rewrite: (path) => path.replace(/^\/temp/, ''),
      // },
    }
  },

  // 打包配置
  build: {
    outDir: "dist",
    assetsDir: "static", //指定静态资源存放路径
    sourcemap: false, //是否构建source map 文件
    minify: "terser", // 混淆器，terser构建后文件体积更小
    terserOptions: {
      compress: {
        //生产环境时移除console
        drop_console: true,
        drop_debugger: true,
      },
    },
    rollupOptions: {
      output: {
        compact: true,
        entryFileNames: "static/js/[name]-[hash].js",
        chunkFileNames: "static/js/[name]-[hash].js",
        assetFileNames: "static/[ext]/[name].[ext]",
      },
    },
  },
  define: {
    // 添加生产环境下的特性标志() 识别和删除不被使用的代码，以减小最终生成的 JavaScript 文件的大小。
    __VUE_PROD_HYDRATION_MISMATCH_DETAILS__: false,
  },
})
