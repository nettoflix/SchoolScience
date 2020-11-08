package sem.allscience.Matematica;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sem.allscience.R;
public class MatrixDimensionSelector extends AppCompatActivity {
    Intent iMatrixMultiplication;
    int row_A;
    int col_A;
    int row_B;
    int col_B;
    String operation;
    Button btnPronto;
    Spinner spnRow_A;
    Spinner spnCol_A;
    Spinner spnRow_B;
    Spinner spnCol_B;
   Spinner spnOperation;
    String sDimensions[];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_dimensionselector);
        iMatrixMultiplication = new Intent(this,MatrizMultiplicationActivity.class);
        sDimensions = new String[100];
      //  int row_A = Integer.parseInt(spinnerRow_A.getSelectedItem().toString());
       // int col_A = Integer.parseInt(spinnerCol_A.getSelectedItem().toString());
        for(int i = 0;  i< sDimensions.length; ++i)
        {
            if(i==0)
            {
                sDimensions[0] = "1";
            }
            else
            {
                sDimensions[i] = String.valueOf(i);
            }
        }
        //spinner A dimensions
        //Row
        List<CharSequence> listRow_A =
                new ArrayList<CharSequence>(Arrays.asList(sDimensions));
        ArrayAdapter adapter= new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                listRow_A);
        spnRow_A = (Spinner) findViewById(R.id.selector_dimension_Row_A);
        spnRow_A.setAdapter(adapter);
        spnRow_A.setOnItemSelectedListener(new spinnerRow_A_listener());

        //Col
        spnCol_A = (Spinner) findViewById(R.id.selector_dimension_Col_A);
        spnCol_A.setAdapter(adapter);
        spnCol_A.setOnItemSelectedListener(new spinnerCol_A_listener());
        //spinner B dimensions
        //Row
        spnRow_B = (Spinner) findViewById(R.id.selector_dimension_Row_B);
        spnRow_B.setAdapter(adapter);
        spnRow_B.setOnItemSelectedListener(new spinnerRow_B_listener());
        //Col
        spnCol_B = (Spinner) findViewById(R.id.selector_dimension_Col_B);
        spnCol_B.setAdapter(adapter);
        spnCol_B.setOnItemSelectedListener(new spinnerCol_B_listener());
        //Operation
        spnOperation = (Spinner) findViewById(R.id.selector_matrix_operator);
        spnOperation.setOnItemSelectedListener(new spinnerOperation_listener());
        String operators[] = {"*","+","-"};
        List<CharSequence> operators_list = new ArrayList<CharSequence>(Arrays.asList(operators));
        ArrayAdapter adapter_operator= new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                operators_list);
       spnOperation.setAdapter(adapter_operator);
       spnOperation.setSelection(0);
        operation = spnOperation.getSelectedItem().toString();
        //BUTTON TO SET THE DIMENSIONS
        btnPronto = (Button)findViewById(R.id.dimendions_pronto);

    }

    public void DimensionsSet(View view) {
        row_A = Integer.parseInt(spnRow_A.getSelectedItem().toString());
        col_A = Integer.parseInt(spnCol_A.getSelectedItem().toString());
        row_B = Integer.parseInt(spnRow_B.getSelectedItem().toString());
        col_B = Integer.parseInt(spnCol_B.getSelectedItem().toString());
        operation = spnOperation.getSelectedItem().toString();
        Log.d("row_A in dimensions", spnRow_A.getSelectedItem().toString());
        Log.d("col_A in dimensions", spnCol_A.getSelectedItem().toString());
        Log.d("row_B in dimensions", spnRow_B.getSelectedItem().toString());
        Log.d("col_B in dimensions", spnCol_B.getSelectedItem().toString());
        iMatrixMultiplication.putExtra("row_A", row_A );
        iMatrixMultiplication.putExtra("col_A", col_A );
        iMatrixMultiplication.putExtra("row_B", row_B );
        iMatrixMultiplication.putExtra("col_B", col_B );
        iMatrixMultiplication.putExtra("operation", operation);
        TextView warning = findViewById(R.id.dimensions_warning);
        if(operation.equals("*"))
        {
            if(col_A == row_B)
            {
                startActivity(iMatrixMultiplication);
            }
            else
            {
                warning.setVisibility(View.VISIBLE);
                warning.setText("Para que seja possível multiplicar duas matrizes, o número de linhas da A deve ser o mesmo de colunas da B");
            }

        }
        if(operation.equals("+") || operation.equals("-"))
        {

            if(row_A == row_B && col_A == col_B)// matrices are of equal size
            {
                startActivity(iMatrixMultiplication);
            }
            else
            {
                warning.setVisibility(View.VISIBLE);
                warning.setText("Só é possível somar ou diminuir matrizes com as mesmas dimensões");
            }
        }



    }
class  spinnerOperation_listener implements  AdapterView.OnItemSelectedListener
{

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        operation = adapterView.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
    class spinnerCol_B_listener implements AdapterView.OnItemSelectedListener
    {

        @SuppressLint("ShowToast")
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String numberSelected = adapterView.getSelectedItem().toString();
            col_B = Integer.parseInt(numberSelected);
            //to sum two matrices, they must have the same dimensions
            if(operation.equals("+"))
            {
                spnCol_A.setSelection(col_B);
            }

            //Toast toast= Toast.makeText(getApplicationContext(), numberSelected, Toast.LENGTH_SHORT);
            //toast.show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    class spinnerCol_A_listener implements AdapterView.OnItemSelectedListener
    {

        @SuppressLint("ShowToast")
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String numberSelected = adapterView.getSelectedItem().toString();
                col_A = Integer.parseInt(numberSelected);

                //The main condition of matrix multiplication is that the number of columns of the 1st matrix must equal to the number of rows of the 2nd one.
            if(operation.equals("*"))
            {
                spnRow_B.setSelection(col_A);
            }
            //to sum two matrices, they must have the same dimensions
            else if(operation.equals("+"))
            {
                spnCol_B.setSelection(col_A);
            }

                //Toast toast= Toast.makeText(getApplicationContext(), numberSelected, Toast.LENGTH_SHORT);
                //toast.show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    class spinnerRow_A_listener implements AdapterView.OnItemSelectedListener
    {

        @SuppressLint("ShowToast")
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String numberSelected = adapterView.getSelectedItem().toString();
            row_A = Integer.parseInt(numberSelected);
            //to sum two matrices, they must have the same dimensions
            if(operation.equals("+"))
            {
                spnRow_B.setSelection(row_A);
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    class spinnerRow_B_listener implements AdapterView.OnItemSelectedListener
    {

        @SuppressLint("ShowToast")
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String numberSelected = adapterView.getSelectedItem().toString();
            row_B = Integer.parseInt(numberSelected);


            //The main condition of matrix multiplication is that the number of columns of the 1st matrix must equal to the number of rows of the 2nd one.
            if(operation.equals("*"))
            {
                spnCol_A.setSelection(row_B);
            }
            //to sum two matrices, they must have the same dimensions
            else if(operation.equals("+"))
            {
                spnRow_A.setSelection(row_B);
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}
