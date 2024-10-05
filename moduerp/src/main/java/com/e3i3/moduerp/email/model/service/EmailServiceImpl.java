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
}
