package generation;

import java.util.Scanner;

public class QL_Bai10 {
    static class Input{
        int n,m,y,x;
        boolean[][] map;
    }
    static class Output{
        int rs;
    }
    static Input input() {
        Input in = new Input();
        Scanner sc = new Scanner(System.in);
         in.n = sc.nextInt();
         in.m = sc.nextInt();
         in.y = sc.nextInt();
         in.x = sc.nextInt();
         in.map = new boolean[in.n][in.m];
        for(int i=0; i<in.n; i++){
            for(int j=0; j<in.m; j++){
                if(sc.nextInt()==1){
                    in.map[i][j]=true;
                }
            }
        }
        sc.close();
        return in;
    }

    static boolean isValidPosition(boolean[][] map, int y, int x){
        int n = map.length;
        int m = map[0].length;
        if(y<0 || y >= n || x < 0 || x >= m){
            return false;
        }
        return !map[y][x];
    }
    static void backtrack(boolean[][] map, int y, int x, Output output){
        output.rs+=1;
        map[y][x]=true;
        int[] goY= new int[]{-1, 0, 1, 0};
        int[] goX= new int[]{0, -1, 0, 1};
        for(int k=0; k<4; k++){
            int newY = y+goY[k];
            int newX = x+goX[k];
            if(isValidPosition(map,newY, newX)){
                backtrack(map, newY, newX, output);
            }
        }
    }

    static void solution(boolean[][] map, int y, int x){
        Output output = new Output();
        if(map[y][x]){
            output.rs = 0;
        }else{
            backtrack(map, y, x, output);
        }
        System.out.println(output.rs);
    }

    public static void main(String[] args) {
        Input in = input();
        solution(in.map, in.y-1, in.x-1);
    }
}
