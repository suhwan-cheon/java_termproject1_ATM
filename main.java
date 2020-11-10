import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

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
    	screen.transaction(DB, atm);
      
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