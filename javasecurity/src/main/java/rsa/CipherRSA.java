package rsa;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/*
RSA : 공개키 암호 알고리즘. 비대칭키.
    공개키로 암호화 -> 개인키로 복호화 가능
    개인키로 암호화 -> 공개키로 복호화 가능
 */
class CipherRSA {
    private static Cipher cipher;
    private static PrivateKey privateKey;
    private static PublicKey publicKey;

    static {
        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            KeyPairGenerator key = KeyPairGenerator.getInstance("RSA");
            key.initialize(2048);
            KeyPair keyPair = key.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String encrypt(String plain) {
        byte[] cipherMsg = new byte[1024];

        try {
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            cipherMsg = cipher.doFinal(plain.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return byteToHex(cipherMsg);
    }

    static String decrypt(String cipherMsg) {
        byte[] plainMsg = new byte[1024];

        try {
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            plainMsg = cipher.doFinal(hexToByte(cipherMsg.trim()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String(plainMsg);
    }

    private static String byteToHex(byte[] cipherMsg) {
        if (cipherMsg == null) {
            return null;
        }

        StringBuilder str = new StringBuilder();

        for (byte b : cipherMsg) {
            str.append(String.format("%02X", b));
        }

        return str.toString();
    }

    private static byte[] hexToByte(String str) {
        if (str == null) {
            return null;
        }

        if (str.length() < 2) {
            return null;
        }

        int len = str.length() / 2;
        byte[] buf = new byte[len];

        for (int i = 0; i < len; i++) {
            buf[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
        }
        return buf;
    }

    static void getKey() {
        try {
            KeyPairGenerator key = KeyPairGenerator.getInstance("RSA");
            key.initialize(2048);
            KeyPair keyPair = key.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("javasecurity/privatekey.ser"));
            oos.writeObject(privateKey);
            oos.flush();
            oos.close();

            oos = new ObjectOutputStream(new FileOutputStream("javasecurity/publickey.ser"));
            oos.writeObject(publicKey);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void encryptFile(String plainfile, String cipherfile) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("javasecurity/privatekey.ser"));
            privateKey = (PrivateKey)ois.readObject();

            ois.close();
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            FileInputStream fis = new FileInputStream(plainfile);
            FileOutputStream fos = new FileOutputStream(cipherfile);
            CipherOutputStream cos = new CipherOutputStream(fos, cipher);
            byte[] buf = new byte[1024];
            int len;

            while ((len = fis.read(buf)) != -1) {
                cos.write(buf, 0, len);
            }

            fis.close();
            cos.flush();
            cos.close();
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void decryptFile(String cipherfile, String plainfile) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("javasecurity/publickey.ser"));
            publicKey = (PublicKey)ois.readObject();

            ois.close();
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            FileInputStream fis = new FileInputStream(cipherfile);
            FileOutputStream fos = new FileOutputStream(plainfile);
            CipherOutputStream cos = new CipherOutputStream(fos, cipher);
            byte[] buf = new byte[1024];
            int len;

            while ((len = fis.read(buf)) != -1) {
                cos.write(buf, 0, len);
            }

            fis.close();
            cos.flush();
            cos.close();
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
