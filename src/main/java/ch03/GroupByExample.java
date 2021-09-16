package ch03;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.GroupedObservable;

import java.util.UUID;

public class GroupByExample {

    public static void main(String[] args) {
        Observable<Score> scores = Observable.just(
                new Score(UUID.randomUUID().toString(), 3),
                new Score(UUID.randomUUID().toString(), 4),
                new Score(UUID.randomUUID().toString(), 5)
        );
        Observable<GroupedObservable<String, Score>> groupBy = scores.groupBy(Score::uuid);
        groupBy.subscribe(grObs -> {
            grObs.subscribe(o -> System.out.println("Key[" + grObs.getKey() + "]: " + o));
        });
    }
}

record Score(String uuid, int value) {}
