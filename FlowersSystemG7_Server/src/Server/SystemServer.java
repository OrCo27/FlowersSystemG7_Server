package Server;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Customers.Customer;
import Customers.Membership;
import Customers.MembershipType;
import Logic.DbGetter;
import Logic.DbQuery;
import Logic.DbUpdater;
import Logic.ISelect;
import Logic.IUpdate;

import PacketSender.Command;
import PacketSender.Packet;
import Products.CatalogProduct;
import Products.FlowerInProduct;
import Users.Permission;
import Users.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class SystemServer extends AbstractServer implements Initializable {

	public SystemServer(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	//private static final int DEFAULT_PORT = 5555;
	private String user = "root";
	private String password = "1q2w3e!";
	private String database = "test";
	private static final int DEFAULT_PORT = 5555;
	@FXML
	private TextField txtPort;
	@FXML
	private Button btnSubmit;
	@FXML
	private TextArea txtLog;
	@FXML
	private TextField txtDb;
	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtPass;
	@FXML
	private Button btnClear;
	int port = 0; // Port to listen on
	public SystemServer() {
		super(DEFAULT_PORT);
	}
	/**
	 * 
	 * @param msg log message to see the information
	 */
	private void printlogMsg(String msg)
	{
		String time=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());//get datetime for log print
		txtLog.setText(time+"---"+msg+"\n\r"+txtLog.getText());
	}
	/**
	 * if button pressed check
	 * the function check if server already listen to port 
	 * if yes then stop to listen
	 *  otherwise start listen and update button text
	 * */
	public void onSubmitClicked(ActionEvent event)
	{
		if(!isListening())//check if not listen
		{
			try {
				port = Integer.parseInt(txtPort.getText()); // Get port from command line
				this.setPort(port);
			} catch (Throwable t) {//if  port is wrong or listening already
				printlogMsg("ERROR - Could not listen for clients from this port! Using default port");
				this.setPort(DEFAULT_PORT);
				return;
			}
		}
			if(changeListening(txtDb.getText(), txtUser.getText(), txtPass.getText()))//check if switch listening is complete
			{
				if(btnSubmit.getText().equals("Start service")) {//if it wasn't listening
					printlogMsg("Server has started listening on port:"+port);//write to log
					btnSubmit.setText("Stop service");//update button
				}
				else//if it was listen
				{
					printlogMsg("Server has finished listening on port:"+port);
					btnSubmit.setText("Start service");///update button
				}
			}
	}
	/***
	 * clear log text area
	 */
	public void onClearClicked(ActionEvent event)
	{
		txtLog.clear();
	}
	public void start(Stage arg0) throws Exception {
		
		String title = "Server";
		String srcFXML = "/Server/App.fxml";
		String srcCSS = "/Server/application.css";
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(srcFXML));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(srcCSS).toExternalForm());
			arg0.setTitle(title);
			arg0.setScene(scene);
			arg0.show();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		arg0.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				Platform.exit();
			}
		});
	}

	public boolean changeListening(String database,String user, String password) 
	{
		if(!isListening())//if start service has been pressed
		{
			if(database.isEmpty()) {
				JOptionPane.showMessageDialog(null,"Please Fill DataBase name","Error",JOptionPane.ERROR_MESSAGE);
				printlogMsg("database name missing\n\r");
				return false;	
			}
			if(user.isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please Fill user name","Error",JOptionPane.ERROR_MESSAGE);
				printlogMsg("user name missing");
				return false;
			}
			try {
				DbQuery db = new DbQuery(user, password, database);//check connection to database
				db.connectToDB();
				db.connectionClose();
				listen(); // Start listening for connections
			}
			catch (Exception e) {
				printlogMsg(e.getMessage());
				return false;
			}
		}
		else//if stop service has been pressed
		{
			try {
				stopListening();
				close();
			} catch (IOException e) {
				printlogMsg(e.getMessage());
			}
		}
		return true;
	}
	public void getCatalogProductsHandler(DbQuery db, Command key)
	{	
		DbGetter dbGet = new DbGetter(db, key);
		
		dbGet.performAction(new ISelect() {
			@Override
			public String getQuery() {
				return "SELECT P.pId, C.productName, C.discount, C.image, T.typeId, T.description, P.price "
						+ "FROM product P INNER JOIN ProductType T ON P.pId = T.typeId "
						+ "INNER JOIN CatalogProduct C ON P.pId = C.pId";
			}

			@Override
			public Object createObject(ResultSet rs) throws SQLException {
				int id = rs.getInt(1);
				String productName = rs.getString(2);
				int discount = rs.getInt(3);
				int typeId = rs.getInt(5);
				double price = rs.getDouble(7);

				CatalogProduct catalogPro = new CatalogProduct(id, typeId, price, null, null, productName, discount, "");
				return (Object)catalogPro;
			}

			@Override
			public void setStatements(PreparedStatement stmt, Packet packet) throws SQLException { }
		});
	}
	
	public void getFlowersHandler(DbQuery db, Command key)
	{	
		DbGetter dbGet = new DbGetter(db, key);
		
		dbGet.performAction(new ISelect() {
			@Override
			public String getQuery() {
				return "SELECT FP.fId, FP.pId, FP.quantity " + 
						"FROM flowerinproduct FP";
			}

			@Override
			public Object createObject(ResultSet rs) throws SQLException {
				int fId = rs.getInt(1);
				int productID = rs.getInt(2);
				int qty = rs.getInt(3);

				FlowerInProduct fp = new FlowerInProduct(fId, productID, qty);
				return (Object)fp;
			}

			@Override
			public void setStatements(PreparedStatement stmt, Packet packet) throws SQLException { }
		});
	}
	//Elias     @@@
	
	//getting all users
	public void getUsersHandler(DbQuery db, Command key)
	{
		DbGetter dbGet = new DbGetter(db, key);
		dbGet.performAction(new ISelect() {
		@Override
		public String getQuery()
		{
			return "SELECT * FROM User";
		}

		@Override
		public Object createObject(ResultSet rs) throws SQLException 
		{
			int uId = rs.getInt(1);
			String user =rs.getString(2);
			String password = rs.getString(3);
			int islogged = rs.getInt(4);
			String perm=rs.getString(5);
			Permission permission = null;
			boolean isloggedbool=(islogged==1);
			User newuser;
			if(perm.equals((Permission.Administrator).toString()))
			permission= Permission.Administrator;
			else if(perm.equals((Permission.Blocked).toString()))
			permission= Permission.Blocked;
			else if(perm.equals((Permission.Limited).toString()))
			permission= Permission.Limited;
		
			newuser=new User(uId, user, password, isloggedbool, permission);
			return (Object)newuser;
		}
	
		@Override
		public void setStatements(PreparedStatement stmt, Packet packet) throws SQLException { }
		}
			
		);
	}
	
	//getting user by User Name 
	public void getUserByUserNameHandler(DbQuery db, Command key)
	{
		DbGetter dbGet = new DbGetter(db, key);
		dbGet.performAction(new ISelect() {
		@Override
		public String getQuery() {
		return "SELECT * "+ "FROM User u where user=?";
	}

	@Override
	public Object createObject(ResultSet rs) throws SQLException {
		int uId = rs.getInt(1);
		String user =rs.getString(2);
		String password = rs.getString(3);
		int islogged = rs.getInt(4);
		String perm=rs.getString(5);
		Permission permission = null;
		boolean isloggedbool=(islogged==1);
		User newuser;
		if(perm.equals((Permission.Administrator).toString()))
		permission= Permission.Administrator;
		else if(perm.equals((Permission.Blocked).toString()))
		permission= Permission.Blocked;
		else if(perm.equals((Permission.Limited).toString()))
		permission= Permission.Limited;

		newuser=new User(uId, user, password, isloggedbool, permission);
	return (Object)newuser;
	}

	@Override
	public void setStatements(PreparedStatement stmt, Packet packet) throws SQLException { 
		User user = (User) packet.getParameterForCommand(Command.getUsersByUserName).get(0);
		stmt.setString(1, user.getUser());
		}
	});
	}
	//adding user 
	public void addUserHandler(DbQuery db, Command key)
	{
		DbUpdater<User> dbUpdate = new DbUpdater<>(db, key);
		dbUpdate.performAction(new IUpdate<User>() {
	
		@Override
		public String getQuery() {
		// TODO Auto-generated method stub
		return "insert into User(uId,user,password,isLogged,permission) values(?,?,?,?,?)";
	
		}
	
		@Override
		public void setStatements(PreparedStatement stmt, User obj) throws SQLException {
		// TODO Auto-generated method stub
		int islog=0;
		stmt.setInt(1, obj.getuId());
		stmt.setString(2, obj.getUser());
		stmt.setString(3, obj.getPassword());
		if(obj.isLogged()==true)
		islog=1;
		stmt.setInt(4, islog);
		stmt.setString(5, obj.getPermission().toString());
		}
		});
	}
	
	//adding customer 
	public void addCustomertHandler(DbQuery db, Command key)
	{
		DbUpdater<Customer> dbUpdate = new DbUpdater<>(db, key);
		dbUpdate.performAction(new IUpdate<Customer>() {
	
		@Override
		public String getQuery() {
		// TODO Auto-generated method stub
			return "INSERT into Customer (uId,mId) values(?,?)";
	
		}
	
		@Override
		public void setStatements(PreparedStatement stmt, Customer obj) throws SQLException {
		// TODO Auto-generated method stub
			stmt.setInt(1, obj.getuId());
			stmt.setInt(2, obj.getMembershipId()); 
		}
	});
	}
	
	
	//getting all MemberShip
	public void getMemberShipHandler(DbQuery db, Command key)
	{
		DbGetter dbGet = new DbGetter(db, key);
		dbGet.performAction(new ISelect() {
		@Override
		public String getQuery() {
		return "SELECT * " + 
		"FROM MemberShip";
	}

	@Override
	public Object createObject(ResultSet rs) throws SQLException {
		int mId = rs.getInt(1);
		String memship =rs.getString(2);
		Double discount = rs.getDouble(3);
		MembershipType memtype;
		Membership newmemship;
	
		if(memship.equals((MembershipType.Normal).toString()))
		memtype= MembershipType.Normal;
		else if(memship.equals((MembershipType.Monthly).toString()))
		memtype= MembershipType.Monthly;
		else
		memtype= MembershipType.Yearly;

	newmemship=new Membership(mId, memtype, discount);
	return (Object)newmemship;
	}

	@Override
	public void setStatements(PreparedStatement stmt, Packet packet) throws SQLException { }
	});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void updateCatalogProductHandler(DbQuery db,  Command key)
	{
		DbUpdater<CatalogProduct> dbUpdate = new DbUpdater<>(db, key);
	
		dbUpdate.performAction(new IUpdate<CatalogProduct>() {

			@Override
			public String getQuery() {
				return "UPDATE product P INNER JOIN ProductType T ON P.pId = T.typeId "
						+ "INNER JOIN CatalogProduct C ON P.pId = C.pId "
						+ "SET C.productName = ?, C.discount = ?, C.image = ?, P.typeId = ?, P.price = ? "
						+ "WHERE P.pId = ?";
			}

			@Override
			public void setStatements(PreparedStatement stmt, CatalogProduct obj) throws SQLException {
				stmt.setString(1, obj.getName());
				stmt.setInt(2, obj.getSaleDiscountPercent());
				stmt.setString(3, obj.getImgUrl());
				stmt.setInt(4, obj.getProductTypeId());
				stmt.setDouble(5, obj.getPrice());
				stmt.setInt(6, obj.getId());
			}
		});
	}
	
	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		Packet packet = (Packet) msg;
		printlogMsg("from: "+client+" commands: "+packet.getCommands());
		DbQuery db = new DbQuery(user, password, packet, client,database);
		try {
			db.connectToDB();
			for (Command key : packet.getCommands())
			{
				if (key.equals(Command.getCatalogProducts)) {
					getCatalogProductsHandler(db, key);
				}
	
				else if (key.equals(Command.updateCatalogProduct)) {
					updateCatalogProductHandler(db, key);
				}
				
				else if (key.equals(Command.getFlowers)) {
					getFlowersHandler(db, key);
				}
				else if(key.equals(Command.getMemberShip))
					getMemberShipHandler(db, key);
				else if(key.equals(Command.getUsers))
					getUsersHandler(db, key);
				else if(key.equals(Command.addCustomers))
					addCustomertHandler(db,key);
				else if(key.equals(Command.addUsers))
					addUserHandler(db, key);
				else if(key.equals(Command.getUsersByUserName))
					getUserByUserNameHandler(db, key);
			}
			db.connectionClose();
		}
		catch (Exception e) {
			printlogMsg(e.getMessage());
			packet.setExceptionMessage(e.getMessage());
		}
		finally {
			try {
				db.sendToClient();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				printlogMsg(e.getMessage());
			}
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtDb.setText(database);
		txtUser.setText(user);
		txtPass.setText(password);
	}


}
