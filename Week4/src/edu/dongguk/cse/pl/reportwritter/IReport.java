package edu.dongguk.cse.pl.reportwritter;

import java.util.ArrayList;

public interface IReport {
	// int totoalSearchedNo; // �� �˻� ����
	// String filename; // ����� ���� �̸�
	// ArrayList<ISearchItem> itemList; // �˻���� ����Ʈ

	// ����� ���� �̸� get/set
	void setFileName(String filename);

	String getFileName();

	// �� �˻� ���� get/set
	void setTotalSearchedNo(int totalResultNo);

	int getTotalSearchedNo();

	// �˻� ��� ���� add/�˻� ��� ����Ʈ get
	ArrayList<ISearchItem> getSearchList();

	void addSearchItm(ISearchItem item);
}
