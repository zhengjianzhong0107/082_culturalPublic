import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
    import yonghu from '@/views/modules/yonghu/list'
    import wenwuweihu from '@/views/modules/wenwuweihu/list'
    import wenwushouzang from '@/views/modules/wenwushouzang/list'
    import wenwuzhanshi from '@/views/modules/wenwuzhanshi/list'
    import wenwuleixing from '@/views/modules/wenwuleixing/list'
    import gonggaolan from '@/views/modules/gonggaolan/list'
    import menu from '@/views/modules/menu/list'
    import config from '@/views/modules/config/list'
    import wenwufengcun from '@/views/modules/wenwufengcun/list'


//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '系统首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '系统首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }
      ,{
	path: '/yonghu',
        name: '用户',
        component: yonghu
      }
      ,{
	path: '/wenwuweihu',
        name: '文物维护',
        component: wenwuweihu
      }
      ,{
	path: '/wenwushouzang',
        name: '文物收藏',
        component: wenwushouzang
      }
      ,{
	path: '/wenwuzhanshi',
        name: '文物展示',
        component: wenwuzhanshi
      }
      ,{
	path: '/wenwuleixing',
        name: '文物类型',
        component: wenwuleixing
      }
      ,{
	path: '/gonggaolan',
        name: '公告栏',
        component: gonggaolan
      }
      ,{
	path: '/menu',
        name: '菜单列表',
        component: menu
      }
      ,{
	path: '/config',
        name: '轮播图管理',
        component: config
      }
      ,{
	path: '/wenwufengcun',
        name: '文物封存',
        component: wenwufengcun
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '系统首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}
export default router;
