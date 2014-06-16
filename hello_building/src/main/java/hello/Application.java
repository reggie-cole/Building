package hello;

import java.sql.SQLException;

import javax.sql.DataSource;

import hello.controllers.BuildingSaveService;
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
	
	@Bean
	public Converter<String, BuildingModel> messageConverter() {
		return new Converter<String, BuildingModel>() {
			@Override
			public BuildingModel convert(String id) {
				return BuildingRepo().getBuildingModel(Long.valueOf(id));
			}
		};
	}
	 @Bean
	 BuildingSaveService saveService() {
	        return new BuildingSaveService();
	    }

	 @Bean
	    DataSource dataSource() {
	        return new SimpleDriverDataSource() {{
	            setDriverClass(org.h2.Driver.class);
	            setUsername("sa");
	            setUrl("jdbc:h2:mem");
	            setPassword("");
	        }};
	    }
	 
	@Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("Creating tables");
        jdbcTemplate.execute("drop table "+ BUILDING_TABLE+" if exists");
        jdbcTemplate.execute("create table "+ BUILDING_TABLE+"(" +
                " ID serial, "+
        		BUILDING_NAME+" varchar(100) NOT NULL, "+
                BUILDING_ID +" INT, "+
                BUILDING_ADDRESS+" varchar(100) ,"+
                BUILDING_FLOOR+" varchar(100) ,"+
                BUILDING_ROOM+" varchar(100) ) ");
        return jdbcTemplate;
    }
	
	@Bean
	public BuildingRepoInterface BuildingRepo() {
		return new InternalBuildingRepo();
	}
	
    public static void main(String[] args) {
    	
    	 //
    	ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
    	BuildingSaveService bookingService = ctx.getBean(BuildingSaveService.class);
    	BuildingModel model = new BuildingModel();
    	model.setBuildingName("MAxis Building");
    	model.setBuildingId(1l);
    	model.setRooms(3);
    	model.setBuildingAddress("muryberry lane");
    	model.setFloorCount(3);
        bookingService.saveBuilding(model);
        System.err.println("amount of buildings:"+ bookingService.findAllBuilding().size());
       
//        
//        String name;
//        Double length;
//        Double width;
//        BuildingType type = null;
//        Scanner input = new Scanner(System.in);
//        System.out.println("Enter building name:");
//        name = input.nextLine();
//        System.out.println("Enter building length:");
//        length = input.nextDouble();
//        System.out.println("Enter building width:");
//        width = input.nextDouble();
//        System.out.println(" 1:Commerical \n 2: Residential \n 3: Industrial");
//        System.out.println();
//        int choice = input.nextInt();
//        switch(choice){
//        case 1: type = BuildingType.COMMERCIAL;
//        break;
//        case 2: type = BuildingType.RESIDENTIAL;
//        break;
//        case 3: type = BuildingType.INDUSTRIAL;
//        break;
//        }
//        BuildingModel building = new BuildingModel(name, type, length, width);
//        building.printBuildInfo();;
        
    }

}
