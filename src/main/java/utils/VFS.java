package utils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class VFS{
	private static String dir=System.getProperty("user.dir")+'\\';

	public static boolean isExist(String path){
		File f = new File(System.getProperty("user.dir") + path);
		return f.exists();
	}
	public static boolean isFile(String path){
		File f = new File(System.getProperty("user.dir") + path);
		return f.isFile();
	}
	public static String getAbsolutePath(String path){
		if(isAbsolute(path))
			return path;
		else
			return (dir+path);
	}
	public static String getRelativePath(String path){
		if(isAbsolute(path)){
			return path.substring(dir.length()) ;
		}
		else{
			return path;
		}
	}
	private static boolean isAbsolute(String path){
		return path.startsWith(dir);
	}
	public static List<File> bfs(String path){
		path=getAbsolutePath(path);
		List<File> resp = new LinkedList<File> ();
		File[] temp;
		Queue<File> queue = new LinkedList<File>();
		File file = new File(path);
		queue.add(file);
		while(queue.size()>0){
			file=queue.poll();
			if(file.isDirectory()){
				temp=file.listFiles();
				for(int counter=0;counter<temp.length;counter++)
					queue.add(temp[counter]);
			}
			else
				resp.add(file);
		}
		return resp;
	}
	public static void writeToFile(String path, String data) {
		path=getAbsolutePath(path);
		FileWriter fw=null;
		try {
			File file=new File(path);
            file.getParentFile().mkdirs();
			fw = new FileWriter(file);
			fw.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(fw!=null){
				try{
					fw.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	public static void writeToEndOfFile(String path,String data){
		path=getAbsolutePath(path);
		File file = new File(path);
		FileWriter err = null;
		try{
//			path=getAbsolutePath(path);
			err=new FileWriter(file,true);
			err.write(data);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(err!=null){
				try{
					err.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	public static String readFile(String path){
		StringBuilder string = new StringBuilder();
		String line;
		path = getAbsolutePath(path);
		BufferedReader br = null;
		FileReader fr = null;
		try{
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			while((line=br.readLine())!=null){
				string.append(line);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(br!=null){
				try{
					br.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			if(fr!=null){
				try{
					fr.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return string.toString();
	}
}