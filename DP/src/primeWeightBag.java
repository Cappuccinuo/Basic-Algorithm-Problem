import java.util.*;

public class primeWeightBag {
    public static long numberOfPrimeWeight(int[] bag) {
        int maxWeight = 500000;
        //int maxWeight = 10;
        Arrays.sort(bag);
        int len = bag.length;
        long count = 0;
        boolean[] isPrime = new boolean[maxWeight + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        long[][] dp = new long[len + 1][maxWeight + 1];

        // Sieve of Eratosthenes
        for (int factor = 2; factor * factor <= maxWeight; factor++) {
            if (isPrime[factor]) {
                for (int j = factor * factor; j < maxWeight; j += factor) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 0; i <= len; i++) {
            dp[i][0] = 1;
        }
        int consecutive = 1;
        for (int i = 1; i <= len; i++) {
            if (i > 1 && bag[i - 1] == bag[i - 2]) {
                consecutive++;
            }
            else {
                consecutive = 1;
            }
            for (int j = 1; j <= maxWeight; j++) {
                if (j - bag[i - 1] >= 0) {
                    if (consecutive > 1) {
                        if (j < consecutive * bag[i - 1]) {
                            dp[i][j] = dp[i - 1][j];
                        }
                        else if (j == consecutive * bag[i - 1]) {
                            dp[i][j] = dp[i - 1][j] + 1;
                        }
                        else {
                            dp[i][j] = dp[i - 1][j - bag[i - 1]];
                        }
                    }
                    else {
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - bag[i - 1]];
                    }
                }
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        for (int i = 0; i <= 12; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 1; i <= 7; i++) {
            for (int j = 0; j <= 12; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
            

        for (int i = 0; i <= maxWeight; i++) {
            if (isPrime[i]) {
                count += dp[len][i];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] bag1 = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};// 4
        int[] bag2 = new int[]{4, 6, 8, 10, 12, 14};// 0
        int[] bag3 = new int[]{1, 2, 4, 8, 16, 32, 64, 128};// 54
        int[] bag4 = new int[]{9947, 9948, 9949, 9950, 9951, 9952, 9953, 9954, 9955, 9956, 9957, 9958, 9959,
        9960, 9961, 9962, 9963, 9964, 9965, 9966, 9967, 9968, 9969, 9970, 9971, 9972, 9973,
        9974, 9975, 9976, 9977, 9978, 9979, 9980, 9981, 9982, 9983, 9984, 9985, 9986, 9987,
        9988, 9989, 9990, 9991, 9992, 9993, 9994, 9995, 9996};// 91378169764810
        int[] bag7 = new int[]{10000, 9999, 9998, 9997, 9996, 9995, 9994, 9993, 9992, 9991, 9990, 9989,
        9988, 9987, 9986, 9985, 9984, 9983, 9982, 9981, 9980, 9979, 9978, 9977, 9976, 9975,
        9974, 9973, 9972, 9971, 9970, 9969, 9968, 9967, 9966, 9965, 9964, 9963, 9962, 9961,
        9960, 9959, 9958, 9957, 9956, 9955, 9954, 9953, 9952, 9951};// 89655114688016
        int[] bag9 = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 10000, 10000, 10000, 10000, 10000,
        10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000,
        10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000,
        10000, 10000, 10000, 10000, 10000, 10000, 10000};// 6555

        // Not pass
        int[] bag5 = new int[]{9947, 9947, 9947, 9947, 9947, 9947, 9947, 9947, 9947, 9947, 9947, 9947, 9947,
                9947, 9947, 9947, 9947, 9947, 9947, 9947, 9947, 9947, 9947, 9947, 9947, 9934, 9934,
                9934, 9934, 9934, 9934, 9934, 9934, 9934, 9934, 9934, 9934, 9934, 9934, 9934, 9934,
                9934, 9934, 9934, 9934, 9934, 9934, 9934, 9934, 9934};// 54
        int[] bag6 = new int[]{1, 1, 2, 2, 4, 4, 8, 8, 16, 16, 32, 32, 64, 64, 128, 128, 256, 256, 512, 512, 1024,
        1024, 2048, 2048, 4096, 1, 1, 2, 2, 4, 4, 8, 8, 16, 16, 32, 32, 64, 64, 128, 128, 256, 256,
        512, 512, 1024, 1024, 2048, 2048, 4096};// 62648678
        int[] bag8 = new int[]{9990, 9991, 9992, 9993, 9994, 9995, 9996, 9997, 9998, 9999, 9990, 9991, 9992,
        9993, 9994, 9995, 9996, 9997, 9998, 9999, 9990, 9991, 9992, 9993, 9994, 9995, 9996,
        9997, 9998, 9999, 9990, 9991, 9992, 9993, 9994, 9995, 9996, 9997, 9998, 9999, 9990,
        9991, 9992, 9993, 9994, 9995, 9996, 9997, 9998, 9999};// 4814999

        int[] bag10 = new int[]{1, 1, 1, 1, 1, 2, 3};

        System.out.println(numberOfPrimeWeight(bag5));
    }
}