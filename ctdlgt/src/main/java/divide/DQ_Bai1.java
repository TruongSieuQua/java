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
    static void merge(int[] arr, int l, int r, int[] temp){
        int mid = (l+r)/2;
        int i=l, j=mid+1, idx=l;

        while(i <= mid && j<=r){
            if(arr[i] <= arr[j]){
                temp[idx] = arr[i];
                i++;
            }else{
                temp[idx] = arr[j];
                j++;
            }
            idx++;
        }
        if(i <= mid){
            System.arraycopy(arr, i, temp, idx, mid-i+1);
        }else if(j <= r){
            System.arraycopy(arr, j, temp, idx, r-j+1);
        }
        System.arraycopy(temp, l, arr, l, r-l+1);
    }
    static void divide(int[] arr, int l, int r, int[] temp){
        if(l >= r){
            return;
        }
        int m = (l+r)/2;
        divide(arr, l, m, temp);
        divide(arr, m+1, r, temp);
        merge(arr, l, r, temp);
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
