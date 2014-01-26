package com.health.care;
import android.location.*;
import android.net.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DiseaseDetect {
	public DiseaseDetect() {
		
	}
	public boolean isCrucialArea(double curLat, double curLon,String fileName,int radius) {
		ArrayList<Location> allPts = readCSV(fileName);
		int numLocalCases = 0;
		int thresh = 25; // number of bearable cases in an area
		Location curLoc = new Location("");
		curLoc.setLatitude(curLat);
		curLoc.setLongitude(curLon);
		for (Location l : allPts) {
			float dist = curLoc.distanceTo(l);
			if (dist < radius) {
				numLocalCases++;
			}
		}
		if (numLocalCases > thresh) {
			return true;
		}
		return false;				
	}
	
	private ArrayList<Location> readCSV(String filename){
		ArrayList<Location> locations=new ArrayList<Location>();
		File file=new File(filename);
		BufferedReader rdr=null;
		try {
			rdr = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line=null;
		
		try {
			while ((line=rdr.readLine())!=null){
				String[] tokens=line.split(",");
				Location loc=new Location("oneLoc");	// Assuming csv of format lat,long in every line
				loc.setLatitude(Double.parseDouble(tokens[0]));
				loc.setLongitude(Double.parseDouble(tokens[1]));
				locations.add(loc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		finally{
			try {
				rdr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return locations;
	}

}