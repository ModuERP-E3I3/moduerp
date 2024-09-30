package com.e3i3.moduerp.employee.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


<<<<<<< Updated upstream
@Data // getter, setter, toString, equals, hashCode ¸Þ¼Òµå¸¦ ÀÚµ¿À¸·Î »ý¼º
@NoArgsConstructor // ±âº»»ý¼ºÀÚ
@AllArgsConstructor // ¸ðµç ÇÊµå¸¦ ¸Å°³º¯¼ö·Î ¹Þ´Â »ý¼ºÀÚ »ý¼º
=======
@Data // getter, setter, toString, equals, hashCode ë©”ì†Œë“œë¥¼ ìžë™ìœ¼ë¡œ ìƒì„±
@NoArgsConstructor // ê¸°ë³¸ìƒì„±ìž
@AllArgsConstructor // ëª¨ë“  í•„ë“œë¥¼ ë§¤ê°œë³€ìˆ˜ë¡œ ë°›ëŠ” ìƒì„±ìž ìƒì„±
>>>>>>> Stashed changes
@Accessors(chain = true) // company.setBizNumber("123").setCompanyName("MyCompany)
public class Employee implements Serializable{
	private static final long serialVersionUID = -3562883942862635847L;
	
	private UUID uuid; // uuid
<<<<<<< Updated upstream
	private String bizNumber; // »ç¾÷ÀÚ¹øÈ£
	private String approvalCode; // ½ÂÀÎÄÚµå
	private String departmentId; // ºÎ¼­ ID
	private String jobId; // Á÷¹« ID
	private char privateAuthority; // »ç¼³ ±ÇÇÑ ¿©ºÎ
	private String empNo; // »ç¿ø¹øÈ£
	private String empName; // »ç¿ø¸í
	private String empEmail; // »ç¿ø ÀÌ¸ÞÀÏ
	private String userPhone; // »ç¿ëÀÚ ÀüÈ­¹øÈ£
	private Date registrationDate; // µî·ÏÀÏÀÚ
	private String profileImg; // ÇÁ·ÎÇÊ ÀÌ¹ÌÁö¸í (uuid_ÇÁ·ÎÇÊ¸í)
	private Date updatedAt; // ¼öÁ¤ÀÏÀÚ
	private char isDeleted; // Å»Åð ¿©ºÎ
	private String lastLoginLocation; // ¸¶Áö¸· ·Î±×ÀÎ À§Ä¡
	private String deletedExcuse; // »èÁ¦ »çÀ¯
	private Date deletedAt; // »èÁ¦ÀÏÀÚ
	private Date hireDate; // ÀÔ»çÀÏÀÚ
	private Date quitDate; // Åð»çÀÏÀÚ
	private String reNum; // ÁÖ¹Î¹øÈ£
	private String address; // ÁÖ¼Ò
	private char isEmailChanged; // ÀÌ¸ÞÀÏ º¯°æ ¿©ºÎ
	private String newEmpEmail; // »õ ÀÌ¸ÞÀÏ ÁÖ¼Ò
	private double remainingLeave; // ³²Àº ÈÞ°¡ ÀÏ¼ö
	private LocalTime contractStartTime; // °è¾à»ó Ãâ±Ù ½Ã°£
	private LocalTime contractEndTime; // °è¾à»ó Åð±Ù ½Ã°£
	private UUID mgrUuid; // °ü¸®ÀÚ uuid
}
=======
	private String bizNumber; // ì‚¬ì—…ìžë²ˆí˜¸
	private String approvalCode; // ìŠ¹ì¸ì½”ë“œ
	private String departmentId; // ë¶€ì„œ ID
	private String jobId; // ì§ë¬´ ID
	private char privateAuthority; // ì‚¬ì„¤ ê¶Œí•œ ì—¬ë¶€
	private String empNo; // ì‚¬ì›ë²ˆí˜¸
	private String empName; // ì‚¬ì›ëª…
	private String empEmail; // ì‚¬ì› ì´ë©”ì¼
	private String userPhone; // ì‚¬ìš©ìž ì „í™”ë²ˆí˜¸
	private Date registrationDate; // ë“±ë¡ì¼ìž
	private String profileImg; // í”„ë¡œí•„ ì´ë¯¸ì§€ëª… (uuid_í”„ë¡œí•„ëª…)
	private Date updatedAt; // ìˆ˜ì •ì¼ìž
	private char isDeleted; // íƒˆí‡´ ì—¬ë¶€
	private String lastLoginLocation; // ë§ˆì§€ë§‰ ë¡œê·¸ì¸ ìœ„ì¹˜
	private String deletedExcuse; // ì‚­ì œ ì‚¬ìœ 
	private Date deletedAt; // ì‚­ì œì¼ìž
	private Date hireDate; // ìž…ì‚¬ì¼ìž
	private Date quitDate; // í‡´ì‚¬ì¼ìž
	private String reNum; // ì£¼ë¯¼ë²ˆí˜¸
	private String address; // ì£¼ì†Œ
	private char isEmailChanged; // ì´ë©”ì¼ ë³€ê²½ ì—¬ë¶€
	private String newEmpEmail; // ìƒˆ ì´ë©”ì¼ ì£¼ì†Œ
	private double remainingLeave; // ë‚¨ì€ íœ´ê°€ ì¼ìˆ˜
	private LocalTime contractStartTime; // ê³„ì•½ìƒ ì¶œê·¼ ì‹œê°„
	private LocalTime contractEndTime; // ê³„ì•½ìƒ í‡´ê·¼ ì‹œê°„
	private UUID mgrUuid; // ê´€ë¦¬ìž uuid
}
>>>>>>> Stashed changes
