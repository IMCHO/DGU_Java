package edu.dongguk.cse.pl.reportwritter;

import java.util.ArrayList;

public interface IReport {
	// int totoalSearchedNo; // 총 검색 개수
	// String filename; // 출력할 파일 이름
	// ArrayList<ISearchItem> itemList; // 검색결과 리스트

	// 출력할 파일 이름 get/set
	void setFileName(String filename);

	String getFileName();

	// 총 검색 개수 get/set
	void setTotalSearchedNo(int totalResultNo);

	int getTotalSearchedNo();

	// 검색 결과 저장 add/검색 결과 리스트 get
	ArrayList<ISearchItem> getSearchList();

	void addSearchItm(ISearchItem item);
}
