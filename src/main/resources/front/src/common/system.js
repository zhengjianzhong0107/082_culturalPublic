export function isAuth(tableName, key) {
  let role = localStorage.getItem("UserTableName");
  let menus = [{"backMenu":[{"child":[{"appFrontIcon":"cuIcon-list","buttons":["新增","查看","修改","删除"],"menu":"用户","menuJump":"列表","tableName":"yonghu"}],"menu":"用户管理"},{"child":[{"appFrontIcon":"cuIcon-taxi","buttons":["新增","查看","修改","删除"],"menu":"文物展示","menuJump":"列表","tableName":"wenwuzhanshi"}],"menu":"文物展示管理"},{"child":[{"appFrontIcon":"cuIcon-skin","buttons":["新增","查看","修改","删除"],"menu":"文物类型","menuJump":"列表","tableName":"wenwuleixing"}],"menu":"文物类型管理"},{"child":[{"appFrontIcon":"cuIcon-present","buttons":["新增","查看","修改","删除"],"menu":"文物收藏","menuJump":"列表","tableName":"wenwushouzang"}],"menu":"文物收藏管理"},{"child":[{"appFrontIcon":"cuIcon-cardboard","buttons":["新增","查看","修改","删除"],"menu":"文物维护","menuJump":"列表","tableName":"wenwuweihu"}],"menu":"文物维护管理"},{"child":[{"appFrontIcon":"cuIcon-camera","buttons":["新增","查看","修改","删除"],"menu":"文物封存","menuJump":"列表","tableName":"wenwufengcun"}],"menu":"文物封存管理"},{"child":[{"appFrontIcon":"cuIcon-paint","buttons":["新增","查看","修改","删除"],"menu":"公告栏","menuJump":"列表","tableName":"gonggaolan"}],"menu":"公告栏管理"},{"child":[{"appFrontIcon":"cuIcon-wenzi","buttons":["新增","查看","修改","删除"],"menu":"轮播图管理","tableName":"config"},{"appFrontIcon":"cuIcon-keyboard","buttons":["查看","编辑名称","编辑父级","删除"],"menu":"菜单列表","tableName":"menu"}],"menu":"系统管理"}],"frontMenu":[{"child":[{"appFrontIcon":"cuIcon-list","buttons":["查看"],"menu":"文物展示列表","menuJump":"列表","tableName":"wenwuzhanshi"}],"menu":"文物展示模块"},{"child":[{"appFrontIcon":"cuIcon-album","buttons":["查看"],"menu":"公告栏列表","menuJump":"列表","tableName":"gonggaolan"}],"menu":"公告栏模块"}],"hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"否","hasFrontRegister":"否","roleName":"管理员","tableName":"users"},{"backMenu":[{"child":[{"appFrontIcon":"cuIcon-present","buttons":["新增","查看","修改","删除"],"menu":"文物收藏","menuJump":"列表","tableName":"wenwushouzang"}],"menu":"文物收藏管理"},{"child":[{"appFrontIcon":"cuIcon-cardboard","buttons":["新增","查看","修改","删除"],"menu":"文物维护","menuJump":"列表","tableName":"wenwuweihu"}],"menu":"文物维护管理"},{"child":[{"appFrontIcon":"cuIcon-camera","buttons":["新增","查看","修改","删除"],"menu":"文物封存","menuJump":"列表","tableName":"wenwufengcun"}],"menu":"文物封存管理"}],"frontMenu":[{"child":[{"appFrontIcon":"cuIcon-list","buttons":["查看"],"menu":"文物展示列表","menuJump":"列表","tableName":"wenwuzhanshi"}],"menu":"文物展示模块"},{"child":[{"appFrontIcon":"cuIcon-album","buttons":["查看"],"menu":"公告栏列表","menuJump":"列表","tableName":"gonggaolan"}],"menu":"公告栏模块"}],"hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"否","hasFrontRegister":"否","roleName":"用户","tableName":"yonghu"}];
  for(let i=0;i<menus.length;i++){
    if(menus[i].tableName==role){
      for(let j=0;j<menus[i].frontMenu.length;j++){
          for(let k=0;k<menus[i].frontMenu[j].child.length;k++){
            if(tableName==menus[i].frontMenu[j].child[k].tableName){
              let buttons = menus[i].frontMenu[j].child[k].buttons.join(',');
              return buttons.indexOf(key) !== -1 || false
            }
          }
      }
    }
  }
  return false;
}

/**
 *  * 获取当前时间（yyyy-MM-dd hh:mm:ss）
 *   */
export function getCurDateTime() {
    let currentTime = new Date(),
    year = currentTime.getFullYear(),
    month = currentTime.getMonth() + 1 < 10 ? '0' + (currentTime.getMonth() + 1) : currentTime.getMonth() + 1,
    day = currentTime.getDate() < 10 ? '0' + currentTime.getDate() : currentTime.getDate(),
    hour = currentTime.getHours(),
    minute = currentTime.getMinutes(),
    second = currentTime.getSeconds();
    return year + "-" + month + "-" + day + " " +hour +":" +minute+":"+second;
}

/**
 *  * 获取当前日期（yyyy-MM-dd）
 *   */
export function getCurDate() {
    let currentTime = new Date(),
    year = currentTime.getFullYear(),
    month = currentTime.getMonth() + 1 < 10 ? '0' + (currentTime.getMonth() + 1) : currentTime.getMonth() + 1,
    day = currentTime.getDate() < 10 ? '0' + currentTime.getDate() : currentTime.getDate();
    return year + "-" + month + "-" + day;
}
