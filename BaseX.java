package testing;

import java.util.List;

public class BaseX {
	public List<Long> numbers;
	public InBaseX inBaseX;
	public List<InBaseX> listInBaseX;
	public List<Long> unitializedList;

	public BaseX(List<Long> numbers, InBaseX inBaseX, List<InBaseX> listInBaseX, List<Long> unitializedList) {
		super();
		this.numbers = numbers;
		this.inBaseX = inBaseX;
		this.listInBaseX = listInBaseX;
		this.unitializedList = unitializedList;
	}
	

}