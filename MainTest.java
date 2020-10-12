

public class MainTest {
    public static void main (String [] arg) {
        int size = 14000;
        int nThreads = 4;
        int [][] d = DataInitializer.createEdge(DataInitializer.createData(size),size);
        ConnectedComponents ccl = new ConnectedComponents(d, size + 2, true);
        ConnectedComponents ccl2  = new ConnectedComponents(d, size + 2);
        for (int i = 0; i < size + 2; i++) {
//            System.out.println(java.util.Arrays.toString(d[i]));
        }
        long timep0 = System.currentTimeMillis();
        int [][] outp = ccl.resolveLabelsParallel();
        long timep1 = System.currentTimeMillis();

        long times0 = System.currentTimeMillis();
        int [][] outs = ccl2.resolveLabels();
        long times1 = System.currentTimeMillis();


/*        for (int i = 400; i < 450; i++) {
           System.out.println("parallel: " + java.util.Arrays.toString(outp[i]));
           System.out.println("serial: " + java.util.Arrays.toString(outs[i]));
        }
*/
        long dts = times1 - times0;
        long dtp = timep1 - timep0;
        double speedUp = dts/(double)dtp;
        double efficiency = speedUp / nThreads;
        System.out.println("parallel: " + dtp + " , serial: " + dts + " speedUp: " + speedUp + "efficiency: " + efficiency);


    }

}
