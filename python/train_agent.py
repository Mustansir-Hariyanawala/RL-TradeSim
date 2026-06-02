import torch
import torch.nn as nn
import torch.optim as optim


class PolicyNetwork(nn.Module):
    def __init__(self, input_dim=3, hidden_dim=16, output_dim=3):
        super().__init__()
        self.layers = nn.Sequential(
            nn.Linear(input_dim, hidden_dim),
            nn.ReLU(),
            nn.Linear(hidden_dim, output_dim),
        )

    def forward(self, x):
        return self.layers(x)


def generate_intraday_batch(batch_size=64):
    data = torch.rand(batch_size, 3)
    labels = torch.argmax(data[:, :2], dim=1)
    labels = torch.where(labels == 0, torch.tensor(1), torch.tensor(2))
    return data, labels


def train_agent(epochs=10, lr=1e-3):
    model = PolicyNetwork()
    optimizer = optim.Adam(model.parameters(), lr=lr)
    loss_fn = nn.CrossEntropyLoss()

    for _ in range(epochs):
        features, targets = generate_intraday_batch()
        logits = model(features)
        loss = loss_fn(logits, targets)
        optimizer.zero_grad()
        loss.backward()
        optimizer.step()

    return model


if __name__ == "__main__":
    trained_model = train_agent()
    torch.save(trained_model.state_dict(), "policy_network.pt")
    print("Saved trained PyTorch policy network to policy_network.pt")
