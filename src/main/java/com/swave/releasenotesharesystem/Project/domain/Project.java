package com.swave.releasenotesharesystem.Project.domain;

import com.swave.releasenotesharesystem.ReleaseNote.domain.ReleaseNote;
import com.swave.releasenotesharesystem.User.domain.UserInProject;
import jdk.jfr.Timestamp;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//tosting 에러가 나면 @data를 게터세터로 바꾼다

@Entity
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;
    @Column(name = "project_name")
    private String name;
    @Column(columnDefinition = "TEXT", name = "description")
    private String description;
    @Column(name = "create_date") @Timestamp // 자동 생성 yyyy-mm-dd
    private Date createDate;

    // Project 와 userInProject mapping
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @Column(name = "user_in_project_id")
    private List<UserInProject> userInProjectList;

    // project 와 release note mapping
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @Column(name = "release_note_id")
    private List<ReleaseNote> releaseNoteList;


    @Builder
    public Project(Long id,String name, String description, Date createDate){
        this.id = id;
        this.name=name;
        this.description=description;
        this.createDate=createDate;
    }



}
