/**
 * Board.java
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

public class Board {
    private int[][] board;
    private int dimBoard;
    private int dimLine;
    private HashSet<Player> players;

    public Board(int dimBoard, int dimLine) {
	this.dimBoard = dimBoard;
	this.dimLine = dimLine;
	this.board = new int[dimBoard][dimBoard];
	this.players = new HashSet<Player>();
    }

    public boolean isEmptySquare(int x, int y) {
	return this.board[x][y] == 0 ? true : false;
    }

    public void putToken(int player, int x, int y) {
	this.board[x][y] = player;
    }

    public void resetBoard() {
	this.board = new int[dimBoard][dimBoard];
    }

    public boolean playerWin(Player player) {
	int count = 0;
	int playerNum = player.getNum();
	for (int i = 0; i < dimBoard; i++) {
	    count = 0;
	    for (int j = 0; j < dimBoard; j++) {
		count = board[i][j] == playerNum ? count + 1 : 0;
		if (count == dimLine)
		    return true;
	    }
	}

	for (int j = 0; j < dimBoard; j++) {
	    count = 0;
	    for (int i = 0; i < dimBoard; i++) {
		count = board[i][j] == playerNum ? count + 1 : 0;
		if (count == dimLine)
		    return true;
	    }
	}

	for (int k = 0; k < dimBoard; k++) {
	    count = 0;
	    for (int i = 0; i < dimBoard - k; i++) {
		count = board[i][i + k] == playerNum ? count + 1 : 0;
		if (count == dimLine)
		    return true;
	    }
	}

	for (int k = 1; k < dimBoard; k++) {
	    count = 0;
	    for (int i = k; i < dimBoard; i++) {
		count = board[i][i - k] == playerNum ? count + 1 : 0;
		if (count == dimLine)
		    return true;

	    }
	}

	for (int k = 0; k < dimBoard; k++) {
	    count = 0;
	    for (int i = 0; i <= k; i++) {
		count = board[i][k - i] == playerNum ? count + 1 : 0;
		if (count == dimLine)
		    return true;
	    }
	}

	for (int k = 0; k < dimBoard; k++) {
	    count = 0;
	    for (int i = 1; k + i < dimBoard; i++) {
		count = board[k + i][dimBoard - i] == playerNum ? count + 1 : 0;
		if (count == dimLine)
		    return true;
	    }
	}

	return false;
    }

    public int[][] getBoard() {
	return board;
    }

    public void setPlayers(String player1, String player2) {
	this.players.clear();
	this.players.add(new Player(1, player1));
	this.players.add(new Player(2, player2));
    }

    public Player getPlayer(int num) {
	for (Player p : players) {
	    if (p.getNum() == num)
		return p;
	}
	return null;
    }

}
