const base = {
    get() {
        return {
            url : "http://localhost:8080/springboot3no5q/",
            name: "springboot3no5q",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springboot3no5q/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "古文物收藏管理系统"
        } 
    }
}
export default base
