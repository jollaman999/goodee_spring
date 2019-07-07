package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

public class DigestMain2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        Class.forName("org.mariadb.jdbc.Driver");

        Connection connection;
        connection = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/model1", "model1", "1234");

        List<String> password_list = new ArrayList<>();
        PreparedStatement pstmt = connection.prepareStatement("select password from useraccount");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            password_list.add(rs.getString("password"));
        }

        for (String password : password_list) {
            String encrypted_password = getEncryptedString(password, "SHA-256");

            System.out.println("plain : " + password);
            System.out.println("encrypted : " + encrypted_password);

            pstmt = connection.prepareStatement("update userbackup set password = ? where password = ?");
            pstmt.setString(1, encrypted_password);
            pstmt.setString(2, password);

            pstmt.executeUpdate();
        }

        rs.close();
    }

    private static String getEncryptedString(String input, String algo) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algo);
        byte[] plain = input.getBytes();
        byte[] hash = md.digest(plain);

        StringBuilder sb = new StringBuilder();
        for(byte b: hash) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
