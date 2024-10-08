package com.e3i3.moduerp.notice.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.notice.model.dto.Notice;

@Repository("noticeDao")
public class NoticeDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public int getNoticeCount() {
        return sqlSessionTemplate.selectOne("noticeMapper.getNoticeCount");
    }

    public List<Notice> getNoticeList(int page, int pageSize) {
        int start = (page - 1) * pageSize + 1;  // Oracle의 ROWNUM은 1부터 시작
        int end = page * pageSize;
        
        HashMap<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("end", end);
        
        return sqlSessionTemplate.selectList("noticeMapper.getNoticeList", params);
    }


    public Notice getNoticeById(int noticeId) {
        return sqlSessionTemplate.selectOne("noticeMapper.getNoticeById", noticeId);
    }
    
    public void insertNotice(Notice notice) {
        sqlSessionTemplate.insert("noticeMapper.insertNotice", notice);
    }
    
    public void updateNotice(Notice notice) {
        sqlSessionTemplate.update("noticeMapper.updateNotice", notice);
    }
    
    public void deleteNotice(int noticeId) {
        sqlSessionTemplate.delete("noticeMapper.deleteNotice", noticeId);
    }
}
