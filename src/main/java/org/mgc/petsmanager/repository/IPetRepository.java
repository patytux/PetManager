/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mgc.petsmanager.repository;

import java.util.List;

import org.mgc.petsmanager.model.Gender;
import org.mgc.petsmanager.model.Pet;

/**
 *
 * @author patytux
 */
public interface IPetRepository {

    Pet insert(Pet pet);

    Pet getPet(long id);

    List<Pet> getPets();

    boolean delete(Pet pet);

    List<Pet> getPetsByName(String name);

    List<Pet> getPetsByType(String type);

    List<Pet> getPetsByGenderAndType(Gender gender, String type);

}
