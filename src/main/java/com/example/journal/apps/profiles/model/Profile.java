package com.example.journal.apps.profiles.model;

import com.example.journal.apps.common.BaseEntity;
import com.example.journal.apps.users.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "profiles")
public class Profile extends BaseEntity {

    private Integer age;

    private String city;

    private String country;

    private String bio;

    @Column(length = 15)
    private String phone;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
