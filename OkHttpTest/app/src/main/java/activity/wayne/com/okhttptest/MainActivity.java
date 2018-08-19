package activity.wayne.com.okhttptest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    //Get请求
    // private TextView tv_content;
    //private Button btn_get;

    //Post请求
//    private TextView tv_content2;
//    private Button btn_post;

    //Get请求-下载
    private ImageView iv_image;
    private Button btn_get2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findId();
        setListener();

    }


    public void findId() {
        //Get请求
//        tv_content = (TextView) findViewById(R.id.tv_content);
//        btn_get = (Button) findViewById(R.id.btn_get);

        //Post请求
//        tv_content2 = (TextView) findViewById(R.id.tv_content2);
//        btn_post = (Button) findViewById(btn_post);

        //Get请求-下载
        iv_image = (ImageView) findViewById(R.id.iv_image);
        btn_get2 = (Button) findViewById(R.id.btn_get2);
    }

    public void setListener() {
        //Get-Get请求获取一个网页
       /* btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.同步&异步GET请求
                //第一步:拿到OkHttpClient对象
                OkHttpClient client = new OkHttpClient();
                //第二部:构造Request对象
                *//*
                 *如需在get请求时传递参数，我们可以以下面的方式将参数拼接在url后面
                 * https://www.baidu.com?username=admin&password=admin
                 *//*
                Request request = new Request.Builder().get().url("https://www.baidu.com").build();
                //第三步:将Request封装成Call
                Call call = client.newCall(request);
                //第四步:根据需要调用同步或异步请求方法
                *//*
                 *第四步两点注意的地方：
                 * 1.同步调用会阻塞主线程，一般不适用
                 * 2.异步调用的回调函数是在子线程，我们不能在子线程更新UI,
                 *   需借助runOnUiThread()方法或Handler来处理
                 *//*
                //同步调用,返回Response,会抛出IO异常
                //Response response = call.execute();
                //异步调用，并设置回调函数
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(MainActivity.this,"get failed",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String res = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_content.setText(res);
                            }
                        });
                    }
                });
            }
        });*/

        //Post请求-异步Post请求提交键值对
        /*btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一步：拿到OkHttpClient对象
                OkHttpClient client = new OkHttpClient();
                //第二部：构建FormBody,传入参数
                FormBody formBody = new FormBody.Builder().add("username", "admin").add("password", "admin").build();
                //第三步：构建Request,将FormBody作为Post方法的参数传入
                final Request request = new Request.Builder().url("http://www.jianshu.com/").post(formBody).build();
                //第四步：将Request封装成Call
                Call call = client.newCall(request);
                //第五步：调用请求，重写回调方法
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(MainActivity.this,"post failed",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final  String res2 = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_content2.setText(res2);
                            }
                        });
                    }
                });
            }
        });*/

        //Post请求-异步Post请求提交字符串
        /*
         *上面我们的post提交键值对的参数是通过构造一个FormBody通过键值对的方式来添加进去的,
         * 其实post方法需要传入的是一个RequestBody对象,FormBody是RequestBody的子类,
         * 但有时候我们常常会遇到要传入一个字符串的需求,比如客户端给服务器发送一个json字符串,
         * 那这种时候就需要用到另一种方式来构造一个RequestBody
         *
         * 下面的MediaType我们指定传输的是纯文本,
         * 而且编码方式是utf-8,通过上面的方式我们就可以向服务端发送json字符串啦。
         */
         /* btn_post.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  //第一步：拿到OkHttpClient对象
                  OkHttpClient client = new OkHttpClient();
                  //第二步：构建RequestBody,传入参数
                  RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"),"{username:admin;password:admin}");
                  //第三步：构建Request,将RequestBody作为Post方法的参数传入
                  final Request request = new Request.Builder().url("http://www.jianshu.com/").post(requestBody).build();
                  //第四步：将Request封装成Call
                  Call call = client.newCall(request);
                  call.enqueue(new Callback() {
                      @Override
                      public void onFailure(Call call, IOException e) {
                          Toast.makeText(MainActivity.this,"post failed",Toast.LENGTH_SHORT).show();
                      }

                      @Override
                      public void onResponse(Call call, Response response) throws IOException {
                          final  String res3 = response.body().string();
                          runOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  tv_content2.setText(res3);
                              }
                          });
                      }
                  });
              }
          });*/

        //Post请求-异步Post请求上传文件
        /*
         *将手机SD卡根目录下的1.png图片进行上传。代码中的application/octet-stream
         * 表示我们的文件是任意二进制数据流,当然你也可以换成更具体的image/png
         */
      /* btn_post.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //第一步：拿到OkHttpClient对象
               OkHttpClient client = new OkHttpClient();
               //第二部：构建RequestBody,传入参数
               File file = new File(Environment.getExternalStorageDirectory(), "1.png");
               if (!file.exists()) {
                   Toast.makeText(MainActivity.this, "文件不存在", Toast.LENGTH_SHORT).show();
               } else {
                   RequestBody requestBody2 = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                   //第三步：构建Request,将RequestBody2作为Post方法的参数传入
                   final Request request = new Request.Builder().url("http://www.jianshu.com/").post(requestBody2).build();
                   //第四步：将Request封装成Call
                   Call call = client.newCall(request);
                   call.enqueue(new Callback() {
                       @Override
                       public void onFailure(Call call, IOException e) {
                           Toast.makeText(MainActivity.this,"请求上传文件失败",Toast.LENGTH_SHORT).show();
                       }

                       @Override
                       public void onResponse(Call call, Response response) throws IOException {
                           final  String res4 = response.body().string();
                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   tv_content2.setText(res4);
                               }
                           });
                       }
                   });
               }

           }
       });*/

        //Post-异步Post请求提交表单
        /*
         *简介:
         *1>在网页上经常会遇到用户注册的情况,需要你输入用户名,密码,还有上传头像,
         *  这其实就是一个表单,那么接下来我们看看如何利用OkHttp来进行表单提交。
         * 经过上面的学习,大家肯定也懂,主要的区别就在于构造不同的RequestBody传递给post方法即可.
         * 2>用到一个MuiltipartBody,这是RequestBody的一个子类,我们提交表单就是利用这个类来构建一个RequestBody,
         *  下面的代码我们会发送一个包含用户民、密码、头像的表单到服务端
         */
        /*
         *注意点：
         * (1)如果提交的是表单,一定要设置setType(MultipartBody.FORM)这一句
         *(2)提交的文件addFormDataPart()的第一个参数,就上面代码中的myfile就是类似于键值对的键,是供服务端使用的,
         * 就类似于网页表单里面的name属性,例如下面:<input type="file" name="myfile">
         *(3)提交的文件addFormDataPart()的第二个参数文件的本地的名字,第三个参数是RequestBody,里面包含了我们要上传的文件的路径以及MidiaType
         *(4)记得在AndroidManifest.xml文件中添加存储卡读写权限
         */
        /* btn_post.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //第一步：拿到OkHttpClient对象
               OkHttpClient client = new OkHttpClient();
               //第二部：构建RequestBody,传入参数
               File file = new File(Environment.getExternalStorageDirectory(), "1.png");
               if (!file.exists()) {
                   Toast.makeText(MainActivity.this, "文件不存在", Toast.LENGTH_SHORT).show();
                   return;
               }
               RequestBody multipartBody = new MultipartBody.Builder()
                       .setType(MultipartBody.FORM)
                       .addFormDataPart("username","admin")
                       .addFormDataPart("password","admin")
                       .addFormDataPart("myfile","1.png",RequestBody.create(MediaType.parse("application/octet-stream"),file))
                       .build();
               //第三步：构建Request,将multipartBody作为Post方法的参数传入
               final Request request = new Request.Builder().url("http://www.jianshu.com/").post(multipartBody).build();
               //第四步：将Request封装成Call
               Call call = client.newCall(request);
               call.enqueue(new Callback() {
                   @Override
                   public void onFailure(Call call, IOException e) {
                       Toast.makeText(MainActivity.this,"请求上传文件失败",Toast.LENGTH_SHORT).show();
                   }

                   @Override
                   public void onResponse(Call call, Response response) throws IOException {
                       final  String res5 = response.body().string();
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               tv_content2.setText(res5);
                           }
                       });
                   }
               });
           }
       });
*/

    //Get-异步Get请求下载文件
        //从网络下载一个文件到本地根目录(此处我们以下载一张图片为例)
       /* btn_get2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();
                final Request request = new Request.Builder()
                        .get()
                        .url("https://www.baidu.com/img/bd_logo1.png")
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(MainActivity.this,"下载图片失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //拿到字节流
                        InputStream is = response.body().byteStream();

                        int len = 0;
                        final File file = new File(Environment.getExternalStorageDirectory(),"n.png");
                        FileOutputStream fos = new FileOutputStream(file);
                        byte[] buf = new byte[128];

                        while ((len = is.read(buf))!=-1){
                            fos.write(buf,0,len);
                        }

                        fos.flush();

                        fos.close();
                        is.close();
                    }
                });
            }
        });*/


        //Get-异步Get请求下载文件
       // 从网络下载一张图片并显示到ImageView中
        //使用BitmapFactory的decodeStream将图片的输入流直接转换为Bitmap,然后设置到ImageView中
        btn_get2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();
                final Request request = new Request.Builder()
                        .get()
                        .url("https://www.baidu.com/img/bd_logo1.png")
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(MainActivity.this,"下载图片失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        InputStream is = response.body().byteStream();

                        final Bitmap bitmap = BitmapFactory.decodeStream(is);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iv_image.setImageBitmap(bitmap);
                            }
                        });
                    }
                });
            }
        });

    }
}