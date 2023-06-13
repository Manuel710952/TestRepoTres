package com.maesquiv.dto;

public class ConectionFTPPojo {
	public String serverName;
	public int port;
	public String userName;
	public String password;
	
	public ConectionFTPPojo(String serverName, int port, String userName, String password) {
		super();
		this.serverName = serverName;
		this.port = port;
		this.userName = userName;
		this.password = password;
	}
	
	public ConectionFTPPojo() {}
	
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ConectionFTPPojo [serverName=" + serverName + ", port=" + port + ", userName=" + userName
				+ ", password=" + password + "]";
	}
	
}
