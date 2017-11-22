/**
 * Player.java
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

import java.util.HashSet;
import java.util.Set;

public class Player {

    private int num;
    private String name;

    private static Set<String> fakePlayersNames = fakePlayersNamesSet();

    public Player(int num, String name) {
	this.num = num;
	this.name = name;
    }

    public static Set<String> fakePlayersNamesSet() {
	Set<String> s = new HashSet<String>();
	s.add("Player1");
	s.add("Player 1");
	s.add("Player2");
	s.add("Player 2");
	return s;
    }

    public int getNum() {
	return num;
    }

    public void setNum(int num) {
	this.num = num;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public boolean isReal() {
	if (fakePlayersNames.contains(this.name))
	    return false;
	return true;
    }

}
