package generation;

import java.util.Scanner;

public class QL_Bai5 {

    static int[] input(){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        sc.close();
        return new int[]{n, k};
    }

    static void backtrack(int[] chars, int[] permutation, int idx, boolean[] used){
        if(idx == permutation.length){
            printPermutation(permutation);
        }else{
            for(int j=0; j < chars.length; j++){
                if(used[j]){
                    continue;
                }
                used[j]=true;
                permutation[idx] = chars[j];

                backtrack(chars, permutation, idx+1, used);

                permutation[idx] = '\0';
                used[j]=false;
            }
        }
    }

    static void solution(int[] chars, int n){
        boolean[] used = new boolean[chars.length];
        int[] permutation = new int[n];
        backtrack(chars, permutation, 0, used);
    }

    static void printPermutation(int[] permutation){
        int n = permutation.length;
        for (int i = 0; i < n; i++) {
            System.out.printf("  %d", permutation[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] ins = input();
        int[] chars = new int[ins[0]];
        for (int i=0; i < ins[0]; i++){
            chars[i] = 1+i;
        }
        solution(chars, ins[1]);
    }
}
