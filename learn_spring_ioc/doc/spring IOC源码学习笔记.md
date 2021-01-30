# spring IOC源码学习笔记

#### 1.spring概述

##### 1.1 spring是什么

##### 2.2 什么是spring IOC

 **控制反转**（Inversion of Control，缩写为**IoC**），是[面向对象编程](https://baike.baidu.com/item/面向对象编程/254878)中的一种设计原则，可以用来减低计算机[代码](https://baike.baidu.com/item/代码/86048)之间的[耦合度](https://baike.baidu.com/item/耦合度/2603938)。其中最常见的方式叫做**[依赖注入](https://baike.baidu.com/item/依赖注入/5177233)**（Dependency Injection，简称**DI**），还有一种方式叫“依赖查找”（Dependency Lookup）。通过控制反转，对象在被创建的时候，由一个调控系统内所有对象的外界实体将其所依赖的对象的引用传递给它。也可以说，依赖被注入到对象中。控制反转是一种通过描述（在Java中可以是XML 或者注解）并通过第三方去产生或获取特定对象的方式。

Spring IoC 容器的设计主要是基于BeanFactory 和ApplicationContext 两个接口，其中ApplicationContext 是BeanFactory 的子接口之一，换句话说BeanFactory 是Spring IoC 容器所定义的最底层接口，而ApplicationContext 是其高级接口之一，并且对BeanFactory 功能做了许多有用的扩展，所以在绝大部分的工作场景下， 都会使用ApplicationContext 作为Spring IoC 容器， 其下图所示，图中展示的是Spring 相关的IoC 容器接口的主要设计。

![1611822946731](C:\Users\86178\AppData\Roaming\Typora\typora-user-images\1611822946731.png)

##### 2.3 spring IOC容器的初始化和依赖注入

Bean 的初始化和依赖注入在Spring IoC 容器中是两大步骤，bean是在初始化之后，才会进行依赖注入。

Bean 的初始化分为3 步：
1） Resource 定位，这步是Spring IoC 容器根据开发者的配置，进行资源定位，在Spring的开发中，通过XML 或者注解都是十分常见的方式，定位的内容是由开发者所提供的。
2） BeanDefinition 的载入，这个过程就是Spring 根据开发者的配置获取对应的POJO,用以生成对应实例的过程。
3） BeanDefinition 的注册，这个步骤就相当于把之前通过BeanDefinition 载入的POJO往Spring IoC 容器中注册，这样就可以使得开发和测试人员都可以通过描述从中得到Spring IoC 容器的Bean 了。
做完了这3 步， Bean 就在Spring IoC 容器中得到了初始化，但是没有完成依赖注入，也就是没有注入其配置的资源给B ean ，那么它还不能完全使用。对于依赖注入， Spri ng Bean还有一个配置选项一一lazy-init ， 其含义就是是否初始化Spring Bean 。在没有任何配置的情况下，它的默认值为default ，实际值为false ，也就是Spring IoC 默认会自动初始化Bean 。如果将其设置为true ，那么只有当我们使用Spring IoC 容器的getBean 方法获取它时，它才会进行初始化， 完成依赖注入。

- **2.3.1 bean的创建方式**

  1）使用默认构造函数创建

  ```xml
  <!-- 第一种方式：使用默认构造函数创建。
              在spring的配置文件中使用bean标签，配以id和class属性之后，且没有其他属性和标签时。
              采用的就是默认构造函数创建bean对象，此时如果类中没有默认构造函数，则对象无法创建。
  -->
  <bean id="accountService" class="com.shepherd.service.impl.AccountServiceImpl"></bean>
     
  ```

  2）使用普通工厂中的方法创建对象（使用某个类中的方法创建对象，并存入spring容器）

  ```java
  /**
   * 模拟一个工厂类（该类可能是存在于jar包中的，我们无法通过修改源码的方式来提供默认构造函数）
   */
  public class InstanceFactory {
  
      public IAccountService getAccountService(){
          return new AccountServiceImpl();
      }
  }
  
  ```

  ```xml
  <!-- 第二种方式： 使用普通工厂中的方法创建对象（使用某个类中的方法创建对象，并存入spring容器）
    -->
  <bean id="instanceFactory" class="com.shepherd.factory.InstanceFactory"></bean>
  <bean id="accountService" factory-bean="instanceFactory" factory-method="getAccountService"></bean>
  
  ```

  3）使用工厂中的静态方法创建对象（使用某个类中的静态方法创建对象，并存入spring容器)

  ```java
  /**
   * 模拟一个工厂类（该类可能是存在于jar包中的，我们无法通过修改源码的方式来提供默认构造函数）
   */
  public class StaticFactory {
      public static IAccountService getAccountService(){
          return new AccountServiceImpl();
      }
  }
  
  ```

  ```xml
  <!-- 第三种方式：使用工厂中的静态方法创建对象（使用某个类中的静态方法创建对象，并存入spring容器)
   -->
  <bean id="accountService" class="com.shepherd.factory.StaticFactory" factory-method="getAccountService"></bean>
  
  ```

- **2.3.2 bean的作用范围**

  ```xml
  <!-- bean的作用范围调整
          bean标签的scope属性：
              作用：用于指定bean的作用范围
              取值： 常用的就是单例的和多例的
                  singleton：单例的（默认值）
                  prototype：多例的
                  request：作用于web应用的请求范围
                  session：作用于web应用的会话范围
                  global-session：作用于集群环境的会话范围（全局会话范围），当不是集群环境时，它就是session
  -->
  
  <bean id="accountService" class="com.shepherd.service.impl.AccountServiceImpl" scope="prototype"></bean>
  
  ```

- **2.3.3 bean的生命周期**

  ```xml
  <!-- bean对象的生命周期
              单例对象
                  出生：当容器创建时对象出生
                  活着：只要容器还在，对象一直活着
                  死亡：容器销毁，对象消亡
                  总结：单例对象的生命周期和容器相同
              多例对象
                  出生：当我们使用对象时spring框架为我们创建
                  活着：对象只要是在使用过程中就一直活着。
                  死亡：当对象长时间不用，且没有别的对象引用时，由Java的垃圾回收器回收
  --><bean id="accountService" class="com.shepherd.service.impl.AccountServiceImpl"
            scope="singleton" init-method="init" destroy-method="destroy"></bean>
  
  ```

  生命周期主要是为了了解Spring IoC 容器初始化和销毁Bean 的过程，通过对它的学习就可以知道如何在初始化和销毁的时候加入自定义的方法，以满足特定的需求。图下图展示了Spring IoC 容器初始化和销毁Bean 的过程。

  ![1611826624746](C:\Users\86178\AppData\Roaming\Typora\typora-user-images\1611826624746.png)

  从上图可以看到， Spring IoC 容器对Bean 的管理还是比较复杂的， Spring IoC 容器在执行了初始化和依赖注入后，会执行一定的步骤来完成初始化，通过这些步骤我们就能自定义初始化，而在Spring IoC 容器正常关闭的时候，它也会执行一定的步骤来关闭容器，释放资源。除需要了解整个生命周期的步骤外，还要知道这些生命周期的接口是针对什么而言的， 首先介绍生命周期的步骤。

  - 如果Bean 实现了接口BeanNameAware 的setBeanName 方法，那么它就会调用这个方法。
  - 如果Bean 实现了接口BeanFactory Aware 的setBeanFactory 方法，那么它就会调用这个方法。
  -  如果Bean 实现了接口App licationContextAware 的setApplicationContext 方法，且Spring IoC 容器也必须是一个ApplicationContext 接口的实现类，那么才会调用这个方法，否则是不调用的。
  -  如果Bean 实现了接口BeanPostProcessor 的postProcessBeforelnitialization 方法，那么它就会调用这个方法。
  - 如果Bean 实现了接口BeanF actory PostProcessor 的a企erProperti 巳sS巳t 方法，那么它就会调用这个方法。
  - 如果Bean 自定义了初始化方法，它就会调用己定义的初始化方法。如果Bean 实现了接口BeanPostProcessor 的postProcessA武erlnitialization 方法，完成了这些调用，这个时候Bean 就完成了初始化，那么Bean 就生存在S pring IoC 的容器中了，使用者就可以从中获取Bean 的服务。

  当服务器正常关闭，或者遇到其他关闭Spring IoC 容器的事件，它就会调用对应的方法完成Bean 的销毁，其步骤如下：

  - 如果Bean 实现了接口DisposableBean 的destroy 方法，那么就会调用它。
  - 如果定义了自定义销毁方法， 那么就会调用它。

- **2.3.4 依赖注入(DI)**

  依赖注入：Dependency Injection。它是spring框架核心ioc的具体实现。 我们的程序在编写时，通过控制反转，把对象的创建交给了spring，但是代码中不可能出现没有依赖的情况。ioc解耦只是降低他们的依赖关系，但不会消除。例如：我们的业务层仍会调用持久层的方法。 那这种业务层和持久层的依赖关系，在使用spring之后，就让spring来维护了。 简单的说，就是坐等框架把持久层对象传入业务层，而不用我们自己去获取。

  1）构造函数注入

  ```xml
  <!--构造函数注入：
          使用的标签:constructor-arg
          标签出现的位置：bean标签的内部
          标签中的属性
              type：用于指定要注入的数据的数据类型，该数据类型也是构造函数中某个或某些参数的类型
              index：用于指定要注入的数据给构造函数中指定索引位置的参数赋值。索引的位置是从0开始
              name：用于指定给构造函数中指定名称的参数赋值                          常用的
              =============以上三个用于指定给构造函数中哪个参数赋值
              value：用于提供基本类型和String类型的数据
              ref：用于指定其他的bean类型数据。它指的就是在spring的Ioc核心容器中出现过的bean对象
          优势：
              在获取bean对象时，注入数据是必须的操作，否则对象无法创建成功。
          弊端：
              改变了bean对象的实例化方式，使我们在创建对象时，如果用不到这些数据，也必须提供。
  -->
  <bean id="user" class="com.shepherd.entity.User">
          <constructor-arg name="name" value="泰斯特"></constructor-arg>
          <constructor-arg name="age" value="18"></constructor-arg>
          <constructor-arg name="birthday" ref="now"></constructor-arg>
   </bean>
  
  <!-- 配置一个日期对象 -->
  <bean id="now" class="java.util.Date"></bean>
  ```

  2）set方法注入

  ```xml
      <!-- set方法注入                更常用的方式
          涉及的标签：property
          出现的位置：bean标签的内部
          标签的属性
              name：用于指定注入时所调用的set方法名称
              value：用于提供基本类型和String类型的数据
              ref：用于指定其他的bean类型数据。它指的就是在spring的Ioc核心容器中出现过的bean对象
          优势：
              创建对象时没有明确的限制，可以直接使用默认构造函数
          弊端：
              如果有某个成员必须有值，则获取对象是有可能set方法没有执行。
      -->
      <bean id="user2" class="com.shepherd.entity.User">
          <property name="name" value="TEST" ></property>
          <property name="age" value="21"></property>
          <property name="birthday" ref="now"></property>
      </bean>
  ```

  3）复杂类型(集合类型)注入

  ```xml
      <!-- 复杂类型的注入/集合类型的注入
          用于给List结构集合注入的标签：
              list array set
          用于个Map结构集合注入的标签:
              map  props
          结构相同，标签可以互换
      -->
      <bean id="user3" class="com.shepherd.entity.User">
          <property name="arrays">
              <set>
                  <value>AAA</value>
                  <value>BBB</value>
                  <value>CCC</value>
              </set>
          </property>
  
          <property name="list">
              <array>
                  <value>AAA</value>
                  <value>BBB</value>
                  <value>CCC</value>
              </array>
          </property>
  
          <property name="set">
              <list>
                  <value>AAA</value>
                  <value>BBB</value>
                  <value>CCC</value>
              </list>
          </property>
  
          <property name="map">
              <props>
                  <prop key="testC">ccc</prop>
                  <prop key="testD">ddd</prop>
              </props>
          </property>
  
          <property name="properties">
              <map>
                  <entry key="testA" value="aaa"></entry>
                  <entry key="testB">
                      <value>BBB</value>
                  </entry>
              </map>
          </property>
      </bean>
  ```

**2.4 spring IOC注解实现**

- **@Component**

  ```properties
  作用： 把资源让spring来管理。相当于在xml中配置一个bean。 
  属性： value：指定bean的id。如果不指定value属性，默认bean的id是当前类的类名。首字母小写
  ```

- **@Controller @Service @Repository**

  ```properties
  下面三个注解都是针对一个的衍生注解，他们的作用及属性都是一模一样的。他们只不过是提供了更加明确的语义化。以下三个注解他们的作用和属性与Component是一模一样。是spring框架为我们提供明确的三层使用的注解，使我们的三层对象更加清晰 
      @Controller：一般用于表现层的注解。
      @Service：一般用于业务层的注解。 
      @Repository：一般用于持久层的注解。
  细节：如果注解中有且只有一个属性要赋值时，且名称是value，value在赋值是可以不写。
  ```

- **@Autowired**

  ```properties
  自动按照类型注入。当使用注解注入属性时，set方法可以省略。它只能注入其他bean类型。当有多个类型匹配时，使用要注入的对象变量名称作为bean的id，在spring容器查找，找到了也可以注入成功。找不到就报错。
  ```

- **@Qualifier**

  ```properties
  
  作用：在按照类中注入的基础之上再按照名称注入。它在给类成员注入时不能单独使用。但是在给方法参数注入时可以（稍后我们讲）
  属性：value：用于指定注入bean的id。
  ```

- **@Resource**

  ```properties
  作用： 直接按照Bean的id注入。它也只能注入其他bean类型。
  属性： name：指定bean的id。
  ```

- **@Value**

  ```properties
  作用： 注入基本数据类型和String类型数据的 
  属性： value：用于指定值
  ```

  ```java
  @Value("${jdbc.driver}")
  private String driver;
  
  @Value("${jdbc.url}")
  private String url;
  
  @Value("${jdbc.username}")
  private String username;
  
  @Value("${jdbc.password}")
  private String password;
  ```

- **@Scope**

  ```properties
  作用： 指定bean的作用范围。
  属性： value：指定范围的值。 
        取值：singleton prototype request session globalsession
  ```

- **@PostConstruct**

  ```properties
  用于指定初始化方法
  ```

- **@PreDestroy**

  ```properties
  用于指定销毁方法
  ```

以上注解示例代码如下：

```java
package com.shepherd.service.impl;

import com.shepherd.dao.IAccountDao;
import com.shepherd.service.IAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author fjZheng
 * @version 1.0
 * @date 2021/1/26 17:41
*/

/**
 * 账户的业务层实现类
 *
 * 曾经XML的配置：
 *  <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"
 *        scope=""  init-method="" destroy-method="">
 *      <property name=""  value="" | ref=""></property>
 *  </bean>
 *
 * 用于创建对象的
 *      他们的作用就和在XML配置文件中编写一个<bean>标签实现的功能是一样的
 *      Component:
 *          作用：用于把当前类对象存入spring容器中
 *          属性：
 *              value：用于指定bean的id。当我们不写时，它的默认值是当前类名，且首字母改小写。
 *      Controller：一般用在表现层
 *      Service：一般用在业务层
 *      Repository：一般用在持久层
 *      以上三个注解他们的作用和属性与Component是一模一样。
 *      他们三个是spring框架为我们提供明确的三层使用的注解，使我们的三层对象更加清晰
 *
 *
 * 用于注入数据的
 *      他们的作用就和在xml配置文件中的bean标签中写一个<property>标签的作用是一样的
 *      Autowired:
 *          作用：自动按照类型注入。只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功
 *                如果ioc容器中没有任何bean的类型和要注入的变量类型匹配，则报错。
 *                如果Ioc容器中有多个类型匹配时：
 *          出现位置：
 *              可以是变量上，也可以是方法上
 *          细节：
 *              在使用注解注入时，set方法就不是必须的了。
 *      Qualifier:
 *          作用：在按照类中注入的基础之上再按照名称注入。它在给类成员注入时不能单独使用。但是在给方法参数注入时可以（稍后我们讲）
 *          属性：
 *              value：用于指定注入bean的id。
 *      Resource
 *          作用：直接按照bean的id注入。它可以独立使用
 *          属性：
 *              name：用于指定bean的id。
 *      以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现。
 *      另外，集合类型的注入只能通过XML来实现。
 *
 *      Value
 *          作用：用于注入基本类型和String类型的数据
 *          属性：
 *              value：用于指定数据的值。它可以使用spring中SpEL(也就是spring的el表达式）
 *                      SpEL的写法：${表达式}
 *
 * 用于改变作用范围的
 *      他们的作用就和在bean标签中使用scope属性实现的功能是一样的
 *      Scope
 *          作用：用于指定bean的作用范围
 *          属性：
 *              value：指定范围的取值。常用取值：singleton prototype
 *
 * 和生命周期相关 了解
 *      他们的作用就和在bean标签中使用init-method和destroy-methode的作用是一样的
 *      PreDestroy
 *          作用：用于指定销毁方法
 *      PostConstruct
 *          作用：用于指定初始化方法
 */
@Service("accountService")
//@Scope("prototype")
public class AccountServiceImpl implements IAccountService {

//    @Autowired
//    @Qualifier("accountDao1")
    @Resource(name = "accountDao2")
    private IAccountDao accountDao = null;

    @PostConstruct
    public void  init(){
        System.out.println("初始化方法执行了");
    }

    @PreDestroy
    public void  destroy(){
        System.out.println("销毁方法执行了");
    }

    public void  saveAccount(){
        accountDao.saveAccount();
    }
}

```

- **@Configuration**

  ```properties
  作用： 用于指定当前类是一个spring配置类，当创建容器时会从该类上加载注解。获取容器时需要使用AnnotationApplicationContext(有@Configuration注解的类.class)。 
  属性： value：用于指定配置类的字节码
  ```

- **@ComponentScan**

  ```properties
  作用： 用于指定spring在初始化容器时要扫描的包。作用和在spring的xml配置文件中的： <context:component-scan base-package="com.itheima"/>是一样的。 
  属性： basePackages：用于指定要扫描的包。和该注解中的value属性作用一样。
  ```

- **@Bean**

  ```properties
  作用： 该注解只能写在方法上，表明使用此方法创建一个对象，并且放入spring容器。 
  属性： name：给当前@Bean注解方法创建的对象指定一个名称(即bean的id）。
  ```

- **@PropertySource**

  ```properties
  作用：用于加载.properties文件中的配置。例如我们配置数据源时，可以把连接数据库的信息写到properties配置文件中，就可以使用此注解指定properties配置文件的位置。 
  属性： value[]：用于指定properties文件位置。如果是在类路径下，需要写上classpath:
  ```

- **@Import**

  ```properties
  作用： 用于导入其他配置类，在引入其他配置类时，可以不用再写@Configuration注解。当然，写上也没问题。 属性： value[]：用于指定其他配置类的字节码。
  ```

以上注解示例代码：

```java
package config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

/**
 * 和spring连接数据库相关的配置类
 */
public class JdbcConfig {

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /**
     * 用于创建一个QueryRunner对象
     * @param dataSource
     * @return
     */
    @Bean(name="runner")
    @Scope("prototype")
    public QueryRunner createQueryRunner(@Qualifier("ds") DataSource dataSource){
        return new QueryRunner(dataSource);
    }

    /**
     * 创建数据源对象
     * @return
     */
    @Bean(name="ds")
    public DataSource createDataSource(){
        try {
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.setDriverClassName(driver);
            druidDataSource.setUrl(url);
            druidDataSource.setUsername(username);
            druidDataSource.setPassword(password);
            return druidDataSource;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Bean(name="ds1")
    public DataSource createDataSource1(){
        try {
            DruidDataSource ds = new DruidDataSource();
            ds.setDriverClassName(driver);
            ds.setUrl("jdbc:mysql://localhost:3306/test");
            ds.setUsername(username);
            ds.setPassword(password);
            return ds;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

```

```java
package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * 该类是一个配置类，它的作用和bean.xml是一样的
 * spring中的新注解
 * Configuration
 *     作用：指定当前类是一个配置类
 *     细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时，该注解可以不写。
 * ComponentScan
 *      作用：用于通过注解指定spring在创建容器时要扫描的包
 *      属性：
 *          value：它和basePackages的作用是一样的，都是用于指定创建容器时要扫描的包。
 *                 我们使用此注解就等同于在xml中配置了:
 *                      <context:component-scan base-package="com.itheima"></context:component-scan>
 *  Bean
 *      作用：用于把当前方法的返回值作为bean对象存入spring的ioc容器中
 *      属性:
 *          name:用于指定bean的id。当不写时，默认值是当前方法的名称
 *      细节：
 *          当我们使用注解配置方法时，如果方法有参数，spring框架会去容器中查找有没有可用的bean对象。
 *          查找的方式和Autowired注解的作用是一样的
 *  Import
 *      作用：用于导入其他的配置类
 *      属性：
 *          value：用于指定其他配置类的字节码。
 *                  当我们使用Import的注解之后，有Import注解的类就父配置类，而导入的都是子配置类
 *  PropertySource
 *      作用：用于指定properties文件的位置
 *      属性：
 *          value：指定文件的名称和路径。
 *                  关键字：classpath，表示类路径下
 */
@Configuration
@ComponentScan("com.shepherd")
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {


}
```

