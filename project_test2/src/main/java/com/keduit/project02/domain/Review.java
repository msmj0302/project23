package com.keduit.project02.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
@Data
@Entity
public class Review {
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private Long rno; // 오라클-시퀀스, MySQL-auto_increment / 자동입력

	/*
	 * @ManyToOne // 여러 개의 답변을 하나의 유저가 쓸 수 있다.
	 * 
	 * @JoinColumn(name = "userName") private User user;
	 */
	
	@Column(nullable = true)
	private String username;

	@Column(nullable = true, length = 20)
	private String btype;

	@Column(nullable = false, length = 500)
	private String content;

	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@CreationTimestamp
	private LocalDate reviewDate;

	@Column(nullable = true, length = 12)
	private int scope;
}