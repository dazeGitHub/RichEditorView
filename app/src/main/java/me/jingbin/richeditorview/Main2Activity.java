package me.jingbin.richeditorview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import me.jingbin.richeditor.bottomlayout.LuBottomMenu;
import me.jingbin.richeditor.editrichview.SimpleRichEditor;
import me.jingbin.richeditorview.tools.KeyBoardListener;
import me.jingbin.richeditorview.tools.Tools;

public class Main2Activity extends AppCompatActivity {

    private final String contentImageSrc = "https://upload-images.jianshu.io/upload_images/15152899-e1a43b1cca2a4d58.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp";
    private final String emojiImageSrc = "https://gitee.com/mayundaze/img_bed/raw/master/bama001.png";
    private SimpleRichEditor richEditor;
    private LuBottomMenu luBottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        KeyBoardListener.getInstance(this).init();

        richEditor = findViewById(R.id.rich_edit);
        luBottomMenu = findViewById(R.id.lu_bottom_menu);

        initView();
    }

    private void initView() {
        richEditor.setLuBottomMenu(luBottomMenu);

        richEditor.setOnButtonClickListener(new SimpleRichEditor.OnButtonClickListener() {
            @Override
            public void addImage() {
                // 添加图片
                Tools.hideSoftKeyBoard(Main2Activity.this);
                richEditor.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        richEditor.edAddimgsrc(contentImageSrc);
                    }
                }, 70);
            }

            @Override
            public void addProduct() {}
        });

        findViewById(R.id.btn_add_emoji).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                richEditor.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        richEditor.edAddEmojisrc(emojiImageSrc);
                    }
                }, 70);
            }
        });

    }
}
