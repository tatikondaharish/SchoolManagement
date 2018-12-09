package com.servlets.projects.Hibernate.Hibernate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;

import com.harish.projA.exceptions.StudentExceptions;

public class SchoolHttpClient {
	public static void main(String[] args) throws IOException{
		String url="http://localhost:8080/Hibernate-1/insert/student";
		HashMap<String,String> headers=new HashMap<String,String>();
		headers.put("Headers","id,name,regNo");
		headers.put("Content-Type","text/plain");
		headers.put("Accept","Application/json");
		String data="1,harish,12361;2,amrutha,12323;3,ranjan,12356;4,babloo,12362;5,harika,12352;6,aryan,12317;7,dhruv,12337;8,babbu,12342";
		SchoolHttpClient.doPost(url, headers, data);
		
		
	}
	static HttpURLConnection conn;
	public static void doGet(String reqUrl,HashMap<String,String> headers) {
		int respCode=0;
		String response="";
		try {
			URL url=new URL(reqUrl);
			conn=(HttpURLConnection)url.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				for(Entry<String,String> entry:headers.entrySet()) {
					conn.setRequestProperty(entry.getKey(),entry.getValue());
				}
				conn.connect();
				respCode=conn.getResponseCode();
				response=IOUtils.toString(conn.getInputStream());
				System.out.println("Response code is "+respCode);
				System.out.println("data is "+response);
		}
		catch(Exception e) {
			System.out.println("Error");
		}
	}
	public static void doPost(String reqUrl,HashMap<String,String> headers,String data) throws IOException {
		int respCode=0;
		String response="";
		try {
			
			URL url=new URL(reqUrl);
			conn=(HttpURLConnection)url.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				for(Entry<String,String> entry:headers.entrySet()) {
					conn.setRequestProperty(entry.getKey(),entry.getValue());
				}
				conn.getOutputStream().write(data.getBytes());
				conn.connect();
				respCode=conn.getResponseCode();
			
				response=IOUtils.toString(conn.getInputStream());
				System.out.println("Response code is "+respCode);
				System.out.println("data is "+response);
				int[][] a= {{1,2,3},{4,5,6}};
				System.out.println(a[0][1]);
		}
		catch(Exception e) {
		
			System.out.println("error is ");
			
		}
		

		}
	
	}

