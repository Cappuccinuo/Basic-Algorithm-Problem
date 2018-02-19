import java.util.*;

public class insertionSort {
    public static int[] sort(int[] A) {
        int[] nums = Arrays.copyOf(A, A.length);
        int key;
        for (int i = 1; i < A.length; i++) {
            key = nums[i];
            int j = i - 1;
            while (j >= 0 && A[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
        return nums;
    }

    public static void showArray(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] A = new int[]{3,5,4,2,1};
        int[] result = sort(A);
        showArray(result);
    }
}