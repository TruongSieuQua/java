package divide;

import java.util.Scanner;

public class DQ_Bai5 {

    static class Input{
        int[] arr;
        int[] nums;
    }

    static void input(Input in){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        in.arr = new int[n];
        for(int i=0; i<n; i++){
            in.arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        in.nums = new int[k];
        for(int i=0; i<k; i++){
            in.nums[i] = sc.nextInt();
        }
    }

    static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void binarySort(int[] arr, int l, int r){
        if(l>=r) return;

        int i = l+1, j=r;
        Integer pivot = arr[l];

        while (true){
            while(i<j && arr[i] >= pivot){
                i++;
            }

            while (i<j && arr[j] < pivot){
                j--;
            }

            if(i < j){
                swap(arr, i, j);
                i++;
                j--;
            }else break;
        }
        // i=j va arr[i] > pivot
        if(pivot < arr[i]){
            swap(arr, i, l);
            binarySort(arr, l, i-1);
            binarySort(arr, i+1, r);
        }else{ // i=j va arr[i] < pivot
            swap(arr, i-1, l);
            binarySort(arr, l, i-2);
            binarySort(arr, i, r);
        }

    }

    static int binarySearch(int[] arr, int value, int l, int r){
        if(l>r) return -1;
        int m = (l+r)/2;
        if(value == arr[m]){
            return m;
        }else if(value > arr[m]){
            return binarySearch(arr, value, l, m-1);
        }else{
            return binarySearch(arr, value, m+1, r);
        }
    }

    static void sort(int[] arr){
        binarySort(arr, 0, arr.length-1);
    }

    static void solution(Input in, StringBuilder sb){
        sort(in.arr);
        int n = in.arr.length;
        int k = in.nums.length;
        int[] arr = in.arr;
        int[] nums = in.nums;

        for (int i=0; i<n; i++){
            sb.append(String.format("%d ", arr[i]));
        }
        sb.append("\n");

        for (int num : nums){
            int idx = binarySearch(arr, num, 0, n-1);
            sb.append(String.format("%d\n", idx));
        }
        System.out.print(sb.toString());
    }
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Input in = new Input();
        input(in);
        solution(in, sb);
    }
}
