package com.kh.youtube.domain;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Channel {
	private int channelCode;
	private String channelName;
	private String channelDesc;
	private Date channelDate;
	
	
	// Channel 엔티티와 Member 엔티티를 n 대 1 관계로 설정
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")	// name 속성은 join하는 컬럼명(foreign key). 외래키 생성 or Member 엔티티와 기본키와 매핑
	private Member member;
}