import java.util.ArrayList;

import edu.dongguk.cse.pl.reportwritter.ISearchItem;

public class CSearchItem implements ISearchItem {

	int lineNo;
	int indexNo;

	// 라인넘버 get/set
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}

	public int getLineNo() {
		return lineNo;
	}

	// 인덱스 get/set
	public void setIndexNo(int indexNo) {
		this.indexNo = indexNo;
	}

	public int getIndexNo() {
		return indexNo;
	}
}
