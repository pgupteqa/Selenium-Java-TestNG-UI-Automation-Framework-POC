package com.ui.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;

public class LoginDataProvider {
	
	@DataProvider(name="LoginDataProvider")
	public Iterator<Object[]> loginDataProvider()
	{
		Gson gson = new Gson();
		File testdatafile = new File(System.getProperty("user.dir")+"//testData//logintestdata.json");
		FileReader filereader=null;
		try {
			filereader = new FileReader(testdatafile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TestData data = gson.fromJson(filereader, TestData.class);
		
		//code for dataprovider using an list of object array
		List<Object[]> dataToReturn = new ArrayList<Object[]>();
		for(User user: data.getLogindata())
		{
			dataToReturn.add(new Object[] {user}); // 2d array
		}
		
		return dataToReturn.iterator();
		
	}

}
