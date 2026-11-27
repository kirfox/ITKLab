package ru.example.spring;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

record Storage(double price, String description) {}

public class DataAggregator {

    ProductInfo laptop = new ProductInfo("Ноутбук", 899.99, "Мощный ноут, 2 ядра, 4 оперативки", 4.7);

    ProductInfo aggregateProductInfo(String productName) {

        try(ExecutorService pool = Executors.newFixedThreadPool(3)) {
            CompletableFuture<Double> priceFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    return fetchPrice();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, pool).exceptionally(ex -> 0.0);

            CompletableFuture<String> descFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    return fetchDescription();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, pool).exceptionally(ex -> "Нет данных");

            CompletableFuture<Double> ratingFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    return fetchRating();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, pool).exceptionally(ex -> 0.0);

            CompletableFuture<ProductInfo> completableFuture = priceFuture.
                    thenCombine(descFuture, Storage::new)
                    .thenCombine(ratingFuture, (storage, rating) -> new ProductInfo(productName, storage.price(), storage.description(), rating));
            return completableFuture.join();
        }
    }

    public double fetchPrice() throws InterruptedException {
        Thread.sleep(1000);
        if (Math.random() < 0.2) throw new RuntimeException();
        return laptop.getPrice();
    }

    public String fetchDescription() throws InterruptedException {
        Thread.sleep(2000);
        if (Math.random()  < 0.2) throw new RuntimeException();
        return laptop.getDescription();
    }

    public double fetchRating() throws InterruptedException {
        Thread.sleep(3000);
        if (Math.random()  < 0.2) throw new RuntimeException();
        return laptop.getRating();
    }

}
