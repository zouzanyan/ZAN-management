<!--suppress HtmlUnknownTag -->
<template>
  <el-container class="home_container">
    <el-header style="box-shadow: 0px 2px 6px rgba(0, 21, 41, .35);">
      <div class="home_title">ZAN通用管理系统</div>
      <div class="home_userinfoContainer">
        <el-avatar class="el-icon-user-solid"></el-avatar>
        <el-dropdown style="position: relative;top: -12px;left: 10px;">
          <div class="el-dropdown-link home_userinfo">
            <span style="">{{ employeeName }}</span>

            <i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>
      <el-aside width="200px" style="box-shadow: 2px 0 6px rgba(0, 21, 41, .36); background-color: gray">
        <el-menu :default-active="$route.path" router class="el-menu-vertical-demo" background-color="#304156"
                 text-color="#fff" active-text-color="#1890ff" v-if="userPermission">
          <el-menu-item index="/">
            <i class="el-icon-menu"></i>
            <span slot="title">系统主页</span>
          </el-menu-item>

          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-goods"></i>
              <span>商品管理</span>
            </template>
            <el-menu-item index="/productCategoryManage"
                          v-if="userPermission.indexOf('/productCategoryManage') !== -1">
              分类管理
            </el-menu-item>
            <el-menu-item index="/productManage"
                          v-if="userPermission.indexOf('/productManage') !== -1">
              商品管理
            </el-menu-item>
          </el-submenu>


          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span>系统管理</span>
            </template>
            <el-menu-item index="/supplierManage"
                          v-if="userPermission.indexOf('/supplierManage') !== -1">
              供应商管理
            </el-menu-item>
            <el-menu-item index="/employeeManage"
                          v-if="userPermission.indexOf('/employeeManage') !== -1">
              用户管理
            </el-menu-item>
            <el-menu-item index="/roleAuthorityManage"
                          v-if="userPermission.indexOf('/roleAuthorityManage') !== -1">
              权限管理
            </el-menu-item>

          </el-submenu>

          <el-menu-item index="/about">
            <i class="el-icon-setting"></i>
            <span slot="title">关于</span>
          </el-menu-item>
        </el-menu>
<!--        <div><span></span></div>-->
      </el-aside>

      <el-container>
        <el-main height="">
          <!-- 面包屑导航 -->
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">主页</el-breadcrumb-item>
            <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
          </el-breadcrumb>
          <!-- main区域用于route到不同的页面 -->
          <router-view></router-view>
        </el-main>
        <el-footer height="">
          <div></div>
        </el-footer>
      </el-container>
    </el-container>
  </el-container>


</template>
<script>
// import axios from "axios";

export default {
  methods: {
    logout() {
      let _this = this;
      this.$confirm('注销登录吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        // 设置 cookie 失效
        document.cookie = "userinfo=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/";
        // 清除 local里的权限数据
        localStorage.clear()
        _this.$router.replace({path: '/login'})
      }).catch(() => {
        console.log('取消操作')
      })
    },


  },
  mounted: function () {

  },

  created() {
    if (document.cookie.length > 0) {
      var arr = document.cookie.split('; ') // 这里显示的格式需要切割一下自己可输出看下
      for (let i = 0; i < arr.length; i++) {
        let arr2 = arr[i].split('=') // 再次切割
        // 判断查找相对应的值
        if (arr2[0] === 'userinfo') {
          let arr3 = arr2[1].split(':');
          this.employeeId = arr3[0]
          // localStorage.setItem("employeeId", arr3[0])
        }
      }
      // 根据cookie里的employeeId获取employeeName
      axios.get(this.$store.state.globalhost + 'employee/' + this.employeeId
      ).then((response) => {
        console.log(response.data['employeeName'])
        this.employeeName = response.data['employeeName']
        // localStorage.setItem("employeeName", response.data['employeeName'])

      });
      this.userPermission = localStorage.getItem("user_authority")
    }
  }
  ,
  data() {
    return {
      userPermission: '',
      employeeId: '',
      employeeName: ''
    }
  }
}

</script>
<style>
/* .el-header {
    background-color: #0A64A4;
} */

.home_container {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
}

.el-header {
  background-color: #0A64A4;
  color: #ffffff;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.home_userinfo {
  color: #fff;
  cursor: pointer;
}


.el-aside {
  background-color: gray;
  height: 100vh;
}

.el-menu {
  border-right: none !important;
}
</style>