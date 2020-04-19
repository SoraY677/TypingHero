
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;



public class Chara implements Common{

    public static final int SPEED =4;//�ړ��X�s�[�h
    private Image image; //�L�����N�^�[�̃C���[�W
    private int x,y;//�P�ʁ@�}�X
    private int px,py;//�P�ʁ@�s�N�Z��
    private int direction;//�E�҂̌���
    private int count;//�L�����N�^�[�̃A�j���[�V�����J�E���^
    private boolean isMoving;//�ړ����� 
    private int movingLength;//�ړ����̃s�N�Z����
    private Thread threadAnime;//�L�����N�^�[�̃A�j���[�V�����X���b�h
    private Map map;//�}�b�v�̎Q��

    //�L�����N�^�[�̓o�ꂪ�ȒP��
    public Chara(int x,int y,String filename,Map map){
     
     this.x = x;
     this.y = y;
     px = x*CS;
     py = y*CS;

     direction = DOWN;
     count = 0;
 
     this.map = map;

     loadImage(filename);//�C���[�W�̃��[�h
      
     //�A�j���[�V�����X���b�h   
     threadAnime = new Thread(new AnimationThread());
     threadAnime.start();
     
      }

      public void draw(Graphics g, int offsetX, int offsetY){
       //count��direction�̒l�ɉ����ĕ\���摜�̐؂�ւ�
       g.drawImage(image, px+offsetX, py+offsetY, px+offsetX+CS, py+offsetY+CS, count*CS, direction*CS, count*CS+CS, direction*CS+CS,null);
      }
   
     //�ړ�������������true��Ԃ��B
      public boolean move() {
                  switch (direction){
            case LEFT:
                if (moveLeft()) {
                return true;
                }
                break;
            case RIGHT:
                if (moveRight()){
                return true;
                }
                break;
            case UP:
                if (moveUp()){
                return true;
                }
                break;
            case DOWN:
                if (moveDown()){
                return true;
                }
                break; 
            }
            return false;
          }

    //���ֈړ�
    private boolean moveLeft() {
          int nextX = x - 1;
          int nextY = y;
          if(nextX < 0) nextX = 0;
        //�ړ���ɏ�Q�����Ȃ���Έړ��J�n
        if (!map.isHit(nextX,nextY)) {
           //SPEED�s�N�Z�����ړ�
           px -= Chara.SPEED;
           if (px < 0) px = 0;
        //�ړ����������Z
        movingLength += Chara.SPEED; 
        //�ړ���1�}�X���𒴂��Ă�����
        if (movingLength >= CS){
        //�ړ�
        x --;
        if (x<0) x=0;
        px = x * CS;
        //�ړ�������
        isMoving = false;
        return true;
       }
     } else {
          isMoving = false;
          //���̈ʒu�ɖ߂�
          px = x * CS;
          py = y * CS;
        }
      return false;
    }

    //�E�ֈړ�
    private boolean moveRight(){
         int nextX = x + 1;
         int nextY = y;
         if (nextX > Map.COL - 1) nextX = Map.COL - 1;
       //��Q�����Ȃ���Έړ��J�n
         if (!map.isHit(nextX,nextY)){
             //SPEED�s�N�Z�����ړ�
             px += Chara.SPEED;
             if (px > Map.WIDTH - CS)
                 px = Map.WIDTH - CS;
             //�ړ����������Z
             movingLength += Chara.SPEED;
             //�ړ���1�}�X�����Ă�����
             if (movingLength >= CS ) {
                 //�ړ�����
                 x++;
                 if (x > Map.COL - 1) x=Map.COL - 1;
                 px = x * CS;
                 //�ړ�������
                 isMoving =false;
                 return true;
              }
            }else {
                 isMoving = false;
                 //���̈ʒu�ɖ߂�
                 px = x * CS;
                 py = y * CS;
        
            }
                 return false;
         }

     //��Ɉړ�����
     public boolean moveUp() {
           int nextX = x;
           int nextY = y-1;
           if (nextY < 0) nextY = 0;
           //��Q���Ȃ���Έړ����J�n����
           if (!map.isHit(nextX,nextY)){
              //SPEED�s�N�Z�����ړ�
              py -= Chara.SPEED;
              if (py < 0) py=0 ;
              //�ړ����������Z
              movingLength += Chara.SPEED;
              //�ړ���1�}�X�����Ă�����
              if(movingLength >= CS) {
                //�ړ�����
                y--;
                if (y<0) y=0;
                py = y*CS;
                //�ړ�����
                isMoving = false;
                return true;
            }
        } else {
               isMoving = false;
               px = x * CS;
               py = y * CS;
            }
           return false;
        }

     //���ֈړ�����
     private boolean moveDown() {
             //1�}�X��̍��W
              int nextX = x;
              int nextY = y + 1;
              if (nextY > Map.ROW -1) nextY = Map.ROW -1;
             //���̏ꏊ�ɏ�Q�����Ȃ���Έړ����J�n����
              if (!map.isHit(nextX,nextY)) {
               //SPEED�s�N�Z�����ړ�
               py += Chara.SPEED; 
               if (py > Map.HEIGHT - CS)
                  py = Map.HEIGHT - CS;
               //�ړ����������Z 
               movingLength += Chara.SPEED;
               //�ړ���1�}�X�����Ă�����
               if (movingLength >= CS) { 
                    //�ړ�����
                    y++;
                    if(y > Map.ROW -1) y = Map.ROW -1;
                    py = y*CS;
               //�ړ�������
               isMoving = false;
               return true;
               }
           }else{
                 isMoving = false;
                 px = x * CS;
                 py = y * CS;
           }
            return false;
          }

    public int getX(){
           return x;
       }
    public int getY(){
           return y;
       }
   
    public int getPx() {
            return px;
       }
   
    public int getPy() {
          return py;
       } 
    
    public void setDirection(int dir) {
          direction = dir;
       }
         
    public boolean isMoving() {
           return isMoving;
       }

    public void setMoving(boolean flag) {
              isMoving = flag;
              //�ړ�������������
              movingLength = 0;
          }

    private void loadImage(String filename) {
          ImageIcon icon =new ImageIcon(getClass().getResource(filename));
          image =icon.getImage();
      }

   private class AnimationThread extends Thread {
          public void run() {
                   while (true){
                       if(count ==0){
                           count =1;
                 }else if(count == 1){
                           count =0;
                 }
          try{
               Thread.sleep(300);
         }catch(InterruptedException e) {
             e.printStackTrace(); 
           }
        }
      }
     }           
   }


   