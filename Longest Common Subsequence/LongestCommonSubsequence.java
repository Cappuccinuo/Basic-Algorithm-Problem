public class LongestCommonSubsequence {
    static String[][] s;
    public static void main(String[] args) {
        String s1 = "ABCDGHLQR";
        String s2 = "AEDPHR";
        System.out.println(lcsDynamicProgramming(s1, s2));
        System.out.println(lcsRecursion(s1, s2, 0, 0));
        printSolution(s1, s1.length(), s2.length());
    }

    public static void printSolution(String str, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (s[i][j].equals("SW")) {
            printSolution(str, i - 1, j - 1);
            System.out.print(str.charAt(i - 1));
        }
        else if (s[i][j].equals("N")) {
            printSolution(str, i - 1, j);
        }
        else {
            printSolution(str, i, j - 1);
        }
    }

    public static int lcsDynamicProgramming(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int len1 = s1.length();
        int len2 = s2.length();
        s = new String[s1.length() + 1][s2.length() + 1];
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    s[i][j] = "SW";
                }
                else if (dp[i - 1][j] < dp[i][j - 1]) {
                    dp[i][j] = dp[i][j - 1];
                    s[i][j] = "W";
                }
                else {
                    dp[i][j] = dp[i - 1][j];
                    s[i][j] = "N";
                }
            }
        }
        return dp[len1][len2];
    }

    public static int lcsRecursion(String s1, String s2, int len1, int len2) {
        int l1 = s1.length();
        int l2 = s2.length();
        if (len1 == l1 || len2 == l2) {
            return 0;
        }

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        if (c1[len1] == c2[len2]) {
            return 1 + lcsRecursion(s1, s2, len1 + 1, len2 + 1);
        }
        else {
            return Math.max(lcsRecursion(s1, s2, len1 + 1, len2), lcsRecursion(s1, s2, len1, len2 + 1));
        }
    }
}