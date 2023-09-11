package com.kh.youtube.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert // default값 자동으로 추가
public class Member {

	@Id // primary key
	@Column
	private String id;

	@Column
	private String password;

	@Column
	private String name;

	@Column
	private String authority;

}
