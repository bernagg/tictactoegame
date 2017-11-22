/**
 * IUi.java
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
package view;

import java.util.List;

import model.Player;
import model.Record;

public interface IUi {

    public void printTitle();

    public int askDimBoard();

    public int askDimLine();

    public int askTokenX();

    public int askTokenY();

    public void printPlayerTurn(Player player);

    public void printWinner(Player winner);

    public void printBoard(int[][] board);

    public void showRecords(String recordsName, List<Record> records);

}
