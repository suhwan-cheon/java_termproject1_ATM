
import java.util.HashMap;
import java.util.Map;

public class DataBase {
   /*
   database ���������� ���� class�� ����°� ����ѵ�
   �̷��� ���� ���������� ���Ƽ� �̹��ָ� �̷��� ����!
   */
   //(id, password) map
	private static Map<String, Integer> info = new HashMap <String, Integer>();
	
   //(id, cash) map
	private static Map<String, Long> account = new HashMap <String, Long>();
   
	//������ -> ������ �ȵ���?
   public DataBase() {
      // �ʱ� ATM�� DataBase�� ���� �ִٴ� �����Ͽ� �����ϱ� ������
      // constructor�� �ʱ� �������� ������� �־��ݴϴ�. ->info (Hashmap) ���ٰ�
      info.put("00000-00000", 0000);
      account.put("00000-00000", (long)10000000);
      info.put("11111-11111", 0000);
      account.put("11111-11111", (long)20000);
      info.put("22222-22222", 0000);
      account.put("22222-22222", (long)30000);
   }
   
   //�ش� ������ ���� �ܾ��� �����մϴ�.(getter)
   static long getBalance(String id) {
      return account.get(id);
   }
   //�ؽøʿ� �ܾ� ������ ������Ʈ�մϴ�.(setter)
   static void setBalance(String id, long cash) {
      account.put(id, cash);
   }
   //���ڷ� ���� id�� password�� �ʿ� ����� password�� ��ġ�ϴ��� �˷��ݴϴ�.
   static boolean matchingPassword(String id, int password) {
      if(password == info.get(id)) return true;
      return false;
   }
   //���ڷ� ���� id�� db�� �����ϴ��� �˷��ݴϴ�
   static boolean checkingId(String id) {
         if(info.containsKey(id)) {
        	 return true;
         }
         return false;
   }
   /*
    //�ش� ������ ���� �ܾ��� �����մϴ�.(getter)
   static int getID(String id) {
      return account.get(id);
   }
   //�ؽøʿ� �ܾ� ������ ������Ʈ�մϴ�.(setter)
   static void setID(String id, int cash) {
      account.put(id, cash);
   }
   */
}