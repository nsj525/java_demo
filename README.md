# java_demo

#### 介绍
java基础demo，

#### 软件架构
Spring框架整合其他相关框架


#### package介绍
aop: 切面相关
enums: 枚举相关
generic: 泛型
java_extends: 继承和多态
refactor: 《重构》学习demo转化
strategy: 策略相关


#### 相关资料

1. [aop原理](https://blog.csdn.net/wyl6019/article/details/80136000)
2. [spring中本类方法互调，被调用方法上的注解不生效](https://www.cnblogs.com/ynyhl/p/12066530.html)
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 设计模式
****
>1、策略模式Strategy（行为型模式）

所谓策略模式，通俗的理解就是把不同业务场景下的代码块，封装成对应的方法，在系统运行时，匹配不同的策略，实现对应的业务

常见结构：
- 定义超类（父类）
- 定义实现类
- 匹配策略（声明超类对象，调用方法）

常见注入的方式：
1. Parent parent = new Son();
2. 通过@Autowired或@Resource,借助Spring注入所有子类
- List<Parent> list;
- Map<String,Parent> map;
