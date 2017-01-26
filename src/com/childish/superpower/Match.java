package com.childish.superpower;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.CreatedTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by SONATA on 1/19/2017.
 */

@Entity
public class Match extends Model{
    private Player player1;
    private Player player2;
    private Date dateOfMatch;
    private Tournament tournament;
    private int player1Wins;
    private int player2Wins;
    public static Finder<Long, Match> finder = new Finder(Match.class);

    public Match(Player winner, Player loser, int winnerWins, int loserWins, Date datePlayed, Tournament tourneyOfMatch) {
        player1 = winner;
        player1Wins = winnerWins;
        player2 = loser;
        player2Wins = loserWins;
        dateOfMatch = datePlayed;
        tournament = tourneyOfMatch;
    }

    public Player getWinner() {
        return player1;
    }

    public Player getLoser() {
        return player2;
    }

    public int getPlayer1Wins() {
        return player1Wins;
    }

    public int getPlayer2Wins() {
        return player2Wins;
    }

    public Date getDateOfMatch() {
        return dateOfMatch;
    }

    public Tournament getTournament() {
        return tournament;
    }


}
