package TagContentExtractor;

import java.util.*;
import java.util.regex.*;

public class Solution{
    public static void main(String[] args){
        String tagReg = "<(.+)>([^<]+)</\\1>";
        Pattern tagPattern = Pattern.compile(tagReg);
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        while(testCases>0){
            String line = in.nextLine();

            Matcher tagMatcher = tagPattern.matcher(line);
            if (tagMatcher.find()) {
                do {
                    System.out.println(tagMatcher.group(2));
                } while (tagMatcher.find());
            } else {
                System.out.println("None");
            }

            testCases--;
        }
    }
}
