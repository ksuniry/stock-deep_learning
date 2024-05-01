package com.stock.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.stock.constants.UtilConst;

public class CommonUtil {
	
	public static Boolean isEmpty(Object obj) {
		if(obj == null) {
			return true;
		}else {
			if(obj instanceof List) {
				if(((List) obj).size() == 0) {
					return true;
				}
			}else if("".equals(obj)){
				return true;
			}
		}
		return false;
	}
	
	public static String getNowDateByFormat(String format) {
		LocalDateTime now = LocalDateTime.now();         
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);         
		return now.format(formatter); 	
		 
	}
	
	public static long getBetweenHourByYyyyMMddHHmmss(String yyyyMMddHHmmss) {
		
		Integer[] startArray = getYyyyMMddHHmmssArray(yyyyMMddHHmmss);
		Integer[] endtArray = getYyyyMMddHHmmssArray(getNowDateByFormat(UtilConst.DATE_FORMAT_YYYYMMDDHHMMSS));
		
		LocalDateTime startTime = LocalDateTime.of(startArray[0], startArray[1], startArray[2], startArray[3], startArray[4], startArray[5]);
		LocalDateTime endTime = LocalDateTime.of(endtArray[0], endtArray[1], endtArray[2], endtArray[3], endtArray[4], endtArray[5]);
		         
		return ChronoUnit.HOURS.between(startTime, endTime);
		 
	}
	public static long getBetweenSecondByYyyyMMddHHmmss(String yyyyMMddHHmmss) {
		
		Integer[] startArray = getYyyyMMddHHmmssArray(yyyyMMddHHmmss);
		Integer[] endtArray = getYyyyMMddHHmmssArray(getNowDateByFormat(UtilConst.DATE_FORMAT_YYYYMMDDHHMMSS));
		
		LocalDateTime startTime = LocalDateTime.of(startArray[0], startArray[1], startArray[2], startArray[3], startArray[4], startArray[5]);
		LocalDateTime endTime = LocalDateTime.of(endtArray[0], endtArray[1], endtArray[2], endtArray[3], endtArray[4], endtArray[5]);
		         
		return ChronoUnit.SECONDS.between(startTime, endTime);
		 
	}
	
	//20240413175724
	public static Integer[] getYyyyMMddHHmmssArray(String yyyyMMddHHmmss) {
		Integer[] returnArray = new Integer[6];
		
		returnArray[0] = Integer.parseInt(yyyyMMddHHmmss.substring(0, 4));
		returnArray[1] = Integer.parseInt(yyyyMMddHHmmss.substring(4, 6));
		returnArray[2] = Integer.parseInt(yyyyMMddHHmmss.substring(6, 8));
		returnArray[3] = Integer.parseInt(yyyyMMddHHmmss.substring(8, 10));
		returnArray[4] = Integer.parseInt(yyyyMMddHHmmss.substring(10, 12));
		returnArray[5] = Integer.parseInt(yyyyMMddHHmmss.substring(12, 14));
		return returnArray;
	}
	
	public static Boolean isTraideTime() {
		
		Integer[] nowTimeArray = getYyyyMMddHHmmssArray(getNowDateByFormat(UtilConst.DATE_FORMAT_YYYYMMDDHHMMSS));
		int hour =  nowTimeArray[3];
		int minute =  nowTimeArray[4];
		
		Boolean isTraidTime = false;
		if(9 <=hour && hour<=15) {
			if(hour == 15) {
				if(minute < 21) {
					isTraidTime = true;
				}
			}else {
				isTraidTime = true;
			}
		}
		return isTraidTime;
	}
	
	
	
	@SuppressWarnings("resource")
	public static String readFile(String path, String fileName) throws Exception{

        File file = new File(path, fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String retStr = "";
        String line;
        while ((line = br.readLine()) != null) {
            retStr += line + "\n";
        }

        return retStr;
    }

    public static void writeFile(String path, String fileName, String content) {
        File file = new File(path, fileName);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	
}
