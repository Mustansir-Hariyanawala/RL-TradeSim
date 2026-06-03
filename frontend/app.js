const output = document.getElementById('output');

function buildInferenceModel() {
  const model = tf.sequential();
  model.add(tf.layers.dense({ units: 8, inputShape: [3], activation: 'relu' }));
  model.add(tf.layers.dense({ units: 3, activation: 'softmax' }));
  return model;
}

function actionFromTensor(tensor) {
  const actions = ['HOLD', 'BUY', 'SELL'];
  const idx = tensor.argMax(1).dataSync()[0];
  return actions[idx];
}

async function runSimulation() {
  const response = await fetch('http://localhost:8080/api/v1/market-data/intraday?symbol=NIFTY');
  const candles = await response.json();

  const latest = candles[candles.length - 1];
  const features = tf.tensor2d([[latest.open, latest.close, latest.volume]]).div(100000);

  const model = buildInferenceModel();
  const action = actionFromTensor(model.predict(features));

  output.textContent = `Latest close: ${latest.close}\nInference action: ${action}`;
}

document.getElementById('run').addEventListener('click', () => {
  runSimulation().catch((error) => {
    output.textContent = `Simulation failed: ${error.message}`;
  });
});
