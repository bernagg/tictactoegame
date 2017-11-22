/**
 * Record.java
 * 
 * Copyright 2014 Bernabé Gonzalez, Marc Sabaté, Joaquim Dalmau
 * 
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package model;

import java.util.Map.Entry;

/**
 * Record save the records of the game in a Entry Map
 * 
 * @author Bernabé Gonzalez, Marc Sabaté, Joaquim Dalmau
 * 
 */
public class Record implements Entry<String, Integer>, Comparable<Record> {
    private final String key;
    private int value;

    // Constructors
    public Record(String key) {
	this.key = key;
    }

    public Record(String key, int value) {
	this.key = key;
	this.value = value;
    }

    // Getters and Setters

    @Override
    public String getKey() {
	return this.key;
    }

    @Override
    public Integer getValue() {
	return this.value;
    }

    @Override
    public Integer setValue(Integer value) {
	int oldValue = this.value;
	this.value = value;
	return oldValue;
    }

    @Override
    public int compareTo(Record recordTwo) {
	return recordTwo.getValue().compareTo(this.getValue());
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((key == null) ? 0 : key.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Record other = (Record) obj;
	if (key == null) {
	    if (other.key != null)
		return false;
	} else if (!key.equals(other.key))
	    return false;
	return true;
    }

}
