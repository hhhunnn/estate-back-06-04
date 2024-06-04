package com.estate.back.entity;

import com.estate.back.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Estate 데이터베이스의 User 테이블과 매핑되는 Entity 클래스
@Entity(name="user")
@Table(name="user") // 클래스명과 테이블명이 다르기 때문에 name 지정 / 클래스명이 User이면 지정안해도 됨
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private String userId;
    private String userPassword;
    private String userEmail;
    private String userRole;
    private String joinPath;

    // AuthServiceImpementation에서 하면 코드가 길어지기때문에 생성자를 따로 만들어줌
    public UserEntity (SignUpRequestDto dto) {

        this.userId = dto.getUserId();
        this.userPassword = dto.getUserPassword();
        this.userEmail = dto.getUserEmail();
        this.userRole = "ROLE_USER";
        this.joinPath = "HOME";

    }
}
