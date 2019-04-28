package com.kylodw.bitmap.testhttp.customenum;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/28
 */
class OrderInfo {
    private final int orderStatus;
    private final String statusName;
    private final String nextStatus;
    private final int nextCode;
    private final int shopType;

    private OrderInfo(int orderStatus, String statusName, String nextStatus, int nextCode, int shopType) {
        this.orderStatus = orderStatus;
        this.statusName = statusName;
        this.nextStatus = nextStatus;
        this.nextCode = nextCode;
        this.shopType = shopType;
    }

    public static final OrderInfo FUKUAN = new OrderInfo(99, "未付款", "下一个状态", 20, 1);

    public int getOrderStatus() {
        return orderStatus;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getNextStatus() {
        return nextStatus;
    }

    public int getNextCode() {
        return nextCode;
    }

    public int getShopType() {
        return shopType;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderStatus=" + orderStatus +
                ", statusName='" + statusName + '\'' +
                ", nextStatus='" + nextStatus + '\'' +
                ", nextCode=" + nextCode +
                ", shopType=" + shopType +
                '}';
    }
}



