package com.ui.pojo;

import java.util.List;

public class TestData {
	
	//dataset for logintestdata.json
	List<User> logindata;
	
	//dataset for registration.json
	List<Registration> registrationdata;

	public List<Registration> getRegistrationdata() {
		return registrationdata;
	}

	public void setRegistrationdata(List<Registration> registrationdata) {
		this.registrationdata = registrationdata;
	}

	public List<User> getLogindata() {
		return logindata;
	}

	public void setLogindata(List<User> logindata) {
		this.logindata = logindata;
	}

}
