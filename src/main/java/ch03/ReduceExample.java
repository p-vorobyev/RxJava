package ch03;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class ReduceExample {
    public static void main(String[] args) {
        Single<Integer> reduce = Observable.range(0, 4).reduce(0, Integer::sum);
        reduce.subscribe(System.out::println);
    }
}
