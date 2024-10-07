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

	// 수신한 이메일 조회 (UUID를 기준으로)
	public List<Email> selectEmailsByRecipient(String recipientUUID) {
		return sqlSessionTemplate.selectList("EmailMapper.selectEmailsByRecipient", recipientUUID);
	}

	// 보낸 이메일 조회 (UUID를 기준으로)
	public List<Email> selectEmailsBySender(String senderUUID) {
		return sqlSessionTemplate.selectList("EmailMapper.selectEmailsBySender", senderUUID);
	}

	// 로그인 유저가 보낸 이메일과 받은 이메일 조회 (UUID를 기준으로)
	public List<Email> selectEmailsByUser(String userUUID) {
		return sqlSessionTemplate.selectList("EmailMapper.selectEmailsByUser", userUUID);
	}

	// 이메일 ID로 조회하여 isRead 'Y'로 업데이트하기
	public Email updateReadStatus(Long emailId) {
		return sqlSessionTemplate.selectOne("EmailMapper.updateReadStatus", emailId);
	}

	// 이메일 ID로 이메일 조회
	public Email selectEmailById(Long emailId) {
		return sqlSessionTemplate.selectOne("EmailMapper.selectEmailById", emailId);
	}

	public void deleteEmailsBatch(List<Long> emailIds) {
		sqlSessionTemplate.delete("EmailMapper.deleteEmailsBatch", emailIds); // 삭제 쿼리 실행
	}

	// 읽음 상태 일괄 업데이트
	public void updateReadStatusBatch(List<Long> emailIds) {
		sqlSessionTemplate.update("EmailMapper.updateReadStatusBatch", emailIds);
	}
}
