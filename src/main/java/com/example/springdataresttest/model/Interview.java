package com.example.springdataresttest.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Interview extends BaseEntity{


    private boolean isActive;
    private String day;
    private Integer time;
    private String type;




}
