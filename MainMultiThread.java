public class MainMultiThread {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final int [][] TEST_22 =
                    {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    public static final int [][] TEST_24 =
            {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0, 0,0, 0, 0}};


    public static final int [][] TEST_12 =
            {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0,-1, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0,-1, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0,0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};


    public static void main (String [] arg) {
        int size;
        int nThreads;
        if ( arg.length > 0) {
            size = Integer.parseInt(arg[0]);
            nThreads = Integer.parseInt(arg[1]);
        }
        else {
            size = 10000;
            nThreads = 5;
        }
        System.out.println("#nThreads   #workload    # time   #speedup   #efficiency");
        int [][] d = DataInitializer.createEdge(DataInitializer.createData(size),size);
        ConnectedComponents [] ccl = new ConnectedComponents[nThreads];
        ConnectedComponents cclSingle = new ConnectedComponents(d,size+2);

        long times0 = System.currentTimeMillis();
        cclSingle.resolveLabels();
        long times1 = System.currentTimeMillis();
        long dts = times1 - times0;

        System.out.println(" Sequential time = " + dts);

        for (int i = 1; i < nThreads; i++) {
            createRunCclThread(d, size + 2, dts, i);
        }
    }

        public static void runCclThread (ConnectedComponents ccl, long dts, int n){
            long time0 = System.currentTimeMillis();
            int [][] out  = ccl.resolveLabelsParallel();
            long time1 = System.currentTimeMillis();
            long dtp = time1 - time0;
            double speedUp = dts/(double)dtp;
            double efficiency = speedUp / n;
            System.out.println("parallel thread n: " + n + " | time: "+ dtp + " | serial: " + dts + " | speedUp: " + speedUp + " | efficiency: " + efficiency);

        }


        //Using this method and the tests (22 and 24) it is clear that for the current version of the program, to have sound results, it is necessary to provide a size multiple of all the number of threads involved
        public static void createRunCclThreadPrint (int [][] data, int size, long dts, int n) {
            ConnectedComponents ccl = new ConnectedComponents(data, size, n);
            long time0 = System.currentTimeMillis();
            int [][] out  = ccl.resolveLabelsParallel();
            long time1 = System.currentTimeMillis();
            //System.out.println(java.util.Arrays.toString(out[size-2]));
/*            for (int i = 0; i < size; i++) {
                System.out.println( java.util.Arrays.toString(out[i]));
            }
*/
            for (int i = 0; i < size; i++) {
                System.out.print(" Line # :  " + i%10 + "    ||  ");
                for (int j = 0; j < size; j++) {
                    if (out[i][j]>9)
                        System.out.print(out[i][j] + "   ");
                    else
                    switch (out[i][j]){
                        case 1:  System.out.print(ANSI_RED + out[i][j] + "    " + ANSI_RESET);
                        break;

                        case 2:  System.out.print(ANSI_BLUE + out[i][j] + "    " + ANSI_RESET);
                            break;

                        case 3:  System.out.print(ANSI_WHITE + out[i][j] + "    " + ANSI_RESET);
                            break;

                        case 4:  System.out.print(ANSI_CYAN + out[i][j] + "    " + ANSI_RESET);
                            break;

                        case 5:  System.out.print(ANSI_GREEN + out[i][j] + "    " + ANSI_RESET);
                            break;

                        case 6:  System.out.print(ANSI_PURPLE + out[i][j] + "    " + ANSI_RESET);
                            break;

                        case 7:  System.out.print(ANSI_YELLOW + out[i][j] + "    " + ANSI_RESET);
                            break;

                         default: System.out.print(out[i][j] + "    ");

                    }
                }
                System.out.println();
            }

            long dtp = time1 - time0;
            double speedUp = dts/(double)dtp;
            double efficiency = speedUp / n;
            System.out.println("parallel thread n: " + n + " | time: "+ dtp + " | serial: " + dts + " | speedUp: " + speedUp + " | efficiency: " + efficiency);


        }

    public static void createRunCclThread (int [][] data, int size, long dts, int n) {
        ConnectedComponents ccl = new ConnectedComponents(data, size, n);
        long time0 = System.currentTimeMillis();
        int [][] out  = ccl.resolveLabelsParallel();
        long time1 = System.currentTimeMillis();

        long dtp = time1 - time0;
        double speedUp = dts/(double)dtp;
        double efficiency = speedUp / n;
        System.out.println( n + "        " + size + "        " + dtp + "        " + speedUp + "        " + efficiency);
    }


    }


