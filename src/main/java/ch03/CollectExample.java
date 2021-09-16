package ch03;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

import java.util.ArrayList;
import java.util.List;

public class CollectExample {
    public static void main(String[] args) {
        listSingle().subscribe(System.out::println);
        listSingleCollected().subscribe(System.out::println);
        stringSingle().subscribe(System.out::println);
    }

    private static Single<String> stringSingle() {
        Single<String> single = Observable.range(1, 10)
                .collect(StringBuilder::new, (builder, value) -> builder.append(value).append(","))
                .map(builder -> {
                    String str = builder.toString();
                    return str.substring(0, str.length() - 1);
                });
        return single;
    }

    private static Single<List<Integer>> listSingleCollected() {
        Single<List<Integer>> collect = Observable.range(10, 20)
                .collect(ArrayList::new, List::add);
        return collect;
    }

    private static Single<List<Integer>> listSingle() {
        Single<List<Integer>> listSingle = Observable.range(10, 20)
                .reduce(new ArrayList<>(), (list, value) -> {
                    list.add(value);
                    return list;
                });
        return listSingle;
    }
}
