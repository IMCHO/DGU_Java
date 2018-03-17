
public class Practice3 {
	public static int power(int num1,int power) {
		int result=1;             // 결과값을 저장한 변수 선언
		while(power--!=0) {       // power에 저장된 횟수만큼 반복분 진행
			result*=num1;         // 반복문이 돌 때마다 num1 곱해줌
		}
		return result;
	}
	
	public static void main(String[] args) {   // main함수
		System.out.println(power(3,5));
		}      // 결과 출력
}
