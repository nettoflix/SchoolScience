package sem.allscience;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import sem.allscience.Matematica.MenuActivity;



public class MainActivity extends AppCompatActivity {
Intent iMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void goMath(View view) {
         iMenu= new Intent(this, MenuActivity.class);
        startActivity(iMenu);

    }
}
