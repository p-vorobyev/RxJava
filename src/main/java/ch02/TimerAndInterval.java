package ch02;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class TimerAndInterval {
    public static void main(String[] args) throws InterruptedException {
        Observable.timer(300, TimeUnit.MILLISECONDS).subscribe((Long l) -> System.out.println("Zero:  " + l));
        Disposable subscribe = Observable.interval(100, TimeUnit.MILLISECONDS).subscribe(System.out::println);
        while (!subscribe.isDisposed()) {
            Thread.sleep(1000);
            subscribe.dispose();
        }
    }
}
