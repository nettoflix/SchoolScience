package sem.allscience.Matematica;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;

import sem.allscience.R;

public class trigoGraphView extends View implements View.OnTouchListener {



    public trigoGraphView(Context context) {
        super(context);
    }
    int generalAngle;
    Paint hipoPaint;
    Paint adjaPaint;
    Paint opoPaint;
    Paint linePaint;
    Paint arcPaint;
    Paint circlePaint;
    int radius;
    Point hipo1;
    Point hipo2;
    Point adja1;
    Point adja2;
    Point opo1;
    Point opo2;
    String sinText;
    String cosText;
    String tanText;
    TextPaint numberCosPaint;
    TextPaint numberSinPaint;
    TextPaint numberTanPaint;
    float fontSize;
    DecimalFormat numberFormat;
    Paint.FontMetrics fontMetrics;
    public trigoGraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        numberFormat = new DecimalFormat("#0.00");
        int width = getWidth();
        int height = getHeight();
        int strokeWidth = 3;
        hipoPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hipoPaint.setColor(Color.BLACK);
        hipoPaint.setStyle(Paint.Style.STROKE);
        hipoPaint.setStrokeWidth(strokeWidth);
        adjaPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        adjaPaint.setColor(Color.GREEN);
        adjaPaint.setStyle(Paint.Style.STROKE);
        adjaPaint.setStrokeWidth(strokeWidth);
        opoPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        opoPaint.setColor(Color.BLUE);
        opoPaint.setStyle(Paint.Style.STROKE);
        opoPaint.setStrokeWidth(strokeWidth);
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setColor(Color.GRAY);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(strokeWidth);
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(ContextCompat.getColor(context, R.color.vermelho));
        linePaint.setStrokeWidth(strokeWidth);

        hipo1 = new Point();
        hipo2 = new Point();
        adja1 = new Point();
        adja2 = new Point();
        opo1 = new Point();
        opo2 = new Point();
        radius = Math.round((getWidth()/2) * getResources().getDisplayMetrics().density);
        numberCosPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        numberCosPaint.setColor(ContextCompat.getColor(context, R.color.verde));
        // Set the number text size.
        // Translate it
        fontSize = 16f;
        numberCosPaint.setTextSize(Math.round(fontSize * getResources().getDisplayMetrics().scaledDensity));
        numberSinPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        numberSinPaint.setColor(ContextCompat.getColor(context, R.color.azulForte));
        numberSinPaint.setTextSize(Math.round(fontSize * getResources().getDisplayMetrics().scaledDensity));
        numberTanPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        numberTanPaint.setColor(ContextCompat.getColor(context, R.color.preto));
        numberTanPaint.setTextSize(Math.round(fontSize * getResources().getDisplayMetrics().scaledDensity));
        fontMetrics = numberCosPaint.getFontMetrics();
        generalAngle = 90;


    }
    public void drawCustomArc(float offsetX, float offsetY, double angle, float radius,Canvas canvas)
    {
        //double radians = Math.toRadians(angle);
        for(double i=0; i<angle; ++i)
        {
            double radians = Math.toRadians(i);
            int x = (int) (Math.cos(radians) * radius);
            int y  = -(int) (Math.sin(radians) * radius);
            canvas.drawPoint(offsetX+x,offsetY+y, arcPaint);
        }

    }
    public void drawAngleTriangle(float x, float y, int angle, double radius, Canvas canvas)
    {
        double radians = Math.toRadians(angle);
        double cos = (Math.cos(radians));
        double sin = (Math.sin(radians));
        double tan = (Math.tan(radians));

        //HIPOTENUSA
        hipo1.x = 0;
        hipo1.y = 0;
        hipo2.x = (int) (cos*radius);
        hipo2.y = -(int) (sin*radius);
        canvas.drawLine( x+ hipo1.x,  y+ hipo1.y, x+hipo2.x, y+hipo2.y,hipoPaint);
        //ADJACENT
        adja1.x = 0;
        adja1.y = 0;
        adja2.x = hipo2.x;
        adja2.y = 0;
        canvas.drawLine( x+ adja1.x,  y+ adja1.y, x+adja2.x, y+adja2.y,adjaPaint);
        //OPO
        opo1.x = hipo2.x;
        opo1.y = 0;
        opo2.x = hipo2.x;
        opo2.y = hipo2.y;
        canvas.drawLine( x+ opo1.x,  y+ opo1.y, x+opo2.x, y+opo2.y,opoPaint);
        //Text to display
        cosText = String.valueOf(numberFormat.format(cos));
        sinText = String.valueOf(numberFormat.format(sin));
        tanText = String.valueOf(numberFormat.format(tan));

        float textWidth = numberCosPaint.measureText(cosText);
        //half the length of the adjacent line
        float cosTextX = (x + adja1.x) + ((adja2.x-adja1.x)/2 - (textWidth/2));
        float cosTextY = (y+adja1.y) - (0.5f* fontSize);
        canvas.drawText(cosText,cosTextX, cosTextY, numberCosPaint);
        textWidth = numberCosPaint.measureText(sinText);
        //half the length of the adjacent line
        float sinTextX = (x + opo1.x) - 18;
        float sinTextY = (y+hipo2.y) + ((opo1.y - hipo2.y )/2);
        canvas.drawText(sinText,sinTextX, sinTextY, numberSinPaint);



    }
    @SuppressLint("DrawAllocation")
    float centerX;
    float bigCircleRadius;
    float centerCircleY;
    @Override
    public void onDraw(Canvas canvas)
    {
        final float maxTextHeight = -fontMetrics.top + fontMetrics.bottom;
        // Grab canvas dimensions.

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int arcRadius = canvasWidth / 6;
        // Calculate horizontal center.
        centerX = canvasWidth * 0.5f;
        final float centerY = canvasHeight * 0.5f;
        bigCircleRadius = canvasWidth/2 - 20;
        centerCircleY = bigCircleRadius + 10 ;
        //draw big circle
        canvas.drawCircle(centerX, centerCircleY, bigCircleRadius, linePaint);
        //the 2 perpendicular lines who divides the circle in 4 parts
        canvas.drawLine(centerX - bigCircleRadius, centerCircleY,centerX + bigCircleRadius , centerCircleY,linePaint);
        canvas.drawLine(centerX, centerCircleY - bigCircleRadius, centerX, centerCircleY+bigCircleRadius,linePaint);
        if(generalAngle!=90.0)
        {
            drawCustomArc(centerX, centerCircleY, generalAngle, arcRadius, canvas);
        }  else {
            drawStraightAngle(canvas,centerX,centerCircleY,arcRadius);
        }
        drawAngleTriangle(centerX,centerCircleY,generalAngle ,bigCircleRadius, canvas);
        drawSubtitles(canvas,centerX, centerCircleY + bigCircleRadius+ maxTextHeight, (int) generalAngle, maxTextHeight);


    }
    public void drawStraightAngle(Canvas canvas, float x, float y, float length)
    {
        canvas.drawLine(x+length, y, x+length, y-length, arcPaint);
        canvas.drawLine(x, y-length, x+length, y-length, arcPaint);
    }
    public void drawSubtitles(Canvas canvas, float x, float y, int angle, float maxTextHeight)
    {
        String angleString = "(" + String.valueOf(angle)+ "º" + ") =";
        //cos
        String cosString = "cos"+angleString+cosText;
        float xCos;
        xCos = x - numberCosPaint.measureText(cosString)/2;
        canvas.drawText(cosString, xCos, y, numberCosPaint);
        //sin
        String sinString = "sin"+angleString+sinText;
        float ySin = y+maxTextHeight+10;
        canvas.drawText(sinString, xCos, ySin , numberSinPaint);
        //tan
        String tanString = "tan"+angleString+tanText;
        float yTan = ySin + maxTextHeight + 10;
        canvas.drawText(tanString, xCos, yTan, numberTanPaint);
    }
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // final Paint.FontMetrics fontMetrics = numberPaint.getFontMetrics();

        // Measure maximum possible width of text.
        //  final float maxTextWidth = numberPaint.measureText(MAX_COUNT_STRING);
        // Estimate maximum possible height of text.
        // final float maxTextHeight = -fontMetrics.top + fontMetrics.bottom;

        // Add padding to maximum width calculation.
        final int desiredWidth = convertDpToPixels(200, getContext()); //Math.round(maxTextWidth + getPaddingLeft() + getPaddingRight());

        // Add padding to maximum height calculation.
        final int desiredHeight = convertDpToPixels(280, getContext()); //Math.round(maxTextHeight * 2f + getPaddingTop()  + getPaddingBottom());

        // Reconcile size that this view wants to be with the size the parent will let it be.
        final int measuredWidth = resolveSize(desiredWidth, widthMeasureSpec);
        final int measuredHeight = resolveSize(desiredHeight, heightMeasureSpec);

        // Store the final measured dimensions.
        setMeasuredDimension(measuredWidth, measuredHeight);
    }
    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
    public static int convertDpToPixels(int dp, Context context){
        return dp * ((int) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
    private double  previousX;
    private double previousY;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int eventAction = event.getAction();
        // the x/y location
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                //  generalAngle = resolveAngle(x,y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                generalAngle = resolveAngle(x,y);
                break;
        }
        // tell the View to redraw the Canvas
        invalidate();

        // tell the View that we handled the event
        return true;
    }

    private int resolveAngle(double x, double y) {
        //offSet coordinates of the view to be relative to the big circle
        double resolvedX = x - (centerX - bigCircleRadius);
        double resolvedY = y - (centerCircleY - bigCircleRadius);
        //distance between the point where it was pressed to the center of the circle
        resolvedX = (resolvedX - centerX);
        resolvedY = -(resolvedY - centerCircleY);
        double hipot = Math.sqrt(Math.pow(resolvedX,2) + Math.pow(resolvedY,2));
        double sinThroughY = resolvedY / hipot;
        double cosThroughX = resolvedX / hipot;
        double radianAngle = Math.atan2(sinThroughY,cosThroughX);
        int degreeAngle = (int) Math.toDegrees(radianAngle);
        //if the angle is inverted to the negative quadrants, convert it to 180//360 degrees
        if(degreeAngle<0)
        {
            degreeAngle = 180+ (180-Math.abs(degreeAngle));
        }
        return degreeAngle;
    }




    public boolean isInsideCircle(int x, int y)
    {
        //Log.d()
        double differenceX = Math.abs(x-centerX);
        double differenceY= Math.abs(y-centerCircleY);
        double pitágorasHipotenusa = Math.sqrt( Math.pow( differenceX, 2) +  Math.pow(differenceY, 2));
        if(pitágorasHipotenusa <= bigCircleRadius)
        {
            // Log.d("BigCircleRadius  ", String.valueOf(bigCircleRadius));
            //  Log.d("Se encontra dentro do circulo, distância:  ", String.valueOf(pitágorasHipotenusa));
            return true;
        }
        //  Log.d("Não se encontra dentro do circulo, distância:  ", String.valueOf(pitágorasHipotenusa));
        return false;
    }
}
