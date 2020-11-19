package sample;

import java.text.NumberFormat;
import java.util.Locale;

public class Order {
    private String name;
    private String createTime;
    private String status;
    private String price;

    public Order(String name, String createTime, String status, String price) {
        this.name = name;
        this.createTime = createTime;
        this.status = status;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getStatus() {
        return status;
    }

    public String getPrice() {
        return NumberFormat.getNumberInstance(Locale.US).format(price);
    }
}
