package com.corporation.model;

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
    @Column(name = "authority")
    private String userRole;

    @OneToMany(mappedBy = "authority")
    private List<User> user;
}
