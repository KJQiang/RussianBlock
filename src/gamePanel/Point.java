package gamePanel;

public class Point { //储存向量
	private int[] x = new int[4];
	private int[] y = new int[4];
	int num;
	public Point(int num,int x,int y) {
		this.num = num;
		this.x[num] = x;
		this.y[num] = y;
	}
	public void setX(int num,int x) {
		this.x[num] = x;
	}
	public void setY(int num,int y) {
		this.y[num] = y;
	}
	public int getX(int num) {
		return this.x[num];
	}
	public int getY(int num) {
		return this.y[num];
	}
}
