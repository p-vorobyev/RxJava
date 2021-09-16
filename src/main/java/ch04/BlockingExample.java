package ch04;

import io.reactivex.rxjava3.core.Observable;

import java.util.List;

public class BlockingExample {
    public static void main(String[] args) {
        List<Person> people = persons().toList().blockingGet();
        people.forEach(System.out::println);
    }

    record Person(int id, String name) {}

    static Observable<Person> persons() {
        return Observable.range(0, 30)
                .map(i -> new Person(i, "Name" + i));
    }
}
