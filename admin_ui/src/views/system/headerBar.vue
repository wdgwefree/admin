<template>
  <div class="header_body">
    <!-- 折叠按钮 -->
    <div class="collapse_btn_c" @click="collapseChange">
      <el-icon v-if="sideBar.collapse">
        <Expand/>
      </el-icon>
      <el-icon v-else>
        <Fold/>
      </el-icon>
    </div>
    <div class="logo_title">admin管理系统</div>
    <div class="right_c">
      <div class="header-user-con">
        <!-- 用户头像 -->
        <el-avatar class="user-avatar_c" :size="30" :src="userInfoStore.avatar"/>
        <!-- 用户名下拉菜单 -->
        <el-dropdown class="user-name_c" trigger="click" @command="handleCommand">
        		<span class="el-dropdown-link">
        			{{ userInfoStore.userName }}
        			<el-icon class="el-icon_c">
        				<arrow-down/>
        			</el-icon>
        		</span>
          <template #dropdown>
            <el-dropdown-menu>
              <!--<a href="https://github.com/lin-xin/vue-manage-system" target="_blank">-->
              <!--  <el-dropdown-item>项目仓库</el-dropdown-item>-->
              <!--</a>-->
              <el-dropdown-item command="user">个人中心</el-dropdown-item>
              <el-dropdown-item divided command="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ElMessage, ElMessageBox} from 'element-plus'
import {removeToken} from '@/utils/auth'
import {logoutG} from '@/api/system/login.js';
import {useRouter} from 'vue-router';
import {useUserInfoStore} from '@/stores/userInfo';
import {useSideBarStore} from '@/stores/sidebar';

const sideBar = useSideBarStore();

const userInfoStore = useUserInfoStore();

const router = useRouter();

const handleCommand = (command) => {
  if (command == 'logout') {
    logout();
  } else if (command == 'user') {
    router.push("/personalInfo");
  }
};

// 注销
const logout = () => {
  ElMessageBox.confirm(
      '确定注销并退出系统吗？',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(() => {
    logoutG().then(res => {
      console.log("退出成功", res);
      removeToken();
      // 在路由切换完成后执行
      router.push("/login").then(() => {
      });
    }).catch(err => {
    })
    // 重置 Pinia 实例
  })
}

// 侧边栏折叠
const collapseChange = () => {
  sideBar.handleCollapse();
};

</script>

<style scoped lang="scss">

.header_body {
  position: relative;
  box-sizing: border-box;
  width: 100%;
  height: 35px;
  font-size: 22px;
  border-bottom: 1px solid #d8dce5;
}

.collapse_btn_c {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  float: left;
  padding: 0 21px;
  cursor: pointer;
}

.right_c {
  float: right;
  padding-right: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.user-avatar_c {
  margin-right: 5px;
}

.el-icon_c {
  margin-top: 10px;
  cursor: pointer;
}

.user-name_c {
  margin-top: -3px;
  cursor: pointer;
}

.logo_title {
  float: left;
  font-size: 18px;
  display: flex;
  align-items: center;
  height: 100%;
}

</style>