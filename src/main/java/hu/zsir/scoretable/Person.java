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

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Feco
 */
public class Person {

    private final StringProperty name;
    private final IntegerProperty score;
    private final StringProperty date;

    public Person(String name, Integer score, String date) {
        this.name = new SimpleStringProperty(this, "name", name);
        this.score = new SimpleIntegerProperty(this, "score", score);
        this.date = new SimpleStringProperty(this, "date", date);
    }

    public StringProperty getName() {
        return name;
    }
    
    public StringProperty nameProperty() {
          return name;
     }

    public IntegerProperty getScore() {
        return score;
    }
    
    public IntegerProperty scoreProperty() {
        return score;
    }

    public StringProperty getDate() {
        return date;
    }
    
    public StringProperty dateProperty() {
        return date;
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public void setScore(Integer score) {
        this.score.set(score);
    }
    
    public void setDate(String date) {
        this.date.set(date);
    }

}
