package gamePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import resource.*;
import menu.*;
public class CreateGame extends CreateMenu {
	JPanel gamePanel = new JPanel();
	JButton quit = new JButton("Quit!");
	JLabel challenger = new JLabel();
	public CreateGame(JPanel thispanel) {
		gamePanel = thispanel;
	}
	public void createGame() {
		gamePanel.setLayout(null);
		quit.setBounds(350, 30, 50,50);
		gamePanel.add(quit);
		quitButton();
		challenger.setText("挑战者："+Datas.name);
		challenger.setBounds(350,100,200,50);
		gamePanel.add(challenger);
		//初始化，添加退出按钮
		//游戏线程建立
		Datas.cr = true;//建立第一个方块
		Datas.fail = false;//重制输赢状态
		Datas.jsq = -1; //第一次运行标记
		Datas.sc = 0; //分数清零
		Thread game = new Thread(new GameThread(gamePanel));
		game.start();
		if(Datas.ex == false) {
			game.interrupt();
		}
	}
	
	public void quitButton() { //退出按钮
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Datas.ex = false;
				RemovePanel re_game = new RemovePanel(gamePanel);
				re_game.ClearPanel();
				createMenu(gamePanel);
				startButton(gamePanel);
			}
		});
	}
}
