public class CountingSort {
    public static int[] countingSort(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        int[] count = new int[10];
        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        for (int i = 0; i < n; i++) {
            result[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        return result;
    }

    public static void showArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,6,7,2,1,3,8,7,6,5,5,9};
        int[] result = countingSort(arr);
        showArray(result);
    }
}