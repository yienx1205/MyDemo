通过canal+kafka方式实现MySQL与redis数据同步

- 该项目为概念版，未启动过

# 搭建Kafka
下载安装包，解压，打开/config/server.properties配置文件，修改日志目录：

```shell
log.dirs=./logs
```

首先启动ZooKeeper，我用的是3.6.1版本：



接着再启动Kafka，在Kafka的bin目录下打开cmd，输入命令：

```shell
kafka-server-start.bat ../../config/server.properties
```

我们可以看到ZooKeeper上注册了Kafka相关的配置信息：



然后需要创建一个队列，用于接收canal传送过来的数据，使用命令：

```shell
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic canaltopic
```

创建的队列名是`canaltopic`。

# 配置Cannal Server
canal官网下载相关安装包：


找到canal.deployer-1.1.4/conf目录下的canal.properties配置文件：

```properties
# tcp, kafka, RocketMQ 这里选择kafka模式
canal.serverMode = kafka
# 解析器的线程数，打开此配置，不打开则会出现阻塞或者不进行解析的情况
canal.instance.parser.parallelThreadSize = 16
# 配置MQ的服务地址，这里配置的是kafka对应的地址和端口
canal.mq.servers = 127.0.0.1:9092
# 配置instance，在conf目录下要有example同名的目录，可以配置多个
canal.destinations = example
```

通过canal的配置，就可以直接发送到kafka，相当于canal就是个kafka
的producer，项目中只需配置consumer就可以。

然后配置instance，找到/conf/example/instance.properties配置文件：

```properties
## mysql serverId , v1.0.26+ will autoGen(自动生成，不需配置)
# canal.instance.mysql.slaveId=0

# position info
canal.instance.master.address=127.0.0.1:3306
# 在Mysql执行 SHOW MASTER STATUS;查看当前数据库的binlog
canal.instance.master.journal.name=mysql-bin.000006
canal.instance.master.position=4596
# 账号密码
canal.instance.dbUsername=canal
canal.instance.dbPassword=Canal@****
canal.instance.connectionCharset = UTF-8
#MQ队列名称
canal.mq.topic=canaltopic
#单队列模式的分区下标
canal.mq.partition=0
```

配置完成后，就可以启动canal了。

# MySQL对应表结构

```sql
CREATE TABLE `tb_commodity_info` (
  `id` varchar(32) NOT NULL,
  `commodity_name` varchar(512) DEFAULT NULL COMMENT '商品名称',
  `commodity_price` varchar(36) DEFAULT '0' COMMENT '商品价格',
  `number` int(10) DEFAULT '0' COMMENT '商品数量',
  `description` varchar(2048) DEFAULT '' COMMENT '商品描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';
```
