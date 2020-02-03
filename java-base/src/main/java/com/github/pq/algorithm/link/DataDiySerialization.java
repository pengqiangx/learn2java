package com.github.pq.algorithm.link;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataDiySerialization implements Serializable {

    private transient String username;

    private transient int age;

    private void writeObject(ObjectOutputStream os) throws IOException {
        os.defaultWriteObject();
        os.writeObject(username+"/");
        os.writeObject(age);
        System.out.println("write Object of "+this.getClass().getName());
    }

    private void readObject(ObjectInputStream os) throws IOException, ClassNotFoundException {
        os.defaultReadObject();
        username =(String) os.readObject()+"x";
        age = (int)os.readObject();
        System.out.println("read Object of "+this.getClass().getName());
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age ;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "DataDiySerialization{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
