/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skatingman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Scanner;


/**
 *
 * @author Lenovo
 */
public class Multiplayertest2 extends JPanel implements ActionListener,KeyListener  {
    
    private int space;  //space bt opposite cars
    private int width=56;  //cars width
    private int height=100;  //cars heidth
    private int width1=100; //tree width
    private int height1=100; //tree height
    private int speed;   //speed of opposite car
    private int WIDTH=500;  //frame
    private int HEIGHT=700; //frame
    private int move=20;
    private int count=1;
    private int level=0;
    private Rectangle car;
    private Rectangle car2;
    private ArrayList <Rectangle> ocars;
    private ArrayList <Rectangle> line;
    private ArrayList <Rectangle> tree;
    private Random rand;
    Timer t;
 
   Scanner sc=new Scanner(System.in);
    BufferedImage sktman,sktman2;
    BufferedImage ocar1,ocar2,ocar3;
    BufferedImage tr1,bg,road;
    Boolean linef=true;
    
    
    public Multiplayertest2() throws IOException{
        
       sktman=ImageIO.read(new File("D:\\Other Study\\CAR\\sktman3.png"));
      sktman2=ImageIO.read(new File("D:\\Other Study\\CAR\\sktman3.png"));
       tr1=ImageIO.read(new File("D:\\Other Study\\CAR\\tree3.png"));
       bg=ImageIO.read(new File("D:\\Other Study\\CAR\\bg1.png"));
       ocar1=ImageIO.read(new File("D:\\Other Study\\CAR\\ocar8.png"));
       road=ImageIO.read(new File("D:\\Other Study\\CAR\\road.png"));
        rand=new Random();
        
        
        tree =new ArrayList<Rectangle>();
        ocars=new ArrayList<Rectangle>();
        line=new ArrayList<Rectangle>();
        car =new Rectangle(WIDTH/2-90,HEIGHT-100,width,height);
        car2 =new Rectangle(WIDTH/2+10,HEIGHT-100,width,height);
        t=new Timer(20,this);
        space=300;
        speed=2;
        
        addKeyListener(this);
        setFocusable(true);
        
        addOcars(true);
        addOcars(true);
        addOcars(true);
        addOcars(true);
        
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);        
        addLine(true);
        
        addTree(true);
        addTree(true);
        addTree(true);
        addTree(true);
  
        t.start();
    }
 
    public void addLine(boolean linef){
        int x=WIDTH/2-2;
        int y=700;
        int width=4;
        int height=80;
        int space=130;
        if(linef){
            line.add(new Rectangle(x,y-(line.size()*space),width,height));
        }else{
            line.add(new Rectangle(x,line.get(line.size()-1).y-space,width,height));
        }
    }
    public void addOcars(boolean check){
        int positionx = rand.nextInt()%2;  //  2 way road by %2
        int x=0;
        int y=0;
        int Width=width;
        int Heigth=height;
        
        
        if(positionx==0){
            x=WIDTH/2-90;
        }else{
            x=WIDTH/2+10;
        }
        
        if(check){
            ocars.add(new Rectangle(x,y-100-(ocars.size()*space),Width,Heigth));
        }else{
            ocars.add(new Rectangle(x,ocars.get(ocars.size()-1).y-500,Width,Heigth));
        }
    }
    public void addTree(boolean ch) {
    int pox=rand.nextInt()%2;
    int x=0;
    int y=0;
    int Width=width1;
    int Heigth=height1;
    
    if(pox==0){
        x=WIDTH-90;
    }else{
        x=WIDTH-490;
    }
    
    if(ch){
        tree.add(new Rectangle(x,y-100-(tree.size()*space),Width,Heigth));
    }else{
        tree.add(new Rectangle(x,tree.get(tree.size()-1).y-400,Width,Heigth));
    }
 }
    @Override
    public void paintComponent(Graphics g){
     
        g.drawImage(bg,0,0,null);
//        g.setColor(Color.cyan);
//        g.fillRect(0, 0, WIDTH, HEIGHT);   //frame
        
          g.drawImage(road,WIDTH/2-100,0, this);
//        g.setColor(Color.ORANGE);
//        g.fillRect(WIDTH/2-100,0,200,HEIGHT );  //road
//       
                
          g.drawImage(sktman,car.x,car.y, this);
//        g.setColor(Color.red);
//        g.fillRect(car.x,car.y, car.width,car.height);  //main car

            g.drawImage(sktman2,car2.x,car2.y, this);
//       g.setColor(Color.GREEN);
//        g.fillRect(car2.x,car2.y, car2.width,car2.height);  //main 2 car
        
             Font f=new Font(TOOL_TIP_TEXT_KEY, 5,20);
             g.setColor(Color.red);
           g.setFont(f);
           g.drawString("SCORE : " + count ,WIDTH/2-20,20);
           g.drawString("LEVEL : " + level , WIDTH/2-20,40);
           if(count<1000)
                level=1;
            else{
                    if(count%1000==0)
                        level++;
                    else
                        level=level;
            }
        g.setColor(Color.white);
        for(Rectangle rect : line){
            g.fillRect(rect.x, rect.y,rect.width, rect.height);
        }
//        g.setColor(Color.BLUE);
//        g.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);   //center line

        g.setColor(Color.MAGENTA);
//        
        for(Rectangle rect : ocars){
            int r=rand.nextInt(3);
                g.drawImage(ocar1,rect.x, rect.y, this);
        }
            
        for(Rectangle rect : tree){
            g.drawImage(tr1,rect.x,rect.y,this);
        }
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Graphics g=getGraphics();
        Rectangle rect;
        count++;
        
        for(int i=0;i<ocars.size();i++){
            rect=ocars.get(i);
            if(count%1000==0){
                  speed++;
           }
           rect.y+=speed;
          if(rect.y+rect.height>HEIGHT) //car disappeare
              {   
                  ocars.remove(rect);     //remove dis appeare car
                  addOcars(false);        // add new car
             }
       }
        
     for(int i=0;i<tree.size();i++){
            rect=tree.get(i);
             rect.y+=speed;
            if(rect.height+rect.y>HEIGHT) //tree disappeare
            {   
                tree.remove(rect);      //remove dis appeare tree
                addTree(false);         //add new tree
            }
       }
         //car crashing  with oponents car
   
           for(Rectangle r:ocars){
             if(r.intersects(car)){
                       g.setColor(Color.red);
                      g.setFont(getFont().deriveFont(28.0f));
                      g.drawString("GAME OVER", WIDTH/2-70,HEIGHT/2);
                      g.setColor(Color.PINK);
                      g.drawString("PLAYER 2 WIN...", WIDTH/2-70,HEIGHT/2+40);
                      g.setColor(Color.green);
                      g.drawString("SCORE :"+ count, WIDTH/2 - 70,HEIGHT/2- 40);
                      try {
                            Thread.sleep(3000);
                       } catch (InterruptedException ex) {
                            Logger.getLogger(work2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                exit(0);
            }
            else if(r.intersects(car2)){
                 g.setFont(getFont().deriveFont(28.0f));
                 g.setColor(Color.red);
                 g.drawString("GAME OVER", WIDTH/2-70,HEIGHT/2);
                 g.setColor(Color.PINK);
                 g.drawString("PLAYER 1 WIN...", WIDTH/2-70,HEIGHT/2+40);
                g.setColor(Color.green);
                g.drawString("SCORE :"+ count, WIDTH/2-70,HEIGHT/2-40);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                     Logger.getLogger(work2.class.getName()).log(Level.SEVERE, null, ex);
                }
                exit(0);
        }
            else if(car.intersects(car2)){
                car2.x=car2.x+10;
                if(car2.x>350){
                     g.setFont(getFont().deriveFont(28.0f));
                     g.setColor(Color.red);
                     g.drawString("GAME OVER", WIDTH/2-70,HEIGHT/2);
                    g.setColor(Color.PINK);
                    g.drawString("PLAYER 1 WIN...", WIDTH/2-70,HEIGHT/2+40);
                    g.setColor(Color.green);
                    g.drawString("SCORE :"+ count, WIDTH/2-70,HEIGHT/2-40);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                         Logger.getLogger(work2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                exit(0);
               }
          }
           else if(car2.intersects(car)){
                car.x=car.x-10;
                if(car.x<110){
                     g.setFont(getFont().deriveFont(28.0f));
                      g.setColor(Color.red);
                     g.drawString("GAME OVER", WIDTH/2-70,HEIGHT/2);
                     g.setColor(Color.PINK);
                    g.drawString("PLAYER 2 WIN...", WIDTH/2-70,HEIGHT/2+40);
                    g.setColor(Color.orange);
                     g.drawString("SCORE :"+ count, WIDTH/2-70,HEIGHT/2-40);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                         Logger.getLogger(work2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                exit(0);
                }
            }
     }
        
           for(int i=0;i<line.size();i++){
            rect=line.get(i);
           rect.y+=speed;
           if(rect.y>HEIGHT) //line disappeare
            {   
                line.remove(rect);     //remove dis appeare line
                addLine(false);        // add new line
            }
        }
         repaint();
    }

    public void moveup(){
        if(car.y-move<0){
            System.out.println("\b");
        }else{
            car.y-=move;
        }
    }
    
    public void movedown(){
        if(car.y+move+car.height>HEIGHT-1){
            System.out.println("\b");
        }else{
            car.y+=move;
        }
    }
     
    public void moveleft(){
        if(car.x-move<WIDTH/2-120){
            System.out.println("\b");
        }else{
            car.x-=move;
        }
    }
    public void moveright(){
        if(car.x+move>WIDTH/2+40){
            System.out.println("\b");
        }else{
            car.x+=move;
        }
    }
    
     public void pauseGame(){
      Graphics g=getGraphics();
         Font f=new Font(TOOL_TIP_TEXT_KEY, 5,20);
             g.setColor(Color.red);
           g.setFont(f);
           g.drawString("Your Game Is Pause For 4 Second",50,80);
   try {
                    Thread.sleep(4000);
                } catch (InterruptedException ex) {
                     Logger.getLogger(work2.class.getName()).log(Level.SEVERE, null, ex);
                }
   }
       
    public void moveupE(){
        if(car2.y-move<0){
            System.out.println("\b");
        }else{
            car2.y-=move;
        }
    }
    
    public void movedownX(){
        if(car2.y+move+car2.height>HEIGHT-1){
            System.out.println("\b");
        }else{
            car2.y+=move;
        }
    }
    
    public void moveleftA(){
        if(car2.x-move<WIDTH/2-120){
            System.out.println("\b");
        }else{
            car2.x-=move;
        }
    }
    public void moverightF(){
        if(car2.x+move>WIDTH/2+60){
            System.out.println("\b");
        }else{
            car2.x+=move;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int key=e.getKeyCode();
         switch(key){
             case KeyEvent.VK_UP:
                 moveup();
                 break;
             case KeyEvent.VK_DOWN:
                 movedown();
                 break;
             case KeyEvent.VK_LEFT:
                 moveleft();
                 break;
             case KeyEvent.VK_RIGHT:
                 moveright();
                 break;   
             case KeyEvent.VK_P:
                 pauseGame();
                 break;
            case KeyEvent.VK_E:
                 moveupE();
                 break;
             case KeyEvent.VK_X:
                 movedownX();
                 break;
             case KeyEvent.VK_F:
                 moverightF();
                 break;
             case KeyEvent.VK_A:
                 moveleftA();
                 break;
             default:
                 break;
          
         }
    }

    @Override
    public void keyReleased(KeyEvent e) {
         
        }
    
}
