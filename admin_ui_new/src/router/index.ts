import { createRouter, createWebHashHistory, RouterOptions } from "vue-router";
import NProgress from 'nprogress'

// 默认静态路由，不需要权限的路由
export const routes = [
  {
    path: "/",
    redirect: "/login",
    name: "root",
    meta: {
      loading: true,
      keepAlive: true,
      title: "",
      icon: "",
    },
    component: () => import("@/views/system/home-page.vue"),
  },
  {
    path: "/home",
    name: "home",
    meta: {
      loading: true,
      keepAlive: true,
      title: "",
      icon: "",
    },
    component: () => import("@/views/system/home-page.vue"),
  },
  {
    path: "/login",
    name: "login",
    meta: {
      loading: true,
      keepAlive: true,
      title: "",
      icon: "",
    },
    component: () => import("@/views/system/login.vue"),
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

//路由加载前
router.beforeEach((to, from, next) => {
  console.log("from--------------------", from.path)
  console.log("to--------------------", to.path)
  NProgress.start();
  next();
});

// 路由加载后，关闭loading
router.afterEach(() => {
  NProgress.done();
})


export default router
