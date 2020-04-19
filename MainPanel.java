/*Main Panel*/

import java.awt.*;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class MainPanel extends JPanel implements KeyListener,Runnable, Common {
   //パネルサイズ
   public static final int WIDTH = 480;
   public static final int HEIGHT = 480;

   //アクションキー
   private ActionKey leftKey;
   private ActionKey rightKey;
   private ActionKey upKey;
   private ActionKey downKey;

   //ゲームループ
   private Thread gameLoop;
   
   //乱数生成器
   private Random rand = new Random();

   //マップ
   private Map map;
   //勇者
   private Chara hero;
   //王様
   private Chara king;
   //兵士
   private Chara soldier;

   //メインパネル
 public MainPanel() {
   //パネルの推奨サイズの設定 
   setPreferredSize(new Dimension(WIDTH,HEIGHT));
   
   //パネルがキー操作を受け付けるようにする
   setFocusable(true);
   addKeyListener(this);
   
   //アクションキーを制作
   leftKey = new ActionKey();
   rightKey = new ActionKey();
   upKey = new ActionKey();
   downKey = new ActionKey(); 
  
   //マップの作成 
   map = new Map("mapdate/map.dat",this);
   //勇者
   hero  =new Chara(1,1,"character/hero.gif",map);
   //王様
   king = new Chara(6,6,"character/king.gif",map);
   //兵士
   soldier = new Chara(8,9,"character/soldier.gif",map);

   //マップにキャラを登録
   map.addChara(hero);
   map.addChara(king);
   map.addChara(soldier);

   //ゲームループ
   gameLoop = new Thread(this);
   gameLoop.start();
}

 
 //描写設定
 public void paintComponent(Graphics g) {
       super.paintComponent(g);
       
       //x方向のオフセット計算
       int offsetX = MainPanel.WIDTH / 2 - hero.getPx();
       //マップ端ではスクロールしない
       offsetX = Math.min(offsetX,0);
       offsetX = Math.max(offsetX,MainPanel.WIDTH - map.getWidth());

       //y方向のオフセット計算
       int offsetY = MainPanel.WIDTH / 2 - hero.getPy();
       //マップ端ではスクロールしない
       offsetY = Math.min(offsetY,0);
       offsetY = Math.max(offsetY,MainPanel.HEIGHT - map.getHeight());
       
       map.draw(g,offsetX,offsetY);//マップを描く(キャラはマップに登録されているのでマップが描く)
     }
 
 //ゲームループ
 public void run(){
       while (true){
          //キー入力のチェック
          checkInput();
          //勇者の動き
          heroMove();
          //キャラの動き
          charaMove();

          repaint();//再描写
        
      //20ミリ秒の休止
      try{
          Thread.sleep(20);
       }catch (InterruptedException e){
            e.printStackTrace();
           }
    }
  }

 //キーチェック
 private void checkInput() {
     //左
     if (leftKey.isPressed()) {
           if (!hero.isMoving()) {
                hero.setDirection(LEFT);
                hero.setMoving(true);
             }
            }
      //右
      if (rightKey.isPressed()) {
           if (!hero.isMoving()){ 
                hero.setDirection(RIGHT);
                hero.setMoving(true);
           } 
          }
      //上
      if (upKey.isPressed()) {
           if (!hero.isMoving()) {
                hero.setDirection(UP);
                hero.setMoving(true);
           }
          }
       //下
       if (downKey.isPressed()) {
           if (!hero.isMoving()) {
                hero.setDirection(DOWN);
                hero.setMoving(true); 
            }
           }
          }

 //勇者の移動処理
 private void heroMove() {
          if (hero.isMoving()) {
              if (hero.move()) {
                 }
                }
              }

 //勇者以外のキャラの移動処理
 private void charaMove() {
     //移動中なら
       if(soldier.isMoving()) {
         soldier.move();//移動する
     //もし移動中でないなら0.02の確立でランダムに移動
 }else if(rand.nextDouble() < 0.02){
        soldier.setDirection(rand.nextInt(4));
        soldier.setMoving(true);
      }
  }
 //キー入力
 public void keyPressed(KeyEvent e) {
       int KeyCode = e.getKeyCode();//押されたキーは？
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
      int KeyCode = e.getKeyCode();//押されたキーは？
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
 
 

 



