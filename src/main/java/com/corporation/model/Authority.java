package com.corporation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@Data
@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @Column(name = "id")
    private short id;

    @Column(name = "authority")
    private String authority;

    @JsonIgnore
    @OneToMany(mappedBy = "authority")
    private List<User> user;
}
