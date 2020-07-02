package com.test.ui;


import com.test.service.Controller;
import com.test.service.ControllerIm;
import com.test.service.TimerIm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LifeGameUI extends JFrame implements ActionListener {
    private Thread thread;
    boolean running;
    private int[][] Map;
    private Controller control = new ControllerIm();
    private TimerIm time = new TimerIm();

    private static LifeGameUI frame;
    private int maxWidth,maxLength;

    private JPanel ZeroPanel;   //主面板

    private JPanel GamePanel;   //显示细胞的panel
    private JButton[][] nGrid;  //一个按钮表示一个细胞

    private JToolBar BottomToolBar; //主菜单栏
    private JPanel BottomPanel;//菜单栏中的Panel
    private JPanel Panel3;//3、
    private JLabel Label3;
    private JComboBox jBox3;
    private JPanel Panel4;//4、
    private JButton ButtonStart;
    private JPanel Panel5;//5、
    private JButton ButtonPause;
    private JPanel Panel6;//6、
    private JButton ButtonReset;
    private JPanel Panel7;//7、
    private JLabel Label7;
    private JButton ButtonText;
    private JPanel Panel8;//7、
    private JButton ButtonNext;

    public LifeGameUI(String title){
        super(title);
        initGUI();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //初始化GUI
    public void initGUI(){
        //初始化窗口大小
        if(maxWidth == 0){
            this.maxWidth = 36;
        }
        if(maxLength == 0){
            this.maxLength = 48;
        }
        //初始化细胞数量....//controller也需要init

        //实现三个主面板
        ZeroPanel = new JPanel(new BorderLayout());
        GamePanel = new JPanel(new GridLayout(maxLength,maxWidth));
        BottomToolBar = new JToolBar();

        this.setContentPane(ZeroPanel); //主面板放进容器中
        //次面板和toolBar放进主面板中
        ZeroPanel.add(GamePanel,"Center");
        ZeroPanel.add(BottomToolBar,"South");
        //向ToolBar中加入各种组件
        //1、向ToolBar中加入BottomPanel;
        BottomPanel = new JPanel();
        BottomToolBar.add(BottomPanel);
        //2、向ToolBar中加入BottomPanel;
        BottomToolBar.add(BottomPanel);

        //2.1.3、向BottomPanel中加入Panel3;
        Panel3 = new JPanel(new FlowLayout());
        BottomPanel.add(Panel3);
        //2.1.3、向Panel2中加入Label2;
        Label3 = new JLabel("速度：");
        Panel3.add(Label3);
        //2.1.3、向Panel1中加入jBox2;
        jBox3 = new JComboBox();
        jBox3.addItem(0);
        jBox3.addItem(1);
        jBox3.addItem(2);
        jBox3.addItem(3);
        jBox3.addItem(4);
        jBox3.addItem(5);
        jBox3.addItem(6);
        jBox3.addItem(7);
        jBox3.addItem(8);
        jBox3.addItem(9);

        Panel3.add(jBox3);
        //2.1.4、向BottomPanel中加入Panel4;
        Panel4 = new JPanel(new FlowLayout());
        BottomPanel.add(Panel4);
        //2.1.4向Panel4中加入ButtonStart
        ButtonStart = new JButton("开始");
        Panel4.add(ButtonStart);
        //2.1.5、向BottomPanel中加入Panel5;
        Panel5 = new JPanel(new FlowLayout());
        BottomPanel.add(Panel5);
        //2.1.5向Panel4中加入ButtonPause
        ButtonPause = new JButton("暂停");
        Panel5.add(ButtonPause);
        //2.1.6、向BottomPanel中加入Panel6;
        Panel6 = new JPanel(new FlowLayout());
        BottomPanel.add(Panel6);
        //2.1.6向Panel4中加入ButtonReset
        ButtonReset = new JButton("复位");
        Panel6.add(ButtonReset);

        //2.1.7、向BottomPanel中加入Panel7;
        Panel7 = new JPanel(new FlowLayout());
        BottomPanel.add(Panel7);
        //2.1.7向Panel7中加入Label7
        Label7 = new JLabel("代数：");
        Panel7.add(Label7);
        //2.1.8向Panel7中添加textFile7
        ButtonText = new JButton("0");
        Panel7.add(ButtonText);
        Panel8 = new JPanel();
        BottomPanel.add(Panel8);
        ButtonNext = new JButton("下一代");
        Panel8.add(ButtonNext);
        //3、向GamePanel加入nGrid[][]按钮
        nGrid = new JButton[maxLength][maxWidth];   //实现nGrid
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < maxWidth; j++) {
                nGrid[i][j] = new JButton(""); //按钮内容置空以表示细胞
                nGrid[i][j].setBackground(Color.WHITE); //初始时所有细胞均为死
                GamePanel.add(nGrid[i][j]);     //加入按钮
            }
        }
        // 设置窗口
        this.setSize(maxWidth*20, maxLength*20);
        this.setResizable(true);
        this.setLocationRelativeTo(null); // 让窗口在屏幕居中
        this.setVisible(true);// 将窗口设置为可见的
        //注册监听器。。。。。
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
        //1.startButton
        ButtonStart.addActionListener(this);
        //2.pauseButton
        ButtonPause.addActionListener(this);
        //3.ResetButton
        ButtonReset.addActionListener(this);
        //ButtonNext
        ButtonNext.addActionListener(this);
        //加入所有细胞按钮的监听
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < maxWidth; j++) {
                nGrid[i][j].addActionListener(this);
            }
        }
    }

    public int getMaxLength() {
        return maxLength;
    }
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
    public int getMaxWidth(){
        return maxWidth;
    }
    public void setMaxWidth(int maxWidth){
        this.maxWidth = maxWidth;
    }

    public static void main(String[] args) {
        frame = new LifeGameUI("LifeGame");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        //监听所有的显示按钮
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < maxWidth; j++) {
                if(source==nGrid[i][j]){
                    if(nGrid[i][j].getBackground().equals(Color.WHITE)){
                        nGrid[i][j].setBackground(Color.BLACK);
                    }else if(nGrid[i][j].getBackground().equals(Color.BLACK)){
                        nGrid[i][j].setBackground(Color.WHITE);
                    }
                }
            }
        }
       
        //程序开始跑起来
        if(source == ButtonStart){
            //调用start函数
            running = true;
            thread = new Thread(new Runnable() {
               @Override
               public void run() {
                   ButtonStart.setEnabled(false);//开始不可用
                   ButtonReset.setEnabled(false);   //复位不可用
                   ButtonNext.setEnabled(false);
                   int speed = (Integer) jBox3.getItemAt(jBox3.getSelectedIndex());
                   jBox3.setEnabled(false);
                   while(running){
                       try {
                           thread.sleep(1000 - (speed*100));
                       } catch (InterruptedException ex) {
                           ex.printStackTrace();
                       }
                       startUI();
                   }
                   ButtonReset.setEnabled(true);   //复位不可用
                   ButtonStart.setEnabled(true);
                   ButtonNext.setEnabled(true);
                   jBox3.setEnabled(true);
               }
            });
            thread.start();
        }
        else if(source == ButtonPause ){
            running = false;
        }else if(source == ButtonReset){
            control.resetGeneration();
            ButtonText.setText("0");
            resetUI();
        }else if(source == ButtonNext){
            startUI();
        }
    }
    public void startUI() {
        //如果界面细胞全死了，停止
        boolean life = false;
        //start之后实现Map，但是之后的length，width不允许改变。init control;
        Map = new int[this.maxLength][this.maxWidth];
        //初始化
        for (int i = 0; i < this.maxLength; i++) {
            for (int j = 0; j < this.maxWidth; j++) {
                if (nGrid[i][j].getBackground() == Color.BLACK) {
                    Map[i][j] = 1;  //1表示细胞室是活的
                    life = true;    //标记有没有活细胞
                } else {
                    Map[i][j] = 0;  //0表示细胞室是活的
                }
            }
        }
        if(life == false){
            running = false;
        }
        control.init(Map);
        //调用nextGeneration,生成新的Map
        control.nextGeneration(Map);
        //打印输出Map，循环。
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < maxWidth; j++) {
                if(Map[i][j] == 1){
                    nGrid[i][j].setBackground(Color.BLACK); //初始时所有细胞均为活的
                    System.out.print("*");
                }else{
                    nGrid[i][j].setBackground(Color.WHITE); //初始时所有细胞均为死
                    System.out.print("-");
                }
            }
            System.out.println();
        }
        //System.out.println(control.getGeneration());
        ButtonText.setText(String.valueOf(control.getGeneration()));
    }
    public void resetUI() {
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < maxWidth; j++) {
                    nGrid[i][j].setBackground(Color.WHITE); //初始时所有细胞均为死
            }
        }
    }
}
