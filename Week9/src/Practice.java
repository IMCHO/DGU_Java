class Fraction {
	private int numerator; // ����
	private int denomiator; // �и�

	public Fraction(int a, int b) { // ������ ����
		numerator = a;
		denomiator = b;
	}

	int getNumer() {
		return numerator;
	}

	int getDenomi() {
		return denomiator;
	}

	String sub(Fraction f) { // ���� �Լ�
		if (this.denomiator == f.denomiator) { // �и� �����Ƿ� �״�� ���� ����
			this.numerator -= f.numerator;
		} else { // �ٸ��� �и� ���� �����༭ ���� ���� �� ����
			int temp = this.denomiator;
			this.denomiator *= f.denomiator;
			this.numerator *= f.denomiator;
			f.denomiator *= temp;
			f.numerator *= temp;
			this.numerator -= f.numerator;
		}
		this.changeSimpleFraction(); // ���м� ó��
		return this.numerator + " / " + this.denomiator;
	}

	String div(Fraction f) { // ���ϱ� �Լ�
		this.denomiator *= f.numerator;
		this.numerator *= f.denomiator; // ������ ������
		this.changeSimpleFraction(); // ���м� ó��
		return this.numerator + " / " + this.denomiator;
	}

	void changeSimpleFraction() {
		int temp = (this.numerator < this.denomiator ? this.numerator : this.denomiator); // ���� �� ��ȯ
		for (int i = temp; i > 0; i--) { // �ִ������� ���Ͽ��� �ϹǷ� ���������� ī��Ʈ �ٿ�
			if (this.numerator % i == 0 && this.denomiator % i == 0) { // �Ѵ� ������ �������� ���� �� ���� �ִ�����
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
