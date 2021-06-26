package ch01;

import io.reactivex.rxjava3.core.Observable;

public class SyncAndAsyncObservable {
    public static void main(String[] args) {
        //better not run Threads inside Observable
        Observable<Object> obs = Observable.create(o -> new Thread(() -> {
            o.onNext(1);
            o.onNext(2);
            o.onNext(3);
            o.onComplete();
        }).start())
                .doOnNext(i -> System.out.println(Thread.currentThread()))
                .filter(i -> (Integer)i % 2 == 0);

        obs.map(i -> "Значение " + i + " обработано в потоке " + Thread.currentThread())
                .subscribe(System.out::println);

        System.out.println("Вывод до порождения значений.");
    }

}
