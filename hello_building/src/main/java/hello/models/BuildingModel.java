/**
 * 
 */
package hello.models;

import hello.models.types.BuildingType;
import hello.models.types.RoomType;

import java.util.LinkedList;

/**
 * @author reginald
 *
 */
public class BuildingModel extends AbstractContainer {

	private String buildingName;
	private LinkedList<FloorModel> floors;
	
	/**
	 * 
	 * @param name
	 */
	public BuildingModel(String name,BuildingType buildingType,Double length,Double width){
		super(buildingType,length,width);
		buildingName = name;
		floors = new LinkedList<FloorModel>();
		floors.add(new FloorModel(this));
	}

	public void setBuildingType(BuildingType type){
		super.type = type;
	}
	
	public String getBuildingName() {
		return buildingName;
	}
	
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	public int getNumRooms() {
		int rooms = 0;
		for(FloorModel floor : floors){
			rooms += floor.getNumRooms();
		}
		return rooms;
	}

	public int getNumFloors() {
		return floors.size();
	}
	
	/**
	 * add a floor to a building
	 */
	public void addFloor() {
		floors.add(new FloorModel(this));
	}
	
	/**
	 *  add a Room to a building
	 * @param room
	 * @param floorNumber
	 */
	public void addRoom(RoomType room,int floorNumber){
		if( floorNumber >= floors.size()){
			return;
		}else{
			FloorModel floor = floors.get(floorNumber);
			floor.addRoom(room);
		}
		
	}

	public void printBuildInfo(){
		System.out.println("Buiding name:"+buildingName);
		System.out.println("Buiding type:"+getTypeName());
		System.out.println("Buiding num rooms:"+getNumRooms());
		System.out.println("Buiding num floors:"+getNumFloors());
		System.out.println("Buiding size:"+getSize());
	}
}
