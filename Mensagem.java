package testing;

import java.util.List;

public class Mensagem {
	public String id;
	public int x;
	public Long y;
	public BaseX bx;
	public List<BaseY> by;

	public Mensagem(String id, int x, Long y, BaseX bx, List<BaseY> by) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.bx = bx;
		this.by = by;
	}
	


}
