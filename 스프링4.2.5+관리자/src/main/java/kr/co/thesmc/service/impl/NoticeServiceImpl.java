package kr.co.thesmc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.thesmc.dao.NoticeDao;
import kr.co.thesmc.model.Book;
import kr.co.thesmc.model.Notice;
import kr.co.thesmc.service.NoticeService;
import kr.co.thesmc.util.CommonUtil;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Resource
	private NoticeDao noticeDao;

	@Override
	public int getNoticeTotal() throws Exception {
		return noticeDao.getNoticeTotal();
	}

	@Override
	public List<Book> getNoticeList(int i, int j) throws Exception {
		return noticeDao.getNoticeList(i, j);
	}

	@Override
	public void insertNotice(Notice n) throws Exception {
		noticeDao.insertNotice(n);
	}

	@Override
	public void updateNotice(Notice n) throws Exception {
		noticeDao.updateNotice(n);
	}

	@Override
	public void deleteNotice(String[] delChk) throws Exception {
		for(int i=0; i<delChk.length; i++){
			String no =  CommonUtil.nullCheck(delChk[i]);
			noticeDao.deleteNotice(no);
		}
	}

	@Override
	public Notice getNoticeView(String sno) throws Exception {
		return noticeDao.getNoticeView(sno);
	}
}
