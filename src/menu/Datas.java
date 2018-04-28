package menu;

public class Datas {
	public static double sp;//游戏速度
	public static String name;//挑战者姓名
	public static int sc;//当前分数
	public static boolean ex = false;//继续逻辑循环与否
	public static boolean cr = true;//是否创建方块
	public static boolean fail = false;//是否判定结束游戏
	public static int jsq;//开关keylistener防止重复计算按键
	public static int next;//下一个方块
	public static int now;//现在的方块类型
	public static int nextcolor;//下一个方块颜色
	public static int nowcolor;//现在的颜色
	public static int keystat;//检测旋转、加速动作
	public Datas() {
		//构造器初始化
	}
}
