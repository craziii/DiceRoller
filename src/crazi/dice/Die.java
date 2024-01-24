package crazi.dice;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Die {

    protected final List<DieSide> sides;
    protected DieSide currentSide;

    public Die(DieBuilder builder){
        this.sides = builder.sides;
    }

    public Die() {
        this.sides = null;
    }

    protected void roll(Random random){
        double totalWeight = sides.stream().mapToDouble(x -> x.weight).sum();
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
        protected List<DieSide> sides;
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
            return new Die(this);
        }
    }
}


