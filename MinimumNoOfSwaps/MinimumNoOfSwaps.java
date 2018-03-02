// https://www.geeksforgeeks.org/minimum-number-of-swaps-required-for-arranging-pairs-adjacent-to-each-other/

// Given a length 2n unsorted array, find the minimum number
// of swaps required to arrange the array to pairs (2n - 1, 2n) or (2n, 2n - 1), 
// such that 2n - 1 is adjacent with 2n
// Example: arr = [2,4,1,3]
// Result: arr = [2,1,4,3], just need to swap 4 and 1 

// Thought:
// 1.
// If first and second elements are pairs, then recur for remaining n - 1 pairs,
// and return the value returned by the recursive call
// 2.
// If first and second elements are not pairs, then there are two ways:
// a) swap second with pair of first and recur for n - 1 elements, suppose the 
// value is a
// b) swap back
// c) swap first with pair of second and recur for n - 1 elements, suppose the
// value is b
// d) swap back
// e) Return 1 + min(a, b)

import java.util.*;

public class MinimumNoOfSwaps {

    public static int findMinimumSwaps(int[] arr, Map<Integer, Integer> pairs) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            index.put(arr[i], i);
        }
        return findMinimumSwapsUtil(arr, pairs, index, 0);
    }

    public static int findMinimumSwapsUtil(int[] arr, Map<Integer, Integer> pairs, Map<Integer, Integer> index, int current) {
        if (current == arr.length) {
            return 0;
        }

        int v1 = arr[current];
        int v2 = arr[current + 1];
        int pv2 = pairs.get(v1);

        if (pv2 == v2) {
            return findMinimumSwapsUtil(arr, pairs, index, current + 2);
        }
        else {
            int idx1 = index.get(v1);
            int idx2 = index.get(v2);
            int idx3 = index.get(pairs.get(v1));
            int idx4 = index.get(pairs.get(v2));

            swap(index, arr, idx2, idx3);
            int a = findMinimumSwapsUtil(arr, pairs, index, current + 2);
            swap(index, arr, idx2, idx3);

            swap(index, arr, idx1, idx4);
            int b = findMinimumSwapsUtil(arr, pairs, index, current + 2);
            swap(index, arr, idx1, idx4);

            return 1 + Math.min(a, b);
        }
    }

    public static void swap(Map<Integer, Integer> index, int[] arr, int i1, int i2) {
        index.compute(arr[i1], (k, v) -> i2);
        index.compute(arr[i2], (k, v) -> i1);

        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }
    
    /* 
    public static void showSolution(List<swapPairs> list) {
        Collections.reverse(list);
        list.forEach((temp) -> System.out.println("swap " + temp.i + " and " + temp.j));
    }
    */

    public static void main(String[] args) {
        Map<Integer, Integer> pairs = new HashMap<>();
        pairs.put(1, 2);
        pairs.put(2, 1);
        pairs.put(3, 4);
        pairs.put(4, 3);
        pairs.put(5, 6);
        pairs.put(6, 5);
        int[] arr = new int[]{5, 4, 3, 2, 6, 1};
        System.out.println(findMinimumSwaps(arr, pairs));
    }
}