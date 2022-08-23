package com.keduit.project02.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="user")
@Entity
@Data
public class User {
	@Id
    @Column(nullable = false, length = 100, unique = true)
    private String username; // 아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;
    
    @Column(nullable = false, length = 50)
    private String email;
    
    @Column(nullable = false, length = 20)
    private String userTel;
    
    @Column(nullable = true, length = 50)
    private String dogName;

    @Column(nullable = true, length = 50)
    private String dogType;
    
//	    @ColumnDefault("user")
    // DB는 RoleType이라는게 없어서, String임을 알려준다.
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 쓰면, ADMIN,USER로 형이 강제가 된다.

    private String oauth; 



    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate; // 날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDate.now();
    }
}