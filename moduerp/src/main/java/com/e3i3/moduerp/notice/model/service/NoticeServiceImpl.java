package com.e3i3.moduerp.notice.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.notice.model.dao.NoticeDao;
import com.e3i3.moduerp.notice.model.dto.Notice;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
    private NoticeDao NoticeDao;

    @Override
    public int getNoticeCount() {
        return NoticeDao.getNoticeCount();
    }

    @Override
    public List<Notice> getNoticeList(int page, int pageSize) {
        return NoticeDao.getNoticeList(page, pageSize);
    }

    @Override
    public Notice getNoticeById(int noticeId) {
        return NoticeDao.getNoticeById(noticeId);
    }
    
    @Override
    public void insertNotice(Notice notice) {
        NoticeDao.insertNotice(notice);
    }

    @Override
    public void updateNotice(Notice notice) {
        NoticeDao.updateNotice(notice);
    }

    @Override
    public void deleteNotice(int noticeId) {
        NoticeDao.deleteNotice(noticeId);
    }
    
}