/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.zsir;

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
