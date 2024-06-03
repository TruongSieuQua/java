package divide;

import java.util.Arrays;
import java.util.Scanner;

public class DQ_Bai1 {
    static int[] input(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        return arr;
    }

    static void output(int[] rs){
        int n = rs.length;
        for(int i=0; i<n-1; i++){
            System.out.printf("%d ", rs[i]);
        }
        System.out.println(rs[n-1]);
    }

    static void merge(int[] arr, int[] temp, int l, int m, int r){
        int i=l, j=m, idx=l;
        int n1 = m-l+1;
        int n2 = r-m;

        while(i <= n1 && j<=r){
            if(arr[i] <= arr[j]){
                temp[idx] = arr[i];
                i++;
            }else{
                temp[idx] = arr[j];
                j++;
            }
            idx++;
        }
//        System.arraycopy(arr, idx, temp, idx, n1-i);
//        System.arraycopy(arr, idx, temp);
    }

    static void divide(){

    }

    static void solution(){

    }

    public static void main(String[] args) {
        int[] arr = input();

    }
}
