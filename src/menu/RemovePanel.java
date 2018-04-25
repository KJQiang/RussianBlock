package menu;

import javax.swing.JPanel;

public class RemovePanel {
	JPanel litt = new JPanel();
	public RemovePanel(JPanel thispanel) {
		//初始化构造器
		litt = thispanel;
	}
	public void ClearPanel() {
		litt.removeAll();
		litt.validate();
		litt.repaint();
	}
}
