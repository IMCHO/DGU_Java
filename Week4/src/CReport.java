
import java.util.ArrayList;
import edu.dongguk.cse.pl.reportwritter.*;

public class CReport implements IReport {

	int totalSearchedNo;
	String fileName;
	ArrayList<ISearchItem> itemList;

	// 출력할 파일 이름 get/set
	public void setFileName(String filename) {
		this.fileName = filename;
	}

	public String getFileName() {
		return fileName;
	}

	// 총 검색 개수 get/set
	public void setTotalSearchedNo(int totalResultNo) {
		this.totalSearchedNo = totalResultNo;
	}

	// 검색 결과 저장 add/검색 결과 리스트 get
	public ArrayList<ISearchItem> getSearchList() {
		return itemList;
	}

	public int getTotalSearchedNo() {
		return totalSearchedNo;
	}

	@Override
	public void addSearchItm(ISearchItem item) {
		// TODO 자동 생성된 메소드 스텁
		if (itemList == null) {
			itemList = new ArrayList<ISearchItem>();
		} else {
			itemList.add(item);
		}
	}
}
