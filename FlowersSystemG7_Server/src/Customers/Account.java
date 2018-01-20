package Customers;

import java.io.Serializable;
import java.util.ArrayList;

import Commons.Refund;

public class Account implements Serializable {
	private int num;
	private int customerId;
	private double balance;
	private int mId;
	private Membership memberShip;
	
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}

	public Membership getMemberShip() {
		return memberShip;
	}
	public void setMemberShip(Membership memberShip) {
		this.memberShip = memberShip;
	}

	private int branchId;
	private AccountStatus accountStatus;
	private String creditCard;
	
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
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

	
	public Account(int branchId, int customerId, int mId,double balance, AccountStatus accountStatus, String creditCard) {
		super();
		this.customerId = customerId;
		this.mId = mId;
		this.balance = balance;
		this.branchId = branchId;
		this.accountStatus = accountStatus;
		this.creditCard = creditCard;
	}
	public Account(int num, int customerId,int mId, double balance, int branchId, AccountStatus accountStatus,
			String creditCard) {
		super();
		this.num = num;
		this.customerId = customerId;
		this.mId=mId;
		this.balance = balance;
		this.branchId = branchId;
		this.accountStatus = accountStatus;
		this.creditCard = creditCard;
	}
	public Account(int branchId, int customerId,double balance, AccountStatus accountStatus, String creditCard) {
		super();
		this.customerId = customerId;
		this.balance = balance;
		this.branchId = branchId;
		this.accountStatus = accountStatus;
		this.creditCard = creditCard;
	}

}

