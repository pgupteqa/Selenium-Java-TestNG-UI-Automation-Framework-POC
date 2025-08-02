package com.ui.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.InvalidUsers;
import com.ui.pojo.Registration;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;
import com.utility.PropertiesUtil;

public class MultiDataProvider {
	
	private TestData loadTestData(String filename) {
		Gson gson = new Gson();
		File file = new File(System.getProperty("user.dir") + "//testData//"+filename);
		FileReader fileReader=null;
		try 
		{
			fileReader = new FileReader(file);	
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gson.fromJson(fileReader, TestData.class);
	}
	
	
	@DataProvider(name = "LoginDataProvider")
	public Iterator<Object[]> loginDataProvider() {
		TestData data = loadTestData(PropertiesUtil.readPropertyData("loginTestDataJsonFileName"));
		List<Object[]> result = new ArrayList<>();
		for (User user : data.getLogindata()) {
			result.add(new Object[]{user});
		}
		return result.iterator();
	}
	
	@DataProvider(name = "invalidLoginDataProvider")
	public Iterator<Object[]> inValidLoginDataProvider() {
		TestData data = loadTestData(PropertiesUtil.readPropertyData("invalidLoginTestDataJsonFileName"));
		List<Object[]> result = new ArrayList<>();
		for (InvalidUsers user : data.getInvalidLogindata()) {
			result.add(new Object[]{user});
		}
		return result.iterator();
	}
	
	@DataProvider(name = "registrationDataProvider")
	public Iterator<Object[]> registrationDataProvider() {
		TestData data = loadTestData(PropertiesUtil.readPropertyData("registrationTestDataJsonFileName"));
		List<Object[]> result = new ArrayList<>();
		for (Registration reg : data.getRegistrationdata()) {
			result.add(new Object[]{reg});
		}
		return result.iterator();
	}
	
	@DataProvider(name="LoginTestCSVDataProvider")
	public Iterator<User> loginCSVDataProvider()
	{
		return CSVReaderUtility.readCSVFile(PropertiesUtil.readPropertyData("loginTestDataCsvFileName"));
	}
	
	@DataProvider(name="LoginTestExcelDataProvider")
	public Iterator<User> loginExcelDataProvider()
	{
		return ExcelReaderUtility.readExcelData(PropertiesUtil.readPropertyData("loginTestDataExcelFileName"));
	}

}
