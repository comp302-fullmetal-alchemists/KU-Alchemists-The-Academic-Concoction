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


/* 
 *  R G B - 8 INGREDIENTS CREATED FROM THE 3 ASPECTS
//Alchemy alch1 = new Alchemy(-AlchemicalConstants.SMALL, AlchemicalConstants.SMALL, -AlchemicalConstants.LARGE); 
//Alchemy alch2 = new Alchemy(AlchemicalConstants.LARGE, AlchemicalConstants.LARGE, AlchemicalConstants.LARGE);
//Alchemy alch3 = new Alchemy(-AlchemicalConstants.LARGE, -AlchemicalConstants.LARGE, -AlchemicalConstants.LARGE);
//Alchemy alch4 = new Alchemy(-AlchemicalConstants.SMALL, AlchemicalConstants.LARGE, AlchemicalConstants.SMALL);
//Alchemy alch5 = new Alchemy(AlchemicalConstants.LARGE, AlchemicalConstants.SMALL, -AlchemicalConstants.SMALL);
//Alchemy alch6 = new Alchemy(AlchemicalConstants.SMALL,- AlchemicalConstants.LARGE, -AlchemicalConstants.SMALL);
//Alchemy alch7 = new Alchemy(AlchemicalConstants.SMALL, -AlchemicalConstants.SMALL, AlchemicalConstants.LARGE);
//Alchemy alch8 = new Alchemy(-AlchemicalConstants.LARGE, -AlchemicalConstants.SMALL, AlchemicalConstants.SMALL);









 * 
 * 
 */


