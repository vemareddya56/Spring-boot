package com.example.EmployeeService.config;



import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.jdbc.pool.OracleDataSource;

@Configuration
@ConfigurationProperties("oracle")
@EnableConfigurationProperties

@Data @NoArgsConstructor  @AllArgsConstructor
public class OracleDatabaseconfig {
	
	//@Value("${oracle.userName:springboot}")
	private String userName;
	
	//@Value("${oracle.password}")
	private String password;
	
	//@Value("${oracle.dburl}")
	private String dburl;
	
	@Bean
	DataSource initOracleDS() throws SQLException
	{
		
		OracleDataSource dataSource = new OracleDataSource();
		System.out.println("========================================================");
		  
		dataSource.setUser(userName);
		dataSource.setPassword(password);
		dataSource.setURL(dburl);
		dataSource.setImplicitCachingEnabled(true);
		dataSource.setFastConnectionFailoverEnabled(true);
		
		System.out.println("========================================================");
		System.out.println("[user]:"+ userName +"[password]:"+ password + "[url]:"+ dburl);
		return dataSource;
		
		
	}

}
