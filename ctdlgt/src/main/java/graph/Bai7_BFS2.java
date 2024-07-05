package graph;

import java.util.*;

public class Bai7_BFS2 {

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

    static void bfs(int[][] a, int i, boolean[] visited, int[] path){
        Queue<Integer> queue = new LinkedList<>();
        int n = a.length;

        queue.add(i);
        visited[i] = true;

        while (!queue.isEmpty()){
            Integer j = queue.remove();

            for (int k = 0; k < n; k++) {
                if(!visited[k] && a[j][k]==1){
                    queue.add(k);
                    path[k] = j;
                    visited[k] = true;
                }
            }
        }
    }

    static void solution(int n, int s, int d, int[][] a){
        boolean[] visited = new boolean[n];
        int[] reversePath = new int[n];
        Arrays.fill(reversePath, -1);

        bfs(a, s, visited, reversePath);

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

    public static void main(String[] args) {
        Input[] inputs = input();
        for(Input i: inputs){
            solution(i.n, i.s, i.d, i.a);
        }
    }
}
