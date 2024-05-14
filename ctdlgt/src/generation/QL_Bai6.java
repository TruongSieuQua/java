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
    /*
     *  0 0 0 0 0 0
            + + + +
        6 - 4 + idx + 1
        i: 0 -> 0 1 2 < 3
        i: 1 -> 0 1 2 3 < 4
     */
    static void backtrack(int[] chars, int[] permutation, int idx, boolean[] used){
        if(idx == permutation.length){
            printPermutation(permutation);
            return;
        }else{
            for(int j=idx; j <= chars.length-permutation.length+idx; j++){
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
        for (int j : permutation) {
            System.out.printf("  %d", j);
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
