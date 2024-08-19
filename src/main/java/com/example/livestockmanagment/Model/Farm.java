package com.example.livestockmanagment.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "name can not be empty")
    private String name;

    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "location can not be empty")
    private String location;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "sellerId can not be null")
    private Integer sellerId;

    @Column(columnDefinition = "int not null DEFAULT 0")
    @NotNull(message = "sellerId can not be null")
    private int stock;

    @Column(columnDefinition = "varchar(9) CHECK (status='Empty' or status='available')")
    @Pattern(regexp = "^(Empty|available)$", message = "status  must be either 'Empty' or 'available'")
    private String status;

    @NotNull(message = "capacity can not be null DEFAULT 10")
    private int capacity;

}
