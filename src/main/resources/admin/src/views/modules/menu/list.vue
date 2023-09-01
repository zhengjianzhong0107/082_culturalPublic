<template>
	<div class="main-content" :style='{"padding":"30px 0 0 0"}'>
		<!-- 列表页 -->
		<template v-if="showFlag">
			<el-form class="center-form-pv" :style='{"width":"180px","margin":"0 0 20px 20px","position":"absolute","zIndex":"1003"}' :inline="true" :model="searchForm">
				<el-row :style='{"display":"block"}' >
					<div :style='{"margin":"0 0px 15px 0","display":"inline-block"}' class="select">
						<label :style='{"margin":"0 10px 0 0","color":"#666","textAlign":"center","display":"inline-block","width":"auto","lineHeight":"40px","fontSize":"14px","fontWeight":"500","height":"40px"}' class="item-label">角色</label>
						<el-select v-model="roleName" placeholder="请选择角色">
							<el-option v-for="(item,index) in roleNameList" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<el-button :style='{"border":"2px solid #3CB5AF","cursor":"pointer","padding":"0 20px","outline":"none","margin":"0px 0 5px 0","color":"#3CB5AF","borderRadius":"40px","background":"#fff","width":"160px","fontSize":"14px","height":"40px"}' type="success" @click="search()">查询</el-button>
				</el-row>

				<el-row :style='{"width":"170px","margin":"10px 0 0","flexDirection":"column","display":"flex"}'>




				</el-row>
			</el-form>
			
			<!-- <div> -->
				<el-table class="tables"
					:stripe='false'
					:style='{"padding":"0","boxShadow":" 0px 4px 10px 0px rgba(0,0,0,0.3020)","borderColor":"#eee","margin":"0 0 15px 210px","borderWidth":"1px 0 0 1px","background":"#fff","width":"calc(100% - 230px)","borderStyle":"solid"}' 
					v-if="isAuth('menu','查看')"
					:data="backChildMenuList"
					v-loading="dataListLoading"
				@selection-change="selectionChangeHandler">
					<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='false' label="索引" type="index" width="50" />
					<el-table-column :resizable='true' :sortable='false' prop="parentMenuName" label="一级菜单">
						<template slot-scope="scope">
							{{scope.row.parentMenuName}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='false' prop="menu" header-align="center" label="二级菜单">
						<template slot-scope="scope">
							{{scope.row.menu}}
						</template>
					</el-table-column>
					<el-table-column width="300" label="操作">
						<template slot-scope="scope">
							<el-button :style='{"border":"1px solid rgba(135, 154, 108, 1)","cursor":"pointer","padding":"0 10px","margin":"0 10px 5px 0","outline":"none","color":"rgba(135, 154, 108, 1)","borderRadius":"4px","background":"#fff","width":"auto","fontSize":"14px","height":"32px"}' v-if=" isAuth('menu','修改')" type="primary" size="mini" @click="addOrUpdateHandler(scope.row.id)">修改</el-button>





							<el-button :style='{"border":"1px solid rgba(135, 154, 108, 1)","cursor":"pointer","padding":"0 10px","margin":"0 10px 5px 0","outline":"none","color":"rgba(135, 154, 108, 1)","borderRadius":"4px","background":"#fff","width":"auto","fontSize":"14px","height":"32px"}' v-if="isAuth('menu','编辑名称')" type="primary" size="mini" @click="updateMenuNamePage(scope.row)">编辑名称</el-button>
							<el-button :style='{"border":"1px solid rgba(135, 154, 108, 1)","cursor":"pointer","padding":"0 10px","margin":"0 10px 5px 0","outline":"none","color":"rgba(135, 154, 108, 1)","borderRadius":"4px","background":"#fff","width":"auto","fontSize":"14px","height":"32px"}' v-if="isAuth('menu','编辑父级')" type="primary" size="mini" @click="updateMenuLevelPage(scope.row)">编辑父级</el-button>
							<el-button :style='{"border":"1px solid rgba(135, 154, 108, 1)","cursor":"pointer","padding":"0 10px","margin":"0 10px 5px 0","outline":"none","color":"rgba(135, 154, 108, 1)","borderRadius":"4px","background":"#fff","width":"auto","fontSize":"14px","height":"32px"}' v-if="isAuth('menu','删除')" type="danger" size="mini" @click="deleteMenu(scope.row)">删除</el-button>
						</template>
					</el-table-column>
				</el-table>
				<el-pagination
					@size-change="sizeChangeHandle"
					@current-change="currentChangeHandle"
					:current-page="pageIndex"
					background
					:page-sizes="[10, 20, 30, 50]"
					:page-size="pageSize"
					:layout="layouts.join()"
					:total="totalPage"
					prev-text="<"
					next-text=">"
					:hide-on-single-page="false"
					:style='{"padding":"0","margin":"20px 0 10px 210px","whiteSpace":"nowrap","color":"#333","textAlign":"center","width":"calc(100% - 230px)","fontWeight":"500"}'
				></el-pagination>
			<!-- </div> -->
		</template>
		
		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>




		<el-dialog title="修改菜单" :visible.sync="updateMenuNameVisiable" width="50%">
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item label="一级菜单">
					<el-input v-model="checkParentMenuName"></el-input>
				</el-form-item>
				<el-form-item label="二级菜单">
					<el-input v-model="checkChildMenuName"></el-input>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="updateMenuNamePage">取 消</el-button>
				<el-button type="primary" @click="updateMenuName">确 定</el-button>
			</span>
		</el-dialog>

		<el-dialog title="修改菜单" :visible.sync="updateMenuLevelVisiable" width="50%">
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item label="一级菜单">
					<el-select v-model="checkParentMenuName" placeholder="请选择一级菜单">
						<el-option v-for="(item,index) in parentMenuNameList" v-bind:key="index" :label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="updateMenuLevelPage">取 消</el-button>
				<el-button type="primary" @click="updateMenuLevel">确 定</el-button>
			</span>
		</el-dialog>

	</div>
</template>

<script>
import axios from 'axios'
import AddOrUpdate from "./add-or-update";
export default {
  data() {
    return {
      searchForm: {
        key: ""
      },
      form:{},
	checkParentMenuName: '',
	oldCheckParentMenuName: '',
	checkChildMenuName: '',
	oldCheckChildMenuName: '',
	menuList: [],
	roleName: '管理员',
	roleNameList: [],
	backMenuList: [],
	backChildMenuList: [],
	parentMenuNameList: [],
	updateMenuNameVisiable: false,
	updateMenuLevelVisiable: false,
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: true,
      sfshVisiable: false,
      shForm: {},
      chartVisiable: false,
      chartVisiable1: false,
      chartVisiable2: false,
      chartVisiable3: false,
      chartVisiable4: false,
      chartVisiable5: false,
      addOrUpdateFlag:false,
      layouts: ["total","prev","pager","next","sizes","jumper"],

    };
  },
  created() {
    this.init();
    this.getDataList();
    this.contentStyleChange()
  },
  mounted() {
  },
  filters: {
    htmlfilter: function (val) {
      return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
    }
  },
  components: {
    AddOrUpdate,
  },
  methods: {

	// 修改菜单名称页面
	updateMenuNamePage(row) {
		this.updateMenuNameVisiable = !this.updateMenuNameVisiable;
		if (row) {
			this.checkParentMenuName = row.parentMenuName;
			this.oldCheckParentMenuName = row.parentMenuName;
			this.checkChildMenuName = row.menu;
			this.oldCheckChildMenuName = row.menu;
		}
	},
	// 修改菜单名称
	updateMenuName() {
		for (var i in this.menuList) {
			if (this.menuList[i].roleName == this.roleName) {
				let oldBackMenuList = this.menuList[i].backMenu;
				let parentMenuName = '';
				let childMenuList = [];
				for (var j in oldBackMenuList) {
					parentMenuName = oldBackMenuList[j].menu;
					childMenuList = oldBackMenuList[j].child;
					if (this.oldCheckParentMenuName == parentMenuName) {
						if (parentMenuName != this.checkParentMenuName) {
							//修改一级菜单名字
							oldBackMenuList[j].menu = this.checkParentMenuName;
						}
						for (var k in childMenuList) {
							if (this.oldCheckChildMenuName == childMenuList[k].menu) {
								if (this.checkChildMenuName != childMenuList[k].menu) {
									//修改二级菜单名字
									childMenuList[k].menu = this.checkChildMenuName;
									childMenuList[k].parentMenuName = this.checkParentMenuName;
								}
							}
						}
					}
				}
			}
		}
		this.$http({
			url: "menu/update",
			method: "post",
			data: {
				"id": 1,
				"menujson": JSON.stringify(this.menuList)
			}
		}).then(({
			data
		}) => {
			if (data && data.code === 0) {
				this.$message({
					message: "操作成功",
					type: "success",
					duration: 1500,
					onClose: () => {
						this.getDataList(this.roleName);
						this.updateMenuNamePage();
					}
				});
			} else {
				this.$message.error(data.msg);
			}
		});
	},
	// 修改一级菜单页面
	updateMenuLevelPage(row) {
		this.updateMenuLevelVisiable = !this.updateMenuLevelVisiable;
		if (row) {
			this.checkParentMenuName = row.parentMenuName;
			this.oldCheckParentMenuName = row.parentMenuName;
			this.checkChildMenuName = row.menu;
		}
	},
	// 修改一级菜单
	updateMenuLevel() {
		for (var i in this.menuList) {
			if (this.menuList[i].roleName == this.roleName) {
				debugger
				let oldBackMenuList = this.menuList[i].backMenu;
				let parentMenuName = '';
				let childMenuList = [];
				for (var j in oldBackMenuList) {
					parentMenuName = oldBackMenuList[j].menu;
					childMenuList = oldBackMenuList[j].child;
					if (this.oldCheckParentMenuName == parentMenuName) {
						//获取原父级菜单下的子菜单
						if (parentMenuName != this.checkParentMenuName) {
							let fromChildMenu;
							for (var k in childMenuList) {
								if (this.checkChildMenuName == childMenuList[k].menu) {
									fromChildMenu = childMenuList[k];
									let toChildMenuList = [];
									for (var l in oldBackMenuList) {
										if (this.checkParentMenuName == oldBackMenuList[l].menu) {
											toChildMenuList = oldBackMenuList[l].child;
											toChildMenuList.push(fromChildMenu);
											break;
										}
									}
									childMenuList.splice(k, 1)
									break;
								}
							}
						}
						break;
					}
				}
			}
		}
		this.$http({
			url: "menu/update",
			method: "post",
			data: {
				"id": 1,
				"menujson": JSON.stringify(this.menuList)
			}
		}).then(({
			data
		}) => {
			if (data && data.code === 0) {
				this.$message({
					message: "操作成功",
					type: "success",
					duration: 1500,
					onClose: () => {
						this.getDataList(this.roleName);
						this.updateMenuLevelPage();
					}
				});
			} else {
				this.$message.error(data.msg);
			}
		});
	},
    contentStyleChange() {
      this.contentPageStyleChange()
    },
    // 分页
    contentPageStyleChange(){
      let arr = []

      // if(this.contents.pageTotal) arr.push('total')
      // if(this.contents.pageSizes) arr.push('sizes')
      // if(this.contents.pagePrevNext){
      //   arr.push('prev')
      //   if(this.contents.pagePager) arr.push('pager')
      //   arr.push('next')
      // }
      // if(this.contents.pageJumper) arr.push('jumper')
      // this.layouts = arr.join()
      // this.contents.pageEachNum = 10
    },








    init () {
    },
    search() {
      this.pageIndex = 1;
      this.getDataList(this.roleName);
    },

	// 获取菜单列表
	getDataList(rn) {
		if (rn == null) rn = "管理员";
		this.roleNameList = [];
		this.parentMenuNameList = [];
		this.backChildMenuList = [];
		this.dataListLoading = true;
		let params = {
			page: this.pageIndex,
			limit: this.pageSize,
			sort: 'id',
		}
		this.$http({
			url: "menu/page",
			method: "get",
			params: params
		}).then(({
			data
		}) => {
			if (data && data.code === 0) {
				this.dataList = data.data.list;
				this.menuList = eval('(' + data.data.list[0].menujson + ')');
				for (var i in this.menuList) {
					this.roleNameList.push(this.menuList[i].roleName);
					if (this.menuList[i].roleName == rn) {
						this.backMenuList = this.menuList[i].backMenu;
						let parentMenuName = '';
						let childMenuList = [];
						for (var j in this.backMenuList) {
							this.parentMenuNameList.push(this.backMenuList[j].menu);
							parentMenuName = this.backMenuList[j].menu;
							childMenuList = this.backMenuList[j].child;
							// console.log(parentMenuName);
							// console.log(childMenuList);
							if (childMenuList.length > 0) {
								for (var k in childMenuList) {
									childMenuList[k].parentMenuName = parentMenuName;
									this.backChildMenuList.push(childMenuList[k]);
								}
							} else {
								let parentNode = {};
								parentNode.parentMenuName = parentMenuName;
								this.backChildMenuList.push(parentNode);
							}

						}
					}
				}
				this.totalPage = data.data.total;
			} else {
				this.dataList = [];
				this.totalPage = 0;
			}
			// console.log(this.roleNameList)
			// console.log(this.backChildMenuList)
			// console.log(this.parentMenuNameList);
			this.dataListLoading = false;
		});
	},
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    },
    // 添加/修改
    addOrUpdateHandler(id,type) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.crossAddOrUpdateFlag = false;
      if(type!='info'){
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id,type);
      });
    },
    // 下载
    download(file){
      window.open(`${file}`)
    },
	// 删除
	deleteMenu(row) {
		this.$confirm(`确定进行删除操作?`, "提示", {
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			type: "warning"
		}).then(() => {
			debugger
			if (row) {
				this.checkParentMenuName = row.parentMenuName;
				let delFlag = false;
				for (var i in this.menuList) {
					if (this.menuList[i].roleName == this.roleName) {
						let oldBackMenuList = this.menuList[i].backMenu;
						let parentMenuName = '';
						let childMenuList = [];
						for (var j in oldBackMenuList) {
							parentMenuName = oldBackMenuList[j].menu;
							childMenuList = oldBackMenuList[j].child;
							if (this.checkParentMenuName == parentMenuName) {
								if(childMenuList.length>0) {
									this.$message.error("存在二级菜单，无法删除。");
									break;
								}
								oldBackMenuList.splice(j, 1)
								delFlag = true;
								break;
							}
						}
						break;
					}
				}
				if(delFlag) {
					this.$http({
						url: "menu/update",
						method: "post",
						data: {
							"id": 1,
							"menujson": JSON.stringify(this.menuList)
						}
					}).then(({
						data
					}) => {
						if (data && data.code === 0) {
							this.$message({
								message: "操作成功",
								type: "success",
								duration: 1500,
								onClose: () => {
									this.getDataList(this.roleName);
								}
							});
						} else {
							this.$message.error(data.msg);
						}
					});
				}
			}
		})
	},


  }

};
</script>
<style lang="scss" scoped>
	
	.center-form-pv {
	  .el-date-editor.el-input {
	    width: auto;
	  }
	}
	
	.el-input {
	  width: auto;
	}
	
	// form
	.center-form-pv .el-input /deep/ .el-input__inner {
				border: 2px solid #3CB5AF;
				border-radius: 4px;
				padding: 0 12px;
				box-shadow: 5px 2px 0px 0px #3CB5AF;
				outline: none;
				color: #333;
				width: 160px;
				font-size: 14px;
				height: 40px;
			}
	
	.center-form-pv .el-select /deep/ .el-input__inner {
				border: 2px solid #3CB5AF;
				border-radius: 4px;
				padding: 0 10px;
				box-shadow: 5px 2px 0px 0px #3CB5AF;
				outline: none;
				color: #333;
				width: 160px;
				font-size: 14px;
				height: 40px;
			}
	
	.center-form-pv .el-date-editor /deep/ .el-input__inner {
				border: 2px solid #3CB5AF;
				border-radius: 4px;
				padding: 0 10px 0 30px;
				box-shadow: 5px 2px 0px 0px #3CB5AF;
				outline: none;
				color: #333;
				width: 160px;
				font-size: 14px;
				height: 40px;
			}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
				color: #fff;
				font-weight: 500;
				width: 100%;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
				background: #fff;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
				padding: 12px 0;
				background: rgba(27, 167, 166, 1);
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: center;
			}

	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
				padding: 0 10px;
				word-wrap: normal;
				word-break: break-all;
				white-space: normal;
				font-weight: bold;
				display: inline-block;
				vertical-align: middle;
				width: 100%;
				line-height: 24px;
				position: relative;
				text-overflow: ellipsis;
			}

	
	.el-table /deep/ .el-table__body-wrapper tbody {
				width: 100%;
			}

	.el-table /deep/ .el-table__body-wrapper tbody tr {
				background: #fff;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 12px 0;
				color: #999;
				background: #fff;
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: center;
			}
	
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
				padding: 12px 0;
				color: #000;
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: center;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 12px 0;
				color: #999;
				background: #fff;
				border-color: #eee;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: center;
			}

	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
				padding: 0 10px;
				overflow: hidden;
				word-break: break-all;
				white-space: normal;
				line-height: 24px;
				text-overflow: ellipsis;
			}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
				margin: 0 10px 0 0;
				color: #666;
				font-weight: 400;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev {
				border: none;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #666;
				background: #f4f4f5;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next {
				border: none;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #666;
				background: #f4f4f5;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #C0C4CC;
				background: #f4f4f5;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #C0C4CC;
				background: #f4f4f5;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}

	.main-content .el-pagination /deep/ .el-pager {
				padding: 0;
				margin: 0;
				display: inline-block;
				vertical-align: top;
			}

	.main-content .el-pagination /deep/ .el-pager .number {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #666;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #f4f4f5;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #1ba7a6;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #f4f4f5;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
				cursor: default;
				padding: 0 4px;
				margin: 0 5px;
				color: #FFF;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #1ba7a6;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
				margin: 0 5px;
				width: 100px;
				position: relative;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0 25px 0 8px;
				color: #606266;
				display: inline-block;
				font-size: 13px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
				top: 0;
				position: absolute;
				right: 0;
				height: 100%;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
				cursor: pointer;
				color: #C0C4CC;
				width: 25px;
				font-size: 14px;
				line-height: 28px;
				text-align: center;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
				margin: 0 0 0 24px;
				color: #606266;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
				border-radius: 3px;
				padding: 0 2px;
				margin: 0 2px;
				display: inline-block;
				width: 50px;
				font-size: 14px;
				line-height: 18px;
				position: relative;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0 3px;
				color: #606266;
				display: inline-block;
				font-size: 14px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
			}
</style>
