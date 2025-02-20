package com.project.shopapp.MODELS.KEY;


import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class IDorderdetail implements Serializable {
    private Long orderId;
    private Long productId;
//    IDOrderdetail(Long orderId, Long productId) {
//        this.orderId = orderId;
//        this.productId = productId;
//    }

}
