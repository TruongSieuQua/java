package generation;

import java.util.Scanner;

public class QL_Bai6 {

    static int[] input(){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        sc.close();
        return new int[]{n, k};
    }

    // 1 2 3 4 5 6
    // 0 1 2  // len = 4
    static void backtrack(int[] arr, int n, int k, int p, int start, int[] rs){
        if(p == k){
            printPermutation(rs);
            return;
        }
        for(int i=start; i <= n-(k-p); i++){
                rs[p] = arr[i];
                backtrack(arr, n, k, p+1, i+1, rs);
        }
    }

    static void solution(int n, int k){
        int[] arr = new int[n];
        int[] rs = new int[k];
        for(int i=0; i < n; i++){
            arr[i] = i+1;
        }
       backtrack(arr, n, k, 0, 0, rs);
    }

    static void printPermutation(int[] perm){
        int len = perm.length;
        for(int i=0; i < len-1; i++){
            System.out.print(perm[i] + "  ");
        }
        System.out.println(perm[len-1]);
    }

    public static void main(String[] args) {
        int[] ins = input();
        solution(ins[0], ins[1]);
    }
}
