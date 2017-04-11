package de.illilli.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import de.illilli.opendata.service.Config;

/**
 * <p>
 * Die ConnectionFactory unterstellt, dass im Tomcat die Verbindung zur
 * Datenbank definiert wurde.
 * </p>
 * <p>
 * Beispiel
 * </p>
 * 
 * <pre>
 * &lt;Resource 
 * 	name="jdbc/publictransport/vrs" 
 * 	auth="Container" 
 * 	type="javax.sql.DataSource"
 * 	username="username" 
 * 	password="password" 
 * 	driverClassName="org.postgresql.Driver"
 * 	url="jdbc:postgresql://server:5432/vrs" 
 * 	maxTotal="25" 
 * 	maxIdle="10"
 * 	validationQuery="select 1" /&gt;
 * </pre>
 * 
 */
public class ConnectionFactory {

	public static Connection getConnection() throws SQLException, NamingException {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup(Config.getProperty("javax.naming.initContext.lookup"));
		DataSource ds = (DataSource) envContext.lookup(Config.getProperty("javax.naming.envContext.lookup"));
		Connection conn = ds.getConnection();
		return conn;
	}
}
