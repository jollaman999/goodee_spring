package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DigestMain3 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, IOException {
        Class.forName("org.mariadb.jdbc.Driver");

        Connection connection;
        connection = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/model1", "model1", "1234");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("아이디를 입력하세요 : ");
        String userid = br.readLine();

        String sql = "select * from userbackup where userid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userid);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            System.out.println("존재하지 않는 아이디 입니다!");
            return;
        }

        System.out.print("비밀번호를 입력하세요 : ");
        String input_password = br.readLine();

        String dbpassword = resultSet.getString("password");
        String encrypted_input_password = getEncryptedString(input_password, "SHA-256");

        if (encrypted_input_password.equalsIgnoreCase(dbpassword)) {
            System.out.println(userid + "님 반갑습니다!");
        } else {
            System.out.println("비밀번호를 확인해 주십시오!");
        }
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
