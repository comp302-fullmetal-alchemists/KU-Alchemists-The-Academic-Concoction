package system.domain;

public class Alchemy {
    private int[][] alchemical;

    public class AlchemicalConstants {
        public static final int SMALL = 1;
        public static final int LARGE = 2;
        public static final int RED = 0;
        public static final int GREEN = 1;
        public static final int BLUE = 2;
    }

    public Alchemy(int redAspect, int greenAspect, int blueAspect) {
        alchemical = new int[3][2]; // 3 aspects, each with a size and sign

        alchemical[AlchemicalConstants.RED][0] = Math.abs(redAspect);
        alchemical[AlchemicalConstants.RED][1] = Integer.signum(redAspect);

        alchemical[AlchemicalConstants.GREEN][0] = Math.abs(greenAspect);
        alchemical[AlchemicalConstants.GREEN][1] = Integer.signum(greenAspect);

        alchemical[AlchemicalConstants.BLUE][0] = Math.abs(blueAspect);
        alchemical[AlchemicalConstants.BLUE][1] = Integer.signum(blueAspect);
    }

  
}