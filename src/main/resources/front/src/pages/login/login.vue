<template>
<div>
	<div class="container" :style='{"minHeight":"100vh","width":"100%","alignItems":"flex-start","background":"url(http://codegen.caihongy.cn/20230128/effb535f5a6142daa256f40fb8542933.jpg) no-repeat center bottom / 100% 100%,#fff","justifyContent":"flex-start","display":"flex"}'>
		<el-form ref="loginForm" :model="loginForm" :style='{"padding":"60px 0 40px","boxShadow":"0 0px 0px rgba(64, 158, 255, .8)","margin":"60px 0 0 12%","borderRadius":"o","background":"rgba(255,255,255,1)","width":"44%","height":"100%"}' :rules="rules">
			<div v-if="false" :style='{"margin":"0 0 10px 0","color":"rgba(64, 158, 255, 1)","textAlign":"center","width":"100%","lineHeight":"44px","fontSize":"20px","textShadow":"4px 4px 2px rgba(64, 158, 255, .5)"}'>USER / LOGIN</div>
			<div v-if="true" :style='{"padding":"0 0 10px ","margin":"20px auto 40px","color":"#888","textAlign":"center","background":"url(http://codegen.caihongy.cn/20230128/4cbad5a2e6f444188a2c08cb74dc8160.png) no-repeat center bottom","width":"80%","lineHeight":"50px","fontSize":"24px","fontWeight":"500"}'>古文物收藏管理系统登录</div>
			<el-form-item v-if="loginType==1" class="list-item" :style='{"width":"60%","margin":"0 auto 20px","flexWrap":"wrap","display":"flex"}' prop="username">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>账号：</div>
				<input :style='{"border":"2px solid #ddd","padding":"0 10px","color":"#333","borderRadius":"8px","flex":"1","background":"#fff","width":"100%","fontSize":"14px","height":"44px"}' v-model="loginForm.username" placeholder="请输入账户">
			</el-form-item>
			<el-form-item v-if="loginType==1" class="list-item" :style='{"width":"60%","margin":"0 auto 20px","flexWrap":"wrap","display":"flex"}' prop="password">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"rgba(64, 158, 255, 1)"}'>密码：</div>
				<input :style='{"border":"2px solid #ddd","padding":"0 10px","color":"#333","borderRadius":"8px","flex":"1","background":"#fff","width":"100%","fontSize":"14px","height":"44px"}' v-model="loginForm.password" placeholder="请输入密码" type="password">
			</el-form-item>
			<el-form-item v-if="roles.length>1" class="list-type" :style='{"width":"50%","margin":"20px auto 10px"}' prop="role">
				<el-radio v-model="loginForm.tableName" :label="item.tableName" v-for="(item, index) in roles" :key="index" @change.native="getCurrentRow(item)">{{item.roleName}}</el-radio>
			</el-form-item>
			<el-form-item :style='{"padding":"10px 0 20px","margin":"0","alignItems":"center","top":"0","textAlign":"right","left":"0","background":"linear-gradient(180deg, rgba(255,255,255,1) 0%, rgba(178,213,232,1) 100%)","display":"flex","width":"10%","position":"absolute","height":"100%"}'>
				<el-button v-if="loginType==1" :style='{"border":"0","cursor":"pointer","padding":"0","margin":"0 auto","outline":"none","color":"#288bbf","borderRadius":"0","background":"none","width":"100%","fontSize":"24px","height":"100vh"}' @click="submitForm('loginForm')">登录</el-button>
				<el-button v-if="loginType==1" :style='{"border":"0","cursor":"pointer","padding":"0 0 ","margin":"0","color":"#666","bottom":"14%","marginLeft":"-50px","outline":"none","borderRadius":"8px","left":"450%","background":"none","width":"100px","fontSize":"16px","lineHeight":"1","position":"absolute","height":"40px"}' @click="resetForm('loginForm')">重置</el-button>
                <el-upload v-if="loginType==2" :action="baseUrl + 'file/upload'" :show-file-list="false" :on-success="faceLogin">
                    <el-button :style='{"border":"0","cursor":"pointer","padding":"0","margin":"0 auto","outline":"none","color":"#288bbf","borderRadius":"0","background":"none","width":"100%","fontSize":"24px","height":"100vh"}'>人脸识别登录</el-button>
                </el-upload>
			</el-form-item>
			<div :style='{"width":"100%","margin":"20px 0 0 0","alignItems":"center","flexWrap":"wrap","justifyContent":"center","display":"flex"}'>
			<router-link :style='{"cursor":"pointer","border":"2px solid #ddd","padding":"0 10px","margin":"0 10px 0 0","color":"#288bbf","borderRadius":"8px","textAlign":"center","background":"#eee","fontSize":"16px","textDecoration":"none","minWidth":"100px","lineHeight":"40px"}' :to="{path: '/register', query: {role: item.tableName,pageFlag:'register'}}" v-if="item.hasFrontRegister=='是'" v-for="(item, index) in roles" :key="index">注册{{item.roleName.replace('注册','')}}</router-link>
			</div>
		</el-form>
    </div>
</div>
</template>

<script>

export default {
	//数据集合
	data() {
		return {
            baseUrl: this.$config.baseUrl,
            loginType: 1,
			roleMenus: [{"backMenu":[{"child":[{"appFrontIcon":"cuIcon-list","buttons":["新增","查看","修改","删除"],"menu":"用户","menuJump":"列表","tableName":"yonghu"}],"menu":"用户管理"},{"child":[{"appFrontIcon":"cuIcon-taxi","buttons":["新增","查看","修改","删除"],"menu":"文物展示","menuJump":"列表","tableName":"wenwuzhanshi"}],"menu":"文物展示管理"},{"child":[{"appFrontIcon":"cuIcon-skin","buttons":["新增","查看","修改","删除"],"menu":"文物类型","menuJump":"列表","tableName":"wenwuleixing"}],"menu":"文物类型管理"},{"child":[{"appFrontIcon":"cuIcon-present","buttons":["新增","查看","修改","删除"],"menu":"文物收藏","menuJump":"列表","tableName":"wenwushouzang"}],"menu":"文物收藏管理"},{"child":[{"appFrontIcon":"cuIcon-cardboard","buttons":["新增","查看","修改","删除"],"menu":"文物维护","menuJump":"列表","tableName":"wenwuweihu"}],"menu":"文物维护管理"},{"child":[{"appFrontIcon":"cuIcon-camera","buttons":["新增","查看","修改","删除"],"menu":"文物封存","menuJump":"列表","tableName":"wenwufengcun"}],"menu":"文物封存管理"},{"child":[{"appFrontIcon":"cuIcon-paint","buttons":["新增","查看","修改","删除"],"menu":"公告栏","menuJump":"列表","tableName":"gonggaolan"}],"menu":"公告栏管理"},{"child":[{"appFrontIcon":"cuIcon-wenzi","buttons":["新增","查看","修改","删除"],"menu":"轮播图管理","tableName":"config"},{"appFrontIcon":"cuIcon-keyboard","buttons":["查看","编辑名称","编辑父级","删除"],"menu":"菜单列表","tableName":"menu"}],"menu":"系统管理"}],"frontMenu":[{"child":[{"appFrontIcon":"cuIcon-list","buttons":["查看"],"menu":"文物展示列表","menuJump":"列表","tableName":"wenwuzhanshi"}],"menu":"文物展示模块"},{"child":[{"appFrontIcon":"cuIcon-album","buttons":["查看"],"menu":"公告栏列表","menuJump":"列表","tableName":"gonggaolan"}],"menu":"公告栏模块"}],"hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"否","hasFrontRegister":"否","roleName":"管理员","tableName":"users"},{"backMenu":[{"child":[{"appFrontIcon":"cuIcon-present","buttons":["新增","查看","修改","删除"],"menu":"文物收藏","menuJump":"列表","tableName":"wenwushouzang"}],"menu":"文物收藏管理"},{"child":[{"appFrontIcon":"cuIcon-cardboard","buttons":["新增","查看","修改","删除"],"menu":"文物维护","menuJump":"列表","tableName":"wenwuweihu"}],"menu":"文物维护管理"},{"child":[{"appFrontIcon":"cuIcon-camera","buttons":["新增","查看","修改","删除"],"menu":"文物封存","menuJump":"列表","tableName":"wenwufengcun"}],"menu":"文物封存管理"}],"frontMenu":[{"child":[{"appFrontIcon":"cuIcon-list","buttons":["查看"],"menu":"文物展示列表","menuJump":"列表","tableName":"wenwuzhanshi"}],"menu":"文物展示模块"},{"child":[{"appFrontIcon":"cuIcon-album","buttons":["查看"],"menu":"公告栏列表","menuJump":"列表","tableName":"gonggaolan"}],"menu":"公告栏模块"}],"hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"否","hasFrontRegister":"否","roleName":"用户","tableName":"yonghu"}],
			loginForm: {
				username: '',
				password: '',
				tableName: '',
				code: '',
			},
			role: '',
            roles: [],
			rules: {
				username: [
					{ required: true, message: '请输入账户', trigger: 'blur' }
				],
				password: [
					{ required: true, message: '请输入密码', trigger: 'blur' }
				]
			},
			codes: [{
				num: 1,
				color: '#000',
				rotate: '10deg',
				size: '16px'
			}, {
				num: 2,
				color: '#000',
				rotate: '10deg',
				size: '16px'
			}, {
				num: 3,
				color: '#000',
				rotate: '10deg',
				size: '16px'
			}, {
				num: 4,
				color: '#000',
				rotate: '10deg',
				size: '16px'
			}]
		}
	},
	created() {
        for(let item in this.roleMenus) {
            if(this.roleMenus[item].hasFrontLogin=='是') {
                this.roles.push(this.roleMenus[item]);
            }
        }
	},
	mounted() {
	},
    //方法集合
    methods: {
		randomString() {
			var len = 4;
			var chars = [
			  'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			  'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			  'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			  'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			  'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2',
			  '3', '4', '5', '6', '7', '8', '9'
			]
			var colors = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f']
			var sizes = ['14', '15', '16', '17', '18']
			
			var output = []
			for (var i = 0; i < len; i++) {
			  // 随机验证码
			  var key = Math.floor(Math.random() * chars.length)
			  this.codes[i].num = chars[key]
			  // 随机验证码颜色
			  var code = '#'
			  for (var j = 0; j < 6; j++) {
			    var key = Math.floor(Math.random() * colors.length)
			    code += colors[key]
			  }
			  this.codes[i].color = code
			  // 随机验证码方向
			  var rotate = Math.floor(Math.random() * 45)
			  var plus = Math.floor(Math.random() * 2)
			  if (plus == 1) rotate = '-' + rotate
			  this.codes[i].rotate = 'rotate(' + rotate + 'deg)'
			  // 随机验证码字体大小
			  var size = Math.floor(Math.random() * sizes.length)
			  this.codes[i].size = sizes[size] + 'px'
			}
		},
      getCurrentRow(row) {
        this.role = row.roleName;
      },
      submitForm(formName) {
        if (this.roles.length!=1) {
            if (!this.role) {
                this.$message.error("请选择登录用户类型");
                return false;
            }
        } else {
            this.role = this.roles[0].roleName;
            this.loginForm.tableName = this.roles[0].tableName;
        }
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$http.get(`${this.loginForm.tableName}/login`, {params: this.loginForm}).then(res => {
              if (res.data.code === 0) {
                localStorage.setItem('Token', res.data.token);
                localStorage.setItem('UserTableName', this.loginForm.tableName);
                localStorage.setItem('username', this.loginForm.username);
                localStorage.setItem('adminName', this.loginForm.username);
                localStorage.setItem('sessionTable', this.loginForm.tableName);
                localStorage.setItem('role', this.role);
                localStorage.setItem('keyPath', this.$config.indexNav.length+2);
                this.$router.push('/index/center');
                this.$message({
                  message: '登录成功',
                  type: 'success',
                  duration: 1500,
                });
              } else {
                this.$message.error(res.data.msg);
              }
            });
          } else {
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.container {
		position: relative;
		background: url(http://codegen.caihongy.cn/20230128/effb535f5a6142daa256f40fb8542933.jpg) no-repeat center bottom / 100% 100%,#fff;
		
		.el-form-item {
		  & /deep/ .el-form-item__content {
		    width: 100%;
		  }
		}
		
		.list-item /deep/ .el-input .el-input__inner {
			border: 2px solid #ddd;
			border-radius: 8px;
			padding: 0 10px;
			color: #333;
			flex: 1;
			background: #fff;
			width: 100%;
			font-size: 14px;
			height: 44px;
		}
		
		.list-code /deep/ .el-input .el-input__inner {
			border: 2px solid #ddd;
			border-radius: 8px;
			padding: 0 10px;
			outline: none;
			color: #333;
			background: #fff;
			display: inline-block;
			vertical-align: middle;
			width: calc(100% - 110px);
			font-size: 14px;
			height: 44px;
		}
		
		.list-type /deep/ .el-radio__input .el-radio__inner {
			background: rgba(53, 53, 53, 0);
			border-color: #999;
		}
		.list-type /deep/ .el-radio__input.is-checked .el-radio__inner {
			background: #288bbf;
			border-color: #288bbf;
		}
		.list-type /deep/ .el-radio__label {
			color: #999;
			font-size: 14px;
		}
		.list-type /deep/ .el-radio__input.is-checked+.el-radio__label {
			color: #288bbf;
			font-size: 14px;
		}
	}
</style>
