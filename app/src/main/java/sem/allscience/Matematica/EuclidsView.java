package sem.allscience.Matematica;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;

import sem.allscience.Manager.MyMath;

public class EuclidsView extends View implements View.OnTouchListener {


    public EuclidsView(Context context) {
        super(context);
    }
    boolean heightIsBigger;

    Canvas gCanvas;
    int colors[];
    boolean changeColor;
    Paint outerPaint;
    Paint inPaint;
    int color;
    int rectWidth;
    int rectHeight;
    int  rectWidthDrawable;
    int  rectHeightDrawable;
    boolean drawEuclid;
    boolean tooLarge;
    Rect screenSize;
    DecimalFormat numberFormat;
    GCDActivity activity;
    public EuclidsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //this.activity = activity;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        screenSize = new Rect();
        display.getRectSize(screenSize); //it is fucked up
        drawEuclid= false;
        changeColor = false;
        numberFormat = new DecimalFormat("#0.000");


        outerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerPaint.setColor(Color.BLACK);
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setStrokeWidth(5);
        outerPaint.setTextSize(20);
        inPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        inPaint.setColor(Color.GREEN);


        color = 0;
        colors= new int[]{Color.BLUE, Color.GREEN, Color.RED, Color.GRAY, Color.YELLOW, Color.CYAN};
    }



    @SuppressLint("DrawAllocation")
    @Override
    public void onDraw(Canvas canvas) {
        //gCanvas = canvas;
        //  final float maxTextHeight = -fontMetrics.top + fontMetrics.bottom;
        // Grab canvas dimensions.

        if(drawEuclid){
            canvas.drawRect(0,0, rectWidthDrawable,rectHeightDrawable, outerPaint);
            gcd(rectHeightDrawable,rectWidthDrawable, canvas);
        }
        else if(tooLarge)
        {
            this.setVisibility(View.GONE);
            activity.euclidsWarning1.setText("As proporções do retângulo excedem os limites da tela, o algoritmo não será renderizado");
            activity.euclidsWarning1.setVisibility(View.VISIBLE);
        }

    }
    @SuppressLint("LongLogTag")
    public void setValues(int h, int w)
    {
        heightIsBigger = true;
        //if height is bigger then width, swap them, so the rect drawn is horizontal
        if(h<w)
        {
            heightIsBigger = false;
            int temp;
            temp=h;
            h=w;
            w=temp;
        }
        rectHeight = h;
        rectWidth = w;


        rectHeightDrawable = rectHeight;
        rectWidthDrawable= rectWidth;

        MyMath.Pair reducedFraction =  MyMath.reduceFraction(rectHeightDrawable, rectWidthDrawable);
        rectHeightDrawable = (int) reducedFraction.t;
        rectWidthDrawable = (int) reducedFraction.u;
        // if there are few pixels, scale it up it to look good
        if(heightIsBigger)
        {
            while(rectHeightDrawable < 300)
            {
                rectHeightDrawable*=3;
                rectWidthDrawable*=3;
            }
        }
        else
        {
            while(rectWidthDrawable < 200)
            {
                rectHeightDrawable*=3;
                rectWidthDrawable*=3;
            }
        }

        if(rectHeightDrawable<(0.60*screenSize.height()))
        {
            Log.d("euclids size is bigger than screen", String.valueOf(screenSize.height()));
            drawEuclid = true;
        }
        else
        {
            tooLarge = true;
            drawEuclid = false;
        }

        changeColor = true;
    }
    public void gcd(int a, int b, Canvas canvas)
    {
        int x=0;
        int y=0;
        int c=0;
        while(a!=0) {
            int intDivision = b / a;
            int squaresToFill = a * intDivision;
            for (int i = 0; i < intDivision; ++i)
            {
                if(c<colors.length)
                {
                    inPaint.setColor(colors[c]);
                }

                canvas.drawRect(x, y, x+a, y+a, outerPaint);
                canvas.drawRect(x, y, x+a, y+a, inPaint);
                if(x+a < rectWidthDrawable)
                {
                    x+=a;
                }
                if(y+a < rectHeightDrawable)
                {
                    y+=a;
                }

            }
            c++;
            int r = b%a;
            b=a;  a=r;
        }
        changeColor = false;
        //canvas.drawText(String.valueOf(b), 20,rectHeight10+80, outerPaint);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // final Paint.FontMetrics fontMetrics = numberPaint.getFontMetrics();

        // Measure maximum possible width of text.
        //  final float maxTextWidth = numberPaint.measureText(MAX_COUNT_STRING);
        // Estimate maximum possible height of text.
        // final float maxTextHeight = -fontMetrics.top + fontMetrics.bottom;

        // Add padding to maximum width calculation.
        final int desiredWidth = convertDpToPixels(rectWidthDrawable, getContext()); //Math.round(maxTextWidth + getPaddingLeft() + getPaddingRight());

        // Add padding to maximum height calculation.
        final int desiredHeight = convertDpToPixels(rectHeightDrawable, getContext()); //Math.round(maxTextHeight * 2f + getPaddingTop()  + getPaddingBottom());

        // Reconcile size that this view wants to be with the size the parent will let it be.
        final int measuredWidth = resolveSize(desiredWidth, widthMeasureSpec);
        final int measuredHeight = resolveSize(desiredHeight, heightMeasureSpec);

        // Store the final measured dimensions.
        setMeasuredDimension(measuredWidth, measuredHeight);
    }
    public static float convertPixelsToDp(float px, Context context) {
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
    public static int convertDpToPixels(int dp, Context context) {
        return dp * ((int) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
    private double previousX;
    private double previousY;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int eventAction = event.getAction();
        // the x/y location
        int x = (int) event.getX();
        int y = (int) event.getY();
        // put your code in here to handle the event
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                //  generalAngle = resolveAngle(x,y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        // tell the View to redraw the Canvas
        invalidate();
        return true;
    }
}


