/*Typing Hero*/

import java.awt.Container;
import javax.swing.JFrame;

public class TypingHero extends JFrame {
      
       public TypingHero() {
        //�^�C�g���̐ݒ�
        setTitle("�^�C�s���O�E��");
      
       //���C���p�l���̍쐬
       MainPanel panel = new MainPanel();
       Container contentPane = getContentPane();
       contentPane.add(panel);

       //�p�l���T�C�Y�ɍ��킹�ăt���[���T�C�Y�̐ݒ�
       pack();
   }
  
       public static void main (String[] args) {
           TypingHero frame = new TypingHero();
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setVisible(true);
        }
       } 
  

