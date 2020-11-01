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
   
	//flag�� ���� ����ڿ��� ���� �����ݴϴ�.
   static long Show(int flag, String id, DataBase db, ATM atm) {
	  System.out.println(flag);
      if(flag == 1) { // �ܾ���ȸ
    	 // String message = String.format("Welcome %s, to ATM program!",name);
    	 String message = String.format("�ܾ� ��ȸ �����Դϴ�.\n�ش� ������ �ܾ��� : %d�� �Դϴ�!\n",db.getBalance(id));
    	 JOptionPane.showInternalMessageDialog(null, message);
         return -1;
      }
      else if(flag == 2) { // �Ա�
         while(true) {
        	 String temp_cash = JOptionPane.showInputDialog("�Ա��� �ݾ��� �Է��ϼ���");
        	 cash = Long.parseLong(temp_cash);
        	 String t_one_cnt = JOptionPane.showInputDialog("�Ա��� ����� �� �������� �� ������ �Է��ϼ���");
        	 one_cnt = Integer.parseInt(t_one_cnt);
        	 String t_five_cnt = JOptionPane.showInputDialog("�Ա��� ����� �� �� �������� �� ������ �Է��ϼ���");
        	 five_cnt = Integer.parseInt(t_five_cnt);
        	 if(cash != ((long)one_cnt*10000)+((long)five_cnt*50000)) {
                 //System.out.println("�߸��Է��ϼ̽��ϴ�! �ٽ� �Է����ּ���!");
        		 JOptionPane.showInternalMessageDialog(null, "�߸��Է��ϼ̽��ϴ�! �ٽ� �Է����ּ���!");
             }
        	 else {
        		 atm.one += one_cnt;
            	 atm.five += five_cnt;
            	 String message = String.format("�Ա��� �Ϸ�Ǿ����ϴ�.\n�ش� ������ �ܾ��� : %d�� �Դϴ�!\n",db.getBalance(id) + cash);
            	 JOptionPane.showInternalMessageDialog(null, message);
            	 break;
        	 }
         }
         return cash;
      }
      else if(flag == 3) { // ���
         
         while(true) {
        	 String temp_cash = JOptionPane.showInputDialog("����� �ݾ��� �Է��ϼ���");
        	 cash = Long.parseLong(temp_cash);
        	 String t_one_cnt = JOptionPane.showInputDialog("����� ����� �� �������� �� ������ �Է��ϼ���");
        	 one_cnt = Integer.parseInt(t_one_cnt);
        	 String t_five_cnt = JOptionPane.showInputDialog("����� ����� �� �� �������� �� ������ �Է��ϼ���");
        	 five_cnt = Integer.parseInt(t_five_cnt);
        	 if(cash != ((long)one_cnt*10000)+((long)five_cnt*50000)) {
        		 JOptionPane.showInternalMessageDialog(null, "�߸��Է��ϼ̽��ϴ�! �ٽ� �Է����ּ���!");
             }
        	 else {
        		 int cmp_one = atm.one;
                 int cmp_five = atm.five;
                 
                 // atm ���� ���� �ǰ� 5���� ���� �󸶳� ���Ҵ����� ���� ��� �������� �Ǵ�
                 if(one_cnt <= cmp_one && five_cnt <= cmp_five) { 
                	 atm.one -= one_cnt;
                	 atm.five -= five_cnt;
                	 String message = String.format("����� �Ϸ�Ǿ����ϴ�.\n�ش� ������ �ܾ��� : %d�� �Դϴ�!\n",db.getBalance(id) - cash);
                	 JOptionPane.showInternalMessageDialog(null, message);
                	 break;
                 }
                 else {
                	 JOptionPane.showInternalMessageDialog(null, "ATM ����� ���� �����մϴ�! �ٽ� �Է����ּ���!");
                 }
        	 }
         }
         return cash;
         
      }
      else { // �۱�
         // �Է� �ݾ� �� ����� Ȯ��
         while(true) {
        	 String temp_cash = JOptionPane.showInputDialog("�۱��� �ݾ��� �Է��ϼ���");
        	 cash = Long.parseLong(temp_cash);
             if(cash > db.getBalance(id)) {
            	 JOptionPane.showInternalMessageDialog(null, "���� �ܾ��� �����մϴ�! �ٽ� �Է����ּ���!");
             }
             else break;
         }
         
         return cash;
      }
   }
   
   // �۱��� ���¸� �������� �����ϴ� �Լ� 
   static String to_id(int x) {
	   if(x == 0) remit_id = JOptionPane.showInputDialog("�۱��� ���¸� �Է����ּ��� : ");
	   else remit_id = JOptionPane.showInputDialog("�߸��� �۱� �����Դϴ�! �ٽ� �Է����ּ���!");
	   return remit_id;
   }
   
   static void remit_success(DataBase db,String idid){
	   	String message = String.format("�۱��� �Ϸ��߽��ϴ�.\n�ش� ������ �ܾ��� : %d�� �Դϴ�!\n",db.getBalance(idid));
  	 	JOptionPane.showInternalMessageDialog(null, message);
   }
   
   //���� ȭ�� �����ֱ�
   static String Input_id(int x) {
	  if(x == 0) id = JOptionPane.showInputDialog("�ݰ����ϴ� ������! ���� ��ȣ�� �Է����ּ���!");    // x == 0 �̶�� ó�� �Է�
	  else id = JOptionPane.showInputDialog("���¹�ȣ�� �߸� �Է��߽��ϴ�!! �ٽ� �Է����ּ���!");      // x != 0 �̶�� ���Է�
      return id;
   }
   static int Input_pass(int x) {
	  String temp_pw;
	  if(x == 0)  temp_pw = JOptionPane.showInputDialog("��й�ȣ�� �Է����ּ���!");    			   // x == 0 �̶�� ó�� �Է�
	  else temp_pw = JOptionPane.showInputDialog("��й�ȣ�� �߸� �Է��߽��ϴ�!! �ٽ� �Է����ּ���!");      // x != 0 �̶�� ���Է�
      password = Integer.parseInt(temp_pw);
      return password;
   }
   
   static int transaction() {
	   JFrame frame = new JFrame("ATM");
	   //���� ATM ȭ���� �߸鼭 � �۾��� �� �� ��ư�� ���� �Է� �޴´�(�Ա�, ���, �ܾ���ȸ, �۱�)
	   //JFrame frame = new JFrame("ATM");
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   JLabel label = new JLabel("ATM : ���Ͻô� �۾��� �����ϼ���.");
	   Dimension dim = new Dimension(600,400);
	   frame.add(label);
	   frame.setLocation(400,200);
	   frame.setPreferredSize(dim);
			
	   // ��ư ����
	   FlowLayout layout = new FlowLayout();
	   frame.setLayout(layout);
			
	   JButton print_button=new JButton("�ܾ���ȸ");
	   print_button.setBounds(0,0,50,25);
	   frame.add(print_button);
	   JButton deposit_button=new JButton("�Ա�");
	   deposit_button.setBounds(0,0,50,25);
	   frame.add(deposit_button);
	   JButton withdraw_button=new JButton("���");
	   withdraw_button.setBounds(0,0,50,25);
	   frame.add(withdraw_button);
	   JButton remit_button=new JButton("�۱�");
	   remit_button.setBounds(0,0,50,25);
	   frame.add(remit_button);
			
	   // ��ư�� ���� EVENT Handling
	   // cmd = input.nextInt();
	   ActionListener eventHandler = new ActionListener() {
		   @Override
		   public void actionPerformed(ActionEvent e) {
	                
	                if(e.getSource() == print_button){
	                    System.out.printf("�ܾ���ȸ����!");
	                    cmd = 1;
	                    frame.dispose();
	                }
	                else if(e.getSource() == deposit_button){
	                    System.out.printf("�Ա�����!");
	                    cmd = 2;
	                    frame.dispose();
	                }
	                else if(e.getSource() == withdraw_button){
	                    System.out.printf("�������!");
	                    cmd = 3;
	                    frame.dispose();
	                }
	                else if(e.getSource() == remit_button){
	                    System.out.printf("�۱�����!");
	                    cmd = 4;
	                    frame.dispose();
	                }
	            }
			};
		    
			print_button.addActionListener(eventHandler);
			deposit_button.addActionListener(eventHandler);
			withdraw_button.addActionListener(eventHandler);
			remit_button.addActionListener(eventHandler);
			
			frame.pack();
			frame.setVisible(true);
			
			
	   //frame.exit(0);
	   //System.exit(0);
	   return cmd;
   }
   
   //���� ����
   static void ShowDown() {
	  JOptionPane.showInternalMessageDialog(null, "�ȳ��������� ������!");
   }
}