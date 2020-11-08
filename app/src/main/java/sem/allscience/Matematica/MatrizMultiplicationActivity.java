package sem.allscience.Matematica;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import sem.allscience.Manager.DesignByCode;
import sem.allscience.Manager.Handler;
import sem.allscience.Manager.MyMath;
import sem.allscience.R;

public class MatrizMultiplicationActivity extends AppCompatActivity {
   public enum matrixType{
       Edit_Texts,
       Text_Views,
    }
    String operation;
    int row_A;
    int col_A;
    int row_B;
    int col_B;
    LayoutParams layoutParams;
    EditText matrix_A[][];
    LinearLayout firstCol_mA;
    LinearLayout firstRow_mA;
    LinearLayout verticaHolder_mA;

    EditText matrix_B[][];
    LinearLayout firstCol_mB;
    LinearLayout firstRow_mB;
    LinearLayout verticaHolder_mB;
    Dialog resultMatrix_dialog;
    //views attributes
    int square_width;
    int square_height;
    int halfSquare;
    int textSize;
    TextView warningInput;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matriz_mult);

        Intent intent = getIntent();
        resultMatrix_dialog = new Dialog(this);
        operation = intent.getStringExtra("operation");
        //mA
        row_A = intent.getIntExtra("row_A",0);
        col_A = intent.getIntExtra("col_A", 0);
        matrix_A = new EditText[row_A][col_A];
        verticaHolder_mA = (LinearLayout) findViewById(R.id.verticalHolder_mA);
        firstCol_mA = (LinearLayout) findViewById(R.id.rowNumbersLayout_mA);
        firstRow_mA = (LinearLayout) findViewById(R.id.colNumbersLayout_mA);
        layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        initGrid(matrixType.Edit_Texts,matrix_A, row_A, col_A, firstRow_mA, firstCol_mA, verticaHolder_mA);
       //mB
        row_B = intent.getIntExtra("row_B",0);
        col_B = intent.getIntExtra("col_B", 0);
        matrix_B = new EditText[row_B][col_B];
        verticaHolder_mB = (LinearLayout) findViewById(R.id.verticalHolder_mB);
        firstCol_mB = (LinearLayout) findViewById(R.id.rowNumbersLayout_mB);
        firstRow_mB = (LinearLayout) findViewById(R.id.colNumbersLayout_mB);
        initGrid(matrixType.Edit_Texts,matrix_B,row_B, col_B, firstRow_mB, firstCol_mB, verticaHolder_mB);
        //warning, error textView
        warningInput = findViewById(R.id.matrix_inputWarningView);
    }
@SuppressLint("ResourceAsColor")
public Grid initGrid(matrixType type,View matrix[][] ,int rows, int coluns, LinearLayout firstRow, LinearLayout firstColun, LinearLayout verticalHolder)
{

    square_width = (int) MyMath.convertDpToPixels(45,this);
    square_height = (int) MyMath.convertDpToPixels(45,this);
    halfSquare = square_width/2;
    textSize = (int) MyMath.converterSpToPixels(10,this);
//first row
    CardView fr[] = new CardView[coluns+1];
    {
        for (int tc = 0; tc <= coluns; ++tc) {
            TextView textView = new TextView(this);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(textSize);
            textView.setText(String.valueOf(tc));
            int textWidth = (int) MyMath.converterSpToPixels(DesignByCode.getTextBounds(textView).width(),this);
            int textHeight = (int) MyMath.converterSpToPixels(DesignByCode.getFontHeight(textSize),this);
            DesignByCode. setMarginTop(textView,(halfSquare) - (textHeight/2)-5);
            DesignByCode.setMarginLeft(textView, (halfSquare) - (textWidth/2)-5);
            // setMarginLeft(textView, MyMath.convertDpToPixels(20,this));
            if (tc == 0) {
                textView.setText(null);
            }

            CardView cardview = new CardView(this);

            DesignByCode.customView(cardview,Color.BLUE, Color.BLACK);
            DesignByCode.setCardSize(cardview,square_width,square_height);
            cardview.addView(textView);
            cardview.setRadius(0);
            fr[tc] = cardview;
           firstRow.addView(cardview);
        }
    }
    //first colun
    CardView fc[] = new CardView[rows+1];
    for(int tr=1; tr<=rows; ++tr)
    {
        TextView textView = new TextView(this);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(textSize);
        textView.setText("  "+String.valueOf(tr));
        textView.setText(String.valueOf(tr));
        int textWidth = (int)  DesignByCode.getTextBounds(textView).width();
        int textHeight = (int) MyMath.converterSpToPixels( DesignByCode.getFontHeight(textSize),this);
        DesignByCode.setMarginTop(textView,(halfSquare) - (textHeight/2)-5);
        DesignByCode.setMarginLeft(textView, (halfSquare) - (textWidth/2)-5);

        //setMarginLeft(textView, halfSquare);
        //rowColNumbers.add(textView);
        CardView cardview = new CardView(this);
        cardview.setRadius(0);
        DesignByCode.setCardSize(cardview,square_width,square_height);
        cardview.addView(textView);
        DesignByCode.customView(cardview,Color.BLUE, Color.BLACK);
        firstColun.addView(cardview);
        fc[tr] = cardview;
    }
    if (type==matrixType.Edit_Texts) {
    for(int r=0; r<rows; ++r) {
        LinearLayout hLayout = new LinearLayout(this);
        hLayout.setLayoutParams(layoutParams);
        hLayout.setOrientation(LinearLayout.HORIZONTAL);

            for (int c = 0; c < coluns; ++c) {
                CardView cardview = new CardView(this);

                EditText et = new EditText(this);
                et.setTextSize(MyMath.converterSpToPixels(12, this));
                //et.setText("coe");
                cardview.addView(et);
                cardview.setRadius(0);
                DesignByCode.setCardSize(cardview, square_width, square_height);
                DesignByCode.customView(et, Color.WHITE, Color.BLACK);
                DesignByCode.customView(cardview, Color.BLUE, Color.BLACK);
                matrix[r][c] = et;
                hLayout.addView(cardview);
            }
            verticalHolder.addView(hLayout);
        }
    }
    else if(type==matrixType.Text_Views)
    {
        CardView.LayoutParams card_layoutParams = (new CardView.LayoutParams(
                CardView.LayoutParams.WRAP_CONTENT, CardView.LayoutParams.WRAP_CONTENT));
        for(int r=0; r<rows; ++r) {
            LinearLayout hLayout = new LinearLayout(this);
            hLayout.setLayoutParams(layoutParams);
            hLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int c = 0; c < coluns; ++c) {
                CardView cardview = new CardView(this);
                cardview.setLayoutParams(card_layoutParams);
                TextView textView = new TextView(this);
                textView .setTextSize(MyMath.converterSpToPixels(12, this));
                matrix[r][c] = textView;
                cardview.addView(textView);
                cardview.setRadius(0);
                //DesignByCode.setCardSize(cardview, square_width, square_height);
                DesignByCode.customView(textView, Color.WHITE, Color.BLACK);
                DesignByCode.customView(cardview, Color.BLUE, Color.BLACK);
                hLayout.addView(cardview);
            }
            verticalHolder.addView(hLayout);
        }
    }

    Grid grid = new Grid(fr, fc, matrix);
    return grid;
}

    public void Calcular(View view) {
        boolean incompatibleInput=false;
        int int_mA[][] = new int[row_A][col_A];
        int int_mB[][]= new int[row_B][col_B];
        //grab int value inserted by the user in the editTexts of the matrices
        for(int r1=0; r1<row_A; ++r1)
        {
            for (int c1=0; c1<col_A; ++c1)
            {
                String element = matrix_A[r1][c1].getText().toString();
                if(Handler.isIntParsable(element)) {
                    int_mA[r1][c1] = Integer.parseInt(element);
                }
                else
                {
                    incompatibleInput = true;
                    break;
                }
            }
        }
        for(int r2=0; r2<row_B; ++r2)
        {
            for (int c2=0; c2<col_B; ++c2)
            {
                String element = matrix_B[r2][c2].getText().toString();
                if(Handler.isIntParsable(element))
                {
                    int_mB[r2][c2] = Integer.parseInt(element);
                }
                else
                {
                    incompatibleInput=true;
                    break;
                }
            }
        }

        if(!incompatibleInput)
        {
            warningInput.setVisibility(View.GONE);
       //switch operation
            int resultMatrix[][]=null;
            if(operation.equals("*"))
            {
                resultMatrix = MyMath.multiplyMatrix(int_mA,int_mB);
            }
            else if(operation.equals("+"))
            {
                resultMatrix = MyMath.sumMatrix(int_mA, int_mB);
            }
            else if(operation.equals("-"))
            {
                resultMatrix = MyMath.subtractMatrix(int_mA,int_mB);
            }
            initDialog(resultMatrix);
        }
        else
        {
            warningInput.setVisibility(View.VISIBLE);
        }
    }

    public void initDialog(int resultMatrix[][])
    {
        int rows = resultMatrix.length;
        int coluns = resultMatrix[0].length;
        TextView resultViews[][] = new TextView[rows][coluns];
        resultMatrix_dialog.setContentView(R.layout.dialog_result_matrix);
        LinearLayout dialog_firstCol = (LinearLayout) resultMatrix_dialog.findViewById(R.id.resultMatrix_firstColun);
        LinearLayout dialog_firstRow = (LinearLayout) resultMatrix_dialog.findViewById(R.id.resultMatrix_firstRow);
        LinearLayout dialog_viewHolder = (LinearLayout) resultMatrix_dialog.findViewById(R.id.resultMatrix_viewHolder);
        Grid grid = initGrid(matrixType.Text_Views,resultViews,rows,coluns,dialog_firstRow, dialog_firstCol, dialog_viewHolder);
        TextView[][] textViews = (TextView [][]) grid.views;
        int biggestWidth=0;
        for(int vr =0; vr<rows; ++vr)
        {
        for (int vc=0; vc<coluns; ++vc)
        {
            String text =" " + String.valueOf(resultMatrix[vr][vc]);
            textViews[vr][vc].setText(text);
            textViews[vr][vc].setTextColor(Color.BLACK);
            textViews[vr][vc].setTextSize(textSize);
            int textWidth = (int) MyMath.convertDpToPixels(DesignByCode.getTextBounds(textViews[vr][vc]).width()*2,this);
            textViews[vr][vc].setHeight(square_height);
            if(textWidth>biggestWidth) {
                biggestWidth = textWidth;
            }
        }
        }
        if(biggestWidth<square_width) biggestWidth = square_width;

        for(int r=0; r<rows; ++r) {
            for (int c = 0; c < coluns; ++c) {
                    textViews[r][c].setWidth(biggestWidth);
            }
        }
        //stretch also the first row which doesn't belong to resultMatrix array
        for(int c=0; c<grid.firstRow.length; ++c)
        {

            if(c!=0)  //don't stretch the first square, because iT should actually be part of the first colun
            DesignByCode.setCardSize(grid.firstRow[c], biggestWidth, square_height);
        }
        resultMatrix_dialog.show();
    }

}
