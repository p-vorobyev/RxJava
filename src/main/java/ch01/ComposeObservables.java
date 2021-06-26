package ch01;

import io.reactivex.rxjava3.core.Observable;

public class ComposeObservables {
    public static void main(String[] args) {
        Observable<String> a = Observable.create(o -> new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            o.onNext("one");
            o.onNext("two");
            o.onComplete();
        }).start());

        Observable<String> b = Observable.create(o -> new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            o.onNext("three");
            o.onNext("four");
            o.onComplete();
        }).start());

        //no guarantee from what observable values will be first
        Observable.merge(a, b).subscribe(System.out::println);

        System.out.println("Output before initializing values.");
    }
}
