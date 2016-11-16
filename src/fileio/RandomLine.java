package fileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RandomLine {

	public static void main(String[] args) {

		BufferedReader inputStream = null;

		try {
			inputStream = new BufferedReader(new FileReader("files/hamlet.txt"));
			String line;
			
			ArrayList<String> lines = new ArrayList<String>();
			
			// read in all lines in the file
			while ((line = inputStream.readLine()) != null) {
				//System.out.println(line);
				lines.add(line);
			}
			
			int r =(int) (Math.random() * lines.size());
			
			System.out.println(lines.get(r));

		} 
		catch (IOException e) {
			/* If there was an error, print a stack trace */
			e.printStackTrace();
		}
		finally {
			/* Once the try finishes, clean things up */
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
