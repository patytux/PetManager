/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mgc.petsmanager.service;

import java.util.List;
import java.util.Map;

import org.mgc.petsmanager.model.Gender;
import org.mgc.petsmanager.model.Pet;
import org.mgc.petsmanager.repository.IPetRepository;

/**
 *
 * @author patytux
 */
public class PetService {

    private final static String NAME_CRITERIA = "name";

    private final static String TYPE_CRITERIA = "type";

    private final static String GENDER_CRITERIA = "gender";

    private final static String FEMALE = "female";

    private final static String MALE = "male";

    private final IPetRepository iPetRepository;

    public PetService(IPetRepository iPetRepository) {
        this.iPetRepository = iPetRepository;
    }

    public Pet save(Pet pet) {
        return (this.iPetRepository.getPet(pet.getId()) == null)
                ? this.iPetRepository.insert(pet) : null;
    }

    public boolean delete(Pet pet) {
        return this.iPetRepository.delete(pet);
    }

    public List<Pet> getPetsByCriteria(Map<String, String> criteria) {
        if (!this.validCriteria(criteria)) {
            throw new IllegalArgumentException("Invalid criteria");
        }
        if (criteria.size() == 1 && criteria.containsKey(NAME_CRITERIA)) {
            return this.iPetRepository.getPetsByName(criteria.get(NAME_CRITERIA));
        }

        if (criteria.size() == 1 && criteria.containsKey(TYPE_CRITERIA)) {
            return this.iPetRepository.getPetsByType(criteria.get(TYPE_CRITERIA));
        }
        if (criteria.size() == 2 && criteria.containsKey(TYPE_CRITERIA)
                && criteria.containsKey(GENDER_CRITERIA)
                && (criteria.get(GENDER_CRITERIA).toLowerCase().equals(FEMALE)
                || criteria.get(GENDER_CRITERIA).toLowerCase().equals(MALE))) {
            Gender gender = criteria.get(GENDER_CRITERIA).equalsIgnoreCase(FEMALE) ? Gender.F : Gender.M;
            return this.iPetRepository.getPetsByGenderAndType(gender, criteria.get(TYPE_CRITERIA));
        }
        throw new IllegalArgumentException("Invalid criteria");
    }

    public static void printPet(Pet pet) {
        System.out.println("pet ID: " + pet.getId()
                + " - Type: " + pet.getType()
                + " - Name: " + pet.getName()
                + " - Gender: " + pet.getGender()
                + " - Last Update: " + pet.getLastUpdate());
    }

    public void printPets() {
        printPets(this.iPetRepository.getPets());
    }

    public static void printPets(List<Pet> petsToPrint) {
        for (Pet pet : petsToPrint) {
            printPet(pet);
        }
    }

    private boolean validCriteria(Map<String, String> criteria) {
        boolean valid = true;
        for (String key : criteria.keySet()) {
            if (!(key.equals(NAME_CRITERIA) || key.equals(GENDER_CRITERIA)
                    || key.equals(TYPE_CRITERIA))) {
                valid = false;
            }
        }
        return valid;
    }
}
