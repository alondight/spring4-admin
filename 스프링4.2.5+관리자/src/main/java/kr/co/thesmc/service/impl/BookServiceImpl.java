package kr.co.thesmc.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.thesmc.dao.BookDao;
import kr.co.thesmc.model.Book;
import kr.co.thesmc.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Resource
	private BookDao bookDao;

	@Override
	public List<Book> getBookList(Map<String, String> filter) throws Exception {
		return bookDao.getBookList(filter);
	}

	@Override
	public void insertBook(Book vo) throws Exception {
		bookDao.insertBook(vo);
	}

	@Override
	public void updateBook(Book vo) throws Exception {
		bookDao.updateBook(vo);
	}

	@Override
	public void deleteBook(String id) throws Exception {
		bookDao.deleteBook(id);
	}

	@Override
	public List<String> getBookExcelListHeader() throws Exception {
		List<String> header = new LinkedList<String>();
		header.add("ID");
		header.add("이름");
		header.add("작가");
		header.add("가격");
		header.add("장르");
		header.add("출판사");
		header.add("발행권수");
		return header;
	}

	@Override
	public List<Map<String, String>> getBookExcelList(Map<String, String> filter) throws Exception {
		return bookDao.getBookExcelList(filter);
	}
}
