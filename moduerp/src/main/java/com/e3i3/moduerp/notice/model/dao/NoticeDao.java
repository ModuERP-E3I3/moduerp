package com.e3i3.moduerp.notice.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.notice.model.dto.Notice;

@Repository("noticeDao")
public class NoticeDao {
	@SuppressWarnings("unused")
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int getNoticeCount() {
		return 0;
	}

	public ArrayList<Notice> getNoticeList(int page, int pageSize) {
		return null;
	}

	public Notice getNoticeById(int noticeId) {
		return null;
	}
}