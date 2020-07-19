/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skatingman;
 
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Lenovo
 */
public class SkatingMan extends JFrame {
        
 SkatingMan(int i) throws IOException {
 if(i==1){
         work1();
   }
 
 else if(i==2){
        work2();
    }
}
   
 public void work1() throws IOException{
     JFrame jf =new JFrame("SKATINGMAN");
          work2 w;
                w = new work2();
                 jf. add(w);
     jf.setLocale(null);
        jf.setVisible(true);
        jf.setSize(500,700);
        setDefaultLookAndFeelDecorated(true);
 }
 
 public void work2() throws IOException{
         JFrame jf =new JFrame("SKATINGMAN");
           Multiplayertest2 mpt;
            mpt = new Multiplayertest2();
                 jf.add(mpt);
     jf.setLocale(null);
        jf.setVisible(true);
        jf.setSize(500,700);
        setDefaultLookAndFeelDecorated(true);
 
 }
    public static void main(String[] args) throws IOException {
        JFrame jf =new JFrame("SKATINGMAN");
          work2 w;
                w = new work2();
                 jf. add(w);
      
        jf.setLocale(null);
        jf.setVisible(true);
        jf.setSize(500,700);
        setDefaultLookAndFeelDecorated(true);
        
    }
}
