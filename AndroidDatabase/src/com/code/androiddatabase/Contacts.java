package com.code.androiddatabase;

public class Contacts {

	String name, educat, email, mobile;

	public Contacts(String name, String educat, String email, String mobile) {
		super();
		this.name = name;
		this.educat = educat;
		this.email = email;
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEducat() {
		return educat;
	}

	public void setEducat(String educat) {
		this.educat = educat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {

		return "Contacts [name=" + name + ", educat=" + educat + ", email="
				+ email + ", mobile=" + mobile + "]";
	}

}
