package graph;

import java.util.Scanner;

public class Bai9_LienThongManh {

    static int[][][] input(){
        Scanner sc = new Scanner(System.in);
        int numsOfTest = sc.nextInt();
        int[][][] inputs = new int[numsOfTest][][];
        for (int t = 0; t < numsOfTest; t++) {
            int n = sc.nextInt();

            int[][] input = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    input[i][j] = sc.nextInt();
                }
            }
            inputs[t] = input;
        }
        return inputs;
    }

    static void dfs(int[][] a, int i, boolean[] visited){
        visited[i] = true;
        int n = a.length;
        for (int j=0; j<n; j++){
            if(!visited[j] && a[i][j]==1){
                dfs(a, j, visited);
            }
        }
    }

    static void solution(int[][] a){
        int n = a.length;
        boolean[] visited = new boolean[n];

        dfs(a, 0, visited);

        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                System.out.println("Not connected");
                return;
            }
        }
        System.out.println("Strongly Connected");
    }

    public static void main(String[] args) {
        int[][][] inputs = input();

        for (int[][] input : inputs) {
            solution(input);
        }
    }
}
