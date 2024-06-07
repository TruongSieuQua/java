package divide;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DQ_Bai7 {
    static List<String> str;
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
    static void output(List<String> str){
        int size = str.size();
        for (int i=0; i<size-2; i++){
            System.out.println(str.get(i));
        }
        System.out.print(str.get(size-2));
        System.out.print(str.get(size-1));
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
        str.add(String.format("Partitioning: left=%d, right=%d", l, r));
        str.add(String.format("%s ", joinIntArray(arr, l, r, " ")));
        while (true) {
            while (i < j && arr[i] >= pivot) {
                i++;
            }
            while (i < j && arr[j] < pivot) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
                str.add(String.format("%s ", joinIntArray(arr, l, r, " ")));
            } else break;
        }
        if (arr[i] < pivot) {
            swap(arr, i, r);
        }else{
            i+=1;
        }
        str.add(String.format("%s \n", joinIntArray(arr, l, r, " ")));
        divide(arr, l, i-1);
        divide(arr, i + 1, r);
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
        int n = arr.length;
        str=new ArrayList<>();

        str.add(String.format("%s \n", joinIntArray(arr, 0, n-1, " ")));
        divide(arr, 0, n-1);
        str.add(String.format("%s ", joinIntArray(arr, 0, n-1, " ")));

        output(str);
    }
}

