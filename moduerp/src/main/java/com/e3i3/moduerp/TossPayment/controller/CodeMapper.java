package com.e3i3.moduerp.TossPayment.controller;

public class CodeMapper {

	// 카드 매핑
	public static String getCardName(String cardCode) {
		switch (cardCode) {
		case "3K":
			return "기업비씨";
		case "46":
			return "광주은행";
		case "71":
			return "롯데카드";
		case "30":
			return "KDB산업은행";
		case "31":
			return "BC카드";
		case "51":
			return "삼성카드";
		case "38":
			return "새마을금고";
		case "41":
			return "신한카드";
		case "62":
			return "신협";
		case "36":
			return "씨티카드";
		case "33":
			return "우리BC카드";
		case "W1":
			return "우리카드";
		case "37":
			return "우체국예금보험";
		case "39":
			return "저축은행중앙회";
		case "35":
			return "전북은행";
		case "42":
			return "제주은행";
		case "15":
			return "카카오뱅크";
		case "3A":
			return "케이뱅크";
		case "24":
			return "토스뱅크";
		case "21":
			return "하나카드";
		case "61":
			return "현대카드";
		case "11":
			return "KB국민카드";
		case "91":
			return "NH농협카드";
		case "34":
			return "Sh수협은행";
		default:
			return "알 수 없는 발급사 (" + cardCode + ")";
		}
	}

}
