import java.util.ArrayList;

import edu.dongguk.cse.pl.reportwritter.ISearchItem;

public class CSearchItem implements ISearchItem {

	int lineNo;
	int indexNo;

	// ���γѹ� get/set
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}

	public int getLineNo() {
		return lineNo;
	}

	// �ε��� get/set
	public void setIndexNo(int indexNo) {
		this.indexNo = indexNo;
	}

	public int getIndexNo() {
		return indexNo;
	}
}
