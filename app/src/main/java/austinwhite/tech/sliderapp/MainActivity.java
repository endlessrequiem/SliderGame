package austinwhite.tech.sliderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Button;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView numdisplay;
    TextView numguess;
    TextView result;
    Button mRefresh;
    Random mysterynum = new Random();
    int numrange = 100;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        numdisplay = findViewById(R.id.numberdisplaydebug); //number from slider, for debugging
        numguess = findViewById(R.id.slidenumber); //number to guess
        seekBar = findViewById(R.id.seekBar); //the slider itself
        mRefresh = findViewById(R.id.refresh); //new game

        final int randnumber = mysterynum.nextInt(numrange);
        String randnumstring = String.valueOf(randnumber);
        numguess.setText(getString(R.string.guessthenumber) + " " + randnumstring); //this is the number we have to match with


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                //String number = String.valueOf(progress);
                //numdisplay.setText(number);
                //for debug purposes
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),getString(R.string.gamestart), Toast.LENGTH_SHORT).show(); //just shows that the game has started
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBar.setEnabled(false);
                if (seekBar.getProgress() == randnumber) {
                    result.setText(getString(R.string.youwin));
                } else {
                    result.setText(getString(R.string.youlose) + " " + getString(R.string.numberwas) + " " + seekBar.getProgress());
                }
            }
        });

        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });

    }
}