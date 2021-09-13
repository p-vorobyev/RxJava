package ch03;

import io.reactivex.rxjava3.core.Observable;

public class FilterMapping {
    public static void main(String[] args) {
        Observable.just(8, 9, 10)
                .doOnNext(i -> System.out.println("A: " + i))
                .filter(i -> i % 3 > 0)
                .doOnNext(i -> System.out.println("B: " + i))
                .map(i -> "#" + i * 10)
                .doOnNext(i -> System.out.println("C: " + i))
                .filter(s -> s.length() < 4)
                .subscribe(s -> System.out.println("D: " + s));
    }
}
