package generation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QL_Bai8_1 {

    private static void printSolutions(List<int[]> solutions) {
        for(int i=0; i<solutions.size(); i++){
            System.out.printf("Solution%3d: ", i+1);
            for(int j: solutions.get(i)){
                System.out.printf("%3d", j+1);
            }
            System.out.println();
        }
    }

    private static boolean isSafe(int[] cols, int n, int row, int col) {
        for (int i = 1; i < col; i++) {
            if(cols[i-1] == row || cols[i-1]-i+n == row-col+n || cols[i-1]+i == row+col){
                return false;
            }
        }
        return true;
    }
    // Goi i la row => Xep row [0, n-1] vao mang cols voi col tu 1 -> n
    private static void solveNQueens(int[] cols, int n, int col, List<int[]> solutions) {
        if (col-1 == n) {
            solutions.add(cols.clone());
            return;
        }
        // Duyet het row
        for (int i = 0; i < n; i++) {
            if (isSafe(cols, n, i, col)) {
                cols[col-1] = i;
                solveNQueens(cols, n, col+1, solutions);
                cols[col-1] = -1;
            }
        }
    }

    public static void nQueens(int n) {
        int[] board = new int[n];
        Arrays.fill(board, -1);
        List<int[]> solutions = new ArrayList<>();
        solveNQueens(board, n, 1, solutions);
        printSolutions(solutions);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        nQueens(n);
    }
}
