package cn.neu.demo.JavaDemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        TreeSet<Date> ts = new TreeSet<Date>();
        ts.add(sdf.parse("2013-07-20"));
        ts.add(sdf.parse("2013-06-20"));
        ts.add(sdf.parse("2014-06-18"));
        ts.add(sdf.parse("2018-05-20"));
        ts.add(sdf.parse("2019-02-02"));
        for (Date elem : ts) {
            System.out.println(sdf.format(elem));
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        Queue<Date> queue = new PriorityQueue<>(ts);
        for (Date elem : queue) {
            System.out.println(sdf.format(elem));
        }
        System.out.println("=====================================");
        TreeSet<String> tsP = new TreeSet<>();
        tsP.add("2013-07-20");
        tsP.add("2013-06-20");
        tsP.add("2014-06-18");
        tsP.add("2018-05-20");
        tsP.add("2019-02-02");
        for (String elem : tsP) {
            System.out.println(elem);
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        Queue<String> queueP = new PriorityQueue<>(tsP);
        for (String elem : queueP) {
            System.out.println(elem);
        }
    }
}
