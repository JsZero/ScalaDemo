package cn.neu.demo.ChangeDirDemo;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.*;
import java.util.HashMap;

public class OutPutCsv {
    public static void main(String[] args) {
        HashMap<String, Boolean> hs = new HashMap<>();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\imsha\\Desktop\\Yum预估二期\\database_output_by_js.csv")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            String url = "jdbc:postgresql://172.20.41.10:5432/model_deploy";
            Connection con = DriverManager.getConnection(url, "postgres", "talkingdata-yum");
            Statement st = con.createStatement();
            String sql = " select * from data_code_name";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                StringBuffer sb = new StringBuffer();
                for (int tmp = 2; tmp <= columnCount; tmp++) {
                    sb.append(rs.getString(tmp));
                    if (tmp != columnCount) {
                        sb.append(",");
                    }
                }
                sb.append("\n");
                bw.write(sb.toString());
            }
            rs.close();
            st.close();
            con.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
