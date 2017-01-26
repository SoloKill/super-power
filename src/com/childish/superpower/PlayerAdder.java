package com.childish.superpower;

import com.ea.agentloader.AgentLoader;
import io.ebean.Ebean;
import io.ebean.Query;

/**
 * Created by SONATA on 1/19/2017.
 */
public class PlayerAdder {
    public static void main(String[] args) {
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
