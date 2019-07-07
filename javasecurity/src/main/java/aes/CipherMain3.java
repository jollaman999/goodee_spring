package aes;

import java.sql.*;

/*
userbackup2 테이블의 email 값을 암호화 하기.
key는 비밀번호 해쉬값의 앞의 16자리로 정한다.
 */
public class CipherMain3 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");

        Connection connection;
        connection = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/model1", "model1", "1234");

        PreparedStatement pstmt = connection.prepareStatement("select * from useraccount");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String userid = rs.getString("userid");
            String password = rs.getString("password");
            String key = password.substring(0, 16);
            String email = rs.getString("email");
            String encrypted_email = CipherUtil.encrypt(email, key);

            pstmt = connection.prepareStatement("update useraccount set email = ? where userid = ?");
            pstmt.setString(1, encrypted_email);
            pstmt.setString(2, userid);

            pstmt.executeUpdate();
        }

        rs.close();
    }
}
