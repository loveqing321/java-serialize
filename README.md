# java-serialize
java 序列化与反序列化测试


整合了java常用的序列化与反序列化工具，包括三种类型的序列化与反序列化，byte/json/xml。

### byte 字节数组序列化：

```
-----------------------------------------------

jdk，  jboss marshalling，  protobuf，  thrift

-----------------------------------------------

```

****************** test byte serialize/unserialize ****************
Jdk 	 serialize: 303545000(303ms)		unserialize: 252196000(252ms)		byteSize: 5578056
MessagePack 	 serialize: 1129156000(1129ms)		unserialize: 171346000(171ms)		byteSize: 6446330
Marshalling 	 serialize: 232842000(232ms)		unserialize: 141572000(141ms)		byteSize: 5578056
Protobuf 	 serialize: 234199000(234ms)		unserialize: 68910000(68ms)		byteSize: 6761268
Thrift compact protocol	 serialize: 265856000(265ms)		unserialize: 28000(0ms)		byteSize: 8388608
Thrift binary protocol	 serialize: 185083000(185ms)		unserialize: 37000(0ms)		byteSize: 16777216
Thrift json protocol	 serialize: 538411000(538ms)		unserialize: 48000(0ms)		byteSize: 16777216
Thrift simple json protocol	 serialize: 263089000(263ms)		unserialize: 130000(0ms)		byteSize: 16777216

**结论： protobuf与thrift的序列化效率都不错，thrift的反序列化效率很高，但是thrift序列化后的字节比较多（启用压缩和不启用压缩差出一倍）。但是protobuf与thrift的使用需要预先定义好IDL，然后编译之后才能用作序列化与反序列化对象。相比于此，jboss marshalling更简单些。**

### json 序列化：

```
-----------------------------------------------

jackson，  json-lib，  gson，  fastjson

-----------------------------------------------

```

### xml 序列化
```
-----------------------------------------------

java beans encoder/decoder，  jaxb，  jibx，  xstream

-----------------------------------------------

```
