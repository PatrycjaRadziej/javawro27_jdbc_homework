package com.javawro27.jdbc.homework;

import com.javawro27.jdbc.homework.model.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDao {
    private MysqlConnection mysqlConnection;

    public PetDao() {
        this.mysqlConnection = new MysqlConnection();
    }

    public void addToDatabase(Pet pet) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = mysqlConnection.getConnection();
            statement = connection.prepareStatement(PetTableQueries.INSERT_PET_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pet.getName());
            statement.setInt(2, pet.getAge());
            statement.setString(3, pet.getOwnerName());
            statement.setDouble(4, pet.getWeight());
            statement.setBoolean(5, pet.isPureRace());


            int affectedRecords = statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) { // jeśli jest rekord
                Long generatedKey = generatedKeys.getLong(1);
                pet.setId(generatedKey);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    if (statement != null) {
                        statement.close();
                    }
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Błąd zamknięcia połączenia");
            }
        }
    }

    public List<Pet> getAllPets () {
        List<Pet> list = new ArrayList<>();

        try(Connection connection = mysqlConnection.getConnection()){

            try(PreparedStatement statement = connection.prepareStatement(PetTableQueries.SELECT_ALL_PETS_QUERY)){
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()){
                    Pet newPet = Pet.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .age(resultSet.getInt(3))
                            .ownerName(resultSet.getString(4))
                            .weight(resultSet.getDouble(5))
                            .pureRace(resultSet.getBoolean(6))
                            .build();

                    list.add(newPet);

                }
            }


        } catch (SQLException e){
            e.printStackTrace();
        }
        return  list;
    }

    public void updatePet(Pet pet) {
        if (pet.getId() == null) {
            System.err.println("Can't edit student without id.");
            return;
        }
        try (Connection connection = mysqlConnection.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(PetTableQueries.UPDATE_PET_QUERY)) {
                statement.setString(1, pet.getName());
                statement.setInt(2, pet.getAge());
                statement.setString(3, pet.getOwnerName());
                statement.setDouble(4, pet.getWeight());
                statement.setBoolean(5, pet.isPureRace());

                // uzupełnienie klauzuli where
                statement.setLong(6, pet.getId());

                int affectedRecords = statement.executeUpdate();
                System.out.println("Edytowanych rekordów: " + affectedRecords);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

        public void deletePet (Pet pet){
            if (pet.getId() == null) {
                System.err.println("Can't edit student without id.");
                return;
            }
            deletePet(pet.getId());
        }

    public void deletePet(Long petId) {
        try (Connection connection = mysqlConnection.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(PetTableQueries.DELETE_PET_QUERY)) {
                // uzupełnienie klauzuli where
                statement.setLong(1, petId);

                int affectedRecords = statement.executeUpdate();
                System.out.println("Edytowanych rekordów: " + affectedRecords);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
