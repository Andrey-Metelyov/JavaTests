package IntArrayToStringWithRanges;

import java.util.Arrays;

public class Solution {
    String compress(int[] array) {
        if (array.length == 0) return "";
        if (array.length == 1) return String.valueOf(array[0]);

        Arrays.sort(array);

        StringBuilder result = new StringBuilder();
        int cur = 0;
        while (cur < array.length) {
            int start = cur;
            int last = start;
            while (last < array.length - 1 && array[last + 1] - array[last] == 1) {
                last++;
            }
            if (start != last) {
                result.append(array[start])
                        .append("-")
                        .append(array[last])
                        .append(",");
                cur = last;
            } else {
                result.append(array[start]).append(",");
            }
            cur++;
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    private void test(int[] array) {
        System.out.println(Arrays.toString(array) + " - '" + compress(array) + "'");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.test(new int[] {1,4,5,7});
        solution.test(new int[] {1,4,5,6});
        solution.test(new int[] {1,2,4});
        solution.test(new int[] {1,2,4,5});
        solution.test(new int[] {5,2,4,8});
        solution.test(new int[] {2,3,6,8,9});
        solution.test(new int[] {2,3,6,8,9,11,12,13});
        solution.test(new int[] {1,2,3,4});
        solution.test(new int[] {1,1,2,3,4});
        solution.test(new int[] {});
    }
}
