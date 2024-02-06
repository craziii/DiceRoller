package crazi.dice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Die {

    private final List<DieSide> sides;
    private DieSide currentSide;
    private final boolean isFair;

    public Die(DieBuilder builder, boolean isFair){
        this.sides = builder.sides;
        this.isFair = isFair;
    }

    public Die(boolean isFair) {
        this.isFair = isFair;
        this.sides = null;
    }

    protected void roll(Random random) {
        double totalWeight;
        if (isFair) {
            totalWeight = sides.size();
        } else {
            totalWeight = sides.stream().mapToDouble(x -> x.weight).sum();
        }
        double randomValue = random.nextDouble() * totalWeight;
        double currentWeight = 0;
        for (DieSide side : sides) {
            currentWeight = currentWeight + side.weight;
            if (currentWeight >= randomValue) {
                setCurrentSide(side);
                return;
            }
        }
    }

    protected List<Integer> roll(List<Die> dice, Random random){
        List<Integer> output = new ArrayList<>();
        for(Die die : dice){
            output.add(die.rollAndReturnVal(random));
        }
        return output;
    }

    protected int rollAndReturnVal(Random random){
        roll(random);
        return getCurrentValue();
    }

    public List<DieSide> getSides() {
        return sides;
    }

    public DieSide getCurrentSide() {
        return currentSide;
    }

    public void setCurrentSide(DieSide currentSide) {
        this.currentSide = currentSide;
    }

    public int getCurrentValue(){
        return currentSide.val;
    }

    public static class DieSide{
        final int val;
        final float weight;

        public DieSide(int val, float weight) {
            this.val = val;
            this.weight = weight;
        }
    }

    public static class DieBuilder{
        protected List<DieSide> sides = new ArrayList<>();
        private final boolean isFair;

        public DieBuilder(boolean isFair){
            this.isFair = isFair;
        }
        public void addDieSide(DieSide side){
            this.sides.add(side);
        }

        public Die build() throws Exception {
            if(isFair){
                if(sides.stream().anyMatch(side -> side.weight != 1)){
                    throw new Exception("DieBuilder: Not fair die");
                }
            }
            if(sides.stream().anyMatch(side -> side.weight < 0)){
                throw new Exception("DieBuilder: Weight of side < 0");
            }
            sides.sort(Comparator.comparingInt(s -> s.val));
            return new Die(this, isFair);
        }
    }
}


