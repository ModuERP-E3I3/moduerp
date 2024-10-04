package com.e3i3.moduerp.email.model.service;

import java.util.List;

import com.e3i3.moduerp.email.model.dto.Email;

public interface EmailService {
	void insertEmail(Email email);

	List<Email> selectAllEmails();

	List<Email> selectEmailsByRecipient(String recipientEmail);

	List<Email> selectEmailsBySender(String senderEmail);

	List<Email> selectEmailsByUser(String userEmail);

	Email selectEmailById(Long emailId);

	// 읽음 상태 업데이트 메서드
	Email updateReadStatus(Long emailId);

	public void updateReadStatusBatch(List<Long> emailIds);

}
