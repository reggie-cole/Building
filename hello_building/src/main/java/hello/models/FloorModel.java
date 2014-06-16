package hello.models;

import hello.models.types.RoomType;

import java.util.LinkedList;

public class FloorModel extends AbstractContainer {

	private LinkedList<RoomType>  rooms = new LinkedList<RoomType>();;
	private static final RoomType FLOOR_ROOM_TYPE = RoomType.FLOOR_ROOM_AREA;
	
	public FloorModel(BuildingModel building){
		
		
		super.setContainerType(FLOOR_ROOM_TYPE);
		super.setLength(building.getLength());
		super.setWidth(building.getWidth());
	}
	public void addRoom(RoomType room){
		rooms.add(room);
	}
	
	public void removeRoom(RoomType room){
		rooms.remove(room);
	}
	
	public LinkedList<RoomType> getRooms(){
		return rooms;
	}
	
	public int getNumRooms(){
		
		return (rooms.size() <= 1)? 1 :rooms.size() ;
	}
	
}
