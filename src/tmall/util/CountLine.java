package tmall.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CountLine {
	public static int lineNum = 0;
	public static void countLine(File file) throws Exception{
		File[] files = file.listFiles();
		for(File f : files){
			if(f.isDirectory()){
				CountLine.countLine(f);
			}else{
				if(f.getName().endsWith(".jsp")|| f.getName().endsWith(".java") || f.getName().endsWith(".css")){
					System.out.println(f.getName());
					BufferedReader br = new BufferedReader(new FileReader(f));
					while(br.readLine()!=null){
						CountLine.lineNum++;
					}
					br.close();
				}
			}
		}
	}
	public static void main(String[] args) throws Exception{
		String[] files = {"./src","./WebRoot"};
		for(String fileName : files){
			File file = new File(fileName);
			countLine(file);
		}
		System.out.println(CountLine.lineNum);
	}
}