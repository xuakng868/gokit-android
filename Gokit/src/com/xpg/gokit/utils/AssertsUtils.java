package com.xpg.gokit.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.content.Context;
import android.util.Log;

public class AssertsUtils {
	static public String getTextByName(Context c,String name){
		String result = "";  
        try {  
            InputStream in = c.getResources().getAssets().open(name);  
            BufferedReader brReader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while(( line = brReader.readLine())!=null){
            	result +=line;
            }
             
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
	}
	/**
	 * 从assert中复制出文件到某个文件
	 * @param c
	 * @param orifile
	 * @param desfile
	 * @return
	 * @throws IOException 
	 */
	static public boolean copyFileTo(Context c,String orifile,String desfile) throws IOException{
		InputStream myInput;  
        OutputStream myOutput = new FileOutputStream(desfile);  
        myInput = c.getAssets().open(orifile);  
        byte[] buffer = new byte[1024];  
        int length = myInput.read(buffer);
        while(length > 0)
        {
            myOutput.write(buffer, 0, length); 
            length = myInput.read(buffer);
        }
        
        myOutput.flush();  
        myInput.close();  
        myOutput.close(); 
		
		return true;
	}
	
	static public boolean copyAllAssertToCacheFolder(Context c ) throws IOException{
		
		String [] files = c.getAssets().list("Devices");
		String filefolder = c.getFilesDir().toString();
//		File configfile = new File(filefolder+"/XPGWifiConfig.json");
		
//		if(!configfile.exists()){
//			copyFileTo(c, "XPGWifiConfig.json", filefolder+"/XPGWifiConfig.json");
//		}
		File devicefile = new File(filefolder+"/Devices/");
		devicefile.mkdirs();
		
		for(int i = 0 ;i<files.length;i++){
			File devfile = new File(filefolder+"/Devices/"+files[i]);
			if(!devfile.exists()){
				copyFileTo(c,"Devices/"+files[i],filefolder+"/Devices/"+files[i]);
			}
		}
		String []filestr = devicefile.list();
		for(int i = 0;i < filestr.length;i++){
			Log.i("file", filestr[i]);
		}
		
		return true;
	}
}
