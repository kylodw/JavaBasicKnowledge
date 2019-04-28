
## synchronized 和lock的区别：
 - 1，jvm层面 关键字 底层monitor对象     两个exit 正常退出和异常退出
      lock：新的java类 api层面的锁
 -  2，synchronized 不需要手动释放锁
     lock：需要配对lock(),unlock()释放锁
 -  3，synchronized  不可中断，除非抛异常或者正常完成
     Lock： 可中断 （1，设置超时方法2，调用中断方法中断）
 - 4，synchronized 默认非公平锁
     Lock 默认非公平，但是可以通过变量设置
 - 5，绑定多个Condition
      synchronized  没有
     Lock 可以分组唤醒
     
##  死锁

     > jps -l 查看进程编号
     > jstack 22208 查看堆栈信息是否有死锁
   