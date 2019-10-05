# StorageTest
对于只在当前周期中有用的一次性数据，可通过定义变量的形式直接存储
优点：存储快，定义简单
缺点：数据没有持久性

  




SharedPrefrences存储：单一小数据
保存：
1.得到sp对象
2.得到editor对象
3.得到输入的key/value
4.使用editor保存key-value
5.提示
读取：
1.得到输入的key
2.根据key读取value


drawable是可绘制对象
bitmap对应一张图片文件
手机内部file存储:较大的数据或图片以文件形式保存在内部
1.将asserts下的logo.png保存到手机内部
2.读取手机内部图片显示

1.得到InputStream-->读取asserts下的logo.png
2.得到OutputStream-->/data/data/packagename/files/logo.png
3.边读边写
4.提示

1.得到图片文件的路径
2.读取加载图片文件得到bitmap对象
3.将其设置到imageView中显示







手机外部file存储/sdcard
路径1：/storage/sdcard/Android/data/packageName/files/
其他应用可以访问，应用卸载时删除
路径2：/storage/sdcard/xxx/
其他应用可以访问，应用卸载时不会删除
保存：
1.判断sdcard状态，如果是挂载的状态则集序，否则提示
2.读取输入内容/文件名
3.得到指定文件的OutputStream
	得到sd卡下的files路径
	组成完整路径
	创建FileOutputStream
4.写数据
5.提示
读取：







Sqlite数据库存储：有一定结构的数据
默认情况下其他应用不能访问，可通过ContentCovert提供其他应用访问
轻量型关系数据库，安装文件小，支持多操作系统，支持多语言，处理速度快（数据量不是特别大）
一个数据库本质上是一个.db文件
只能打开你本地的数据库文件，得导入数据文件

1.数据库的创建
2.数据库版本的更新
3.表数据的CRUB
4.数据库事务









远程服务器存储：声明权限，必须在分线程执行
存储位置：远程服务器上
特点：
	1.必须联网请求
	2.只能在分线程执行
	3.需要声明权限
技术：
	1.原生的HttpUrlConnectionURL
	2.包装的HttpClient(相当于浏览器)/HttpGet/HttpPost/HttpResponse/HttpEntity
	3.框架：Volley/Xutils(异步网络请求)
		a.不需要我们启动分线程，框架内部接收到请求后会自动在分线程
		b.如何返回给你结果数据？切换到主线程调用监听器的回调方法
实现联网请求功能的3步：
	a.主线程显示提示视图
	b.分线程联网请求，并得到响应数据
	c.主线程显示数据
GET产生一个TCP数据包；POST产生两个TCP数据包。
 
长的说：
对于GET方式的请求，浏览器会把http header和data一并发送出去，服务器响应200（返回数据）；
而对于POST，浏览器先发送header，服务器响应100 continue，浏览器再发送data，服务器响应200 ok（返回数据）。
一.JDK内置原生API：HttpUrlConnection
1.显示ProgressDialog
2.启动分线程
3.在分线程，发送请求，得到响应数据
	得到path，并带上参数name=Tom&age=11
	创建url对象
	打开链接，得到对象
	设置请求方式，连接超时，读写超时
	连接服务器
	发请求，得到响应数据
		得到响应码，必须时200才读取
		得到InputStream，并读取成String
4.在主线程，显示得到的结果，移除dialog
	

二.Android内置包装API：HttpClient浏览器：能提交请求的客户端对象


三.异步网络请求框架：Volley,Xutils
1.创建请求队列对象（一次）
2.创建请求对象StringRequest
3.将请求添加到队列中



