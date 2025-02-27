package com.project.shopapp.Controller;


import com.project.shopapp.DTOS.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailController {
    @PostMapping("")
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
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllOrderDetails(@Valid @PathVariable("id") Long id)  {

//        return ResponseEntity.ok().body("Get all order details successfully "+ id);
        return ResponseEntity.ok().body("Le Thành Nhan "+ id);
    }
    //Lấy ra danh sách của order details của một cái order nào do
    @GetMapping("/order/{id}")
    public ResponseEntity<?> GetOrderDetailss(@Valid @PathVariable("id") Long id){
        return ResponseEntity.ok().body("Get all order details successfully");
    }
    //Cap nhat order detail thong qua id
    @PutMapping("/{id}")
    public ResponseEntity<?> UpdateOrderDetails(@Valid @PathVariable("id") Long id
    ,@RequestBody OrderDetailDTO newOrderDetailsData){
        return ResponseEntity.ok().body("Update order details with "+id +" new orderDetailsData "+newOrderDetailsData);
    }
    //xoa order detail thong qua id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteOrderDetails(@Valid @PathVariable("id") Long id
    ){
        return ResponseEntity.noContent().build();
    }
 }
