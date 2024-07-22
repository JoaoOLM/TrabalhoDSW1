package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract public class GenericDAO {
    
    public GenericDAO() {
        try {
            
        	/* Setup Banco de dados MySQL */
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
    	
    	/* Conexão banco de dados MySQL */
		String host     = System.getenv().getOrDefault("MYSQL_HOST", "localhost");
        String user     = System.getenv().getOrDefault("MYSQL_USER", "root");
        String password = System.getenv().getOrDefault("MYSQL_ROOT_PASSWORD", "admin");

        return DriverManager.getConnection("jdbc:mysql://" + host + ":3306/Estagio?serverTimezone=UTC", user, password);
    
	}
}