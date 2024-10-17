package com.e3i3.moduerp.notice.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.notice.model.dao.NoticeDao;
import com.e3i3.moduerp.notice.model.dto.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDAO;

    @Override
    public int addNotice(Notice notice) {
        return noticeDAO.insertNotice(notice);
    }

    @Override
    public Notice getNoticeById(String noticeId) {
        return noticeDAO.selectNoticeById(noticeId);
    }

    @Override
    public List<Notice> getAllNotices() {
        return noticeDAO.selectAllNotices();
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeDAO.updateNotice(notice);
    }

    @Override
    public int removeNotice(String noticeId) {
        return noticeDAO.deleteNotice(noticeId);
    }
    
    @Override
    public List<Notice> searchNotices(String category, String keyword) {
    	// 카테고리에 따른 검색 로직 분리
        if ("제목".equals(category)) {
        	// 제목으로 검색
            return noticeDAO.searchNoticesByTitle(keyword);
        } else if ("내용".equals(category)) {
        	// 내용으로 검색
            return noticeDAO.searchNoticesByBody(keyword);
        } else if ("제목+내용".equals(category)) {
        	// 제목+내용으로 검색
            return noticeDAO.searchNoticesByTitleAndBody(keyword);
        } else {
        	// 카테고리가 없으면 전체 공지사항 반환
            return noticeDAO.selectAllNotices();
        }
    }
    
    @Override
    public List<Notice> getNoticesWithPagination(int page, int size) {
        int offset = (page - 1) * size;
        return noticeDAO.getNoticesWithPagination(offset, size);
    }
    @Override
    public int countAllNotices() {
        return noticeDAO.countAllNotices();
    }
    @Override
    public List<Notice> searchNoticesWithPagination(String category, String keyword, int page, int size) {
        int offset = (page - 1) * size;
        return noticeDAO.searchNoticesWithPagination(category, keyword, offset, size);
    }
    @Override
    public int countFilteredNotices(String category, String keyword) {
        return noticeDAO.countFilteredNotices(category, keyword);
    }

}
