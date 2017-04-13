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
@Table(name = "user_information")
public class UserInformation extends AbstractEntity{

    @OneToOne
    @JoinColumn(name = "user_id", updatable = false)
    @JsonBackReference
    private User user;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
