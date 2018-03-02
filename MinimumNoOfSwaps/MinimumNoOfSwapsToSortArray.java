// https://www.geeksforgeeks.org/minimum-number-swaps-required-sort-array/

// ans = Σ(i = 1) k(cycle_size – 1)   k is the number of cycles

// First make a list of pairs, that in each pair there is arr number and corresponding index
// Then sort the list based on the arr number, that is the key of pairs
// Next we traverse the arr, and see how many circles are there in the graph, if two numbers 
// have one cycle length 2, they just need 1 swap, if three numbers have one cycle length 3,
// they need 2 swaps

import java.util.*;
import javafx.util.Pair;

public class MinimumNoOfSwapsToSortArray{
    public static int minSwaps(int[] a) {
        int n = a.length;
        // Pair <num value, num index>
        ArrayList<Pair <Integer, Integer>> list = new ArrayList<Pair <Integer, Integer>>();
        for (int i = 0; i < n; i++) {
            list.add(new Pair<Integer, Integer>(a[i], i));
        }
        list.sort(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                return p1.getKey() - p2.getKey();
            }
        });
        //list.forEach((temp) -> System.out.println(temp.getValue()));
        Boolean[] visited = new Boolean[n];
        Arrays.fill(visited, false);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] || list.get(i).getValue() == i) {
                continue;
            }
            int cycle_size = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = list.get(j).getValue();
                cycle_size++;
            }
            ans += cycle_size - 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 4, 5, 3, 2};
        System.out.println(minSwaps(a));
    }
}