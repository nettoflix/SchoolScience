package sem.allscience.Matematica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import sem.allscience.R;


public class MenuActivity extends AppCompatActivity {
    //Intents
    Intent iMatrizDimensionSelector;
    Intent iDeterminantes;
    Intent iSohcahtoa;
    Intent iGCD;
    Intent iBhaskara;
    //Trigonometria
    boolean trigoListVisible;
    TextView enterSohcahtoa;
    TextView showTrigo;
    //AlgebraLinear
    boolean algebraLinearListVisible;
    TextView enterDt;
    TextView enterMatrizMult;
    //Formulas
    boolean formulasListVisible;
    TextView enterBhaskara;
    TextView enterGCD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_math);
        //init intents
        iSohcahtoa = new Intent(this,TrigonometriaActivity.class);
        iDeterminantes = new Intent(this,DeterminanteActivity.class);
        iGCD = new Intent(this,GCDActivity.class);
        iBhaskara =new Intent(this,BhaskaraActivity.class);
        iMatrizDimensionSelector = new Intent(this, MatrixDimensionSelector.class);
        initFormulasViews();
        initTrigoViews();
        initAlgebraLinearViews();


    }

    private void initFormulasViews() {
        enterBhaskara = (TextView)findViewById(R.id.id_enterBhaskara);
        enterGCD = (TextView)findViewById(R.id.id_enterGCD);
        hideView(enterBhaskara, true);
        hideView(enterGCD, true);
    }

    private void initAlgebraLinearViews() {
        algebraLinearListVisible = false;
        enterDt = (TextView) findViewById(R.id.id_enter_determinantes);
        enterMatrizMult = (TextView)findViewById(R.id.id_enter_matrizMultiplication);
        hideView(enterMatrizMult, true);
        hideView(enterDt, true);
    }

    //initializers
    public void initTrigoViews()
    {
        trigoListVisible = false;
        //showTrigo = (TextView) findViewById(R.id.id_listTrigo);
        enterSohcahtoa = (TextView) findViewById(R.id.id_sohcahtoa);
        hideView(enterSohcahtoa, true);
    }
    //functions OnClick
    public void onEnterSohcahtoa(View view) {

        startActivity(iSohcahtoa);
    }
    public void enterMatrizMultiplication(View view) {startActivity((iMatrizDimensionSelector));}
    public void enterDeterminantes(View view) {
        startActivity(iDeterminantes);
    }
    public void onEnterBhaskara(View view) {
        startActivity(iBhaskara);
    }

    public void onEnterGCD(View view) {
        startActivity(iGCD);
    }

    //functions

    public void showTrigoList(View view)
    {
        if (trigoListVisible)
        {
            hideView(enterSohcahtoa,true);
            trigoListVisible = !trigoListVisible;
        }
        else
        {
            hideView(enterSohcahtoa,false);
            trigoListVisible = !trigoListVisible;
        }

    }
    public void showAlgebraLinearList(View view) {
        if(algebraLinearListVisible)
        {
            hideView(enterDt, true);
            hideView(enterMatrizMult, true);
            algebraLinearListVisible = !algebraLinearListVisible;
        }
        else{
            hideView(enterMatrizMult, false);
            hideView(enterDt, false);
            algebraLinearListVisible = !algebraLinearListVisible;
        }
    }
    public void showFormulasList(View view) {
        if (formulasListVisible)
        {
            hideView(enterBhaskara,true);
            hideView(enterGCD,true);
            formulasListVisible = !formulasListVisible;
        }
        else
        {
            hideView(enterBhaskara,false);
            hideView(enterGCD,false);
            formulasListVisible = !formulasListVisible;
        }
    }


    public void hideView(View v,boolean b)
    {
        if(b){
            v.setVisibility(View.GONE);
        }
        else
        {
            v.setVisibility(View.VISIBLE);
        }


    }


}
