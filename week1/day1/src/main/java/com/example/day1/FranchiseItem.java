package com.example.day1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class FranchiseItem extends MenuItem {

    public FranchiseItem(String name, double price) {
        super(name, price);
    }

    @Override
    public String toString() {
        return "\n\tFranchiseItem{" +
                "\n\t\tname='" + this.name + '\'' +
                ",\n\t\tprice=" + this.price +
                "\n\t}\n\t";
    }
}