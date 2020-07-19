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
public class work2 extends JPanel implements ActionListener,KeyListener  {
    
    private int space;  //space bt opposite cars
    private int width=80;  //cars width
    private int height=80;  //cars heidth
    private int width1=100; //tree width
    private int height1=100; //tree height
    private int speed;   //speed of opposite car
    private int WIDTH=500;  //frame
    private int HEIGHT=700; //frame
    private int move=20;
    private int count=1;
    private int level=0;
    private Rectangle skt;
    private ArrayList <Rectangle> ocars;
    private ArrayList <Rectangle> line;
    private ArrayList <Rectangle> tree;
    private Random rand;
    Timer t;
 
    BufferedImage sktman,man;
    BufferedImage ocar1,ocar2,ocar3;
    BufferedImage tr1,bg,road,score;
    Boolean linef=true;
    
    public work2() throws IOException{
        
       sktman=ImageIO.read(new File("D:\\Other Study\\CAR\\sktman3.png"));
       tr1=ImageIO.read(new File("D:\\Other Study\\CAR\\tree3.png"));
       bg=ImageIO.read(new File("D:\\Other Study\\CAR\\bg1.png"));
       ocar1=ImageIO.read(new File("D:\\Other Study\\CAR\\ocar8.png"));
       road=ImageIO.read(new File("D:\\Other Study\\CAR\\road.png"));
       
       
        rand=new Random();
        
        tree =new ArrayList<Rectangle>();
        ocars=new ArrayList<Rectangle>();
        line=new ArrayList<Rectangle>();
       
        skt =new Rectangle(WIDTH/2-90,HEIGHT-100,width,height);
        
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
    int positionx=rand.nextInt()%2;
    int x=0;
    int y=0;
    int Width=width1;
    int Heigth=height1;
    
    if(positionx==0){
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
                
          g.drawImage(sktman,skt.x,skt.y, this);
//        g.setColor(Color.red);
//        g.fillRect(car.x,car.y, car.width,car.height);  //main car
        
     /// score section:
           g.setColor(Color.red);
           Font f=new Font(TOOL_TIP_TEXT_KEY, 5,20);
           g.setFont(f);
           g.drawString("SCORE : " + count ,WIDTH/2-20,20);
           g.drawString("LEVEL : " + level , WIDTH/2-20,40);
           
      // Level section :
      
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
        for(Rectangle rect : ocars){
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
             if(rect.y+rect.height>HEIGHT) //car disappeare
            {   
                ocars.remove(rect);     //remove dis appeare car
                addOcars(false);        // add new car
            }
               rect.y+=speed;
        }
        
        for(int i=0;i<tree.size();i++){
            rect=tree.get(i);
            if(rect.height+rect.y>HEIGHT) //tree disappeare
            {   
                tree.remove(rect);      //remove dis appeare tree
                addTree(false);         //add new tree
            }
             rect.y+=speed;
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
         //car crashing  with oponents car
      
        for(Rectangle r : ocars){
            if(r.intersects(skt)){
                g.setColor(Color.green);
               g.setFont(getFont().deriveFont(28.0f));
               g.drawString("GAME OVER", WIDTH/2-70,HEIGHT/2);
                g.setColor(Color.red);
               g.drawString("SCORE :"+ count, WIDTH/2-70,HEIGHT/2-40);
               try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                     Logger.getLogger(work2.class.getName()).log(Level.SEVERE, null, ex);
                }
                exit(0);
              }
        }
        repaint();
    }

    public void moveup(){
        if(skt.y-move<0){
            
        }else{
            skt.y-=move;
        }
    }
    
    public void movedown(){
        if(skt.y+move+skt.height>HEIGHT-1){
            
        }else{
            skt.y+=move;
        }
    }
     
    public void moveleft(){
        if(skt.x-move<WIDTH/2-120){
            
        }else{
            skt.x-=move;
        }
    }
    public void moveright(){
        if(skt.x+move>WIDTH/2+40){
           
        }else{
            skt.x+=move;
        }
    }
    
     public void pauseGame() {
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
            default:
                break;
         }
    }

    @Override
    public void keyReleased(KeyEvent e) {
         
        }
    
}
