package com.example.demo.EqualAndHashCodeConcept;

import java.util.HashSet;
import java.util.Set;

public class EqualsAndHashcodeConcept {
    public static void main(String[] args) {
//in this concept see 2 files 1.PojoForEqualsAndHashConcept and this main file ...
        //in general concept when we use "new" always a new object will be created even tho we create for a same class
        // so in some case scenarios we check whenever we create an object we create for that particular pojo class its created
        // that time when we use "new" always new hashcode will be generated so to override that we need to add
        //@EqualsAndHashCode above the pojo class
        // 2nd method alt+insert a list will be shown , in that click equals and hash method .. override method will be added


        PojoForEqualsAndHashConcept e1 = new PojoForEqualsAndHashConcept();
        PojoForEqualsAndHashConcept e2 = new PojoForEqualsAndHashConcept();
        System.out.println(e1.hashCode());
        System.out.println(e2.hashCode());
        Set<PojoForEqualsAndHashConcept> s = new HashSet<>();
        s.add(e1);
        s.add(e2);
        System.out.println(s.size());
    }
}
