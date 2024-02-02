package w04_algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
sample input(첫 번째 숫자는 노드의 개수, 두 번째 숫자는 간선의 개수 이다).
vertices:
5
Adjacency matrix:
0   1   -   1   5
9   0   3   2   -
-   -   0   4   -
-   -   2   0   3
3   -   -   -   0
src:
2
dst:
5
 */

public class FloydAlgorithm {
    static int num;			// 노드의 개수
	static int[][] dist;	// 각 노드간의 거리를 저장하는 2차원 배열
    static int[][] path;	// 노드를 이동하는 경로의 데이터를 저장하는 2차원 배열
	final static int INF = -1 ;	// INF 처리, 경로가 없는 노트의 표현

    // 플로이드 알고리즘을 이용하는 함수
	// 노드를 1개부터 n개까지 거쳐가는 경우를 모두 고려한다.
    public static void floyd(int n, int[][] dist, int[][] path) {
		// 중간 지점 i를 지나가는 경로에 대한 설정
        for(int i = 0; i < dist.length; i++) {
			// j에서 k로 가는 경로에 대한 탐색
			for(int j = 0; j < dist.length; j++) {
				for(int k = 0; k < dist.length; k++) {
					// j에서 k로 가는 경로 중 i를 거칠 수 있는 길이 없다면(INF라면)
					if(dist[j][i] == INF || dist[i][k] == INF)			
						continue; // 다음 i에 대하여 탐색한다.
					// j에서 k로 가는 경로 중 i를 지나가는 경로를 다음 정수에 저장한다. 						
					int temp = dist[j][i] + dist[i][k];
					// 이전에 j에서 k까지 갈 수 있는 경로가 없었거나(INF) || i를 지나가는 경로가 더 빠를 경우
					if(dist[j][k] == INF || temp < dist[j][k]) {
						dist[j][k] = temp;	// 최단 거리를 재설정한다.							
						path[j][k] = i+1;	// 최단 거리로 가는 경로를 저장한다.
					}
				}
			}
		}
    }

	/**
	 * 다음 입력값이 정수일 때까지 입력을 건너뛰는 함수.
	 * 입력받은 데이터 파일에서 "vertics :", "src :", "dst :" 등의 문자열은
	 * 데이터로서 가치가 없는 정보이기 때문에 입력을 건너뛴다.
	 * @param scan 입력을 받는 Scanner
	 */
	public static void skipString(Scanner scan) {
		while(!scan.hasNextInt())		// 다음 입력값이 정수일 때까지
			scan.next();				// 압력을 건너뛴다.
	}

	/**
	 * 재귀를 이용하여 출발지에서 도착지까지의 최소 거리로 가는 경로를 출력한다.
	 * @param start 출발 지점
	 * @param end	도착 지점
	 * @return		경로를 문자열로 출력
	 */
	public static String printPath(int start, int end) {
		// 현재 출력하고 있는 경로가 최단거리로 가는 경로일 때 path 추적을 종료한다.
		if(path[start-1][end-1] == 0) return "";
		// 출발 지점부터 현재 경로와 현재 경로부터 도착 지점까지의 경로중 최단 경로를 탐색한다.
		return printPath(start, path[start-1][end-1]) + path[start-1][end-1] + " " + printPath(path[start-1][end-1], end); 									
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
		// Floyd Algorithm을 실행한다.
		floyd(num, dist, path);
		// 실행 후엔 경로 정보가 path[][]에 저장되었을 것이다.

		skipString(scan);				// 문자열 입력을 건너뛴다. - src: 
		int start = scan.nextInt();		// 출발 지점을 입력받는다.
		skipString(scan);				// 문자열 입력을 건너뛴다. - dst:
		int end = scan.nextInt();		// 도착 지점을 입력받는다.

		// 테스트를 위한 거리 및 경로 배열 출력
		// printArray(dist);
		// printArray(path);

		// 출발지에서 도착지까지의 최소 거리를 출력한다.
		System.out.println("dist: " + dist[start-1][end-1]);
		// 출발지에서 도착지까지의 최소 거리로 가는 경로를 출력한다.
		System.out.println("path: " + printPath(start, end));
		
		// 스캐너 종료.
		scan.close();
	}
}