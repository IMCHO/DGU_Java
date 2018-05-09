class Fraction {
	private int numerator; // 분자
	private int denomiator; // 분모

	public Fraction(int a, int b) { // 생성자 정의
		numerator = a;
		denomiator = b;
	}

	int getNumer() {
		return numerator;
	}

	int getDenomi() {
		return denomiator;
	}

	String sub(Fraction f) { // 빼기 함수
		if (this.denomiator == f.denomiator) { // 분모가 같으므로 그대로 연산 진행
			this.numerator -= f.numerator;
		} else { // 다르면 분모를 서로 곱해줘서 같게 만든 뒤 연산
			int temp = this.denomiator;
			this.denomiator *= f.denomiator;
			this.numerator *= f.denomiator;
			f.denomiator *= temp;
			f.numerator *= temp;
			this.numerator -= f.numerator;
		}
		this.changeSimpleFraction(); // 기약분수 처리
		return this.numerator + " / " + this.denomiator;
	}

	String div(Fraction f) { // 곱하기 함수
		this.denomiator *= f.numerator;
		this.numerator *= f.denomiator; // 역으로 곱해줌
		this.changeSimpleFraction(); // 기약분수 처리
		return this.numerator + " / " + this.denomiator;
	}

	void changeSimpleFraction() {
		int temp = (this.numerator < this.denomiator ? this.numerator : this.denomiator); // 작은 수 반환
		for (int i = temp; i > 0; i--) { // 최대공약수를 구하여야 하므로 위에서부터 카운트 다운
			if (this.numerator % i == 0 && this.denomiator % i == 0) { // 둘다 나뉘어 떨어지는 순간 그 수는 최대공약수
				this.numerator /= i;
				this.denomiator /= i;
				return;
			}
		}
	}
}

public class Practice {
	public static void main(String args[]) {
		Fraction A = new Fraction(2, 3);
		Fraction B = new Fraction(6, 2);
		System.out.println(A.div(B));
	}

}
