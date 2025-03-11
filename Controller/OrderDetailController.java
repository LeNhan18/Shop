package com.project.shopapp.Controller;


import com.project.shopapp.DTOS.OrderDetailDTO;
import com.project.shopapp.Exception.DataNotFoundException;
import com.project.shopapp.MODELS.OrderDetail;
import com.project.shopapp.Respones.OrderDetailResponse;
import com.project.shopapp.Service.OrderDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orderdetails")
@RequiredArgsConstructor

public class OrderDetailController {
    @Autowired
    private final OrderDetailService orderDetailService;
    @PostMapping("/2")
    public ResponseEntity<?> orderDetails(@Valid @RequestBody OrderDetailDTO orderDetailDTO, BindingResult result)  {
          try{
              if(result.hasErrors()){
                  List<String> results= result.getFieldErrors()
                          .stream()
                          .map(err -> "Field '" + err.getField() + "': " + err.getDefaultMessage())
                          .collect(Collectors.toList());
                  return ResponseEntity.badRequest().body(results);
              }
          }catch(Exception e){
              e.getMessage();
          }
          return ResponseEntity.ok().body("Create order details successfully");
    }
    @PostMapping("")
    public ResponseEntity<?> createOrderDetails(@Valid @RequestBody OrderDetailDTO orderDet){
        try{
            OrderDetail newOrderDet = orderDetailService.CreateOrderDetail(orderDet);
            return ResponseEntity.ok().body(OrderDetailResponse.fromOrder(newOrderDet));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllOrderDetails(@Valid @PathVariable("id") Long id)  {
       OrderDetail orderDetail = orderDetailService.getOrderDetail(id);
        return ResponseEntity.ok(orderDetail);
    }
    //Lấy ra danh sách của order details của một cái order nào do
    @GetMapping("/order/{id}")
    public ResponseEntity<?> GetOrderDetailss(@Valid @PathVariable("id") Long id){
       List<OrderDetail> orderDetailList = orderDetailService.findById(id);
       List<OrderDetailResponse> orderDetailResponses = orderDetailList.stream()
               .map(OrderDetailResponse::fromOrder)
               .toList();
        return ResponseEntity.ok(orderDetailResponses);
    }
    //Cap nhat order detail thong qua id
    @PutMapping("/{id}")
    public ResponseEntity<?> UpdateOrderDetails(@Valid @PathVariable("id") Long id
    ,@RequestBody OrderDetailDTO orderDetailsDTO){
        //Tạo mot doi tượng order detail tham chiếu tới
        try{
            OrderDetail orderDetail = orderDetailService.updateOrderDetail(id, orderDetailsDTO);
            return ResponseEntity.ok().body(orderDetail);
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //xoa order detail thong qua id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteOrderDetails(@Valid @PathVariable("id") Long id
    ){
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.ok().body("Delete Succesly");
    }
 }
