package com.e3i3.moduerp.notice.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.notice.model.dto.Notice;

import java.util.List;
import java.util.Map;

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
    
    
    // Get notices with pagination
    public List<Notice> getNoticesWithPagination(int offset, int size) {
        Map<String, Object> params = Map.of("offset", offset, "size", size);
        return sqlSessionTemplate.selectList("NoticeMapper.getNoticesWithPagination", params);
    }

    // Count all notices
    public int countAllNotices() {
        return sqlSessionTemplate.selectOne("NoticeMapper.countAllNotices");
    }

    // Search notices with pagination
    public List<Notice> searchNoticesWithPagination(String category, String keyword, int offset, int size) {
        Map<String, Object> params = Map.of("category", category, "keyword", keyword, "offset", offset, "size", size);
        return sqlSessionTemplate.selectList("NoticeMapper.searchNoticesWithPagination", params);
    }

    // Count filtered notices
    public int countFilteredNotices(String category, String keyword) {
        Map<String, Object> params = Map.of("category", category, "keyword", keyword);
        return sqlSessionTemplate.selectOne("NoticeMapper.countFilteredNotices", params);
    }
}
