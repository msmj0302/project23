package com.keduit.project02.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.keduit.project02.domain.BookingVo;
public interface BookingRepository extends JpaRepository<BookingVo,Integer>{
	
	@Query(value = "select bookingDate from booking where btype=? group by bookingDate having count(bookingDate) > 2 ",nativeQuery = true)
	public ArrayList<String> findDisabledDays(String btype); 
//
//	@Query(value = "select * from booking where username=?1 order by id desc",nativeQuery = true)
//    public List<BookingVo> findByUsername(String username);
	
	@Query(value = "SELECT * FROM booking WHERE bookingDate < ?",nativeQuery = true)
	public List<BookingVo> findPastBooking(LocalDate now);
	
	@Query(value = "SELECT * FROM booking WHERE bookingDate >= ?",nativeQuery = true)
	public List<BookingVo> findBookingList(LocalDate now);

	public BookingVo findByBno(Long bno);
	
}
