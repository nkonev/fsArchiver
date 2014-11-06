package logic;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nik on 05.11.14.
 */
public class Runner {
    static String filesDir = "/home/nik/images";

    /*public static void main(String... args) throws ZipException {
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // set compression method to deflate compression

        // Set the compression level. This value has to be in between 0 to 9
        // Several predefined compression levels are available
        // DEFLATE_LEVEL_FASTEST - Lowest compression level but higher speed of compression
        // DEFLATE_LEVEL_FAST - Low compression level but higher speed of compression
        // DEFLATE_LEVEL_NORMAL - Optimal balance between compression level/speed
        // DEFLATE_LEVEL_MAXIMUM - High compression level with a compromise of speed
        // DEFLATE_LEVEL_ULTRA - Highest compression level but low speed
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

        File dir = new File(filesDir);

        for(File file: dir.listFiles()){
            ZipFile zipFile = new ZipFile(file.getAbsoluteFile() + ".zip");
            //file.lastModified();
            if(file.isDirectory()){
                zipFile.addFolder(file, parameters);
            }else {
                zipFile.addFile(file, parameters);
            }
        }
    }*/
    
    static String exit = "q";
    
    public static void main(String ...args) throws IOException{
    	 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    	 String isExit = "";
         do {
        	 for(File file: getNewFiles(new File("c:\\Programming\\Examples 9 java\\workspace\\fsArchiver"))){
        		 System.out.println(file);
        	 }
             System.out.println("Enter \"" + exit + "\" to exit, or enter any other to reload properties and re-process fileName...");

             isExit = bufferedReader.readLine();
         } while (!isExit.equals(exit));

         bufferedReader.close();
    }
    
    static private List<File> existed=new ArrayList<File>();
    public static File[] getNewFiles(File dir){
    	File[] files = dir.listFiles();
    	for(File file: files){
    		if(!existed.contains(file)){
    			existed.add(file);
    		}
    	}
		return files;
    }
}
