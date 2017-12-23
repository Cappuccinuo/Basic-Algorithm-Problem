// Reference : 
// https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/Knapsack01.java

import java.util.*;

public class Knapsack {
    public static void main(String[] args) {
        int[] val = new int[]{1,4,5,7};
        int[] wt = new int[]{1,3,4,5};
        int weight = 7;
        System.out.println(bottomUpDP(val, wt, 7));
        System.out.println(topDownRecursive(val, wt, 7));
    }

    public static int bottomUpDP(int[] val, int[] wt, int weight) {
        int[][] T = new int[val.length + 1][weight + 1];
        for (int i = 1; i <= val.length; i++) {
            for (int j = 0; j <= weight; j++) {
                if (j == 0) {
                    T[i][j] = 0;
                    continue;
                }
                if (j < wt[i - 1]) {
                    T[i][j] = T[i - 1][j];
                }
                else {
                    T[i][j] = Math.max(val[i - 1] + T[i - 1][j - wt[i - 1]], T[i - 1][j]);
                }
            }
        }
        return T[val.length][weight];
    }

    public static int topDownRecursive(int[] val, int[] wt, int weight) {
        Map<Index, Integer> map = new HashMap<>();
        return topDownRecursiveUtil(val, wt, weight, map, val.length, 0);
    }

    public static int topDownRecursiveUtil(int[] val, int[] wt, int remainingWeight, Map<Index, Integer> map, int totalItems, int currentItem) {
        if (currentItem >= totalItems || remainingWeight <= 0) {
            return 0;
        }

        Index key = new Index();
        key.remainingWeight = remainingWeight;
        key.remainingItem = totalItems - currentItem - 1;

        if (map.containsKey(key)) {
            return map.get(key);
        }
        int maxValue;
        if (remainingWeight < wt[currentItem]) {
            maxValue = topDownRecursiveUtil(val, wt, remainingWeight, map, totalItems, currentItem + 1);
        }
        else {
            maxValue = Math.max(val[currentItem] + topDownRecursiveUtil(val, wt, remainingWeight - wt[currentItem], map, totalItems, currentItem + 1),
                topDownRecursiveUtil(val, wt, remainingWeight, map, totalItems, currentItem + 1));
        }
        map.put(key, maxValue);
        return maxValue;
    }
}

class Index {
    int remainingWeight;
    int remainingItem;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Index index = (Index) o;
        if (remainingWeight != index.remainingWeight) {
            return false;
        }
        return remainingItem == index.remainingItem;
    }

    @Override
    public int hashCode() {
        int result = remainingWeight;
        result = 31 * result + remainingItem;
        return result;
    }
}