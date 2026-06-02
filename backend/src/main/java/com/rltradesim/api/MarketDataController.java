package com.rltradesim.api;

import com.rltradesim.model.MarketCandle;
import com.rltradesim.service.MarketDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/market-data")
public class MarketDataController {
    private final MarketDataService marketDataService;

    public MarketDataController(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    @GetMapping("/intraday")
    public List<MarketCandle> intraday(@RequestParam(defaultValue = "NIFTY") String symbol) {
        return marketDataService.getCleanedIntradayData(symbol);
    }
}
