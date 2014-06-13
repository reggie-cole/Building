/**
 * 
 */
package hello.models.types;

import hello.models.api.ContainerInterface;

/**
 * @author regianld
 *
 */
public enum BuildingType implements ContainerInterface<BuildingType>{

	COMMERCIAL("Commercial",6.25,"blue"),
	INDUSTRIAL("Industrial",9.25,"yellow"),
	RESIDENTIAL("Residential",7.25,"green");
	
	private String type;
	private String simCityColor;
	private Double taxRate;
	private static final ContainerType CONTAINER_TYPE = ContainerType.BUILDING;
	
	private BuildingType(String type,Double taxRate,String simCityColor){
		this.type = type;
		this.taxRate = taxRate;
		this.simCityColor = simCityColor;
		
	}
	
	public String getTypeName(){
		return type;
	}
	
	public String getColor(){
		return simCityColor;
	}
	
	public Double getTaxRate(){
		return taxRate;
	}

	@Override
	public ContainerType getContainterType() {
		return CONTAINER_TYPE;
	}

	@Override
	public BuildingType getType() {
		return this;
	}
}
