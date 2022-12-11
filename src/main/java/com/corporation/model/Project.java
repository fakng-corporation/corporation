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
@Table(name = "project")
public class Project {

    public static final Project NULL_PROJECT = new Project(0, "", "");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projects")
//    private Set<User> users;

    public Project(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
