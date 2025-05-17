package com.example.demo.PrototypeDesignPatternConcept;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Prototype {

    String name;
    String id;

    public Prototype() {

    }

    public Prototype cloneObjectMethod(String id, String name)
    {
        return new Prototype(id,name);
    }

}
