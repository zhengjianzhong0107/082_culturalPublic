import VueRouter from 'vue-router'

//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import wenwuzhanshiList from '../pages/wenwuzhanshi/list'
import wenwuzhanshiDetail from '../pages/wenwuzhanshi/detail'
import wenwuzhanshiAdd from '../pages/wenwuzhanshi/add'
import wenwuleixingList from '../pages/wenwuleixing/list'
import wenwuleixingDetail from '../pages/wenwuleixing/detail'
import wenwuleixingAdd from '../pages/wenwuleixing/add'
import wenwushouzangList from '../pages/wenwushouzang/list'
import wenwushouzangDetail from '../pages/wenwushouzang/detail'
import wenwushouzangAdd from '../pages/wenwushouzang/add'
import wenwuweihuList from '../pages/wenwuweihu/list'
import wenwuweihuDetail from '../pages/wenwuweihu/detail'
import wenwuweihuAdd from '../pages/wenwuweihu/add'
import wenwufengcunList from '../pages/wenwufengcun/list'
import wenwufengcunDetail from '../pages/wenwufengcun/detail'
import wenwufengcunAdd from '../pages/wenwufengcun/add'
import gonggaolanList from '../pages/gonggaolan/list'
import gonggaolanDetail from '../pages/gonggaolan/detail'
import gonggaolanAdd from '../pages/gonggaolan/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'wenwuzhanshi',
					component: wenwuzhanshiList
				},
				{
					path: 'wenwuzhanshiDetail',
					component: wenwuzhanshiDetail
				},
				{
					path: 'wenwuzhanshiAdd',
					component: wenwuzhanshiAdd
				},
				{
					path: 'wenwuleixing',
					component: wenwuleixingList
				},
				{
					path: 'wenwuleixingDetail',
					component: wenwuleixingDetail
				},
				{
					path: 'wenwuleixingAdd',
					component: wenwuleixingAdd
				},
				{
					path: 'wenwushouzang',
					component: wenwushouzangList
				},
				{
					path: 'wenwushouzangDetail',
					component: wenwushouzangDetail
				},
				{
					path: 'wenwushouzangAdd',
					component: wenwushouzangAdd
				},
				{
					path: 'wenwuweihu',
					component: wenwuweihuList
				},
				{
					path: 'wenwuweihuDetail',
					component: wenwuweihuDetail
				},
				{
					path: 'wenwuweihuAdd',
					component: wenwuweihuAdd
				},
				{
					path: 'wenwufengcun',
					component: wenwufengcunList
				},
				{
					path: 'wenwufengcunDetail',
					component: wenwufengcunDetail
				},
				{
					path: 'wenwufengcunAdd',
					component: wenwufengcunAdd
				},
				{
					path: 'gonggaolan',
					component: gonggaolanList
				},
				{
					path: 'gonggaolanDetail',
					component: gonggaolanDetail
				},
				{
					path: 'gonggaolanAdd',
					component: gonggaolanAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
