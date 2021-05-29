# [Home](../README.md)

## [Java](https://www.matools.com/api/java8)

### 位运算
__________________________________________________________________________________________________________________						
|  符号 |  描述  																	|  例子  						|	
| :---- | :---------------------------------------------------------------------	| :---------------------------	|						
| &     | 如果相对应位都是1，则结果为1，否则为0 									| (A＆B) 得到12，即0000 1100	|					
| \|    | 如果相对应位都是 0，则结果为 0，否则为 1 									| (A\|B) 得到61，即 0011 1101	|					
| ^     | 如果相对应位值相同，则结果为0，否则为1 									| (A^B) 得到49，即 0011 0001	|					
| ~     | 取反，即0变成1，1变成0。 													| (~A) 得到-61，即1100 0011		|				
| <<    | 左移，左操作数按位左移右操作数指定的位数。 								| (A<<2) 得到240，即 1111 0000	|					
| >>    | 右移，左操作数按位右移右操作数指定的位数。 								| (A>>2) 得到15即 1111			|			
| >>>   | 右移补零，左操作数的值按右操作数指定的位数右移，移动得到的空位以零填充。 	| (A>>>2) 得到15即0000 1111		|	

__________________________________________________________________________________________________________________				

### 基本类型
___________________________________________________________
| 名字   | 基本类型 | 大小    | 最小值 	| 最大值 | 默认值 	|
| :--	 | :--		| :--	  | :--	 	| :--	 | :--		|
| 位     | byte		| 8 bits  | -128	| 127    | 0		|
| 短整数 | short	| 16 bits | -215	| 214    | 0		|
| 整数   | int		| 32 bits | -231	| 230	 | 0		|
| 长整数 | long		| 64 bits | -263	| 262	 | 0		|
| 单精度 | float	| 32 bits | -231	| 230	 | 0		|
| 双精度 | double	| 64 bits | -263	| 262	 | 0		|
| 字符   | char		| 16 bits | 0		| 215    | null		|
| 布尔值 | boolean	| 8 bits  | true	| false  | false	|

___________________________________________________________

## 多线程
![Thread](../images/Thread.png)

### 锁机制
#### 公平锁/非公平锁
- 公平锁是指多个线程按照申请锁的顺序来获取锁
- 非公平锁是指多线程获取锁的顺序并不是按照申请锁的顺序，有可能后申请的线程比先申请的线程优先获取锁，有可能会造成优先反转或饥饿现象

#### 可重入锁
- 又名递归锁，是指在同一个线程在外层方法获取锁的时候，在进入内部方法会自动获取锁

```java
/**
 * 因为获取了setA()的锁（方法外层的锁），
 * 此时同时调用setB()将会自动获取setB()的锁，如果不自动获取的话方法B将不会执行
 */
synchronized void setA() throws Exception {
	Thread.sleep(1000);
	setB();
}
synchronized void setB() throws Exception {
	Thread.sleep(1000);
}
```

#### 独享锁/共享锁
- 独享锁是指该锁一次只能被一个线程所持有
- 共享锁是指该锁可被多个线程所持有

#### 互斥锁/读写锁
- 是独享锁/共享锁的具体实现

#### 乐观锁/悲观锁
- 悲观锁认为数据有可能会被其他人修改，

#### 分段锁

#### 偏向锁/轻量级锁/重量级锁

#### 自旋锁

### Thread
```java
public class Thread1 extends Thread {
	private String name;

	public Thread1(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(name + "运行：" + i);
			try {
				sleep((int) Math.random() * 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Thread1 t1 = new Thread1("A");
		Thread1 t2 = new Thread1("B");
		t1.start();
		t2.start();
	}
}
```
### Runnable
```java
public class Thread2 implements Runnable {
	private String name;

	public Thread2(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(name + "运行：" + i);
			try {
				Thread.sleep((int) Math.random() * 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Thread(new Thread2("A")).start();
		new Thread(new Thread2("B")).start();
	}
}
```
### sheep() 与 yield()
> sleep()使当前线程进入停滞状态，所以执行sleep()的线程在指定的时间内肯定不会被执行。
> yield()只是使当前线程重新回到可执行状态，所以执行yield()的线程有可能在进入到可执行状态后马上又被执行。
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
> 创造型模式，其在父类中提供一个创建对象的方法， 允许子类决定实例化对象的类型。
> 工厂方法模型建议使用特殊的工厂方法代替对于对象构造函数的直接调用（即使用 new 运算符）。对象任将通过new运算符创建，只是该运算符在工厂方法中条用，工厂方法返回的对象通常称为“产品”

<div align=center> 
	<img src="../images/设计模式-工厂1.png" alt=""> 
	<p>子类可以修改工厂方法返回的对象类型</p>
</div>



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