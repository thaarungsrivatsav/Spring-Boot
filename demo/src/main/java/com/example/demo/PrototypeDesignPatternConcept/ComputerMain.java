package com.example.demo.PrototypeDesignPatternConcept;

public class ComputerMain {
    public static void main(String[] args) {
        Prototype obj1 = new Prototype();
        obj1.setId("1");
        obj1.setName("employee 1");
        Prototype obj2 = obj1.cloneObjectMethod(obj1.getId(),obj1.getName());
        System.out.println("Object 1 hashcode = "+obj1.hashCode()+" Object 2 hashcode = "+obj2.hashCode());
        System.out.println("Printing using object 1 : Id = "+obj1.getId()+" Name = "+obj1.getName());
        System.out.println("Printing values inside object 2 using cloned object : Id = "+obj2.getId()+" Name = "+obj2.getName());
    }
}
