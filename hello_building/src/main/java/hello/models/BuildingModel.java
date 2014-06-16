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

	private String buildingName ="";
	private LinkedList<FloorModel> floors = new LinkedList<FloorModel>();
	private Long buildingId;	
	private String buildingAddress="";	
	private int typeId;
	private BuildingType type;
	private int rooms = 1;
	private int floorCount = 1;
	
	public void setRooms(int rooms){
		if(rooms <= 0){
			this.rooms = 1;
		}else{
			this.rooms = rooms;
		}
	}
	
	public int getRooms(){
		return rooms;
	}
	public int getFloorCount(){
		return floorCount;
	}
	
	public void setFloorCount(int floorCount){
		if(floorCount <= 0){
			this.floorCount = 1;
		}else{
			this.floorCount = floorCount;
		}
	}
	
	public String getType(){
		return this.type.name();
	}
	
	public String getBuildingName() {
		return buildingName;
	}
	
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	public int getNumRooms() {
		
		for(FloorModel floor : floors){
			rooms += floor.getNumRooms();
		}
		return rooms;
	}

	public int getNumFloors() {
		return (floors.size() == 0 )? floorCount :floors.size() ;
	}
	
	/**
	 * add a floor to a building
	 */
	public void addFloor() {
		floors.add(new FloorModel(this));
		floorCount = floors.size();
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

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingAddress() {
		return buildingAddress;
	}

	public void setBuildingAddress(String buildingAddress) {
		this.buildingAddress = buildingAddress;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
		if(typeId == 1){
			
			this.type = BuildingType.COMMERCIAL;
		}else{
			this.type = BuildingType.RESIDENTIAL;
		}
	}
	
	public void buildingPrintInfo(){
		System.out.println("building name: "+this.buildingName+" id:"+this.buildingId);
	}
}
