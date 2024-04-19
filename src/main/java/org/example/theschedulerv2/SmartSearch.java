package org.example.theschedulerv2;

public class SmartSearch {

    private static int min(int left, int top, int diagonal){
        int min = Math.min(left, top);
        if(diagonal < min) return diagonal;
        return min;
    }
    private static int levDistance(String x, String y){
        if(x.equals("")) return y.length();
        if(y.equals("")) return x.length();
        if(x.equals(y)) return 0;

        int[][] levVals = new int[2][x.length()+1];

        for(int i = 0; i <= x.length(); i++){
            levVals[0][i] = i;
        }

        for(int indexY= 0; indexY < y.length(); indexY++){
            // Set the empty string distance
            levVals[1][0] = levVals[0][0] + 1;

            char yChar = y.charAt(indexY);
            System.out.print(yChar + ": ");
            for(int indexX = 1; indexX <= x.length(); indexX++){
                if(yChar == x.charAt(indexX-1)) {
                    levVals[1][indexX] = levVals[0][indexX-1];
                }else{
                    levVals[1][indexX] = min(
                            levVals[1][indexX-1]+1,
                            levVals[0][indexX]+1,
                            levVals[0][indexX-1]+1
                    );
                }
            }

            for(int i = 0; i <= x.length(); i++){
                levVals[0][i] = levVals[1][i];
                System.out.print(levVals[0][i] + " ");
            }
            System.out.println();

        }

        return levVals[1][x.length()];
    }
}
