package JavaStringTokens;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine().trim();
        // Write your code here.
        if (s.isEmpty()) {
            System.out.println(0);
        } else {
            String[] res = s.split("[ !,?._'@]+");
            System.out.println(res.length);
            System.out.println(String.join("\n", res));
        }
        scan.close();
    }
}
