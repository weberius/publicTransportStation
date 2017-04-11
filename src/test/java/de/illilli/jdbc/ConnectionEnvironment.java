package de.illilli.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.postgresql.ds.PGSimpleDataSource;

import de.illilli.opendata.service.Config;

/**
 * <a href=
 * "https://blogs.oracle.com/randystuph/entry/injecting_jndi_datasources_for_junit"
 * >Injecting JNDI datasources for JUnit Tests outside of a container</a>
 * 
 * <p>
 * for setup connection it is necessary to use tomcat-dependency in pom
 * </p>
 * 
 * <pre>
 * &lt;dependency&gt;
 *   &lt;groupId&gt;org.apache.tomcat&lt;/groupId&gt;
 *   &lt;artifactId&gt;tomcat-catalina&lt;/artifactId&gt;
 *   &lt;version&gt;8.0.15&lt;/version&gt;
 *   &lt;scope&gt;test&lt;/scope&gt;
 * &lt;/dependency&gt;
 * </pre>
 *
 */
public class ConnectionEnvironment {

	private final static Logger logger = Logger.getLogger(ConnectionEnvironment.class);

	public static void setUpConnectionForJndi() throws IOException {

		InputStream inputStream = ConnectionEnvironment.class.getResourceAsStream("/jndi.properties");
		Properties properties = new Properties();
		properties.load(inputStream);

		try {
			// Create initial context
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
			System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");

			InitialContext ic = new InitialContext();
			ic.createSubcontext(Config.getProperty("javax.naming.subcontext.0"));
			ic.createSubcontext(Config.getProperty("javax.naming.subcontext.1"));
			ic.createSubcontext(Config.getProperty("javax.naming.subcontext.2"));
			ic.createSubcontext(Config.getProperty("javax.naming.subcontext.3"));
			ic.createSubcontext(Config.getProperty("javax.naming.subcontext.4"));

			// Construct DataSource
			PGSimpleDataSource ds = new PGSimpleDataSource();
			ds.setServerName(properties.getProperty("servername"));
			ds.setPortNumber(Integer.parseInt(properties.getProperty("portnumber")));
			ds.setDatabaseName(properties.getProperty("databasename"));
			ds.setUser(properties.getProperty("user"));
			ds.setPassword(properties.getProperty("password"));

			String bind = Config.getProperty("javax.naming.initContext.lookup") + "/"
					+ Config.getProperty("javax.naming.envContext.lookup");
			logger.info("bind context for test '" + bind + "'");
			ic.bind(bind, ds);

		} catch (NamingException ex) {
			ex.printStackTrace();
		}

	}
}
