package com.CodingNinjas.LeaveXpress.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "leave_model")
public class LeaveModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String type;

    String startDate;

    String endDate;

    String description;

    boolean isAccepted;

}
