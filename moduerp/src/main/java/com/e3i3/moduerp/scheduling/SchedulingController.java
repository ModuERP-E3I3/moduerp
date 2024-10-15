package com.e3i3.moduerp.scheduling;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.e3i3.moduerp.pay.model.dto.PayDTO;
import com.e3i3.moduerp.pay.model.service.PayService;

@Configuration
@EnableScheduling
public class SchedulingController {

	@Autowired
	private PayService payService; // PayService 주입

	// 매일 자정에 실행되는 스케줄러 (현재 날짜와 일치하는 결제 요청 처리)
	@Scheduled(cron = "0 * * * * ?")
	public void processSubscriptionPayment() {
		System.out.println("스케줄러 메서드 실행");

		// 오늘 날짜의 '일(DAY)'만 추출 (String 형태)
		String today = String.valueOf(LocalDate.now().getDayOfMonth());

		System.out.println("스케줄링 실행: 오늘의 결제 요청 처리 - 일자: " + today);

		// 오늘의 결제 요청 데이터를 DB에서 조회
		List<PayDTO> payList = payService.getPaymentsByRequestDay(today);

		// 결제 처리 로직 수행
		for (PayDTO pay : payList) {
			System.out.println("결제 처리 중: " + pay);

			// 결제 처리 로직 (예: API 호출 등)
			// 예제: 결제 상태 업데이트
//            pay.setPayStatus('Y');
//            payService.updatePayStatus(pay);
		}
	}
}
