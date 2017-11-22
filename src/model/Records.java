/**
 * Records.java
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/**
 * Records allow to manage the records of the game.
 * 
 * @author Bernabé Gonzalez, Marc Sabaté, Joaquim Dalmau
 * 
 */
public class Records {
    /* The file where records are saved */
    private static final String PATH = "data/records.dat";
    /* List of records */
    private ArrayList<Record> listRecords;

    // Constructor
    public Records() {
	listRecords = new ArrayList<>();
    }

    /**
     * Read data from a csv file.
     */
    public void readData() {
	try {
	    FileReader fr = new FileReader(new File(PATH));
	    BufferedReader br = new BufferedReader(fr);
	    String line = br.readLine();
	    while (line != null) {
		String[] values = line.split(":");
		String name = values[0];
		int points = Integer.parseInt(values[1]);
		listRecords.add(new Record(name, points));
		line = br.readLine();
	    }
	    br.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Write data to a csv file.
     */
    public void writeData() {
	try {
	    FileWriter fw = new FileWriter(new File(PATH));
	    BufferedWriter bw = new BufferedWriter(fw);
	    Iterator<Record> it = listRecords.iterator();
	    while (it.hasNext()) {
		Entry<String, Integer> e = it.next();
		String name = e.getKey();
		int points = e.getValue();
		bw.write(name + ":" + points + "\n");
	    }
	    bw.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Add plus points to a player. This player may be exist.
     * 
     * @param name
     */
    public void plusPlayer(String name) {
	Record o = new Record(name);
	if (listRecords.contains(o)) {
	    Iterator<Record> it = listRecords.iterator();
	    while (it.hasNext()) {
		Entry<String, Integer> e = it.next();
		String nameTemp = e.getKey();
		if (nameTemp.equals(name)) {
		    int tempValue = e.getValue();
		    e.setValue(tempValue + 1);
		}
	    }
	} else {
	    listRecords.add(new Record(name, 1));
	}
    }

    /**
     * List sorted records
     * 
     * @return a List of sroted records
     */
    public List<Record> getListRecords() {
	Collections.sort(listRecords);
	return listRecords;
    }

    public int getWins(String player) {
	int wins = 0;
	for (int i = 0; i < listRecords.size() && wins == 0; i++) {
	    Record record = listRecords.get(i);
	    if (record.getKey().equals(player)) {
		wins = record.getValue();
	    }
	}
	return wins;
    }
}
