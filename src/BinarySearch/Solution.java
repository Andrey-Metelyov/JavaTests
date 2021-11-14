package BinarySearch;

public class Solution {
    public int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    private int binarySearch(int[] nums, int target, int start, int end) {
        int index = start + (end - start) / 2;
        System.err.printf("%d, %d, %d, %d%n", target, start, end, index);
        if (nums[index] == target) return index;
        if (end == start) return -1;
        if (target > nums[index]) return binarySearch(nums, target, index + 1, end);
        return binarySearch(nums, target, 0, index);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.search(new int[] {-1,0,3,5,9,12}, 9));
        System.out.println(solution.search(new int[] {-1,0,3,5,9,12}, 2));
        System.out.println(solution.search(new int[] {-1,0,3,5,9,12}, 13));
    }
}
