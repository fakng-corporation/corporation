package com.corporation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Bleschunov Dmitry
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "skill")
public class Skill {

    public static final Skill NULL_SKILL = new Skill(0, "");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

//    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "skills")
//    private Set<User> users;

    public Skill(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
