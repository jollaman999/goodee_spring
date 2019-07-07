package aes;

import java.sql.*;

/*
userbackup2 테이블의 암호화된 email 값을 복호화하여 화면에 출력하기.
key는 비밀번호 해쉬값 앞의 16자리로 정한다.
 */
public class CipherMain4 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");

        Connection connection;
        connection = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/model1", "model1", "1234");

        PreparedStatement pstmt = connection.prepareStatement("select * from userbackup2");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String userid = rs.getString("userid");
            String password = rs.getString("password");
            String key = password.substring(0, 16);
            String encrypted_email = rs.getString("email");
            String decrypted_email = CipherUtil.decrypt(encrypted_email, key);

            System.out.println("userid : " + userid);
            System.out.println("email : " + decrypted_email);
            System.out.println();
        }

        rs.close();
    }
}
