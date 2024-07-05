package divide;

import java.util.Scanner;
public class DQ_Bai10 {
    static int[] input(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        sc.close();
        return arr;
    }
    static void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    static void divide(int[] arr, int l, int r, StringBuilder sb){
        if (l >= r) return;
        int pivot = arr[r];
        int i = l, j = r - 1;
        sb.append(String.format("Partitioning: left=%d, right=%d\n", l, r));
        joinIntArray(arr, l, r, " ", sb);
        while (true) {
            while (arr[i]>pivot) { // Giam dan
                i++;
            }
            while (arr[j]<=pivot) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
                joinIntArray(arr, l, r, " ", sb);
                i++;
                j--;
            } else break;
        }
        swap(arr, i, r);
        joinIntArray(arr, l, r, " ", sb);
        divide(arr, l, i-1, sb);
        divide(arr, i+1, r, sb);
    }
    public static void joinIntArray(int[] array, int start, int end, String delimiter, StringBuilder sb) {
        for (int k = start; k <= end; k++) {
            sb.append(array[k]);
            if (k < end) {
                sb.append(delimiter);
            }
        }
        sb.append(" \n");
    }
    public static void main(String[] args) {
        int[] arr = input();
        int n = arr.length;
        StringBuilder sb = new StringBuilder();

        joinIntArray(arr, 0, n-1, " ", sb);
        sb.append("\n");
        divide(arr, 0, n-1, sb);
        joinIntArray(arr, 0, n-1, " ", sb);
        System.out.print(sb);
    }
}