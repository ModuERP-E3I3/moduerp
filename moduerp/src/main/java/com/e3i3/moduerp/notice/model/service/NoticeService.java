package com.e3i3.moduerp.notice.model.service;

import java.util.List;

import com.e3i3.moduerp.notice.model.dto.Notice;

public interface NoticeService {
    int getNoticeCount();
    List<Notice> getNoticeList(int page, int pageSize);
    Notice getNoticeById(int noticeId); // 공지사항 상세 조회 메서드 추가
}