package com.e3i3.moduerp.email.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3i3.moduerp.email.model.dao.EmailDao;
import com.e3i3.moduerp.email.model.dto.Email;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private EmailDao emailDao;

	@Override
	public void insertEmail(Email email) {
		emailDao.insertEmail(email);
	}

	@Override
	public List<Email> selectAllEmails() {
		return emailDao.selectAllEmails();
	}

	@Override
	public List<Email> selectEmailsByRecipient(String recipientUUID) {
		return emailDao.selectEmailsByRecipient(recipientUUID);
	}

	@Override
	public List<Email> selectEmailsBySender(String senderUUID) {
		return emailDao.selectEmailsBySender(senderUUID);
	}

	@Override
	public List<Email> selectEmailsByUser(String userUUID) {
		return emailDao.selectEmailsByUser(userUUID);
	}

	@Override
	public Email selectEmailById(Long emailId) {
		return emailDao.selectEmailById(emailId);
	}

	@Override
	public Email updateReadStatus(Long emailId) {
		return emailDao.updateReadStatus(emailId);
	}

	@Override
	public void updateReadStatusBatch(List<Long> emailIds) {
		emailDao.updateReadStatusBatch(emailIds);
	}

	// 페이징 처리를 위한 메서드 구현
	@Override
	public List<Email> selectEmailsByUserWithPaging(String userUUID, int offset, int limit) {
		return emailDao.selectEmailsByUserWithPaging(userUUID, offset, limit);
	}

	// 총 이메일 수를 세기 위한 메서드 구현
	@Override
	public int countEmailsByUser(String userUUID) {
		return emailDao.countEmailsByUser(userUUID);
	}

	@Override
	public void deleteEmailsBatch(List<Long> emailIds) {
		emailDao.deleteEmailsBatch(emailIds); // DAO 메서드 호출
	}
	
	 // 보낸 이메일 페이징 처리를 위한 메서드 구현
    @Override
    public int countEmailsBySender(String senderUUID) {
        return emailDao.countEmailsBySender(senderUUID);
    }

    @Override
    public List<Email> selectEmailsBySenderWithPaging(String senderUUID, int offset, int limit) {
        return emailDao.selectEmailsBySenderWithPaging(senderUUID, offset, limit);
    }
    
    
    // 추가된 메서드 구현
    @Override
    public int countEmailsByRecipient(String recipientUUID) {
        return emailDao.countEmailsByRecipient(recipientUUID);
    }

    @Override
    public List<Email> selectEmailsByRecipientWithPaging(String recipientUUID, int offset, int limit) {
        return emailDao.selectEmailsByRecipientWithPaging(recipientUUID, offset, limit);
    }

}
