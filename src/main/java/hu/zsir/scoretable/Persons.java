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

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A model class for Persons, which is stored in score table.
 * 
 * @author Feco
 */
@XmlRootElement(name = "scoretable")
@XmlAccessorType(XmlAccessType.FIELD)
public class Persons {

    /**
     * List of persons, which is represents the players.
     */
    @XmlElementWrapper(name = "persons")
    @XmlElement(name = "person")
    private List<Person> persons;

    /**
     * Constructs a new Persons object.
     */
    public Persons() {
    }

    /**
     * Constructs a new Persons object with the specified list of persons.
     * 
     * @param persons the specified list of persons
     */
    public Persons(List<Person> persons) {
        this.persons = persons;
    }

    /**
     * Gets the persons.
     * 
     * @return the list of persons
     */
    public List<Person> getPersons() {
        return persons;
    }

    /**
     * Sets the persons with the specified list of persons.
     * 
     * @param persons the specified list of persons
     */
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
    
    /**
     * Adds the specified person to the list of persons.
     * 
     * @param person the specified person
     */
    public void addPerson(Person person) {
        persons.add(person);
    }

}
