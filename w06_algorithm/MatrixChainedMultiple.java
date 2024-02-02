package w06_algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MatrixChainedMultiple {
    
    static int num;			// 노드의 개수
	static int[] diag;	    // 각 노드간의 거리를 저장하는 2차원 배열
    static int[][] path;	// 노드를 이동하는 경로의 데이터를 저장하는 2차원 배열
	final static int INF = -1 ;	// INF 처리, 경로가 없는 노트의 표현

    public static void minmulti(int n, int[] diag, int[][] path) {
		// I am sorry professor...

    }

    /**
	 * 다음 입력값이 정수일 때까지 입력을 건너뛰는 함수.
	 * 입력받은 데이터 파일에서 "vertics :" 등의 문자열은
	 * 데이터로서 가치가 없는 정보이기 때문에 입력을 건너뛴다.
	 * @param scan 입력을 받는 Scanner
	 */
	public static void skipString(Scanner scan) {
		while(!scan.hasNextInt())		// 다음 입력값이 정수일 때까지
			scan.next();				// 압력을 건너뛴다.
	}

    /**
	 * 코드 동작 확인용 : 
	 * 입력받은 배열을 출력한다.
	 * @param arr 출력할 배열
	 */
	public static void printArray(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j : arr[i])
				System.out.print(j == INF ? "X " : j + " ");	// INF값을 X 기호로 출력한다.
			System.out.println();
		} System.out.println();
	}

    public static void main(String[] args) throws FileNotFoundException {
		// input힐 데이터들이 들어있는 파일을 불러오기 : 파일의 경로를 복사하여 pathname에 집어넣는다.
		File data = new File("input1.txt");
		// 스캐너 선언.
		Scanner scan = new Scanner(data);
		
        while(!scan.hasNext()) {
            // 문자열 입력을 건너뛴다. - matrix: 
		    skipString(scan);
            int row = scan.nextInt();	// matrix 숫자 입력 받아 row로 설정.
            int col = scan.nextInt();	// matrix 숫자 입력 받아 col로 설정.

            path = new int[row][col];

			// path 배열 입력
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    path[i][j] = scan.nextInt();
                }
            }
        }
    }
}
