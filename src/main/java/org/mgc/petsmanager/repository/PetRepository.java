/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mgc.petsmanager.repository;

import java.util.ArrayList;
import java.util.List;

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

}
