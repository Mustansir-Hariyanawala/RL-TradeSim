# RL-TradeSim

Browser-based trading simulator that bridges reinforcement learning with frontend deployment.

## Implemented architecture

- **RL engine (Python + PyTorch):** `python/train_agent.py` trains a lightweight policy network against synthetic intraday-like features for Nifty/BankNifty style actions.
- **Backend (Spring Boot + PostgreSQL-ready):** `backend/` exposes cleaned intraday market data via `GET /api/v1/market-data/intraday?symbol=NIFTY|BANKNIFTY`.
- **Browser inference (JavaScript + TensorFlow.js):** `frontend/` loads streamed backend candles and performs in-browser action inference (`HOLD`, `BUY`, `SELL`) in real time.

## Run locally

### Backend

```bash
cd backend
mvn spring-boot:run
```

### Python RL trainer

```bash
cd python
pip install -r requirements.txt
python train_agent.py
```

### Browser client

Serve `frontend/index.html` using any static server and open it in a browser while backend is running on `localhost:8080`.
