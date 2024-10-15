package com.e3i3.moduerp.email.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// 로그인 유저가 받은 이메일의 총 개수를 세는 메서드
	public int countEmailsByRecipient(String recipientUUID) {
		return sqlSessionTemplate.selectOne("EmailMapper.countEmailsByRecipient", recipientUUID);
	}

	// 로그인 유저가 받은 이메일을 페이징하여 조회하는 메서드
	public List<Email> selectEmailsByRecipientWithPaging(String recipientUUID, int offset, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("recipientUUID", recipientUUID);
		params.put("offset", offset);
		params.put("limit", limit);
		return sqlSessionTemplate.selectList("EmailMapper.selectEmailsByRecipientWithPaging", params);
	}

	public int countEmailsBySender(String senderUUID) {
		return sqlSessionTemplate.selectOne("EmailMapper.countEmailsBySender", senderUUID);
	}

	public List<Email> selectEmailsBySenderWithPaging(String senderUUID, int offset, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("senderUUID", senderUUID);
		params.put("offset", offset);
		params.put("limit", limit);
		return sqlSessionTemplate.selectList("EmailMapper.selectEmailsBySenderWithPaging", params);
	}

	// 로그인 유저가 보낸 이메일과 받은 이메일 조회 (페이징 처리)
	public List<Email> selectEmailsByUserWithPaging(String userUUID, int offset, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("userUUID", userUUID);
		params.put("offset", offset);
		params.put("limit", limit);
		return sqlSessionTemplate.selectList("EmailMapper.selectEmailsByUserWithPaging", params);
	}

	// 전체 이메일 수를 세는 메서드
	public int countEmailsByUser(String userUUID) {
		return sqlSessionTemplate.selectOne("EmailMapper.countEmailsByUser", userUUID);
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
	
	//로그인 유저가 받은 안 읽은 이메일 개수 조회
	public int countUnreadEmailsByRecipient(String uuid) {
		return sqlSessionTemplate.selectOne("EmailMapper.countUnreadEmailsByRecipient", uuid);
	}
}
