package com.example.demo.BuilderDesignPatternConcept;


public class ComputerBuilder {
    int ram,rom;
    public static ComputerBuilder builder()
    {
        return new ComputerBuilder();
    }
    public ComputerBuilder setRam(int ram) {
        this.ram = ram;
        return this;
    }
    public ComputerBuilder setRom(int rom) {
        this.rom = rom;
        return this;
    }

    public Computer build(){
        return new Computer(ram,rom);
    }




}
