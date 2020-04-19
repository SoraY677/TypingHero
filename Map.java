import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.swing.ImageIcon;

public class Map implements Common{

       private int[][] map;
        
       private int row;
       private int col;

       private int width;
       private int height;
 
      //マップチップ
       private Image grassImage;
       private Image waterImage;
       private Image bridgeImage;
       
      //このマップにいるキャラクターたち
       private Vector charas = new Vector();

       //メインパネルへの参照
       private MainPanel panel;

      public Map(String filename,MainPanel panel){
           //マップをロード
           load(filename);

           loadImage();
      }

      public void draw(Graphics g,int offsetX,int offsetY){
          
      //オフセットをもとに描写範囲を求める
      int firstTileX = pixelsToTiles(-offsetX);
      int lastTileX = firstTileX + pixelsToTiles(MainPanel.WIDTH) +1;
      //描写範囲はマップの大きさより大きくならないように調節
      lastTileX = Math.min(lastTileX,col);

      int firstTileY = pixelsToTiles(-offsetY);
      int lastTileY = firstTileY + pixelsToTiles(MainPanel.HEIGHT) +1;
      //描写範囲がマップの大きさより大きくならないように調節
      lastTileY = Math.min(lastTileY,row);

      //数値に応じたマップチップをセット
           for(int i= firstTileY;i<lastTileY;i++){
            for(int j= firstTileX;j<lastTileX;j++){
                 switch(map[i][j]){
                    case 0 ://草原
                        g.drawImage(grassImage,tilesToPixels(j)+offsetX,tilesToPixels(i)+offsetY,panel);
                      break;
                    case 1 ://海
                        g.drawImage(waterImage,tilesToPixels(j)+offsetX,tilesToPixels(i)+offsetY,panel);
                      break;
                    case 2 :
                        g.drawImage(bridgeImage,tilesToPixels(j)+offsetX,tilesToPixels(i)+offsetY,panel);
                      break;
                }
               }
              }
         
          //マップにいるキャラの描写
          for (int n=0; n<charas.size(); n++) {
                     Chara chara = (Chara)charas.get(n);
                     chara.draw(g,offsetX,offsetY);
                 }
             } 

      //海の中は勇者入らない
     public boolean isHit(int x, int y) {
        if(map[y][x] ==1){
              return true;
        }  
     
       //他のキャラクターがいたら当たる
       for (int i = 0; i < charas.size(); i++) {
              Chara chara = (Chara) charas.get(i);
            if (chara.getX() == x && chara.getY() ==y) {
                 return true;
              } 
            }    
        return false;
        }
      
     //キャラクターをこのマップに追加する
     public void addChara(Chara chara) {
                charas.add(chara);
             }
     
     //ピクセルの単位をマスの単位に変換して値を返す
      public static int pixelsToTiles(double pixels) {
             return (int)Math.floor(pixels / CS);
      }

     //マスの単位をピクセルの単位に変換して値を返す
     public static int tilesToPixels(int tiles) {
             return tiles * CS;
      }
     
     public int getRow() {
             return row;
     }

     public int getCol() {
              return col;
     }
   
     public int getWidth(){
               return width;
     }

     public int getHeight(){
                return height;
     }

     //マップを読み込む
     private void load(String filename) {
         try {
              BufferedReader br = new BufferedReader(
                      new InputStreamReader(getClass().getResourceAsStream(filename)));
              //rowを読み取る
              String line = br.readLine();
              row = Integer.parseInt(line);
              //colを読み取る
              line = br.readLine();
              col = Integer.parseInt(line);
              //マップサイズを設定
              width = col * CS;
              height = row * CS;
              //マップを作成
              map = new int[row][col];
              for (int i=0;i<row;i++){
                  line = br.readLine();
                  for(int j=0; j<col; j++) {
                       map[i][j] = Integer.parseInt(line.charAt(j)+"");
                 }
                }
              }catch (Exception e) {
                  e.printStackTrace();
                }
            }


      private void loadImage(){
 
     ImageIcon icon = new ImageIcon(getClass().getResource("maptip/grass.gif"));
     grassImage =icon.getImage();

     icon = new ImageIcon(getClass().getResource("maptip/water.gif"));
     waterImage =icon.getImage();
      
     icon =new ImageIcon(getClass().getResource("maptip/bridge.gif"));
     bridgeImage = icon.getImage();
   }
 
     public void show() {
              for (int i=0; i<col; i++){
                   for (int j=0; j<col; j++){
                       System.out.print(map[i][j]);
                   }
                 System.out.println();
            }
           }
          }
