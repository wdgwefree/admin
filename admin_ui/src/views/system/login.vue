<template>
  <div class="login-wrap">
    <div class="login-container">
      <el-form :model="loginForm" :rules="loginRules" ref="loginForm" class="login-form">
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
      </el-form>
    </div>

    <!--  底部  -->
    <div class="el-login-footer">
      <span>© 2023 All Rights Reserved. 山东卓码信息技术有限公司</span>
    </div>
  </div>
</template>

<script>
import {User, Lock} from '@element-plus/icons-vue';
import {encrypt, decrypt} from '@/utils/jsencrypt';
import {loginP, getLoginInfoG} from '@/api/system/login.js';
import {setToken, setRememberMe, getRememberMe, removeRememberMe} from '@/utils/auth';
import {markRaw} from 'vue';
import {useUserInfoStore} from '@/stores/userInfo';

export default {
  name: "login",
  created() {
    this.getCookie();
  },
  data() {
    return {
      User: markRaw(User),
      Lock: markRaw(Lock),
      // 进度条
      loading: false,

      loginForm: {
        userAccount: "",
        password: "",
        rememberMe: false,
      },
      loginRules: {
        userAccount: [
          {required: true, message: "请输入账号", trigger: "blur"}
        ],
        password: [
          {required: true, message: "请输入密码", trigger: "blur"}
        ]
      }
    }
  },
  methods: {
    login() {
      //校验
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          // 如果是记住密码  将数据保存到cookie内 否则 删除掉cookie中的数据
          if (this.loginForm.rememberMe) {
            setRememberMe(this.loginForm.userAccount, encrypt(this.loginForm.password), this.loginForm.rememberMe);
          } else {
            removeRememberMe();
          }
          let data = {
            userAccount: this.loginForm.userAccount,
            password: encrypt(this.loginForm.password)
          }
          loginP(data).then(res => {
            if (res.code == this.$code.success) {
              setToken(res.data)
              this.$router.push("/");
              getLoginInfoG().then(res => {
                if (res.code == this.$code.success) {
                  useUserInfoStore().setUserInfoStore(res.data);
                }
              })
            }else{
              this.loading = false;
            }
          })
        }
      })
    },

    // 获取cookie里面的 用户 密码  是否记住密码
    getCookie() {
      const {userAccount, password, rememberMe} = getRememberMe();
      this.loginForm = {
        userAccount: userAccount === undefined ? this.loginForm.userAccount : userAccount,
        password: password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
      };
    },
  }
}
</script>

<style lang="scss" scoped>

.login-wrap {
  position: fixed;
  display: flex;
  bottom: 0;
  right: 0;
  width: 100%;
  height: 100%;
  background: url(../../assets/img/login_bg.jpg);
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
</style>