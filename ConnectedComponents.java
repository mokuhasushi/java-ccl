//based on https://www.sciencedirect.com/science/article/pii/S0031320317301693 algorithm in section 3.3.3.1

public class ConnectedComponents {
    private int[][] data;
    private int dataSize;
    private UnionFind labels;
    private int nextlabel = 1;
    private CCLThread[] threads;
    private int nThreads;
    private boolean square = false;


    //Constructor for sequential computing

    public ConnectedComponents(int[][] data, int size) {
        this.data = data;
        this.dataSize = size;
        this.labels = new UnionFind(size * size / 4);
    }

    //Constructor for square computing

    public ConnectedComponents(int[][] data, int size, boolean flag) {
        this.data = data;
        this.dataSize = size;
        int lmaxSize = size * size/4;
        this.labels = new UnionFind(lmaxSize);
        this.square = flag;
        this.threads = new CCLThread[4];
        this.nThreads = 4;
        for (int i = 0; i < 4; i++) {
            this.threads[i] = new CCLThread(data, i % 2 * size / 2, i / 2 * size / 2 , lmaxSize / 4 * i, size / 2, labels);
        }
    }

    //Constructor for generic multithread computing. Note that to have a correct result the size of the array must be a multiple of the threads number.

    public ConnectedComponents(int[][] data, int size, int threads) {
        this.data = data;
        this.dataSize = size;
        int lmaxSize = size * size/4;
        this.labels = new UnionFind(lmaxSize);
        this.nThreads = threads;
        this.threads = new CCLThread[threads];
        int sizeY = size / threads;

        for (int i = 0; i < threads; i++) {
            this.threads[i] = new CCLThread(data, 0, i * sizeY , lmaxSize / threads * i, size, size / threads, labels);
//            System.out.println("nThreads: " + threads + "y, sizeY" + y + sizeY);
        }
    }


    // Sequential solving algorithm. Often this is inferior to the multithread computation, even with a single thread running, because of additional checks performed, which are not necessary in this version

    public int[][] resolveLabels() {
        for (int i = 1; i < dataSize - 1; i++) {
            for (int j = 1; j < dataSize - 1; j++) {
                if (data[i][j] == 0)
                    continue;
                else {
                    if (data[i - 1][j] > 0) {
                        data[i][j] = labels.find(data[i - 1][j] - 1) + 1;
                    } else if (data[i - 1][j + 1] > 0) {
                        if (data[i][j - 1] > 0)
                            data[i][j] = labels.union(data[i - 1][j + 1] - 1, data[i][j - 1] - 1) + 1;
                        else if (data[i - 1][j - 1] > 0)
                            data[i][j] = labels.union(data[i - 1][j - 1] - 1, data[i - 1][j + 1] - 1) + 1;
                        else data[i][j] = labels.find(data[i - 1][j + 1] - 1) + 1;
                    } else if (data[i][j - 1] > 0)
                        data[i][j] = labels.find(data[i][j - 1] - 1) + 1;
                    else if (data[i - 1][j - 1] > 0)
                        data[i][j] = labels.find(data[i - 1][j - 1] - 1) + 1;
                    else
                        data[i][j] = nextlabel++;
                }
            }
        }

        labels.flatten1();

        for (int i = 1; i < dataSize - 1; i++) {
            for (int j = 1; j < dataSize - 1; j++) {
                if (data[i][j] != 0)
                    data[i][j] = labels.find(data[i][j] - 1) + 1;
            }
        }
        return data;
    }

    //This is the multithreaded algorithm. Lines from 102 to 185 are specific for the division in squares of the matrix. This solution didn't prove itself advantagious, but the code is left for reference

    public int[][] resolveLabelsParallel() {
        for (int i = 0; i < nThreads; i++) {
            threads[i].start();
        }
        for (int i = 0; i < nThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // A division of the matrix in a 2x2 square did not provide good results.
        if (square) {
            int j = dataSize / 2;
            int i;
            for (i = 0; i < dataSize / 2 - 1; i++) {
                if (data[i][j] == 0)
                    continue;
                else {
                    if (data[i - 1][j] > 0) {
                        data[i][j] = labels.find(data[i - 1][j] - 1) + 1;
                    } else if (data[i - 1][j + 1] > 0) {
                        if (data[i][j - 1] > 0)
                            data[i][j] = labels.union(data[i - 1][j + 1] - 1, data[i][j - 1] - 1) + 1;
                        else if (data[i - 1][j - 1] > 0)
                            data[i][j] = labels.union(data[i - 1][j - 1] - 1, data[i - 1][j + 1] - 1) + 1;
                        else data[i][j] = labels.find(data[i - 1][j + 1] - 1) + 1;
                    } else if (data[i][j - 1] > 0)
                        data[i][j] = labels.find(data[i][j - 1] - 1) + 1;
                    else if (data[i - 1][j - 1] > 0)
                        data[i][j] = labels.find(data[i - 1][j - 1] - 1) + 1;
                    else
                        data[i][j] = nextlabel++;
                }

            }
            i = dataSize / 2;
            for (j = 0; j < dataSize; j++) {
                if (data[i][j] == 0)
                    continue;
                else {
                    if (data[i - 1][j] > 0) {
                        data[i][j] = labels.find(data[i - 1][j] - 1) + 1;
                    } else if (data[i - 1][j + 1] > 0) {
                        if (data[i][j - 1] > 0)
                            data[i][j] = labels.union(data[i - 1][j + 1] - 1, data[i][j - 1] - 1) + 1;
                        else if (data[i - 1][j - 1] > 0)
                            data[i][j] = labels.union(data[i - 1][j - 1] - 1, data[i - 1][j + 1] - 1) + 1;
                        else data[i][j] = labels.find(data[i - 1][j + 1] - 1) + 1;
                    } else if (data[i][j - 1] > 0)
                        data[i][j] = labels.find(data[i][j - 1] - 1) + 1;
                    else if (data[i - 1][j - 1] > 0)
                        data[i][j] = labels.find(data[i - 1][j - 1] - 1) + 1;
                    else
                        data[i][j] = nextlabel++;
                }
            }
            j = dataSize / 2;
            for (i = dataSize / 2 + 1; i < dataSize; i++) {
                if (data[i][j] == 0)
                    continue;
                else {
                    if (data[i - 1][j] > 0) {
                        data[i][j] = labels.find(data[i - 1][j] - 1) + 1;
                    } else if (data[i - 1][j + 1] > 0) {
                        if (data[i][j - 1] > 0)
                            data[i][j] = labels.union(data[i - 1][j + 1] - 1, data[i][j - 1] - 1) + 1;
                        else if (data[i - 1][j - 1] > 0)
                            data[i][j] = labels.union(data[i - 1][j - 1] - 1, data[i - 1][j + 1] - 1) + 1;
                        else data[i][j] = labels.find(data[i - 1][j + 1] - 1) + 1;
                    } else if (data[i][j - 1] > 0)
                        data[i][j] = labels.find(data[i][j - 1] - 1) + 1;
                    else if (data[i - 1][j - 1] > 0)
                        data[i][j] = labels.find(data[i - 1][j - 1] - 1) + 1;
                    else
                        data[i][j] = nextlabel++;
                }

            }

            labels.flatten1();

            LabelSolverThread[] lsThread = new LabelSolverThread[4];
            for (int k = 0; k < 4; k++) {
                lsThread[k] = new LabelSolverThread(data, k / 2 * dataSize / 2, k % 2 * dataSize / 2, dataSize / 2, dataSize/2, labels);
                lsThread[k].start();
            }
            for (int k = 0; k < 4; k++) {
                try {
                    lsThread[k].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } //square
        //Here there is some sequential code for joining the edges of the submatrices. This has to be sequential
        else {
            for (int i = dataSize / nThreads ; i < dataSize; i += dataSize/nThreads) {
/*                System.out.println(i + "    " + java.util.Arrays.toString(data[i ]));
                try {
                    System.out.println(i + "    " + java.util.Arrays.toString(data[i + 1]));
                }catch (IndexOutOfBoundsException e ){}*/
                for (int j = 0; j < dataSize; j++) {
                    if (data[i][j] == 0)
                        continue;
                    else {
                        if (data[i - 1][j] > 0) {
                            data[i][j] = labels.union(data[i - 1][j] - 1, data[i][j] - 1) + 1;
                        } else if (data[i - 1][j + 1] > 0) {
                            if (data[i][j - 1] > 0)
                                data[i][j] = labels.union(data[i - 1][j + 1] - 1, data[i][j - 1] - 1) + 1;
                            else if (data[i - 1][j - 1] > 0)
                                data[i][j] = labels.union(data[i - 1][j - 1] - 1, data[i - 1][j + 1] - 1) + 1;
                            else data[i][j] = labels.union(data[i - 1][j + 1] - 1, data[i][j] - 1) + 1;
                        } //The next condition is already checked inside the threads, since it is operated on the same line
                        //else if (data[i][j - 1] > 0)
                          //  data[i][j] = labels.find(data[i][j - 1] - 1) + 1;
                        else if (data[i - 1][j - 1] > 0)
                            data[i][j] = labels.union(data[i - 1][j - 1] - 1, data[i][j] - 1) + 1;
                        else ;
                            //Do nothing
//                            System.out.println("Do nothing");
                            //data[i][j] = nextlabel++;
                    }
                }
            }
//            System.out.println(labels.toString());
            //Use of flatten2 increases computation time of about 50 ms for a workload of 10800
            labels.flatten1();

//            System.out.println(labels.toString());

            //  Use of threads for label resolution can significantly reduce computation time
            LabelSolverThread[] lsThread = new LabelSolverThread[nThreads];
            for (int k = 0; k < nThreads; k++) {
                lsThread[k] = new LabelSolverThread(data, 0, k * dataSize / nThreads, dataSize, dataSize/nThreads, labels);
                lsThread[k].start();
            }
            for (int k = 0; k < nThreads; k++) {
                try {
                    lsThread[k].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }

        return data;
    }
}


    class CCLThread extends Thread {

        private int[][] data;
        private int lstart;
        private int dataSize;
        private int nextLabel;
        private UnionFind labels;
        private int x0, y0;
        private int sizeX, sizeY;


        public CCLThread(int[][] localData, int x, int y, int lstart, int size, UnionFind labels) {
            this.data = localData;
            this.lstart = lstart;
            this.sizeY = size;
            this.sizeX = size;
            this.labels = labels;
            this.nextLabel = 1 + lstart;
            this.x0 = x;
            this.y0 = y;
        }

        public CCLThread(int[][] localData, int x, int y, int lstart, int sizeX, int sizeY, UnionFind labels) {
            this.data = localData;
            this.lstart = lstart;
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.labels = labels;
            this.nextLabel = 1 + lstart;
            this.x0 = x;
            this.y0 = y;
        }

        @Override
        public void run() {
            resolveLocalLabels();
        }
        public void resolveLocalLabels() {
            for (int i = y0; i < sizeY + y0; i++) {
                for (int j = x0; j < sizeX + x0; j++) {
                    if (data[i][j] == 0)                                                                                                                            //case: background pixel
                        continue;
                    else if (i == y0 && j == x0)
                        data[i][j] = nextLabel++;                                                                                                          //top left corner
                    else if (i == y0)
                        if (data[i][j - 1] > 0)                                                                                                                       //first row
                            data[i][j] = labels.find(data[i][j - 1] - 1) + 1;
                        else
                            data[i][j] = nextLabel++;
                    else if (j == x0)                                                                                                                                //first column
                        if (data[i - 1][j] > 0)
                            data[i][j] = labels.find(data[i - 1][j] - 1) + 1;
                        else if (data[i - 1][j + 1] > 0)
                            data[i][j] = labels.find(data[i - 1][j + 1] - 1) + 1;
                        else data[i][j] = nextLabel++;
                    else if (j == x0 + sizeX - 1) {                                                                                                      //last column
                        if (data[i - 1][j] > 0)
                            data[i][j] = labels.find(data[i - 1][j] - 1) + 1;
                        else if (data[i][j - 1] > 0)
                            data[i][j] = labels.find(data[i][j - 1] - 1) + 1;
                        else if (data[i - 1][j - 1] > 0)
                            data[i][j] = labels.find(data[i - 1][j - 1] - 1) + 1;
                        else
                            data[i][j] = nextLabel++;
                    } else {                                                                                                                                                      //general case
                        if (data[i - 1][j] > 0) {
                            data[i][j] = labels.find(data[i - 1][j] - 1) + 1;
                        } else if (data[i - 1][j + 1] > 0) {
                            if (data[i][j - 1] > 0)
                                data[i][j] = labels.union(data[i - 1][j + 1] - 1 , data[i][j - 1] - 1) + 1;
                            else if (data[i - 1][j - 1] > 0)
                                data[i][j] = labels.union(data[i - 1][j - 1] - 1, data[i - 1][j + 1] - 1) + 1;
                            else data[i][j] = labels.find(data[i - 1][j + 1] - 1) + 1;
                        } else if (data[i][j - 1] > 0)
                            data[i][j] = labels.find(data[i][j - 1] - 1) + 1;
                        else if (data[i - 1][j - 1] > 0)
                            data[i][j] = labels.find(data[i - 1][j - 1] - 1) + 1;
                        else
                            data[i][j] = nextLabel++;
                    }
                }

            }
        }
        //
    }

    class LabelSolverThread extends Thread {
        private int [][] data;
        private UnionFind labels;
        private int x; private int y;
        private int sizeX, sizeY;

        public LabelSolverThread ( int [][] data, int x, int y, int sizeX, int sizeY, UnionFind labels) {
            this.data = data;
            this.labels = labels;
            this.x = x;
            this.y = y;
            this.sizeX = sizeX;
            this.sizeY = sizeY;
        }

        @Override
        public void run() {
            findLabels();
        }

        public void findLabels () {
            for (int i = y; i < y + sizeY ; i++) {
                for (int j = x; j < x +sizeX ; j++) {
                    if (data[i][j] != 0){
//                        System.out.print(" findLabels : " + data[i][j] + "parent : "  + labels.find(data[i][j] - 1) +    "after:  ");
                        data[i][j] = labels.find(data[i][j] - 1) + 1;
//                        System.out.println(data[i][j]);
                    }
                }
            }
        }
    }
/*
    class JoinerThread extends Thread {

        private int [][] data;
        private UnionFind labels;
        private int y;
        private int size;

        public JoinerThread ( int [][] data, int y, int size, UnionFind labels) {
            this.data = data;
            this.labels = labels;
            this.y = y;
            this.size = size;
        }

        @Override
        public void run() {
            for (int i = dataSize / nThreads; i < dataSize; i += dataSize/nThreads) {
                for (int j = 0; j < dataSize; j++) {
                    if (data[i][j] == 0)
                        continue;
                    else {
                        if (data[i - 1][j] > 0) {
                            data[i][j] = labels.find(data[i - 1][j] - 1) + 1;
                        } else if (data[i - 1][j + 1] > 0) {
                            if (data[i][j - 1] > 0)
                                data[i][j] = labels.union(data[i - 1][j + 1] - 1, data[i][j - 1] - 1) + 1;
                            else if (data[i - 1][j - 1] > 0)
                                data[i][j] = labels.union(data[i - 1][j - 1] - 1, data[i - 1][j + 1] - 1) + 1;
                            else data[i][j] = labels.find(data[i - 1][j + 1] - 1) + 1;
                        } else if (data[i][j - 1] > 0)
                            data[i][j] = labels.find(data[i][j - 1] - 1) + 1;
                        else if (data[i - 1][j - 1] > 0)
                            data[i][j] = labels.find(data[i - 1][j - 1] - 1) + 1;
                        else
                            data[i][j] = nextlabel++;

                }
            }

        }
    }
}
*/