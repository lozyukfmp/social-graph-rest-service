package com.lozyukartem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
@Entity
@Table(name = "user_credentials")
public class UserCredentials extends AbstractEntity{

    @OneToOne
    @JoinColumn(name = "user_id", updatable = false)
    @JsonBackReference
    private User user;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
