public class MatrixMultiplicationCost {
    public static void main(String[] args) {
        int[] arr = new int[]{5,10,3,12,5,50,6};
        System.out.println(findCost(arr));
        System.out.println(findCostRecursion(arr, 1, arr.length - 1));
    }

    public static int findCostRecursion(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        int min = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int count = findCostRecursion(arr, i, k) + 
                        findCostRecursion(arr, k + 1, j) +
                        arr[i - 1] * arr[k] * arr[j];
            if (count < min) {
                min = count;
            }
        }
        return min;
    }

    public static int findCost(int[] arr) {
        int[][] dp = new int[arr.length][arr.length];
        int value = 0;

        for (int i = 1; i < arr.length; i++) {
            dp[i][i] = 0;
        }

        for (int len = 2; len < arr.length; len++) {
            for (int i = 0; i < arr.length - len; i++) {
                int j = len + i;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    value = dp[i][k] + dp[k][j] + arr[i] * arr[k] * arr[j];
                    if (value < dp[i][j]) {
                        dp[i][j] = value;
                    }
                }
            }
        }
        return dp[0][arr.length - 1];
    }
}