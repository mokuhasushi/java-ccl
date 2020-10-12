

public class MainSingleThread {

    public static void main(String[] args) {
        int size = 10000;
/*        int[][] d1 = new int[12][12];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if ( i == 0 || i == 11 || j == 0 || j == 11)
                    d1[i][j] = 0;
                else if (i > 0 && i < 3)
                    if (j >= 0 && j < 3)
                        d1[i][j] = -1;
                    else d1[i][j] = 0;
                else if (i >= 4 && i < 6)
                    if (j > 0 && j < 3 || j > 6 && j < 9)
                        d1[i][j] = -1;
                    else
                        d1[i][j] = 0;
                else
                    d1[i][j] = 0;
            }
        }
        for (int i = 0; i < 12; i++) {
            System.out.println(java.util.Arrays.toString(d1[i]));
        }
*/
        int [][]d2 = DataInitializer.createData(size);
        d2 = DataInitializer.createEdge(d2,size);
        size = size + 2;
        for (int i = 0; i < size; i++) {
//            System.out.println(java.util.Arrays.toString(d2[i]));
        }


        ConnectedComponents ccl = new ConnectedComponents(d2,size);

        long time0 = System.currentTimeMillis();
        int [][] out = ccl.resolveLabels();
        long time1 = System.currentTimeMillis();

        long dt = time1 - time0;
        System.out.println(dt);
/*        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(out[i][j]);
                if (out[i][j] > 99)
                    System.out.print("  ");
                else if (out[i][j] > 9) {
                    System.out.print("    ");
                } else {
                    System.out.print("      ");
                }
            }
            System.out.println();
       }
*/

    }
}
