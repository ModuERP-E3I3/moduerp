package com.e3i3.moduerp.notice.model.service;

import java.util.ArrayList;

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
    public ArrayList<Notice> getNoticeList(int page, int pageSize) {
        return NoticeDao.getNoticeList(page, pageSize);
    }

    @Override
    public Notice getNoticeById(int noticeId) {
        return NoticeDao.getNoticeById(noticeId); 
    }
}