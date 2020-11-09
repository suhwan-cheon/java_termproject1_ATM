import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Interface {
	static Scanner input = new Scanner(System.in);
	private static String id;
	private static String remit_id;
	private static int password, cmd;
	private static long cash;
	private static int input_flag;
	private static int one_cnt, five_cnt;
   
	//flag에 따라 사용자에게 무언가 보여줍니다.
   static long Show(int flag, String id, DataBase db, ATM atm) {
	  System.out.println(flag);
      if(flag == 1) { // 잔액조회
    	 // String message = String.format("Welcome %s, to ATM program!",name);
    	 String message = String.format("잔액 조회 서비스입니다.\n해당 계좌의 잔액은 : %d원 입니다!\n",db.getBalance(id));
    	 JOptionPane.showInternalMessageDialog(null, message);
         return -1;
      }
      else if(flag == 2) { // 입금
         while(true) {
        	 String temp_cash = JOptionPane.showInputDialog("입금할 금액을 입력하세요");
        	 cash = Long.parseLong(temp_cash);
        	 String t_one_cnt = JOptionPane.showInputDialog("입금할 지폐수 중 만원권이 몇 개인지 입력하세요");
        	 one_cnt = Integer.parseInt(t_one_cnt);
        	 String t_five_cnt = JOptionPane.showInputDialog("입금할 지폐수 중 오 만원권이 몇 개인지 입력하세요");
        	 five_cnt = Integer.parseInt(t_five_cnt);
        	 if(cash != ((long)one_cnt*10000)+((long)five_cnt*50000)) {
                 //System.out.println("잘못입력하셨습니다! 다시 입력해주세요!");
        		 JOptionPane.showInternalMessageDialog(null, "잘못입력하셨습니다! 다시 입력해주세요!");
             }
        	 else {
        		 atm.one += one_cnt;
            	 atm.five += five_cnt;
            	 String message = String.format("입금이 완료되었습니다.\n해당 계좌의 잔액은 : %d원 입니다!\n",db.getBalance(id) + cash);
            	 JOptionPane.showInternalMessageDialog(null, message);
            	 break;
        	 }
         }
         return cash;
      }
      else if(flag == 3) { // 출금
         
         while(true) {
        	 String temp_cash = JOptionPane.showInputDialog("출금할 금액을 입력하세요");
        	 cash = Long.parseLong(temp_cash);
        	 String t_one_cnt = JOptionPane.showInputDialog("출금할 지폐수 중 만원권이 몇 개인지 입력하세요");
        	 one_cnt = Integer.parseInt(t_one_cnt);
        	 String t_five_cnt = JOptionPane.showInputDialog("출금할 지폐수 중 오 만원권이 몇 개인지 입력하세요");
        	 five_cnt = Integer.parseInt(t_five_cnt);
        	 if(cash != ((long)one_cnt*10000)+((long)five_cnt*50000)) {
        		 JOptionPane.showInternalMessageDialog(null, "잘못입력하셨습니다! 다시 입력해주세요!");
             }
        	 else {
        		 int cmp_one = atm.one;
                 int cmp_five = atm.five;
                 
                 // atm 내의 만원 권과 5만원 권이 얼마나 남았는지를 통해 출금 가능한지 판단
                 if(one_cnt <= cmp_one && five_cnt <= cmp_five) { 
                	 atm.one -= one_cnt;
                	 atm.five -= five_cnt;
                	 String message = String.format("출금이 완료되었습니다.\n해당 계좌의 잔액은 : %d원 입니다!\n",db.getBalance(id) - cash);
                	 JOptionPane.showInternalMessageDialog(null, message);
                	 break;
                 }
                 else {
                	 JOptionPane.showInternalMessageDialog(null, "ATM 기계의 지폐가 부족합니다! 다시 입력해주세요!");
                 }
        	 }
         }
         return cash;
         
      }
      else { // 송금
         // 입력 금액 및 지폐수 확인
         while(true) {
        	 String temp_cash = JOptionPane.showInputDialog("송금할 금액을 입력하세요");
        	 cash = Long.parseLong(temp_cash);
             if(cash > db.getBalance(id)) {
            	 JOptionPane.showInternalMessageDialog(null, "계좌 잔액이 부족합니다! 다시 입력해주세요!");
             }
             else break;
         }
         
         return cash;
      }
   }
   
   // 송금할 계좌를 메인으로 리턴하는 함수 
   static String to_id(int x) {
	   if(x == 0) remit_id = JOptionPane.showInputDialog("송금할 계좌를 입력해주세요 : ");
	   else remit_id = JOptionPane.showInputDialog("잘못된 송금 계좌입니다! 다시 입력해주세요!");
	   return remit_id;
   }
   
   static void remit_success(DataBase db,String idid){
	   	String message = String.format("송금을 완료했습니다.\n해당 계좌의 잔액은 : %d원 입니다!\n",db.getBalance(idid));
  	 	JOptionPane.showInternalMessageDialog(null, message);
   }
   
   //시작 화면 보여주기
   static String Input_id(int x) {
	  if(x == 0) id = JOptionPane.showInputDialog("반갑습니다 고객님! 계좌 번호를 입력해주세요!");    // x == 0 이라면 처음 입력
	  else id = JOptionPane.showInputDialog("계좌번호를 잘못 입력했습니다!! 다시 입력해주세요!");      // x != 0 이라면 재입력
      return id;
   }
   static int Input_pass(int x) {
	  String temp_pw;
	  if(x == 0)  temp_pw = JOptionPane.showInputDialog("비밀번호를 입력해주세요!");    			   // x == 0 이라면 처음 입력
	  else temp_pw = JOptionPane.showInputDialog("비밀번호를 잘못 입력했습니다!! 다시 입력해주세요!");      // x != 0 이라면 재입력
      password = Integer.parseInt(temp_pw);
      return password;
   }
   
 
   
   static void transaction(DataBase db, ATM atm) {
	   
	   JFrame frame = new JFrame("ATM");
  	   //먼저 ATM 화면이 뜨면서 어떤 작업을 할 지 버튼을 통해 입력 받는다(입금, 출금, 잔액조회, 송금)
  	   //JFrame frame = new JFrame("ATM");
  	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	   JLabel label = new JLabel("ATM : 원하시는 작업을 선택하세요.");
  	   Dimension dim = new Dimension(600,400);
  	   frame.add(label);
  	   frame.setLocation(400,200);
  	   frame.setPreferredSize(dim);
  			
  	   // 버튼 생성
  	   FlowLayout layout = new FlowLayout();
  	   frame.setLayout(layout);
  			
  	   JButton print_button=new JButton("잔액조회");
  	   print_button.setBounds(0,0,50,25);
  	   frame.add(print_button);
  	   JButton deposit_button=new JButton("입급");
  	   deposit_button.setBounds(0,0,50,25);
  	   frame.add(deposit_button);
  	   JButton withdraw_button=new JButton("출급");
  	   withdraw_button.setBounds(0,0,50,25);
  	   frame.add(withdraw_button);
  	   JButton remit_button=new JButton("송금");
  	   remit_button.setBounds(0,0,50,25);
  	   frame.add(remit_button);
  	   JButton exit_button=new JButton("종료");
	   exit_button.setBounds(0,0,50,25);
	   frame.add(exit_button);
  	   
  	   // 버튼에 관한 EVENT Handling
  	   ActionListener eventHandler = new ActionListener() {
  		   @Override
  		   public void actionPerformed(ActionEvent e) {
  			 long return_Cash;
  	                if(e.getSource() == print_button){
  	                    System.out.printf("잔액조회하자!");
  	                    cmd = 1;
  	                    return_Cash = Show(cmd, id, db, atm);
  	                }
  	                else if(e.getSource() == deposit_button){
  	                    System.out.printf("입금하자!");
  	                    cmd = 2;
  	                    return_Cash = Show(cmd, id, db, atm);
  	                    atm.Deposit(db, id, return_Cash);
  	                }
  	                else if(e.getSource() == withdraw_button){
  	                    System.out.printf("출금하자!");
  	                    cmd = 3;
  	                    return_Cash = Show(cmd, id, db, atm);
  	                    atm.WithDraw(db, id, return_Cash);
  	                }
  	                else if(e.getSource() == remit_button){
  	                    System.out.printf("송금하자!");
  	                    cmd = 4;
  	                    return_Cash = Show(cmd, id, db, atm);
  	                    String to_id;
	  	                to_id = to_id(0);
	  	            	if(db.checkingId(to_id)==false) {
	  	            		while(db.checkingId(to_id)==false) {
	  	            			to_id = to_id(1);
  	            			}
	  	            	}
  	            		atm.Remit(db, id, to_id, return_Cash);
  	            		remit_success(db, id);
  	                }
  	                else if(e.getSource() == exit_button) {
  	                	System.out.println("종료하자!");
  	                	ShowDown();
  	  	                System.exit(0);
  	                }
  	                
  	            }
  			};
  		    
  			print_button.addActionListener(eventHandler);
  			deposit_button.addActionListener(eventHandler);
  			withdraw_button.addActionListener(eventHandler);
  			remit_button.addActionListener(eventHandler);
  			exit_button.addActionListener(eventHandler);
  			
  			frame.pack();
  			frame.setVisible(true);
  			 
   }
   
   //서비스 종료
   static void ShowDown() {
	  JOptionPane.showInternalMessageDialog(null, "안녕히가세요 고객님!");
   }
}
