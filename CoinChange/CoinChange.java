public class CoinChange {
    public static void main(String[] args) {
        int[] D = new int[]{50, 25, 10, 1};
        int[] limit = new int[]{2, 1, 2, 5};
        int N = 30;
        makeChangeLimitedCoins(D, limit, 30);
    }

    public static void makeChangeLimitedCoins(int[] D, int[] limit, int N) {
        int[] dp = new int[N + 1];
        dp[0] = 0;
        int len = D.length;
        int[][] track = new int[N + 1][len];
        for (int i = 0; i < len; i++) {
            // Track is used to record how many coins left at current value and current coin
            track[0][i] = limit[i];
        }
        for (int j = 1; j <= N; j++) {
            dp[j] = Integer.MAX_VALUE;
            for (int k = 0; k < len; k++) {
                if (j >= D[k] && (dp[j - D[k]] < Integer.MAX_VALUE) && (track[j - D[k]][k] > 0)) {
                    if (dp[j] > 1 + dp[j - D[k]]) {
                        dp[j] = 1 + dp[j - D[k]];
                        track[j][k] = track[j - D[k]][k] - 1;
                    }
                    else {
                        track[j][k] = track[j - D[k]][k];
                    }
                }
                else if(j < D[k]) {
                    track[j][k] = track[0][k];
                }
            }
        }
        printResult(dp, N);
        //printSolution(track, D, N, limit, len, dp);
    }

    public static void printResult(int[] dp, int N) {
        System.out.println("Printing Coin value and Change:");
        for (int i = 1; i <= N; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                System.out.println(i + ":" + "Not possible");
            }
            else {
                System.out.println(i + ":" + dp[i]);
            }
        }
        System.out.println();
    }
    /*
    public static void printSolution(int[][] track, int[] D, int N, int[] limit, int len, int[] dp) {
        for (int i = 1; i <= N; i++) {
            System.out.println("When the amount is: " + i);
            if (dp[i] == Integer.MAX_VALUE) {
                System.out.println("Impossible.");
                System.out.println();
                continue;
            }
            for (int j = 0; j < len; j++) {
                int num = track[i][j];
                System.out.println("Use " + num + " of coins worth " + D[j]);
            }
            System.out.println();
        }
    }
    */
}