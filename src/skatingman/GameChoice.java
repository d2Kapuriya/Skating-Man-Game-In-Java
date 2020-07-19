/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skatingman;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.*;
import java.io.IOException;

public class GameChoice extends JFrame implements ActionListener {
    
          JButton b1=new JButton("Single Player");
           JButton b2=new JButton("Two Player ");
            JButton b3=new JButton("START");
            JButton  b4;
            
        Container con=getContentPane();
              int t=0;
    public GameChoice() throws IOException {
          b1.setBounds(100, 100, 100,100);
           b2.setBounds(100, 300, 100,100);
            b3.setBounds(250, 200,100 ,100);
      
            con.add(b1);
           con.add(b2);
           con.add(b3);
        b1.addActionListener(this);
         b2.addActionListener(this);
         b3.addActionListener(this);
     
      
    }
         @Override
    public void actionPerformed(ActionEvent e){
      
          if(t==0){   
              b4=(JButton)e.getSource();
              t++;
          }else{
                if(b4==b1){
                    try {
                        SkatingMan sk=new SkatingMan(1);
                    } catch (IOException ex) {
                        Logger.getLogger(GameChoice.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(b4==b2){
                    try {
                        SkatingMan sk=new SkatingMan(2);
                      } catch (IOException ex) {
                        Logger.getLogger(GameChoice.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 }
          }
  }
    
    public static void main(String[] args) throws IOException {
        GameChoice g=new GameChoice();
        g.setLayout(null);
          g. setVisible(true);
       g.setSize(500,720);
        setDefaultLookAndFeelDecorated(true);
    }
}
