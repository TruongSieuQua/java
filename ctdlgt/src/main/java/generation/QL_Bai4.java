package generation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QL_Bai4 {

    public static void generatePermutations(int[] nums) {
        List<List<Integer>> allPermutations = new ArrayList<>();
        backtrack(nums, 0, allPermutations);

        Collections.sort(allPermutations, (a, b) -> {
            for (int i = 0; i < a.size(); i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i) - b.get(i);
                }
            }
            return 0;
        });

        for (List<Integer> perm : allPermutations) {
            for (int num : perm) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    private static void backtrack(int[] nums, int k, List<List<Integer>> allPermutations) {
        if (k == nums.length) {
            List<Integer> currentPermutation = new ArrayList<>();
            for (int i : nums) {
                currentPermutation.add(i);
            }
            allPermutations.add(currentPermutation);
            return;
        }

        for (int i = k; i < nums.length; i++) {
            swap(nums, k, i);
            backtrack(nums, k + 1, allPermutations);
            swap(nums, k, i);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        scanner.close();

        generatePermutations(nums);
    }
}