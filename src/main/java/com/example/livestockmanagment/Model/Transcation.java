package com.example.livestockmanagment.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transcation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "selleId can not be null")
    private Integer selleId;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "buyerId can not be null")
    private Integer buyerId;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "livestockId can not be null")
    private Integer livestockId;

    @Column(columnDefinition = "date not null")
    private LocalDate date;

    @Column(columnDefinition = "varchar(8) check (status='Pending' or status='Complete')")
    @Pattern(regexp = "^(Pending|Complete)$", message = "status  must be either 'Pending' or 'Complete'")
    private String status;
}
