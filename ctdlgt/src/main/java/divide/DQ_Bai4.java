package divide;

import java.util.Scanner;

public class DQ_Bai4 {
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
    static void output(int[] rs){
        for (int r : rs) {
            System.out.printf("%d ", r);
        }
    }
    static void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    static void divide(int[] arr, int l, int r){
        if (l >= r) return;
        int pivot = arr[r];
        int i = l, j = r - 1;
        while (true) {
            while (i < j && arr[i] <= pivot) {
                i++;
            }
            while (i < j && arr[j] > pivot) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
            } else break;
        }
        if (arr[i] > pivot) {
            swap(arr, i, r);
        }
        divide(arr, l, i - 1);
        divide(arr, i + 1, r);
    }
    public static void main(String[] args) {
        int[] arr = input();
        divide(arr, 0, arr.length-1);
        output(arr);
    }
}
