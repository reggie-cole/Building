package hello.models.types;

import hello.models.api.ContainerInterface;

public enum RoomType implements ContainerInterface<RoomType> {
	RESTROOM("Restroom",true,ContainerType.ROOM),
	FLOOR_ROOM_AREA("Floor",true,ContainerType.FLOOR),
	BEDROOM("Bedroom",false,ContainerType.ROOM),
	LIVING_ROOM("Living room",false,ContainerType.ROOM),
	CONFERENCE_ROOM("Conference Room",false,ContainerType.ROOM), 
	OFFICE("office",false,ContainerType.ROOM),
	GAME_ROOM("Game Room",false,ContainerType.ROOM),
	BREAK_ROOM("Break room",false,ContainerType.ROOM),
	CLOSET("closet",false,ContainerType.ROOM);
	
	private String type;
	private boolean isEssenstial;
	private final ContainerType containerType;
	private RoomType(String roomType,boolean isEssenstial,ContainerType containType){
		type = roomType;
		this.isEssenstial = isEssenstial;
		containerType = containType;
	}
	
	public String getTypeName(){
		return type;
	}
	
	public boolean isRequired(){
		return isEssenstial;
	}
	
	@Override
	public ContainerType getContainterType() {
		return containerType;
	}
	@Override
	public RoomType getType() {
		return this;
	}
	
}
