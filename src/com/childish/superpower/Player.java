package com.childish.superpower;

import io.ebean.Model;
import io.ebean.bean.EnhancedTransactional;
import io.ebean.Finder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

/**
 * Created by SONATA on 1/19/2017.
 */
@Entity
public class Player extends Model implements EnhancedTransactional{
    private String playerTag;
    private String playerName;
    private ArrayList<String> aliases;

    public static Finder<Long, Player> finder = new Finder<>(Player.class);


    @Id
    Long id;


    public Player(String tag, String realName) {
        playerName = realName;
        playerTag = tag;
        aliases = new ArrayList<String>();

    }


    public Player() {
        playerTag = "test";
        playerName = "test";
        aliases = new ArrayList<String>();

    }

    public String toString() {
        return "Name: " + playerName + " Tag: " + playerTag + "Aliases: " + aliases;
    }

    public boolean equals(Player toCompare) {
        if (getPlayerName().equals(toCompare.getPlayerName())) {
            return true;
        }
        return false;
    }

    public String getPlayerTag() {
        return playerTag;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList getAliases() {
        return aliases;
    }


}
