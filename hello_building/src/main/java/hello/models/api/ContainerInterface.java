package hello.models.api;

import hello.models.types.ContainerType;

public interface ContainerInterface<T> {

	public ContainerType getContainterType();
	public String getTypeName();
	public T getType();
}
