# Serialduino for Java v0.5
Yet another Java Arduino Serial Monitor - Work in  Progress. 
<img src="https://github.com/emanuelepaiano/serialduino/blob/master/img/image.jpg">

Supported links:
<ul>
<li>COM ports</li>
<li>TCP/IP</li>
<li>Bluetooth RFCOMM (under development, will be supported on v1.0)</li>
</ul>

Tested on Linux Mint 18. 

## Dependencies: 
jSSC, for com support driver - https://code.google.com/archive/p/java-simple-serial-connector/

## Import library to other projects

### Standalone
Download jSSC and <a href="https://github.com/emanuelepaiano/serialduino/releases">SerialDuino jar files</a>. Import this libraries into your Eclipse Build Path project.

### Maven

If you want to import SerialDuino into another Maven project, you can download this package by git:

<pre>
# git clone https://github.com/emanuelepaiano/serialduino.git
</pre>

enter into serialduino/ directory and run "mvn package":

<pre>
# cd serialduino
# mvn package
# mvn install
</pre>

add follow lines to your project's pom.xml:

```html

<dependency>
    <groupId>io.github.emanuelepaiano</groupId>
    <artifactId>serialduino</artifactId>
    <version>0.5.0</version>
</dependency>
```

## Examples
See src/serialduino/examples sources.

### License
Apache 2.0 - http://www.apache.org/licenses/LICENSE-2.0

### Author
Emanuele Paiano - nixw0rm [at] gmail [dot] com
