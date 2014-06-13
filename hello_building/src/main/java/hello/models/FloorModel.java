package hello.models;

import hello.models.types.RoomType;

import java.util.LinkedList;

public class FloorModel extends AbstractContainer {

	private LinkedList<RoomType> rooms;
	private static final RoomType FLOOR_ROOM_TYPE = RoomType.FLOOR_ROOM_AREA;
	
	public FloorModel(BuildingModel building){
		super(FLOOR_ROOM_TYPE,building.getLength(),building.getWidth());
		rooms = new LinkedList<RoomType>();
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
