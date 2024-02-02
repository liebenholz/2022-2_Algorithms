package w10_algorithm;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.io.FileNotFoundException;

public class HuffmanCoding {
	// 문자에 대응하는 huffman code를 트리 탐색을 통해 재귀적으로 저장하는 함수.
	// root에 해당하는 생성된 허프만 코드를 지속적으로 연결하며 가져가는 문자열 변수 s.
	public static void encode(HuffmanNode root, String s, String[] sarr) {

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
		encode(root.left, s + "0", sarr);	// 좌측으로 가면 "0"을 저장.
		encode(root.right, s + "1", sarr);	// 우측으로 가면 "1"을 저장.
	}

	// 문자에 대응하는 huffman code의 값 출력
	public static void printCode(char[] carr, String[] sarr) {
        for(int i=0; i<carr.length; i++) {
            System.out.println(carr[i]+ " : " + sarr[i]);
        }
    }

	public static void main(String[] args) throws FileNotFoundException {

		// symbol, frequency, huffman code를 담을 배열 선언.
		char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i' ,'j', 'k'};
        int[] charfreq = {70, 10, 27, 31, 91, 18, 9, 14, 57, 5, 7};
		// 암호화할 문자의 종류 n.
        int n = charArray.length;
		String[] HuffmanCode = new String[n];

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

		// 트리 탐색을 진행하면서 huffman coding 진행.
		encode(root, "", HuffmanCode);
		// 대응하는 문자에 대한 암호화 결과값 출력하기
		printCode(charArray, HuffmanCode);
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
