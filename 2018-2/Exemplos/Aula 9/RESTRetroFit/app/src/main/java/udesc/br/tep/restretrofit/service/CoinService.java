package udesc.br.tep.restretrofit.service;

import retrofit2.Call;
import retrofit2.http.*;
import udesc.br.tep.restretrofit.pojo.Coin;

/**
 * Created by luis on 14/04/18.
 */

public interface CoinService {
    @GET("{coin}/ticker")
    Call<Coin> buscarTickerCoin(@Path("coin") String coin);
}
