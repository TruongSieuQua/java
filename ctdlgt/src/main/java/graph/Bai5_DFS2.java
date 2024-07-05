package graph;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Bai5_DFS2 {

    static class Input{
        int n;
        int s;
        int d;

        int[][] a;

        public Input(int n, int s, int d, int[][] a){
            this.n = n;
            this.s = s;
            this.d = d;
            this.a = a;
        }
    }

    static Input[] input(){
        Scanner sc = new Scanner(System.in);

        int numOfTests = sc.nextInt();
        Input[] inputs = new Input[numOfTests];
        for (int t = 0; t < numOfTests; t++) {
            int n = sc.nextInt();
            int s = sc.nextInt();
            int d = sc.nextInt();

            int[][] a = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = sc.nextInt();
                }
            }
            inputs[t] = new Input(n, s, d, a);
        }
        return inputs;
    }

    static void solution(int n, int s, int d, int[][] a){
        boolean[] visited = new boolean[n];
        int[] reversePath = new int[n];
        Arrays.fill(reversePath, -1);

        dfs(a, s, d, visited, reversePath);

        System.out.printf("Path from %d to %d:", s, d);

        if(!visited[d]){
            System.out.println(" No path exits");
            return;
        }else{
            Stack<Integer> stack = new Stack<>();
            int i = d;
            while (s != i){
                stack.push(i);
                i = reversePath[i];
            }
            stack.push(s);

            boolean isFirst = true;
            while (!stack.isEmpty()) {
                if(!isFirst){
                    System.out.printf(" --> %d", stack.pop());
                }else{
                    System.out.printf(" %d", stack.pop());
                    isFirst = false;
                }
            }
        }
        System.out.println();
    }

    static void dfs(int[][] a, int i, int d, boolean[] visited, int[] path){
        visited[i] = true;
        int n = a.length;
        for (int j = 0; j < n; j++) {
            if(!visited[j] && a[i][j]==1){
                path[j] = i;
                dfs(a, j, d, visited, path);
            }
        }
    }

    public static void main(String[] args) {
        Input[] inputs = input();
        for (Input i : inputs){
            solution(i.n, i.s, i.d, i.a);
        }
    }
}
