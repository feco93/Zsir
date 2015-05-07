/* 
 * Copyright (C) 2015 Feco
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
 * Model class for game.
 *
 * @author Feco
 */
public class Game {

    /**
     * Player A of the game.
     */
    private final Player playerA;
    /**
     * Player B of the game.
     */
    private final Player playerB;
    /**
     * Table of the game.
     */
    private final Table table;
    /**
     * Deck of the game.
     */
    private final Deck deck;
    /**
     * The current player of the game.
     */
    private Player currentplayer;
    /**
     * The next player of the game.
     */
    private Player nextplayer;
    /**
     * The list of available operators.
     */
    private final List<Operator> operators = Arrays.asList(BeatOperator.getBeatoperator(), CallOperator.getCallOperator(), CheckOperator.getCheckoperator());

    /**
     * Constructs a new Game object.
     */
    public Game() {
        deck = new Deck();
        playerA = new Human();
        playerB = new Computer();
        table = new Table();currentplayer = playerA;
        nextplayer = playerB;
    }

    /**
     * Starts the game.
     */
    public void start() {        
        DrawOperator.getDrawoperator().apply(this);
    }

    /**
     * Indicates whether the game is over.
     *
     * @return true if the game is over
     */
    public boolean isGoal() {
        return deck.isEmpty() && playerA.cards.isEmpty() && playerB.cards.isEmpty() && table.getCards().isEmpty();
    }

    /**
     * Swaps the players.
     */
    public void swapPlayers() {
        Player temp = currentplayer;
        currentplayer = nextplayer;
        nextplayer = temp;
    }

    /**
     * Gets the table.
     *
     * @return the table object of the game
     */
    public Table getTable() {
        return table;
    }

    /**
     * Gets the deck.
     *
     * @return the deck object of the game
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Gets the current player.
     *
     * @return the current player
     */
    public Player getCurrentplayer() {
        return currentplayer;
    }

    /**
     * Gets the next player.
     *
     * @return the next player
     */
    public Player getNextplayer() {
        return nextplayer;
    }

    /**
     * Gets the player A.
     *
     * @return player A
     */
    public Player getPlayerA() {
        return playerA;
    }

    /**
     * Gets the player B.
     *
     * @return player B
     */
    public Player getPlayerB() {
        return playerB;
    }

    /**
     * Execute the next turn in this game.
     * 
     * @return true if the game is not over
     */
    public boolean nextLoop() {
        if (!isGoal()) {
            List<Operator> applicableOperators = new ArrayList<>();
            System.out.println("-------------------");
            for (Operator op : operators) {
                if (op.isApplicable(this)) {
                    applicableOperators.add(op);
                    System.out.println(op.toString());
                }
            }
            if (applicableOperators.size() == 1) {
                if (applicableOperators.get(0) instanceof CallOperator) {
                    getCurrentplayer().chooseCard(this);
                } else {
                    applicableOperators.get(0).apply(this);
                }
            } else {
                getCurrentplayer().chooseOperator(this);
            }
            return true;
        }
        return false;
    }
}
