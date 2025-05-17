package com.example.demo.EqualAndHashCodeConcept;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)  //this is the equals and hash method which will return same hashcode value whenever a new obj is created
public class PojoForEqualsAndHashConcept {


    private  String name;

    @EqualsAndHashCode.Include
    private  Long id;



}
