package com.corporation.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * @author Bleschunov Dmitry
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "public")
public class User {

    public static final User NULL_USER = new User(0, "", "", "", "");

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "about_me")
    private String aboutMe;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_projects",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id", referencedColumnName = "id") }
    )
    private Set<Project> projects;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_skills",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") }
    )
    private Set<Skill> skills;

    public User(int id, String nickname, String email, String password, String aboutMe) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.aboutMe = aboutMe;
    }
}
