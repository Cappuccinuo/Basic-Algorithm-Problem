import java.util.*;

public class Sort {
    // Time complexity: θ(n^2)
    public static int[] bubbleSort(int[] unsorted) {
        int[] nums = Arrays.copyOf(unsorted, unsorted.length);
        boolean flag;
        for (int i = 0; i < nums.length - 1; i++) {
            flag = false;
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return nums;
    }

    // Removes one element from the input data, finds the location it
    // belongs within the sorted list, and inserts it there. 
    // Time complexity: O(n^2)
    public static int[] insertionSort(int[] unsorted) {
        int[] nums = Arrays.copyOf(unsorted, unsorted.length);
        int i, j, key;
        for (i = 1; i < nums.length; i++) {
            key = nums[i];
            j = i - 1;
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
        return nums;
    }

    // finding the smallest/largest element in the unsorted sublist,
    // swapping with the leftmost unsorted element, and moving the 
    // sublist boundaries one element to the right
    // Time complexity: O(n^2)
    public static int[] selectionSort(int[] unsorted) {
        int[] nums = Arrays.copyOf(unsorted, unsorted.length);
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            index = i;
            for (int j = index + 1; j < nums.length; j++) {
                if (nums[j] < nums[index]) {
                    index = j;
                }
            }
            if (i != index) {
                swap(nums, i, index);
            }
        }
        return nums;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void displayArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] unsorted = new int[]{5, 20, 1, 8, 49, 9};
        displayArray(bubbleSort(unsorted));
        displayArray(insertionSort(unsorted));
        displayArray(selectionSort(unsorted));
    }
}