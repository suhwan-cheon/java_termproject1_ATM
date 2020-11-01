import java.util.*;

import javax.swing.JOptionPane;

public class main {
   
	static Scanner input = new Scanner(System.in);
   static int command; //������� �Է��� �޴� Ŀ�ǵ�
   static ATM atm = new ATM(); //ATM ��ü
   static DataBase DB = new DataBase(); //DataBase ��ü
   static Interface screen; //Interface ��ü
   static String id;
   static int password;
   
   public static void main(String[] args) {
      
      //login
      serviceOn();
      while(true) {
         command = screen.transaction();
         //while(command == 0) {};
    	 // command = 4; // test�� �ڵ� �� �����ϱ⤿!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
         long return_Cash;
         //System.out.println(command);
         switch(command) {
         case 0:
            break;
         case 1:
            return_Cash = screen.Show(command, id, DB, atm);
            break;
         case 2:
            return_Cash = screen.Show(command, id, DB, atm);
            atm.Deposit(DB, id, return_Cash);
            break;
         case 3:
            return_Cash = screen.Show(command, id, DB, atm);
            atm.WithDraw(DB, id, return_Cash);
            break;
         case 4:
            return_Cash = screen.Show(command, id, DB, atm);
            String to_id;
            to_id = screen.to_id(0);
        	if(DB.checkingId(to_id)==false) {
        		while(DB.checkingId(to_id)==false) {
        			to_id = screen.to_id(1);
        		}
        	}
        	
            atm.Remit(DB, id, to_id, return_Cash);
            screen.remit_success(DB, id);
            
            break;
         }
         //command = 0; // test�� �ڵ� �� �����ϱ⤿!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
         if(command == 0) break;
      }
      serviceOff();
      System.exit(0);
   }
   
   // GUI�� �ٲ�
   private static void serviceOn() {
      String tmp_id = screen.Input_id(0);
      while(!Checkid(tmp_id)) {
         tmp_id = screen.Input_id(1);
      }
      int tmp_pass = screen.Input_pass(0);
      while(!Checkpass(tmp_id, tmp_pass)) {
         tmp_pass = screen.Input_pass(1);
      }
      id = tmp_id; password = tmp_pass;
   }
   
   // 
   private static void transaction() {
      command = screen.transaction();
   }
   private static void serviceOff() {
      screen.ShowDown();
   }
   //�Է��� id�� ���Ŀ� �´��� Ȯ��
   private static boolean Checkid(String id) {
	   if(id.length() != 11) return false;
		for(int i=0; i<id.length(); i++) {
			char tmp = id.charAt(i);
			if(i == 5) {
				if(tmp != '-') return false;
			}
			else {
				if('0' > tmp || tmp > '9') return false;
			}
		}
		if(DB.checkingId(id)) return true;
		else return false;
   }
   //�Է��� password�� ���Ŀ� �´��� Ȯ��
   private static boolean Checkpass(String id, int password) {
      if(!DB.matchingPassword(id, password)) return false;
      return true;
   }
   
}