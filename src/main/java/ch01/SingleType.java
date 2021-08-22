package ch01;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SingleType {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> mergeWith = getDataA().mergeWith(getDataB());
        Flowable<String> merge = Single.merge(getDataA(), getDataB());
        merge.doOnComplete(() -> System.out.println("Completed merge() method."))
                .subscribe(s -> System.out.println(s + "_merge" + " " + Thread.currentThread()));
        mergeWith.doOnComplete(() -> System.out.println("Completed mergeWith() method."))
                .subscribe(s -> System.out.println(s + "_mergeWith" + " " + Thread.currentThread()));
        System.out.println("Message is printed first because of reactive methods above.");
        Thread.sleep(300);
    }

    static Single<String> getDataA() {
        return Single.<String>create(o -> o.onSuccess("DataA"))
                .subscribeOn(Schedulers.io());
    }

    static Single<String> getDataB() {
        return Single.just("DataB").subscribeOn(Schedulers.io());
    }
}
