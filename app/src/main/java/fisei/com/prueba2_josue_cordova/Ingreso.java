package fisei.com.prueba2_josue_cordova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Ingreso extends AppCompatActivity {

    private int ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
    }

    public void AbrirActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, ACTIVITY);
    }


}