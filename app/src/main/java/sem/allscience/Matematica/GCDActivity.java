package sem.allscience.Matematica;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import sem.allscience.Manager.Handler;
import sem.allscience.R;


public class GCDActivity extends AppCompatActivity {
    Button calcule;
    TextView resultView;
    EditText gcdwidth;
    EditText gcdheight;
    EuclidsView euclidsView;
    TextView euclidsWarning1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcd);
        //initialize references to the XML views
        euclidsWarning1 = (TextView) findViewById(R.id.euclids_warning1);
        calcule = (Button)findViewById(R.id.id_calcular_gcd);
        resultView = (TextView)  findViewById(R.id.id_gcd_result);
        gcdwidth = (EditText)findViewById(R.id.id_gcdwidth);
        gcdheight = (EditText)findViewById(R.id.id_gcdheight);
        euclidsView = (EuclidsView) findViewById(R.id.euclids);
        //send a reference of this class to the  euclids view, so it has access to the other views surrounding it
        euclidsView.activity = this;
        //don't draw the euclid rectangle yet, because the user still didn't enter any value
        euclidsView.setVisibility(View.GONE);
        euclidsWarning1.setVisibility(View.VISIBLE);
        resultView.setVisibility(View.GONE);

    }
    public void calcularGCD(View view)
    {
        //check if the string is parsable to integer, mainly checking if it is not null
if(Handler.isIntParsable(gcdheight.getText().toString())&& Handler.isIntParsable(gcdwidth.getText().toString()))
{
    euclidsWarning1.setVisibility(View.GONE);
    euclidsView.setVisibility(View.VISIBLE);
    int a = Integer.parseInt(gcdheight.getText().toString());
    int b = Integer.parseInt(gcdwidth.getText().toString());
    int result = gcd(a,b);
    Log.d("a", String.valueOf(a));
    Log.d("b", String.valueOf(b));
    resultView.setText("MDC="+ String.valueOf(result));
    resultView.setVisibility(View.VISIBLE);
    euclidsView.setValues(a,b);
    euclidsView.invalidate();
}

    }
    public int gcd(int a, int b)
    {
        if(a==0) return b;
        return gcd(b%a, a);
    }
}
