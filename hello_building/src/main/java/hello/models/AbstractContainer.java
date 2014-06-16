package hello.models;

import hello.models.api.ContainerInterface;
import hello.models.types.ContainerType;


public class AbstractContainer {

	private Double size;
	private Double length;
	private Double width;
    protected ContainerInterface<?> type;
	
//    public AbstractContainer(ContainerInterface<?> type,Double containerLength,Double containerWidth){
//		length = containerLength;
//		width = containerWidth;
//		this.type = type;
//	}
	
	public Double getSize(){
		calculateSize();
		return size;
	}
	
	public void setLength(Double length){
		this.length = length;
	}
	
	public Double getLength(){
		return length;
	}
	public Double getWidth(){
		return width;
	}
	public void setWidth(Double wid){
		width = wid;
	}

	private void calculateSize() {
		size = length * width;
		
	}

	public ContainerType getContainterType() {
		return type.getContainterType();
	}
	public void setType(ContainerInterface<?> ct){
		type = ct;
	}
	
	public String getTypeName(){
		return type.getTypeName();
	}
	
	
	
}
