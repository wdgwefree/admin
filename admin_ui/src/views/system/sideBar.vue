<template>
  <div class="sideBar_body">
    <el-menu :default-active="$route.path" :collapse="sideBar.collapse" router unique-opened>
      <template v-for="item in menuList">
        <template v-if="item.subs">
          <el-sub-menu :index="item.index" :key="item.index">
            <template #title>
              <el-icon>
                <component :is="item.icon"></component>
              </el-icon>
              <span>{{ item.title }}</span>
            </template>
            <template v-for="subItem in item.subs">
              <el-menu-item :index="subItem.index">
                {{ subItem.title }}
              </el-menu-item>
            </template>
          </el-sub-menu>
        </template>
        <template v-else>
          <el-menu-item :index="item.index">
            <el-icon>
              <component :is="item.icon"></component>
            </el-icon>
            <template #title>{{ item.title }}</template>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script setup>
import {useSideBarStore} from '@/stores/sidebar';
import {useRouter} from 'vue-router';

const router = useRouter();

const sideBar = useSideBarStore();

const menuList = [
  {
    icon: 'Odometer',
    index: '/dashboard',
    title: '系统首页',
  },
  {
    icon: 'Setting',
    index: '/systemManagement',
    title: '系统管理',
  },
  {
    icon: 'Warning',
    index: '/permission',
    title: '权限管理',
  },
  {
    icon: 'Edit',
    index: '3',
    title: '表单相关',
    subs: [
      {
        index: '/form',
        title: '基本表单',
      },
      {
        index: '/upload',
        title: '文件上传',
      }
    ],
  },
];

const addRoutesToRouter = () => {
  // 动态添加路由
  menuList.forEach(item => {
    if (item.subs) {
      item.subs.forEach(subItem => {
        router.addRoute(item.index.replace('/', ''),{
          path: subItem.index,
          name: subItem.index.replace('/', ''),
          meta: {
            title: subItem.title,
          },
          component: () => import(`@/views/system${subItem.index}.vue`),
        });
      });
    } else {
      router.addRoute('home',{
        path: item.index,
        name: item.index.replace('/', ''),
        meta: {
          title: item.title,
        },
        component: () => import(`@/views/system${item.index}.vue`),
      });
    }
  });
};

addRoutesToRouter();


</script>
<style scoped lang="scss">
.sideBar_body {
  display: block;
  //overflow-y: scroll;
}

.sideBar_body::-webkit-scrollbar {
  width: 0;
}

.sideBar_body > ul {
  height: 100%;
}
</style>