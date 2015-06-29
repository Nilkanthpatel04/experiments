

import java.util.UUID;

public class UUIDDemo {
  public static void main(String[] args) {
	for(int i=0; i< 20; i++) {
	  String uuid = UUID.randomUUID().toString();
	  System.out.println("uuid = " + uuid);
	}
  }
}
