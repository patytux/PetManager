/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mgc.petsmanager.repository;

import java.util.List;

import org.mgc.petsmanager.model.Pet;

/**
 *
 * @author patytux
 */
public interface IPetRepository {

    public Pet insert(Pet pet);

    public Pet getPet(long id);

    public List<Pet> getPets();

    public boolean delete(Pet pet);

}
