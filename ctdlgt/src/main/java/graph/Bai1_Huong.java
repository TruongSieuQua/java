package graph;

import java.util.Scanner;

public class Bai1_Huong {
    static int[][][] input(){
        Scanner sc = new Scanner(System.in);
        int numsOfTest = sc.nextInt();
        int[][][] inputs = new int[numsOfTest][][];
        for (int t = 0; t < numsOfTest; t++) {
            int n = sc.nextInt();

            int[][] input = new int[n+1][n+1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    input[i][j] = sc.nextInt();
                }
            }
            inputs[t] = input;
        }
        return inputs;
    }

    public static void solution(int[][] a){
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if(a[i][j]!=a[j][i]){
                    System.out.println("Directed graph");
                    return;
                }
            }
        }
        System.out.println("Undirected graph");
    }

    public static void main(String[] args) {
        int[][][] inputs = input();

        for (int[][] input : inputs) {
            solution(input);
        }
    }
}
