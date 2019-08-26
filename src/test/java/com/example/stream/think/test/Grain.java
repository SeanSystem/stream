package com.example.stream.think.test;

public class Grain {

    @Override
    public String toString() {
        return "Grain{}";
    }
}

class Wheat extends Grain{
    @Override
    public String toString() {
        return "Wheat{}";
    }
}

class Mill {
    Grain process(){ return new Grain();}
}

class WheatMill extends Mill{
    Wheat process(){ return new Wheat();}
}

class CovariantReturn {

    public static void main(String[] args) {
        Mill m = new Mill();
        Grain g = m.process();
        System.out.println(g);
        m = new WheatMill();
        g = m.process();
        System.out.println(g);
    }
}