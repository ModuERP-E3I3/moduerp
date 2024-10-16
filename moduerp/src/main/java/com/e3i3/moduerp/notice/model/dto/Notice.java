package com.e3i3.moduerp.notice.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Notice {
    private String noticeId;
    private String title;
    private String body;
    private String  attachment;  // Nullable
    private java.sql.Date noticeDate;
}
