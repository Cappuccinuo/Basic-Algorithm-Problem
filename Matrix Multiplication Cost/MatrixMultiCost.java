public class MatrixMultiCost {
    public static void main(String[] args) {
        int[] p = new int[]{5, 10, 3, 12, 5, 50, 6};
        int[][] m = findCost(p);
        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[0].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] findCost(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        printOptimalSolution(s, 1, 6);
        System.out.println();
        return m;
    }

    public static void printOptimalSolution(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        }
        else {
            System.out.print("(");
            printOptimalSolution(s, i, s[i][j]);
            printOptimalSolution(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }
}