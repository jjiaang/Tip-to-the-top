package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Save {
    
	protected static int numSaves = 0;
	protected static File currentSave;
	
	
	
	// make an arraylist of files, iterate through saves directory and add each files to the arraylist
	// that way, it's easier to access diff saves / get number of saves / etc
	
	public static void selectSave() {
		// needs to be implemented on loading screen
		currentSave = new File("./resources/saves/default.txt");
	}
	
	public static File getSave() {
		return currentSave;
	}
	
    public static void generateSave (PrintWriter out) {
    	out.println(numSaves);
    	// other variables to be stored in save here
    	// we should draft up a consistent format before we implement this -- we don't want to have to change order. 
    }
    
    /**
     * 
     */
    public static void createSaveFile() {
    	File defaultSave = new File("./resources/saves/default.txt");
    	numSaves++;
    	try {
    		boolean isCreated = defaultSave.createNewFile();
    		System.out.println("Was a new save created? " + isCreated);
    	} catch (Exception e) {
    	
    	}

    }
    
    /**
     * Early implementation of a save feature for the game. 
     */
    public static void saveWriter(File saveFile) {
    	
    	try {
        	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(saveFile.getPath())));
        	generateSave(out);
        	out.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
}
