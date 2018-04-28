package gamePanel;
import java.util.Vector;
public class Row { //旋转模块
	int[][] b = new int[15][26];
	int i1,j1;
	Vector<Point> points = new Vector<Point>(4);//矢量空间
	int ax=0,ay=0;
	public Row(int[][] b) { //初始化构造器
		this.b = b;
	}
	private void getPoint() {
		int i,j,js = 0;
		js = 0;
		for(i=0;i<15;i++) {
			for(j=0;j<25;j++) {
				if(b[i][j]>0) {
					if(js == 0) {
						i1 =i; j1 = j;
					}
					Point p = new Point(js,i-i1,j-j1);
					points.add(p);
					js++;
				}
			}
		}
	}
	private void change() { //魔法
		int tempx = 0, tempy =0;
		int i;
		for(i=1;i<4;i++) {
			tempx = points.get(i).getX(i);
			tempy = points.get(i).getY(i);
			points.get(i).setX(i,-tempy);
			points.get(i).setY(i,tempx);
		}
	}
	private void clear() { //清理点集
		int i;
		for(i=0;i<4;i++) {
			points.remove(i);
		}
	}
	public void rowl() {//主方法
		getPoint();
		change();
		int i,j,color = 0;
		for(i=0;i<15;i++) {//删除所有活方块
			for(j=0;j<25;j++) {
				if(b[i][j]>0) {
					color = b[i][j];
					b[i][j] = 0;
				}
			}
		}
		for(i=0;i<4;i++) {
			b[points.get(i).getX(i)+i1][points.get(i).getY(i)+j1] = color;
		}
		//clear();
	}
}