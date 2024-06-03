package generation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class S_Bai2 {

    static List<Integer> input(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        List<Integer> permutation = new ArrayList<>();
        for (int i = n; i >= 1; i--) {
            permutation.add(i);
        }
        scanner.close();
        return permutation;
    }

    private static void output(List<List<Integer>> permutations){
        for (List<Integer> perm : permutations) {
            for (int num : perm) {
                System.out.print("  " + num);
            }
            System.out.println();
        }
    }

    private static void generation(List<Integer> permutation, List<List<Integer>> permutations){
        do {
            permutations.add(new ArrayList<>(permutation));
        } while (nextPermutation(permutation));
    }

    // Hàm sinh hoán vị tiếp theo
    private static boolean nextPermutation(List<Integer> perm) {
        int n = perm.size();
        int i = n - 2;

        // Tim vi tri i sao cho perm[i] > per[i+1]
        while (i >= 0 && perm.get(i) < perm.get(i + 1)) {
            i--;
        }

        // Neu khong con hoan vi
        if (i < 0) {
            return false;
        }

        int j = n - 1;

        // Tim vi tri perm[j] lon nhat < perm[i]
        while (perm.get(j) > perm.get(i)) {
            j--;
        }

        // Doi cho perm[j] va perm[i]
        Collections.swap(perm, i, j);

        // Sap xep bang cach dao nguoc tu doan i+1 den cuoi
        int left = i + 1;
        int right = n - 1;
        while (left < right) {
            Collections.swap(perm, left, right);
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        List<Integer> permutation = input();
        List<List<Integer>> permutations = new ArrayList<>();

        generation(permutation, permutations);

        output(permutations);
    }
}
