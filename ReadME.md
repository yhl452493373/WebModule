webmodule目录下执行archetype:create-from-project
target/generated-sources/archetype目录下执行clean install

之后在maven库中找到com/h3w/webmodule-archetype/1.0.0-SNAPSHOT/webmodule-archetype-1.0.0-SNAPSHOT.jar,用压缩软件打开(不要解压),
修改META-INF/maven/archetype-metadata.xml:
将
```xml
    <fileSet filtered="true" packaged="true" encoding="UTF-8">
      <directory>src/main/java</directory>
      <includes>
        <include>**/*.java</include>
      </includes>
    </fileSet>
```
修改为
```xml
    <fileSet filtered="true" packaged="true" encoding="UTF-8">
      <directory>src/main/java</directory>
      <includes>
        <include>**/**</include>
      </includes>
    </fileSet>
```
将
```xml
    <fileSet encoding="UTF-8">
      <directory>src/main/resources</directory>
      <includes>
        <include>**/*.ftl</include>
        <include>**/*.yml</include>
      </includes>
    </fileSet>
```
修改为
```xml
    <fileSet filtered="true" encoding="UTF-8">
      <directory>src/main/resources</directory>
      <includes>
        <include>**/**</include>
      </includes>
    </fileSet>
```
在archetype-resources/src/main/java/__artifactId__目录下新建config,mapper,service目录,同时将该目录下的MyCodeGenerator.java
中的
```java
    import ${package}.generator.CodeGenerator;
    import ${package}.generator.CodeGeneratorConfig;
    import ${package}.generator.DataSourceGeneratorConfig;
```
修改为
```java
    import com.h3w.generator.CodeGenerator;
    import com.h3w.generator.CodeGeneratorConfig;
    import com.h3w.generator.DataSourceGeneratorConfig;
```
将同级目录下的Application.java中的
```java
    @ComponentScan({
            "com.h3w.config",
            "com.h3w.webmodule.config",
            "com.h3w.webmodule.mapper",
            "com.h3w.webmodule.service",
            "com.h3w.webmodule.controller"})
```
修改为
```java
    @ComponentScan({
            "com.h3w.config",
            "${package}.${artifactId}.config",
            "${package}.${artifactId}.mapper",
            "${package}.${artifactId}.service",
            "${package}.${artifactId}.controller"})
```
将archetype-resources/src/main/resources中所有yml文件中的com.h3w替换为{package}.${artifactId}
之后保存
然后就能在maven或者idea中使用生成的archetype了