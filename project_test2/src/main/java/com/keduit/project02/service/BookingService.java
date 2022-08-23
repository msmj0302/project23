package com.keduit.project02.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keduit.project02.domain.BookingVo;
import com.keduit.project02.domain.RoleType;
import com.keduit.project02.domain.User;
import com.keduit.project02.repository.BookingRepository;

@Service
public class BookingService {
	
	private int price = 30000;
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Transactional // 전체가 성공시 Commit, 실패시 Rollback - springframework
    public boolean insertBooking(BookingVo bookingVo) {
    	
    	String bookingDate = ""+bookingVo.getBookingDate();
    	ArrayList<String> disabledDayList = findDisabledDays(bookingVo.getBtype());
    	 for(int i=0; i<disabledDayList.size(); i++) {
    		 System.out.println("비활성화 날짜 : " + disabledDayList.get(i));
    		 System.out.println("예약하려는 날짜" + bookingVo.getBookingDate());
    		 if(disabledDayList.get(i).equals(bookingDate)) {
    			 return false;
    			 
    		 }
    	 }
    	 
    	 calculation(bookingVo);
    	 bookingVo.setPrice(price);
    	 bookingRepository.save(bookingVo);
    	 return true;
    	 
    	
    }
    
    @Transactional // 전체가 성공시 Commit, 실패시 Rollback - springframework
    public ArrayList<String> findDisabledDays(String btype) {
        
    	return bookingRepository.findDisabledDays(btype);
    	
    	
    }
    
    public void calculation(BookingVo bookingVo) {
    	
    	if(bookingVo.getDogSize().equals("중형견")) {
    		
    		price+=2000;
    		System.out.println(price);
    	}else if(bookingVo.getDogSize().equals("대형견")){
    		price+=3000;
    		System.out.println(price);
    	}
    		
    	
    	if(bookingVo.getPictureSize().equals("중형") ) {
    		
    		price+=2000;
    		System.out.println(price);
    	}else if(bookingVo.getPictureSize().equals("대형") ) {
    		price+=3000;
    		System.out.println(price);
    		
    	}
    	
    	if(bookingVo.getPeople().equals("5인 이상")) {
    		price+=10000;
    		System.out.println(price);
    	}
    }
    
//    public List<Integer> getCanBookingTime(String bType) {
//        int nowHour=utilservice.getNowHour();
//        List<BookingVo> alreadyTimes=getAlreadyTime(bType);
//        List<Integer> array=new ArrayList<>();
//        try {
//           if(alreadyTimes.isEmpty()){
//            for(int i=openTime;i<=closeTime;i++){
//                if(i>nowHour){
//                    array.add(i);  
//                }
//            }
//           }else{
//            for(int i=openTime;i<=closeTime;i++){
//                for(int j=0;j<alreadyTimes.size();j++){
//                    if(i>nowHour){
//                        LocalDateTime localDateTime=LocalDateTime.now().withHour(i).withMinute(0).withSecond(0).withNano(0);
//                        if(utilservice.compareDate(alreadyTimes.get(j).getBookingDateTime(), localDateTime)){//utilservice.compareDate(alreadyTimes.get(ii).getReservationdatetime())==false){
//                            System.out.println("불가능 시간 "+i);
//                            break;
//                        }else if(j==alreadyTimes.size()-1){
//                            System.out.println("가능한 시간 "+i);
//                            array.add(i);    
//                        } 
//                    }
//                } 
//            }
//           }
//           System.out.println(array);
//            return array;
//        } catch (Exception e) {
//            throw new RuntimeException("오류가 발생했습니다 잠시 후 다시시도 바랍니다");
//        }
//    }
    
//    @Transactional(rollbackFor = {Exception.class})
//    public JSONObject insertReservation(BookingDto bookingDto,String username,String uName,List<Integer>requestTime,HttpSession httpSession,String phone) {
//        try {
//            String bType= bookingDto.getBType();
//            String confirm=confrimTime(bType, requestTime);
//            if("true".equals(confirm)){
//                for(int i=0;i<requestTime.size();i++){
//                    Timestamp timestamp=utilservice.makeToTimestamp(requestTime.get(i));
//                    BookingVo bookingVo=new BookingVo(0,bookingDto.getBType(), username, uName, phone ,null, requestTime.get(i), timestamp,  bookingDto.getPrice());
//                    bookingRepository.save(bookingVo);
//                }
//                return utilservice.makeJson(responResultEnum.sucInsertReservation.getBool(), responResultEnum.sucInsertReservation.getMessege());  
//            }
//            failInsert(httpSession);
//            return utilservice.makeJson(responResultEnum.valueOf(confirm).getBool(), responResultEnum.valueOf(confirm).getMessege());
//            } catch (Exception e) {
//            e.printStackTrace();
//            failInsert(httpSession);
//            throw new RuntimeException("오류가 발생했습니다 잠시 후 다시시도 바랍니다");
//        }
//    }
    
//    private String confrimTime(String bType,List<Integer>requestTime){
//        List<BookingVo>alreadyTimes=bookingRepository.findByBType(bType);
//        int nowHour=utilservice.getNowHour();
//            for(int i=0;i<requestTime.size();i++){
//                System.out.println("요청시간"+requestTime.get(i)+"현재시간"+nowHour);
//                if(requestTime.get(i)<=nowHour){
//                    System.out.println("불가이유:이전시간 요청");
//                    return "beforeTime";
//                }
//                for(BookingVo r:alreadyTimes){
//                    LocalDateTime localDateTime=LocalDateTime.now().withHour(i).withMinute(0).withSecond(0).withNano(0);
//                    if(utilservice.compareDate(r.getBookingDateTime(), localDateTime)){
//                        System.out.println("불가이유: 중복시간 요청");
//                        return "alreadyTime";
//                    }
//                }
//            }
//            return "true";
//    }
    
//    private List<BookingVo> getAlreadyTime(String bType){
//        try {
//            return bookingRepository.findByBType(bType);
//        } catch (Exception e) {
//           throw new RuntimeException("오류가 발생했습니다 잠시 후 다시시도 바랍니다");
//        }
//    }
    
//    private void failInsert(HttpSession httpSession) {
//        utilservice.emthySession(httpSession);
//    }

//	public JSONObject deleteReservation(String username, int bno, String phone) {
//		System.out.println(phone+" 예약취소");
//        try {
//        BookingVo bookingVo=confrimReservation(bno,username);
//          if(bookingVo!=null){
//                bookingRepository.deleteById(bno);
//                return utilservice.makeJson(responResultEnum.sucDeleteRerservation.getBool(), responResultEnum.sucDeleteRerservation.getMessege());
//          }
//          return utilservice.makeJson(responResultEnum.failDeleteReservation.getBool(), responResultEnum.failDeleteReservation.getMessege());
//        } catch (Exception e) {
//           e.printStackTrace();
//           throw new RuntimeException("deleteReservation에서 오류가 발생 했습니다");
//        }
//	}
	
//    @Transactional
//    public JSONObject updateReservation(String email,BookingDto bookingDto) {
//        BookingVo reservationvo=getReservationByBno(bookingDto.getBno());
//        try {
//            return utilservice.makeJson(responResultEnum.updateReservation.getBool(),responResultEnum.updateReservation.getMessege());
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("updateReservation 오류가 발생했습니다");
//        }
//    }
	
//    private BookingVo getReservationByBno(int bno){
//        return bookingRepository.findById(bno).orElseThrow(()-> new RuntimeException("존재 하지 않는 예약입니다"));
//    }
//    
//    private BookingVo confrimReservation(int bno,String username) {
//        BookingVo bookingVo=bookingRepository.findById(bno).orElseThrow(()->new RuntimeException("예약자와 취소자가 일치 하지 않습니다"));
//        if(bookingVo.getUsername().equals(username)){
//            return bookingVo;
//        }
//        return null;
//    }
}