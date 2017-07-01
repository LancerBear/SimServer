package com.lancer.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.lancer.entity.TemperatureEntity;

public class TemperatureManager {
	
	static public int ADD_SUCCESS = 1;
	static public int ADD_ERROR = 0;
	
	public int addTemperature(String tempString, String devID) {
		int state = ADD_ERROR;
		boolean valid = true;
		float dec_temperature = -100;
		//处理温度数据，高四位符号位，次高四位整数位，低八位小数位
		//0000 0001 1100 1000
		String integerBits = tempString.substring(4,12);
		String decimalBits = tempString.substring(13,15);
		
		int decimalVal = 0;
		int integerVal = 0;
		try {
			integerVal = Integer.valueOf(integerBits,2);
			decimalVal = Integer.valueOf(decimalBits,2);
		} catch (Exception e) {
			// TODO: handle exception
			valid = false;
			e.printStackTrace();
		}
		System.out.println("integerBits : " + integerBits);
		System.out.println("integerVal : " + integerVal);
		if(tempString.charAt(12) == '1')
			integerVal += 0.5;
		if (tempString.charAt(0) == '1')
			integerVal = 0 - integerVal;
		dec_temperature = integerVal;
		System.out.println("dec: " + dec_temperature);
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sim","root","root");
			
//			Connection conn = DriverManager.getConnection("jdbc:mysql://157.0.96.9:3306/sim","root","root");
			Statement stmt = conn.createStatement();
			if (valid)
				stmt.executeUpdate("insert into t_temperature (temperature,devID) value(" + dec_temperature + "," + devID + ");");
			else
				stmt.executeUpdate("insert into t_temperature (temperature,valid) value(" + dec_temperature + ",0);");
			
			state = ADD_SUCCESS;
		} catch (Exception e) {
			
			state = ADD_ERROR;
			e.printStackTrace();
		}
		return state;
	}
}
