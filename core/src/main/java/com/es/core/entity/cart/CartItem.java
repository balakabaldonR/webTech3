package com.es.core.entity.cart;

import com.es.core.entity.phone.Phone;

public class CartItem {

    private Phone phone;
    private Long quantity;

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
