package com.keduit.project02.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.keduit.project02.domain.BookingVo;
import com.keduit.project02.domain.PriceVo;
import com.keduit.project02.domain.User;
public interface PriceRepository extends JpaRepository<PriceVo,Integer>{
	
	Optional<PriceVo> findByPno(int pno);
}
