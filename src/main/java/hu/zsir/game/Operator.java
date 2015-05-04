/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir.game;

/**
 *
 * @author Feco
 */
interface Operator {

    public boolean isApplicable(Game game);

    public void apply(Game game);
}
