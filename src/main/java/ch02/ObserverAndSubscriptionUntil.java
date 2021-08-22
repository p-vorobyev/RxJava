package ch02;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ObserverAndSubscriptionUntil {

    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> range = Observable.range(0, 10000000).subscribeOn(Schedulers.io());
        Disposable disposable = range.filter(i -> i % 2 == 0)
                .takeWhile(i -> i <= 300)
                .subscribe(ObserverAndSubscriptionUntil::onNext, ObserverAndSubscriptionUntil::onError, ObserverAndSubscriptionUntil::getAction);
        Thread.sleep(300);
        disposable.dispose();
        System.out.println(disposable.isDisposed());
        System.out.println(counter);
    }

    static void getAction() {
        System.out.println();
        System.out.println(Thread.currentThread());
    }

    static void onNext(Integer i) {
        counter++;
        System.out.print(i + ",");
    }

    static void onError(Throwable t) {}
}
