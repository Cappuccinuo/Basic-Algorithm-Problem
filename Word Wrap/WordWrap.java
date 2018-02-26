public class WordWrap {
    public int printSolution(int[] p, int n) {
        int k;
        if (p[n] == 1) {
            k = 1;
        }
        else {
            k = printSolution(p, p[n] - 1) + 1;   
        }
        System.out.println("Line number" + " " + k + ":" + 
                "From Word No." + " " + p[n] + " " + "to" + " " + n);
        return k;
    }

    public void solveWordWrap(int[] l, int n, int M) {
        int[][] extraSpace = new int[n + 1][n + 1];
        int[][] lc = new int[n + 1][n + 1];
        int[] c = new int[n + 1];
        int[] p = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            extraSpace[i][i] = M - l[i - 1];
            for (int j = i + 1; j <= n; j++) {
                extraSpace[i][j] = extraSpace[i][j - 1] - l[j - 1] - 1;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (extraSpace[i][j] < 0) {
                    lc[i][j] = Integer.MAX_VALUE;
                } 
                else if (j == n && extraSpace[i][j] < 0) {
                    lc[i][j] = 0;
                }
                else {
                    lc[i][j] = extraSpace[i][j] * extraSpace[i][j];
                }
            }
        }

        c[0] = 0;
        for (int j = 1; j <= n; j++) {
            c[j] = Integer.MAX_VALUE;
            for (int i = 1; i <= j; i++) {
                if (c[i - 1] != Integer.MAX_VALUE && lc[i][j] != Integer.MAX_VALUE && (c[i - 1] + lc[i][j] < c[j])) {
                    c[j] = c[i - 1] + lc[i][j];
                    p[j] = i;
                }
            }
        }
        printSolution(p, n);
    }

    public static void main(String[] args) {
        WordWrap w = new WordWrap();
        int[] l = new int[]{3, 2, 2, 5};
        int n = l.length;
        int M = 6;
        w.solveWordWrap(l, n, M);
    }
}