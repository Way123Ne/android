package phone_call.wayne.com.onactresultdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button buttonA;
    private TextView textA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartA();
    }

    public void StartA() {
        Button buttonA = (Button) findViewById(R.id.button1);
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, B.class);
                String passString = "Hello,this is A";
                intent.putExtra("ToB", passString);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {//resultCode为回传得标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                Bundle b = data.getExtras();//data为B中回传的Intent
                String str = b.getString("FromB");//str即为回传的值
                TextView textA = (TextView) findViewById(R.id.textA);
                textA.setText(str);
                break;
            default:
                break;
        }
    }

    
}

