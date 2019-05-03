package udesc.br.tep.restretrofit.pojo;

/**
 * Created by luis on 14/04/18.
 */

public class Ticker {
    private float high;
    private float low;
    private float vol;
    private float last;
    private float buy;
    private float sell;
    private int date;

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getVol() {
        return vol;
    }

    public void setVol(float vol) {
        this.vol = vol;
    }

    public float getLast() {
        return last;
    }

    public void setLast(float last) {
        this.last = last;
    }

    public float getBuy() {
        return buy;
    }

    public void setBuy(float buy) {
        this.buy = buy;
    }

    public float getSell() {
        return sell;
    }

    public void setSell(float sell) {
        this.sell = sell;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Ticker{" +
                "high=" + high +
                ", low=" + low +
                ", vol=" + vol +
                ", last=" + last +
                ", buy=" + buy +
                ", sell=" + sell +
                ", date=" + date +
                '}';
    }
}
