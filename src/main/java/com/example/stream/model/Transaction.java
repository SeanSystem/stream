package com.example.stream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sean
 * 2020/03/03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Trader trader;

    private int year;

    private int value;
}
