package model.tool;

import java.util.List;

public abstract class Tool<E> {
	public abstract boolean isExist(E obj);
	public abstract int add(E obj);
	public abstract boolean remove(E obj);
	public abstract boolean edit(E obj);
	public abstract E find(int index);
	public abstract E find(E obj);
	public abstract List<E> findAll();
}
