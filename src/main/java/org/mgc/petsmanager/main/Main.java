/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mgc.petsmanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (args.length > 1 && args.length < 4) {
            loadFromCSV(args[0], petService);
            try {
                Map<String, String> criteria = extractCriteria(args);
                PetService.printPets(petService.getPetsByCriteria(criteria));
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid arguments");
            }
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
    }

    private static Map extractCriteria(String[] args) {
        Map<String, String> criteria = new HashMap();
        for (int i = 1; i < args.length; i++) {
            String[] values = args[i].split("=");
            if (values.length == 2) {
                criteria.put(values[0], values[1]);
            } else {
                throw new IllegalArgumentException("Invalid criteria");
            }
        }
        return criteria;
    }
}
