package com.childish.superpower;

import com.ea.agentloader.AgentLoader;
import io.ebean.Ebean;
import io.ebean.Query;

import java.util.Scanner;

/**
 * Created by SONATA on 1/19/2017.
 */
public class PlayerAdder {
    public static void main(String[] args) {

        System.out.println("Enter a command:");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();
        System.out.println();


        if(command.equalsIgnoreCase("add")) {
            //get player info from user
            System.out.println("Enter player name:");
            String playerName = scanner.next();
            System.out.println();
            System.out.println("Enter player tag:");
            String playerTag = scanner.next();

            //add user to db
            tryLoadEnhancer();
            Player player = new Player(playerTag, playerName);
            player.save();


        } else if(command.equalsIgnoreCase("find")) {
            //in future have specify between match and player, if player specified have specify between tag and name
            System.out.println("Enter player name (CASE SENSITIVE!):" );
            String playerName = scanner.next();
            Player foundPlayer = Player.finder.query().where().eq("player_name", playerName).findUnique();
            System.out.println(foundPlayer);

        }

        else {
            System.out.println("Command not recognized. Input command.");
        }

        tryLoadEnhancer();
        Player player = new Player();
        player.save();
        Query query = Player.finder.query();
        Player playerFromDB = (Player)query.where().eq("id",1).findUnique();
        System.out.println(playerFromDB.getPlayerName());
    }

    public static void tryLoadEnhancer() {
        try {
            AgentLoader.loadAgentClass(io.ebean.enhance.agent.Transformer.class.getName(), "");
        } catch (Exception e) {
            if (!(e instanceof ClassNotFoundException) && !(e instanceof ClassNotFoundException)) {
                System.out.println("Failed to load runtime instrumentation: " + e.getMessage());
                System.out.println("This message can be ignored if using a fatjar that was instrumented AOT");
            } else {
                System.out.println("Running with AOT instrumentation");
            }
        }
    }
}
