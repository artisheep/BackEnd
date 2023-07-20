package com.swave.urnr.user.responsedto;

import com.swave.urnr.releasenote.domain.Comment;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;


@ApiModel(value = "유저 데이터 응답 DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; // unsigend int
    @Column(name = "email")
    private String email; // @ 이메일 유효성 검사
    @Column(name = "password")
    private String password;
    @Column(name = "department")
    private String department;
    @Column(name = "username")
    private String username;
    @Column(name = "provider")
    private String provider;

    @Column(name = "is_deleted")
    private boolean isDeleted = Boolean.FALSE;




}
