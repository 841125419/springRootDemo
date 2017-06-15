在 src.main.resources.application.yml中修改为自个的数据库即可完成数据库配置
datasource:
    driverClassName: oracle.jdbc.OracleDriver
    #url: jdbc:oracle:thin:@172.20.1.19:1521:XE
    url: jdbc:oracle:thin:@localhost:1521:MYTEST
    username: root
    password: root
访问 http://localhost:8083/hmallTable/hmallTable 即可访问初始化配置管理功能