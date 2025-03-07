package com.project.shopapp.Controller;

import com.project.shopapp.DTOS.OrderDTO;
import com.project.shopapp.MODELS.Order;
import com.project.shopapp.Service.IMP.IMPOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final IMPOrderService orderService;
     @PostMapping("/create")
    public ResponseEntity<?> createOrder (@Valid @RequestBody OrderDTO orderDTO , BindingResult result) {
         try {
             if (result.hasErrors()) {
                 List<String> errorsMessage = result.getFieldErrors()
                         .stream()
                         .map(FieldError::getDefaultMessage)
                         .toList();
                 return ResponseEntity.badRequest().body(result.getAllErrors());
             }
             Order order = orderService.createOrder(orderDTO);
             return ResponseEntity.ok("Order created successfuly");
         }catch (Exception e){
             return ResponseEntity.badRequest().body(e.getMessage());
         }
       }
       @GetMapping("/{userId}")
       public ResponseEntity<?> getOrders (@Valid @PathVariable("user_id") Long userId){
           try {
               List<Order>orders = orderService.findbyUserid(userId);
              return ResponseEntity.ok(orders);
           }catch (Exception e){
               return ResponseEntity.badRequest().body(e.getMessage());
           }
       }
       @GetMapping("/{id}")
       public ResponseEntity<?> getOrder(@Valid @PathVariable("id") Long orderId){
         try {
             Order existingorderService=orderService.getOrder(orderId);
             return ResponseEntity.ok(existingorderService);

         }catch (Exception e){
             return ResponseEntity.badRequest().body(e.getMessage());
         }
       }
      // Cong viec cua admin
       @PutMapping("/{id}")
       public ResponseEntity<?> updateOrder (@Valid @PathVariable("id") Long id,@Valid @RequestBody OrderDTO orderDTO){
         try{
             Order order = orderService.updateOrder(id,orderDTO);
             return ResponseEntity.ok(order);
         }catch (Exception e){
             return ResponseEntity.badRequest().body(e.getMessage());
         }
       }
       //Xóa mềm => cập nhật trường active ==false
       @DeleteMapping("/{id}")
       public ResponseEntity<?> deleteOrder (@Valid @PathVariable("id") Long id){
         //xóa mềm => cập nhật trường active bằng false
           orderService.deleteOrder(id);
           return ResponseEntity.ok("delete order");
       }
    }
