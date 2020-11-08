package sem.allscience.Matematica;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

import sem.allscience.Manager.MyMath;
import sem.allscience.R;

public class BhaskaraActivity extends AppCompatActivity {
    Intent iDeterminante;
    Dialog detailsDialog;
    MyMath math;
    MyMath.bhaskara quadraticDetails;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhaskara);
        //initalize activities intents
        iDeterminante = new Intent(this, DeterminanteActivity.class);
        //initialize objects
        math= new MyMath();
        detailsDialog = new Dialog(this);
    }
    //triggered but the calculete button

    public void calculeBhaskara(View view) {
        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        EditText aText = (EditText)findViewById(R.id.slot_a);
        EditText bText = (EditText)findViewById(R.id.slot_b);
        EditText cText = (EditText)findViewById(R.id.slot_c);
        TextView result =(TextView)findViewById(R.id.id_quadratic_results);

        if(math.isNumeric(aText.getText().toString()) &&math.isNumeric(bText.getText().toString())&&math.isNumeric(cText.getText().toString()))
        {
            int iA = Integer.parseInt(aText.getText().toString());
            int iB = Integer.parseInt(bText.getText().toString());
            int iC = Integer.parseInt(cText.getText().toString());

            double dA = Double.valueOf(iA);
            double dB = Double.valueOf(iB);
            double dC = Double.valueOf(iC);
            quadraticDetails = math.solveQuadraticEquation(dA, dB, dC);

            if (quadraticDetails.noSolution) {
                resultToShow = "Sem solução. A não pode ser 0!!";
            } else if (quadraticDetails.complexSolution) {
                resultToShow = "Delta<0 Soluções Complexas!";
            } else if (quadraticDetails.singleSolution) {
                resultToShow = numberFormat.format(quadraticDetails.x1);
            } else {
                resultToShow = numberFormat.format(quadraticDetails.x1) + " and " + numberFormat.format(quadraticDetails.x2);
                //resultToShow = String.valueOf(quadraticDetails.x1)+" and " + String.valueOf(quadraticDetails.x2);
            }

            result.setText(resultToShow);

            delta = numberFormat.format(quadraticDetails.delta);
            powerOfB = numberFormat.format(quadraticDetails.power_Of_B);
            mult_4ac = numberFormat.format(quadraticDetails.mult_4ac);
            sqrt_delta = numberFormat.format(quadraticDetails.sqrtDelta);
        }
        else
        {
            resultToShow = "Vazio";
            delta = "Vazio";
            powerOfB = "Vazio";
            mult_4ac = "Vazio";
            sqrt_delta = "Vazio";
            result.setText(resultToShow);
        }

    }
    //deital dialog stuff
    String resultToShow ="Vazio";
    String delta = "Vazio";
    String powerOfB = "Vazio";
    String mult_4ac = "Vazio";
    String sqrt_delta = "Vazio";
    TextView dialog_results;
    TextView dialog_delta;
    TextView dialog_bPower;
    TextView dialog_4ac;
    TextView dialog_sqrtDelta;
    TextView exitDialog;
    public void showDetails(View view) {
        detailsDialog.setContentView(R.layout.dialog_quadraticdetails);
        dialog_results = (TextView) detailsDialog.findViewById(R.id.id_quadratic_dialog_results);
        dialog_delta = (TextView) detailsDialog.findViewById(R.id.id_quadratic_dialog_delta);
        dialog_bPower = (TextView) detailsDialog.findViewById(R.id.id__dialog_b_b_result);
        dialog_4ac = (TextView) detailsDialog.findViewById(R.id.id__dialog_4ac_result);
        dialog_sqrtDelta = (TextView) detailsDialog.findViewById(R.id.id_dialog_sqrtOfDelta);
        dialog_results.setText(resultToShow);
        dialog_delta.setText(delta);
        dialog_bPower.setText(powerOfB);
        dialog_4ac.setText(mult_4ac);
        dialog_sqrtDelta.setText(sqrt_delta);


      exitDialog = (TextView) findViewById(R.id.id_exitDialog);
        detailsDialog.show();
    }

    public void exitDialogClick(View view) {
        detailsDialog.dismiss();
    }

    private class spinnerTopListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String itemSelected = parent.getItemAtPosition(position).toString();
            if (itemSelected.equals("Determinantes")) {
              startActivity(iDeterminante);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
