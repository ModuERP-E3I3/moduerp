package com.e3i3.moduerp.email.model.service;

import java.util.List;

import com.e3i3.moduerp.email.model.dto.Email;

public interface EmailService {
	void insertEmail(Email email);

	List<Email> selectAllEmails();

	List<Email> selectEmailsByRecipient(String recipientUUID);

	List<Email> selectEmailsBySender(String senderUUID);

	List<Email> selectEmailsByUser(String userUUID);

	Email selectEmailById(Long emailId);

	// 읽음 상태 업데이트 메서드
	Email updateReadStatus(Long emailId);

	void updateReadStatusBatch(List<Long> emailIds);
	
	
	public void deleteEmailsBatch(List<Long> emailIds);
	
	  // 페이징 처리를 위한 메서드 추가
    List<Email> selectEmailsByUserWithPaging(String userUUID, int offset, int limit);

    // 총 이메일 수를 세기 위한 메서드 추가
    int countEmailsByUser(String userUUID);

    
}
