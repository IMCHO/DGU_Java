public class Practice2 {
	public static double sum(long n1,long n2) {  // long 매개변수를 2개 받은 후 더한 값 리턴
		double temp1=n1;
		double temp2=n2;
		return temp1+temp2;
	}
	public static void main(String[] args) {   // main 함수
		long num1=5555555555555555555L;        // long 변수
		long num2=6666666666666666666L;
		System.out.println(sum(num1,num2));    // 합 출력
	}
}