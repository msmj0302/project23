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
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="priceTbl")
@Entity
@Data
public class PriceVo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pno;
	
	@Column(nullable = false)
	private int conPrice;
	
	@Column(nullable = false)
	private int familyPrice;
	
	@Column(nullable = false)
	private int idPrice;
	
	@Column(nullable = false)
	private int spaPrice;
	
	@Column(nullable = false)
	private int swimPrice;
	
	@Column(nullable = false)
	private int dogSizePrice;
	
	@Column(nullable = false)
	private int pictureSizePrice;
	
	@Column(nullable = false)
	private int peopleNumPrice;
	
	@Column(nullable = false)
	private int dogNumPrice;
	
	
	
	
}