package com.rltradesim.model;

import java.time.LocalDateTime;

public record MarketCandle(
        String symbol,
        LocalDateTime timestamp,
        double open,
        double high,
        double low,
        double close,
        long volume
) {
}
