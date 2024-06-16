package com.smartjob.createuser.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    private String password;
    @CreationTimestamp
    private Timestamp created;
    @UpdateTimestamp
    private Timestamp modified;
    @UpdateTimestamp
    private Timestamp lastLogin;
    @Builder.Default
    private UUID token = UUID.randomUUID();
    @Builder.Default
    private Boolean isActive = true;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Phone> phones;

}
