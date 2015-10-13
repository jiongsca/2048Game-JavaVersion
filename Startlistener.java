package com.study0306;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Startlistener implements ActionListener, KeyListener {
	// 声明一个画笔的属性
	private Graphics g;
	private Random rand = new Random();
	private int r1, r2, c1, c2;
	private Image[] array = new Image[11];// 以防数组的越界
	private int[][] number;// 声明一个二维数组来存储下标
	private Frame2048 game;
	private boolean begin = true;
	private boolean flag = false;// 先给其一个初始值，然后做为一个标志位置，去判定是否数组里面有变化，在这些之前，要是没有就是false不能随机
	private int[][] compare = new int[4][4];// 用来对比的数组
	public Startlistener(Graphics g, Frame2048 game, int[][] number) {
		this.g = g;
		this.game = game;
		this.number = number;// 在那边定义了，在这里从那边传过来，然后就是在这边去产生数据，那边和这边也一样可以用数据，但是那边改变了也不会改变这边的数据，只有当这边的数据改变了，两边的数据才会改变
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
		// 把图片存到数组当中呗
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

	// 事件处理方法
	public void actionPerformed(ActionEvent e) {
		// 设置使得只能是第一次点击的时候才可以随机出图片，要是点击第二次就是不能出图片和数据了
		if (begin) {
			randoNumber();
			drawTAF();
			begin = false;
		}
	
	}

	// 写一个产生2和4的随机方法，要的是要遍历所有的位置，要是有值得地方就不能随机到那去
	public void drawTAF() {
		int hang = r1 * 110;
		int lie = c1 * 110;
		int index = rand.nextInt(2);
		g.drawImage(array[index], 75 + lie, 80 + hang, null);
		// 存储图片数组所随机出来的下标
		number[r1][c1] = index + 1;
		hang = r2 * 110;
		lie = c2 * 110;
		index = rand.nextInt(2);
		g.drawImage(array[index], 75 + lie, 80 + hang, null);
		// 存储图片数组所随机出来的下标
		number[r2][c2] = index + 1;
		printlnNumber();

	}

	// 写一个随机生成随机数的方法，里面加判断以免产生的随机数相同

	public void randoNumber() {
		r1 = rand.nextInt(4);
		r2 = rand.nextInt(4);
		c1 = rand.nextInt(4);
		c2 = rand.nextInt(4);
		if (r1 == r2 && c1 == c2) {
			randoNumber();// 递归调用自己，知道不满足条件才能退出来
		}

	}

	// 单击键盘上的按键时的执行方法
	public void keyTyped(KeyEvent e) {

	}

	// 按下键盘上的按键时的执行方法
	public void keyPressed(KeyEvent e) {
		printlnNumber();
		int keycode = e.getKeyCode();
		int h = 0;
		switch (keycode) {
		case KeyEvent.VK_LEFT:// 向左键
			System.out.println("向左了！");
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < 4; j++) {
					if (number[i][j] != 0) {
						int temp = number[i][j];
						int c = j - 1;
						while (c >= 0 && number[i][c] == 0) {
							number[i][c] = temp;
							number[i][c + 1] = 0;
							flag = true;
							c--;
						}

					}

				}
			}

			// 要从左到右去遍历那个数组，看看有没有相邻的位置有没有相同的数字，要是有的话就相加，把想加后的数字放到最后左去
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < 4; j++) {

					if (number[i][j] != 0) {

						int temp = number[i][j];
						int c = j - 1;
						while (c >= 0 && number[i][c] == number[i][j]) {
							number[i][c] += 1;
							flag = true;
							c--;
						}

					}

				}
			}
			// 做一个能否产生图片的判断
			if (flag) {
				randTOF();
			}
			game.repaint();
			break;
		case KeyEvent.VK_RIGHT:// 向右键
			System.out.println("向右了！");
			for (int i = 0; i < 4; i++) {
				for (int j = 2; j >= 0; j--) {

					if (number[i][j] != 0) {
						int temp = number[i][j];
						int c = j + 1;
						while (c < 4 && number[i][c] == 0) {
							number[i][c] = temp;
							number[i][c - 1] = 0;
							flag = true;
							c++;
						}

					}

				}
			}
			// 要从右到左去遍历那个数组，看看有没有相邻的位置有没有相同的数字，要是有的话就相加，把想加后的数字放到最后右去
			for (int i = 0; i < 4; i++) {
				for (int j = 2; j >= 0; j--) {

					if (number[i][j] != 0) {

						int temp = number[i][j];
						int c = j + 1;
						while (c < 4 && number[i][c] == number[i][j]) {
							number[i][c] += 1;
							flag = true;
							c++;
						}

					}

				}
			}
			// 做一个判断，只要有图片动就产生一个新的2或4

			if (flag) {
				randTOF();
			}

			game.repaint();

			break;

		case KeyEvent.VK_UP:// 向上键

			System.out.println("向上了！");
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < 4; j++) {

					if (number[j][i] != 0) {

						int temp = number[j][i];
						int c = j - 1;
						while (c >= 0 && number[c][i] == 0) {
							number[c][i] = temp;
							number[c + 1][i] = 0;
							flag = true;
							c--;
						}

					}

				}
			}
			// 从上到下去遍历，看看有没有相同的要去相加呢！
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < 4; j++) {

					if (number[j][i] != 0) {

						int temp = number[j][i];
						int c = j - 1;
						while (c >= 0 && number[c][i] == number[j][i]) {
							number[c][i] += 1;
							flag = true;
							c--;
						}

					}

				}
			}
			if (flag) {
				randTOF();
			}
			game.repaint();

			break;
		case KeyEvent.VK_DOWN:// 向下键

			System.out.println("向下了！");
			for (int i = 0; i < 4; i++) {
				for (int j = 2; j >= 0; j--) {

					if (number[j][i] != 0) {

						int temp = number[j][i];
						int c = j + 1;
						while (c < 4 && number[c][i] == 0) {
							number[c][i] = temp;
							number[c - 1][i] = 0;
							flag = true;
							c++;
						}

					}

				}
			}
			// 从下到上去遍历，看看有没有相同的，就去相加
			for (int i = 0; i < 4; i++) {
				for (int j = 2; j >= 0; j--) {

					if (number[j][i] != 0) {

						int temp = number[j][i];
						int c = j + 1;
						while (c < 4 && number[c][i] == number[j][i]) {
							number[c][i] += 1;
							flag = true;
							c++;
						}

					}

				}
			}
			if (flag) {
				randTOF();
			}
			game.repaint();

			break;
		}
		
		
	}
	//外面是定义类或者方法和变量的什么的，要是想要执行这个方法要把代码放到函数方法里面或者是放到入口函数方法或者最后就是由主函数去调用去做

	// 释放键盘上的按键时的执行方法
	public void keyReleased(KeyEvent e) {

	}

	// 定义随机生成一张2或者是4的方法
	public void randTOF() {
		int r = rand.nextInt(4);
		int c = rand.nextInt(4);
		if (number[r][c] == 0) {
			number[r][c] = rand.nextInt(2) + 1;

		}

	}

	

	// 定义一个输出数组的里面的值得函数，看看里面的变化
	public void printlnNumber() {
		for (int i = 0; i < number.length; i++) {
			for (int j = 0; j < number[i].length; j++) {
				System.out.print(number[i][j] + "  ");

			}
			System.out.println();
		}
	}
}
