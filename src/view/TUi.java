/**
 * TUi.java
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
import java.util.Scanner;

import model.Player;
import model.Record;
import controller.Controller;

public class TUi implements IUi {

    private Controller controller;
    Scanner s = new Scanner(System.in);
    private int dimBoard;

    public TUi() {
	this.controller = new Controller(this);
	controller.play("Player1", "Player2");
    }

    public static void main(String[] args) {
	new TUi();
    }

    @Override
    public void printTitle() {
	System.out.println("TIC TAC TOE");
	System.out.println("=============");
    }

    @Override
    public int askDimBoard() {
	System.out.print("Dimensió del taulell ? ");
	int dimBoard = s.nextInt();
	while (!this.validateBoardDimension(dimBoard)) {
	    System.out
		    .println("The minimum value of Board dimension is 3. The maximum value of board dimension is 10");
	    System.out.println("Introduce data again");
	    dimBoard = s.nextInt();
	}
	this.dimBoard = dimBoard;
	return dimBoard;
    }

    @Override
    public int askDimLine() {
	System.out.print("Dimensió de la línia ? ");
	int dimLine = s.nextInt();
	while (!this.validateLineDimension(dimLine, this.dimBoard)) {
	    System.out
		    .println("The minimum value of Line dimension are minimum 3 and maximum value of Line dimension is less than board dimension");
	    System.out.println("Introduce data again");
	    dimLine = s.nextInt();
	}
	return dimLine;
    }

    @Override
    public int askTokenX() {
	System.out.print("Coordenada x de la fitxa ? ");
	int x = s.nextInt();
	return x;
    }

    @Override
    public int askTokenY() {
	System.out.print("Coordenada y de la fitxa ? ");
	int y = s.nextInt();
	return y;
    }

    @Override
    public void printPlayerTurn(Player player) {
	System.out.println("És el torn del jugador " + player.getNum());

    }

    @Override
    public void printWinner(Player winner) {
	System.out.println("El guanyador és el jugador " + winner.getNum());
    }

    @Override
    public void printBoard(int[][] board) {
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board.length; j++) {
		System.out.print(board[i][j] + " ");
	    }
	    System.out.println();
	}
    }

    /**
     * Validate if introduced data are valid.
     */
    private boolean validateBoardDimension(int boardDim) {
	// minimum dimension of board are 3x3 and maximum dimension of board are
	// 10x10
	return boardDim >= 3 && boardDim <= 10;
    }

    /**
     * Validate if introduced data are valid.
     */
    private boolean validateLineDimension(int lineDim, int boardDim) {
	// minimum dimension of line are equal or more than board dimension
	return lineDim >= boardDim;

    }

    @Override
    public void showRecords(String recordsName, List<Record> records) {
	// TODO Auto-generated method stub

    }

}
