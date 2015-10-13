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
 * �½�һ����ȥ�̳д���JFrame����࣬ȥʹ������Ĺ��ܣ���ô���ʱ���������Ѿ���һ������Ҳ����JFrame���ˣ�ֱ��ȥ���ö���ͺ�
 */
public class Frame2048 extends JFrame {
	private Random rand = new Random();
	private Graphics g;
	private int r, c;
	private Image[] array = new Image[11];
	private int[][] number = new int[4][4];// ��ʱ������ֵ��������ʱ��Ϳ���ȥ�ı�ͼƬ��λ��
	private boolean flag;

	/*
	 * ���������������ں�����Ȼ��ȥͨ����ں���ȥʵ�ֹ���
	 */
	public static void main(String[] args) {
		Frame2048 frame = new Frame2048();
		frame.UI();

	}

	// ��һ�㷽��ȥ��flag�������
	public void setFlag(boolean flag) {
		this.flag = flag;

	}

	// ���幹�캯������ʼ����ʱ���ȥ���غʹ洢ͼƬ����

	public Frame2048() {
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
	 * �����ʼ������ķ���
	 */
	public void UI() {
		this.setTitle("Game2048");
		this.setSize(new Dimension(600, 650));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.getContentPane().setBackground(new Color(88, 88, 88));
		JButton jb = new JButton("��ʼ��Ϸ");
		jb.setFocusable(false);// ���ð�ť���ܵõ�����
		this.setFocusable(true);// ���ô����ý���
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(jb);
		this.setVisible(true);
		g = this.getGraphics();// ��JFrame2048����������ȡ������Ķ���Ȼ�����������ù��캯������Startlistener��������ȥʹ����
		Startlistener sl = new Startlistener(g, this, number);
		jb.addActionListener(sl);
		this.addKeyListener(sl);

	}

	/*
	 * ��дpaint����
	 */
	public void paint(Graphics g) {

		super.paint(g);// ���ø����paint�ķ��� �ػ��������Ͳ����������ʱ��ͱ䲻����Щ����
		// ��ʵ������Ҫ��д����Ϊ�˵�ʱ���괰��֮��Ҳ�ܰ�Ҫ���Ļ������������ڵ�ʱ���ƶ��Ļ�����Ķ����᲻����

		// ���Ʊ������ο�
		g.setColor(Color.LIGHT_GRAY);// ���ô���ο����ɫ
		// ����һ����������ɫ�ľ���
		g.fillRoundRect(65, 70, 450, 450, 15, 15);
		g.setColor(new Color(234, 223, 223));// ����С���ο����ɫ
		// ���ߺ�����
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++)
				g.fillRoundRect(75 + i * 110, 80 + j * 110, 100, 100, 15, 15);
		}
		/*
		 * ����number[][]�������������û��ֵȥ��ͼƬʹ�ú�������ݸı��ˣ������ͼƬҲҪ�ı�λ��
		 */
		// �����ﶨ�����number����ȥ��ͼƬ
		for (int i = 0; i < number.length; i++) {
			for (int j = 0; j < number[i].length; j++) {
				if (number[i][j] != 0) {
					int index = number[i][j];
					index--;// ��ֹ�ػ��ʱ�������Խ��
					int hang = i * 110;
					int lie = j * 110;
					g.drawImage(array[index], 75 + lie, 80 + hang, null);

				}

			}
		}
		//PanDing();
		//System.out.println("flag="+flag);
		
	}

	// ����һ���ж���Ӯ�ĺ���
	public void PanDing() {
		// Ҫ�ǳ�����2048���ͼƬ�Ϳ���ȥ��ʾ��ʾ����ʾӮ��
		for (int i = 0; i < number.length; i++) {
			for (int j = 0; j < number[i].length; j++) {
				if (number[i][j] == 11) {
					JOptionPane.showMessageDialog(null, "��Ӯ��!");
				} else if ((number[i][j] != 0) && (flag != true)) {
					JOptionPane.showMessageDialog(null, "������!");

				}

			}
		}

	}

}
