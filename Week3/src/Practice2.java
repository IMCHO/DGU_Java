import java.math.*;

public class Practice2 {
	public static BigInteger sum(long n1,long n2) {  // long 매개변수를 2개 받은 후 더한 값 리턴
		String s1=String.valueOf(n1);                // long변수를 String변수로 변환
		String s2=String.valueOf(n2);
		BigInteger temp1=new BigInteger(s1);         // BigInteger 변수를 이용하여 계산
		BigInteger temp2=new BigInteger(s2);
		BigInteger addResult=temp1.add(temp2);       // 합 계산
		return addResult;
	}
	public static void main(String[] args) {   // main 함수
		long num1=5555555555555555555L;        // long 변수
		long num2=6666666666666666666L;
		System.out.println(sum(num1,num2));    // 합 출력
	}
}