package ch01;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CompletableType {
    public static void main(String[] args) throws InterruptedException {
        Completable completable = Completable.create(c -> {
            doAsyncAction("CompletableType check");
        });
        completable.subscribe();
        Thread.sleep(200);
    }

    static void doAsyncAction(String data) {
        Single.create(s -> {
            try {
                s.onSuccess("Data is wrote - " + data);
            } catch (Exception e) {
                s.onError(e);
            }
        }).subscribeOn(Schedulers.io()).subscribe(System.out::println);
    }
}
