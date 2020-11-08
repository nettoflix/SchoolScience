package sem.allscience.Matematica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import sem.allscience.Manager.MyMath;
import sem.allscience.R;

public class DeterminanteActivity extends AppCompatActivity {
    Intent iBhaskara;
    TextView resultView;
int matrixFormat = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_determinante);
        //Start intents of the other activities
        iBhaskara = new Intent(this, BhaskaraActivity.class);
        resultView = (TextView) findViewById(R.id.id_detResult);
        resultView.setVisibility(View.GONE);
    //initialize spinner matrix type (det 2x2, 3x,3)
    Spinner spinnerDet = (Spinner) findViewById(R.id.id_spinnerMatrixType);
    ArrayAdapter<CharSequence> detSpinnerAdpt = ArrayAdapter.createFromResource(this, R.array.detType, android.R.layout.simple_spinner_item);
    detSpinnerAdpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerDet.setAdapter(detSpinnerAdpt);
    spinnerDet.setOnItemSelectedListener(new spinnerDetListener());
}
    //Compute Button onClick method
    public void computeDet(View view) {
        //the matrix is 2x2 or 3x3?

        //2x2
        if(matrixFormat == 0)
        {
            //X
            EditText x1text = (EditText) findViewById(R.id.x1);
            EditText x2text = (EditText) findViewById(R.id.x2);

            //Y
            EditText y1text = (EditText) findViewById(R.id.y1);
            EditText y2text = (EditText) findViewById(R.id.y2);





            //TextView resultView = (TextView) findViewById(R.id.TextResult2);
            int x1 = Integer.parseInt( x1text.getText().toString());
            int x2 = Integer.parseInt( x2text.getText().toString());
            int y1 = Integer.parseInt( y1text.getText().toString());
            int y2 = Integer.parseInt( y2text.getText().toString());

            MyMath.vec3d vecX= new  MyMath.vec3d(x1,y1, 0);
            MyMath.vec3d vecY= new  MyMath.vec3d(x2,y2, 0);

            int iResult = MyMath.solveDt(vecX,
                    vecY,
                   null);
            resultView.setText("Determinante="+Integer.toString(iResult));
            resultView.setVisibility(View.VISIBLE);
        }
        //3x3
        if(matrixFormat==1)
        {
            //X
            EditText x1text = (EditText) findViewById(R.id.id_x1);
            EditText x2text = (EditText) findViewById(R.id.id_x2);
            EditText x3text = (EditText) findViewById(R.id.id_x3);
            //Y
            EditText y1text = (EditText) findViewById(R.id.id_y1);
            EditText y2text = (EditText) findViewById(R.id.id_y2);
            EditText y3text = (EditText) findViewById(R.id.id_y3);
            //Z
            EditText z1text = (EditText) findViewById(R.id.id_z1);
            EditText z2text = (EditText) findViewById(R.id.id_z2);
            EditText z3text = (EditText) findViewById(R.id.id_z3);



           // TextView resultView = (TextView) findViewById(R.id.TextResult);
            int x1 = Integer.parseInt( x1text.getText().toString());
            int x2 = Integer.parseInt( x2text.getText().toString());
            int x3 = Integer.parseInt( x3text.getText().toString());
            int y1 = Integer.parseInt( y1text.getText().toString());
            int y2 = Integer.parseInt( y2text.getText().toString());
            int y3 = Integer.parseInt( y3text.getText().toString());
            int z1 = Integer.parseInt( z1text.getText().toString());
            int z2 = Integer.parseInt( z2text.getText().toString());
            int z3 = Integer.parseInt( z3text.getText().toString());
            MyMath.vec3d vecX= new MyMath.vec3d(x1,y1,z1);
            MyMath.vec3d vecY= new  MyMath.vec3d(x2,y2,z2);
            MyMath.vec3d vecZ= new  MyMath.vec3d(x3,y3,z3);
            int iResult = MyMath.solveDt(vecX,
                    vecY, vecZ);
            resultView.setText("Determinante="+ Integer.toString(iResult));
            resultView.setVisibility(View.VISIBLE);
        }

    }


    private class spinnerTopListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String itemSelected = parent.getItemAtPosition(position).toString();
            if (itemSelected.equals("Fórmula De Bhaskara"))
            {
                startActivity(iBhaskara);
            }
        }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
    private class spinnerDetListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String itemSelected = parent.getItemAtPosition(position).toString();
        if(itemSelected.equals("2x2"))
        {
            set2x2Matrix();
        }
        if(itemSelected.equals("3x3"))
        {
            set3x3Matrix();
        }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void set2x2Matrix() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        detFragment2x2 detFragment2x2 = new detFragment2x2();
       fragmentTransaction.replace(R.id.detFragmentHolder, detFragment2x2);
        fragmentTransaction.commit();
        matrixFormat = 0;

    }
    private void set3x3Matrix() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        detFragment3x3 DetFragment = new detFragment3x3();
        fragmentTransaction.replace(R.id.detFragmentHolder, DetFragment);
        fragmentTransaction.commit();
        matrixFormat = 1;
    }
}
