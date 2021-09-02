package ch02;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;

/*
* {@link PublishSubject} will push event to all subscribers.
* Each subscriber will get onNext() events from PublishSubject from the moment they subscribed.
*
* */
public class PublishSubjectExample {

    static PublishSubject<String> subject = PublishSubject.<String>create();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                pushEvents(subject);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        subscribeOnPublishSubject();
    }

    private static void pushEvents(PublishSubject<String> subject) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            TimeUnit.MILLISECONDS.sleep(200);
            subject.onNext("Iteration " + i);
        }
        subject.onComplete();
    }

    private static void subscribeOnPublishSubject() throws InterruptedException {
        Observable<String> obs = observable();
        Disposable subscribe = obs.map(s -> "obs: " + s).takeWhile(s -> !s.contains("Iteration 30")).subscribe(System.out::println);

        TimeUnit.MILLISECONDS.sleep(200);
        Observable<String> obs1 = observable();
        Disposable subscribe1 = obs1.map(s -> "obs1: " + s).takeWhile(s -> !s.contains("Iteration 30")).subscribe(System.out::println);

        TimeUnit.MILLISECONDS.sleep(200);
        Observable<String> obs2 = observable();
        Disposable subscribe2 = obs2.map(s -> "obs2: " + s).takeWhile(s -> !s.contains("Iteration 30")).subscribe(System.out::println);

        System.out.println("Is subscribe is disposed? - " + subscribe.isDisposed());
        System.out.println("Is subscribe1 is disposed? - " + subscribe1.isDisposed());
        System.out.println("Is subscribe2 is disposed? - " + subscribe2.isDisposed());
        TimeUnit.SECONDS.sleep(8);
        System.out.println("Is subscribe is disposed? - " + subscribe.isDisposed());
        System.out.println("Is subscribe1 is disposed? - " + subscribe1.isDisposed());
        System.out.println("Is subscribe2 is disposed? - " + subscribe2.isDisposed());
    }

    private static Observable<String> observable() {
        return subject;
    }
}
