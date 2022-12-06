package com.spring.sns.domain.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserEntity {

    @Id
    private Long id;

    @Column(name = "user_name")
    private String userName;
}
