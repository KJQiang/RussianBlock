package achievement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import java.io.*;

import menu.CreateMenu;
import menu.RemovePanel;

public class AcheivePanel extends CreateMenu { //记分板类
	JPanel thispanel = new JPanel();
	JButton back = new JButton("Back");//返回主菜单按钮
	public static ArrayList<Users> table = new ArrayList<Users>();
	JLabel topic = new JLabel("挑战者：                他获得了：");
	boolean bj;
	public AcheivePanel(JPanel thispanel,boolean bj) { //构造器
		this.thispanel = thispanel;
		this.bj = bj;
	}
	
	public void createAch() { //主方法
		thispanel.setLayout(null);
		topic.setBounds(100,100,400,100);
		topic.setFont(new   java.awt.Font("Dialog",1,20));
		thispanel.add(topic);
		if(bj)
		read("temp.txt");
		bj = false;
		sort();
		backbutton();
		printf();
	}
	
	private void sort() {
		int i,j,sctemp;
		String nmtemp = null;
		for(i=0;i<table.size();i++) {
			for(j=i+1;j<table.size();j++) {
				if(table.get(i).getSc()<table.get(j).getSc()) {
					sctemp = table.get(i).getSc();
					nmtemp = table.get(i).getName();
					table.get(i).setName(table.get(j).getName());
					table.get(i).setSc(table.get(j).getSc());
					table.get(j).setName(nmtemp);
					table.get(j).setSc(sctemp);
				}
			}
		}//按分数排序
		if(table.size()>6) {
			for(i=6;i<table.size();i++) {
				table.remove(i);
			}
		}//删除第八名以后的信息
	}
	private void read(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String temp = null;
            // 一次读入一行，直到读入null为文件结束
            while ((temp = reader.readLine()) != null) {
                int i,sci;
                String name,sc;
                sci = 0;
                i=0;
                while(temp.charAt(i)!=' ') {
                	i++;
                }
                name = temp.substring(0, i);
                sc = temp.substring(i+1);
                sci = Integer.parseInt(sc);
                table.add(new Users(name,sci));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
	private void write(String filename) throws IOException {
		File f = new File(filename);
		f.createNewFile();
		FileOutputStream fout = new FileOutputStream(f);  
		PrintStream printStream = new PrintStream(fout); 
		System.setOut(printStream);
		int i;
		for(i=0;i<table.size();i++) {
			System.out.println(table.get(i).getName()+" "+table.get(i).getSc());
		}
	}
	
	private void backbutton() { //返回按钮
		back.setBounds(200, 20, 100, 50);
		thispanel.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					write("temp.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new RemovePanel(thispanel).ClearPanel();
				createMenu(thispanel);
				startButton(thispanel);
			}
		});
	}
	private void printf() { //打印
		int i;
		for(i=0;i<table.size();i++) {
			JLabel j = new JLabel();
			j.setText(table.get(i).getName()+"                      "+table.get(i).getSc()+"分");
			j.setBounds(120, i*60+200, 500, 60);
			j.setFont(new   java.awt.Font("Dialog",1,18));
			thispanel.add(j);
		}
	}
}
