package com.corporation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post_statistics")
public class PostStatistics {
    @Id
    @JoinColumn(name = "post_id")
    private Long postId;

    @Column(name = "likes")
    private long likes;

    @Column(name = "views")
    private long views;

    @Column(name = "comment_amount")
    private long commentAmount;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    private Post post;
}
