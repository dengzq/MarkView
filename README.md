# MarkView

Android消息角标;

特点：</br>
1.支持多种模式：文字带圆点，文字带消息数，图片带圆点，图片带消息数；</br>
2.支持自定义特性，包括文本大小，颜色，背景色，边框颜色和大小，消息数和主体的间距；

### 演示
<img width="338" height="565" src="https://github.com/dengzq/MarkView/blob/master/image/androidmarkview.gif" alt="HeaderAndFooter"/>

### 配置
Maven

```
<dependency>
  <groupId>com.dengzq.widget</groupId>
  <artifactId>markview</artifactId>
  <version>1.0.3</version>
  <type>pom</type>
</dependency>
```
Gradle

```
compile 'com.dengzq.widget:markview:1.0.3'
```


### 使用
在xml文件中

```
<com.dengzq.markview.MarkView
            android:id="@+id/ticket7"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="10dp"
            android:background="#e3e3e3"
            app:contentImg="@mipmap/ic_launcher"
            app:contentText="@string/app_name"
            app:contentTextColor="#3cc"
            app:contentTextSize="40sp"
            app:dotSize="10dp"
            app:emptyVisible="true"
            app:markMode="textNum"
            app:tipBgColor="@color/colorAccent"
            app:tipNumber="@{String.valueOf(activity.observableNumber)}"
            app:tipSize="20dp"
            app:tipStrokeColor="#000"
            app:tipStrokeEnable="true"
            app:tipStrokeWidth="1dp"
            app:tipTbMargin="2.5dp"
            app:tipTextColor="@color/colorPrimary"
            app:xOffset="-5dp"
            app:yOffset="-10dp"/>
```
