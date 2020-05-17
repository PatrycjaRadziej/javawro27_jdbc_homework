package com.javawro27.jdbc.homework;

import com.javawro27.jdbc.homework.model.Pet;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//    PetDao dao = new PetDao();
//
//    Pet pet = Pet.builder()
//            .name("Joko")
//            .age(3)
//            .ownerName("Patrycja")
//            .weight(14.5)
//            .pureRace(false)
//            .build();
//
// //   dao.addToDatabase(pet);
//
//    List<Pet> petList = dao.getAllPets();
//
//        Pet pet2 = Pet.builder()
//                .name("Kuki")
//                .age(2)
//                .ownerName("Krzysztof")
//                .weight(21.5)
//                .pureRace(false)
//                .build();
//
//        //       dao.addToDatabase(pet2);
//
//        Pet pet3 = Pet.builder()
//                .name("Królik")
//                .age(5)
//                .ownerName("Patrycja")
//                .weight(2.5)
//                .pureRace(true)
//                .build();
//
////        dao.addToDatabase(pet3);

//        petList.forEach(System.out::println);



        PetDao dao = new PetDao();
        Scanner scanner = new Scanner(System.in);
        String komenda;

        do {
            System.out.println("Podaj komendę [add/list/delete/update/quit]");
            komenda = scanner.nextLine();

            if (komenda.equalsIgnoreCase("add")) {
                addPets(dao, scanner);
            }else if(komenda.equalsIgnoreCase("list")){
                listPets(dao);
            }else if(komenda.equalsIgnoreCase("delete")){
                deletePet(dao, scanner);
            }else if(komenda.equalsIgnoreCase("update")){
                updatePet(dao, scanner);
            }

        }while (!komenda.equalsIgnoreCase("quit"));

    }


    private static void addPets (PetDao dao, Scanner scanner){
        System.out.println("Podaj parametry: imie wiek imie_wlaściciela waga rasowy");
        String linia = scanner.nextLine();
        String [] slowa = linia.split(" ");
        Pet pet = Pet.builder()
                .name(slowa[0])
                .age(Integer.parseInt(slowa[1]))
                .ownerName(slowa[2])
                .weight(Double.parseDouble(slowa[3]))
                .pureRace(Boolean.parseBoolean(slowa[4]))
                .build();
        dao.addToDatabase(pet);
    }

    private static void listPets(PetDao dao) {
        System.out.println("Lista: ");

        dao.getAllPets().forEach(System.out::println);
    }

    private static void deletePet(PetDao dao, Scanner scanner) {
        System.out.println("Podaj parametry: Identyfikator");
        Long id = Long.valueOf(scanner.nextLine());

        dao.deletePet(id);
    }

    private static void updatePet(PetDao dao, Scanner scanner) {
        System.out.println("Podaj parametry: Iimie wiek imie_wlaściciela waga rasowy");
        String linia = scanner.nextLine();
        String[] slowa = linia.split(" ");

        System.out.println("Podaj parametry: Identyfikator");
        Long id = Long.valueOf(scanner.nextLine());

        Pet pet = Pet.builder()
                .name(slowa[0])
                .age(Integer.parseInt(slowa[1]))
                .ownerName(slowa[2])
                .weight(Double.parseDouble(slowa[3]))
                .pureRace(Boolean.parseBoolean(slowa[4]))
                .build();

        dao.updatePet(pet);

    }
}
