package com.corporation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authority")
public class Authority {

    @Id
    @Column(name = "id")
    private short id;

    @Column(name = "authority")
    private String authority;

    @OneToMany(mappedBy = "authority")
    private List<User> user;
}
