package com.bms.model;
import java.io.Serializable;

/**
 * 
 * @Project：mybatisForSpring3   
 * @ClassName：AccountModel   
 * @Description：model  
 * @author：lixiu   
 * @CreateTime：Jul 30, 2011 1:57:02 PM   
 * @Modifier：lixiu  
 * @ChangeTime：Jul 30, 2011 1:57:02 PM   
 * @Remark：TODO 
 * @version 1.0
 */

public class AccountModel implements Serializable {
    private static final long serialVersionUID = -7970848646314840509L;
    private String accountId;
    private String username;
    private String password;
    private String email;
    private String  address;
    //getter、setter
    @Override
    public String toString() {
        return this.accountId + "#" + this.username +  "#" + 
            this.password +  "#" + this.email +  "#" + this.address;
    }
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
    
    
}
