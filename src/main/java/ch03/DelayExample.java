package ch03;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

import static io.reactivex.rxjava3.core.Observable.timer;

public class DelayExample {
    public static void main(String[] args) throws InterruptedException {
        //Ordering of events depends on delay for each element
        observable().subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(6);
    }

    private static Observable<String> observable() {
        return Observable.just("тебя", "я", "люблю")
                .delay(word -> timer(word.length(), TimeUnit.SECONDS));
    }
}
