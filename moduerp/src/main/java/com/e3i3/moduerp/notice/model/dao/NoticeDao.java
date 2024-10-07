package com.e3i3.moduerp.notice.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.notice.model.dto.Notice;

@Repository("noticeDao")
public class NoticeDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int getNoticeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<Notice> getNoticeList(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public Notice getNoticeById(int noticeId) {
		// TODO Auto-generated method stub
		return null;
	}
}