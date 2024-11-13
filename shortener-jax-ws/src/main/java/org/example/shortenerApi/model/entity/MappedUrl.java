package org.example.shortenerApi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mapped-url", uniqueConstraints = @UniqueConstraint(columnNames = {"longUrl"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MappedUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short-url")
    private String shortUrl;

    @Column(name = "long-url")
    private String longUrl;
}
