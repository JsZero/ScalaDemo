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
//        String suffixPath = "D:\\Workspace\\pycharm-workspace\\ds-yum2project\\data\\hdfs_data\\";
        String suffixPath = args[0];

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


        int count0 = 0;
        for (String data_name : hs.keySet()) {
            if (hs.get(data_name) == true) {
                System.out.println("=========================================");
                System.out.println(data_name);
                count0++;
            }
        }
        System.out.println("=========================================");
        System.out.println(count0);
        System.out.println("=========================================");
//        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
//        for (String data_name : hs.keySet()) {
//            System.out.println(data_name + "\t" + hs.get(data_name));
//        }
        int count1 = 0;
        for (int i = 1; i < args.length - 1; i++) {
            String path = suffixPath + args[i];

            File f = new File(path);// all
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("path: " + path);
            System.out.println(f.isDirectory());
            if (f.isDirectory()) {
                try {
                    for (File fileP : f.listFiles()) {// all child dir
                        if (fileP.isDirectory()) {
                            for (File file : fileP.listFiles()) {
                                if (file.isDirectory()) {
                                    File[] files = file.listFiles();//input_data child dir
                                    String parentPath = file.getAbsolutePath();
//                                    System.out.println("===================================");
//                                    for(File fi:files){
//                                        System.out.println(fi.getAbsolutePath());
//                                    }


                                    if (files.length != 0) {
                                        File sourceFile = null;
                                        if (!files[0].getName().startsWith(".")) {
                                            sourceFile = files[0];
                                        } else if (files[0].getName().startsWith(".") && files.length == 2) {
                                            sourceFile = files[1];
                                        } else {
                                            continue;
                                        }
                                        if (hs.get(sourceFile.getName()) == null && !isDateFormat(sourceFile.getName())) {
                                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                                            System.out.println("Warning: [" + sourceFile.getAbsolutePath() + "] could not be found in database");
                                            continue;
                                        }

                                        String sourcePath = sourceFile.getAbsolutePath();
                                        count1++;
                                        if (isDateFormat(sourceFile.getName())) {
                                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                                            File childFile = sourceFile.listFiles()[0];
                                            String childPath = childFile.getAbsolutePath();
                                            File originFile = new File(file.getAbsolutePath() + "/" + childFile.getName());
                                            boolean coRes = childFile.renameTo(originFile);
                                            System.out.println("child file [" + childPath + "] has been moved to " +
                                                    "origin file [" + originFile.getAbsolutePath() + "] " + (coRes ? "successfully" : "failed"));
                                            boolean deRes = sourceFile.delete();
                                            System.out.println("target dir [" + sourcePath + "] has been deleted " + (deRes ? "successfully" : "failed"));
                                            continue;
                                        } else {
                                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                                            System.out.println("do nothing with file [" + sourcePath + "]");
                                        }

                                        String targetDirPath = parentPath + "/" + timeLayerDir;
                                        String targetPath = parentPath + "/" + timeLayerDir + "/" + sourceFile.getName();
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
                                                System.out.println("source file [" + sourcePath + "] has been moved to " +
                                                        "target file [" + targetPath + "] " + (mvRes ? "successfully" : "failed"));
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
        System.out.println("=========================================");
        System.out.println(count1);
        System.out.println("=========================================");
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
