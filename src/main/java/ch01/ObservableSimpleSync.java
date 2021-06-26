package ch01;

import io.reactivex.rxjava3.core.Observable;

public class ObservableSimpleSync {
    public static void main(String[] args) {
        Observable<Integer> o = Observable.create(i -> {
            i.onNext(1);
            i.onNext(2);
            i.onNext(3);
            i.onComplete();
        });
        o.map(i -> "Число: " + i).subscribe(System.out::println);
    }
}
