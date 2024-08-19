package com.example.livestockmanagment.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "name can not be empty")
    private String name;

    @Column(columnDefinition = "varchar(20) not null unique")
    @NotNull(message = "email can not be null")
    @Email(message = "must be valid email ")
    private String email;

    @Column(columnDefinition = "varchar(20) not null unique")
    @NotNull(message = "username can not be null")
    @Length(min = 4,message = "username Length must be more than 4")
    private String username;

    @Column(columnDefinition = "varchar(20) not null")
    @NotNull(message = "password can not be null")
    private String password;

}
