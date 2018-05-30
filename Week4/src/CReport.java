
import java.util.ArrayList;
import edu.dongguk.cse.pl.reportwritter.*;

public class CReport implements IReport {

	int totalSearchedNo;
	String fileName;
	ArrayList<ISearchItem> itemList;

	// ����� ���� �̸� get/set
	public void setFileName(String filename) {
		this.fileName = filename;
	}

	public String getFileName() {
		return fileName;
	}

	// �� �˻� ���� get/set
	public void setTotalSearchedNo(int totalResultNo) {
		this.totalSearchedNo = totalResultNo;
	}

	// �˻� ��� ���� add/�˻� ��� ����Ʈ get
	public ArrayList<ISearchItem> getSearchList() {
		return itemList;
	}

	public int getTotalSearchedNo() {
		return totalSearchedNo;
	}

	@Override
	public void addSearchItm(ISearchItem item) {
		// TODO �ڵ� ������ �޼ҵ� ����
		if (itemList == null) {
			itemList = new ArrayList<ISearchItem>();
		} else {
			itemList.add(item);
		}
	}
}
