package ch06;

import io.reactivex.rxjava3.core.Observable;

public class Buffer {
    public static void main(String[] args) {
        Observable.range(1, 7)
                .buffer(3)
                .subscribe(System.out::println);
    }
}
