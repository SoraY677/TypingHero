
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;



public class Chara implements Common{

    public static final int SPEED =4;//移動スピード
    private Image image; //キャラクターのイメージ
    private int x,y;//単位　マス
    private int px,py;//単位　ピクセル
    private int direction;//勇者の向き
    private int count;//キャラクターのアニメーションカウンタ
    private boolean isMoving;//移動中か 
    private int movingLength;//移動中のピクセル数
    private Thread threadAnime;//キャラクターのアニメーションスレッド
    private Map map;//マップの参照

    //キャラクターの登場が簡単に
    public Chara(int x,int y,String filename,Map map){
     
     this.x = x;
     this.y = y;
     px = x*CS;
     py = y*CS;

     direction = DOWN;
     count = 0;
 
     this.map = map;

     loadImage(filename);//イメージのロード
      
     //アニメーションスレッド   
     threadAnime = new Thread(new AnimationThread());
     threadAnime.start();
     
      }

      public void draw(Graphics g, int offsetX, int offsetY){
       //countとdirectionの値に応じて表示画像の切り替え
       g.drawImage(image, px+offsetX, py+offsetY, px+offsetX+CS, py+offsetY+CS, count*CS, direction*CS, count*CS+CS, direction*CS+CS,null);
      }
   
     //移動が完了したらtrueを返す。
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

    //左へ移動
    private boolean moveLeft() {
          int nextX = x - 1;
          int nextY = y;
          if(nextX < 0) nextX = 0;
        //移動先に障害物がなければ移動開始
        if (!map.isHit(nextX,nextY)) {
           //SPEEDピクセル分移動
           px -= Chara.SPEED;
           if (px < 0) px = 0;
        //移動距離を加算
        movingLength += Chara.SPEED; 
        //移動が1マス分を超えていたら
        if (movingLength >= CS){
        //移動
        x --;
        if (x<0) x=0;
        px = x * CS;
        //移動が完了
        isMoving = false;
        return true;
       }
     } else {
          isMoving = false;
          //元の位置に戻す
          px = x * CS;
          py = y * CS;
        }
      return false;
    }

    //右へ移動
    private boolean moveRight(){
         int nextX = x + 1;
         int nextY = y;
         if (nextX > Map.COL - 1) nextX = Map.COL - 1;
       //障害物がなければ移動開始
         if (!map.isHit(nextX,nextY)){
             //SPEEDピクセル分移動
             px += Chara.SPEED;
             if (px > Map.WIDTH - CS)
                 px = Map.WIDTH - CS;
             //移動距離を加算
             movingLength += Chara.SPEED;
             //移動が1マス超えていたら
             if (movingLength >= CS ) {
                 //移動する
                 x++;
                 if (x > Map.COL - 1) x=Map.COL - 1;
                 px = x * CS;
                 //移動が完了
                 isMoving =false;
                 return true;
              }
            }else {
                 isMoving = false;
                 //元の位置に戻す
                 px = x * CS;
                 py = y * CS;
        
            }
                 return false;
         }

     //上に移動する
     public boolean moveUp() {
           int nextX = x;
           int nextY = y-1;
           if (nextY < 0) nextY = 0;
           //障害がなければ移動を開始する
           if (!map.isHit(nextX,nextY)){
              //SPEEDピクセル分移動
              py -= Chara.SPEED;
              if (py < 0) py=0 ;
              //移動距離を加算
              movingLength += Chara.SPEED;
              //移動が1マス超えていたら
              if(movingLength >= CS) {
                //移動する
                y--;
                if (y<0) y=0;
                py = y*CS;
                //移動完了
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

     //下へ移動する
     private boolean moveDown() {
             //1マス先の座標
              int nextX = x;
              int nextY = y + 1;
              if (nextY > Map.ROW -1) nextY = Map.ROW -1;
             //その場所に障害物がなければ移動を開始する
              if (!map.isHit(nextX,nextY)) {
               //SPEEDピクセル分移動
               py += Chara.SPEED; 
               if (py > Map.HEIGHT - CS)
                  py = Map.HEIGHT - CS;
               //移動距離を加算 
               movingLength += Chara.SPEED;
               //移動が1マス超えていたら
               if (movingLength >= CS) { 
                    //移動する
                    y++;
                    if(y > Map.ROW -1) y = Map.ROW -1;
                    py = y*CS;
               //移動が完了
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
              //移動距離を初期化
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


   