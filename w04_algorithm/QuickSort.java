package w04_algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// 정렬할 배열 4, 10, 2, 3, 45, 100, 39, 99
// 정렬할 배열 15, 22, 13, 27, 12, 10, 20, 25 

public class QuickSort {

    // 분할(divide) 동작을 한 횟수 저장
    static int divide = 0;
    // 정복(conquer) 동작을 한 횟수 저장
    static int conquer = 0;

    /**
     * 읽어들이는 파일에서 배열의 크기를 탐색해 반환하는 함수. 
     */ 
    private static int getNum(File data) throws FileNotFoundException {
        // 스캐너 선언.
		Scanner inputData = new Scanner(data);
        // 배열의 크기(숫자의 개수)를 저장하는 정수값
		int count = 0;
        // 다음 입력으로 정수를 가지고 있다면
		while(inputData.hasNextInt()) {
			count++;      // 배열의 크기를 하나 증가시킨다
            inputData.nextInt();  // 다음 정수를 입력받는다.
		}
		inputData.close();  // 데이터 읽어들이기를 종료한다.
		return count;       // 배열의 크기를 반환한다.
	}
 
    /**
     * 배열의 i번째 값과 j번째 값을 바꾸는 함수.
     * @param arr 배열
     * @param i   바꿀 데이터의 첫번째 인덱스
     * @param j   바꿀 데이터의 두번째 인덱스
     */
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];  // i번째 값을 임시로 저장한다.
        arr[i] = arr[j];    // j번째 값을 j번째에 저장한다.
        arr[j] = temp;      // 임시저장했던 값을 i번째에 저장한다. 
    }
 
    /**
     *  분할할 위치(피봇)의 인덱스를 반환하는 함수.
        배열의 마지막 원소를 피벗으로 사용하고 피벗을 정렬된 배열에서 올바른 위치에 배치하며
        피벗보다 작은 요소를 피벗의 왼쪽에 배치하고, 피벗보다 큰 요소를 피벗의 오른쪽에 배치힌다.
     * @param arr  정렬할 배열
     * @param low  배열의 첫번째 인덱스
     * @param high 배열의 마지막 인덱스
     * @return     피봇의 인덱스
     */
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];     // 가장 마지막 원소를 피봇으로 지정한다.
        int i = (low - 1);         // 탐색범위의 가장 작은 인덱스 값의 -1을 저장한다.
 
        // low 부터 high 까지 배열의 값을 피봇과 반복 비교하는 loop
        for (int j = low; j < high ; j++) {
            if (arr[j] < pivot) {  // 현재 값이 피봇보다 작으면
                i++;               // 작은 수 인덱스 값을 하나 증가시킨다.
                swap(arr, i, j);   // i번째 값과 j번째 값을 swap한다.
            }
        }
        // 탐색 범위에서 가장 큰 값을 범위 내 가장 오른쪽 방으로 이동하고 swap을 한다.
        swap(arr, i + 1, high);
        // 분할이 끝난 후 피봇의 인덱스 값을 반환한다.
        return (i + 1);
    }

    /**
     * 퀵 정렬을 진행하는 함수.
     * @param arr  정렬할 배열
     * @param low  시작 인덱스
     * @param high 마지막 인덱스
     */
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 분할을 통해 배열의 피봇을 탐색한다.
            int pi = partition(arr, low, high);
            divide++;   // divide 동작 진행
            // 피봇의 왼쪽에 있는 배열을 재귀하며 정렬한다.
            quickSort(arr, low, pi - 1);
            conquer++;  // conquer 동작 진행
            // 피봇의 오른쪽에 있는 배열을 재귀하며 정렬한다.
            quickSort(arr, pi + 1, high);
            conquer++;  // conquer 동작 진행
        }
    }
 
    /**
     * // 배열을 출력하는 함수.
     * @param arr  출력할 배열
     * @param size 배열의 크기
     */
    static void printArray(int[] arr, int size) {
        // 배열의 첫 인덱스부터 끝 인덱스까지
        for (int i = 0; i < size; i++)
            // 차례차례 출력한다.
            System.out.print(arr[i] + " ");
        System.out.println();    // 줄바꿈
    }
 
    public static void main(String[] args) throws FileNotFoundException {
        // int[] arr = { 15, 22, 13, 27, 12, 10, 20, 25 };

        // 정렬할 숫자들로 이루어진 파일을 불러오기 : 파일의 경로를 복사하여 pathname에 집어넣는다.
        File data = new File("input1.txt");
        // 스캐너 선언
		Scanner scan = new Scanner(data);
        // 배열의 크기를 불러와 데이터를 저장할 배열을 선언한다
		int[] arr = new int[getNum(data)];
        // 파일 내 정수값을 읽어들여 차례대로 배열에 저장한다.
		for(int i = 0; i < arr.length; i++) {
			arr[i] = scan.nextInt();
		}
        int n = arr.length;  // 배열의 크기 저장
        // 삽입한 정수들의 배열을 퀵 정렬을 사용하여 정렬한다.
        quickSort(arr, 0, n - 1);
        // 정렬한 배열을 출력한다.
        printArray(arr, n);
        // 분할 정복을 진행한 횟수를 출력한다.
        System.out.println("Divide : " + divide + ", Conquer : " + conquer);

        // 스캐너 종료.
        scan.close();
    }
}