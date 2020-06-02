## RichEditorView
Android 基于WebView的富文本编辑器 - 仿简书编辑器

## Functions

 - Bold
 - Italic
 - Strikethrough
 - Blockquote
 - Heading 1
 - Heading 2
 - Heading 3
 - Heading 4
 - Line
 - Undo
 - Redo
 - Insert Image
 - Insert Product(Custom layout)

特点：

 - 点击内容部分，键盘会将底部菜单栏弹起，点击标题则收起
 - 文字会自动定位在菜单栏的上方
 - 文字类型标识如加粗，删除线会根据光标位置自动改变

## Setting for Editor


```java
richEditor.edThishtml();                  // 获取标题和内容
richEditor.edUpcover(headerImageSrc);     // 添加封面图片
richEditor.edAddimgsrc(contentImageSrc);  // 添加内容图片
richEditor.edAddProduct(123, new Gson().toJson(goodsBean)); // 添加产品
richEditor.edOutdata(mTitle, mContent);  // 回显内容
```

## Screenshots

<img width="300" height=“470” src="https://raw.githubusercontent.com/dazeGitHub/RichEditorView/master/file/test.jpg"></img>

## Thanks
 - reference by [RichEditorWeb](https://github.com/youlookwhat/RichEditorView)

## License
```
Copyright (C) 2016 Bin Jing

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
