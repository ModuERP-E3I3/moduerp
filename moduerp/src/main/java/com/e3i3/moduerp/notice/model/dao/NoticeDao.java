package com.e3i3.moduerp.notice.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.notice.model.dto.Notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NoticeDao {
    
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    
    private static final String NAMESPACE = "NoticeMapper";

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
    
    
    /**
     * 전체 공지사항 조회 with 페이징
     */
    public List<Notice> findAllNotices(int offset, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", limit);
        return sqlSessionTemplate.selectList(NAMESPACE + ".findAllNotices", params);
    }

    /**
     * 전체 공지사항 개수 조회
     */
    public int countAllNotices() {
        return sqlSessionTemplate.selectOne(NAMESPACE + ".countAllNotices");
    }

    /**
     * 조건 검색 공지사항 조회 with 페이징
     */
    public List<Notice> searchNotices(String category, String keyword, int offset, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("category", category);
        params.put("keyword", keyword);
        params.put("offset", offset);
        params.put("limit", limit);
        return sqlSessionTemplate.selectList(NAMESPACE + ".searchNotices", params);
    }

    /**
     * 조건 검색 공지사항 개수 조회
     */
    public int countNotices(String category, String keyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("category", category);
        params.put("keyword", keyword);
        return sqlSessionTemplate.selectOne(NAMESPACE + ".countNotices", params);
    }
}
