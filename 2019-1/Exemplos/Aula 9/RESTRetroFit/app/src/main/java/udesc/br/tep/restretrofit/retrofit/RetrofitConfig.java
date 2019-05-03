package udesc.br.tep.restretrofit.retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import udesc.br.tep.restretrofit.service.CoinService;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://www.mercadobitcoin.net/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public CoinService getCoinService() {
        return this.retrofit.create(CoinService.class);
    }

}
