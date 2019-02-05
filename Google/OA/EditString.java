public class EditString {
    public static void main(String[] args) {
        String S = "ba";
        String T = "ab";
        System.out.println(canEdit(S, T));
    }

    private static boolean canEdit(String S, String T) {
        int m = S.length();
        int n = T.length();
        if (m > n) {
            return false;
        }
        if (S.equals(T)) {
            return true;
        }
        boolean[][] dp = new boolean[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = i; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = true;
                }

                else if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }

                else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[m][n];
    }
}