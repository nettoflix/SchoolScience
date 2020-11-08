package sem.allscience.Matematica;

import android.view.View;

import androidx.cardview.widget.CardView;

public class Grid {
    CardView[] firstRow;
    CardView[] firstColun;
    View[][]views;
    public Grid(CardView[] fr, CardView[] fc, View[][] v)
    {
        this.firstRow = fr;
        this.firstColun = fc;
        this.views = v;
    }
}
