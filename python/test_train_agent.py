import importlib.util
import unittest


@unittest.skipUnless(importlib.util.find_spec("torch") is not None, "torch not installed")
class TrainAgentTest(unittest.TestCase):
    def test_train_agent_returns_model(self):
        from train_agent import train_agent

        model = train_agent(epochs=1)
        self.assertTrue(hasattr(model, "forward"))


if __name__ == "__main__":
    unittest.main()
