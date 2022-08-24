package com.keduit.project02.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Table(name="carttbl")
@Builder
@Entity
public class CartVO {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cno;
	
	@Column(nullable = false, length = 25)
	private String username;
	
	@Column(nullable = false, length = 25)
	private String name;
	
	@Column(nullable = false, length = 20)
	private String phone;
	
	@Column(nullable = true, length = 20)
	private String dogSize;
	
	@Column(nullable = true, length = 20)
	private String pictureSize;
	
	@Column(nullable = true, length = 20)
	private String people;
	
	@Column(nullable = true, length = 20)
	private int dogNum;
	
	
	@Column(nullable = false, length = 50)
	@DateTimeFormat(pattern = "yyyy-M-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-M-dd", timezone = "Asia/Seoul")
    private LocalDate bookingDate; // 날짜
	
	@Column(nullable = false, length = 50)
	private int price;
	
	@Column(nullable = false, length = 50)
	private String btype;
	
	@Column(nullable = false, length = 50)
	private String themeName;
	
	
/*	
	bookingdatetime datetime ,
	themeName varchar(8),
	price int */

}
