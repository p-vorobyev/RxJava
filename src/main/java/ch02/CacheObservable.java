package ch02;

import io.reactivex.rxjava3.core.Observable;

public class CacheObservable {
    public static void main(String[] args) {
        withoutCache();
        System.out.println("---");
        withCache();
    }

    private static Observable<Integer> getInts() {
        return Observable.create(o -> {
            System.out.println("Create");
            o.onNext(32);
            o.onComplete();
        });
    }

    private static void withoutCache() {
        System.out.println("Begin");
        subscription(getInts());
        System.out.println("End");
    }

    private static void withCache() {
        Observable<Integer> cached = getInts().cache();
        System.out.println("Begin");
        subscription(cached);
        System.out.println("End");
    }

    private static void subscription(Observable<Integer> ints) {
        ints.subscribe(i -> System.out.println("First subscription value: " + i));
        ints.subscribe(i -> System.out.println("Second subscription value: " + i));
    }
}
