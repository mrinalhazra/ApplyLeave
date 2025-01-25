package com.CodingNinjas.LeaveXpress.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LeaveDto {
    String type;

    String startDate;

    String endDate;

    String description;
}
