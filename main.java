import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class main {
   
    static Scanner input = new Scanner(System.in);
    static int command; //사용자의 입력을 받는 커맨드
    static ATM atm = new ATM(); //ATM 객체
    static DataBase DB = new DataBase(); //DataBase 객체
    static Interface screen; //Interface 객체
    static String id;
    static int password;
   
    public static void main(String[] args) {
      
    	//login
    	serviceOn();
    	screen.transaction(DB, atm);
      
    }
   
    // GUI로 바꿈
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
    //입력한 id가 형식에 맞는지 확인
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
    //입력한 password가 형식에 맞는지 확인
    private static boolean Checkpass(String id, int password) {
    	if(!DB.matchingPassword(id, password)) return false;
    	return true;
    }
    
	}