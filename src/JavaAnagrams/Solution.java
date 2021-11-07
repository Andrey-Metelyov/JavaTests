package JavaAnagrams;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Solution {

    static boolean isAnagram(String a, String b) {
        // Complete the function
        HashMap<Character, Integer> hmA = new HashMap<>();
        HashMap<Character, Integer> hmB = new HashMap<>();
        for (Character ch : a.toLowerCase(Locale.ROOT).toCharArray()) {
            hmA.put(ch, hmA.getOrDefault(ch, 0) + 1);
        }
        for (Character ch : b.toLowerCase(Locale.ROOT).toCharArray()) {
            hmB.put(ch, hmB.getOrDefault(ch, 0) + 1);
        }
        System.err.println(hmA);
        System.err.println(hmB);
        if (hmA.equals(hmB)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
}
