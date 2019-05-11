package com.kylodw.bitmap.testhttp.data;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/30
 */
public interface AddTask {
    void addTask(int a);
}

class MyTask implements AddTask {
    @Override
    public void addTask(int a) {
        System.out.println(a);
    }
}

class Test {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        myTask.addTask(1);
    }
}
