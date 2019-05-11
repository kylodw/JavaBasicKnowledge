package com.kylodw.bitmap.testhttp.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/05/05
 */
public class ParseDate  {
    public static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);

    public static void main(String[] args) {
        ExecutorService executorService=Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new MyRunable(i));
        }
    }
  static class MyRunable implements Runnable{
        int i=0;

      public MyRunable(int i) {
          this.i = i;
      }

      @Override
      public void run() {
        try {
            Date t=sdf.parse("2019-5-5 16:44:"+i%60);
            System.out.println(i+"\t:\t"+t);
        }catch (ParseException e){
            e.printStackTrace();
        }
      }
  }
}
