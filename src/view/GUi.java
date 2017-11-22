/**
 * GUi.java
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Player;
import model.Record;
import controller.Controller;

public class GUi extends JFrame implements IUi, ActionListener, WindowListener {

    private static final long serialVersionUID = 1L;

    private Controller c;
    private JPanel jpTop;
    private JPanel jpCenter;
    private JPanel jpRight;
    private JButton jbPlayer1;
    private JButton jbPlayer2;
    private JLabel jlRecord1;
    private JLabel jlRecord2;
    private JTextField jtfDimBoard;
    private JTextField jtfDimLine;
    private JButton jbStart;
    private JButton[][] jbsBoard;
    private JMenuBar jmbMenu;
    private JMenu jmView;
    private JMenuItem jmiTop10;

    private int dimBoard;
    private int dimLine;

    private int pressedX;
    private int pressedY;

    private final static Object lock = new Object();
    private Runnable r;
    private Thread t;

    /**
     * Constructor
     */
    public GUi() {
	initComponents();
	registerListeners();
	c = new Controller(this);
    }

    /**
     * Initialize all components of the GUI
     */
    private void initComponents() {

	this.setSize(650, 400);
	this.setResizable(true);
	this.setLocationRelativeTo(null);
	this.setTitle("Tic Tac Toe");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	createMenuBar();

	this.setJMenuBar(jmbMenu);

	jpTop = new JPanel(new FlowLayout(FlowLayout.LEFT));

	jtfDimBoard = new JTextField(4);
	jtfDimBoard.setText("5");
	jtfDimBoard.setHorizontalAlignment(JTextField.RIGHT);
	jtfDimLine = new JTextField(4);
	jtfDimLine.setText("3");
	jtfDimLine.setHorizontalAlignment(JTextField.RIGHT);
	jbStart = new JButton("Comença partida");

	jpTop.add(new JLabel("Tamany del taulell"));
	jpTop.add(jtfDimBoard);
	jpTop.add(new JLabel("Tamany de la linia"));
	jpTop.add(jtfDimLine);
	jpTop.add(jbStart);

	createRightPanel();

	this.add(jpRight, BorderLayout.EAST);
	this.add(jpTop, BorderLayout.NORTH);

	this.setVisible(true);
    }

    /**
     * Create de MenuBar
     */
    private void createMenuBar() {
	jmbMenu = new JMenuBar();

	jmView = new JMenu("Veure");

	jmiTop10 = new JMenuItem("Top 10");

	jmView.add(jmiTop10);

	jmbMenu.add(jmView);
    }

    /**
     * Create the right JPanel with the scoreboards
     */
    public void createRightPanel() {
	jpRight = new JPanel(new GridBagLayout());
	jpRight.setAlignmentX(JPanel.CENTER_ALIGNMENT);
	jpRight.setAlignmentY(JPanel.TOP_ALIGNMENT);
	GridBagConstraints gbc = new GridBagConstraints();

	gbc.gridx = 0;
	gbc.gridy = 0;
	gbc.gridwidth = 1;
	gbc.gridheight = 1;
	gbc.weightx = 0.5;
	gbc.ipadx = 0;
	gbc.ipady = 15;
	jpRight.add(new JLabel("Jugador"), gbc);

	gbc.gridx = 1;
	gbc.gridy = 0;
	gbc.gridwidth = 1;
	gbc.gridheight = 1;
	gbc.weightx = 10;
	gbc.ipadx = 25;
	gbc.ipady = 15;
	jpRight.add(new JLabel("Vict."), gbc);

	jbPlayer1 = new JButton("Player 1");
	jbPlayer1.setPreferredSize(new Dimension(100, 25));
	jbPlayer1.setBorderPainted(false);
	jbPlayer1.setBackground(Color.LIGHT_GRAY);
	gbc.gridx = 0;
	gbc.gridy = 1;
	gbc.gridwidth = 1;
	gbc.gridheight = 1;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.insets = new Insets(0, 0, 3, 0);
	gbc.ipadx = 0;
	gbc.ipady = 0;
	jpRight.add(jbPlayer1, gbc);

	jlRecord1 = new JLabel("0");
	gbc.gridx = 1;
	gbc.gridy = 1;
	gbc.gridwidth = 1;
	gbc.gridheight = 1;
	gbc.fill = GridBagConstraints.CENTER;
	gbc.ipadx = 25;
	jpRight.add(jlRecord1, gbc);

	jbPlayer2 = new JButton("Player 2");
	jbPlayer2.setBorderPainted(false);
	jbPlayer2.setBackground(Color.LIGHT_GRAY);
	gbc.gridx = 0;
	gbc.gridy = 2;
	gbc.gridwidth = 1;
	gbc.gridheight = 1;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.ipadx = 0;
	jpRight.add(jbPlayer2, gbc);

	jlRecord2 = new JLabel("0");
	gbc.gridx = 1;
	gbc.gridy = 2;
	gbc.gridwidth = 1;
	gbc.gridheight = 1;
	gbc.fill = GridBagConstraints.CENTER;
	gbc.ipadx = 25;
	jpRight.add(jlRecord2, gbc);
    }

    /**
     * Register listeners
     */
    private void registerListeners() {
	jmiTop10.addActionListener(this);
	jbStart.addActionListener(this);
	jbPlayer1.addActionListener(this);
	jbPlayer2.addActionListener(this);
    }

    /**
     * Create the board of buttons
     */
    private void createBoard() {
	if (jbsBoard != null) {
	    for (JButton[] jbs : jbsBoard) {
		for (JButton jb : jbs) {
		    jpCenter.remove(jb);
		}
	    }
	}
	jbsBoard = new JButton[dimBoard][dimBoard];
	jpCenter = new JPanel(new GridLayout(dimBoard, dimBoard));
	for (int i = 0; i < dimBoard; i++) {
	    for (int j = 0; j < dimBoard; j++) {
		jbsBoard[i][j] = new JButton();
		jbsBoard[i][j].addActionListener(this);
		jbsBoard[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		jpCenter.add(jbsBoard[i][j]);
	    }
	}
	this.remove(jpCenter);
	this.add(jpCenter, BorderLayout.CENTER);
    }

    /**
     * Unused
     */
    @Override
    public void printTitle() {

    }

    /**
     * Return the board dimension
     * 
     * @return the dimBoard
     */
    @Override
    public int askDimBoard() {
	return dimBoard;
    }

    /**
     * Return the line dimension
     * 
     * @return the dimLine
     */
    @Override
    public int askDimLine() {
	return dimLine;
    }

    /**
     * Return de X token of next position. This method wait for the user to
     * click one of the board buttons to return it's position
     * 
     * @return the pressedX button position
     */
    @Override
    public int askTokenX() {
	synchronized (lock) {
	    while (pressedX == -1) {
		try {
		    lock.wait();
		} catch (InterruptedException e) {
		}
	    }
	    lock.notifyAll();
	    return pressedX;
	}
    }

    /**
     * Return de Y token of next position
     * 
     * @return the pressedY button position
     */
    @Override
    public int askTokenY() {
	pressedX = -1;
	return pressedY;
    }

    /**
     * Show which player turn is
     * 
     * @param player
     *            the player who play this turn
     */
    @Override
    public void printPlayerTurn(Player player) {
	if (player.getNum() == 1) {
	    jbPlayer2.setBackground(Color.LIGHT_GRAY);
	    jbPlayer2.setForeground(Color.BLACK);
	    jbPlayer1.setBackground(Color.DARK_GRAY);
	    jbPlayer1.setForeground(Color.WHITE);
	} else {
	    jbPlayer1.setBackground(Color.LIGHT_GRAY);
	    jbPlayer1.setForeground(Color.BLACK);
	    jbPlayer2.setBackground(Color.DARK_GRAY);
	    jbPlayer2.setForeground(Color.WHITE);
	}

    }

    /**
     * Show who winned the game
     * 
     * @param winner
     *            the player who winned
     */
    @Override
    public void printWinner(Player winner) {
	if (jbPlayer1.getText().equals(winner.getName())) {
	    jlRecord1.setText(c.getWins(winner.getName()) + "");
	} else {
	    jlRecord2.setText(c.getWins(winner.getName()) + "");
	}

	for (int i = 0; i < jbsBoard.length; i++) {
	    for (int j = 0; j < jbsBoard[0].length; j++)
		jbsBoard[i][j].setEnabled(false);
	}
	JOptionPane.showMessageDialog(this, new JLabel("Ha guanyat el jugador "
		+ winner.getName()));
    }

    /**
     * Update all shown cells info
     * 
     * @param board
     *            the board of the actual match
     */
    @Override
    public void printBoard(int[][] board) {
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board[0].length; j++) {
		jbsBoard[i][j].setText(board[i][j] + "");
		if (board[i][j] == 1) {
		    jbsBoard[i][j].setBackground(Color.GREEN);
		} else if (board[i][j] == 2) {
		    jbsBoard[i][j].setBackground(Color.RED);
		} else {
		    jbsBoard[i][j].setBackground(Color.WHITE);

		}
	    }
	}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	Object src = e.getSource();
	if (src.equals(jmiTop10)) {
	    List<Record> records = c.getTop10();
	    showRecords("Top 10", records);
	} else if (src.equals(jbStart)) {
	    dimBoard = Integer.parseInt(jtfDimBoard.getText());
	    dimLine = Integer.parseInt(jtfDimLine.getText());
	    if (areValidDimensions(dimBoard, dimLine)) {
		startMatch();
	    }
	} else if (src.equals(jbPlayer1) || src.equals(jbPlayer2)) {
	    changePlayerName(src);
	} else {
	    for (int i = 0; i < dimBoard; i++) {
		for (int j = 0; j < dimBoard; j++) {
		    if (src.equals(jbsBoard[i][j])) {
			jbsBoard[i][j].setEnabled(false);
			synchronized (lock) {
			    pressedX = i;
			    pressedY = j;
			    lock.notifyAll();
			}
		    }
		}
	    }
	}

    }

    /**
     * Change one player of the match
     * 
     * @param src
     *            the button that represents the player who changed
     */
    public void changePlayerName(Object src) {
	String name = JOptionPane.showInputDialog(this, new JLabel(
		"Introdueix un nom de jugador"), "Player");
	if (name != null && !name.equals("")) {
	    if (src.equals(jbPlayer1)) {
		jbPlayer1.setText(name);
		jlRecord1.setText(c.getWins(name) + "");
	    } else {
		jbPlayer2.setText(name);
		jlRecord2.setText(c.getWins(name) + "");
	    }
	    c.setPlayers(jbPlayer1.getText(), jbPlayer2.getText());
	}
    }

    public void startMatch() {
	pressedX = -1;
	createBoard();
	r = new Runnable() {
	    @Override
	    public void run() {
		c.play(jbPlayer1.getText(), jbPlayer2.getText());
	    }
	};
	if (t != null)
	    t.interrupt();
	t = new Thread(r);
	t.start();
    }

    /**
     * Validate if introduced data are valid.
     */
    private boolean validateBoardDimension(int dimBoard) {
	// minimum dimension of board are 3x3 and maximum dimension of board are
	// 10x10
	return dimBoard >= 3 && dimBoard <= 10;
    }

    /**
     * Validate if introduced data are valid.
     */
    private boolean validateLineDimension(int dimBoard, int dimLine) {
	// minimum dimension of line are equal or more than board dimension
	return dimLine <= dimBoard && dimLine >= 3;

    }

    private boolean areValidDimensions(int dimBoard, int dimLine) {
	if (!this.validateBoardDimension(dimBoard)) {
	    JOptionPane
		    .showMessageDialog(
			    this,
			    new JLabel(
				    "The minimum value of Board dimension is 3. The maximum value of board dimension is 10"));
	} else if (!this.validateLineDimension(dimBoard, dimLine)) {
	    JOptionPane
		    .showMessageDialog(
			    this,
			    new JLabel(
				    "The minimum value of Line dimension are minimum 3 and maximum value of line dimension is less than board dimension"));
	} else {
	    return true;
	}
	return false;
    }

    public static void main(String[] args) {
	new GUi();
    }

    @Override
    public void showRecords(String recordsName, List<Record> records) {
	JPanel jpRecords = new JPanel(new GridLayout(records.size() + 1, 1));
	JLabel jlPl = new JLabel("Jugadors");
	jlPl.setFont(jlPl.getFont().deriveFont(Font.BOLD));
	JLabel jlW = new JLabel("Victories");
	jlW.setFont(jlW.getFont().deriveFont(Font.BOLD));
	jpRecords.add(jlPl);
	jpRecords.add(jlW);
	for (Record r : records) {
	    String name = r.getKey();
	    int wins = r.getValue();
	    jpRecords.add(new JLabel(name));
	    jpRecords.add(new JLabel(wins + ""));
	}
	JOptionPane.showMessageDialog(this, jpRecords, recordsName,
		JOptionPane.DEFAULT_OPTION);
    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

}
