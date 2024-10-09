import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home.vue'
import Welcome from '@/views/Welcome.vue'
import About from '@/views/About.vue'
import Login from '@/views/Login.vue'
import SupplierManage from '@/views/SupplierManage.vue'
import ProductCategoryManage from '@/views/ProductCategoryManage.vue'
import ProductManage from '@/views/ProductManage.vue'
import EmployeeManage from '@/views/EmployeeManage.vue'
import RoleAuthorityManage from "@/views/RoleAuthorityManage";
import Auth from "@/views/Auth";

Vue.use(VueRouter)

const routes = [
    {
        path: '/login',
        component: Login
    },
    {
        path: '/',
        component: Home,
        // name: '主页',
        children: [{
            path: '', //空字符串路径表示默认子路由,渲染/home时也会渲染这个welcome
            component: Welcome
        }, {
            path: '/about',
            name: '关于',
            component: About
        }, {
            path: '/supplierManage',
            name: '供应商管理',
            component: SupplierManage
        }, {
            path: '/productCategoryManage',
            name: '分类管理',
            component: ProductCategoryManage
        }, {
            path: '/productManage',
            name: '商品管理',
            component: ProductManage
        }, {
            path: '/employeeManage',
            name: '用户管理',
            component: EmployeeManage
        }, {
            path: '/roleAuthorityManage',
            name: '权限管理',
            component: RoleAuthorityManage
        }, {
            path: '/403',
            name: '异常页面',
            component: Auth
        }
        ]
    },
]

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
})


router.beforeEach((to, from, next) => {
        console.log('路径:   ' + from.path + '->' + to.path)
        if (to.path === '/login' || to.path === '/403') {
            next();
            return
        }
        if (document.cookie.length === 0 || localStorage.length === 0) {
            //没登陆过
            next('/login')
        } else {
            // 登陆过
            let allowPage = localStorage.getItem("user_authority")
            if (allowPage.indexOf(to.path) !== -1) {
                console.log('是后端可通行页面')
                next()
            }else {
                next('/403')
            }

        }
    }

);

export default router
