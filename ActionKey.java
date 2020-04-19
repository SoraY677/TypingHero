public class ActionKey {
   
   //�L�[�̃��[�h
   public static final int NORMAL = 0;
   public static final int DETECT_INITAL_PRESS_ONLY =1;

   //�L�[�̏��
   private static final int STATE_RELEASED = 0;//�L�[�������ꂽ���
   private static final int STATE_PRESSED = 1;//�L�[��������Ă�����
   private static final int STATE_WAITING_FOR_RELEASE = 2;//�L�[���������̂�҂��Ă�����

   private int mode;//�L�[�̃��[�h
   private int amount;//�L�[�̉����ꂽ�� 
   private int state;//�L�[�̏��
   
   //���ʂ̃R���X�g���N�^�Ńm�[�}�����[�h
   public ActionKey() {
       this(NORMAL);
      }
   
   //���[�h�w��̂ł���R���X�g���N�^
   public ActionKey(int mode) {
        this.mode = mode;
         reset();
      }
    
    //���Z�b�g
    public void reset(){
         state = STATE_RELEASED;
         amount = 0;
      }

    public void press() {
        //STATE_WAITING_FOR_RELEASE�̎��͉����ꂽ���ƂɂȂ�Ȃ�
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
          