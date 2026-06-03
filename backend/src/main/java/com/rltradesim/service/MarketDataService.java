package com.rltradesim.service;

import com.rltradesim.model.MarketCandle;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MarketDataService {
    public List<MarketCandle> getCleanedIntradayData(String symbol) {
        String normalizedSymbol = normalizeSymbol(symbol);

        return List.of(
                new MarketCandle(normalizedSymbol, LocalDateTime.parse("2025-01-01T09:15:00"), 48210.0, 48290.0, 48195.0, 48235.0, 120000),
                new MarketCandle(normalizedSymbol, LocalDateTime.parse("2025-01-01T09:16:00"), 48235.0, 48310.0, 48220.0, 48285.0, 132000),
                new MarketCandle(normalizedSymbol, LocalDateTime.parse("2025-01-01T09:17:00"), 48285.0, 48320.0, 48240.0, 48265.0, 116000)
        );
    }

    private String normalizeSymbol(String symbol) {
        if (symbol == null) {
            return "NIFTY";
        }
        String cleaned = symbol.strip().toUpperCase();
        return switch (cleaned) {
            case "NIFTY", "BANKNIFTY" -> cleaned;
            default -> "NIFTY";
        };
    }
}
