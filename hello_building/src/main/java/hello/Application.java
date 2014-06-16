package hello;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import java.sql.DriverManager;
import java.util.List;

import hello.controllers.BuildingDataBaseService;
import hello.models.BuildingModel;
import hello.models.InternalBuildingRepo;
import hello.models.api.BuildingRepoInterface;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;


@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
    
	public static final String BUILDING_TABLE = "BUILDING_TABLE";
	public static final String BUILDING_NAME="BUILDING_NAME";
	public static final String BUILDING_ADDRESS="BUILDING_ADDRESS";
	public static final String BUILDING_ID="BUILDING_ID";
	public static final String BUILDING_FLOOR="BUILDING_FLOOR";
	public static final String BUILDING_ROOM="BUILDING_ROOM";
	private static  Connection connection;
	private final String USER ="sa";
	private final String URL_STRING ="jdbc:h2:file:~/buildingdb";
	private final String PASSWORD="";	
	/**
	 * map the String version of a long to the corresponding building that has that id;
	 * @return
	 */
	@Bean
	public Converter<String, BuildingModel> buildingConvertor() {
		return new Converter<String, BuildingModel>() {
			@Override
			public BuildingModel convert(String id) {
				return BuildingRepo().getBuildingModel(Long.valueOf(id));
			}
		};
	}
	
	/**
	 * DataBase connection
	 * @return
	 */
	 @Bean
	 BuildingDataBaseService saveService() {
	        return new BuildingDataBaseService();
	 }

	 /**
	  * Building List bean
	  * @return
	  */
	 @Bean
	 public BuildingRepoInterface BuildingRepo() {
		 return new InternalBuildingRepo();
	 }
		
		
	 /**
	  * Configure the connection to the database
	  * @return
	  */
	 @Bean
	 DataSource dataSource() {
	        return new SimpleDriverDataSource() {{
	        	
	            setDriverClass(org.h2.Driver.class);
	            setUsername(USER);
	            setUrl(URL_STRING);
	            setPassword(PASSWORD);
	            try{
	            	
	            }catch(Throwable error){
	            	
	            }
	            
	            
	        }};
	 }
	 
	@Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
          // jdbcTemplate.execute("drop table "+ BUILDING_TABLE+" if exists");
        	DatabaseMetaData md;
			try {
				connection = DriverManager.getConnection(URL_STRING, USER, PASSWORD);
				md = connection.getMetaData();
				ResultSet rs = md.getTables(null, null, BUILDING_TABLE, null);
	             if (rs.next()) {
	               System.out.println("Table exist dont create new: ");
	               
	             }else{
	            	 System.out.println("Creating tables ");
	            	 jdbcTemplate.execute("create table "+ BUILDING_TABLE+"(" +
	                         " ID serial, "+
	                 		 BUILDING_NAME+" varchar(100) NOT NULL, "+
	                         BUILDING_ID +" INT, "+
	                         BUILDING_ADDRESS+" varchar(100) ,"+
	                         BUILDING_FLOOR+" varchar(100) ,"+
	                         BUILDING_ROOM+" varchar(100) ) ");
	             }
	             
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
        return jdbcTemplate;
    }
	

 
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }

}
