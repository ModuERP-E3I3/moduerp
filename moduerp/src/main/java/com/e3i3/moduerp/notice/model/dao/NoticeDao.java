package com.e3i3.moduerp.notice.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.notice.model.dto.Notice;

import java.util.List;

@Repository
public class NoticeDao {
    
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public int insertNotice(Notice notice) {
        return sqlSessionTemplate.insert("NoticeMapper.insertNotice", notice);
    }

    public Notice selectNoticeById(String noticeId) {
        return sqlSessionTemplate.selectOne("NoticeMapper.selectNoticeById", noticeId);
    }

    public List<Notice> selectAllNotices() {
        return sqlSessionTemplate.selectList("NoticeMapper.selectAllNotices");
    }

    public int updateNotice(Notice notice) {
        return sqlSessionTemplate.update("NoticeMapper.updateNotice", notice);
    }

    public int deleteNotice(String noticeId) {
        return sqlSessionTemplate.delete("NoticeMapper.deleteNotice", noticeId);
    }
    

    public List<Notice> searchNoticesByTitle(String keyword) {
        return sqlSessionTemplate.selectList("NoticeMapper.searchNoticesByTitle", keyword);
    }

    public List<Notice> searchNoticesByBody(String keyword) {
        return sqlSessionTemplate.selectList("NoticeMapper.searchNoticesByBody", keyword);
    }

    public List<Notice> searchNoticesByTitleAndBody(String keyword) {
        return sqlSessionTemplate.selectList("NoticeMapper.searchNoticesByTitleAndBody", keyword);
    }
    
}
