package activity.wayne.com.greendaotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import entity.wayne.com.greendaotest.User;
import gen.wayne.com.greendaotest.UserDao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mAdd, mDelete, mUpdate, mFind;
    private TextView mContext;
    private User mUser;
    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        mAdd.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mFind.setOnClickListener(this);
    }

    private void initView() {
        mContext = (TextView) findViewById(R.id.textView);
        mAdd = (Button) findViewById(R.id.button);
        mDelete = (Button) findViewById(R.id.button2);
        mUpdate = (Button) findViewById(R.id.button3);
        mFind = (Button) findViewById(R.id.button4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                addData();
                break;
            case  R.id.button2:
                deleteData();
                break;
            case R.id.button3:
                updateData();
                break;
            case R.id.button4:
                findData();
                break;
        }
    }

    private void addData() {
        mUser = new User((long)3,"songweiqi");
        mUserDao.insert(mUser);
        mContext.setText(mUser.getName());
    }

    private void deleteData(){
        deleteUserById(2);
    }

    /**
     * 根据主键删除User
     * @param id  User的主键id
     */
    private void deleteUserById(long id) {
        mUserDao.deleteByKey(id);
    }

    private void updateData(){
        mUser = new User((long)2,"dawei");
        mUserDao.update(mUser);
    }

    private void findData(){
        List<User> users = mUserDao.loadAll();
        String userName="";
        for(int i = 0;i<users.size();i++){
            userName += users.get(i).getName()+",";
        }
        mContext.setText("查询全部数据==>"+userName);
    }
}
