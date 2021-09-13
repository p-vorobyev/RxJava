package ch03;

import io.reactivex.rxjava3.core.Observable;

import java.util.List;
import java.util.stream.IntStream;

public class FlatMapConcurrencyLimit {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        List<User> users = IntStream.range(0, 10_000).mapToObj(User::new).toList();
        //Useful when opening many connections, waiting responses for long time, etc.
        Observable<Integer> observable = Observable.fromIterable(users).flatMap(User::loadProfile, 10);
        observable.subscribe(System.out::println);
    }

    record User(int id) {
        public Observable<Integer> loadProfile() {
            return Observable.just(count++);
        }
    }
}
