配置tomcate  conf/context.xml添加一下元素  去除超过最大缓存问题
<Context>
    <Resources cachingAllowed="true" cacheMaxSize="100000" />
</Context>

在 src.main.resources.application.yml中修改为自个的数据库即可完成数据库配置
datasource:
    driverClassName: oracle.jdbc.OracleDriver
    #url: jdbc:oracle:thin:@172.20.1.19:1521:XE
    url: jdbc:oracle:thin:@localhost:1521:MYTEST
    username: root
    password: root
访问 http://localhost:8083/hmallTable/hmallTable 即可访问初始化配置管理功能