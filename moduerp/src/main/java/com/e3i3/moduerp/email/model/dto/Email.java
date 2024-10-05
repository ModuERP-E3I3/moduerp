package com.e3i3.moduerp.email.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long emailId; // 이메일 ID
    private String senderUUID; // 발신자 UUID
    private String recipientUUID; // 수신자 UUID
    private String senderEmail; // 발신자 이메일 (조회용)
    private String recipientEmail; // 수신자 이메일 (조회용)
    private String senderName; // 발신자 이름 (조회용)
    private String recipientName; // 수신자 이름 (조회용)
    private String subject; // 제목
    private String body; // 본문
    private String attachmentPath; // 첨부파일 경로
    private Timestamp sentDate; // 발송 날짜
    private char isRead; // 읽음 여부 ('Y' 또는 'N', 디폴트는 'N')
}
