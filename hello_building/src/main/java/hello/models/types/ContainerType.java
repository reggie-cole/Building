package hello.models.types;

public enum ContainerType {

	ROOM("Room",false),
	BUILDING("Building",true),
	FLOOR("Floor",true);
	
	private String typeName;
	private boolean hasOtherContatiners;
	
	private ContainerType(String name,boolean hasOthers){		
		typeName = name;
		hasOtherContatiners = hasOthers;
	}
	
	public String getContainterType(){
		return typeName;
	}
	
	public boolean hasOtherContatiners(){
		return hasOtherContatiners;
	}
}
