package com.shepherd.annotation;

import com.shepherd.annotation.anno.Select;
import com.shepherd.annotation.anno.TableField;
import com.shepherd.annotation.anno.TableName;
import com.shepherd.annotation.mapper.AccountMapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/8/24 11:58
 */
public class TestAnnotationDemo {
    public static void main(String[] args) {
        AccountMapper accountMapper = new AccountMapper();
        Class c = accountMapper.getClass();
        StringBuilder sql = new StringBuilder();
        StringBuilder column = new StringBuilder();
        Method[] declaredMethods = c.getDeclaredMethods();
        for(Method method : declaredMethods) {
            Select select = method.getAnnotation(Select.class);
            if (select != null) {
                sql.append(select.value());
                Class<?>[] parameterTypes = method.getParameterTypes();
                for(Class pt : parameterTypes) {
                    if (pt == Account.class) {
                        TableName table = (TableName) pt.getAnnotation(TableName.class);
                        sql.append(" ").append(table.name());
                        Field[] fields = pt.getDeclaredFields();
                        for (Field field : fields) {
                            column.append(" ");
                            TableField tableField = field.getAnnotation(TableField.class);
                            if (tableField == null) {
                                column.append(field.getName());
                            } else {
                                column.append(tableField.value());
                            }
                            column.append(",");
                        }
                    }
                }

            }
        }
        String s = String.format(sql.toString(), column.toString().substring(1, column.toString().length() - 1));
        System.out.println(s);
    }
}
