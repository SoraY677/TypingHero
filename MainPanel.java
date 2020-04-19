/*Main Panel*/

import java.awt.*;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class MainPanel extends JPanel implements KeyListener,Runnable, Common {
   //�p�l���T�C�Y
   public static final int WIDTH = 480;
   public static final int HEIGHT = 480;

   //�A�N�V�����L�[
   private ActionKey leftKey;
   private ActionKey rightKey;
   private ActionKey upKey;
   private ActionKey downKey;

   //�Q�[�����[�v
   private Thread gameLoop;
   
   //����������
   private Random rand = new Random();

   //�}�b�v
   private Map map;
   //�E��
   private Chara hero;
   //���l
   private Chara king;
   //���m
   private Chara soldier;

   //���C���p�l��
 public MainPanel() {
   //�p�l���̐����T�C�Y�̐ݒ� 
   setPreferredSize(new Dimension(WIDTH,HEIGHT));
   
   //�p�l�����L�[������󂯕t����悤�ɂ���
   setFocusable(true);
   addKeyListener(this);
   
   //�A�N�V�����L�[�𐧍�
   leftKey = new ActionKey();
   rightKey = new ActionKey();
   upKey = new ActionKey();
   downKey = new ActionKey(); 
  
   //�}�b�v�̍쐬 
   map = new Map("mapdate/map.dat",this);
   //�E��
   hero  =new Chara(1,1,"character/hero.gif",map);
   //���l
   king = new Chara(6,6,"character/king.gif",map);
   //���m
   soldier = new Chara(8,9,"character/soldier.gif",map);

   //�}�b�v�ɃL������o�^
   map.addChara(hero);
   map.addChara(king);
   map.addChara(soldier);

   //�Q�[�����[�v
   gameLoop = new Thread(this);
   gameLoop.start();
}

 
 //�`�ʐݒ�
 public void paintComponent(Graphics g) {
       super.paintComponent(g);
       
       //x�����̃I�t�Z�b�g�v�Z
       int offsetX = MainPanel.WIDTH / 2 - hero.getPx();
       //�}�b�v�[�ł̓X�N���[�����Ȃ�
       offsetX = Math.min(offsetX,0);
       offsetX = Math.max(offsetX,MainPanel.WIDTH - map.getWidth());

       //y�����̃I�t�Z�b�g�v�Z
       int offsetY = MainPanel.WIDTH / 2 - hero.getPy();
       //�}�b�v�[�ł̓X�N���[�����Ȃ�
       offsetY = Math.min(offsetY,0);
       offsetY = Math.max(offsetY,MainPanel.HEIGHT - map.getHeight());
       
       map.draw(g,offsetX,offsetY);//�}�b�v��`��(�L�����̓}�b�v�ɓo�^����Ă���̂Ń}�b�v���`��)
     }
 
 //�Q�[�����[�v
 public void run(){
       while (true){
          //�L�[���͂̃`�F�b�N
          checkInput();
          //�E�҂̓���
          heroMove();
          //�L�����̓���
          charaMove();

          repaint();//�ĕ`��
        
      //20�~���b�̋x�~
      try{
          Thread.sleep(20);
       }catch (InterruptedException e){
            e.printStackTrace();
           }
    }
  }

 //�L�[�`�F�b�N
 private void checkInput() {
     //��
     if (leftKey.isPressed()) {
           if (!hero.isMoving()) {
                hero.setDirection(LEFT);
                hero.setMoving(true);
             }
            }
      //�E
      if (rightKey.isPressed()) {
           if (!hero.isMoving()){ 
                hero.setDirection(RIGHT);
                hero.setMoving(true);
           } 
          }
      //��
      if (upKey.isPressed()) {
           if (!hero.isMoving()) {
                hero.setDirection(UP);
                hero.setMoving(true);
           }
          }
       //��
       if (downKey.isPressed()) {
           if (!hero.isMoving()) {
                hero.setDirection(DOWN);
                hero.setMoving(true); 
            }
           }
          }

 //�E�҂̈ړ�����
 private void heroMove() {
          if (hero.isMoving()) {
              if (hero.move()) {
                 }
                }
              }

 //�E�҈ȊO�̃L�����̈ړ�����
 private void charaMove() {
     //�ړ����Ȃ�
       if(soldier.isMoving()) {
         soldier.move();//�ړ�����
     //�����ړ����łȂ��Ȃ�0.02�̊m���Ń����_���Ɉړ�
 }else if(rand.nextDouble() < 0.02){
        soldier.setDirection(rand.nextInt(4));
        soldier.setMoving(true);
      }
  }
 //�L�[����
 public void keyPressed(KeyEvent e) {
       int KeyCode = e.getKeyCode();//�����ꂽ�L�[�́H
          if (KeyCode == KeyEvent.VK_LEFT){
              leftKey.press();
    }else if (KeyCode == KeyEvent.VK_RIGHT){
              rightKey.press();
    }else if (KeyCode == KeyEvent.VK_UP){
              upKey.press();
    }else if (KeyCode == KeyEvent.VK_DOWN){
              downKey.press();
    }
   }

 public void keyReleased(KeyEvent e) {
      int KeyCode = e.getKeyCode();//�����ꂽ�L�[�́H
          if (KeyCode == KeyEvent.VK_LEFT){
              leftKey.release();
    }else if (KeyCode == KeyEvent.VK_RIGHT){
              rightKey.release();
    }else if (KeyCode == KeyEvent.VK_UP){
              upKey.release();
    }else if (KeyCode == KeyEvent.VK_DOWN){
              downKey.release();
    }

 }           
 
 public void keyTyped(KeyEvent e) {
 }
} 
 
 

 



