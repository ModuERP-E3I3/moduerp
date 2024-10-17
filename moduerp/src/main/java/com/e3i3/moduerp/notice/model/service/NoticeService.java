package com.e3i3.moduerp.notice.model.service;

import java.util.List;

import com.e3i3.moduerp.notice.model.dto.Notice;

import java.util.List;

public interface NoticeService {
    int addNotice(Notice notice);
    Notice getNoticeById(String noticeId);
    List<Notice> getAllNotices();
    int updateNotice(Notice notice);
    int removeNotice(String noticeId);
    // 검색 기능 추가
    List<Notice> searchNotices(String category, String keyword);
    
    public List<Notice> getNoticesWithPagination(int page, int size);
    
    public int countAllNotices();
    
    
    public List<Notice> searchNoticesWithPagination(String category, String keyword, int page, int size);
    
    public int countFilteredNotices(String category, String keyword);
}
