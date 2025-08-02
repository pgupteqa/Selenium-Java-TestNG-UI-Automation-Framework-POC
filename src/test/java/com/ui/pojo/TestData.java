package com.ui.pojo;

import java.util.List;

public class TestData {
	
	//dataset for logintestdata.json
	List<User> logindata;
	
	//dataset for registration.json
	List<Registration> registrationdata;
	
	//dataset for InvalidLogin.json
	List<InvalidUsers> invalidlogindata;

	public List<InvalidUsers> getInvalidLogindata() {
		return invalidlogindata;
	}

	public void setInvalidLogindata(List<InvalidUsers> invalidLogindata) {
		this.invalidlogindata = invalidLogindata;
	}

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
