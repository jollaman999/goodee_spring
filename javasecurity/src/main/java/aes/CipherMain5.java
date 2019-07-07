package aes;

public class CipherMain5 {
    public static void main(String[] args) {
        String key = "abc1234567";
        CipherUtil.encryptFile("javasecurity/p1.txt", "javasecurity/c.ser", key);
    }
}
