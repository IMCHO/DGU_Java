
public class Practice1 {
	public static int[][] transpose(int[][] matrix){       // �ٲٰ� ���� ����� �Ű������� ����
		int[][] temp=new int[3][2];
		for(int i=0;i<2;i++)
			for(int j=0;j<3;j++) temp[j][i]=matrix[i][j];  // �ٽ� �˰���              
		return temp;                                       // ����� ��� ��ȯ
	}
	public static void main(String[] args) {     // main�Լ�
		int num=1;                               // ��Ŀ� ���� ����
		int[][] matrix=new int[2][3];            // �ٲ���� ���
		int[][] temp=new int[3][2];              // ������ �ٲ� ����� ������ ����
		for(int i=0;i<2;i++)
			for(int j=0;j<3;j++) matrix[i][j]=num++;    // �ʱⰪ �Ҵ�
		temp=transpose(matrix);                         // ���� ���� �޼ҵ�
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) System.out.print(temp[i][j]);
			System.out.println(' ');                             // ���ϴ� ������·� ���
		}
	
	}

}
