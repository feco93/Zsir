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
package hu.zsir.scoretable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * A model class for Person.
 *
 * @author Feco
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    /**
     * The name of the person.
     */
    private String name;
    /**
     * The score of the person.
     */
    private Integer score;
    /**
     * The date of the game.
     */
    private String date;

    /**
     * Constructs a new Person object with the specified parameters.
     *
     * @param name the name of the person
     * @param score the score of the person
     * @param date the date of the game
     */
    public Person(String name, Integer score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }

    /**
     * Contructs a new Person object.
     */
    public Person() {
    }

    /**
     * Gets the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person with the specified value.
     *
     * @param name the specified name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the score of the person.
     *
     * @return the score of the person
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Sets the score with the specified value.
     *
     * @param score the specified score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * Gets the date of the game.
     *
     * @return the date of the game
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the game with the specified date.
     *
     * @param date the specified date
     */
    public void setDate(String date) {
        this.date = date;
    }

}
