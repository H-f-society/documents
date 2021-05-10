# [Home](../README.md)

## [Java](https://www.matools.com/api/java8)
### 基本类型
_________________________________________________________
| 名字   | 基本类型 | 大小    | 最小值 | 最大值 | 默认值 |
| :----- | :------- | :------ | :----- | :----- | :----- |
| 位     | byte     | 8 bits  | -128   | 127    | 0      |
| 短整数 | short    | 16 bits | -215   | 214    | 0      |
| 整数   | int      | 32 bits | -231   | 230    | 0      |
| 长整数 | long     | 64 bits | -263   | 262    | 0      |
| 单精度 | float    | 32 bits | -231   | 230    | 0      |
| 双精度 | double   | 64 bits | -263   | 262    | 0      |
| 字符   | char     | 16 bits | 0      | 215    | null   |
| 布尔值 | boolean  | 8 bits  | true   | false  | false  |

_________________________________________________________

## 设计模式
### 单例
![单例模式](../images/设计模式-单例.png)

> 双重校验锁 DCL - double checked locking

```java
public class Singleton {
	private volatile static Singleton singleton;
	private Singleton () {}
	public static Singleton getSingleton() {
		if (singleton == null) {
			synchronized (Singleton.class) {
				if (singleton == null) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
}
```
### 工厂
> 其在父类中提供一个创建对象的方法， 允许子类决定实例化对象的类型。

![工厂模式](../images/设计模式-工厂.png)

### 抽象

### 适配器

### 桥接

### 责任链

### 命令
![命令模式](../images/设计模式-命令.png)

### 迭代器

### 中介者

### 生成器

### 原型

### 组合

### 装饰

### 备忘录

### 观察者

### 状态

### 策略

### 外观

### 享元

### 模板方法

### 访问者

### 代理