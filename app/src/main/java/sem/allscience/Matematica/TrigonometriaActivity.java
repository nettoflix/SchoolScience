package sem.allscience.Matematica;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import sem.allscience.R;

public class TrigonometriaActivity extends AppCompatActivity {
    trigoGraphView graph;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trigo);
       graph = findViewById(R.id.trigoGraph);
       graph.setOnTouchListener(graph);
       TextView header = findViewById(R.id.trigoHeader);
        header.setPaintFlags(header.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
    }
}
