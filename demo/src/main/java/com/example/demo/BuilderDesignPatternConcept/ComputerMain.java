package com.example.demo.BuilderDesignPatternConcept;

public class ComputerMain {
    public static void main(String[] args) {
        Computer cm = ComputerBuilder.builder()
                .setRam(8)
                .setRom(32)
                .build();
        System.out.println(cm.getRam()+" "+cm.getRom());


    }
}
