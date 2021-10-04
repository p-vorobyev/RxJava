package ch05.rx.netty;

import io.reactivex.netty.protocol.http.client.HttpClient;
import io.reactivex.netty.protocol.http.server.HttpServer;
import io.reactivex.rxjava3.core.Observable;

import java.math.BigDecimal;

public class RestCurrencyServer {

    private static final BigDecimal rate = new BigDecimal("1.06488");

    public static void main(String[] args) {
        /*HttpServer.newServer(8080).start((req, resp) -> {
                    String amountStr = req.getDecodedPath().substring(1);
                    var amount = new BigDecimal(amountStr);
                    Observable<String> map = Observable.just(amount).map(eur -> eur.multiply(rate)).map(usd -> "");
                    return resp.writeString(map);
                })
                .awaitShutdown();*/
    }
}
