package com.tca.TimeClockApp.Models;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @NotNull
    private String emp_custom_id;

    @NotNull
    private String first_name;

    @NotNull
    private String last_name;
}
