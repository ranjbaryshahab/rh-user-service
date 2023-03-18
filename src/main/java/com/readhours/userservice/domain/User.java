package com.readhours.userservice.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false)
    private UUID id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    @Column(length = 36, columnDefinition = "varchar(36)")
    private String password;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @CreationTimestamp
    private Timestamp updatedAt;

    @CreationTimestamp
    private Timestamp deletedAt;
}
