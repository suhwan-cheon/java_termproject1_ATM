import java.util.*;
import javax.swing.JOptionPane;

public class ATM {
   
	static int one, five; //ATM기 안에 들어있는 만 원권, 오 만원권 지폐 개수
   
   //constructor
   public ATM() {
      one = 10000; five = 10000;
   }
   
   //입금
   static void Deposit(DataBase db, String id, long cash) {
      db.setBalance(id, db.getBalance(id) + cash);
   }
   //출금
   static void WithDraw(DataBase db, String id, long cash) {
      db.setBalance(id, db.getBalance(id) - cash);
   }
   //송금
   static void Remit(DataBase db, String from_id, String to_id, long cash) {
      db.setBalance(from_id, db.getBalance(from_id) - cash);
      db.setBalance(to_id, db.getBalance(to_id) + cash);
   }
   //잔액 확인
   static long DisplayCash(DataBase db, String id) {
      return db.getBalance(id);
   }
   
}