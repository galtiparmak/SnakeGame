package Test;

public class Recursion {
    
    public static void main(String[] args) {
        int test = 134431;
        System.out.println(palindromic(test));
    }

    // Given an integer, write a function that returns true if the given number is palindrome, else false.
    // For example, 12321 is palindrome, but 1451 is not palindrome. 

    public static boolean palindromic(int num) {
        
        String str = String.valueOf(num);
        boolean result = false;
        if (str.length() < 2 || str.equals("")) {
            result = true;
            return result;
        }
        else {
            if (str.charAt(0) == str.charAt(str.length() - 1)) {
                String newStr = str.substring(1, str.length() - 1);
                if (newStr.length() < 2) {
                    return true;
                }
                else {
                    int newNum = Integer.parseInt(newStr);
                    return palindromic(newNum);
                } 
            }
            else {
                return result;
            }
        }
    }

}
