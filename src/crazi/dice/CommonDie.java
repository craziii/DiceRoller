package crazi.dice;

public class CommonDie {

    private Die fairSingleDie(int sides){
        Die.DieBuilder builder = new Die.DieBuilder(true);
        for(int i = 0; i < sides; i++){
            builder.addDieSide(new Die.DieSide(i+1, 1));
        }
        try {
            return builder.build();
        }
        catch (Exception ex){
            return new Die();
        }
    }
    public Die D2(){
        return fairSingleDie(2);
    }
    public Die D4(){
        return fairSingleDie(4);
    }
    public Die D6(){
        return fairSingleDie(6);
    }

    public Die D8(){
        return fairSingleDie(8);
    }

    public Die D10(){
        return fairSingleDie(10);
    }
    public Die D12(){
        return fairSingleDie(12);
    }
    public Die D20(){
        return fairSingleDie(20);
    }

    public Die D100(){
        return fairSingleDie(100);
    }
}
