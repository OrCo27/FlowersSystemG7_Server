package Orders;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import Commons.*;
import Customers.Customer;

/***
 * 
 * Entity class to define an Order
 *
 */
public class Order implements Serializable,IRefundAble {
	private int id;
	private Date creationDate;
	private Timestamp requestedDate;
	private int customerId;
	private Status status;
	private int orderPaymentId;
	private ArrayList<ProductInOrder> productInOrderList;
	private ArrayList<OrderPayment> orderPaymentList;
	private Refund Refund;
	private Delivery deliery;
	private int brId;
	private double total;
	
	/***
	 * 
	 * @return order id
	 */
	public int getoId() {
		return id;
	}
	/***
	 * 
	 * @param id to set
	 */
	public void setoId(int id) {
		this.id = id;
	}
	/***
	 * 
	 * @return creation date
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/***
	 * 
	 * @param creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/***
	 * 
	 * @return branch id
	 */
	public int getBrId() {
		return brId;
	}
	/***
	 * 
	 * @param brId to set
	 */
	public void setBrId(int brId) {
		this.brId = brId;
	}
	/***
	 * 
	 * @return customer id
	 */
	public int getCustomerId() {
		return customerId;
	}
	/***
	 * 
	 * @param customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	/***
	 * 
	 * @return status
	 */
	public Status getStatus() {
		return status;
	}
	/***
	 * 
	 * @param status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	/***
	 * 
	 * @return order payment id
	 */
	public int getOrderPaymentId() {
		return orderPaymentId;
	}
	/***
	 * 
	 * @param orderPaymentId to set
	 */
	public void setOrderPaymentId(int orderPaymentId) {
		this.orderPaymentId = orderPaymentId;
	}
	/***
	 * 
	 * @return product in order list
	 */
	public ArrayList<ProductInOrder> getProductInOrderList() {
		return productInOrderList;
	}
	/***
	 * 
	 * @param productInOrderList to set
	 */
	public void setProductInOrderList(ArrayList<ProductInOrder> productInOrderList) {
		this.productInOrderList = productInOrderList;
	}
	/***
	 * 
	 * @return refund
	 */
	public Refund getRefund() {
		return Refund;
	}
	/***
	 * 
	 * @param refund to set
	 */
	public void setRefund(Refund refund) {
		Refund = refund;
	}
	/***
	 * 
	 * @return delivery
	 */
	public Delivery getDeliery() {
		return deliery;
	}
	/***
	 * 
	 * @param deliery to set
	 */
	public void setDeliery(Delivery deliery) {
		this.deliery = deliery;
	}
	/***
	 * Constructor for server side
	 * @param id
	 * @param creationDate
	 * @param requestedDate
	 * @param customer
	 * @param status
	 * @param orderPayment
	 * @param productInOrderList
	 * @param refund
	 * @param deliery
	 */
	public Order(int id, Date creationDate, Timestamp requestedDate, Customer customer, Status status,
			OrderPayment orderPayment, ArrayList<ProductInOrder> productInOrderList, Commons.Refund refund,
			Delivery deliery) {

		this.id = id;
		this.creationDate = creationDate;
		this.setRequestedDate(requestedDate);
		this.customerId = customerId;
		this.status = status;
		this.orderPaymentId = orderPaymentId;
		this.productInOrderList = productInOrderList;
		Refund = refund;
		this.deliery = deliery;
	}
	/***
	 * Constructor for client side
	 * @param id
	 * @param creationDate
	 * @param requestedDate
	 * @param cId
	 * @param status
	 * @param brId
	 * @param total
	 */
	public Order(int id,Date creationDate, Timestamp requestedDate,int cId,Status status,int brId,double total)
	{
		this.id=id;
		this.creationDate=creationDate;
		this.setRequestedDate(requestedDate);
		this.customerId=cId;
		this.status = status;
		this.brId=brId;
		this.setTotal(total);
	}
	/***
	 * Default constructor
	 */
	public Order()
	{

	}
	/***
	 * 
	 * @return total price
	 */
	public double getTotal() {
		return total;
	}
	/***
	 * 
	 * @param total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}
	/***
	 * 
	 * @return requested date
	 */
	public Timestamp getRequestedDate() {
		return requestedDate;
	}
	/***
	 * 
	 * @param requestedDate to set
	 */
	public void setRequestedDate(Timestamp requestedDate) {
		this.requestedDate = requestedDate;
	}
	/***
	 * 
	 * @return order payment list
	 */
	public ArrayList<OrderPayment> getOrderPaymentList() {
		return orderPaymentList;
	}
	/***
	 * 
	 * @param orderPaymentList to set
	 */
	public void setOrderPaymentList(ArrayList<OrderPayment> orderPaymentList) {
		this.orderPaymentList = orderPaymentList;
	}
}
