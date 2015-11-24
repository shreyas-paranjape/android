package in.co.foodamigo.supplier.module;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import in.co.foodamigo.supplier.R;

/**
 * Created by gts on 20/11/15.
 */
public class DisplayActivity extends Activity{
TextView textView4,textView5,textView6,textView7,textView8,textView9,textView10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        Bundle b = getIntent().getExtras();
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        TextView textView5 = (TextView) findViewById(R.id.textView5);
        TextView textView6 = (TextView) findViewById(R.id.textView6);
        TextView textView7 = (TextView) findViewById(R.id.textView7);
        TextView textView8 = (TextView) findViewById(R.id.textView8);
        TextView textView9 = (TextView) findViewById(R.id.textView9);
        TextView textView10 = (TextView) findViewById(R.id.textView10);

        textView4.setText(b.getCharSequence("customer number"));
        textView5.setText(b.getCharSequence("alternate number"));
        textView6.setText(b.getCharSequence("customer name"));
        textView7.setText(b.getCharSequence("add1"));
        textView8.setText(b.getCharSequence("add2"));
        textView9.setText(b.getCharSequence("amount"));
        textView10.setText(b.getCharSequence("pick up"));

    }

}
