package com.crazi.dice;

public class CommonDie {

    private static Die fairSingleDie(int sides){
        Die.DieBuilder builder = new Die.DieBuilder(true);
        for(int i = 0; i < sides; i++){
            builder.addDieSide(new Die.DieSide(i+1, 1));
        }
        try {
            return builder.build();
        }
        catch (Exception ex){
            return new Die(true);
        }
    }
    public static Die D2(){
        return fairSingleDie(2);
    }
    public static Die D4(){
        return fairSingleDie(4);
    }
    public static Die D6(){
        return fairSingleDie(6);
    }
    public static Die D8(){
        return fairSingleDie(8);
    }
    public static Die D10(){
        return fairSingleDie(10);
    }
    public static Die D12(){
        return fairSingleDie(12);
    }
    public static Die D20(){
        return fairSingleDie(20);
    }
    public static Die D100(){
        return fairSingleDie(100);
    }
}
