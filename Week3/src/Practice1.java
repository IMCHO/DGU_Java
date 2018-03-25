
public class Practice1 {
	public static int[][] transpose(int[][] matrix){       // 바꾸고 싶은 행렬을 매개변수로 받음
		int[][] temp=new int[3][2];
		for(int i=0;i<2;i++)
			for(int j=0;j<3;j++) temp[j][i]=matrix[i][j];  // 핵심 알고리즘              
		return temp;                                       // 변경된 행렬 반환
	}
	public static void main(String[] args) {     // main함수
		int num=1;                               // 행렬에 넣을 변수
		int[][] matrix=new int[2][3];            // 바뀌기전 행렬
		int[][] temp=new int[3][2];              // 형식이 바뀐 행렬을 저장할 변수
		for(int i=0;i<2;i++)
			for(int j=0;j<3;j++) matrix[i][j]=num++;    // 초기값 할당
		temp=transpose(matrix);                         // 형식 변경 메소드
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) System.out.print(temp[i][j]);
			System.out.println(' ');                             // 원하는 출력형태로 출력
		}
	
	}

}
