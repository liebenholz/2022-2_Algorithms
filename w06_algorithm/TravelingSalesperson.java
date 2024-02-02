package w06_algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TravelingSalesperson {
    static int num;			// 노드의 개수
	static int[][] dist;	// 각 노드간의 거리를 저장하는 2차원 배열
    static int[][] path;	// 노드를 이동하는 경로의 데이터를 저장하는 2차원 배열
	final static int INF = -1 ;	// INF 처리, 경로가 없는 노트의 표현

    public static void TSP(int n, int[][] dist, int[][] path) {
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


    public static void main(String[] args) throws FileNotFoundException {
		// input힐 데이터들이 들어있는 파일을 불러오기 : 파일의 경로를 복사하여 pathname에 집어넣는다.
		File data = new File("input2.txt");
		// 스캐너 선언.
		Scanner scan = new Scanner(data);
		// 문자열 입력을 건너뛴다. - vertices: 
		skipString(scan);
		
		int num = scan.nextInt();	// vertics 숫자 입력 받는다.
		dist = new int[num][num];	// vertics 크기의 거리 정보를 저장하는 배열을 선언한다.
		path = new int[num][num];	// vertics 크기의 경로 정보를 저장하는 배열을 선언한다.

		// 문자열 입력을 건너뛴다. - Adjacency matrix:
		skipString(scan);

        // 플로이드 초기 거리 테이블 초기화
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				// 입력받는 데이터를 한 줄씩 문자열로 입력을 받는다.
				// 정수와 문자가 혼재하는 입력값이기 때문에 우선 스캐너로 읽어들인 후 후처리를 진행한다.
				String next = scan.next();
				// 입력받은 문자열대로 배열에 체례대로 입력을 한다. 
				// 만약 "-" 를 입력 받는다면, 해당 인덱스의 데이터를 INF로 설정한다. 
				if(next.equals("-")) {
					dist[i][j] = INF;
				} else { dist[i][j] = Integer.valueOf(next);
				}
				path[i][j] = 0;			// path 배열의 초기화
			}
		}
		/// Traveling salesperson problem을 실행한다.
		TSP(num, dist, path);
		// 실행 후엔 경로 정보가 path[][]에 저장되었을 것이다.

		
    }

}
