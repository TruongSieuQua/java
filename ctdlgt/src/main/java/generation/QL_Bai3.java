package generation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QL_Bai3 {

    public static void generatePermutations(int n) {
        // Khởi tạo mảng ban đầu là các số từ n đến 1 theo thứ tự giảm dần
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            permutation[i] = n - i;
        }

        // Danh sách để lưu trữ tất cả hoán vị
        List<List<Integer>> allPermutations = new ArrayList<>();
        backtrack(permutation, 0, n, allPermutations);

        // Sắp xếp tất cả các hoán vị theo thứ tự giảm dần
        Collections.sort(allPermutations, (a, b) -> {
            for (int i = 0; i < a.size(); i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return b.get(i) - a.get(i);
                }
            }
            return 0;
        });

        // In tất cả các hoán vị
        for (List<Integer> perm : allPermutations) {
            for (int num : perm) {
                System.out.print("  " + num);
            }
            System.out.println();
        }
    }

    private static void backtrack(int[] permutation, int k, int n, List<List<Integer>> allPermutations) {
        // Nếu đã tạo được hoán vị đầy đủ (k == n), thêm hoán vị này vào danh sách
        if (k == n) {
            List<Integer> currentPermutation = new ArrayList<>();
            for (int i : permutation) {
                currentPermutation.add(i);
            }
            allPermutations.add(currentPermutation);
            return;
        }

        // Lặp qua các vị trí từ k đến n-1 để tìm các giá trị cho hoán vị tại vị trí k
        for (int i = k; i < n; i++) {
            // Đổi chỗ giá trị tại vị trí k với giá trị tại vị trí i
            swap(permutation, k, i);
            // Gọi đệ quy để tiếp tục tạo hoán vị cho các vị trí tiếp theo
            backtrack(permutation, k + 1, n, allPermutations);
            // Đổi chỗ lại để quay lui
            swap(permutation, k, i);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // Đọc n từ đầu vào
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        scanner.close();

        int n = 6;
        // Gọi hàm để tạo hoán vị
        generatePermutations(n);
        long endTime = System.currentTimeMillis();
        System.out.println("Time executed: " + (endTime - startTime));
    }
}