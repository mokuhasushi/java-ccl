public class DataInitializer {

    public static int[][] createData(int size) {
        int[][] d = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                d[i][j] = Math.random() > 0.8 ? -1 : 0;
            }
        }
        return d;
    }

    public static int[][] createEdge(int[][] data, int size) {
        int[][] newData = new int[size + 2][size + 2];
        for (int i = 0; i < size + 2; i++) {
            for (int j = 0; j < size + 2; j++) {
                if (i == 0 || i == size + 1 || j == 0 || j == size + 1)
                    newData[i][j] = 0;
                else
                    newData[i][j] = data[i - 1][j - 1];
            }
        }
        return newData;
    }

    public static int[][][] divideDataInSquares(int[][] data, int size, int n) {
        int dataSize = size / n;
        int[][][] dividedData = new int[n * n][dataSize][dataSize];
        int[][][] newData = new int[n * n][dataSize][dataSize];
        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < dataSize; j++) {
                for (int k = 0; k < dataSize; k++) {
                    dividedData[i][j][k] = data[j + i / 3 * dataSize][k + i % n * dataSize];
                }
            }
            newData[i] = createEdge(dividedData[i], dataSize);
        }
        return newData;
    }
}
