/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mgc.petsmanager.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.mgc.petsmanager.model.Gender;
import org.mgc.petsmanager.model.Pet;

/**
 *
 * @author patytux
 */
public class PetRepository implements IPetRepository {

    private final List<Pet> pets;

    public PetRepository() {
        this.pets = new ArrayList<>();
    }

    @Override
    public Pet insert(Pet pet) {
        if (pet != null) {
            pet.setId(this.pets.size() + 1);
            this.pets.add(pet);
        }
        return pet;
    }

    @Override
    public List<Pet> getPets() {
        return this.pets;
    }

    @Override
    public Pet getPet(long id) {
        for (Pet pet : this.pets) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Pet pet) {
        return this.pets.remove(pet);
    }

    @Override
    public List<Pet> getPetsByName(String name) {
        List<Pet> petsSearched = new ArrayList<>();
        for (Pet pet : this.pets) {
            if (pet.getName().toLowerCase().contains(name.toLowerCase())) {
                petsSearched.add(pet);
            }
        }
        return petsSearched;
    }

    @Override
    public List<Pet> getPetsByType(String type) {
        List<Pet> petsSearched = new ArrayList<>();
        for (Pet pet : this.pets) {
            if (pet.getType().toLowerCase().equals(type.toLowerCase())) {
                petsSearched.add(pet);
            }
        }
        return sortByLastUpdate(petsSearched);
    }

    @Override
    public List<Pet> getPetsByGenderAndType(Gender gender, String type) {
        List<Pet> petsSearched = new ArrayList<>();
        for (Pet pet : this.pets) {
            if (pet.getType().toLowerCase().equals(type.toLowerCase()) && pet.getGender().equals(gender)) {
                petsSearched.add(pet);
            }
        }
        return sortByLastUpdate(petsSearched);
    }

    private List<Pet> sortByLastUpdate(List<Pet> pets) {
        Collections.sort(pets, new Comparator<Pet>() {
            @Override
            public int compare(Pet pet1, Pet pet2) {
                return pet2.getLastUpdate().compareTo(pet1.getLastUpdate());
            }

        });
        return pets;
    }

}
