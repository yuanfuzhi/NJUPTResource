package com.stitp.recommendation.web;

import com.stitp.recommendation.mapper.OrderMapper;
import com.stitp.recommendation.result.JsonResult;
import com.stitp.recommendation.result.Order;
import com.stitp.recommendation.result.OrderData;
import com.stitp.recommendation.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/song")
public class HelloController {
    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public JsonResult hello(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> map) {
       // double anger, disgust, fear, happiness, neutral, sadness, surprise;
        double[] emotion = new double[7];
        emotion[0] = Double.parseDouble(map.get("anger"));
        emotion[1] = Double.parseDouble(map.get("disgust"));
        emotion[2] = Double.parseDouble(map.get("fear"));
        emotion[3] = Double.parseDouble(map.get("happiness"));
        emotion[4] = Double.parseDouble(map.get("neutral"));
        emotion[5] = Double.parseDouble(map.get("sadness"));
        emotion[6] = Double.parseDouble(map.get("surprise"));

        List<OrderData> orderDatas = new ArrayList<OrderData>();
        Random rand = new Random();

        boolean[] used = new boolean[20];
        int index;
        for(int i=0; i<=6; i++) {
            if(emotion[i] > 10) {
                for(int j=0; j<20; j++)
                    used[j] = false;
                List<Order> orders = orderMapper.recommendByType(i+1);
                int num = (int)(emotion[i] / 10.0);
                for(int j=0; j<num; j++) {
                    index = rand.nextInt(20);
                    while(used[index])
                        index = rand.nextInt(20);
                    used[index] = true;
                    Order result = orders.get(index);
                    orderDatas.add(new OrderData(result));
                }
            }
        }
        return new JsonResult(orderDatas);
    }
}
