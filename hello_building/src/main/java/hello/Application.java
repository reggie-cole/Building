package hello;

import hello.models.BuildingModel;
import hello.models.InternalBuildingRepo;
import hello.models.api.BuildingRepoInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;


@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
    
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
	public BuildingRepoInterface BuildingRepo() {
		return new InternalBuildingRepo();
	}
	
    public static void main(String[] args) {
    	
    	 //ApplicationContext ctx =
        SpringApplication.run(Application.class, args);
        
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
