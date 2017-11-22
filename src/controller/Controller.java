/**
 * Controller.java
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

package controller;

import java.util.List;

import model.Board;
import model.Player;
import model.Record;
import model.Records;
import view.IUi;

public class Controller {

    private Board board;
    private IUi iui;
    private Records records;

    public Controller(IUi iui) {
	super();
	this.iui = iui;
	this.records = new Records();
	this.records.readData();
    }

    public void close() {
	this.records.writeData();
    }

    public void play(String player1, String player2) {
	iui.printTitle();
	int dimBoard = iui.askDimBoard();
	int dimLine = iui.askDimLine();
	board = new Board(dimBoard, dimLine);
	board.setPlayers(player1, player2);
	iui.printBoard(board.getBoard());
	int player = 1;
	boolean win1 = false, win2 = false;
	while (!win1 && !win2) {
	    playTurn(player);
	    player = player == 1 ? 2 : 1;
	    win1 = board.playerWin(board.getPlayer(1));
	    win2 = board.playerWin(board.getPlayer(2));
	}
	Player winner = board.getPlayer(win1 ? 1 : 2);
	if (winner.isReal()) {
	    records.plusPlayer(winner.getName());
	}
	iui.printWinner(winner);
	close();
    }

    public void playTurn(int player) {
	boolean isEmpty;
	do {
	    iui.printPlayerTurn(board.getPlayer(player));
	    int x = iui.askTokenX();
	    int y = iui.askTokenY();
	    isEmpty = board.isEmptySquare(x, y);
	    if (isEmpty)
		board.putToken(player, x, y);
	} while (!isEmpty);
	iui.printBoard(board.getBoard());
    }

    public int getWins(String player) {
	return records.getWins(player);
    }

    public void setPlayers(String player1, String player2) {
	if (board != null) {
	    board.setPlayers(player1, player2);
	}
    }

    public List<Record> getTop10() {
	List<Record> l = records.getListRecords();
	if (l.size() > 10) {
	    l = l.subList(0, 10);
	}
	return l;
    }
}
