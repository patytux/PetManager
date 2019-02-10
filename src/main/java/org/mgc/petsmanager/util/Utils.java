/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mgc.petsmanager.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mgc.petsmanager.model.Gender;
import org.mgc.petsmanager.model.Pet;

/**
 *
 * @author patytux
 */
public class Utils {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd-hhmmss");

    public static List<Pet> readCSV(String csvFile) {
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        List<Pet> pets = new ArrayList<>();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] petLine = line.split(cvsSplitBy);
                Pet pet = new Pet();
                pet.setType(petLine[0]);
                pet.setName(petLine[1]);
                pet.setGender(Gender.valueOf(petLine[2]));
                pet.setLastUpdate(DATE_FORMAT.parse(petLine[3]));
                pets.add(pet);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, "Invalid Gender", ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, "File not found", ex);
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, "Error reading CSV", ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return pets;
    }
}
