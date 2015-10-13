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
	// ����һ�����ʵ�����
	private Graphics g;
	private Random rand = new Random();
	private int r1, r2, c1, c2;
	private Image[] array = new Image[11];// �Է������Խ��
	private int[][] number;// ����һ����ά�������洢�±�
	private Frame2048 game;
	private boolean begin = true;
	private boolean flag = false;// �ȸ���һ����ʼֵ��Ȼ����Ϊһ����־λ�ã�ȥ�ж��Ƿ����������б仯������Щ֮ǰ��Ҫ��û�о���false�������
	private int[][] compare = new int[4][4];// �����Աȵ�����
	public Startlistener(Graphics g, Frame2048 game, int[][] number) {
		this.g = g;
		this.game = game;
		this.number = number;// ���Ǳ߶����ˣ���������Ǳߴ�������Ȼ����������ȥ�������ݣ��Ǳߺ����Ҳһ�����������ݣ������Ǳ߸ı���Ҳ����ı���ߵ����ݣ�ֻ�е���ߵ����ݸı��ˣ����ߵ����ݲŻ�ı�
		// ����ͼƬ
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
		// ��ͼƬ�浽���鵱����
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

	// �¼�������
	public void actionPerformed(ActionEvent e) {
		// ����ʹ��ֻ���ǵ�һ�ε����ʱ��ſ��������ͼƬ��Ҫ�ǵ���ڶ��ξ��ǲ��ܳ�ͼƬ��������
		if (begin) {
			randoNumber();
			drawTAF();
			begin = false;
		}
	
	}

	// дһ������2��4�����������Ҫ����Ҫ�������е�λ�ã�Ҫ����ֵ�õط��Ͳ����������ȥ
	public void drawTAF() {
		int hang = r1 * 110;
		int lie = c1 * 110;
		int index = rand.nextInt(2);
		g.drawImage(array[index], 75 + lie, 80 + hang, null);
		// �洢ͼƬ����������������±�
		number[r1][c1] = index + 1;
		hang = r2 * 110;
		lie = c2 * 110;
		index = rand.nextInt(2);
		g.drawImage(array[index], 75 + lie, 80 + hang, null);
		// �洢ͼƬ����������������±�
		number[r2][c2] = index + 1;
		printlnNumber();

	}

	// дһ���������������ķ�����������ж�����������������ͬ

	public void randoNumber() {
		r1 = rand.nextInt(4);
		r2 = rand.nextInt(4);
		c1 = rand.nextInt(4);
		c2 = rand.nextInt(4);
		if (r1 == r2 && c1 == c2) {
			randoNumber();// �ݹ�����Լ���֪�����������������˳���
		}

	}

	// ���������ϵİ���ʱ��ִ�з���
	public void keyTyped(KeyEvent e) {

	}

	// ���¼����ϵİ���ʱ��ִ�з���
	public void keyPressed(KeyEvent e) {
		printlnNumber();
		int keycode = e.getKeyCode();
		int h = 0;
		switch (keycode) {
		case KeyEvent.VK_LEFT:// �����
			System.out.println("�����ˣ�");
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

			// Ҫ������ȥ�����Ǹ����飬������û�����ڵ�λ����û����ͬ�����֣�Ҫ���еĻ�����ӣ�����Ӻ�����ַŵ������ȥ
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
			// ��һ���ܷ����ͼƬ���ж�
			if (flag) {
				randTOF();
			}
			game.repaint();
			break;
		case KeyEvent.VK_RIGHT:// ���Ҽ�
			System.out.println("�����ˣ�");
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
			// Ҫ���ҵ���ȥ�����Ǹ����飬������û�����ڵ�λ����û����ͬ�����֣�Ҫ���еĻ�����ӣ�����Ӻ�����ַŵ������ȥ
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
			// ��һ���жϣ�ֻҪ��ͼƬ���Ͳ���һ���µ�2��4

			if (flag) {
				randTOF();
			}

			game.repaint();

			break;

		case KeyEvent.VK_UP:// ���ϼ�

			System.out.println("�����ˣ�");
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
			// ���ϵ���ȥ������������û����ͬ��Ҫȥ����أ�
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
		case KeyEvent.VK_DOWN:// ���¼�

			System.out.println("�����ˣ�");
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
			// ���µ���ȥ������������û����ͬ�ģ���ȥ���
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
	//�����Ƕ�������߷����ͱ�����ʲô�ģ�Ҫ����Ҫִ���������Ҫ�Ѵ���ŵ�����������������Ƿŵ���ں�������������������������ȥ����ȥ��

	// �ͷż����ϵİ���ʱ��ִ�з���
	public void keyReleased(KeyEvent e) {

	}

	// �����������һ��2������4�ķ���
	public void randTOF() {
		int r = rand.nextInt(4);
		int c = rand.nextInt(4);
		if (number[r][c] == 0) {
			number[r][c] = rand.nextInt(2) + 1;

		}

	}

	

	// ����һ���������������ֵ�ú�������������ı仯
	public void printlnNumber() {
		for (int i = 0; i < number.length; i++) {
			for (int j = 0; j < number[i].length; j++) {
				System.out.print(number[i][j] + "  ");

			}
			System.out.println();
		}
	}
}
