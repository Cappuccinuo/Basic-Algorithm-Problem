public class QuickSort {
    public static void quicksort(int[] arr, int begin, int end) {
        if (begin < end) {
            int p = partition(arr, begin, end);
            quicksort(arr, begin, p - 1);
            quicksort(arr, p + 1, end);
        }
    }

    public static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int index = begin - 1;
        for (int i = begin; i < end; i++) {
            if (arr[i] < pivot) {
                index++;
                swap(arr, index, i);
            }
        }
        swap(arr, index + 1, end);
        return index + 1;
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
        int[] arr = new int[]{1,4,-5,100,6,10,3,7,2};
        quicksort(arr, 0, arr.length - 1);
        showArray(arr);
    }
}