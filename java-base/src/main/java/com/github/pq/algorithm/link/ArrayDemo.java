package com.github.pq.algorithm.link;

import org.junit.Test;
import sun.text.normalizer.UTF16;

import java.io.*;
import java.util.ArrayList;

public class ArrayDemo {
    @Test
    public void test1() {
        ArrayList<String> list = new ArrayList<String>();

        list.add("1");
        list.remove(null);
        System.out.println(list.size());
        list.remove(null);
        System.out.println(list.size());
    }

    @Test
    public void test2() throws IOException, ClassNotFoundException {
        User user = new User("张三", "1234", "34938493489384934893", "18519200819");
        System.out.println(user);
        ObjectOutputStream os = new ObjectOutputStream(
                new FileOutputStream("/app/test/transient.txt")
        );
        os.writeObject(user);
        os.flush();
        os.close();
        ObjectInputStream is = new ObjectInputStream(
                new FileInputStream("/app/test/transient.txt")
        );
        user = (User) is.readObject();
        is.close();
        System.out.println(user);
    }
    @Test
    public void test3()  throws IOException, ClassNotFoundException {
        User2 user2 = new User2();
        user2.setUsername("张三");
        user2.setPwd("123456");
        System.out.println(user2.getUsername()+","+user2.getPwd());
        ObjectOutputStream os = new ObjectOutputStream(
                new FileOutputStream("/app/test/transient.txt")
        );
        os.writeObject(user2);
        os.flush();
        os.close();

        // 在反序列化之前改变pwd的值
        User2.setPwd("654321");

        ObjectInputStream is = new ObjectInputStream(
                new FileInputStream("/app/test/transient.txt")
        );
        user2 = (User2) is.readObject();
        is.close();
        System.out.println(user2.getUsername()+","+user2.getPwd());
    }

    @Test
    public void test4()  throws IOException, ClassNotFoundException {
        ArrayList array = new ArrayList();
        array.add("张三");
        array.add("李四");
        System.out.println(array.toString());

        ObjectOutputStream os = new ObjectOutputStream(
                new FileOutputStream("/app/test/transient.txt")
        );
        os.writeObject(array);
        os.flush();
        os.close();
        ObjectInputStream is = new ObjectInputStream(
                new FileInputStream("/app/test/transient.txt")
        );
        array = (ArrayList) is.readObject();
        is.close();
        System.out.println(array.toString());
    }

    @Test
    public void test5() throws IOException, ClassNotFoundException {
        DataDiySerialization data = new DataDiySerialization();
        data.setUsername("张三");
        data.setAge(21);
        System.out.println(data.toString());

        ObjectOutputStream os = new ObjectOutputStream(
                new FileOutputStream("/app/test/transient.txt")
        );
        os.writeObject(data);
        os.flush();
        os.close();

        ObjectInputStream is = new ObjectInputStream(
                new FileInputStream("/app/test/transient.txt")
        );
        data = (DataDiySerialization) is.readObject();
        is.close();
        System.out.println(data.toString());

    }

}
