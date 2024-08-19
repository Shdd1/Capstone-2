package com.example.livestockmanagment.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor@Entity
public class Livestock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "name can not be empty")
    private String name;

   // @Column(columnDefinition = "varchar(6) not null CHECK (livestockType='Camels' or livestockType='Horses')")
    @NotEmpty(message = "livestock Type can not be empty")
    @Pattern(regexp = "^(Camels|Horses)$", message = "livestock Type  must be either 'Camels' or 'Horses'")
    private String livestockType;

    //@Column(columnDefinition = "varchar(9) not null CHECK (status='Sold' or status='Available')")
    @Pattern(regexp = "^(Sold|Available)$", message = "livestock status  must be either 'Sold' or 'Available'")
    private String status;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "age can not be null")
    private int age;

    private double price;

    @Column(columnDefinition = "varchar(1) not null CHECK (gender='f' or gender='m')")
    @Pattern(regexp = "^(f|m)$")
    private String gender;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "weight can not be null")
    private int weight;

    @Column(columnDefinition = "varchar(3) not null unique")
    @NotNull(message = "microId can not be null")
    private String microId;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "farmId can not be null")
    private Integer farmId;

    @Column(columnDefinition = "int not null")
    private Integer owner;

//    @Column(columnDefinition = "date")
//    @NotNull(message = "HealthCertificate can not be null")
//    @JsonFormat(pattern="yyyy-MM-dd")
//    private LocalDate HealthCertificate;
//    LocalDate endDate = HealthCertificate.plusMonths(6);
}
