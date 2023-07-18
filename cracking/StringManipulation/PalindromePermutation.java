package StringManipulation;

import java.util.Arrays;

//1.4 Palindrome Permutation
//주어진 문자열이 회문의 순열인지 아닌지 확인
//예) 입력 : tact coa / 출력 : True (순열: "taco cat", "atco cta"등)
public class PalindromePermutation {

    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("ab abaafc  c "));
        System.out.println(sol_isPermutationOfPalindrome("ab abaafc c  "));
        System.out.println(sol_isPermutationOfPalindrome2("ab abaafc  c "));
        System.out.println(sol_isPermutationOfPalindrome3("ab abaafc  c "));
    }

    //Draft - O(NlogN)
    // Arrays.sort(chArr) 때문
    private static boolean isPalindromePermutation(String s) {

        char[] chArr = s.toLowerCase().toCharArray();
        char[] chArr2 = new char[chArr.length];

        Arrays.sort(chArr);

        int j = 0;
        for (int i = 0; i < chArr.length; i++) {
            if (chArr[i] == ' ') {
                continue;
            }
            chArr2[j++] = chArr[i];
        }

        boolean checked = false;

        int i = 0;
        while (i < chArr2.length - 1) {
            if (chArr2[i] != chArr2[i + 1]) {
                if (checked) {
                    return false;
                }
                checked = true;
                i = i + 1;
                continue;
            }
            i = i + 2;
        }
        return true;
    }

    //Solution1 - O(N)
    private static boolean sol_isPermutationOfPalindrome(String phrase) {
        int[] table = buildCharFrequencyTable(phrase);
        return checkMaxOneOdd(table);
    }

    private static boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for (int count : table) {
            if (count % 2 == 1) {
                if (foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }
        return true;
    }

    private static int getCharNumber(char c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if (a <= val && val <= z) {
            return val - a;
        }
        return -1;
    }

    private static int[] buildCharFrequencyTable(String phrase) {
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
            }
        }
        return table;
    }

    //Solution2 - O(N)
    private static boolean sol_isPermutationOfPalindrome2(String phrase) {
        int countOdd = 0;
        int[] table = new int['z' - 'a' + 1];
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
                if (table[x] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }
        return countOdd <= 1;
    }

    //Solution3 - O(N)
    //비트 벡터 사용
    //어떤 숫자에서 1을 뺀 뒤 AND 연산을 수행했을 때 그 결과가 0이라면
    //해당 숫자는 정확히 한 비트만 1로 세팅되어 있었던 것
    private static boolean sol_isPermutationOfPalindrome3(String phrase) {
        int bitVector = createBitVector(phrase);
        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }

    //문자열에 대한 비트 벡터를 만든다. 값이 i인 문자가 등장하면 i번째 비트값을 바꾼다.
    private static int createBitVector(String phrase) {
        int bitVector = 0;
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            bitVector = toggle(bitVector, x);
        }
        return bitVector;
    }

    //정수의 i번째 비트값을 바꾼다.
    private static int toggle(int bitVector, int index) {
        if (index < 0) return bitVector;
        int mask = 1 << index;
        if ((bitVector & mask) == 0) {
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }
        return bitVector;
    }

    //정확히 비트 한 개만 1로 세팅됐는지 확인하기 위해 주어진 정수값에서 1을 뺀 뒤 원래 값과 AND 연산을 한다.
    private static boolean checkExactlyOneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }

}
