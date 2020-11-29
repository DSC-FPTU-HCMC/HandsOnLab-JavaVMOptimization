package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.collections.impl.list.mutable.FastList;


import entities.Sales;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    public static void main( String[] args ) throws Exception
    {
        long startTime = System.currentTimeMillis();
        System.out.println("Start: " + startTime);
        //-XX:NewRatio=3 -XX:SurvivorRatio=3 -XX:TargetSurvivorRatio=80 -XX:MaxTenuringThreshold=8 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:MaxGCPauseMillis=10 -XX:GCPauseIntervalMillis=50 -XX:MaxGCMinorPauseMillis=7 -XX:+ExplicitGCInvokesConcurrent -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=60 -XX:+BindGCTaskThreadsToCPUs -Xnoclassgc -XX:NewRatio=2 -XX:SurvivorRatio=2 -XX:TargetSurvivorRatio=80 -XX:MaxTenuringThreshold=8 -Xms1350M -Xmx1350M
        //InputStream is = new File(App.class.getResourceAsStream("samples.csv"));
        LinkedList<Sales> linkedList = new LinkedList<>();
        ArrayList<Sales> arrayList = new ArrayList<>();
        FastList<Sales> mutableList = FastList.newList();
        BufferedReader br = new BufferedReader(new FileReader("/Users/bangmaple/samples.csv"));
        String str;
        br.readLine();
        while ((str=br.readLine())!=null) {
            String[] row = str.split(",");
            Sales s = new Sales(row[0], row[1], row[2], row[3], row[4], sdf.parse(row[5]),
             row[6], sdf.parse(row[7]), Integer.parseInt(row[8]), Double.parseDouble(row[9]),
              Double.parseDouble(row[10]), Double.parseDouble(row[11]), Double.parseDouble(row[12]),
              Double.parseDouble(row[13]));
        //     linkedList.add(s);
          //  arrayList.add(s);
            mutableList.add(s);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("End: " + endTime);
        System.out.println("Cost: " + (endTime - startTime));
       // for (int i = 0; i < 10; i++) {
      //      System.out.println(list.get(i));
      //  }
        br.close();
    }


}


