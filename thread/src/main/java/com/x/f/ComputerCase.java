package com.x.f;

import java.math.BigDecimal;

/**
 * @author zoe
 * @date 2019-01-17
 */
public class ComputerCase {

}

class Computer{
    private static int count = 0;//生产的个数
    private String name;
    private BigDecimal price;

    public Computer(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        count ++;
    }

    public Computer() {
    }

    @Override
    public String toString() {
        return "第"+count+"台电脑"+
                "电脑名字='" + name + '\'' +
                ", 价格=" + price +
                '}';
    }
}
