package ch03;

import io.reactivex.rxjava3.core.Observable;

public class ScanExample {
    public static void main(String[] args) {
        Observable<Integer> scan = Observable.range(2, 100)
                .scan(1, (total, current) -> total += current);
        scan.subscribe(System.out::println);
    }
}
