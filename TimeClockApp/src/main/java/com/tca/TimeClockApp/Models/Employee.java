package com.tca.TimeClockApp.Models;

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
    private String emp_custom_id;

    private String first_name;

    private String last_name;
}
