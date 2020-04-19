public class ActionKey {
   
   //キーのモード
   public static final int NORMAL = 0;
   public static final int DETECT_INITAL_PRESS_ONLY =1;

   //キーの状態
   private static final int STATE_RELEASED = 0;//キーが離された状態
   private static final int STATE_PRESSED = 1;//キーが押されている状態
   private static final int STATE_WAITING_FOR_RELEASE = 2;//キーが離されるのを待っている状態

   private int mode;//キーのモード
   private int amount;//キーの押された回数 
   private int state;//キーの状態
   
   //普通のコンストラクタでノーマルモード
   public ActionKey() {
       this(NORMAL);
      }
   
   //モード指定のできるコンストラクタ
   public ActionKey(int mode) {
        this.mode = mode;
         reset();
      }
    
    //リセット
    public void reset(){
         state = STATE_RELEASED;
         amount = 0;
      }

    public void press() {
        //STATE_WAITING_FOR_RELEASEの時は押されたことにならない
        if (state !=STATE_WAITING_FOR_RELEASE){
            amount++;
            state = STATE_PRESSED;
         }
       }

     public void release() {
          state = STATE_RELEASED;
       }

     public boolean isPressed() {
        if(amount != 0){
                if(state == STATE_RELEASED) {
                     amount = 0;
          }else if(mode == DETECT_INITAL_PRESS_ONLY) {
                  state = STATE_WAITING_FOR_RELEASE;
                  amount = 0;
           }
          return true;
          }
          
          return false;
       } 
  }
          