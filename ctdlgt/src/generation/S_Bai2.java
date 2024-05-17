package generation;

import java.util.Scanner;

public class S_Bai2 {

    static int input(){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        sc.close();
        return n;
    }

    static void printCombination(int[] rs){
        for(int i = 0; i < rs.length; i++) {
            System.out.printf("%3d", rs[i]);
        }
        System.out.println();
    }

    static void swap(int[] rs, int i, int j){
        rs[i] = rs[i] + rs[j];
        rs[j] = rs[i]-rs[j];
        rs[i] = rs[i]-rs[j];
    }
    private static void reverse(int[] array, int start, int end) {
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }
    static void solution(int n, int k){
        int[] rs = new int[k];
        for (int i = 0; i < k; i++) {
            rs[i] = k-i;
        }
        while(true){
            printCombination(rs);

            // 5 4 3 2 1 => 5 4 3 1 2 => 5 4 2 3 1 => 5 4 2 1 3 => 5 4 1 3 2 => 5 4 1 2 3 => 5 3 4 2 1
            // Tim vt[i] > vt[i+1] => vt[j tu cuoi] < vt[i] => swap(i, j) => reverse(i+1, n-1)

            int i,j;
            for(i=n-2; i>=0; i--){
                if(rs[i] > rs[i+1]){
                    break;
                }
            }
            if(i==-1) break;

            for(j=n-1; j>=0; j--){
                if(rs[j] < rs[i]){
                    break;
                }
            }
            swap(rs, i, j);
            reverse(rs, i+1, n-1);
        }
    }

    public static void main(String[] args) {
        int n = input();
        solution(n, n);
    }
}
