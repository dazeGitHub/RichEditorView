package me.jingbin.richeditorview;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import me.jingbin.richeditor.bottomlayout.LuBottomMenu;
import me.jingbin.richeditor.editrichview.SimpleRichEditor;
import me.jingbin.richeditor.editrichview.base.RichEditor;
import me.jingbin.richeditorview.tools.EmojiUtils;
import me.jingbin.richeditorview.tools.KeyBoardListener;
import me.jingbin.richeditorview.tools.Tools;

public class Main2Activity extends AppCompatActivity {

    private final String contentImageSrc = "https://upload-images.jianshu.io/upload_images/15152899-e1a43b1cca2a4d58.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp";
    private final String assetImageSrc = ":八马_哼:"; //:八马_哼        //[嘻嘻]        // file:///android_asset/images/bama002.png";
    private final String emojiImageSrc = "https://gitee.com/mayundaze/img_bed/raw/master/bama001.png";
    private final String atSomebodyStr = "@张三&nbsp"; //直接加空格不好使

    private SimpleRichEditor richEditor;
    private LuBottomMenu luBottomMenu;
    private Toolbar mToolbar;

    private String mTitle = "";
    private String mContent = "";

    private boolean isShowSourceDialog = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mToolbar = findViewById(R.id.toolbar);
        richEditor = findViewById(R.id.rich_edit);
        luBottomMenu = findViewById(R.id.lu_bottom_menu);

        initView();

        KeyBoardListener.getInstance(this).init();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        mToolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.mipmap.actionbar_more));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        richEditor.setLuBottomMenu(luBottomMenu);

        richEditor.setOnButtonClickListener(new SimpleRichEditor.OnButtonClickListener() {
            @Override
            public void addImage() {
                // 添加图片
                Tools.hideSoftKeyBoard(Main2Activity.this);
                richEditor.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        richEditor.edAddAssetImg(assetImageSrc);
                        richEditor.edAddNetImg(contentImageSrc);
                    }
                }, 70);
            }

            @Override
            public void addProduct() {
            }
        });

        richEditor.setOnOutHandleListener(new RichEditor.OnOutHandleListener() {
            @Override
            public void onClickHeaderImageListener() {
            }

            @Override
            public void onGetTitleContent(final String title, final String content) {
                Log.e("RichEdit", "---获取标题: \n" + title);
                Log.e("RichEdit", "---获取内容: \n" + content);

                String resultStr = "";
                StringBuilder sb = new StringBuilder();
                Document docObj = Jsoup.parse(content);
                Elements elements = docObj.select("#editor");

                for (Element element : elements) {
                    sb.append(element.html());
                }

                resultStr = sb.toString();
                Log.e("RichEdit", "得到 resultStr = \n" + resultStr);

                resultStr = EmojiUtils.instance.replaceImgLabel2Bama(resultStr);

                final String finalResultStr = resultStr;
                richEditor.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isShowSourceDialog) {
                            Tools.show(richEditor, title, finalResultStr, "保存", "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mTitle = title;
                                    mContent = finalResultStr;
                                    Toast.makeText(Main2Activity.this, "保存成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            mTitle = title;
                            mContent = finalResultStr;
                            Toast.makeText(Main2Activity.this, "保存成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 10);
            }
        });

        findViewById(R.id.btn_add_emoji).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                richEditor.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        richEditor.edAddEmojisrc(assetImageSrc);
                    }
                }, 70);
            }
        });

        findViewById(R.id.btn_add_at_sb).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                richEditor.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        richEditor.edAddStr(atSomebodyStr);
                    }
                }, 70);
            }
        });

        findViewById(R.id.btn_test_emoji).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String inputStr = "啦啦啦<img class=\"emojibox\" src=\"https://bbs.78dm.net/assets/f1a0e8de/images/smileys/bama001.png\">哈哈银行";
                String resultStr = EmojiUtils.instance.replaceImgLabel2Bama(inputStr);
                Log.e("TAG", "resultStr = " + resultStr);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionbar_get:
                isShowSourceDialog = true;
                richEditor.edThishtml();
                break;
            case R.id.actionbar_clear:
                richEditor.edOutdata( "");
                mToolbar.setTitle("0字");
                break;
            case R.id.actionbar_save:
                isShowSourceDialog = false;
                richEditor.edThishtml();
                break;
            case R.id.actionbar_show:
                if (!TextUtils.isEmpty(mContent)) {

                    String resultStr = EmojiUtils.instance.replaceBama2ImgLabel(mContent);
                    richEditor.edOutdata(resultStr);    // 回显 标题和内容

//                    richEditor.loadUrl("file:///android_asset/rich/editor_default.html");
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                        richEditor.evaluateJavascript("document.getElementById('editor').innerHTML='" + "Hello" + "'", new ValueCallback<String>() {
//                            @Override
//                            public void onReceiveValue(String value) {
//
//                            }
//                        });
//                    }
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
