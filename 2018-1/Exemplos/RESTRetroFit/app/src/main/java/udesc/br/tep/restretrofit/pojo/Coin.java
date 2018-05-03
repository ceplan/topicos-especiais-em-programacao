package udesc.br.tep.restretrofit.pojo;

/**
 * Created by luis on 14/04/18.
 */

public class Coin {
    private Ticker ticker;

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        if (ticker == null) return "Nada encontrado.";

        return ticker.toString();
    }
}
