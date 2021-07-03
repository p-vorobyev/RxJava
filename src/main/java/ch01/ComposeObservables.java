package ch01;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.ExecutionException;

public class ComposeObservables {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
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
        Observable.merge(a, b).doOnComplete(() -> System.out.println("end of the first subscription")).subscribe(System.out::println);
        Observable.merge(a, b).doOnComplete(() -> System.out.println("end of the second subscription")).subscribe(System.out::println);

        System.out.println("Output before initializing values.");
    }
}
