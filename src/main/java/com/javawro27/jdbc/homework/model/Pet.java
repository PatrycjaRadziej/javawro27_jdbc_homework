package com.javawro27.jdbc.homework.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private Long id;
    private String name;
    private int age;
    private String ownerName;
    private double weight;
    private boolean pureRace;



}
