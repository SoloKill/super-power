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
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Enter a command:");
            String command = scanner.nextLine();
            System.out.println();


            if (command.equalsIgnoreCase("add")) {
                //get player info from user
                System.out.println("Enter player name:");
                String playerName = scanner.nextLine().toUpperCase();
                System.out.println();
                System.out.println("Enter player tag:");
                String playerTag = scanner.nextLine().toUpperCase();

                //check whether player/tag already exist within database
                if(Player.finder.query().where().eq("player_name", playerName).findUnique() == null || Player.finder.query().where().eq("player_tag", playerTag).findUnique() == null) {
                    System.out.println("Player already exists!");
                } else {
                    //add user to db
                    tryLoadEnhancer();
                    Player player = new Player(playerTag, playerName);
                    player.save();
                }

            } else if (command.equalsIgnoreCase("find")) {
                Player foundPlayer = null;
                //in future have specify between match and player, if player specified have specify between tag and name
                System.out.println("Do you want to search by name or by tag? :");
                //determine whether user wants to find by name or by playertag
                if(scanner.nextLine().toUpperCase().equals("NAME")) {
                    System.out.println("Enter player name:");
                    String playerName = scanner.nextLine().toUpperCase();
                    System.out.println();
                    foundPlayer = Player.finder.query().where().eq("player_name", playerName).findUnique();
                } else if(scanner.nextLine().toUpperCase().equals("TAG")) {
                    System.out.println("Enter player tag:");
                    String playerTag = scanner.nextLine().toUpperCase();
                    System.out.println();
                    foundPlayer = Player.finder.query().where().eq("player_tag", playerTag).findUnique();
                }
                //make sure a player was found
                if(foundPlayer == null) {
                    System.out.println("Player not found.");
                } else {
                    System.out.println(foundPlayer);
                }

            } else {
                System.out.println("Command not recognized. Input command.");
            }
        }

        /*tryLoadEnhancer();
        Player player = new Player();
        player.save();
        Query query = Player.finder.query();
        Player playerFromDB = (Player)query.where().eq("id",1).findUnique();
        System.out.println(playerFromDB.getPlayerName());*/
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
