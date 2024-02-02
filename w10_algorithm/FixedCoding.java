package w10_algorithm;

public class FixedCoding {

    public static void encode(char[] carr, String[] sarr) {
        int n = carr.length;
        // char의 순서대로 binary로 encoding 진행
        for(int i=n-1; i>=0; i--) {
            sarr[i] = Integer.toBinaryString(i);
            // 최대 크기의 값만큼 앞에 '0'을 채워주기
            while(sarr[i].length()!=sarr[n-1].length()) {
                sarr[i] = "0" + sarr[i];
            }
        }
    }

    // 문자에 대응하는 fixed code의 값 출력
    public static void printCode(char[] carr, String[] sarr) {
        for(int i=0; i<carr.length; i++) {
            System.out.println(carr[i]+ " : " + sarr[i]);
        }
    }
 
    public static void main(String[] args) {
    
         // symbol, frequency, fixed code를 담을 배열 선언.
         char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i' ,'j', 'k'};
         int[] charfreq = {10, 10, 27, 31, 91, 18, 9, 14, 57, 5, 7};
         String[] fixed = new String[charArray.length];
        
         // fixed 암호화
        encode(charArray, fixed);
        // 대응하는 문자에 대한 암호화 결과값 출력하기
        printCode(charArray, fixed);
    }
}
