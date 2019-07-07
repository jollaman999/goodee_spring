package rsa;

public class CipherMain2 {
    public static void main(String[] args) {
        CipherRSA.getKey();
        CipherRSA.encryptFile("javasecurity/p1.txt", "javasecurity/rsac.ser");
    }
}
