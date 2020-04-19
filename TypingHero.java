/*Typing Hero*/

import java.awt.Container;
import javax.swing.JFrame;

public class TypingHero extends JFrame {
      
       public TypingHero() {
        //タイトルの設定
        setTitle("タイピング勇者");
      
       //メインパネルの作成
       MainPanel panel = new MainPanel();
       Container contentPane = getContentPane();
       contentPane.add(panel);

       //パネルサイズに合わせてフレームサイズの設定
       pack();
   }
  
       public static void main (String[] args) {
           TypingHero frame = new TypingHero();
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setVisible(true);
        }
       } 
  

