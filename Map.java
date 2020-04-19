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
 
      //�}�b�v�`�b�v
       private Image grassImage;
       private Image waterImage;
       private Image bridgeImage;
       
      //���̃}�b�v�ɂ���L�����N�^�[����
       private Vector charas = new Vector();

       //���C���p�l���ւ̎Q��
       private MainPanel panel;

      public Map(String filename,MainPanel panel){
           //�}�b�v�����[�h
           load(filename);

           loadImage();
      }

      public void draw(Graphics g,int offsetX,int offsetY){
          
      //�I�t�Z�b�g�����Ƃɕ`�ʔ͈͂����߂�
      int firstTileX = pixelsToTiles(-offsetX);
      int lastTileX = firstTileX + pixelsToTiles(MainPanel.WIDTH) +1;
      //�`�ʔ͈͂̓}�b�v�̑傫�����傫���Ȃ�Ȃ��悤�ɒ���
      lastTileX = Math.min(lastTileX,col);

      int firstTileY = pixelsToTiles(-offsetY);
      int lastTileY = firstTileY + pixelsToTiles(MainPanel.HEIGHT) +1;
      //�`�ʔ͈͂��}�b�v�̑傫�����傫���Ȃ�Ȃ��悤�ɒ���
      lastTileY = Math.min(lastTileY,row);

      //���l�ɉ������}�b�v�`�b�v���Z�b�g
           for(int i= firstTileY;i<lastTileY;i++){
            for(int j= firstTileX;j<lastTileX;j++){
                 switch(map[i][j]){
                    case 0 ://����
                        g.drawImage(grassImage,tilesToPixels(j)+offsetX,tilesToPixels(i)+offsetY,panel);
                      break;
                    case 1 ://�C
                        g.drawImage(waterImage,tilesToPixels(j)+offsetX,tilesToPixels(i)+offsetY,panel);
                      break;
                    case 2 :
                        g.drawImage(bridgeImage,tilesToPixels(j)+offsetX,tilesToPixels(i)+offsetY,panel);
                      break;
                }
               }
              }
         
          //�}�b�v�ɂ���L�����̕`��
          for (int n=0; n<charas.size(); n++) {
                     Chara chara = (Chara)charas.get(n);
                     chara.draw(g,offsetX,offsetY);
                 }
             } 

      //�C�̒��͗E�ғ���Ȃ�
     public boolean isHit(int x, int y) {
        if(map[y][x] ==1){
              return true;
        }  
     
       //���̃L�����N�^�[�������瓖����
       for (int i = 0; i < charas.size(); i++) {
              Chara chara = (Chara) charas.get(i);
            if (chara.getX() == x && chara.getY() ==y) {
                 return true;
              } 
            }    
        return false;
        }
      
     //�L�����N�^�[�����̃}�b�v�ɒǉ�����
     public void addChara(Chara chara) {
                charas.add(chara);
             }
     
     //�s�N�Z���̒P�ʂ��}�X�̒P�ʂɕϊ����Ēl��Ԃ�
      public static int pixelsToTiles(double pixels) {
             return (int)Math.floor(pixels / CS);
      }

     //�}�X�̒P�ʂ��s�N�Z���̒P�ʂɕϊ����Ēl��Ԃ�
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

     //�}�b�v��ǂݍ���
     private void load(String filename) {
         try {
              BufferedReader br = new BufferedReader(
                      new InputStreamReader(getClass().getResourceAsStream(filename)));
              //row��ǂݎ��
              String line = br.readLine();
              row = Integer.parseInt(line);
              //col��ǂݎ��
              line = br.readLine();
              col = Integer.parseInt(line);
              //�}�b�v�T�C�Y��ݒ�
              width = col * CS;
              height = row * CS;
              //�}�b�v���쐬
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
