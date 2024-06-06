package divide;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DQ_Bai3 {
    static ArrayList<String> str;
    static int[] input(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        return arr;
    }
    static void output(List<String> str){
        int n = str.size();
        for(int i=0; i<n-1; i++){
            System.out.println(str.get(i));
        }
        System.out.print(str.get(n-1));
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
        str.add(String.format("Divide: %d --> %d and %d --> %d", l, m, m+1, r));
        str.add(String.format("%s :: %s ", joinIntArray(arr, l, m, " "), joinIntArray(arr, m+1, r, " ")));
        divide(arr, l, m, temp);
        divide(arr, m+1, r, temp);

        str.add(String.format("Merge: %d --> %d and %d --> %d", l, m, m+1, r));
        merge(arr, l, r, temp);
        str.add(String.format("%s %s \n", joinIntArray(arr, l, m, " "), joinIntArray(arr, m+1, r, " ")));
    }
    static void solution(int[] arr){
        int n = arr.length;
        int[] temp = new int[n];
        str.add(String.format(" %s", joinIntArray(arr, 0, n-1, " ")));
        divide(arr, 0, n-1, temp);
    }

    public static String joinIntArray(int[] array, int start, int end, String delimiter) {
        if (array == null || array.length == 0 || start > end || start < 0 || end >= array.length) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int k = start; k <= end; k++) {
            sb.append(array[k]);
            if (k < end) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        int[] arr = input();
        str = new ArrayList<>();
        solution(arr);
        output(str);
    }
}
