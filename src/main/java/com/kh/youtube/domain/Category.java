package com.kh.youtube.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	// primary key를 @Id로 설정
	@Id
	@Column(name="CATEGORY_CODE")	// 만약 db랑 자바 필드랑 이름이 같으면 자동으로 인식이 된다.
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "categorySequence")	// id를 자동을 생성 (generator는 아래에 설정한 시퀸스 이름)
	// 만들어져 있는 시퀸스 가져온다. name은 식별자 sequenceName은 DB상 시퀸스 이름, allocationSize는 증가값
	@SequenceGenerator(name="categorySequence", sequenceName = "SEQ_CATEGORY", allocationSize = 1)
	private int categoryCode;

	// primary key 외의 일반 필드들은 @Column으로 설정
	@Column(name="CATEGORY_NAME")
	private String categoryName;

}