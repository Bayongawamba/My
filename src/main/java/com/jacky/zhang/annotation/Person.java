package com.jacky.zhang.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Description("person_table")
public class Person {

    @Description("name")
    private String name;

    @Description("phone")
    private String phone;

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Person() {
    }

    @Description
    public String print(String name, String phone){
        System.out.println(name+phone);
        return name+phone;
    }

    public static void main(String[] args) {
        Person p = new Person("小米","18200391111");
        Class c=p.getClass();
        Method[] methods=c.getMethods();
        for (Method m : methods){
            if(!m.isAnnotationPresent(Description.class)){
                continue;
            }
            try {
                Object[] o ={"ddd","sss"};
                m.invoke(new Person(),o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
