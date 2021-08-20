package com.shepherd.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/8/20 11:37
 */
public class TestReflect {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        /**
         获取Class对象的方式：
         1.Class.forName("全类名")：将字节码文件加载进内存，返回Class对象
         2.类名.class：通过类名的属性class获取
         3.对象.getClass()：getClass()方法在Object类中定义着。
         */
//        //1.Class.forName("全类名")
//        Class cls1 = Class.forName("com.shepherd.reflect.Person");
//        System.out.println(cls1);
//        //2.类名.class
//        Class cls2 = Person.class;
//        System.out.println(cls2);
//        //3.对象.getClass()
//        Person p = new Person();
//        Class cls3 = p.getClass();
//        System.out.println(cls3);
//        //==比较三个对象
//        System.out.println(cls1 == cls2);//true
//        System.out.println(cls1 == cls3);//true
//
//        Class c = Student.class;
//        System.out.println(c);
//        System.out.println(c == cls1);//false
//
        Class c = Student.class;
        //获取public类型的属性，包括父类的
        Field[] fields = c.getFields();
        System.out.println(Arrays.asList(fields));

        //只获取当前类所有属性
        Field[] declaredFields = c.getDeclaredFields();
        System.out.println(Arrays.asList(declaredFields));


        //获取当前类的构造对象
        Constructor constructor = c.getConstructor();
        Student stu = (Student) constructor.newInstance();
        System.out.println(stu);

        Field field = c.getField("sn");
        System.out.println(field);
        //获取名称
        System.out.println(field.getName());
        //获取类型
        System.out.println(field.getType());
        //获取修饰符
        System.out.println(Modifier.toString(field.getModifiers()));
        //获取注解
        System.out.println(Arrays.asList(field.getAnnotations()));

        //获取public属性值
        Long sn  = (long) field.get(stu);
        System.out.println(sn);


        //获取private属性值，这时候需要setAccessible(true)，因为私有属性是不能被其他类访问的
        Field declaredField = c.getDeclaredField("schoolName");
        declaredField.setAccessible(true);
        String schoolName = (String)declaredField.get(stu);
        System.out.println(schoolName);

        //修改的对象属性值
        field.set(stu, 1l);
        declaredField.set(stu, "浙大");
        System.out.println(stu);

        //获取当前类、父类的public方法,
        Method[] methods = c.getMethods();
        System.out.println(Arrays.asList(methods));

        //获取当前类的所有方法
        Method[] declaredMethods = c.getDeclaredMethods();
        System.out.println(Arrays.asList(declaredMethods));

        Method method = c.getMethod("getAddress", String.class, String.class, String.class);

        //获取方法的修饰符
        System.out.println(Modifier.toString(method.getModifiers()));

        //获取方法的放回类型
        System.out.println(method.getReturnType());

        //获取方法的参数类型
        System.out.println(Arrays.asList(method.getParameterTypes()));

        //获取参数抛出的异常
        System.out.println(Arrays.asList(method.getExceptionTypes()));


        Method doHomework = c.getDeclaredMethod("doHomework", String.class, Integer.class);
        doHomework.setAccessible(true);
        System.out.println(doHomework.getReturnType());
        System.out.println(doHomework.getName());
        doHomework.invoke(stu, "数学", 2);

        int[] a = new int[100];
        Class<? extends int[]> aClass = a.getClass();
        System.out.println(aClass);
        System.out.println(aClass.getComponentType());

        Object array = Array.newInstance(String.class, 10);
        Array.set(array, 0, "hello");
        System.out.println(array);




//        testReflection();
    }


        public static void testReflection() {
            Class<?> c = HashMap.class;
            //获取类名
            System.out.println("Class : " + c.getCanonicalName());
            //获取类限定符
            System.out.println( "Modifiers : " + Modifier.toString(c.getModifiers()));
            //获取类泛型信息
            TypeVariable[] tv = c.getTypeParameters();
            if (tv.length != 0) {
                StringBuilder parameter = new StringBuilder("Parameters : ");
                for (TypeVariable t : tv) {
                    parameter.append(t.getName());
                    parameter.append(" ");
                }
                System.out.println(parameter.toString());
            } else {
                System.out.println("  -- No Type Parameters --");
            }
            //获取类实现的所有接口
            Type[] intfs = c.getGenericInterfaces();
            if (intfs.length != 0) {
                StringBuilder interfaces = new StringBuilder("Implemented Interfaces : ");
                for (Type intf : intfs){
                    interfaces.append(intf.toString());
                    interfaces.append(" ");
                }
                System.out.println(interfaces.toString());
            } else {
                System.out.println( "  -- No Implemented Interfaces --");
            }
            //获取类继承数上的所有父类
            List<Class> l = new ArrayList<>();
            printAncestor(c, l);
            if (l.size() != 0) {
                StringBuilder inheritance = new StringBuilder("Inheritance Path : ");
                for (Class<?> cl : l){
                    inheritance.append(cl.getCanonicalName());
                    inheritance.append(" ");
                }
                System.out.println(inheritance.toString());
            } else {
                System.out.println("  -- No Super Classes --%n%n");
            }
            //获取类的注解(只能获取到 RUNTIME 类型的注解)
            Annotation[] ann = c.getAnnotations();
            if (ann.length != 0) {
                StringBuilder annotation = new StringBuilder("Annotations : ");
                for (Annotation a : ann){
                    annotation.append(a.toString());
                    annotation.append(" ");
                }
                System.out.println(annotation.toString());
            } else {
                System.out.println("  -- No Annotations --%n%n");
            }
        }

        private static void printAncestor(Class<?> c, List<Class> l) {
            Class<?> ancestor = c.getSuperclass();
            if (ancestor != null) {
                l.add(ancestor);
                printAncestor(ancestor, l);
            }
        }


}
