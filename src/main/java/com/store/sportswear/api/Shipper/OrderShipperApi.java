package com.store.sportswear.api.Shipper;

import com.store.sportswear.dto.SearchOrderDto;
import com.store.sportswear.dto.UpdateOrderShipperDto;
import com.store.sportswear.entity.Order;
import com.store.sportswear.entity.Order_Detail;
import com.store.sportswear.entity.UserSystem;
import com.store.sportswear.service.IOrderService;
import com.store.sportswear.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/shipper/don-hang")
public class OrderShipperApi {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public Page<Order> getOrderByFilter(@RequestParam(defaultValue = "1") int page, @RequestParam String status,
                                          @RequestParam String fromDate, @RequestParam String toDate, @RequestParam int idShipper)
            throws ParseException {

        SearchOrderDto object = new SearchOrderDto();
        object.setToDate(toDate);
        object.setStatus(status);
        object.setFromDate(fromDate);

        UserSystem shipper = userService.getUserById(idShipper);
        Page<Order> listDonHang = orderService.getOrderByShipper(object, page, 6, shipper);
        return listDonHang;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/update")
    public void updateStatusOrder(@RequestBody UpdateOrderShipperDto updateOrderShipperDto) {
        Order order = orderService.getOrderById(updateOrderShipperDto.getOrder_id());
        for (Order_Detail order_detail : order.getDetailList()) {
            for (UpdateOrderShipperDto.UpdateDetailOrder detailOrder : updateOrderShipperDto
                    .getUpdateDetailOrders()) {
                if (order_detail.getId() == detailOrder.getDetail_id()) {
                    order_detail.setNumber(detailOrder.getNumberOrder());
                }
            }
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            String dateStr = format.format(new Date());
            Date date = format.parse(dateStr);
            order.setReceive_at(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        order.setStatus("Chờ duyệt");

        String note = updateOrderShipperDto.getNoteForShipper();

        if (!note.equals("")) {
            order.setNote("Ghi chú shipper: \n" + updateOrderShipperDto.getNoteForShipper());
        }
        orderService.saveOrder(order);

    }
}
