package w10_algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class ReadFileThenEncode {

    public static void FxEncode(char[] carr, String[] sarr) {
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

    public static void PfEncode(char[] carr, int[] freq, String[] sarr) {
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
                }
                sarr[j] = sarr[j] + "1";
            }
        }
        
        // 앞부분 "null"과 '0' 이후에 등장하는 '1'을 삭제.
        for(int i=0; i<n; i++) {
            int zero = sarr[i].indexOf("0") + 1;
            sarr[i] = sarr[i].substring(4, zero);
        }
    }

    // 문자에 대응하는 huffman code를 트리 탐색을 통해 재귀적으로 저장하는 함수.
	// root에 해당하는 생성된 허프만 코드를 지속적으로 연결하며 가져가는 문자열 변수 s.
    public static void HfEncode(HuffmanNode root, String s, String[] sarr) {
	
		// 기본 경우 : left node와 right node가 모두 null일 때
		// 이후 leaf node를 재귀적으로 불러오며 함수 실행.
		if (root.left == null && root.right == null && Character.isLetter(root.c)) {
			// node에 해당하는 문자 c
			char ch = root.c;
			// 그에 대응하는 huffman code를 배열에 저장.
			sarr[ch - 'a'] = s;
			return;
		}

		// 생성된 트리에 대해 left node와 right node 탐색.
		HfEncode(root.left, s + "0", sarr);	// 좌측으로 가면 "0"을 저장.
		HfEncode(root.right, s + "1", sarr);	// 우측으로 가면 "1"을 저장.
	}

	public static void printCode(char[] carr, String[] sarr) {
        for(int i=0; i<carr.length; i++) {
            System.out.println(carr[i]+ " : " + sarr[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        
        // list that holds strings of a file
        List<String> listOfStrings = new ArrayList<String>();
       
        Scanner scan = new Scanner(new FileReader("file.txt")).useDelimiter(",\\s*");
        String str;
       
        // checking end of file
        while (scan.hasNext()) {
            str = scan.next();
           
            // adding each string to arraylist
            listOfStrings.add(str);
        }
       
        // convert any arraylist to array
        String[] array = listOfStrings.toArray(new String[0]);
       
        // print each string in array
        for (String eachString : array) {
            System.out.println(eachString);
        }

        // symbol, frequency, huffman code를 담을 배열 선언.
		char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i' ,'j', 'k'};
        int[] charfreq = {70, 10, 27, 31, 91, 18, 9, 14, 57, 5, 7};
		// 암호화할 문자의 종류 n.
        int n = charArray.length;
        String[] fixed = new String[n];
        String[] prefix = new String[n];
		String[] HuffmanCode = new String[n];

        /*
        // fixed 암호화
        FxEncode(charArray, fixed);
        // 대응하는 문자에 대한 암호화 결과값 출력하기
        printCode(charArray, fixed); 
          
        // prefix 암호화
        PfEncode(charArray, charfreq, prefix);
        // 대응하는 문자에 대한 암호화 결과값 출력하기
        printCode(charArray, prefix); 

         */
    }
}

// node class is the basic structure
// of each node present in the Huffman - tree.
class HuffmanNode {
	int data;
	char c;

	HuffmanNode left;
	HuffmanNode right;
}

// comparator class helps to compare the node
// on the basis of one of its attribute.
// Here we will be compared
// on the basis of data values of the nodes.
class MyComparator implements Comparator<HuffmanNode> {
	public int compare(HuffmanNode x, HuffmanNode y) {
		return x.data - y.data;
	}
}

