package com.javawro27.jdbc.homework;

public interface PetTableQueries {
    String CREATE_DATABASE_QUERY = "create database if not exists `jwro27_students_jdbc_hw`;";
    String CREATE_TABLE_QUERY = "create table if not exists `pets` (\n" +
            "`id` INT AUTO_INCREMENT PRIMARY KEY,\n" +
            "`name` VARCHAR(30) NOT NULL,\n" +
            "`age` INT NOT NULL,\n" +
            "`owner_name` VARCHAR(30) NOT NULL,\n" +
            "`weight` DECIMAL(10, 2),\n" +
            "`pure_race` BOOLEAN NOT NULL);\n";

    String INSERT_PET_QUERY = "insert into `pets` (`name`, `age`, `owner_name`, `weight`, `pure_race`) values ( ?, ?, ?, ?, ?);";

    String SELECT_ALL_PETS_QUERY = "select * from 'pets';";

    String UPDATE_PET_QUERY = "update 'pets' set 'name' =?, 'age'=?, 'owner_name' =?, 'weight'=?, 'pure_race'=? where 'id' =?;";

    String DELETE_PET_QUERY = "delete from 'pets' where 'id' = ?;";
}
