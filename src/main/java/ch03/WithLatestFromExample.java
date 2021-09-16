package ch03;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;

import java.time.LocalDateTime;

import static io.reactivex.rxjava3.core.Observable.interval;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class WithLatestFromExample {
    public static void main(String[] args) {
        Observable<String> slow = interval(17, MILLISECONDS).map(i -> "S" + i);
        Observable<String> fast = interval(10, MILLISECONDS).map(i -> "F" + i)
                .delay(100, MILLISECONDS).startWith(Single.just("FX"));// if we have such delay, other thread will receive in this period default value "FX"
        Disposable disposable = slow.withLatestFrom(fast, (s, f) -> s + ":" + f).forEach(System.out::println);

        LocalDateTime start = LocalDateTime.now();
        do {
            if (start.plusSeconds(5).isBefore(LocalDateTime.now()))
                disposable.dispose();
        } while (!disposable.isDisposed());

    }
}
