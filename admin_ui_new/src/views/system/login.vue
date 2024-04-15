<template>
  <div class="login-wrap">
    <div class="login-container">
      <el-form :model="loginForm" :rules="loginRules" class="login-form">
        <!-- 标题 -->
        <div class="title">admin管理系统</div>
        <el-form-item prop="userAccount">
          <el-input v-model="loginForm.userAccount" placeholder="账号" :prefix-icon="User">
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" type="password" placeholder="密码" :prefix-icon="Lock"></el-input>
        </el-form-item>
        <!-- 记住密码 -->
        <el-checkbox class="login-tip" v-model="loginForm.rememberMe">记住密码</el-checkbox>
        <el-form-item>
          <el-button style="width: 100%;" type="primary" @click="login">
            <span v-if="!loading">登 录</span>
            <span v-else>登 录 中...</span>
          </el-button>
        </el-form-item>
        <!--<div class="forgot-password">-->
        <!--  <router-link to="/forgot-password">忘记密码？</router-link>-->
        <!--</div>-->
      </el-form>
    </div>
    <!--  底部  -->
    <div class="el-login-footer">
      <span>© 2023 All Rights Reserved. 山东卓码信息技术有限公司</span>
    </div>
  </div>
</template>

<script setup lang="ts">
import {Lock, User} from '@element-plus/icons-vue';
import {ref, reactive} from "vue";
import {useRouter} from 'vue-router';

const router = useRouter();

const loginForm = reactive({
  userAccount: '',
  password: '',
  rememberMe: false
});
const loading = ref(false);

const loginRules = {
  userAccount: [{required: true, message: '请输入账号', trigger: 'blur'}],
  password: [{required: true, message: '请输入密码', trigger: 'blur'}],
};
const login = () => {
  // 这里执行登录逻辑，可以使用异步请求等方式
  loading.value = true;
  // 模拟登录过程
  setTimeout(() => {
    // 登录成功后跳转到首页，这里使用了 Vue Router
    router.push('/home');
    loading.value = false;
  }, 1000);
};
</script>

<style lang="scss" scoped>
.login-wrap {
  position: fixed;
  display: flex;
  bottom: 0;
  right: 0;
  width: 100%;
  height: 100%;
  background: url('@/assets/img/login_bg.jpg');
  background-size: cover;
  justify-content: center;
  align-items: center;
}

.login-container {
  border-radius: 10px;
  background: #ffffff;
  width: 350px;
  height: 260px;
  display: flex;
  justify-content: center;
  align-items: center;

  .login-form {
    width: 80%;

    .el-form-item {

    }
  }
}

.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #eee;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}

.title {
  text-align: center;
  //color: #606266;
  font-weight: bold;
  letter-spacing: 1px;
  margin-bottom: 15px;
}

.login-tip {
  margin-bottom: 10px;
}

.forgot-password {
  margin-top: 10px;
  text-align: right;

  a {
    color: #409eff;
  }
}
</style>
