package kr.co.thesmc.dao;

import java.util.List;
import java.util.Map;

import kr.co.thesmc.model.Book;

public interface BookDao {
	List<Book> getBookList(Map<String, String> filter) throws Exception;
	void insertBook(Book vo) throws Exception;
	void updateBook(Book vo) throws Exception;
	void deleteBook(String id) throws Exception;
	List<Map<String, String>> getBookExcelList(Map<String, String> filter) throws Exception;
}
