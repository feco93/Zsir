/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game.model;

import hu.zsir.game.operators.BeatOperator;
import hu.zsir.game.operators.DrawOperator;
import hu.zsir.game.operators.CallOperator;
import hu.zsir.game.operators.CheckOperator;
import hu.zsir.game.operators.Operator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Feco
 */
public class Game {

    private final Player playerA;
    private final Player playerB;
    private final Table table;
    private final Deck deck;
    private Player currentplayer;
    private Player nextplayer;
    public final List<Operator> operators;

    public Game() {
        deck = new Deck();
        playerA = new Player(false);
        playerB = new Player(true);
        currentplayer = playerA;
        nextplayer = playerB;
        table = new Table();
        operators = Arrays.asList(BeatOperator.getBeatoperator(), CallOperator.getCallOperator(), CheckOperator.getCheckoperator());
    }

    public void start() {
        DrawOperator.getDrawoperator().apply(this);
    }

    public boolean isGoal() {
        return deck.isEmpty() && playerA.cards.isEmpty() && playerB.cards.isEmpty() && table.getCards().isEmpty();
    }

    public void swapPlayers() {
        Player temp = currentplayer;
        currentplayer = nextplayer;
        nextplayer = temp;
    }

    public Table getTable() {
        return table;
    }

    public Deck getDeck() {
        return deck;
    }

    public Player getCurrentplayer() {
        return currentplayer;
    }

    public Player getNextplayer() {
        return nextplayer;
    }

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public boolean nextLoop() {
        if (!isGoal()) {
            System.out.println("-------------------");
            List<Operator> applicable = new ArrayList<>();
            for (Operator op : operators) {
                if (op.isApplicable(this)) {
                    applicable.add(op);
                    System.out.println(op.toString());
                }
            }
            if (applicable.size() == 1) {
                if (applicable.get(0) instanceof CallOperator) {
                    getCurrentplayer().chooseCard(this);
                } else {
                    applicable.get(0).apply(this);
                }
            } else {
                getCurrentplayer().chooseOperator(this);
            }
            return true;
        }
        return false;
    }
}
