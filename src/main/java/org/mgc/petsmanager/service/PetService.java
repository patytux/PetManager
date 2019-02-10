/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mgc.petsmanager.service;

import java.util.List;

import org.mgc.petsmanager.model.Pet;
import org.mgc.petsmanager.repository.IPetRepository;

/**
 *
 * @author patytux
 */
public class PetService {

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

    public static void printPet(Pet pet) {
        System.out.println("pet ID: " + pet.getId()
                + " Type: " + pet.getType()
                + " Name: " + pet.getName()
                + " Gender: " + pet.getGender()
                + " Last Update: " + pet.getLastUpdate());
    }

    public void printPets() {
        printPets(this.iPetRepository.getPets());
    }

    public static void printPets(List<Pet> petsToPrint) {
        for (Pet pet : petsToPrint) {
            printPet(pet);
        }
    }
}
