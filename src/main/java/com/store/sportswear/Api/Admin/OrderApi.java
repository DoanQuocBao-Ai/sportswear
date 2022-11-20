package com.store.sportswear.Api.Admin;

import com.store.sportswear.Dto.SearchOrderDto;
import com.store.sportswear.Entity.Order;
import com.store.sportswear.Entity.Order_Detail;
import com.store.sportswear.Entity.Product;
import com.store.sportswear.Service.IOrderService;
import com.store.sportswear.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderApi {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public Page<Order> getOrderByFilter(@RequestParam(defaultValue = "1") int page, @RequestParam String status,
                                          @RequestParam String fromDay, @RequestParam String toDay) throws ParseException {

        SearchOrderDto object = new SearchOrderDto();
        object.setToDate(toDay);
        object.setStatus(status);
        object.setFromDate(fromDay);
        Page<Order> listOrder = (Page<Order>) orderService.getOrderByFilter(object, page);
        return listOrder;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/assign")
    public void assignOrder(@RequestParam("shipper") String emailShipper,
                                @RequestParam("order_id") long order_id) {
        Order order = orderService.getOrderById(order_id);
        order.setStatus("Đang giao");
        order.setShipper(userService.getUserByEmail(emailShipper));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            String dateStr = format.format(new Date());
            Date date = format.parse(dateStr);
            order.setDelivery_at(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        orderService.saveOrder(order);
    }

    @PostMapping("/update")
    public void confirmCompleteOrder(@RequestParam("order_id") long order_id,
                                    @RequestParam("note") String noteAdmin) {
        Order order = orderService.getOrderById(order_id);

        for(Order_Detail order_detail : order.getDetailList()) {
            Product product = order_detail.getProduct();
            product.setProduct_price(product.getProduct_price() * order_detail.getNumber());
            product.setSale_price(product.getSale_price() - order_detail.getNumber() );
        }
        order.setStatus("Hoàn thành");
        String note = order.getNote();
        if (!noteAdmin.equals("")) {
            note += "<br> Ghi chú admin:\n" + noteAdmin;
        }
        order.setStatus(note);
        orderService.saveOrder(order);
    }

    // xác nhận hoàn thành đơn hàng
    @PostMapping("/cancel")
    public void huyDonHangAdmin(@RequestParam("order_id") long order_id) {
        Order order = orderService.getOrderById(order_id);
        order.setStatus("Đã bị hủy");
        orderService.saveOrder(order);
    }

    // lấy dữ liệu làm báo cáo thống kê
    @GetMapping("/report")
    public List<Object> test() {
        return orderService.getOrderByMonthYear();
    }
}
