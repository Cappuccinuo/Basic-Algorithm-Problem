import java.util.*;

public class Coupon {
    public static void main(String[] args) {
        int[] array = new int[]{4, 3, 5, 6, 3, 5, 4};
        System.out.println(Solution(array)); 
    }

    private static int Solution(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                result = Math.min(result, i - map.get(array[i]) + 1);
            }
            map.put(array[i], i);
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}