package com.lancer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lancer.manager.TemperatureManager;
//http://40060210.nat123.net/SimServer/storeDataServlet?ID=SIM900A&temperature=0000000111001000&devID=1
public class StoreDataServlet extends HttpServlet {
	
	char table[] = {0x01,0x02,0x04,0x08,0x10,0x20,0x40,0x80};
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		
		String ID;
		String tempString;
		String devID;
		tempString = request.getParameter("temperature");
		ID = request.getParameter("ID");
		devID = request.getParameter("devID");
		
		System.out.println("tempString: "  + tempString);
		if (ID != null && ID.equals("SIM900A")) {
			if (tempString != null){
				TemperatureManager tm = new TemperatureManager();
				tm.addTemperature(tempString,devID);
			}else{
				System.out.println("temperature data wrong");
			}
		}else {
			System.out.println("ID not match");
		}
	}
}
