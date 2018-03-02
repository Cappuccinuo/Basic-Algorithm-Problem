import java.util.*;

public class RandomMedianStats {
    public static int medianStats(int[] arr, int start, int end, int k) {
        if (k > 0 && k <= end - start + 1) {
            int p = randomPartition(arr, start, end);
            if (p - start == k - 1) {
                return arr[p];
            }
            else if (p - start > k - 1) {
                return medianStats(arr, start, p - 1, k);
            }
            else {
                return medianStats(arr, p + 1, end, k - p + start - 1);
            }
        }
        return Integer.MAX_VALUE;
    }

    public static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int index = start - 1;
        for (int j = start; j < end; j++) {
            if (arr[j] < pivot) {
                index++;
                swap(arr, index, j);
            }
        }
        swap(arr, index + 1, end);
        return index + 1;
    }

    public static int randomPartition(int[] arr, int start, int end) {
        int n = end - start + 1;
        System.out.println(n);
        int pivot = (int)(Math.random() * n);
        System.out.println(pivot);
        swap(arr, start + pivot, end);
        return partition(arr, start, end);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void showArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 3, 2, 5};
        int k = 2;
        int p = medianStats(arr, 0, arr.length - 1, k);
        System.out.println(p);
        showArray(arr);
    }
}