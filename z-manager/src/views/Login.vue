<!--suppress ALL -->
<template>
  <div class="login-container">
    <el-form :model="ruleForm"
             status-icon
             ref="ruleForm"
             label-position="left"
             label-width="0px"
             class="demo-ruleForm login-page">
      <h3 class="title">系统登录</h3>
      <el-form-item>
        <el-input type="text"
                  v-model="ruleForm.phone"
                  auto-complete="off"
                  placeholder="账号"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-input type="password"
                  v-model="ruleForm.password"
                  auto-complete="off"
                  placeholder="密码"
        ></el-input>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" @click="handleSubmit" :loading="logining">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "Login",
  data() {
    return {
      logining: false,
      fullscreenLoading: true,
      ruleForm: {
        // phone: '13312345678',
        // password: '123456'
      }
    }
  },
  methods: {
    handleSubmit() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.logining = true
          let _this = this
          axios.post(this.$store.state.globalhost + 'login', _this.ruleForm).then(function (response) {
            _this.logining = false

            if (response.data.code == -1) {
              _this.$alert('账号不存在', '提示', {
                confirmButtonText: '确定'
              })
            }
            if (response.data.code == -2) {
              _this.$alert('密码错误', '提示', {
                confirmButtonText: '确定'
              })
            }
            if (response.data.code == 0) {
              let controllers = response.data.data.authorityControllerList
              // 把/和/about设置为可信目录
              controllers = controllers + ',/,/about'
              localStorage.setItem("user_authority", controllers)
              _this.$router.replace({path: '/'})

            }
          })
        } else {
          console.log('error submit!');
          return false;
        }
      })
    }
  }
};
</script>

<style scoped>
.login-container {
  width: 100%;
  height: 100%;
}

.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}
</style>
