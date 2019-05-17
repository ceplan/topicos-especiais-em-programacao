package udesc.br.tep.sensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sensor Manager e o sensor usado
        /*
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        System.out.println("Sensores que o dispositivo tem: ");

        for (Sensor sensor: deviceSensors) {
            System.out.println(sensor.getName() + " - " + sensor.getVendor());
            System.out.println(sensor.toString());
        }
        */

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor retorno = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_BEAT);

        if (retorno != null) {
            System.out.println("Voce tem");
        } else {
            System.out.println("Não tem");
        }

        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Aqui deve tratar a precisão
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        // O sensor de luminosidade retorna um valor único.
        // A maioria dos sensores retorna três valores (um pra cada eixo)

        float lux = event.values[0];
        System.out.println("Luminosidade: " + lux);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mLight, 5000000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
