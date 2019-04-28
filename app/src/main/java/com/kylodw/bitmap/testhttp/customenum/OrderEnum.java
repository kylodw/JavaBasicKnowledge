package com.kylodw.bitmap.testhttp.customenum;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/28
 * 有限个
 */
public enum OrderEnum implements ShowInfo {
    NONPAYMENT(99, "未付款", "下一个状态", 20, 1) {
        @Override
        public void show() {

        }
    },
    PAID(20, "已付款", "下一个状态", 30, 2) {
        @Override
        public void show() {

        }
    };

    private int orderStatus;
    private String statusName;
    private String nextStatus;
    private int nextCode;
    private int shopType;

    OrderEnum(int status, String statusName, String nextStatus, int nextCode, int shopType) {
        this.orderStatus = status;
        this.statusName = statusName;
        this.nextStatus = nextStatus;
        this.nextCode = nextCode;
        this.shopType = shopType;
    }

    public static void main(String[] args) {
        System.out.println(OrderEnum.NONPAYMENT);
        System.out.println(OrderEnum.NONPAYMENT.nextCode);
        System.out.println(OrderInfo.FUKUAN);
    }


}
