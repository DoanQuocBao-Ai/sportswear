package com.store.sportswear.Service.implement;

import com.querydsl.core.BooleanBuilder;
import com.store.sportswear.Dto.SearchOrderDto;
import com.store.sportswear.Entity.Order;
import com.store.sportswear.Entity.QOrder;
import com.store.sportswear.Entity.UserSystem;
import com.store.sportswear.Repository.OrderRepository;
import com.store.sportswear.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderRepository repository;

    public OrderServiceImpl() {
        super();
    }

    @Override
    public List<Order> getOrderByFilter(SearchOrderDto object, int page) throws ParseException {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        String status=object.getStatus();
        String fromDate=object.getFromDate();
        String toDate=object.getToDate();
        SimpleDateFormat formatDate= new SimpleDateFormat("dd-mm-yy");
        if (!status.equals("")) {
            booleanBuilder.and(QOrder.order.status.eq(status));
        }

        if (!fromDate.equals("") && fromDate != null) {
            if (status.equals("") || status.equals("Đang chờ giao") || status.equals("Đã hủy")) {
                booleanBuilder.and(QOrder.order.order_at.goe(formatDate.parse(fromDate)));
            } else if (status.equals("Đang giao")) {
                booleanBuilder.and(QOrder.order.delivery_at.goe(formatDate.parse(fromDate)));
            } else { // hoàn thành
                booleanBuilder.and(QOrder.order.receive_at.goe(formatDate.parse(fromDate)));
            }
        }

        if (!toDate.equals("") && toDate != null) {
            if (status.equals("") || status.equals("Đang chờ giao") || status.equals("Đã hủy")) {
                booleanBuilder.and(QOrder.order.order_at.loe(formatDate.parse(toDate)));
            } else if (status.equals("Đang giao")) {
                booleanBuilder.and(QOrder.order.delivery_at.loe(formatDate.parse(toDate)));
            } else { // hoàn thành
                booleanBuilder.and(QOrder.order.receive_at.loe(formatDate.parse(toDate)));
            }
        }
        return (List<Order>) repository.findAll(booleanBuilder, PageRequest.of(page - 1,6));
    }

    @Override
    public Order getOrderById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public Order updateOrderId(Order order) {
        return repository.save(order);
    }

    @Override
    public Page<Order> getOrderByShipper(SearchOrderDto object, int page, int size, UserSystem shipper) throws ParseException {
        BooleanBuilder builder = new BooleanBuilder();
        String status = object.getStatus();
        String fromDate = object.getFromDate();
        String toDate = object.getToDate();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        builder.and(QOrder.order.shipper.eq(shipper));

        if (!status.equals("")) {
            builder.and(QOrder.order.status.eq(status));
        }

        if (!fromDate.equals("") && fromDate != null) {
            if (status.equals("Đang giao")) {
                builder.and(QOrder.order.delivery_at.goe(formatDate.parse(fromDate)));
            } else { // hoàn thành
                builder.and(QOrder.order.receive_at.goe(formatDate.parse(fromDate)));
            }
        }

        if (!toDate.equals("") && toDate != null) {
            if (status.equals("Đang giao")) {
                builder.and(QOrder.order.delivery_at.loe(formatDate.parse(toDate)));
            } else { // hoàn thành
                builder.and(QOrder.order.receive_at.loe(formatDate.parse(toDate)));
            }
        }

        return repository.findAll(builder, PageRequest.of(page - 1, size));
    }

    @Override
    public Order saveOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public List<Object> getOrderByMonthYear() {
        return repository.findByDatetime();
    }

    @Override
    public List<Order> getOrderByStatusAndShipper(String status, UserSystem shipper) {
        return repository.findByStatusAndShipper(status,shipper);
    }

    @Override
    public List<Order> getOrderByUser(UserSystem userSystem) {
        return repository.findByUser(userSystem);
    }

    @Override
    public int countOrderByStatus(String status) {
        return repository.countStatusOrder(status);
    }
}
