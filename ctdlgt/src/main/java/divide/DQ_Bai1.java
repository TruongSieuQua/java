package divide;

import java.util.Scanner;

public class DQ_Bai1 {
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
        int n = rs.length;
        for(int i=0; i<n-1; i++){
            System.out.printf("%d ", rs[i]);
        }
        System.out.println(rs[n-1]);
    }
    static void merge(int[] arr, int l, int r, int mid, int[] temp){
        int i = l, j = mid + 1, k = l;

        while (i <= mid && j <= r) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= r) {
            temp[k++] = arr[j++];
        }

        for (i = l; i <= r; i++) {
            arr[i] = temp[i];
        }
    }
    static void divide(int[] arr, int l, int r, int[] temp){
        if (l < r) {
            int mid = (l + r) / 2;
            divide(arr, l, mid, temp);
            divide(arr, mid + 1, r, temp);
            merge(arr, l, r, mid, temp);
        }
    }
    static int[] solution(int[] arr){
        int n = arr.length;
        int[] temp = new int[n];
        divide(arr, 0, n-1, temp);
        return arr;
    }
    public static void main(String[] args) {
        int[] arr = input();
        int[] kq = solution(arr);
        output(kq);
    }
}
