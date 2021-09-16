package ch03;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * zip() better use only for synchronized threads of observables. When events produced in same time.
 */
public class SimpleZip {
    public static void main(String[] args) throws InterruptedException {
        WeatherStation station = new PolarWeatherStation();
        Observable<Temperature> temperatureObs = station.temperature();
        Observable<Wind> windObs = station.wind();
        Observable<Weather> weatherObservable = temperatureObs.zipWith(windObs, (temperature, wind) -> new Weather(temperature, wind));
        Disposable zipWithSubscribe = weatherObservable.subscribe((w) -> System.out.println("zipWith: " + w));
        Observable<Weather> zip = Observable.zip(temperatureObs, windObs, (t, w) -> new Weather(t, w));
        Disposable zipSubscribe = zip.subscribe((w) -> System.out.println("zip: " + w));
        awaitTermination(zipWithSubscribe, zipSubscribe);
    }

    private static void awaitTermination(Disposable zipWithSubscribe, Disposable zipSubscribe) {
        while (true) {
            if (zipWithSubscribe.isDisposed() && zipSubscribe.isDisposed()) break;
        }
    }

}

record Temperature(int cels) {}

record Wind(int meterPerSecond) {}

record Weather(Temperature temperature, Wind wind) {}

interface WeatherStation {

    Observable<Temperature> temperature();

    Observable<Wind> wind();

}

class PolarWeatherStation implements WeatherStation {
    @Override
    public Observable<Temperature> temperature() {
        return Observable.just(
                new Temperature(-20),
                new Temperature(-25),
                new Temperature(-17)
        ).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Wind> wind() {
        return Observable.just(
                new Wind(18),
                new Wind(25),
                new Wind(15)
        ).subscribeOn(Schedulers.io());
    }
}
