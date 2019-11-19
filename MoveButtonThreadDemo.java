package chapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MoveButtonThreadDemo extends JFrame implements Runnable{
	JPanel p;
	JButton btnMove;
	Thread t;
	int movex=5;
	int movey=5;
	public MoveButtonThreadDemo() {
		super("°´Å¥ÒÆ¶¯");
		p=new JPanel(null);
		btnMove=new JButton("ÒÆ¶¯");
		btnMove.setBounds(0,100,80,25);
		p.add(btnMove);
		this.add(p);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t=new Thread(this);
		t.start();
	}
	public void run() {
		while(t.isAlive()) {
			int x=btnMove.getX()+movex;
			int y=btnMove.getY()+movey;
			if(x<=0) {
				x=0;
				movex=-movex;
			}else if(x>=this.getWidth()-btnMove.getWidth()) {
				x=this.getWidth()-btnMove.getWidth();
				movex=-movex;
			}
			if(y<=0) {
				y=0;
				movey=-movey;
			}else if(y>=this.getHeight()-30-btnMove.getHeight()) {
				y=this.getHeight()-30-btnMove.getHeight();
				movey=-movey;
			}
			btnMove.setLocation(x,y);
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[]args) {
		MoveButtonThreadDemo f=new MoveButtonThreadDemo();
		f.setVisible(true);
	}
}
