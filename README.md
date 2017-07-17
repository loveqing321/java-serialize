# java-serialize
java 序列化与反序列化测试


整合了java常用的序列化与反序列化工具，包括三种类型的序列化与反序列化，byte/json/xml。

### byte 字节数组序列化：

```
-----------------------------------------------

jdk，  jboss marshalling，  protobuf，  thrift

-----------------------------------------------

```

**测试一个嵌套的Messages对象，其中包含10万个Message子对象，结果如下：**

1. Jdk     serialize: 303545000(303ms)		unserialize: 252196000(252ms)		byteSize: 5578056
2. MessagePack 	 serialize: 1129156000(1129ms)		unserialize: 171346000(171ms)		byteSize: 6446330
3. Marshalling 	 serialize: 232842000(232ms)		unserialize: 141572000(141ms)		byteSize: 5578056
4. Protobuf 	 serialize: 234199000(234ms)		unserialize: 68910000(68ms)		byteSize: 6761268
5. Thrift compact protocol	 serialize: 265856000(265ms)		unserialize: 28000(0ms)		byteSize: 8388608
6. Thrift binary protocol	 serialize: 185083000(185ms)		unserialize: 37000(0ms)		byteSize: 16777216
7. Thrift json protocol	 serialize: 538411000(538ms)		unserialize: 48000(0ms)		byteSize: 16777216
8. Thrift simple json protocol	 serialize: 263089000(263ms)		unserialize: 130000(0ms)		byteSize: 16777216

**结论： protobuf与thrift的序列化效率都不错，thrift的反序列化效率很高，但是thrift序列化后的字节比较多（启用压缩和不启用压缩差出一倍）。由于protobuf与thrift的使用需要预先定义好IDL，然后编译之后才能用作序列化与反序列化对象。相比于此，jboss marshalling更简单些。**

### json 序列化：

```
-----------------------------------------------

jackson，  json-lib，  gson，  fastjson

-----------------------------------------------

```

**测试一个嵌套的Messages对象，其中包含10万个Message子对象，结果如下：**

1. jackson 	 serialize: 206993000(206ms)		unserialize: 281085000(281ms)
2. gson 	 serialize: 213390000(213ms)		unserialize: 169707000(169ms)
3. fastjson 	 serialize: 196175000(196ms)		unserialize: 234944000(234ms)
4. json-lib  序列化与反序列化都很慢

**结论：这三种json序列化工具效果都差不多，可能gson在反序列化方面效果更明显些。**

### xml 序列化
```
-----------------------------------------------

java beans encoder/decoder，  jaxb，  jibx，  xstream

-----------------------------------------------

```

**测试一个嵌套的Messages对象，其中包含10万个Message子对象，结果如下：**

1. jibx 	 serialize: 261290000(261ms)		unserialize: 419034000(419ms)
2. jaxb 	 serialize: 311686000(311ms)		unserialize: 728823000(728ms)
3. java beans encoder/decoder 序列化后的xml格式不符合我们实际使用，这里不做对比
4. xstream  序列化与反序列化都很慢

**结论：jibx的序列化和反序列化效果是很不错的，但是jibx需要预先进行binding和compiler（修改原先的序列化对象类代码）。**