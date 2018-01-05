package Customers;

import java.io.Serializable;

public class Account implements Serializable {
	private int num;
	private int customerId;
	private int branchId;
	private AccountStatus accountStatus;
	private String creditCard;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public AccountStatus getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	public Account(int num, int customerId, int branchId, AccountStatus accountStatus, String creditCard) {
		super();
		this.num = num;
		this.customerId = customerId;
		this.branchId = branchId;
		this.accountStatus = accountStatus;
		this.creditCard = creditCard;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
}
