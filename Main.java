public interface StockObserver {
    void update(String stockName, double newPrice);
}

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private String name;
    private double price;
    private List<StockObserver> observers;

    public Stock(String name, double initialPrice) {
        this.name = name;
        this.price = initialPrice;
        this.observers = new ArrayList<>();
    }

    public void subscribe(StockObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(StockObserver observer) {
        observers.remove(observer);
    }

    public void setPrice(double newPrice) {
        if (this.price != newPrice) {
            this.price = newPrice;
            notifyObservers();
        }
    }

    private void notifyObservers() {
        for (StockObserver observer : observers) {
            observer.update(name, price);
        }
    }
}

public class Client implements StockObserver {
    private String clientName;

    public Client(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public void update(String stockName, double newPrice) {
        System.out.println(clientName + " received update: " + stockName + " is now ₹" + newPrice);
    }
}

public class Main {
    public static void main(String[] args) {
        // Create stock
        Stock reliance = new Stock("RELIANCE", 2500);

        // Create clients
        Client alice = new Client("Alice");
        Client bob = new Client("Bob");

        // Subscribe clients
        reliance.subscribe(alice);
        reliance.subscribe(bob);

        // Simulate price change
        reliance.setPrice(2550);
        reliance.setPrice(2600);

        // Unsubscribe Bob
        reliance.unsubscribe(bob);

        // Another update
        reliance.setPrice(2650);
    }
}
