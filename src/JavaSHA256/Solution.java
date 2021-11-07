package JavaSHA256;

import java.security.MessageDigest;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        String string = new Scanner(System.in).nextLine();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(string.getBytes());
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
