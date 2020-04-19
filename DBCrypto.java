import javax.crypto.*;
import javax.crypto.spec.*;
import javax.crypto.interfaces.*;
import java.beans.Encoder;
import java.security.*;
import java.security.spec.*;

public class DBCrypto {
    
    public static String PBKDF2(String input, String salt, int workload)
    {
        try {
            byte[] saltBytes = hexToByteArray(salt);

            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(input.toCharArray(), saltBytes, workload, 512);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            String hash = byteArrayToHex(res);
            return hash;
   
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
       
    }

    public static byte[] PBKDF2Bytes(String input, String salt, int workload)
    {
        try {
            byte[] saltBytes = hexToByteArray(salt);

            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(input.toCharArray(), saltBytes, workload, 512);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            return res;
   
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
       
    }
  
    public static String GenSalt(int length)
    {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[length];
        random.nextBytes(bytes); 

        String rand = byteArrayToHex(bytes);

        return rand;
    }

    private static String byteArrayToHex(byte[] a) {
      StringBuilder sb = new StringBuilder((a.length * 2)+2);
      sb.append("0x");
      for(byte b: a)
         sb.append(String.format("%02x", b));
      return sb.toString();
   }

   private static byte[] hexToByteArray(String s) {

    if (s.substring(0, 2).equalsIgnoreCase("0x")) 
    {
        s = s.substring(2);
    }

    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
        data[i / 2] = (byte) ((toDigit(s.charAt(i)) << 4)
                             + toDigit(s.charAt(i+1)));
    }
    return data;
}

private static int toDigit(char hexChar) {
    int digit = Character.digit(hexChar, 16);
    if(digit == -1) {
        throw new IllegalArgumentException(
          "Invalid Hexadecimal Character: "+ hexChar);
    }
    return digit;
}

}