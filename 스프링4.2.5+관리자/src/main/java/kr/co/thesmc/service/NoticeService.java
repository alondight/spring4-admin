package kr.co.thesmc.service;

import java.util.List;

import kr.co.thesmc.model.Book;
import kr.co.thesmc.model.Notice;

public interface NoticeService {
	int getNoticeTotal() throws Exception;
	List<Book> getNoticeList(int i, int j) throws Exception;
	void insertNotice(Notice n) throws Exception;
	void updateNotice(Notice n) throws Exception;
	void deleteNotice(String[] delChk) throws Exception;
	Notice getNoticeView(String sno)throws Exception;
}
