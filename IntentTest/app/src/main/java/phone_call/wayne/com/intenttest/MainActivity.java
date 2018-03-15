package phone_call.wayne.com.intenttest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tv_intent;
    private Button btn_at;
    private Button btn_atU;
    private Button btn_toSec;
    private Button btn_myA;
    private Button btn_atM;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //打开一个数据视图，但是没有要求，系统会默认让你从多个中选择其中一个打开
    public void action(View view) {
        Intent it = new Intent(Intent.ACTION_VIEW);
        startActivity(it);
    }

    //打开一个数据视图，有数据要求，系统帮你打开一个浏览器，并连接到相关的页面
    public void actionUri(View v) {
        Intent it = new Intent(Intent.ACTION_VIEW);
        it.setData(Uri.parse("http://www.baidu.com"));
        startActivity(it);

    }

    //
    public void toSecondActivity(View v) {
        Intent it = new Intent(this, SecondActivity.class);
        startActivity(it);
    }

    //这里只用自己定义的Action字符串来找到对应的页面,action和category均匹配
    public void myAction(View v) {
        Intent it = new Intent();
        it.setAction("Hello World");
        it.addCategory("android.intent.category.DEFAULT");
        startActivity(it);

    }

    //打开程序入口，特征是桌面
    public void actionMain(View v) {
        try {
            Intent it = new Intent(Intent.ACTION_MAIN);
            it.addCategory(Intent.CATEGORY_HOME);
            startActivity(it);
        } catch (Exception e) {
            Toast.makeText(this, "找不到目标页面", Toast.LENGTH_SHORT).show();
        }
    }

}
