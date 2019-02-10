/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mgc.petsmanager.main;

import java.util.List;
import org.mgc.petsmanager.model.Gender;

import org.mgc.petsmanager.model.Pet;
import org.mgc.petsmanager.repository.IPetRepository;
import org.mgc.petsmanager.repository.PetRepository;
import org.mgc.petsmanager.service.PetService;
import org.mgc.petsmanager.util.Utils;

/**
 *
 * @author patytux
 */
public class Main {

    public static void main(String[] args) {
        IPetRepository petRepository = new PetRepository();
        PetService petService = new PetService(petRepository);
        if (args.length > 0) {
            loadFromCSV(args[0], petService);
            System.out.println("-------------");
            Pet petToDelete = new Pet();
            petToDelete.setId(19);
            petToDelete.setType("DOG");
            petToDelete.setName("Lucas");
            petToDelete.setGender(Gender.M);
            petService.delete(petToDelete);
            petService.printPets();

        } else {
            System.out.println("Invalid arguments");
        }
        System.out.println("-------------------End-----------------");
    }

    private static void loadFromCSV(String csvPath, PetService petService) {
        List<Pet> pets = Utils.readCSV(csvPath);
        for (Pet pet : pets) {
            petService.save(pet);
        }
        petService.printPets();
    }
}
