public class Card {
    public static void main(String[] args) {
        int total_money = 5;
        int total_damage = 4;
        int[] costs = new int[]{4, 5, 1};
        int[] damages = new int[]{1, 2, 3};
        System.out.println(BackPack(total_money, total_damage, costs, damages));
    }

    private static boolean BackPack(int total_money, int total_damage, int[] costs, int[] damages) {
        int len = costs.length;
        int[][] dp = new int[len + 1][total_money + 1];

        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= total_money; j++) {
                dp[i][j] = dp[i - 1][j];
                if (costs[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - costs[i - 1]] + damages[i - 1]);
                }
            }
        }

        return dp[len][total_money] >= total_damage;
    }
}