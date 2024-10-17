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
    
    /**
     * 전체 공지사항 조회 with 페이징
     */
    List<Notice> getNoticesWithPagination(int page, int size);

    /**
     * 조건 검색 공지사항 조회 with 페이징
     */
    List<Notice> searchNoticesWithPagination(String category, String keyword, int page, int size);

    /**
     * 전체 공지사항 개수 조회
     */
    int countAllNotices();

    /**
     * 조건 검색 공지사항 개수 조회
     */
    int countFilteredNotices(String category, String keyword);
}
