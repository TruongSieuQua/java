package divide;

import java.util.Scanner;

public class InterpolationSearch {

    public static class Input{
        int[] arr;
        int[] nums;
    }

    public static Input input(){
        Scanner sc = new Scanner(System.in);
        Input input = new Input();
        int n = sc.nextInt();
        input.arr = new int[n];
        for(int i=0; i<n; i++){
            input.arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        input.nums = new int[k];
        for (int i = 0; i < k; i++) {
            input.nums[i] = sc.nextInt();
        }
        return input;
    }

    public static void solution(Input in){
        int[] arr = in.arr, nums = in.nums;
        int n = arr.length, k=nums.length;
        StringBuilder sb = new StringBuilder(100);
        binarySort(arr, 0, arr.length-1);

        for (int i = 0; i < n; i++) {
            sb.append(String.format("%d ", arr[i]));
        }
        sb.append("\n");
        for (int num: nums){
            int idx = interpolationSearch(arr, num);
            sb.append(String.format("%d\n", idx));
        }
        System.out.print(sb.toString());
    }

    private static void swap(int[]arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j]=t;
    }

    // Tang dan
    private static void binarySort(int[] arr, int l, int r){
        if(l>=r) return;
        int pivot = arr[l];
        int i = l+1, j=r;

        while (true){
            while (i<j && arr[i] <= pivot){
                i++;
            }
            while (i<j && arr[j] > pivot){
                j--;
            }
            if(i<j && arr[i] > arr[j]){
                swap(arr, i, j);
            }else break;
        }
        i = pivot > arr[i]? i :i-1;
        swap(arr, l, i);
        binarySort(arr, l, i-1);
        binarySort(arr, i+1, r);
    }

    private static int interpolationSearch(int[] arr, int value){
        int high = arr.length - 1;
        int low = 0;

        while(value >= arr[low] && value <= arr[high] && low <= high){
            int probe = low + (high-low)*(value-arr[low])/(arr[high]-arr[low]);

            if(arr[probe]==value){
                return probe;
            }else if(arr[probe] < value){
                low = probe+1;
            }else{
                high = probe-1;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        Input in = input();
        solution(in);
    }
}
