package gamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.crypto.Data;
import achievement.*;
import menu.*;
import resource.*;
public class GameThread implements Runnable{
	JPanel thispanel = new JPanel();
	ImageIcon pic2 = new ImageIcon("/Users/qiangkejia/desktop/Study/code/icons/RussianBlock02.jpg");
	ImageIcon bl1 = new ImageIcon("/Users/qiangkejia/desktop/Study/code/icons/RussianBlockOra.jpg");
	ImageIcon bl2 = new ImageIcon("/Users/qiangkejia/desktop/Study/code/icons/RussianBlockYel.jpg");
	ImageIcon bl3 = new ImageIcon("/Users/qiangkejia/desktop/Study/code/icons/RussianBlockBlu.jpg");
	ImageIcon bl4 = new ImageIcon("/Users/qiangkejia/desktop/Study/code/icons/RussianBlockGre.jpg");
	ImageIcon bl = new ImageIcon("/Users/qiangkejia/desktop/Study/code/icons/RussianBlockSpa.jpg");
	ImageIcon pic3 = new ImageIcon("/Users/qiangkejia/desktop/Study/code/icons/RussianBlock03.jpg");
	JLabel[][] a = new JLabel[15][25];//初始化方块组
	public int [][] b = new int[15][26];//初始化方块逻辑数组
	JLabel[][] a1 = new JLabel[4][4];//初始化预显示方块
	int[][] b1 = new int[4][4];
	JLabel temp = new JLabel();
	JLabel score = new JLabel();
	double sp1;
	public GameThread(JPanel thispanel) {
		this.thispanel = thispanel;
	}

	public void run() { //游戏逻辑开始
		if(Datas.jsq == -1) {
			sp1 = Datas.sp;
		}
		Datas.keystat = 0;
		JLabel ic2 = new JLabel();
		JLabel ic3 = new JLabel();
		boolean sd = true;;//第一次屏蔽clear命令
		Datas.next = 2;
		Datas.nextcolor = 2;
		ic2.setIcon(pic2);
		ic2.setBounds(0, 100,300, 500);//重要参数 游戏边界：300宽 400高 方块20平方
		ic3.setIcon(pic3);
		ic3.setBounds(350, 240, 80, 80);
		while(Datas.ex) {//循环开始
			 try {
				Thread.sleep(500-(int)(Datas.sp*300));
			} catch (InterruptedException e) {
				System.out.print("error");
				e.printStackTrace();
			}//延时
			if(Datas.keystat == 1) {
				rows();
			}
			Datas.keystat = 0;
			Datas.sp = sp1;
			Datas.jsq=1;//检测按键开关开启
			if(sd==false) {//是否为第一次循环，否的话清理面板
				clearb();
			}
			else
				down();
			sd = false;
			down();  //方块下行，碰撞检测
			destroy(); //方块消除
			key();//键盘检测，执行操作
			if(Datas.cr) {
				Datas.now = Datas.next;
				Datas.nowcolor = Datas.nextcolor;
				createb();  //如果创造开关开启，那么创造新的方块
			}//添加背景图
			thispanel.requestFocus();//聚焦键盘
			score.setText("当前分数："+Datas.sc);
			score.setBounds(350, 130, 200, 50);
			thispanel.add(score);
			if(Datas.fail) { //游戏输赢检测
				temp.setText("你输了！");
				temp.setBounds(350, 60, 200, 50);
				thispanel.add(temp);
				
			}
			printb(); //打印当前方块
			thispanel.add(ic2);
			thispanel.add(ic3);
			if(Datas.keystat == 2) {
				Datas.sp = 1.5;
			}
			thispanel.validate();
			thispanel.repaint();
			//刷新
		}
		clearb();
		resetb();
		thispanel.remove(ic2);
	}
	private void createb() { //创建方块
		int shape = 0,color,dci;
		double shape0,color0,dci0;
		if(Datas.jsq == -1) {
			while(shape!=3) {
				shape0 = Math.random()*4.99+1;
				shape = (int) shape0;
			}
			Datas.jsq = 0;	
		}
		else {
			shape0 = Math.random()*4.99+1;
			shape = (int) shape0;
		}
		if(shape == 3) {//如果为拐弯，L形状则再进行随机判断生成方块正反
			dci0 = Math.random()*1.99;
			dci = (int) dci0;
			if(dci == 0) {
				shape = 3;
			}
			if(dci == 1) {
				shape = 6;
			}
		}
		if(shape == 2) {
			dci0 = Math.random()*1.99;
			dci = (int) dci0;
			if(dci == 0) {
				shape = 2;
			}
			if(dci == 1) {
				shape = 7;
			}
		}
		Datas.next = shape;
		color0 = Math.random()*3.99+1;
		color = (int) color0;
		Datas.nextcolor = color;
		switch(Datas.now) {
		case 1:{//方块
			b[6][24]=Datas.nowcolor;
			b[7][24]=Datas.nowcolor;
			b[6][23]=Datas.nowcolor;
			b[7][23]=Datas.nowcolor;
			break;
		}
		case 2:{//L形
			b[6][24] = Datas.nowcolor;
			b[6][23] = Datas.nowcolor;
			b[6][22] = Datas.nowcolor;
			b[7][22] = Datas.nowcolor;
			break;
		}
		case 3:{//拐弯形状
			b[6][24] = Datas.nowcolor;
			b[6][23] = Datas.nowcolor;
			b[7][23] = Datas.nowcolor;
			b[7][22] = Datas.nowcolor;
			break;
		}
		case 4:{//直线
			b[6][24] = Datas.nowcolor;
			b[6][23] = Datas.nowcolor;
			b[6][22] = Datas.nowcolor;
			b[6][21] = Datas.nowcolor;
			break;
		}
		case 5:{//凸
			b[7][24] = Datas.nowcolor;
			b[6][23] = Datas.nowcolor;
			b[7][23] = Datas.nowcolor;
			b[8][23] = Datas.nowcolor;
			break;
		}
		case 6:{//反拐弯
			b[6][24] = Datas.nowcolor;
			b[6][23] = Datas.nowcolor;
			b[5][23] = Datas.nowcolor;
			b[5][22] = Datas.nowcolor;
			break;
		}
		case 7:{//反L形
			b[6][24] = Datas.nowcolor;
			b[6][23] = Datas.nowcolor;
			b[6][22] = Datas.nowcolor;
			b[5][22] = Datas.nowcolor;
			break;
		}
		}
		Datas.cr = false;
		//设置方块预显示
		int i,j;
		for(i=0;i<4;i++) {
			for(j=0;j<4;j++) {
				b1[i][j] = 0;
			}
		}
		switch (Datas.next) {
		case 1:{//方块
			b1[1][1] = Datas.nextcolor;
			b1[1][2] = Datas.nextcolor;
			b1[2][1] = Datas.nextcolor;
			b1[2][2] = Datas.nextcolor;
			break;
		}
		case 2:{//L
			b1[1][1] = Datas.nextcolor;
			b1[2][1] = Datas.nextcolor;
			b1[3][1] = Datas.nextcolor;
			b1[3][2] = Datas.nextcolor;
			break;
		}
		case 3:{//拐弯
			b1[1][1] = Datas.nextcolor;
			b1[2][1] = Datas.nextcolor;
			b1[2][2] = Datas.nextcolor;
			b1[3][2] = Datas.nextcolor;
			break;
		}
		case 4:{//直线
			b1[1][1] = Datas.nextcolor;
			b1[2][1] = Datas.nextcolor;
			b1[3][1] = Datas.nextcolor;
			b1[0][1] = Datas.nextcolor;
			break;
		}
		case 5:{//凸
			b1[1][1] = Datas.nextcolor;
			b1[2][0] = Datas.nextcolor;
			b1[2][1] = Datas.nextcolor;
			b1[2][2] = Datas.nextcolor;
			break;
		}
		case 6:{//反拐弯
			b1[1][2] = Datas.nextcolor;
			b1[2][2] = Datas.nextcolor;
			b1[2][1] = Datas.nextcolor;
			b1[3][1] = Datas.nextcolor;
			break;
		}
		case 7:{//反L
			b1[1][2] = Datas.nextcolor;
			b1[2][2] = Datas.nextcolor;
			b1[3][2] = Datas.nextcolor;
			b1[3][1] = Datas.nextcolor;
			break;
		}
		}
	}
	private void down() { //方块下移以及碰撞检测
		int i,j,m,n;
		boolean pj; //是否碰撞
		pj = false;
		//检测是否挑战失败
		for(i=0;i<15;i++) {
			if(b[i][24]<0) {
				//挑战失败
				Datas.cr = false;
				Datas.fail = true;
			}
		}
		if(Datas.fail == false) {
			for (i=0;i<15;i++) {
				for (j=0;j<25;j++) {
					if(b[i][j]>0) { //第一步检测活方块
						if(b[i][j-1]<0 || j==1) {
							pj = true;
						}
					}
				}
			}
			if(pj) {//如果有碰撞
				for(m=0;m<15;m++) {
					for(n=0;n<25;n++) {
						if(b[m][n]>0) {
							b[m][n] = b[m][n]*-1;//杀死方块
						}
					}
				}
				Datas.cr = true; //创建新的方块
			}
			else { //否则向下移动
				for(i=0;i<15;i++) {
					for(j=0;j<25;j++) {
						if(b[i][j]>0) {
							b[i][j-1]=b[i][j];
							b[i][j]=b[i][j+1];
						}
					}
				}
			}
		}
	}
	private void destroy() {//消除方块
		int i,j,m;
		boolean ds;
		ds = true;
		for(j=0;j<25;j++) {
			ds = true;
			for(i=0;i<15;i++) {
				if(b[i][j]>=0) {
					ds = false;
				}
			}
			if(ds) {//j满了
				m = j;
				Datas.sc = Datas.sc + 15;
				for(;m<25;m++) {
					for(i=0;i<15;i++) {
						if(b[i][m]<0) {
							b[i][m]=b[i][m+1];
						}
					}
				}
			}
		}
	}
	private void printb() { // 打印方块
		int i,j;
		for(i=0;i<15;i++) {
			for(j=0;j<25;j++) {
				if(b[i][j]==0) { //判断方块种类
					a[i][j] = new JLabel();
					a[i][j].setIcon(null);
					a[i][j].setBounds(i*20, 600-j*20, 20, 20);
					thispanel.add(a[i][j]);
				}
				if(b[i][j]==1||b[i][j]==-1) { //红
					a[i][j] = new JLabel();
					a[i][j].setIcon(bl1);
					a[i][j].setBounds(i*20, 600-j*20, 20, 20);
					thispanel.add(a[i][j]);
				}
				if(b[i][j]==2||b[i][j]==-2) { //黄
					a[i][j] = new JLabel();
					a[i][j].setIcon(bl2);
					a[i][j].setBounds(i*20, 600-j*20, 20, 20);
					thispanel.add(a[i][j]);
				}
				if(b[i][j]==3||b[i][j]==-3) { //蓝
					a[i][j] = new JLabel();
					a[i][j].setIcon(bl3);
					a[i][j].setBounds(i*20, 600-j*20, 20, 20);
					thispanel.add(a[i][j]);
				}
				if(b[i][j]==4||b[i][j]==-4) { //绿
					
					a[i][j].setIcon(bl4);
					a[i][j].setBounds(i*20, 600-j*20, 20, 20);
					thispanel.add(a[i][j]);
				}
			}
		}
		//显示下一个方块
		for(i=0;i<4;i++) {
			for(j=0;j<4;j++) {
				if(b1[i][j]==0) {
					a1[i][j] = new JLabel();
					a1[i][j].setIcon(null);
					a1[i][j].setBounds(350+i*20, 300 - j*20, 20, 20);
					thispanel.add(a1[i][j]);
				}
				if(b1[i][j]==1) {
					a1[i][j] = new JLabel();
					a1[i][j].setIcon(bl1);
					a1[i][j].setBounds(350+i*20, 300 - j*20, 20, 20);
					thispanel.add(a1[i][j]);
				}
				if(b1[i][j]==2) {
					a1[i][j] = new JLabel();
					a1[i][j].setIcon(bl2);
					a1[i][j].setBounds(350+i*20, 300 - j*20, 20, 20);
					thispanel.add(a1[i][j]);
				}
				if(b1[i][j]==3) {
					a1[i][j] = new JLabel();
					a1[i][j].setIcon(bl3);
					a1[i][j].setBounds(350+i*20, 300 - j*20, 20, 20);
					thispanel.add(a1[i][j]);
				}
				if(b1[i][j]==4) {
					a1[i][j] = new JLabel();
					a1[i][j].setIcon(bl4);
					a1[i][j].setBounds(350+i*20, 300 - j*20, 20, 20);
					thispanel.add(a1[i][j]);
				}
			}
		}
	}
	private void clearb() { //清空方块
		int i,j;
		for(i=0;i<15;i++) {
			for(j=0;j<25;j++) {
				thispanel.remove(a[i][j]);
			}
		}
		for(i=0;i<4;i++) {
			for(j=0;j<4;j++) {
				thispanel.remove(a1[i][j]);
			}
		}
	}
	private void key() { //获取按键,左右横跳
		KeyAdapter k = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				boolean pj;
				pj = false;
				if(Datas.jsq>0) {
					switch (e.getKeyCode()) {
					case 37:{//左
						int i,j;
						for(i=0;i<15;i++) {
							for(j=0;j<25;j++) {
								if(b[i][j]>0 && (i == 0 || b[i-1][j]<0))
									pj = true;
							}
						}
						if(pj==false) {
							for(i=0;i<15;i++) {
								for(j=0;j<25;j++) {
									if(b[i][j]>0 && i!=14) {
										b[i-1][j]=b[i][j];
										b[i][j]=b[i+1][j];
										Datas.jsq=0;
									}else if(b[i][j]>0 && i ==14) {
										b[i-1][j]=b[i][j];
										b[i][j]=0;
										Datas.jsq=0;
									}
								}
							}
						}     
						break;
					}
					case 39:{//右
						int i,j;
						for(i=0;i<15;i++) {
							for(j=0;j<25;j++) {
								if(b[i][j]>0 && (i == 14 || b[i+1][j]<0))
									pj = true;
							}
						}
						if(pj==false) {
							for(i=14;i>=0;i--) {
								for(j=0;j<25;j++) {
									if(b[i][j]>0&&i!=0) {
										b[i+1][j]=b[i][j];
										b[i][j]=b[i-1][j];
										Datas.jsq=0;
									}else if(b[i][j]>0 && i==0) {
										b[i+1][j]=b[i][j];
										b[i][j]=0;
										Datas.jsq=0;
									}
								}
							}
						}   
						break;
					}
					case 38:{//旋转
						Datas.keystat = 1;
						Datas.jsq = 0;
						break;
					}
					case 40:{ //加速
						Datas.keystat =2;
						Datas.jsq = 0;
						break;
					}
					}
				}
			}
		};
		thispanel.addKeyListener(k);
		if(Datas.ex == false) {
			thispanel.removeKeyListener(k);
		}
	}
	private void resetb() { //重置逻辑数组
		int i,j;
		for(i=0;i<15;i++) {
			for(j=0;j<26;j++) {
				b[i][j] = 0;
			}
		}
	}
	private void rows() {
		int i,j;
		new Row(b).rowl();
		for(i=14;i>=0;i--) { //封装一个向右操作抵消滚动
			for(j=0;j<25;j++) {
				if(b[i][j]>0&&i!=0) {
					b[i+1][j]=b[i][j];
					b[i][j]=b[i-1][j];
				}else if(b[i][j]>0 && i==0) {
					b[i+1][j]=b[i][j];
					b[i][j]=0;
				}
			}
		} 
	}
}
