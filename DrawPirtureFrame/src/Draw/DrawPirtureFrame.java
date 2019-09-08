package Draw;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import java.awt.color.*;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class DrawPirtureFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage image = new BufferedImage(1160, 830, BufferedImage.TYPE_INT_BGR);
	Graphics gs = image.getGraphics();
	Graphics2D g = (Graphics2D) gs;
	Component canvas = new DrawPictureCanvas();
	Color forecColor = Color.black;
	Color backgroundColor = Color.white;
	int x = -1;
	int y = -1;
	boolean rubber = false;
	/*
	 * ������ť���˵����
	 */
	private JToolBar toolBar;
	private JButton eraserButton;
	private JToggleButton strokeButton1;
	private JToggleButton strokeButton2;
	private JToggleButton strokeButton3;
	private JButton backgroundButton;
	private JButton foreroundButton;
	private JButton clearButton;
	private JButton savebButton;
	private JButton shapeButton;

	private JMenuItem strokeMenuItem1;
	private JMenuItem strokeMenuItem2;
	private JMenuItem strokeMenuItem3;
	private JMenuItem clearMenuItem;
	private JMenuItem foregroundMenuItem;
	private JMenuItem backgroundItem;
	private JMenuItem eraserMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem saveMenuItem;

	private String shuiyin = "";
	private JMenuItem shuiyinMenuItem;

	public void DrawPictureFrame() {
		setResizable(false);//��Ϊ���ɸ��Ĵ�С
		setTitle("��ͼ����(ˮӡ���ݣ�[" + shuiyin + "])");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(500, 100, 1160, 920);
		init();
		addListener();

	}

	private void init() {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, 1160, 830);
		g.setColor(forecColor);
		canvas.setImage(image);
		getContentPane().add(canvas);

		toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);//�����������ڴ����ϱ�
		
		/*
		 * ���ø����λ��
		 */
		savebButton = new JButton("����");
		savebButton.setToolTipText("����");
		toolBar.add(savebButton);
		toolBar.addSeparator();

		strokeButton1 = new JToggleButton("ϸ��");
		strokeButton1.setSelected(true);
		toolBar.add(strokeButton1);

		strokeButton2 = new JToggleButton("�ϴ���");
		toolBar.add(strokeButton2);

		strokeButton3 = new JToggleButton("����");

		ButtonGroup strokeGroup = new ButtonGroup();
		strokeGroup.add(strokeButton1);
		strokeGroup.add(strokeButton2);
		strokeGroup.add(strokeButton3);
		toolBar.add(strokeButton3);
		toolBar.addSeparator();
		backgroundButton = new JButton("������ɫ");
		toolBar.add(backgroundButton);
		foreroundButton = new JButton("������ɫ");
		toolBar.add(foreroundButton);
		toolBar.addSeparator();

		clearButton = new JButton("���");
		toolBar.add(clearButton);
		eraserButton = new JButton("��Ƥ");
		toolBar.add(eraserButton);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu systemMenu = new JMenu("ϵͳ");
		menuBar.add(systemMenu);
		shuiyinMenuItem = new JMenuItem("ˮӡ");
		systemMenu.add(shuiyinMenuItem);
		saveMenuItem = new JMenuItem("����");
		systemMenu.add(saveMenuItem);
		systemMenu.addSeparator();
		exitMenuItem = new JMenuItem("�˳�");
		systemMenu.add(exitMenuItem);

		JMenu strokeMenu = new JMenu("����");
		menuBar.add(strokeMenu);
		strokeMenuItem1 = new JMenuItem("ϸ��");
		strokeMenu.add(strokeMenuItem1);
		strokeMenuItem2 = new JMenuItem("�ϴ���");
		strokeMenu.add(strokeMenuItem2);
		strokeMenuItem3 = new JMenuItem("����");
		strokeMenu.add(strokeMenuItem3);

		JMenu colorMenu = new JMenu("��ɫ");
		menuBar.add(colorMenu);
		foregroundMenuItem = new JMenuItem("ǰ����ɫ");
		colorMenu.add(foregroundMenuItem);
		backgroundItem = new JMenuItem("������ɫ");
		colorMenu.add(backgroundItem);

		JMenu editMenu = new JMenu("�༭");
		menuBar.add(editMenu);
		clearMenuItem = new JMenuItem("���");
		editMenu.add(clearMenuItem);
		eraserMenuItem = new JMenuItem("��Ƥ");
		editMenu.add(eraserMenuItem);

	}

	private void addListener() {
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(final MouseEvent e) {
				if (x > 0 && y > 0) {
					if (rubber) {
						g.setColor(backgroundColor);
						g.fillRect(x, y, 20, 20);
					} else {
						g.drawLine(x, y, e.getX(), e.getY());
					}
				}
				x = e.getX();
				y = e.getY();
				canvas.repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if (rubber) {
					Toolkit kit = Toolkit.getDefaultToolkit();
					setCursor(DEFAULT_CURSOR);//���������ʾ��ʽ
				} else {
					setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				}
			}
		});
		canvas.addMouseListener(new MouseAdapter() {
			public void mouseReleased(final MouseEvent arg0) {
				x = -1;
				y = -1;
			}
		});

		strokeButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g.setStroke(bs);
			}
		});

		strokeButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BasicStroke bs = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g.setStroke(bs);
			}
		});

		strokeButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BasicStroke bs = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g.setStroke(bs);
			}
		});

		backgroundButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Color bgColor = JColorChooser.showDialog(DrawPictureFrame.this, "ѡ����ɫ�Ի���", Color.CYAN);
				if (bgColor != null) {
					backgroundColor = bgColor;
				}

				backgroundButton.setBackground(backgroundColor);
				g.setColor(backgroundColor);
				g.fillRect(0, 0, 1160, 830);
				g.setColor(forecColor);
				canvas.repaint();

			}
		});

		foreroundButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Color fColor = JColorChooser.showDialog(DrawPictureFrame.this, "ѡ����ɫ�Ի���", Color.CYAN);
				if (fColor != null) {
					forecColor = fColor;
				}

				foreroundButton.setBackground(forecColor);
				g.setColor(forecColor);

			}
		});

		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				g.setColor(backgroundColor);
				g.fillRect(0, 0, 1160, 830);
				g.setColor(forecColor);
				canvas.repaint();

			}
		});

		eraserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (eraserButton.getText().equals("��Ƥ")) {
					rubber = true;
					eraserMenuItem.setText("��ͼ");
					eraserButton.setText("��ͼ");
				} else {
					rubber = false;
					eraserMenuItem.setText("��Ƥ");
					eraserButton.setText("��Ƥ");
					g.setColor(forecColor);
				}

			}
		});

		savebButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addWatermark();
			}
		});

		// ϸ�߲˵���
		strokeMenuItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g.setStroke(bs);
			}
		});

		// �ϴ��߲˵���
		strokeMenuItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BasicStroke bs = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g.setStroke(bs);
			}
		});

		// ���߲˵���
		strokeMenuItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BasicStroke bs = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g.setStroke(bs);
			}
		});

		foregroundMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Color fColor = JColorChooser.showDialog(DrawPictureFrame.this, "ѡ����ɫ�Ի���", Color.CYAN);
				if (fColor != null) {
					forecColor = fColor;
				}

				foreroundButton.setBackground(forecColor);
				g.setColor(forecColor);

			}
		});

		backgroundItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Color bgColor = JColorChooser.showDialog(DrawPictureFrame.this, "ѡ����ɫ�Ի���", Color.CYAN);
				if (bgColor != null) {
					backgroundColor = bgColor;
				}

				backgroundButton.setBackground(backgroundColor);
				g.setColor(backgroundColor);
				g.fillRect(0, 0, 1160, 830);
				g.setColor(forecColor);
				canvas.repaint();

			}
		});

		clearMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				g.setColor(backgroundColor);
				g.fillRect(0, 0, 1160, 830);
				g.setColor(forecColor);
				canvas.repaint();

			}
		});

		eraserMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (eraserButton.getText().equals("��Ƥ")) {
					rubber = true;
					eraserButton.setText("��ͼ");
					eraserMenuItem.setText("��ͼ");
				} else {
					rubber = false;
					eraserButton.setText("��Ƥ");
					eraserMenuItem.setText("��Ƥ");
					g.setColor(forecColor);
				}

			}
		});

		exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		shuiyinMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				shuiyin = JOptionPane.showInputDialog(DrawPictureFrame.this, "�������ʲôˮӡ��");
				if (null == shuiyin) {
					shuiyin = "";
				} else {
					setTitle("��ͼ����(ˮӡ���ݣ�[" + shuiyin + "])");
				}

			}
		});

		toolBar.addMouseMotionListener(new MouseMotionAdapter() {//�������������¼�����
			@Override
			public void mouseMoved(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));//����������״ΪĬ�Ϲ��

			}

		});
	}

	private void addWatermark() {
		if (!"".equals(shuiyin.trim())) {// ���ˮӡ�ֶβ��ǿ��ַ���
			g.rotate(Math.toRadians(-30));
			Font font = new Font("����", Font.BOLD, 72);
			g.setFont(font);
			g.setColor(Color.GRAY);
			AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);//
			g.setComposite(alpha);
			g.drawString(shuiyin, 350, 900);
			canvas.repaint();

			g.rotate(Math.toRadians(30));// ����ת��ͼƬ��ת����
			alpha = AlphaComposite.SrcOver.derive(1f);// ��͸��Ч��
			g.setComposite(alpha);// ʹ�ò�͸��Ч��
			g.setColor(forecColor);// ���ʻָ�֮ǰ����ɫ
		}
	}

	public static void main(String[] args) {
		DrawPictureFrame frame = new DrawPictureFrame();
		frame.setVisible(true);

	}

}




