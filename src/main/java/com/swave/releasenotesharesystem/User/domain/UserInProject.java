package com.swave.releasenotesharesystem.User.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swave.releasenotesharesystem.Project.domain.Project;
import com.swave.releasenotesharesystem.Util.type.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
//@Data
@NoArgsConstructor
public class UserInProject {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_in_project_id")
    private Long id;
    @Column(name = "role")
    private UserRole role; // 구독자, 개발자, 관리자

    // User 와 mapping
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    // Project 와 mapping
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Builder
    public UserInProject(UserRole role, User user, Project project){
        this.role=role;
        this.user=user;
        this.project=project;
    }


}
