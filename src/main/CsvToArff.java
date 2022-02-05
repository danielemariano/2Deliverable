package main;

import java.io.File;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvToArff {
	
	private static final Logger LOGGER = Logger.getLogger(CsvToArff.class.getName());
	
	private CsvToArff() {
		
	}
	
	public static String arffCreation(String path) throws IOException  {
		File projectClasses = new File(path);
		Instances data = null;
		String newPath = "";
		if (projectClasses.exists()) {
			CSVLoader loader = new CSVLoader();
			loader.setFieldSeparator(",");
			try {
				loader.setSource(projectClasses);
				data = loader.getDataSet();//get instances object
			} catch (IOException e) {
				LOGGER.log(Level.INFO, "context", e);
			}
		    // save ARFF
		    ArffSaver saver = new ArffSaver();
		    
			saver.setInstances(data);//set the dataset we want to convert
		    //and save as ARFF
		    String path2 = path.substring(0, path.length()-4);
		    newPath = path2+"_Dataset.arff";
		    try {
				saver.setFile(new File(newPath));
				saver.writeBatch();
			} catch (IOException e) {
				LOGGER.log(Level.INFO, "context", e);
			}
		}
		
		return newPath;
	}	
}
