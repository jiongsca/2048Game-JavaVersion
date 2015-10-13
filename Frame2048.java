package com.study0306;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * 新建一个类去继承窗体JFrame这个类，去使用里面的功能，这么这个时候这个类就已经是一个子类也就是JFrame的了，直接去调用对象就好
 */
public class Frame2048 extends JFrame {
	private Random rand = new Random();
	private Graphics g;
	private int r, c;
	private Image[] array = new Image[11];
	private int[][] number = new int[4][4];// 到时候把这个值传过来到时候就可以去改变图片的位置
	private boolean flag;

	/*
	 * 定义这个函数的入口函数，然后去通过入口函数去实现功能
	 */
	public static void main(String[] args) {
		Frame2048 frame = new Frame2048();
		frame.UI();

	}

	// 用一般方法去传flag这个参数
	public void setFlag(boolean flag) {
		this.flag = flag;

	}

	// 定义构造函数，初始化的时候就去加载和存储图片了呗

	public Frame2048() {
		// 加载图片
		Image image2 = new ImageIcon("Image/2.png").getImage();
		Image image4 = new ImageIcon("Image/4.png").getImage();
		Image image8 = new ImageIcon("Image/8.jpg").getImage();
		Image image16 = new ImageIcon("Image/16.jpg").getImage();
		Image image32 = new ImageIcon("Image/32.jpg").getImage();
		Image image64 = new ImageIcon("Image/64.jpg").getImage();
		Image image128 = new ImageIcon("Image/128.jpg").getImage();
		Image image256 = new ImageIcon("Image/256.jpg").getImage();
		Image image512 = new ImageIcon("Image/512.jpg").getImage();
		Image image1024 = new ImageIcon("Image/1024.jpg").getImage();
		Image image2048 = new ImageIcon("Image/2048.jpg").getImage();
		array[0] = image2;
		array[1] = image4;
		array[2] = image8;
		array[3] = image16;
		array[4] = image32;
		array[5] = image64;
		array[6] = image128;
		array[7] = image256;
		array[8] = image512;
		array[9] = image1024;
		array[10] = image2048;

	}

	/*
	 * 定义初始化界面的方法
	 */
	public void UI() {
		this.setTitle("Game2048");
		this.setSize(new Dimension(600, 650));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.getContentPane().setBackground(new Color(88, 88, 88));
		JButton jb = new JButton("开始游戏");
		jb.setFocusable(false);// 设置按钮不能得到焦点
		this.setFocusable(true);// 设置窗体获得焦点
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(jb);
		this.setVisible(true);
		g = this.getGraphics();// 从JFrame2048这个类这里获取画笔类的对象，然后把这个对象用构造函数传到Startlistener，类里面去使用呗
		Startlistener sl = new Startlistener(g, this, number);
		jb.addActionListener(sl);
		this.addKeyListener(sl);

	}

	/*
	 * 重写paint方法
	 */
	public void paint(Graphics g) {

		super.paint(g);// 调用父类的paint的方法 重画，这样就不会在扩大的时候就变不见那些数字
		// 其实在这里要重写就死为了到时候画完窗体之后也能把要画的画出来而不至于到时候移动的话上面的东西会不见咧

		// 绘制背景矩形框
		g.setColor(Color.LIGHT_GRAY);// 设置大矩形框的颜色
		// 绘制一个带背景颜色的矩形
		g.fillRoundRect(65, 70, 450, 450, 15, 15);
		g.setColor(new Color(234, 223, 223));// 设置小矩形框的颜色
		// 划线横竖线
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++)
				g.fillRoundRect(75 + i * 110, 80 + j * 110, 100, 100, 15, 15);
		}
		/*
		 * 根据number[][]这个数组里面有没有值去画图片使得后面的数据改变了，外面的图片也要改变位置
		 */
		// 在这里定义根据number数组去画图片
		for (int i = 0; i < number.length; i++) {
			for (int j = 0; j < number[i].length; j++) {
				if (number[i][j] != 0) {
					int index = number[i][j];
					index--;// 防止重绘的时候数组的越界
					int hang = i * 110;
					int lie = j * 110;
					g.drawImage(array[index], 75 + lie, 80 + hang, null);

				}

			}
		}
		//PanDing();
		//System.out.println("flag="+flag);
		
	}

	// 定义一个判断输赢的函数
	public void PanDing() {
		// 要是出现了2048这个图片就可以去显示提示框显示赢了
		for (int i = 0; i < number.length; i++) {
			for (int j = 0; j < number[i].length; j++) {
				if (number[i][j] == 11) {
					JOptionPane.showMessageDialog(null, "你赢了!");
				} else if ((number[i][j] != 0) && (flag != true)) {
					JOptionPane.showMessageDialog(null, "你输了!");

				}

			}
		}

	}

}
