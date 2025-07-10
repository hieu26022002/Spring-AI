package com.hieunn.spring_ai_demo.mode;

public record BillItem(
        String itemName,
        String unit,
        Integer quantity,
        Double price,
        Double subTotal
) {
}
