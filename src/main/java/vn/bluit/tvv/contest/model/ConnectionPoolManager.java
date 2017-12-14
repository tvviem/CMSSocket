package vn.bluit.tvv.contest.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class ConnectionPoolManager {
	private String databaseUrl = "";
	private String driverClass="";
	private String serverName;
	private String port;
	private String dbName;
	private String userName = "";
	private String passWord = "";

	// Hold all connect sucess
	Vector connectionPool = new Vector();
	public ConnectionPoolManager() {
	    initialize();
	}

	/*public ConnectionPoolManager(String server, int port, String database, String userName, String password) {
		this.server = server;
		this.port = port;
		this.database = database;
		this.userName = userName;
		this.password = password;
		initialize();
	}*/

	private void initialize() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("app.properties"));
            driverClass = properties.getProperty("DB_DRIVERCLASS");
            serverName = properties.getProperty("DB_SERVERNAME");
            port = properties.getProperty("DB_PORT");
            dbName = properties.getProperty("DB_NAME");
            userName = properties.getProperty("DB_USER");
            passWord = properties.getProperty("DB_PASSWORD");
			databaseUrl = "jdbc:mysql://"+serverName+":"+port+"/"+dbName;
			//Here we can initialize all the information that we need
			initializeConnectionPool();
        } catch (IOException e) {
			// Ghi nhat ky tai day de debug Project sau khi trien khai

        }
	}
	private void initializeConnectionPool() {
		while(!checkIfConnectionPoolIsFull())
		{
			System.out.println("Connection Pool is NOT full. Proceeding with adding new connections");
			//Adding new connection instance until the pool is full
			connectionPool.addElement(createNewConnectionForPool());
		}
		System.out.println("Connection Pool is full.");
	}

	private synchronized boolean checkIfConnectionPoolIsFull() {
		final int MAX_POOL_SIZE = 5; // Cho phep ton tai 5 ket noi hien huu

		//Check if the pool size
		if(connectionPool.size() < 5) {
			return false;
		}
		return true;
	}

	//Creating a connection
	private Connection createNewConnectionForPool() {
		Connection connection;
		try	{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(databaseUrl, userName, passWord);
			System.out.println("Connection: " + connection);
		} catch(SQLException sqle) {
			System.err.println("SQLException: " + sqle);
			return null;
		} catch(ClassNotFoundException cnfe) {
			System.err.println("ClassNotFoundException: " + cnfe);
			return null;
		}

		return connection;
	}

	public synchronized Connection getConnectionFromPool() {
		Connection connection = null;
		//Check if there is a connection available. There are times when all the connections in the pool may be used up
		if(connectionPool.size() > 0)
		{
			connection = (Connection) connectionPool.firstElement();
			connectionPool.removeElementAt(0);
		}
		//Giving away the connection from the connection pool
		return connection;
	}

	public synchronized void returnConnectionToPool(Connection connection) {
		//Adding the connection from the client back to the connection pool
		connectionPool.addElement(connection);
	}

	/*public static void main(String args[]) {
		ConnectionPoolManager ConnectionPoolManager = new ConnectionPoolManager();
	}*/

}