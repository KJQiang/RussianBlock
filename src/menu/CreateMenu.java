package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import gamePanel.*;
import achievement.*;
import resource.*;
public class CreateMenu {
	static ImageIcon pic1 = new ImageIcon("/Users/qiangkejia/desktop/Study/code/icons/RussianBlock01.jpg");
	public static JTextField entername1 = new JTextField(10);//输入姓名框
	public static JRadioButton sp1 = new JRadioButton("慢速");//速度单选框
	public static JRadioButton sp3 = new JRadioButton("中速");
	public static JRadioButton sp2 = new JRadioButton("快速");
	public static ButtonGroup spgroup = new ButtonGroup();
	public static JPanel menupanel = new JPanel();//主菜单面板
	public static JFrame menuwindow = new JFrame("Russian Blocks");
	public static boolean achb = true;
//-----------------------------------主类公共对象--------------------------------------	
	public static void main(String[] args) { //建立主菜单窗口
		menuwindow.setSize(500, 640);
		menuwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuwindow.getContentPane().add(menupanel);
		menupanel.setLayout(null);
		createMenu(menupanel);
		startButton(menupanel);
		menuwindow.setVisible(true);
	}
	public static void createMenu(JPanel thispanel) { //向主菜单窗口添加组件
		JLabel welcome = new JLabel("<html><body>"+"俄罗斯方块，由KJQiang制作"+"<br>"+" &nbsp &nbsp选择游戏难度，然后开始"+"<body></html>");
		welcome.setBounds(180, 464, 200, 50);
		thispanel.add(welcome);
		//添加一个简陋的欢迎字幕
		JLabel ic1 = new JLabel();
		ic1.setIcon(pic1);
		ic1.setBounds(180, 28, 170, 168);
		thispanel.add(ic1);
		//添加一个简陋的图片
		JLabel entername = new JLabel("挑战者！留下你的姓名");
		entername.setBounds(138, 174, 271, 81);
		entername.setFont(new   java.awt.Font("Dialog",1,25));   
		thispanel.add(entername);
		entername1.setBounds(191,253,150,40);
		thispanel.add(entername1);
		//键入名字
		spgroup.add(sp1);
		sp1.setBounds(138, 299, 60, 20);
		spgroup.add(sp2);
		sp2.setBounds(336, 299, 60, 20);
		spgroup.add(sp3);
		sp3.setBounds(237,299,60,20);
		thispanel.add(sp1);
		thispanel.add(sp2);
		thispanel.add(sp3);
		//选择游戏速度
		acheiveButton();
		//成就按钮
	}
	public static void acheiveButton() {
		JButton ach = new JButton("历史分数");
		ach.setBounds(282, 361, 71, 40);
		menupanel.add(ach);
		ach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RemovePanel(menupanel).ClearPanel();
				new AcheivePanel(menupanel,achb).createAch();
				achb = false;
			}
		});
	}
	public static void startButton(JPanel thispanel) { //开始按钮 储存初始化数据并清空面板
		JButton sta = new JButton("Start!");
		sta.setBounds(171, 361, 60, 40);
		thispanel.add(sta);
		sta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Datas.name = entername1.getText();
				Datas.ex = true;
				if(sp1.isSelected()) {
					Datas.sp = 0;
				}
				if(sp2.isSelected()) {
					Datas.sp = 1;
				}
				if(sp3.isSelected()) {
					Datas.sp = 0.5;
				}
				//数据储存
				RemovePanel re_menu = new RemovePanel(thispanel);
				re_menu.ClearPanel();
				//清空面板
				CreateGame game = new CreateGame(thispanel);
				game.createGame();
			}
		});
	}
}
