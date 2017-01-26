package com.childish.superpower;

import io.ebean.Model;

import javax.persistence.Entity;

/**
 * Created by SONATA on 1/19/2017.
 */
@Entity
public class Tournament extends Model {
    private String tournamentName;
    private String tournamentRegion;

    public Tournament(String tName, String tRegion) {
        tournamentName = tName;
        tournamentRegion = tRegion;
    }

    public Tournament() {
        tournamentName = "test";
        tournamentRegion = "test";
    }
}
