package com.swave.releasenotesharesystem.ReleaseNote.domain;

import com.swave.releasenotesharesystem.User.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.groovy.ast.expr.UnaryMinusExpression;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="TEXT", name = "comment_context")
    private String commentContext;

    @JoinColumn(name = "release_note_id")
    @ManyToOne
    private ReleaseNote releaseNote;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //todo : mention 필드 생성

    @Builder
    public Comment(String commentContext, ReleaseNote releaseNote, User user) {
        this.commentContext = commentContext;
        this.releaseNote = releaseNote;
        this.user = user;
    }
}
