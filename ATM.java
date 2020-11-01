import java.util.*;
import javax.swing.JOptionPane;

public class ATM {
   
	static int one, five; //ATM�� �ȿ� ����ִ� �� ����, �� ������ ���� ����
   
   //constructor
   public ATM() {
      one = 10000; five = 10000;
   }
   
   //�Ա�
   static void Deposit(DataBase db, String id, long cash) {
      db.setBalance(id, db.getBalance(id) + cash);
   }
   //���
   static void WithDraw(DataBase db, String id, long cash) {
      db.setBalance(id, db.getBalance(id) - cash);
   }
   //�۱�
   static void Remit(DataBase db, String from_id, String to_id, long cash) {
      db.setBalance(from_id, db.getBalance(from_id) - cash);
      db.setBalance(to_id, db.getBalance(to_id) + cash);
   }
   //�ܾ� Ȯ��
   static long DisplayCash(DataBase db, String id) {
      return db.getBalance(id);
   }
   
}