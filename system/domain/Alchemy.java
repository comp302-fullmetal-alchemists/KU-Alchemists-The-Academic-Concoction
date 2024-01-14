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

    public static Alchemy getAlchemy(int index) {
        switch(index) {
            case 0: return new Alchemy(-AlchemicalConstants.SMALL, AlchemicalConstants.SMALL, -AlchemicalConstants.LARGE); 
            case 1: return new Alchemy(AlchemicalConstants.LARGE, AlchemicalConstants.LARGE, AlchemicalConstants.LARGE);
            case 2: return new Alchemy(-AlchemicalConstants.LARGE, -AlchemicalConstants.LARGE, -AlchemicalConstants.LARGE);
            case 3: return new Alchemy(-AlchemicalConstants.SMALL, AlchemicalConstants.LARGE, AlchemicalConstants.SMALL);
            case 4: return new Alchemy(AlchemicalConstants.LARGE, AlchemicalConstants.SMALL, -AlchemicalConstants.SMALL);
            case 5: return new Alchemy(AlchemicalConstants.SMALL,- AlchemicalConstants.LARGE, -AlchemicalConstants.SMALL);
            case 6: return new Alchemy(AlchemicalConstants.SMALL, -AlchemicalConstants.SMALL, AlchemicalConstants.LARGE);
            case 7: return new Alchemy(-AlchemicalConstants.LARGE, -AlchemicalConstants.SMALL, AlchemicalConstants.SMALL);
        }
        return null;
    }

    public int[][] getAlchemical() {
        return alchemical;
    }

    public static String combine(Alchemy alch1, Alchemy alch2) {
        String status = "neutral";
        String[] colors = {"Red", "Green", "Blue"};
        for (int i = 0; i < 3; i++) {
            if (alch1.alchemical[i][0] != alch2.alchemical[i][0]) {
                if (alch1.alchemical[i][1] == alch2.alchemical[i][1]) {
                    status = colors[i] +  (alch1.alchemical[i][1] == 1? "+" : "-");
                    return status;
                }
            }
        }
        return status;
        
    }
    
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < 3; i++){
            if (this.alchemical[i][0] == 2) res += "b";
            else res += "s";
            if (this.alchemical[i][1] > 0)  res += "+";
            else res += "-";
        }
        return res;
    }

    
    public boolean equals(Alchemy alch) {
        return this.toString().equals(alch.toString());
    }
}


/* 
 *  R G B - 8 INGREDIENTS CREATED FROM THE 3 ASPECTS - According to the actual game rules
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


