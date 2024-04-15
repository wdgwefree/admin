import {createRouter, createWebHistory} from 'vue-router'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css';
import {getToken} from '@/utils/auth'
import {stringUtils as $stringUtils} from "../utils/stringUtils";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/personalInfo',
    },
    {
      path: '/home',
      name: 'home',
      component: () => import('@/views/system/home.vue'),
      children: [
        {
          path: '/personalInfo',
          name: 'personalInfo',
          meta: {
            title: '个人信息',
          },
          component: () => import('@/views/system/personalInfo.vue'),
        },
        {
          path: '/404',
          name: '404',
          meta: {
            title: '404',
          },
          component: () => import('../views/system/404.vue'),
        },
      ],
    },
    {
      path: '/login',
      name: 'login',
      meta: {
        title: '登录',
      },
      component: () => import('@/views/system/login.vue'),
    },
  ]
})

//路由加载前
router.beforeEach((to, from, next) => {
  console.log("from--------------------", from.path)
  console.log("to--------------------", to.path)
  NProgress.start();
  if (to.matched.length === 0) {
    next({path: '/404', replace: false});
  }
  const token = getToken();
  // const hasToken = (token !== null && token !== "" && token !== "undefined" && token !== undefined);
  let hasToken = $stringUtils.isNotBlank(token);

  console.log("hasToken------------------", hasToken);

  if (hasToken) {
    if (to.path === '/login') {
      next({path: '/'});
    } else {
      next();
    }
  } else {
    if (to.path === '/login') {
      next();
    } else {
      next({path: '/login'});
    }
  }
});

// 路由加载后，关闭loading
router.afterEach(() => {
  NProgress.done();
})
export default router
