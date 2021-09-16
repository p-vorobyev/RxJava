package ch03;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.time.LocalDateTime;

import static io.reactivex.rxjava3.core.Observable.interval;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * If threads produce values with different delay we can use latest value for combine.
 */
public class CombineLatestExample {
    public static void main(String[] args) {

        Disposable disposable = Observable.combineLatest(
                interval(17, MILLISECONDS).map(i -> "S" + i),
                interval(10, MILLISECONDS).map(i -> "F" + i),
                (s, f) -> f + ":" + s
        ).forEach(System.out::println);

        LocalDateTime start = LocalDateTime.now();
        do {
            if (start.plusSeconds(5).isBefore(LocalDateTime.now()))
                disposable.dispose();
        } while (!disposable.isDisposed());
    }
}
