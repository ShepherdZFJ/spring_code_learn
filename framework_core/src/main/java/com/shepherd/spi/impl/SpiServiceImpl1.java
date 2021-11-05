package com.shepherd.spi.impl;

import com.shepherd.spi.SpiService;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/11/5 10:34
 */
public class SpiServiceImpl1 implements SpiService {
    @Override
    public void execute() {
        System.out.println("SpiServiceImpl1 execute finish.....");
    }
}
