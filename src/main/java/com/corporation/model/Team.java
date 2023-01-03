package com.corporation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "team")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", length = 128, nullable = false)
    private String title;
    @Column(name = "description", length = 4096)
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;
}
