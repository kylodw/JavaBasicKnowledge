package com.kylodw.bitmap.testhttp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/28
 */
public class MyDynamicProxy {
    public static void main(String[] args) {
        HelloImple helloImple = new HelloImple();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(helloImple);
        Hello hello = (Hello) Proxy.newProxyInstance(HelloImple.class.getClassLoader(), HelloImple.class.getInterfaces(), myInvocationHandler);
        hello.sayHello();
    }

    interface Hello {
        void sayHello();
    }

    static class HelloImple implements Hello {
        @Override
        public void sayHello() {
            System.out.println("kylodw");
        }
    }

    static class MyInvocationHandler implements InvocationHandler {
        private Object target;

        public MyInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("添加代码");
            return method.invoke(target, args);
        }
    }
}
