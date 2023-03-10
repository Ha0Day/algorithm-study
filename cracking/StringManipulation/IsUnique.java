package StringManipulation;

import java.util.HashSet;
import java.util.Set;

public class IsUnique {
    public static void main(String[] args) {
        String s="adbcfww";
        System.out.println(checkDuplicates(s));
        System.out.println(checkDuplicates2(s));
        System.out.println(checkDuplicates3(s));
    }

    public static boolean checkDuplicates(String s){
        char[] charArr=s.toCharArray();

        for(int i=0;i<charArr.length;i++){
            int tmp=charArr[i];
            for(int j=i+1;j<charArr.length;j++){
                if(tmp==charArr[j]) return true;
            }
        }
        return false;
    }

    public static boolean checkDuplicates2(String s){
        Set<Character> set= new HashSet<Character>();

        for(int i=0;i<s.length();i++){
            set.add(s.charAt(i));
        }

        if(set.size()==s.length()) return false;
         return true;
    }

    public static boolean checkDuplicates3(String s){

        for(int i=0;i<s.length();i++){
            int tmp=s.charAt(i);
            for(int j=i+1;j<s.length();j++){
                if(tmp==s.charAt(j)) return true;
            }
        }
        return false;
    }

}