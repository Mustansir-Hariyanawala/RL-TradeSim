package com.rltradesim.api;

import com.rltradesim.model.MarketCandle;
import com.rltradesim.service.MarketDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MarketDataController.class)
class MarketDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MarketDataService marketDataService;

    @Test
    void shouldReturnNormalizedMarketData() throws Exception {
        when(marketDataService.getCleanedIntradayData(" banknifty "))
                .thenReturn(List.of(new MarketCandle(
                        "BANKNIFTY",
                        LocalDateTime.parse("2025-01-01T09:15:00"),
                        48210.0,
                        48290.0,
                        48195.0,
                        48235.0,
                        120000
                )));

        mockMvc.perform(get("/api/v1/market-data/intraday").param("symbol", " banknifty "))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].symbol").value("BANKNIFTY"))
                .andExpect(jsonPath("$[0].close").isNumber());
    }
}
