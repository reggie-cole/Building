package hello;

import hello.models.BuildingModel;
import hello.models.InternalBuildingRepo;
import hello.models.types.BuildingType;
import hello.models.api.BuildingRepo;
import java.util.Arrays;
import java.util.Scanner;

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
    
	@Bean
	public BuildingRepo BuildingRepo() {
		return new InternalBuildingRepo();
	}
	
    public static void main(String[] args) {
    	
    	
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        
        String name;
        Double length;
        Double width;
        BuildingType type = null;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter building name:");
        name = input.nextLine();
        System.out.println("Enter building length:");
        length = input.nextDouble();
        System.out.println("Enter building width:");
        width = input.nextDouble();
        System.out.println(" 1:Commerical \n 2: Residential \n 3: Industrial");
        System.out.println();
        int choice = input.nextInt();
        switch(choice){
        case 1: type = BuildingType.COMMERCIAL;
        break;
        case 2: type = BuildingType.RESIDENTIAL;
        break;
        case 3: type = BuildingType.INDUSTRIAL;
        break;
        }
//        BuildingModel building = new BuildingModel(name, type, length, width);
//        building.printBuildInfo();;
        
    }

}
