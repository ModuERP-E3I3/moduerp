package com.e3i3.moduerp.email.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3i3.moduerp.email.model.dto.Email;

@Repository
public class EmailDao {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    // 이메일 등록
    public void insertEmail(Email email) {
        sqlSessionTemplate.insert("EmailMapper.insertEmail", email);
    }

    // 모든 이메일 조회
    public List<Email> selectAllEmails() {
        return sqlSessionTemplate.selectList("EmailMapper.selectAllEmails");
    }

    // 수신한 이메일 조회
    public List<Email> selectEmailsByRecipient(String recipientEmail) {
        return sqlSessionTemplate.selectList("EmailMapper.selectEmailsByRecipient", recipientEmail);
    }

    // 보낸 이메일 조회
    public List<Email> selectEmailsBySender(String senderEmail) {
        return sqlSessionTemplate.selectList("EmailMapper.selectEmailsBySender", senderEmail);
    }

    // 로그인 유저가 보낸 이메일과 받은 이메일 조회
    public List<Email> selectEmailsByUser(String userEmail) {
        return sqlSessionTemplate.selectList("EmailMapper.selectEmailsByUser", userEmail);
    }
    
    // 이메일 ID로 이메일 조회
    public Email selectEmailById(Long emailId) {
        return sqlSessionTemplate.selectOne("EmailMapper.selectEmailById", emailId);
    }
}
