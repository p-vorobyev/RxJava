package ch03;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.List;

public class FlatMapExample {

    public static void main(String[] args) throws InterruptedException {
        var order0 = new Order(1, "ball");
        var order1 = new Order(2, "sneakers");
        var order2 = new Order(3, "timer");
        var customer0 = new Customer(List.of(order0, order1, order2));
        var customer1 = new Customer(List.of(order0, order1, order2));
        Observable<Order> orderObs = Observable.just(customer0, customer1).flatMap(customer -> Observable.fromIterable(customer.orders()));
        Observable<Order> orderObs1 = Observable.just(customer0, customer1).flatMapIterable(Customer::orders);

        Disposable subscribe = orderObs.subscribe(order -> System.out.println("orderObs: " + order));
        Disposable subscribe1 = orderObs1.subscribe(order -> System.out.println("orderObs1: " + order));
    }

    record Order(int id, String item) {}
    record Customer(List<Order> orders){}
}
