# robot
全技术栈练习

### markdown的常用语法 ###
**加粗**

*斜体*

***斜体加粗***

~~删除线~~

>这个是引用
>>这是引用
>>>这个是引用
 

分割线
---
***

> 区块中使用列表
> 1. 第一项
> 2. 第二项
> + 第一项
> + 第二项
> + 第三项
* 第一项
  > 菜鸟教程
  
  > 学的不仅是技术更是梦想
* 第二项

1. 第一项：
  - 第一项嵌套的第一个元素
  - 第一项嵌套的第二个元素
2. 第二项：
 - 第二项嵌套的第一个元素
 - 第二项嵌套的第二个元素

图片 ![]()

![](https://img2018.cnblogs.com/blog/1813823/201910/1813823-20191020130359318-309106335.webp)

表格

|  表头   | 表头  |
|  ----  | ----  |
| 至少3格横线 | 单元格 |
| 单元格  |  |

代码块

```
function fun(){
echo "这是一句非常牛逼的代码";
}
fun();
```

## 实践小问题总结、记录 ##

1.linux中启动项目且后台运行 **Java -jar xx.jar --spring.profiles.active=dev &** 在命令行后面加上 **&** 符号

2.bootstrap.yml 文件在spring boot项目中无法被读取，需要配置cloud的jar包，或这个是cloud项目
**implementation group: 'org.springframework.cloud', name: 'spring-cloud-starter-bootstrap', version: '3.0.2'**

3.nacos上的配置项取值方法和spring取值一样 **@Value** 
可以通过 *curl -X POST http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=example&group=DEFAULT_GROUP&content=useLocalCache=true*
发布配置

4.nacos获取配置是通过httpclient的方式发送get/post请求，然后解析返回的结果。
**com.alibaba.nacos.common.http.client.request.JdkHttpClientRequest.execute**可以看这个类的方法。注意dataId的文件后缀名称是大写
小写的是无法读取的 {prefix(**application name**)}-{profiles}.{file-extension} YAML/PROPERTIES

5.nacos在启动时会依次去配置中心获取配置，根据**dataId，dataId-profiles，dataId-profiles.YAML**，可以根据这个规则实现根据不同环境启动不同配置
