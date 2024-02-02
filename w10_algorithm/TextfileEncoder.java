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

public class TextfileEncoder {
    
    // Fixed Coding을 진행하는 함수
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

    // Prefix Coding을 진행하는 함수
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
		HfEncode(root.left, s + "0", sarr);	    // 좌측으로 가면 "0"을 저장.
		HfEncode(root.right, s + "1", sarr);	// 우측으로 가면 "1"을 저장.
	}

	public static void printCode(char[] carr, String[] sarr) {
        for(int i=0; i<carr.length; i++) {
            System.out.println(carr[i]+ " : " + sarr[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        
        // 입력받은 문자열을 저장한 ArrayList
        List<String> listOfStrings = new ArrayList<String>();
       
        // 데이터 불러오기
        BufferedReader bf = new BufferedReader
        (new FileReader("/Users/gokyulueau/Documents/Computer Science/univ-lecture/knu22-algorithm/w10_algorithm/inputTwo.txt"));
       
        // 문자열로 한 문장 읽어들이기
        String line = bf.readLine();
       
        // EOF까지 listOfStrings에 문자열 삽입하기
        while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }
        // bufferreader 닫기
        bf.close();
       
        // arraylist 안의 내용을 배열 형태의 array로 옮기기.
        String[] array = listOfStrings.toArray(new String[0]);

        // 문자를 각각의 암호화 방식대로 변환한 후 저장할 ArrayList 선언.
        // Fixed, Prefix, Huffman 방식대로 각각 생성.
        List<String> FixedEncoded = new ArrayList<String>();
        List<String> PrefixEncoded = new ArrayList<String>();
        List<String> HuffmanEncoded = new ArrayList<String>();
       
        // symbol, frequency, huffman code를 담을 배열 선언.
		char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i' ,'j', 'k'};
        int[] charfreq = {70, 10, 27, 31, 91, 18, 9, 14, 57, 5, 7};
		// 암호화할 문자의 종류 n.
        int n = charArray.length;
        String[] fixed = new String[n];
        String[] prefix = new String[n];
		String[] HuffmanCode = new String[n];

        // fixed 암호화
        FxEncode(charArray, fixed);
        
        // 입력받은 file의 문자들을 fixed encoding 진행  
        for (String str : array) {
            int sn = str.length();
            String code = "";
            for(int i=0; i<sn; i++) {
                for(int j=0; j<n; j++) {
                    if(charArray[j]==str.charAt(i)) {
                        code = code + fixed[j];
                    }
                }  
            }
            FixedEncoded.add(code);
        }

        // Fixed Encode 결과 출력.
        System.out.println("Fixed Encoded :");
        for (String str : FixedEncoded) {
            System.out.println(str);
        } System.out.println();
          

        // prefix 암호화
        PfEncode(charArray, charfreq, prefix);

        // 입력받은 file의 문자들을 fixed encoding 진행  
        for (String str : array) {
            int sn = str.length();
            String code = "";
            for(int i=0; i<sn; i++) {
                for(int j=0; j<n; j++) {
                    if(charArray[j]==str.charAt(i)) {
                        code = code + prefix[j];
                    }
                }  
            }
            PrefixEncoded.add(code);
        }

        // Prefix Encode 결과 출력.
        System.out.println("Prefix Encoded :");
        for (String str : PrefixEncoded) {
            System.out.println(str);
        } System.out.println();


        // priority queue인 q 생성.
		PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new MyComparator());

		for (int i = 0; i < n; i++) {
			// Huffman node 생성 후 priority queue에 삽입.
			HuffmanNode hn = new HuffmanNode();

			hn.c = charArray[i];
			hn.data = charfreq[i];

			hn.left = null;
			hn.right = null;

			// Huffman Node를 queue에 삽입한다.
			q.add(hn);
		}

		// root 생성.
		HuffmanNode root = null;

		// queue 안에 node들이 모두 빠져나와 트리를 완성할 때까지 반복문 진행.
		while (q.size() > 1) {
			// 가장 작은 값 peak and poll.
			HuffmanNode x = q.peek();	q.poll();

			// 두번째로 작은 값 peak and poll.
			HuffmanNode y = q.peek();	q.poll();

			// 새로운 huffman node f.
			HuffmanNode f = new HuffmanNode();

			// f 노드의 데이터에 에 x와 y 두 개의 데이터의 합을 저장.
			f.data = x.data + y.data;	f.c = '-';	// 임의의 문자

			f.left = x; 	// 가장 작은 값 x를 left child로 지정.
			f.right = y; 	// 두번째로 작은 값 y를 right child로 지정.
			root = f;	 	// f를 root node로 지정.
			q.add(f);		// f를 priority-queue에 삽입한다.
		}

	    // 트리 탐색을 진행하면서 huffman 암호화.
    	HfEncode(root, "", HuffmanCode);

        // 입력받은 file의 문자들을 fixed encoding 진행  
        for (String str : array) {
            int sn = str.length();
                    String code = "";
            for(int i=0; i<sn; i++) {
                for(int j=0; j<n; j++) {
                    if(charArray[j]==str.charAt(i)) {
                        code = code + HuffmanCode[j];
                    }
                }  
            }
            HuffmanEncoded.add(code);
        }

        // Prefix Encode 결과 출력.
        System.out.println("Huffman Encoded :");
        for (String str : HuffmanEncoded) {
            System.out.println(str);
        } 
    }
}

// Huffman Tree에서 선언되는 기본 노트 Huffman Node.
class HuffmanNode {
	int data;
	char c;

	HuffmanNode left;
	HuffmanNode right;
}

// 두 node의 차를 비교하여 반환하는 comparator.
class MyComparator implements Comparator<HuffmanNode> {
	public int compare(HuffmanNode x, HuffmanNode y) {
		return x.data - y.data;
	}
}
