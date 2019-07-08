# 项目介绍
testcloud主要是以spring cloud为主要内容，通过一个简单的api例子来介绍微服务在项目开发中的使用，
同时也介绍了zuul,oauth,feign,ribbon,hystrix,sleuth等组件的使用。

# 项目说明
1. gateway 网关
2. user 用户授权uaa
3. product 商品微服务

# gateway网关
1. 路由转发
2. 请求过滤

> 对请求统一处理，如对token的处理，本例中使用了TokenValidatFilter进行了token的加工，从redis里拿到当前登陆的用户信息，而向下游传递，下游服务
可以有TokenDataFilter过滤器，对从网页传来的tokenData进行统一的处理，并存到threadLocal变更里，当前线程里使用它，线程结束对象也被释放。