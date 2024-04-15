<template>
  <div class="tags" v-if="tags.show">
    <ul>
      <li class="tags-li" v-for="(item,index) in tags.list" :key="index" :class="{active:isActive(item.path)}"
          @contextmenu.prevent.native="openMenu(item,$event)">
        <router-link :to="item.path" class="tags-li-title">{{ item.title }}</router-link>
        <el-icon class="el-icon-close" @click="closeTags(index)">
          <Close/>
        </el-icon>
      </li>
    </ul>
    <div v-if="visible" class="context-menu" :style="{ top: `${menuPosition.y}px`, left: `${menuPosition.x}px` }"
         @click="handleClick">
      <div class="context-menu-item" @click="refreshTag">
        <el-icon><Refresh/></el-icon>刷新页面
      </div>
      <div class="context-menu-item" @click="closeAll">
        <el-icon><CloseBold /></el-icon>关闭所有
      </div>
    </div>
  </div>
</template>

<script setup>
import {useTagsStore} from '@/stores/tags';
import {onBeforeRouteUpdate, useRoute, useRouter} from 'vue-router';
import {ref, onMounted, onUnmounted} from 'vue';

//useRoute 主要用于获取当前路由的信息，而 useRouter 则主要用于执行路由导航的操作
const route = useRoute();
const router = useRouter();

const tags = useTagsStore();


const isActive = (path) => {
  return path === route.fullPath;
};

//是否显示右键菜单
const visible = ref(false);

//菜单位置
let menuPosition = {x: 0, y: 0};

//右键选择的标签
let rightTag;

//打开右键菜单
const openMenu = (item, e) => {
  menuPosition = {x: e.clientX, y: e.clientY};
  visible.value = true;
  rightTag = item;
  console.log("item", item);
  console.log("rightTag", rightTag);
};

//右键菜单点击命令
const handleClick = (command) => {
  visible.value = false;
  if (command === 'refreshTag') {

  } else if (command === 'closeAll') {
    closeAll();
  }
};

// 添加点击事件监听器
onMounted(() => {
  document.addEventListener('click', handleClickOutside);
});

// 移除点击事件监听器
onUnmounted(() => {
  document.removeEventListener('click', handleClickOutside);
});

// 处理点击页面其他地方的事件
const handleClickOutside = (event) => {
  if (!event.target.closest('.context-menu')) {
    // 如果点击的不是右键菜单内的元素，则关闭右键菜单
    visible.value = false;
  }
};


// 关闭单个标签
const closeTags = (index) => {
  const delItem = tags.list[index];
  tags.delTagsItem(index);
  const item = tags.list[index] ? tags.list[index] : tags.list[index - 1];
  if (item) {
    delItem.path === route.fullPath && router.push(item.path);
  } else {
    router.push('/');
  }
};

// 设置标签
const setTags = (route) => {
  const isExist = tags.list.some(item => {
    return item.path === route.fullPath;
  });
  if (!isExist) {
    if (tags.list.length >= 8) tags.delTagsItem(0);
    tags.setTagsItem({
      name: route.name,
      title: route.meta.title,
      path: route.fullPath
    });
  }
};
setTags(route);

// 在路由更新前执行逻辑
onBeforeRouteUpdate(to => {
  setTags(to);
});

// 关闭全部标签
const closeAll = () => {
  tags.clearTags();
  router.push('/personalInfo');
};
// 关闭其他标签
const closeOther = () => {
  const curItem = tags.list.filter(item => {
    return item.path === route.fullPath;
  });
  tags.closeTagsOther(curItem);
};

</script>

<style scoped lang="scss">
.tags {
  position: relative;
  height: 30px;
  border-bottom: 1px solid #d8dce5;
}

.tags-li {
  display: flex;
  align-items: center;
  float: left;
  margin: 3px 5px 2px 3px;
  border-radius: 3px;
  font-size: 12px;
  overflow: hidden;
  cursor: pointer;
  height: 23px;
  border: 1px solid #e9eaec;
  background: #fff;
  padding: 0 5px 0 12px;
  color: #666;
  -webkit-transition: all 0.3s ease-in;
  -moz-transition: all 0.3s ease-in;
  transition: all 0.3s ease-in;
  .el-icon-close :hover {
    background-color: #b4bccc;
    color: #742929;

  }
}

.tags-li:not(.active):hover {
  background: #f8f8f8;
}

.tags-li.active {
  color: #ffffff;
  background: rgb(64, 158, 255);
}

.tags-li-title {
  float: left;
  max-width: 80px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-right: 5px;
  color: #666;
}

.tags-li.active .tags-li-title {
  color: #fff;
}

.tags-close-box {
  position: absolute;
  right: 0;
  top: 0;
  box-sizing: border-box;
  padding-top: 1px;
  text-align: center;
  width: 110px;
  height: 30px;
  background: #fff;
  box-shadow: -3px 0 15px 3px rgba(0, 0, 0, 0.1);
  z-index: 10;
}

ul {
  list-style: none;
  padding: 0px;
  margin: 0px;
}

a {
  text-decoration: none;
}

.context-menu {
  position: fixed;
  background: #ffffff;
  border: 1px solid #ccc;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  z-index: 1000;
  width: 80px;
  cursor: pointer;
  border-radius:4px;
}



.context-menu-item {
  font-size: 12px;
  padding: 5px 10px;
  border-bottom: 1px solid #d8dce5;
}
.context-menu-item:hover {
  background: #e4e6eb;
}

</style>
