package com.dealership.model;

import java.util.Date;

public class User {

	private String username;
	private String passwords;
	private String Usersname;
	private int age;
	private Date dob;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String passwords, String usersname, int age, Date dob) {
		super();
		this.username = username;
		this.passwords = passwords;
		Usersname = usersname;
		this.age = age;
		this.dob = dob;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswords() {
		return passwords;
	}

	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}

	public String getUsersname() {
		return Usersname;
	}

	public void setUsersname(String usersname) {
		Usersname = usersname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Usersname == null) ? 0 : Usersname.hashCode());
		result = prime * result + age;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((passwords == null) ? 0 : passwords.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (Usersname == null) {
			if (other.Usersname != null)
				return false;
		} else if (!Usersname.equals(other.Usersname))
			return false;
		if (age != other.age)
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (passwords == null) {
			if (other.passwords != null)
				return false;
		} else if (!passwords.equals(other.passwords))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", passwords=" + passwords + ", Usersname=" + Usersname + ", age=" + age
				+ ", dob=" + dob + "]";
	}	
	
}
