package w10_algorithm;

import java.util.Arrays;

public class PrefixCoding {

    public static void encode(char[] carr, int[] freq, String[] sarr) {
        // 배열의 길이
        int n = carr.length;
        // Frequency를 sorting한 배열
        int[] sorted = new int[n];

        // Frequency를 sorted에 저장 후 sorted를 sorting.
        for(int i=0; i<n; i++) {
            sorted[i] = freq[i];
        } Arrays.sort(sorted);

        // 각 Sorted 순서에 일치하는 문자에 대하여 뒤에 0을 삽입, 아니면 1을 삽입 
        for(int i=n-1; i>=0; i--) {
            for(int j=0; j<n; j++) {
                if(sorted[i]==freq[j]) {
                    sarr[j] = sarr[j] + "0";
                } sarr[j] = sarr[j] + "1";
            }
        }
        
        // 앞부분 "null"과 '0' 이후에 등장하는 '1'을 삭제.
        for(int i=0; i<n; i++) {
            int zero = sarr[i].indexOf("0") + 1;
            sarr[i] = sarr[i].substring(4, zero);
        }
    }

    // 문자에 대응하는 prefix code의 값 출력
    public static void printCode(char[] carr, String[] sarr) {
        for(int i=0; i<carr.length; i++) {
            System.out.println(carr[i]+ " : " + sarr[i]);
        }
    }

    public static void main(String[] args) {

         // symbol, frequency, prefix code를 담을 배열 선언.
         char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i' ,'j', 'k'};
         int[] charfreq = {70, 10, 27, 31, 91, 18, 9, 14, 57, 5, 7};
         String[] prefix = new String[charArray.length];

        // prefix 암호화
        encode(charArray, charfreq, prefix);
        // 대응하는 문자에 대한 암호화 결과값 출력하기
        printCode(charArray, prefix);
    }
}

