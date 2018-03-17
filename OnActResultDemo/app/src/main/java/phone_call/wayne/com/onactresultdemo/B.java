package phone_call.wayne.com.onactresultdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Wayne on 2018/3/17.
 */
public class B extends AppCompatActivity {
    private Intent intent;
    private Button button2;
    private TextView textB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        intent = getIntent();
        String result = intent.getStringExtra("ToB");

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(B.this,MainActivity.class);
                String passString = "Hello,this is B";
                intent.putExtra("FromB",passString);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        TextView textB = (TextView)findViewById(R.id.textB);
        textB.setText(result);
    }
}
