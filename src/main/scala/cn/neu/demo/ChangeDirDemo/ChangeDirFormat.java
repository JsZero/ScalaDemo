package cn.neu.demo.ChangeDirDemo;


import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;

public class ChangeDirFormat {
    public static void main(String[] args) {
        String timeLayerDir = args[args.length - 1]; // "2018-08-11";
        String suffixPath = "D:\\Workspace\\pycharm-workspace\\ds-yum2project\\data\\hdfs_data\\";
        for (int i = 0; i < args.length - 1; i++) {
            String path = suffixPath + args[i];
            System.out.println("=============================================================");
            System.out.println(path);
            int num = 0;
            HashMap<String, Boolean> hs;
            hs = new HashMap<>();

            try {
                Class.forName("org.postgresql.Driver").newInstance();
                String url = "jdbc:postgresql://172.20.41.10:5432/model_deploy";
                Connection con = DriverManager.getConnection(url, "postgres", "talkingdata-yum");
                Statement st = con.createStatement();
                String sql = " select data_code,data_name,relative_path from data_code_name";
                ResultSet rs = st.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                while (rs.next()) {
                    if (rs.getString(1).startsWith("TD")) {
                        num++;
//                    System.out.print(rs.getString(2) + "\t");
//                    System.out.println(rs.getString(3));
                        HashSet<String> s = new HashSet<>();
                        CollectionUtils.addAll(s, rs.getString(3).split(";"));
                        hs.put(rs.getString(2), s.contains("[yyyymmdd]"));
                    }
                }
                rs.close();
                st.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
//        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
//        for (String data_name : hs.keySet()) {
//            System.out.println(data_name + "\t" + hs.get(data_name));
//        }
            File f = new File(path);// all
            if (f.isDirectory()) {
                try {
                    for (File fileP : f.listFiles()) {// all的所有子文件夹
                        if(fileP.isDirectory()){
                            for(File file:fileP.listFiles()){
                                if (file.isDirectory()) {
                                    File[] files = file.listFiles();//input_data等文件夹的所有子目录
                                    String parentPath = file.getAbsolutePath();
                                    if (files.length == 1) {
//                            files[0].rename
                                        File sourceFile = files[0];
                                        if (hs.get(sourceFile.getName()) == null && !isDateFormat(sourceFile.getName())) {
                                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                                            System.out.println("Warning: [" + sourceFile.getName() + "] could not be found in database");
                                            continue;
                                        }
                                        String sourcePath = sourceFile.getAbsolutePath();

                                        if (isDateFormat(sourceFile.getName())) {
                                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                                            File childFile = sourceFile.listFiles()[0];
                                            String childPath = childFile.getAbsolutePath();
                                            File originFile = new File(file.getAbsolutePath() + "\\" + childFile.getName());
                                            boolean coRes = childFile.renameTo(originFile);
                                            System.out.println("child file [" + childPath + "]\nhas been moved to\n" +
                                                    "origin file [" + originFile.getAbsolutePath() + "]\n" + (coRes ? "successfully" : "failed"));
                                            boolean deRes = sourceFile.delete();
                                            System.out.println("target dir [" + sourcePath + "] has been deleted " + (deRes ? "successfully" : "failed"));
                                            continue;
                                        }

                                        String targetDirPath = parentPath + "\\" + timeLayerDir;
                                        String targetPath = parentPath + "\\" + timeLayerDir + "\\" + files[0].getName();
                                        File targetDir = new File(targetDirPath);
                                        File targetFile = new File(targetPath);
                                        if (hs.get(sourceFile.getName())) {
//                                System.out.println("sourcePath: " + sourcePath);
//                                System.out.println("targetPath: " + targetPath);
                                            if (!targetDir.exists()) {
                                                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                                                boolean mkdirRes = targetDir.mkdirs();
                                                System.out.println("target dir [" + targetDir + "] has been created " + (mkdirRes ? "successfully" : "failed"));
                                                boolean mvRes = sourceFile.renameTo(targetFile);
                                                System.out.println("source file [" + sourcePath + "]\nhas been moved to\n" +
                                                        "target file [" + targetPath + "]\n" + (mvRes ? "successfully" : "failed"));
                                            }
                                        }


                                    }
                                }
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
//        for (int i = 0; i < args.length - 1; i++) {
//            String path = args[i]; // "D:\\Workspace\\pycharm-workspace\\ds-yum2project\\data\\hdfs_data\\all\\input_data";
//
//        }
    }

    public static boolean isDateFormat(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            sdf.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
