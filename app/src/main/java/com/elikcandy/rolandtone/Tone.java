package com.elikcandy.rolandtone;

public class Tone {
    public String category;
    public String number;
    public String name;
    public int msb;
    public int lsb;
    public int pc;

    public Tone(String category, String number, String name, int msb, int lsb, int pc) {
        this.category = category;
        this.number = number;
        this.name = name;
        this.msb = msb;
        this.lsb = lsb;
        this.pc = pc;
    }

    @Override
    public String toString() {
        return name + " (" + category + ")";
    }
}
