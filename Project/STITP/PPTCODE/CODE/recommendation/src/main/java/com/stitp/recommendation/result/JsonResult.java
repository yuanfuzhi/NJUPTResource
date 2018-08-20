package com.stitp.recommendation.result;

import java.util.List;

public class JsonResult {
    private List<OrderData> orders;

    public JsonResult(List<OrderData> orders) {
        this.orders = orders;
    }

    public List<OrderData> getOrder() {
        return orders;
    }

    public void setOrder(List<OrderData> orders) {
        this.orders = orders;
    }
}
