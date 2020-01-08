package com.facebookfanstatus.facebookfanstatus;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.facebookfanstatus.facebookfanstatus.Class.Smsinformation;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    Animation animFadein,animFadein2;
    private TextView serial,serial1,headline,details,totalPosition;
    ImageView copy,share;
    String sms_serial,sms_headline,sms_details,tP;
    int positon=0,category;

    private ClipboardManager myClipboard;
    private ClipData myClip;
    NestedScrollView parent;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    int adsCount = 0;

    /**
     * 1
     */
    private ArrayList<Smsinformation> arrayList=new ArrayList<>();
    private ArrayList<Smsinformation> arrayList1=new ArrayList<>();
    private ArrayList<Smsinformation> arrayList2=new ArrayList<>();
    private ArrayList<Smsinformation> arrayList3=new ArrayList<>();
    private ArrayList<Smsinformation> arrayList4=new ArrayList<>();
    private ArrayList<Smsinformation> arrayList5=new ArrayList<>();
    private ArrayList<Smsinformation> arrayList6=new ArrayList<>();
    private ArrayList<Smsinformation> arrayList7=new ArrayList<>();
    private ArrayList<Smsinformation> arrayList8=new ArrayList<>();
    private ArrayList<Smsinformation> arrayList9=new ArrayList<>();
    private ArrayList<Smsinformation> arrayList10=new ArrayList<>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);



        ads_setup();





        animFadein2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in2);



        serial=findViewById(R.id.details_serialid);
        serial1=findViewById(R.id.details_serialid1);
        headline=findViewById(R.id.details_titleid);
        details=findViewById(R.id.details_detailsid);
        totalPosition=findViewById(R.id.hh);


        parent=findViewById(R.id.parent);

        sms_serial=getIntent().getStringExtra("serial");
        sms_headline=getIntent().getStringExtra("headline");
        sms_details=getIntent().getStringExtra("details");
        tP=getIntent().getStringExtra("tp");
        positon=getIntent().getIntExtra("position",0);
        category=getIntent().getIntExtra("category",0);

        copy=findViewById(R.id.copyid);
        share=findViewById(R.id.shareid);

        /**
         * 2
         */

        switch (category)
        {
            case 1:
                arrayList=arrayList1;
                break;
            case 2:
                arrayList=arrayList2;
                break;
            case 3:
                arrayList=arrayList3;
                break;
            case 4:
                arrayList=arrayList4;
                break;
            case 5:
                arrayList=arrayList5;
                break;
            case 6:
                arrayList=arrayList6;
                break;
            case 7:
                arrayList=arrayList7;
                break;

            case 8:
                arrayList=arrayList8;
                break;
            case  9:
                arrayList=arrayList9;
                break;
                case  10:
                arrayList=arrayList10;
                break;

        }

        serial.setText(sms_serial);
        serial1.setText(sms_serial);
        headline.setText(sms_headline);
        details.setText(sms_details);
        totalPosition=findViewById(R.id.hh);

        totalPosition.setText(tP);

        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);


        findViewById(R.id.rightIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {



                try {
                    positon++;
                    serial.setText(arrayList.get(positon).getSerial());
                    serial1.setText(arrayList.get(positon).getSerial());
                    headline.setText(arrayList.get(positon).getTitle());
                    details.setText(arrayList.get(positon).getDetails());
                    parent.setVisibility(View.VISIBLE);


                    adsCount++;

                    if (adsCount==10)
                    {
                        IntersitalAds();
                        adsCount=0;
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    positon=0;
                    Log.i("123","error"+e.getMessage());
//                    serial.setText(arrayList.get(positon).getSerial());
//                    serial1.setText(arrayList.get(positon).getSerial());
//                    headline.setText(arrayList.get(positon).getTitle());
//                    details.setText(arrayList.get(positon).getDetails());
//                    parent.setVisibility(View.VISIBLE);

                }


            }
        });

        findViewById(R.id.leftIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try {
                    positon--;
                    serial.setText(arrayList.get(positon).getSerial());
                    serial1.setText(arrayList.get(positon).getSerial());
                    headline.setText(arrayList.get(positon).getTitle());
                    details.setText(arrayList.get(positon).getDetails());
                    parent.setVisibility(View.VISIBLE);


                } catch (Exception e) {
                    e.printStackTrace();
                    positon=arrayList.size()-1;
                    serial.setText(arrayList.get(positon).getSerial());
                    serial1.setText(arrayList.get(positon).getSerial());
                    headline.setText(arrayList.get(positon).getTitle());
                    details.setText(arrayList.get(positon).getDetails());
                    parent.setVisibility(View.VISIBLE);

                }

            }
        });



        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //   Toast.makeText(DetailsActivity.this, ""+arrayList.get(positon).getTitle(), Toast.LENGTH_SHORT).show();

                //      t1 = ed1.getText().toString();
                myClip = ClipData.newPlainText("text", details.getText().toString());
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getApplicationContext(), "Text Copied",
                        Toast.LENGTH_SHORT).show();

            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,details.getText().toString());
                startActivity(Intent.createChooser(shareIntent, "Share..."));
            }
        });


        /**
         * 2
         */

        //position one
        /**
         * 1
         */

        arrayList.add(new Smsinformation(1,"1","","\uD83D\uDC49বউ হবা\uD83D\uDC38\uD83D\uDC49এত্ত এত্ত ভালোবাসা দিমু\uD83D\uDE0Dকখনো কষ্ট দিমু না\uD83D\uDE0Cখালি ঝাড়ু দিয়ে পিটামু\uD83D\uDE1C\uD83D\uDE1C\n" +
                "→রাজি আছে তো\uD83D\uDE0A"));
        arrayList.add(new Smsinformation(1,"2","","কারো পৌষ মাস কারো সর্বনাশ \uD83D\uDE2D\uD83D\uDE2D\n" + "কেউ মিষ্টি খাবে\uD83D\uDE0A - \uD83D\uDE11 কারো অল্প বয়সে বিয়ে হবে\uD83D\uDE15\uD83D\uDE15 - নাইলে রিক্সা ড্রাইভার হবে \uD83D\uDE2D\uD83D\uDE2D"));
        arrayList.add(new Smsinformation(1,"3","","পরিবারের বড় ছেলে \uD83D\uDE2F গুলা ইকটু বঘাটে \uD83D\uDE2Eআর দুষ্ট টাইপের হয় \uD83D\uDE0D\uD83D\uDE18 যেমন আমি\uD83D\uDE2C\uD83D\uDE4C\uD83D\uDE0C এটা নিয়ে ছোটদের \uD83D\uDE32 রাজনীতি করার কিচ্ছু নায়\uD83D\uDE0F"));
        arrayList.add(new Smsinformation(1,"4","","\uD83D\uDC49কমেন্ট এ যদি তুমি \uD83D\uDC49143\uD83D\uDC48 লিখে\uD83D\uDE0Eতাহলে আমি তোমাকে অনেক গুলো চুড়ি কিনে দিবো\uD83D\uDE0D\uD83D\uDE0Dকথা দিলাম\uD83D\uDE0C\uD83D\uDE0C\uD83D\uDE09"));
        arrayList.add(new Smsinformation(1,"5","","ওরা কারা!?!\uD83D\uDE0F\uD83D\uDE0F\uD83D\uDE0F যারা আর কিছু পারুক বা নাই পারুক,\uD83D\uDE15\uD83D\uDE10\uD83D\uDE33 আলগা পিরিত খুব ভালো করে দেখাইতে পারে।\uD83D\uDE12\uD83D\uDE24 মেনশন করে ধরিয়ে দেন তাদের।\uD83D\uDE02\uD83E\uDD23\uD83D\uDC47"));
        arrayList.add(new Smsinformation(1,"6","","মেনশন করুন\uD83D\uDE18\uD83D\uDE18তাকে যার রাগ\uD83D\uDE21 উঠাতে অাপনার সেই মজা লাগে\uD83D\uDE05"));
        arrayList.add(new Smsinformation(1,"7","","আজকাল \uD83D\uDC9C(Advanger_Movie) \uD83D\uDCAAতে সুপারম্যান,,,রোর্বটদেরও গফ দেখায়;\uD83D\uDE02 কিন্তুু কিছু কিছু ছেলে এতোটাই \uD83D\uDC4Cসিঙ্গেল যে তাঁদের বান্ধুবীও নাই থাকলেও বিবাহিত অথবা বফ আছে....\uD83D\uDE01\uD83D\uDE1B\uD83D\uDE01"));
        arrayList.add(new Smsinformation(1,"8","","\uD83D\uDC49\uD83D\uDC49আমাদের আশেপাশের বন্ধু রূপে এমন কিছু মানুষ বাস করে\uD83D\uDE33যারা সবসময় আমাদের বাশ\uD83C\uDF35দেয়ার তালে থাকে"));
        arrayList.add(new Smsinformation(1,"9","","সসচ পরীক্ষার্থী দের হার্ডবিট বাড়ানোর জন্য বেশি কিছু লাগে নাহ। \uD83D\uDE02 শুধু বলবেন ওইইই শুন আজকে কয় তারিখরে? দেখবেন কান্না কান্না মুখ করে পেলসে!\uD83D\uDE02"));
        arrayList.add(new Smsinformation(1,"10","","হঠাৎ রাস্তায় এক্সের সাথে দেখা হয়ে গেলে ব্যাকগ্রাউন্ডে মিউজিক বাজানোর জন্য হলেও তোমাকে চাই!\uD83D\uDC38\uD83C\uDF38"));
        arrayList.add(new Smsinformation(1,"11","","ওরা কারা\uD83D\uDE12\uD83D\uDE12 যারা নুসরাত ফারিয়ার \uD83D\uDE2E রেপ টাকা \uD83D\uDE21 থুক্কু রে পটাকা গানটা এখনো শুনে নায়\uD83D\uDE2C\uD83D\uDE02 আমিও\uD83D\uDE4C\uD83D\uDE0F"));
        arrayList.add(new Smsinformation(1,"12","","ওরা কারা\uD83D\uDE18\uD83D\uDE18 যারা কোলবালিশ\uD83D\uDE0D ছাড়া ঘুমাতে পারেনা\uD83D\uDE0F\uD83D\uDE05"));
        arrayList.add(new Smsinformation(1,"13","","সবাই #Hi লিখে \uD83D\uDE2E কমেন্ট করেন \uD83D\uDE02 যার কমেন্টে \uD83D\uDE2E কোন রিপলে আসবে না \uD83D\uDE0F\uD83D\uDE0Fসেই হবে আজকের বিজয়ী \uD83C\uDFC6\uD83C\uDF89\uD83C\uDFC6"));
        arrayList.add(new Smsinformation(1,"14","","তারাই তো প্রকৃত \uD83D\uDE2F সৌভাগ্যবান ☺ যারা আজকে \uD83D\uDE0C ফজরের নামাজ আদায় করেছে"));
        arrayList.add(new Smsinformation(1,"15","","শুভ সকাল....\uD83D\uDE07 সকাল সকাল (morning_walk)করাটা ভালো....\uD83D\uDE0E ওরা কারা যারা (morning_walk)এ যায়নি;\uD83D\uDE16\uD83D\uDE4A ওরা_আমি\uD83D\uDE1B\uD83D\uDE01\uD83D\uDE37"));
        arrayList.add(new Smsinformation(1,"16","","আপনি কার জন্য রাত জাগেন,\uD83D\uDE2A\uD83D\uDE34ভেবেছেন কখনো???\uD83D\uDE12\uD83D\uDE12\uD83D\uDE12 তার জন্য,যে আপনাকে অবহেলা করে,\uD83D\uDE1F\uD83D\uDE16\uD83D\uDE22 আর আরেকজনকে good night বলে ঘুমিয়ে যায়।\uD83D\uDE42\uD83D\uDE42\uD83D\uDE42"));
        arrayList.add(new Smsinformation(1,"17","","\uD83D\uDC49এখানে তুমি আমার কি হতে চাও খুব জানতে ইচ্ছে করে\uD83D\uDC38 →A.ভাবি হলে শাড়ি কিনে দিবো\uD83D\uDE01 →B.প্রমিকা হলে চুরি কিনে দিবো\uD83D\uDE09 →C.বোন হলে চকলেট কিনে দিবো\uD83C\uDF6C\uD83C\uDF6D →D.বন্ধু হলে মাইর দিবো\uD83D\uDC4A\uD83D\uDC4B✊ →E.বউ হলে যা চাইবা তাই দিবো\uD83D\uDE0D"));
        arrayList.add(new Smsinformation(1,"18","","ইতিহাস সাক্ষী!!! \uD83D\uDE0F\uD83D\uDE0F\uD83D\uDE0F কোনো ছেলে\uD83D\uDC68 আজ পর্যন্ত কোনো মেয়েকে\uD83D\uDC69 বলেনি যে,\uD83D\uDE36\uD83D\uDE33 আমাকে ডিস্টার্ব করবেন না প্লিজ।\uD83D\uDE37\uD83D\uDE0Aআমার গার্লফ্রেন্ড আছে।।\uD83D\uDE42\uD83D\uDE07 হায়রে ছেলেরা!!!\uD83D\uDE12\uD83D\uDE12\uD83D\uDE12"));
        arrayList.add(new Smsinformation(1,"20","","বালিকা\uD83D\uDE18 \uD83D\uDC49\uD83D\uDC49এই ঠাডা মারা বৃষ্টির সময়☔তুমি আর আমি একসাথে ঠাডিতো হয়ে মরতে চাইলে\uD83D\uDE4Aতুমি কি রাজি হবে নাকি ব্রেকআপ করবে\uD83D\uDE30"));
        arrayList.add(new Smsinformation(1,"21","","সেই বালক\uD83D\uDE18কিউটের ডিব্বা যে ভাত\uD83C\uDF5Aরান্না করতে পারে\uD83D\uDE0B\uD83D\uDE1C"));
        arrayList.add(new Smsinformation(1,"22","","ফোন চ্যানেলে জানি ত্রিভুজ প্রেমের কি একটা সিন দেখলাম। রিয়াজ, ফেরদৌস আর পপি। কুছ কুছ হোতা হ্যায় এর মত তিনজন ঘাসে শুয়ে আছে। পীথাগোরাস মনে হয় এমন কিছু দেখেই বলেছিলেন, সমকোনী ত্রিভুজের অতিভুজের বর্গ, বাকি দুই বাহুর বর্গের সমষ্টির সমান। পপি হইল অতিভুজ।"));
        arrayList.add(new Smsinformation(1,"23","","অনেকে নিশ্চয়ই মনে করতেসেন এটা বাংলা সিনেমার নায়িকার ডায়লগ। আসলে তা না। এটা ক্যাপ্টেন হ্যাডকের (টিনটিন) ডায়লগ। তবে বানর, বেল্লিক, উল্লুক, হিপোপে্টোমাস এইসবও থাকবে....................।"));
        arrayList.add(new Smsinformation(1,"24","","ক্যাম্পাসে গেলে হট পেটিস খাওয়া হয়। যে মামা পেটিস বিক্রি করে সে ফেরী করে বেঁচে আর বলতে থাকে, হট পেটিস, হট পেটিস। একবার পেটিস খাওয়ার জন্য মামাকে ডাক দিলাম। আই হট....... পেটিস। আমাদের মাঝখানে থাকা একটা মেয়ে হটাৎ করে অগ্নিচোখে আমার দিকে তাকাল। ক্যান বুঝলা না? সম্ভবত পেটিস শব্দটা সে শুনতে পায় নায়। মানুষ মাঝে মাঝেই ভুল বুঝে"));
        arrayList.add(new Smsinformation(1,"25","","বুকের ভেতর আগুন নামক বাংলা সিনেমায় ভিলেনের প্রতি নায়কের মায়ের একটা ডায়লগ ছিল-  আমার বুকের ভেতর যে আগুন তা মেটানোর মত কোন দমকল তোমার কাছে নেই"));
        arrayList.add(new Smsinformation(1,"26","","George Michael এর Careless Whisper গানটা সুন্দর। তবে এটা বাংলাদেশের চাইনিজ রেস্টুরেন্ট গুলাতে এতই বাজানো হয়েছে যে এটা শুনতে গেলেই মনে হয় একটু পরে ওয়েটার আসবে স্যুপের অর্ডার নিতে।"));
        arrayList.add(new Smsinformation(1,"27","","বিশ্বকাপের উদ্বোধনী অনুষ্ঠানের দায়িত্ব পেয়েছে মাহফুজুর রহমানের স্যাটেলাইট চ্যানেল এটিএন বাংলার অঙ্গ প্রতিষ্ঠান এটিএন ইভেন্টস। ইভা রহমানের গান শুনে যেহেতু সব টিম এমনিতেই পালায় যাবে তাই বাংলাদেশ এইবার নিশ্চিত চ্যাম্পিয়ান। তবে আমরা আশা রাখি বাংলাদেশ খেলেই চ্যাম্পিয়ান হবে।"));
        arrayList.add(new Smsinformation(1,"28","","কিছু কিছু ব্যাপার থাকে যেগুলা ছেলেরা উপেক্ষা আর মেয়েরা সহ্য করতে পারেনা। এই যেমন শীলা কি জাওয়ানী।"));
        arrayList.add(new Smsinformation(1,"29","","ফেিমি মাছ সমুদ্রের অতল গহ্বরে থাকে বলে তারা মনে করে তারা পৃথিবীর সব থেকে বড় প্রানী। বাংলা সিনেমার নায়িকাদের দেখলে বুঝত স্থলে ওদের থেকে বড় প্রানী আছে।"));
        arrayList.add(new Smsinformation(1,"30","","ডিয়ার হুবু ওগো!!\uD83D\uDE0C আমি মরে গেলে তুমি কেঁদো না।\uD83D\uDE1E\uD83D\uDE1E আমি মরার সাথ সাথ তুমিও মরে যেও,,,\uD83D\uDE36 তারপর দুইজনে মিল্লা শ\u200D্যাওলা গাছের উপর বইসা সবাইরে ভয় দেখামু!\uD83D\uDC7B\uD83D\uDC7B\uD83D\uDC79\uD83D\uDE02\uD83D\uDE02"));
        arrayList.add(new Smsinformation(1,"31","","ছেলেরা\uD83D\uDE32 আসলেই অনেক ধৈর্য্যশীল \uD83D\uDE0C কারন তারা একসাথে \uD83D\uDE2F ৭/৮ গফ রাখতে পারে\uD83D\uDE2C\uD83D\uDE12\uD83D\uDE02"));
        arrayList.add(new Smsinformation(1,"32","","ফগবেষণা ছাড়াই প্রমাণিত \uD83D\uDE12\uD83D\uDE12 . কিউট আর সুন্দরী মেয়ে গুলোর দাত বাকা ত্যাড়া হয় \uD83D\uDE02\uD83D\uDE02"));
        arrayList.add(new Smsinformation(1,"33","","ব্রেকাপের পর যখন সে বলে\uD83D\uDC49\uD83C\uDFFC\uD83D\uDC49\uD83C\uDFFCআচ্ছা আমরা কি বন্ধু হইয়ে থাকতে পারি\uD83D\uDE1E\uD83D\uDE16\uD83D\uDE4A তখন মনে হয় ছিনতাইকারী সব কেড়ে নিয়ে বলছে যোগাযোগ রাইখেন ভাই\uD83D\uDE01\uD83D\uDE02\uD83D\uDE48"));
        arrayList.add(new Smsinformation(1,"34","","Comments Ur\uD83D\uDE2F nickname\uD83D\uDE0C\uD83D\uDC40 আমি বলে দিবো তুমি কত জনের ক্রাশ\uD83D\uDE0D\uD83D\uDE0D\uD83D\uDC97\uD83D\uDE12"));
        arrayList.add(new Smsinformation(1,"35","","\uD83D\uDC49ভাবছিলাম মশার মত সারা জীবন তোমার পাশে থাকবে\uD83D\uDC12\uD83D\uDE4Aকিন্তু কখনও ভাবিনি তুমি যে এভাবে কয়েল জ্বালায়ি দিবে\uD83D\uDE1C"));
        arrayList.add(new Smsinformation(1,"36","","চাপা মারলে \uD83D\uDE21 চাপা ফেটে যাবে\uD83D\uDE12\uD83D\uDE12 এই পর্যন্ত কয়টা \uD83D\uDE2F ফোন \uD83D\uDCF1 ব্যবহার করেছেন?\uD83D\uDE20 আমি ২ তা\uD83D\uDE0E"));
        arrayList.add(new Smsinformation(1,"37","","গবেষনায় প্রমাণিত:\uD83D\uDC4C\uD83D\uDE0E\uD83D\uDC4C সকাল সকাল উঠে যারা \uD83D\uDE08গফ/বফদের সাথে কথা বলে \uD83D\uDE01তাঁরা আসলে সকাল দিকে \uD83D\uDE34ঘুম আনার পদ্ধতি করে...\uD83D\uDE4A কারণ বফ/গফদের আজাইরা প্যাচাল ঘুম আনায়াই ছাড়ে;\uD83D\uDE06\uD83D\uDE02\uD83D\uDE06"));
        arrayList.add(new Smsinformation(1,"38","","\uD83D\uDE12আমার মতো কে কে আছো;\uD83D\uDE08 এমন যে সকাল সকাল লুকিয়ে \uD83D\uDCF2মোবাইল নিয়েই\uD83D\uDCF1 ফেবুতে;\uD83D\uDE4A এমন \uD83D\uDE0Eলিজেন্ডেরদের দেখবাম;\uD83D\uDE01"));
        arrayList.add(new Smsinformation(1,"39","","আচ্ছা ছেলেদের\uD83D\uDC68 ওই একটা মনেই\uD83D\uDC98 এতগুলো মেয়ের\uD83D\uDC69 বসবাস কেমনে হয়???\uD83D\uDE09\uD83D\uDE02\uD83D\uDE1D কেমনে ম্যান কেমনে???\uD83D\uDE12\uD83D\uDE10\uD83D\uDE06"));
        arrayList.add(new Smsinformation(1,"40","","আচ্ছা ছেলেদের\uD83D\uDC68 ওই একটা মনেই\uD83D\uDC98 এতগুলো মেয়ের\uD83D\uDC69 বসবাস কেমনে হয়???\uD83D\uDE09\uD83D\uDE02\uD83D\uDE1D কেমনে ম্যান কেমনে???\uD83D\uDE12\uD83D\uDE10\uD83D\uDE06"));
        arrayList.add(new Smsinformation(1,"41","","\uD83D\uDC49মেয়েদের এক পায়ে\uD83D\uDC62\uD83D\uDC62নুপুর থাকে\uD83D\uDE31কিন্তু অন্য পা খালি থাকে কেন..?\uD83D\uDC12\uD83D\uDE4A\uD83D\uDE01 →শুধুমাত্র লিজেন্ডরা বলতে পারবে\uD83D\uDE1C\uD83D\uDE1C"));
        arrayList.add(new Smsinformation(1,"12","","প্রতিদিন হাজার ব্যস্ততার মধ্যেও ⌛⏳.... প্রিয় মানুষটিকে\uD83D\uDE0D\uD83D\uDE18 সময় দেওয়ার নামই \uD83D\uDC47 ভালোবাসা♥ ম্যানশন ইউর ভালোবাসাটাকে\uD83D\uDC95"));
        arrayList.add(new Smsinformation(1,"13","","\uD83D\uDC49\uD83D\uDC49সব ফ্রেন্ড সার্কেলে এমন একজন থাকে\uD83D\uDE33 \uD83D\uDC47 \uD83D\uDC49যে সব সময় বিবাহিত মহিলাদের উপর ক্রাশ খাই\uD83D\uDE01\uD83D\uDE0C"));
        arrayList.add(new Smsinformation(1,"44","","\uD83D\uDC49\uD83D\uDC49আমার খুব ইচ্ছে\uD83D\uDE0Aজীবনে অন্যের গার্লফ্রেন্ড এর সাথে প্রেম করে\uD83D\uDE31তাকে বিয়ে করা\uD83D\uDE0E\uD83D\uDE06"));
        arrayList.add(new Smsinformation(1,"45","","আপনার জীবনে কে সবচেয়ে বেশী গুরুত্বপূর্ণ☺?? রিয়েক্ট করে বুঝান\uD83D\uDE09\uD83D\uDE48 . ❤মা/বাবা \uD83D\uDE2Eগফ/বফ \uD83D\uDE21ভাই/বোন \uD83D\uDE06বেস্টু \uD83D\uDE22নিজেই \uD83D\uDC4Dঅন্য কেউ"));
        arrayList.add(new Smsinformation(1,"46","","ওরা কারা\uD83D\uDE18\uD83D\uDE18 যারা হাউমাউ করে বা কারো সামনে কান্না\uD83D\uDE30 করতে পারেনা\uD83D\uDE1E"));
        arrayList.add(new Smsinformation(1,"47","","ওরা কারা???\uD83D\uDE0F\uD83D\uDE0F\uD83D\uDE0F যারা ছুটির দিনে\uD83D\uDE0A\uD83D\uDE0D কোথাও ঘুরতে না যেয়ে,\uD83D\uDE12\uD83C\uDF92\uD83D\uDECD বাড়িতে আরামসে নাক ডেকে ঘুমাই।\uD83D\uDE2A\uD83D\uDE34\uD83D\uDCA4 ওরা আমি।\uD83D\uDE01\uD83D\uDE0C\uD83D\uDE48"));
        arrayList.add(new Smsinformation(1,"48","","আহাআআআআ\uD83D\uDE1C\uD83D\uDE1C ক্রাশ যখন বাইক দিয়া একলা একলা যায়\uD83D\uDE0D\uD83D\uDE0Dতখন মনে হয় নিজে গিয়া বাইকের পিছনে বসি!!✌✌ কিন্তু ঠিক তখনই ক্রাশ এর গফ আইসা পিছনের সিটে বসে পড়ে!!!\uD83D\uDE12\uD83D\uDE12 অতঃপর আরেকটি স্বপ্নের সমাপ্তি!!!!"));
        arrayList.add(new Smsinformation(1,"49","","ওরা কারা\uD83D\uDE02\uD83D\uDE02? যারা বানায় \uD83D\uDE01\uD83D\uDE01বানায় অনেক ভাব \uD83D\uDE0C\uD83D\uDE09\uD83D\uDE1Cমারা কথা বলতে খুব ভালো পারে।\uD83D\uDE2C\uD83D\uDE2C ম্যানশন দ্যেট পারসান\uD83D\uDE33"));
        arrayList.add(new Smsinformation(1,"50","","ওহে বালক,\uD83D\uDC68\uD83D\uDE0F তোমার ঠোটের\uD83D\uDC44 মুচকি হাসি\uD83D\uDE0A দেখে ক্রাশ খেয়েছিলাম।\uD83D\uDE3B\uD83D\uDC98 কিন্তু পরে দেখি যে তোমার দাঁত ই নাই।\uD83D\uDE02\uD83E\uDD23\uD83D\uDE06 হায় কপ্পাল!!!\uD83D\uDE1F\uD83D\uDE12\uD83D\uDE22"));

        /**
         * 2
         */
        arrayList2.add(new Smsinformation(2,"1","","ফেসবুকে ছেলেরা,\uD83D\uDC68\uD83D\uDC65 মেয়েদেরকে\uD83D\uDC69 তখনই আপু বলে ডাকে \uD83D\uDE01\uD83D\uDE02 যখন তার ফেসবুক পাসওয়ার্ড\uD83D\uDCF2 তার গার্লফ্রেন্ডের\uD83D\uDC6B কাছে থাকে।\uD83E\uDD23\uD83D\uDE06\uD83D\uDE1C এক্কেরে হাচা কথা।\uD83D\uDE0F\uD83D\uDE09\uD83D\uDE0C"));
        arrayList2.add(new Smsinformation(2,"2","","ওরা কারা!?!\uD83D\uDE0A\uD83D\uDE0F\uD83D\uDE0D যারা অন্য কারোর কান্না দেখলে,\uD83D\uDE1F\uD83D\uDE13 নিজেও হুট করে কেঁদে ফেলে।\uD83D\uDE22\uD83D\uDE2D ওরা আমি\uD83D\uDE33\uD83D\uDE28"));
        arrayList2.add(new Smsinformation(2,"3","","আজকাল পোলাপাইন যে হারে লিপস্টিক খাওয়া শুরু করছে\uD83D\uDC38\uD83D\uDC38\uD83D\uDC84\uD83D\uDC4F তাতে খুব শীঘ্রই লিপস্টিক কোম্পানিগুলা লিপস্টিকে চিনি মেশানো শুরু করবে\uD83D\uDE02\uD83D\uDE02\uD83D\uDC8B"));
        arrayList2.add(new Smsinformation(2,"3","","আজকে একটা\uD83D\uDE33 ভালা উপদেশ দেয়\uD83D\uDE0C \uD83D\uDC47 শুন মেয়েরা \uD83D\uDC6D ছেলেদের \uD83D\uDC6C সাথে একদম তর্কে জড়াবেনা\uD83D\uDE12\uD83D\uDE12 কারন ছেলেরা বুঝে কম\uD83D\uDE02চিল্লায় বেশি\uD83D\uDE21\uD83D\uDE1E"));
        arrayList2.add(new Smsinformation(2,"4","","\uD83D\uDC49\uD83D\uDC49কারনে অকারণে হাহা \uD83D\uDE06দেয়া প্রানি গুলোর জন্য আমার প্রচুর মায়া হয়\uD83D\uDE42কারন ছোট বেলা থেকেই মানুষিক রোগিদের জন্য আমার একটা সফ্ট কর্নার কাজ করে"));
        arrayList2.add(new Smsinformation(2,"5","","আপ্নারা কি জানেন? চশমা পড়া ছেলে গুলা এক একটা লুচুর গোডাউন হয়!\uD83D\uDE37 আসলে চশমা পড়ে ভালো হওয়ার ভং ধরে থাকে তারা।\uD83D\uDE1C"));
        arrayList2.add(new Smsinformation(2,"6","","\uD83D\uDC49যে মেয়েগুলো কথায় কথায় রাগ দেখায়\uD83D\uDE21\uD83D\uDE21বিয়ের পর একটা সময় তারা জামার থাপ্পেড়\uD83D\uDC4B\uD83D\uDC4Bখেয়ে ঠিক হয়ে যায়\uD83D\uDE01\uD83D\uDE06\uD83D\uDE1C"));
        arrayList2.add(new Smsinformation(2,"7","","প্রেমিকারা\uD83D\uDE18 \uD83D\uDC49\uD83D\uDC49আর কিছু পারুক না পারুক\uD83D\uDE12হুদাই প্রেমিকদের সন্দেহ করতে ঠিকই পারে\uD83D\uDE0F"));
        arrayList2.add(new Smsinformation(2,"8","","জীবনে যদি আসলেই সুখী হতে চান,\uD83D\uDE0A\uD83D\uDE0A\uD83D\uDE0A তবে কারোর প্রতি\uD83D\uDC95 আর কোনো কিছুর উপর প্রত্যাশা করা ছেড়ে দেন।\uD83D\uDE42\uD83D\uDE04 অনেক সুখী থাকবেন গ্যারান্টিড।\uD83D\uDE0E\uD83D\uDE0C☑"));
        arrayList2.add(new Smsinformation(2,"9","","আপ্নারা কি জানেন? চশমা পড়া ছেলে গুলা এক একটা লুচুর গোডাউন হয়!\uD83D\uDE37 আসলে চশমা পড়ে ভালো হওয়ার ভং ধরে থাকে তারা।\uD83D\uDE1C"));
        arrayList2.add(new Smsinformation(2,"10","","সমবয়সী রিলেশনশিপ গুলি অনেক কিউট হয়\uD83D\uDE31\uD83D\uDE0D\uD83D\uDE0D যদিও বিয়ের ব্যাপারে ..... \uD83D\uDE1E\uD83D\uDE2D\uD83D\uDE2D\uD83D\uDE2D"));
        arrayList2.add(new Smsinformation(2,"10","","ওরা কারা!?!\uD83D\uDE0F\uD83D\uDE0F\uD83D\uDE0F যারা রাতে ডাটা অন\uD83D\uDC49\uD83D\uDCF1 করে লেখাপড়া করে,\uD83D\uDCDA\uD83D\uDCD2\uD83D\uDD8A আর সবাইকে বোকা বানায়???\uD83D\uDE12\uD83D\uDE02\uD83E\uDD23 ওরা আমি,\uD83D\uDE33\uD83D\uDE1Cতবে মাঝেমাঝে।\uD83D\uDE04\uD83D\uDE0C"));
        arrayList2.add(new Smsinformation(2,"10","","\uD83D\uDC49সিনেমায় নায়ক-নায়িকারা\uD83D\uDC8Fবৃষ্টিতে ভিজলে☔\uD83D\uDCA6গান হয় রোমান্স হয়\uD83D\uDE18অারো কতো কিছু হয়\uD83D\uDE0D\uD83D\uDE0Fঅার অামরা ভিজলে\uD83D\uDE0Cজ্বর,ঠান্ডা,কাশি হয়\uD83D\uDE29\uD83D\uDE22"));
        arrayList2.add(new Smsinformation(2,"11","","\uD83D\uDC49কাউকে অন্ধের মতো\uD83D\uDE48বিশ্বাস করা মানে নিজেকে নিজেই বাশ\uD83C\uDF8Bদেয়া"));
        arrayList2.add(new Smsinformation(2,"12","","ডিচিশন আধা আধা\uD83D\uDE02\uD83D\uDE1C বিয়া করলে কালা \uD83D\uDE33 পোলারেই করমো\uD83D\uDE0D যাতে ঝগড়া লাগলে\uD83D\uDE21 কাইল্লা বলে খোটা দিতে পারি\uD83D\uDE02\uD83D\uDE12"));
        arrayList2.add(new Smsinformation(2,"13","","একমাত্র মেয়েরাই পারে \uD83D\uDE31 অবলা ছেলেদের \uD83D\uDE02 সহজ সরল মনটা কে ফুটবল ⚽ ভেবে খেলা করতে\uD83D\uDE12\uD83D\uDE1C"));
        arrayList2.add(new Smsinformation(2,"14","","\uD83D\uDC49মেয়েরা\uD83D\uDC69\u200D❤️\u200D\uD83D\uDC8B\u200D\uD83D\uDC69পড়া-লেখা করে বিয়ে অাটকানোর জন্য\uD83D\uDE0E\uD83D\uDE1Bঅার ছেলেরা\uD83D\uDC6Cপড়া-লেখা করে বিয়া করার জন্য\uD83D\uDE31"));
        arrayList2.add(new Smsinformation(2,"15","","ফপৃথিবীতে \uD83C\uDF0E DSLR নামক যন্ত্রটা আছে বলে\uD83D\uDE31\uD83D\uDCF7 ছেলেরা \uD83D\uDE32 বাস্তবে আক্কাস আলী থেকে\uD83D\uDE2C\uD83D\uDE2C ফেবুতে এসে ঝাক্কাস আলী হয়ে যায়\uD83D\uDE02\uD83D\uDC4C সবি DSLR এর কেলমা\uD83D\uDE02\uD83D\uDE12"));
        arrayList2.add(new Smsinformation(2,"16","","ভালো আছি আমি\uD83D\uDE42\uD83D\uDC48 এটা একটা দাহা মিথ্যা কথা।\uD83D\uDE10\uD83D\uDE15 আর\uD83D\uDE0F আমি তোমাকে ছাড়া বাঁচব না\uD83D\uDC48\uD83D\uDE02 এটা হলো প্রেম\uD83D\uDC98 ঘটিত মিথ্যা কথা। \uD83D\uDE01\uD83D\uDE1C"));
        arrayList2.add(new Smsinformation(2,"17","","সেই মানুষটিকেই\uD83D\uDE0C সারাজীবন ভরসা করা যায়\uD83D\uDE07\uD83D\uDE0D যারা খুব বিপদে\uD83D\uDE30,অসময়ে\uD83D\uDC94 ভালোবেসে পাশে থাকে\uD83D\uDC6B\uD83D\uDE07\uD83D\uDE03"));
        arrayList2.add(new Smsinformation(2,"18","","দুজন মানুষ কথা বলছে। আপনি তাদের কথার মাঝখানে কথা বলার চেষ্টা করছেন, কিন্তু তারা পাত্তা দিচ্ছে না। এর চেয়ে বিরক্তিকর আর কিছু হতে পারে না। - মার্ক টোয়েন"));
        arrayList2.add(new Smsinformation(2,"20","","স্বপ্নেও যদি আমাকে মারার কথা ভাবো, ঘুম থেকে উঠে অবশ্যই ক্ষমা চেয়ে নিয়ো। - মোহাম্মদ আলী"));
        arrayList2.add(new Smsinformation(2,"21","","পত্রিকায় নিজের ছবি দেখতে ইচ্ছা হলে আমি আমার চুলের স্টাইল বদলে ফেলি! - হিলারি ক্লিনটন"));
        arrayList2.add(new Smsinformation(2,"22","","আমি সব সময় দেরি করে অফিসে যাই। তবে তাড়াতাড়ি অফিস থেকে বের হয়ে সেটা পুষিয়ে দিতে চেষ্টা করি। - চার্লস ল্যাম্ব"));
        arrayList2.add(new Smsinformation(2,"23","","মহাবিশ্ব আর মানুষের নির্বুদ্ধিতা, দুটাই অসীম। মহাবিশ্বের ব্যাপারে আমি অবশ্য এখনো নিশ্চিত নই। - আলবার্ট আইনস্টাইন"));
        arrayList2.add(new Smsinformation(2,"24","","আমি তোমার গাড়ির ব্রেক ঠিক করতে পারিনি, তাই হর্নটাকে জোরালো করে দিয়েছি। - স্টিভেন রাইট"));
        arrayList2.add(new Smsinformation(2,"25","","ফঘরের কাজ খুবই বিরক্তিকর। বিছানা গোছাও, থালাবাসনও ধোও, ছয় মাস পর পর এসব কাজ করতে ভালো লাগে? - জোয়ান রিভার্স"));
        arrayList2.add(new Smsinformation(2,"26","","নিরাশাবাদী মানুষের কাছ থেকে ধার করাই সবচেয়ে উত্তম। কারণ, সে টাকা ফিরে পাওয়ার আশা করবে না। - অস্কার ওয়াইল্ড"));
        arrayList2.add(new Smsinformation(2,"27","","প্রত্নতত্ত্ববিদেরা স্বামী হিসেবে সবচেয়ে ভালো। যতই বুড়ো হবেন, আপনার প্রতি তাঁর আগ্রহ তত বাড়বে! - আগাথা ক্রিস্টি"));
        arrayList2.add(new Smsinformation(2,"28","","আমরা ছিলাম ছয় ভাই। তাই আমি নাচতে শিখেছি বাথরুমের দরজার সামনে দাঁড়িয়ে। - বব হোপ"));
        arrayList2.add(new Smsinformation(2,"29","","হিরার নিজস্ব কোন খনি নেই। হিরার জন্ম কয়লার খনিতে । কিন্তু তাই বলে হিরা কখনো কয়লার দামে বিক্রি হয়না এবং কেও হিরাকে কয়লাও বলেনা সহস্র বা লাখো টন কয়লা উত্তোলনের পরেই শুধু হিরার সন্ধান মিলে। তোমার জন্ম যেখানেই হোক, কর্মগুনেই তোমাকে বড় হতে হবে ।"));
        arrayList2.add(new Smsinformation(2,"30","","কখনো আপনি জিতবেন...কখনো শিখবেন"));
        arrayList2.add(new Smsinformation(2,"31","","কারো উপরে প্রতিশোধ নেয়ার সবচাইতে ভালো উপায় হচ্ছে নিজে হ্যাপি থাকা। কারন আপনি তার চেয়ে বেশি সুখে আছেন, এর চেয়ে বড় যন্ত্রনা আপনার অপছন্দের মানুষটির জন্য আর কিছুই হতে পারেনা। প্লিজ, রাগ বা অভিমানের বশে নিজের ক্ষতি করবেন না, হাত পা কাটাকাটি করবেন না, সুইসাইড এটেম্পট নিবেন না।"));
        arrayList2.add(new Smsinformation(2,"32","","“প্রত্যেককে বিশ্বাস করা বিপদজনক, কিন্তু কাউকে বিশ্বাস না করা আরো বেশী বিপদজনক - আব্রাহাম লিংকন।"));
        arrayList2.add(new Smsinformation(2,"33","","‘‘যারা আমাকে সাহায্য করতে মানা করে দিয়েছিল আমি তাদের প্রতি কৃতজ্ঞ, কারন তাদের ‘না’ এর জন্যই আজ আমি নিজের কাজ নিজে করতে শিখেছি।’’"));
        arrayList2.add(new Smsinformation(2,"34","","দুর্ভাগ্যবান তারাই যাদের প্রকৃত বন্ধু নেই। - অ্যারিস্টটল যে নিজেকে অক্ষম ভাবে, তাকে কেউ সাহায্য করতে পারে না। -জন এন্ডারসন"));
        arrayList2.add(new Smsinformation(2,"35","","আমি ব্যর্থতা কে মেনে নিতে পারি কিন্তু আমি চেষ্টা না করাকে মেনে নিতে পারিনা। - মাইকেল জর্ডান"));
        arrayList2.add(new Smsinformation(2,"36","","অসহায়কে অবজ্ঞা করা উচিত নয়, কারণ মানুষ মাত্রেই জীবনের কোন না কোন সময় অসহায়তার শিকার হবে"));
        arrayList2.add(new Smsinformation(2,"37","","\uD83D\uDC49\uD83D\uDC49কারনে অকারণে হাহা \uD83D\uDE06দেয়া প্রানি গুলোর জন্য আমার প্রচুর মায়া হয়\uD83D\uDE42কারন ছোট বেলা থেকেই মানুষিক রোগিদের জন্য আমার একটা সফ্ট কর্নার কাজ করে"));
        arrayList2.add(new Smsinformation(2,"38","","ওরা কারা!?!\uD83D\uDE0A\uD83D\uDE0F\uD83D\uDE0D যারা অন্য কারোর কান্না দেখলে,\uD83D\uDE1F\uD83D\uDE13 নিজেও হুট করে কেঁদে ফেলে।\uD83D\uDE22\uD83D\uDE2D ওরা আমি\uD83D\uDE33\uD83D\uDE28"));

        /**
         * 3
         */

        arrayList3.add(new Smsinformation(3,"1","","সবাই রাতে দেয়, কেও সময় পেলে দিনেও দেয়, \n" +
                "টানা ১ ঘন্টা আবার ২ ঘন্টা ও দেয়, কেও কেও সারা রাত দেয়, \n" +
                "কেও আবার সকালেও দেয়! \n" +
                "দেওয়ার সময় পুরা গরম হইয়া যায় । .\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                "এভাবেই সবাই মোবাইল চার্জ দেয়! হে হে হে ।\"\n"));
        arrayList3.add(new Smsinformation(3,"2","",
                "\n" +
                        " \n" +
                        "\n" +
                        "অনেক মেয়ে \"মুলা\" দিয়ে করে, \n" +
                        "আবার অনেক মেয়ে \"গাজর\" দিয়ে ও করে, \n" +
                        "আবার অনেক মেয়ে \"শষা\" দিয়ে ও করে। \n" +
                        "আবার সব কিছু একসাথে দিয়ে ও করে। \n" +
                        "কি করে জানো ?? \n" +
                        ".\n" +
                        ".\n" +
                        ".\n" +
                        ".\n" +
                        ".\n" +
                        ".\n" +
                        "আরে সালাদ তৈরী করে"));
        arrayList3.add(new Smsinformation(3,"3","","সে আসলো, আমার উপর বসলো, \n" +
                "আমাকে জড়িয়ে ধরলো, পরে কামর, চুমু দিল। \n" +
                "তারপর নিজের প্রয়োজন মিটিয়ে চলে গেল।\n" +
                "খারাপ চিন্তা ভাবনা বাদ দিয়ে ভালো চিন্তা ভাবনা কর।\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ". \n" +
                ".\n" +
                "ঐটা একটা মশা ছিল।"));
        arrayList3.add(new Smsinformation(3,"4","","যখন তোমার একা লাগবে, \n" +
                "তুমি চারদিকে কিছুই দেখতে পাবে না, \n" +
                "দুনিয়া টা ঝাপসা হয়ে আসবে। \n" +
                "তখন তুমি আমার কাছে এসো। \n" +
                ".\n" +
                ".\n" +
                "তোমাকে চোখের ডাক্তার দেখাবো।\n" +
                "\n" +
                " "));
        arrayList3.add(new Smsinformation(3,"5","","ফুলের মাঝে ভ্রমর আসে, \n" +
                "নদীর ওপর নৌকা ভাসে, \n" +
                "শিশির নাচে সবুজ ঘাসে, \n" +
                "রাতের মাঝে জোছনা হাসে। \n" +
                ".\n" +
                ".\n" +
                "আর কিছু মেয়েদের ভালোবাসায় ফরমালিন আছে।"));
        arrayList3.add(new Smsinformation(3,"6","","অদ্ভুত কিছু আবেগ, অজানা কিছু অনুভূতি। \n" +
                "অসম্ভব কিছু ভালো লাগা, হয়তো বা কষ্টের ভয়, \n" +
                "একাকীত্ব নিরবতা। \n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                "এই নিয়ে আমাদের টয়লেটে বসে থাকা।"));
        arrayList3.add(new Smsinformation(3,"7","","যেখানে ভালোলাগা, সেখানেই ভালোবাসা। \n" +
                "যেখানে ভালোবাসা, সেখানেই প্রেম। \n" +
                "যেখানে প্রেম, সেখানেই ব্যাথা। \n" +
                "আর যেখানে ব্যাথা, সেখানেই টাইগার বাম মলম।"));
        arrayList3.add(new Smsinformation(3,"8","","কি দিন আইছে রে, বাতাস বইতেছে, \n" +
                "পাখি গান গাইতেছে, গরু ঘাস খাইতেছে, \n" +
                "জিনিয়াসরা এস.এম.এস করতাছে, \n" +
                "আর আবালটায় এস.এম.এস পড়তাছে।\n"));
        arrayList3.add(new Smsinformation(3,"9","","এক দিন তোমার জীবনে একটি সুন্দর মেয়ে আসবে। \n" +
                "সে তোমাকে ভালোবাসবে KISS করবে। \n" +
                "আবার তোমাকে জড়িয় ধরে বলবে \n" +
                ",\n" +
                ",\n" +
                ",\n" +
                ",\n" +
                ",\n" +
                ",\n" +
                ",\n" +
                ",\n" +
                ",\n" +
                ",\n" +
                ",\n" +
                "আব্বু আমাকে একটা চকলেট কিনে দাও।"));
        arrayList3.add(new Smsinformation(3,"10","","জল পড়ে পাতা নড়ে।মহা গাঁধা এসএমএস পড়ে।\n" +
                "ওরে গাঁধা রাগিস না। বোকার মতো হাসিস না।\n" +
                "এই এসএমএস পড়বি যত, বুদ্ধি হবে গাঁধার মতো।\n" +
                "জোকস ১৯০\n" +
                "\n" +
                "\n" +
                "\n" +
                "ছেলের দু’দিন পর পরীক্ষা। অথচ পড়াশোনার নাম গন্ধ নেই। সারাদিন টইটই করে ঘুরে বেড়ায়। মা, ব্যাপারটা দেখে-\n" +
                "\n" +
                "মাঃ হাবলু, তোর না দু’দিন পরে পরীক্ষা! পড়াশোনা করছিস্\u200C না যে!\n" +
                "\n" +
                "হাবলুঃ মা পরীক্ষার এত চাপ- পড়ার সময়ই পাচ্ছি না!!"));
        arrayList3.add(new Smsinformation(3,"11","","চোখ বুজে দেখো স্বপ্ন দেখো কি না,\n" +
                "পা বাড়িয়ে দেখো পথ খুজে পাও কি না, \n" +
                "মন বাড়িয়ে দেখো কেউ ভালোবাসে কি না, \n" +
                "হাত বাড়িয়ে দেখো \n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                "কেউ পয়সা দেয় কিনা।"));
        arrayList3.add(new Smsinformation(3,"12","","একটি ছাগলের চারটি বাচ্চা হয়েছে। একটি বাচ্ছা তার মাকে জিগাসা করল, মা আমার বাবা কোথায়? ছাগলটা বল্ল চুপ কর তোর বাবা এখন SMSপরছে।"));
        arrayList3.add(new Smsinformation(3,"13","","এক বছর পর দেখলাম, তারপর ধরলাম,\n" +
                "ভালো লাগল একটু টিপলাম,\n" +
                "নরম লাগল তারপর একটু \n" +
                "চুষে দিলাম মজা লাগল। \n" +
                "তাইতো বলি বছরের প্রথম \n" +
                "পাকা আমের স্বাদ-ই আলাদা"));
        arrayList3.add(new Smsinformation(3,"14","","এই চলোনা ওই দিকে নির্জনে যাই Plz না বলোনা।\n" +
                "আরে এত করে বলছি তাও যাবে না ? \n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".ওই বেটা না গেলে বল অন্য রিকশা ডাকি।"));
        arrayList3.add(new Smsinformation(3,"15","","নারী তুমি করিওনা রুপের বড়াই, সবাইতো জানে তোমার প্রিয় বনধু রান্না ঘরের কড়াই। যতই দেখাও তুমি রুপের ঝর্ণা,করতে হবে তোমাকে দরকারি রান্না.."));
        arrayList3.add(new Smsinformation(3,"16","","কপাল আর লুঙ্গীর মধ্যে মিল কোথায়? দুটোই যেকোনো সময় খুলে যেতে পারে !কপাল খুললে পৌষ মাস,আর লুঙ্গী খুললে সর্বনাশ।"));
        arrayList3.add(new Smsinformation(3,"17","","আম গাছে আম ধরে নারিকেল গাছে ঢাব ছেলেদেরকে মিসকল মারা মেয়েদের সভাব গাছের বল লতাপাতা মাছের বল পানি এ যুগের মেয়েরা চায় পঁয়সাওয়ালা স্বামী,.,"));
        arrayList3.add(new Smsinformation(3,"18","","চরম ছড়াঃ \n" +
                "ছোট ছোট ছেলে- মেয়ে প্রেমে পড়েছে । পার্কে গিয়ে তারা আবার ধরা খেয়েছে । কে দেখেছে কে দেখেছে টিচার দেখেছে । এবার বলো টিচার কেন .......... পার্কে গিয়েছে...........??????"));
        arrayList3.add(new Smsinformation(3,"20","","মেয়ে: \n" +
                "-তুমি একটা বদ।\n" +
                "\n" +
                "ছেলে: \n" +
                "-তুমি কি ভালো?\n" +
                "\n" +
                "মেয়ে:\n" +
                "-হ্যা আমি ভালো।\n" +
                "\n" +
                "ছেলে: \n" +
                "-তার মানে তুমি বদ না?\n" +
                "\n" +
                "মেয়ে: \n" +
                "-হ্যা, আমি বদ না।\n" +
                "\n" +
                "ছেলে: \n" +
                "-RFL বদনা?\n" +
                "\n" +
                "মেয়ে: \n" +
                "-না, মানে আমি বদ না।\n" +
                "\n" +
                "ছেলে: \n" +
                "সেটাই তো বললাম তুমি RFL বদনা।"));
        arrayList3.add(new Smsinformation(3,"21","","আপনে একটা গরু না, একটা ছাগল, না একটা ভেড়া. না না না বাজার থেকে একটা দেশী মুগরী কিনে আমাকে দাওয়াত দিয়ে খাওয়াবেন।"));
        arrayList3.add(new Smsinformation(3,"22","","তুমি বীর , তুমি দুর্জয়, তুমি বাঙ্গালি, তুমি সাহসী, তোমার বুকে অনেক জোর, তুমি আমাদের গ্রাম' এর মুরগী চোর!"));
        arrayList3.add(new Smsinformation(3,"23","","আমি ইট, তুমি খোয়া; আমি খই, তুমি মোয়া। আমি ফুল, তুমি কাঁটা; আমি গম, তুমি আটা। আমি নৌকা, তুমি ব্রীজ; আমি মাছ, তুমি ফ্রিজ। আমি রাত, তুমি ভোর; আমি ভালো, তুমি চোর। আমি বৃক্ষ, তুমি ফল; আমি নদী, তুমি জল। আমি মেঘ, তুমি বৃষ্টি; আমি চক্ষু, তুমি দৃষ্টি। আমি গুল্ম, তুমি তরু; আমি চতুর, তুমি ভীরু। আমি বধির, তুমি বোবা; আমি সাগর, তুমি ডোবা আমি খাতা, তুমি কলম; আমি ট্যাবলেট, তুমি মলম। আমি কান্না, তুমি হাসি; আমি টাটকা, তুমি বাসি। আমি বিষন্ন, তুমি হতাশা; আমি কদমা, তুমি বাতাসা। আমি কাঁথা, তুমি বালিশ আমি ব্যথা । তুমি মালিশ। আমি হাত, তুমি পাও; আমি নগদ, তুমি ফাও........"));
        arrayList3.add(new Smsinformation(3,"24","","তুমি যমুনা হলে হব.. অামি যমুনা ব্রিজ.. তুমি চায়ের কাপ হলে হব চায়ের পিরিচ .. তুমি জীবন হলে হব অামি প্রেম.. তুমি দরজা হলে হব অামি দরজার ফ্রেম।।।।।।।.."));
        arrayList3.add(new Smsinformation(3,"25","","ওরে মন কথা শোন, যাবি চলে বান্দরবন, বানরের মত সবাই ঝুলবি নাকি বল? ওরে বাঁচাও আমায়, একটা বানর আমার পিছু নিয়েছে। সেই বানরতা এস এম এস পড়তেছে।"));
        arrayList3.add(new Smsinformation(3,"26","","আমি চোর!আমার বাপ চোর!আমার দাদা চোর!আমার নানা চোর!আমার ১৪ গোষ্টি চোর!\n" +
                "\n" +
                "\"আরে বোকা আস্তে পড়ো।মানুষশুনলে তোমাকে পিটাবে !! !"));
        arrayList3.add(new Smsinformation(3,"27","","রান্না ঘরে রান্না করিচোখে লাগে ধোঁয়াএমন সময় বন্ধু এসেগালে দিল চুমা !!"));
        arrayList3.add(new Smsinformation(3,"28","","আমাদের ভালবাসায় হয়ে গেল ঘাস।খেয়া গেল গরু দিয়ে গেল বাশঁ।"));
        arrayList3.add(new Smsinformation(3,"29","","এল শীত তুমার দারে,, একলা তুমি থাক নারে… সাথে রাখ শুধু তারে,, ভালবাস তুমে যারে… এখন শুধু তার কাম,, জানি আমি তার নাম… সে তুমার সম্বল,, তার নাম কম্বল…"));
        arrayList3.add(new Smsinformation(3,"30","","অফিসের বড় কর্তা ও মন্টুর মধ্যে কথা হচ্ছে—\n" +
                "বড় কর্তা: আচ্ছা আপনি আগের চাকরিটা ছেড়ে দিলেন কেন?\n" +
                "মন্টু: অসুস্থতার জন্য, স্যার।\n" +
                "বড় কর্তা: তা কী হয়েছিল আপনার?\n" +
                "মন্টু: আরে আমার তো কিছুই হয়নি। ওই অফিসের বড় কর্তাই তো আমার কাজে অসুস্থ হয়ে পড়েছিলেন, মানে তার প্রায় মাথা খারাপ হওয়ার জোগাড় হয়েছিল স্যার।\n"));
        arrayList3.add(new Smsinformation(3,"31","","অফিসে সিদ্দিক সাহেবের প্রথম দিন।\n" +
                "বস: আমাদের অফিসে একটি ব্যাপারে আমরা খুবই গুরুত্ব দিই, তা হলো পরিষ্কার-পরিচ্ছন্নতা। তুমি নিশ্চয়ই আমার ঘরে ঢোকার আগে পাপোশে জুতা মুছে ঢুকেছ?\n" +
                "সিদ্দিক: অবশ্যই স্যার।\n" +
                "বস: আরেকটি ব্যাপারে আমরা আরও বেশি কঠোর। তা হলো সততা। দরজার বাইরে কোনো পাপোশ নেই, ইডিয়ট!"));
        arrayList3.add(new Smsinformation(3,"32","","অফিসের নতুন বড়কর্তা কাজের ব্যাপারে খুব কড়া। কাউকে একবিন্দু ছাড় দেন না। চাকরির প্রথম সপ্তাহেই একদিন খেপে গেলেন তিনি। রেগেমেগে রুম থেকে বেরিয়েই এক লোককে পাকড়াও করলেন। অফিসের সবার সামনে চিৎকার করে বললেন, ‘সপ্তাহে কত টাকা মাইনে পাও তুমি, শুনি?’\n" +
                "লোকটা ভয়ে কাঁপতে কাঁপতে বলল, ‘৩০০০ টাকা’।\n" +
                "বড়কর্তা তাঁর মুখের ওপর ৩০০০ টাকা ছুড়ে দিয়ে বললেন, ‘এই নাও তোমার এ সপ্তাহের মাইনে, আর বেরিয়ে যাও।’\n" +
                "লোকটা বেরিয়ে যাওয়ার পর বললেন বড়কর্তা, ‘প্রয়োজন হলে এভাবেই অফিসের প্রত্যেককে বের করে দেব আমি। যাই হোক, ওই লোকটা আমাদের অফিসে কী কাজ করে?’\n" +
                "কর্মচারীদের একজন বলল, ‘স্যার, ও আমাদের এখানে পিজা ডেলিভারি দেয়!’"));
        arrayList3.add(new Smsinformation(3,"33","","\n" +
                "অফিসের বস কর্মচারীদের বললেন, ‘আজ আমার মনটা বেশ ভালো। বলো, তোমাদের কী দাবিদাওয়া। আজ সব শুনব।’\n" +
                "এক কর্মচারী বললেন, ‘স্যার, আমরা ছুটি খুবই কম পাই। ছুটি একটু বাড়িয়ে দেওয়া যায় না?’\n" +
                "বস: কী রকম ছুটি চাও, বলো?\n" +
                "কর্মচারী: ছয় মাসের ছুটি, বছরে দুবার!"));
        arrayList3.add(new Smsinformation(3,"34","","অফিসের এক কর্মকর্তা বড় কর্তার সেক্রেটারির সঙ্গে অফিসে বসেই চুটিয়ে প্রেম করে যাচ্ছিলেন। একদিন বড় কর্তার কাছে হাতেনাতে ধরা পড়ে গেলেন তাঁরা। তিনি রেগে বললেন, ‘আপনারা কাজ না করে অফিসে কী শুরু করেছেন এসব? এটাই কি সেই কাজ যার জন্য প্রতি মাসে আমি আপনাদের বেতন দিই?’\n" +
                "‘না, স্যার। এ নিয়ে আপনাকে চিন্তা করতে হবে না। অফিসে হলেও এই কাজ কিন্তু আমরা কোনো ধরনের বেতন ছাড়াই করছি, স্যার।’—কর্মকর্তার জবাব।"));
        arrayList3.add(new Smsinformation(3,"35","","অফিসের বড় কর্তা তাঁর অফিস সহকারীকে ডেকে বললেন, ‘আমার জন্য খুব ভালো দেখে একটি আয়না নিয়ে আসো। এমন আয়না আনবে যে আয়নাতে আমার চেহারাটা বেশ ভালো দেখা যায়।’\n" +
                "বড় কর্তার কথা শুনে অফিস সহকারী আয়না কিনতে গেলেন। ঘণ্টা খানেক পর অফিস সহকারীর খালি হাতে ফিরে আসা দেখে বড় কর্তা রেগে বললেন, ‘কী, একটা আয়নাও কিনতে পারো না। বেকুব কোথাকার!’\n" +
                "‘আমি সব দোকানেই খোঁজ করেছি। আয়না অনেক আছে কিন্তু কোনো আয়নাতেই তো আপনার চেহারা দেখা যায় না। যে আয়নাতেই তাকাই, আমার চেহারা দেখা যায়। ওই আয়না নিয়ে এলে তো আপনি রেগে যেতেন, স্যার।\n"));
        arrayList3.add(new Smsinformation(3,"36","","\n" +
                "অফিসে দেরি করে এসেছে দেখে কর্মচারীকে ডেকে পাঠালেন বস, ‘আপনার এখানে নয়টার সময় আসা উচিত ছিল।’\n" +
                "কর্মচারী জবাব দেয়, ‘কেন, স্যার? নয়টার সময় কিছু হয়েছিল বুঝি?’"));
        arrayList3.add(new Smsinformation(3,"37","","অফিসের পরিচালক নতুন কর্মচারীকে রাগ করে বলছেন, আমাকে আপনি কী ভেবেছেন, আমি কি গাধা?\n" +
                "–সেটা আমি জানব কী করে! আমি তো নতুন এসেছি।\n"));
        arrayList3.add(new Smsinformation(3,"38","","অনেক দিন পর বাড়িতে একজন মেহমান এসেছে। মেহমান দেখে বাড়ির মালিক বললেন, তা কেমন আছেন, অনেক দিন পর এলেন, আজ তো আর থাকবেন না, আবার কবে আসবেন?\n" +
                "মেহমান : অনেক দিন পর এলাম, যেতে তো আর দিবেন না, লুঙিটা দিন গোসলটা সেরেই আসি।"));
        arrayList3.add(new Smsinformation(3,"39","","অফিস থেকে বাড়ি ফিরে স্বামী বলল, ‘শুরু করার আগে ভাতটা দাও, খেয়ে নিই।' স্ত্রী ভাত বেড়ে দিল। ভাত খেয়ে স্বামী ড্রয়িংরুমের সোফায় বসতে বসতে বলল, ‘শুরু করার আগে এক গ্লাস পানি দাও -- বড্ড তেষ্টা পেয়েছে।' স্ত্রী পানি দিয়ে গেল। পানি খেতে খেতে স্বামী বিছানায় গিয়ে শুয়ে পড়ল। তারপর বলল, ‘শুরু করার আগে এক কাপ চা দাও না আমাকে।' এইবার স্ত্রী গেল খেপে, ‘অ্যাই, পেয়েছ কী তুমি আমাকে, আমি তোমার চাকর? অফিস থেকে ফিরে একটার পর একটা খালি অর্ডার মেরেই যাচ্ছ...নির্লজ্জ, অসভ্য, ছোটলোক, স্বার্থপর...' স্বামী কানে তুলা গুঁজতে গুঁজতে বলে, ‘এই যে...শুরু হয়ে গেল।"));
        arrayList3.add(new Smsinformation(3,"40","","অনেক দিন পর হোস্টেল থেকে বাড়ি ফিরেছে মৌ। ফ্রিজ খুলে সে দেখে, ফ্রিজের ভেতর ভীষণ সুশ্রী একটি মেয়ের ছবি রাখা।\n" +
                "\n" +
                "মৌ ছুটে গেল মায়ের কাছে, ‘মা, ফ্রিজের ভেতর একটা সুন্দরী মেয়ের ছবি রাখা দেখলাম।’\n" +
                "\n" +
                "মা: হুম্। এটাকে বলে ‘পিকচার ডায়েট’। যখনই আমি কোনো খাবার নেওয়ার জন্য ফ্রিজ খুলি, মেয়েটাকে দেখলেই আমার মনে হয়, আমাকেও ওর মতো সুন্দরী হতে হবে। তখন আর খাওয়া হয় না।\n" +
                "\n" +
                "মৌ: বাহ্! দারুণ। তা উপকার পাচ্ছ?\n" +
                "\n" +
                "মা: পাচ্ছি আবার পাচ্ছি না।\n" +
                "\n" +
                "মৌ: কেমন?\n" +
                "\n" +
                "মৌ: আমার ওজন কমেছে আট কেজি। কিন্তু বারবার ফ্রিজ খোলার কারণে তোর বাবার ওজন ১০ কেজি বেড়েছে!"));
        arrayList3.add(new Smsinformation(3,"41","","এক বৃদ্ধা পাশের বাড়ির মহিলার কাছে তাঁর মেয়ের জামাই আর পুত্রবধূর কথা বলছিলেন।\n" +
                "\n" +
                "- মেয়ের জামাই আমার খুবই ভালো। প্রতি সকালে মেয়ের জন্য নাশতা বানিয়ে বিছানায় নিয়ে আসে। অফিস থেকে ফিরে আবার রান্নাঘরে ঢোকে। রাতে বিছানার মশারিটাও টানায় জামাই। জামাই আমার মেয়েকে বড় সুখে রেখেছে।\n" +
                "\n" +
                "- আর ছেলের বউ কেমন?\n" +
                "\n" +
                "- ব"));
        arrayList3.add(new Smsinformation(3,"12","","অনেকদিন বাদে জামাই আসছে তাই  শাশুড়ি ভালো ভালো রান্না করছে। পোলাও, মাংস, রুই মাছ, কোপ্তা, কালিয়া, দই,বেগুন ভাজি এবং পাটশাক।\n" +
                "তো শাশুড়ি প্রথমে জামাইর  প্লেটে একগাদা পাটশাক তুলে দিল। জামাই তাড়াতাড়ি সেটুকু খেয়ে ফেলল।\n" +
                "এদেখে শাশুড়ি বলে উঠলেন  বাবা তোমার বুঝি পাট শাকটা খুব ভালো লেগেছে,আরেকটু দেই?\n" +
                "বলতে বলতে আরেক গাদা পাটশাক জামাইয়ের প্লেটে তুলে দিলেন তিনি। জামাই একটু  মনক্ষুণ্ন হল। খাওয়ার এতো আইটেম; বড় বড় মাংসের টুকরা, মাছের পেটি তাকে হাতছানি দিয়ে ডাকছে। এখন তো শুধু  পাটশাক খেয়েই পেটভরে গেল। জামাই ঐটুকুও  খেয়ে শেষ করতেই শাশুড়ি বললেন, বাবা আরো একটু দেব?\n" +
                "জামাই তখন  খাওয়া ছেড়ে উঠে দাঁড়িয়ে বলল,আম্মা আপনার  আর কষ্ট করে প্লেটে শাক তুলে দেয়া লাগবেনা,\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                "পাট ক্ষেতটা দেখাইয়া দেন, আমি গিয়া খাইয়া আসি।"));
        arrayList3.add(new Smsinformation(3,"13",""," আমাদের বস নাকি মারা গেছেন?\n" +
                ": হ্যাঁ, হার্টফেল করে মারা গেছেন।\n" +
                ": কী দুর্ভাগ্য! সারা জীবন পাসের পর পাস করে, মরার সময় ফেল করে মরলেন!\n" +
                "\n" +
                "\n" +
                ": আপনি তা হলে বলতে চাচ্ছেন যে আপনার ভাই আপনার মাথায় বেলচার বাড়ি মেরেছে ?\n" +
                ": জি, দারোগা সাব। আপনি অরে গেরেপ্তার করেন।\n" +
                ": কিন্তু আপনার মাথায় তো কোনো আঘাতের চিহ্ন দেখতে পাচ্ছি না ।\n" +
                ": যে বেলচাটা দিয়া ও আমার মাথায় মারছে সেইটার অবস্থা একবার দেইখা আসেন না ।"));
        arrayList3.add(new Smsinformation(3,"44","",": আপনি তা হলে বলতে চাচ্ছেন যে আপনার ভাই আপনার মাথায় বেলচার বাড়ি মেরেছে ?\n" +
                ": জি, দারোগা সাব। আপনি অরে গেরেপ্তার করেন।\n" +
                ": কিন্তু আপনার মাথায় তো কোনো আঘাতের চিহ্ন দেখতে পাচ্ছি না ।\n" +
                ": যে বেলচাটা দিয়া ও আমার মাথায় মারছে সেইটার অবস্থা একবার দেইখা আসেন না ।\n"));
        arrayList3.add(new Smsinformation(3,"45","","আফ্রিকার জঙ্গলে বেড়াতে গেছেন এক পর্যটক। বনের ভেতর হাঁটতে হাঁটতে গাইডের সঙ্গে কথা বলছিলেন তিনি।\n" +
                "পর্যটক: এখানে কোনো মানুষখেকো জংলি নেই তো?\n" +
                "গাইড: নাহ্। নেই।\n" +
                "পর্যটক: আপনি নিশ্চিত?\n" +
                "গাইড: অবশ্যই। কারণ শেষ যে মানুষখেকোটা ছিল, ওটাকে আমরা গত পরশু খেয়ে ফে"));
        arrayList3.add(new Smsinformation(3,"46","","আপনি কে?\n" +
                "গরীবের রাজা রবীন হুড।\n" +
                "আপনার ছেলে মেয়ে কয় জন?\n" +
                "ওরা এগার জন।\n" +
                "বড় ছেলে কি করে?\n" +
                "টপ-রংবাজ।\n" +
                "মেঝো ছেলে?\n" +
                "বিশ্ব প্রেমিক।\n" +
                "তার পরের জন?\n" +
                "সেয়ানা পাগল\n" +
                "ছোট ছেলে কি করে?\n" +
                "কুলি নাম্বার ওয়ান।\n" +
                "কোন জিনিসটা আপনার অপছন্দ?\n" +
                "হঠাৎ বৃষ্টি।\n" +
                "আপনার শ্বশুর সাহেব কি করে?\n" +
                "পদ্মা নদীর মাঝি।\n" +
                "আপনার স্ত্রী সম্পর্কে বলুন?\n" +
                "চাপা ডাঙ্গার বউ।\n" +
                "ভক্তদের উদ্দেশ্যে কিছু বলুন।\n" +
                "গরীব কেন কাঁদে?\n" +
                "ভক্তদের উদ্দেশ্যে কোন উপদেশ থাকলে বলুন।\n" +
                "মানুষ মানুষের জন্য।"));
        arrayList3.add(new Smsinformation(3,"47","",": আমাকে ১০০ টাকা ধার দেবে ?\n" +
                "\n" +
                "- দিতে পারি। কবে ফেরত দেবে ?\n" +
                "\n" +
                "- তিন দিন পর।\n" +
                "\n" +
                "- যদি না দাও তা হলে কিন্তু তোমার সাথে আর কোনদিন কথা বলবনা।\n" +
                "\n" +
                "- তা হলে ৫০০ টাকা দাও।\n" +
                "\n" +
                "\n" +
                "\n" +
                ": আপনার কুকুরটা আমাকে স্টেশন পর্যন্ত তাড়া করেছিল আজ? আমি আপনার নামে মামলা করব!\n" +
                "\n" +
                "- এ জন্য তো আপনার কৃতজ্ঞ থাকা উচিত।\n" +
                "\n" +
                "- কেন?\n" +
                "\n" +
                "- কুকুরটা তাড়া না করলে আপনি ট্রেন ধরতে পারতেন না।"));
        arrayList3.add(new Smsinformation(3,"48","","আমাদের পচাদা গেছে ডাক্তারের কাছে। গিয়ে বললো, ডাক্তারবাবু, আমার পেটে গ্যাসের অনেক সমস্যা। কিন্তু ভালো দিক এই যে আমার গ্যাসের গন্ধও হয় না, আওয়াজ ও হয় না। এখানে বসে আমি ১৫-২০ বার গ্যাস ছেড়েছি; কিন্তু কেউ টেরই পায় নি! ডাক্তারঃ এই ওষুধটা খান, আর এক সপ্তাহ পরে আসবেন। এক সপ্তাহ পর ... পচাদা ডাক্তারের কাছে গিয়ে প্রায় আর্তনাদ করে বলে উঠলো, এ কি ওষুধ দিলেন ডাক্তার সাহেব, আমার গ্যাসে এখনো আওয়াজ নেই; কিন্তু জঘন্য গন্ধ বের হয়! ডাক্তারবাবু গম্ভীরভাবে বললেন, গুড, আপনার নাক ঠিক হয়ে গেছে; এখন আপনার কানের চিকিৎসা করতে হবে!\n" +
                "\n" +
                " "));
        arrayList3.add(new Smsinformation(3,"49","",
                "আজিজ মিঞার কারখানায় আগুন লেগেছে। জলদি আগুন নেভাতে না পারলে সর্বনাশ হয়ে যাবে। আজিজ খবর দিলেন দমকল কর্মীদের।\n" +
                        "\n" +
                        "চট-জলদি হাজির হলো দমকল বাহিনী। কারখানার সামনের ছোট গলিটার দুপাশের দোকানগুলো ভেঙে, সদর দরজা গুঁড়িয়ে দিয়ে, দেয়াল ভেঙে সোজা অগ্নিকুণ্ডের স্থলে গিয়ে থামল গাড়ি! প্রচণ্ড ঝাঁকি খেয়ে গাড়ির পেছনে রাখা পানির টাংকিটা ছিটকে গিয়ে পড়ল আগুনে। ব্যস, নিভল আগুন।\n" +
                        "\n" +
                        "দমকল কর্মীদের তৎপরতা দেখে ভীষণ খুশি আজিজ। তিনি দমকল বাহিনীর প্রধানের হাতে ১০ হাজার টাকা পুরস্কার তুলে দিলেন। জিজ্ঞেস করলেন, ‘এই টাকা দিয়ে আপনারা কী করবেন বলুন তো?’\n" +
                        "\n" +
                        "দমকল বাহিনীর প্রধান: প্রথমেই গাড়ির ব্রেকটা ঠিক করাব!"));
        arrayList3.add(new Smsinformation(3,"50","","আবুল খুব অসুস্থ।ডাক্তার আবুলকে চেক-আপ করে আবুলের বউকে বাইরে ডেকে নিয়ে বলল—\n" +
                " উনার হার্টের অবস্থা ভালো নয়। উনাকে প্রতিদিন ভালো-ভালো খাবার রান্না করে খাওয়াবেন। উনার সাথে ভালো ব্যবহার করবেন। শাড়ি-গয়না কেনার জন্য টাকা- পয়সা চেয়ে বিরক্ত করবেন না। বাসায় কোনো টিভি সিরিয়াল দেখবেন না। এভাবে ৬ মাস চললেই উনি সুস্থ হয়ে উঠবেন। ডাক্তার চলে গেলে আবুল তার বউয়ের কাছে জানতে চাইল যে, ডাক্তার কি বলেছে?\n" +
                "আবুলের বউ উত্তর দিল তোমার বাঁচার আর কোন আশাই নেই গো।"));


        /**
         * 4
         */

        arrayList4.add(new Smsinformation(3,"1","","বাবাঃ আজ স্কুলের টিচার কী বললেন?\n" +
                "\n" +
                "ছেলেঃ বললেন তোমার জন্য একজন ভালো অংকের টিউটর রাখতে।\n" +
                "\n" +
                "বাবাঃ মানে?\n" +
                "\n" +
                "ছেলেঃ মানে তুমি হোমওয়ার্কের যে অংকগুলো করে দিয়েছিলে সব ভুল ছিল।"));
        arrayList4.add(new Smsinformation(4,"2","","ছেলে; বাবা, আমাদের বিজ্ঞানের স্যার বলেছেন, অক্সিজেন ছাড়া আমরা নিঃশ্বাস নিতে পারি না। কিন্তু অক্সিজেন আবিষ্কৃত হয়েছে ১৭৭০ সালে। তাহলে তার আগে মানুষ নিঃশ্বাস নিত কিভাবে?"));
        arrayList4.add(new Smsinformation(4,"3","","ছেলে : বাবা সরকার কী ? বাবা : আমি সংসার চালাই তাই আমি সরকারি দল। আর তোর মা খালি খালি ঘ্যান ঘ্যান করে। তাই তোর মা বিরোধী দল। তুই জণগন। তোর ছোট বোন দেশের ভবিষ্যৎ। আর আমাদের কাজের মেয়ে টুম্পা শোষিত শ্রেণী। একটু বাদে মামা ফোন করল। মামা : কীরে সবার খবর কী? ছেলে : সরকার ঘুমোচ্ছে। বিরোধীদল সুবিধামতো আছে। ভবিষ্যৎ কাঁদছে। শোষিত শ্রেণী শোষিত হচ্ছে। আর জণগন তাকিয়ে তাকিয়ে দেখছে।"));
        arrayList4.add(new Smsinformation(4,"4","","বাবা: খোকা, তুমি কাকে বেশি ভালোবাসো? বাবাকে না মাকে?\n" +
                "\n" +
                "খোকা: দুজনকেই।\n" +
                "\n" +
                "বাবা: উহু। যেকোনো একজনের কথা বলতে হবে।\n" +
                "\n" +
                "খোকা: না। আমি দুজনকেই ভালোবাসি।\n" +
                "\n" +
                "বাবা: আচ্ছা ধরো, তোমার মা গেল প্যারিসে, আর আমি যুক্তরাষ্ট্রে। তুমি কার সঙ্গে যাবে?\n" +
                "\n" +
                "খোকা: মায়ের সঙ্গে।\n" +
                "\n" +
                "বাবা: তার মানে তুমি মাকে বেশি ভালোবাসো?\n" +
                "\n" +
                "খোকা: না। প্যারিস যুক্তরাষ্ট্রের চেয়ে বেশি সুন্দর।\n" +
                "\n" +
                "বাবা: ঠিক আছে। ধরো, আমি গেলাম প্যারিসে আর তোমার মা গেল যুক্তরাষ্ট্রে।\n" +
                "\n" +
                "খোকা: তাহলে আমি যুক্তরাষ্ট্রে যাব।\n" +
                "\n" +
                "বাবা: এবার প্যারিসে যাবে না কেন?\n" +
                "\n" +
                "খোকা: প্যারিস তো একবার মায়ের সঙ্গে ঘুরলাম, আবার তোমার সঙ্গে যাব কেন"));
        arrayList4.add(new Smsinformation(4,"5","","বাবা-ছেলের গল্প। মনে হলেই আমার হাসি পায়।\n" +
                "\n" +
                "এক বাচ্চা ছেলে তার বাবাকে বলল, ‘বাবা, আমার চোখে সমস্যা হয়েছে। দূরের জিনিস একদম দেখি না। আমাকে চশমা কিনে দাও।’\n" +
                "\n" +
                "বাবা চমকে উঠলেন। ‘বলিস কী বাবা! চল তো বাইরে গিয়ে পরীক্ষা করি।’\n" +
                "\n" +
                "বাবা-ছেলে বাইরে বের হলো। ছেলে আকাশের দিকে তাকিয়ে হাত তুলে বলল, ‘বাবা, ওইটা কী?’\n" +
                "\n" +
                "বাবা বললেন, ‘ওটা তারা। তুই দেখতে পাচ্ছিস?’\n" +
                "\n" +
                "‘হুঁ, দেখতে পাচ্ছি।’ উত্তর দিল ছেলে।\n" +
                "\n" +
                "বাবা বললেন, ‘বাপধন, আর কত দূরের জিনিস দেখতে চাস তুই?"));
        arrayList4.add(new Smsinformation(4,"6","","ছোট্ট আরিয়ান গেছে বন্ধু সোহানের বাড়ি।\n" +
                "\n" +
                "সোহানের মা: কী চাই?\n" +
                "\n" +
                "আরিয়ান: আন্টি, সোহান কি বাইরে এসে আমাদের সঙ্গে ফুটবল খেলতে পারে?\n" +
                "\n" +
                "সোহানের মা: না! বাইরে ভীষণ গরম!\n" +
                "\n" +
                "আরিয়ান: ঠিক আছে, আন্টি। সোহান না-হয় না-ই এল। সোহানের ফুটবলটা কি বাইরে এসে আমাদের সঙ্গে খেলতে পারে?"));
        arrayList4.add(new Smsinformation(4,"7","","ছোট্ট ৩ বছর বয়সেঃ মা আমাকে অনেক ভালবাসে।\n" +
                "\n" +
                "১০ বছর বয়সেঃ মা…\n" +
                "\n" +
                "১৬ বছর বয়সেঃ মা খুব বিরক্তিকর।\n" +
                "\n" +
                "১৮ বছর বয়সেঃ মা এত ঝামেলা করে ইচ্ছে হয় বাড়ি ছেড়ে চলে যাই।\n" +
                "\n" +
                "২৫ বছর বয়সেঃ মায়ের কথাই ঠিক ছিল।\n" +
                "\n" +
                "৩০ বছর বয়সেঃ মায়ের কাছে ফিরে যেতে ইচ্ছে করছে।\n" +
                "\n" +
                "৫০ বছর বয়সেঃ মাকে কোনভাবেই হারাতে চাই না।\n" +
                "\n" +
                "৭০ বছর বয়সেঃ সব কিছুর বিনিময়ে হলেও মাকে যদি আবার ফিরে পেতাম"));
        arrayList4.add(new Smsinformation(4,"8","","ছাত্রঃ ম্যাডাম আপনি কি মুরগি?\n" +
                "ম্যামঃ কি সব আলতু ফালতু কথা বলো! আমি কেন মুরগি হবো?\n" +
                "ছাত্রঃ তাহলে আপনি আমার খাতায় প্রতিদিন আন্ডা দেন কেন?"));
        arrayList4.add(new Smsinformation(4,"9","","ছেলে লন্ডনে থাকেন | অনেক দিনের শখ গ্রামের বুড়ো বাবা মাকে লন্ডন দেখানোর। অবশেষে ভিসা ম্যানেজ করে বাবা মার জন্য টিকেট পাঠালেন বাংলাদেশে।\n" +
                "নির্ধারিত দিনে তারা এয়ারপোর্ট থেকে বিমানে উঠলেন লন্ডনের উদ্দেশ্যে। বিমান তখন মধ্য আকাশে বুড়োর আবার ডায়াবেটিকের সমস্যাআছে কিছুক্ষন পর পর পানির তেষ্টা পায়। অনেকক্ষন চেপে রাখলেও অবশেষে বুড়ি কে বল্লেন পানি খাব পানি দাও….\n" +
                "বুড়ি এদিক ওদিক তাকিয়ে দেখ... অনেক সুন্দর সুন্দর মেয়েরা ঘুরা ফিরা করছে বিমানে কিন্তু ওদের কোন অনুরোধ করতে সাহস হলো না। শেষে নিজেই পানির খোজে গেলেন আর বিমানের টয়লেটে পানির খোঁজ পেলেন , সেখান থেকে পানি এনে বুড়োকে খাওয়ালেন।\n" +
                "কিছুক্ষন পর বুড়োর আবারও তেষ্টা পেল বুড়ি আবার আগের জায়গা থেকে পানি এনে বুড়োকে খাওয়ালেন।\n" +
                "এভাবে তিন চার বার পানি খাওয়ানোর পর শেষ বার বুড়ি খালি হাতে ফেরত এলো।\n" +
                "\n" +
                "বুড়ো জিজ্ঞেস করল কি ব্যাপার পানি কই ?\n" +
                "\n" +
                "বুড়ি উত্তর দিল : এতক্ষন যেই কুয়া থেকে পানি আন ছিলাম, এখন সেই কুয়ার উপর একটা ল্যাংটা বেটা বসে আছে ।"));
        arrayList4.add(new Smsinformation(4,"10","","ছেলেঃ (চিৎকার করে) হে আল্লাহ, এবারের জন্মদিনে আমাকে একটা ফুটবল উপহার দিও।\n" +
                "মাঃ এভাবে চিৎকার করে কথা বলো না, আল্লাহ কালা না।\n" +
                "ছেলেঃ কিন্তু বাবা যে কালা ।"));
        arrayList4.add(new Smsinformation(4,"11","","ছেলে : এক্সকিউজ মি! আমি কি আপনাকে একটু জড়িয়ে ধরবো?\n" +
                "মেয়ে : হোয়াট ননসেন্স!\n" +
                "ছেলে : ও সরি, আমি আপনাকে একটু আগে জানি কি বলেছি?\n" +
                "মেয়ে : এক্সকিউজ মি! আমি কি আপনাকে একটু জড়িয়ে ধরবো?\n" +
                "ছেলে : বলেন কী? আমি কি আপনাকে নিষেধ করেছি নাকি!"));
        arrayList4.add(new Smsinformation(4,"12","","এক মেয়ের কাছে একটি অপরিচিত নাম্বার থেকে কল আসল।\n" +
                "মেয়ে : হ্যালো.\n" +
                "ছেলে : তোমার কি কোন বয়ফ্রেন্ড আছে??\n" +
                "মেয়ে : হ্যাঁ আছে কিন্তু আপনি কে??\n" +
                "ছেলে : আমি তোর ভাই, দাঁড়া আজকে বাড়িতে আসি তোর খবর আছে!!!\n" +
                "\n" +
                "কিছুক্ষণ পর মেয়েটির নিকট আবার অপরিচিত নাম্বার থেকে আরেকটি কল আসল-\n" +
                "\n" +
                "মেয়ে : হ্যালো!\n" +
                "ছেলে : তোমার কি কোন বয়ফ্রেন্ড আছে??\n" +
                "মেয়ে : না।\n" +
                "ছেলে : তাহলে আমি কে??\n" +
                "মেয়ে: স্যরি স্যরি জান!\n" +
                "আমি মনে করেছি এটা আমার ভাই।\n" +
                "ছেলে : আমি তোর ভাই-ই, আজ তোর একদিন কি আমার একদিন!!"));
        arrayList4.add(new Smsinformation(4,"13","","ছাত্র : স্যার একটি কথা বলবো?\n" +
                "স্যার : কি বলবে বলো?\n" +
                "ছাত্র : আমার খুব লজ্জা লাগছে\n" +
                "স্যার : লজ্জার কি আছে বল?\n" +
                "ছাত্র : আস্তে বলব না জোরে বলব স্যার?\n" +
                "স্যার : আরে বেটা যা বলবি জোরে বল সবাই শুনুক\n" +
                "ছাত্র : চিত্কার করে বলে-স্যার আপনার পেন্টের চেইন খোলা |\n" +
                "স্যার : হারামজাদা আস্তে ক"));
        arrayList4.add(new Smsinformation(4,"14","","জাজ : তোমার পেশা কী?\n" +
                "আসামি : তালা এক্সপার্ট।\n" +
                "জাজ : রাত দুটায় সোনার দোকানে কী করছিলে?\n" +
                "আসামি : ওদের ভল্টের তালাটা সার্ভিসিং করছিলাম।"));
        arrayList4.add(new Smsinformation(4,"15","","জেলার : তোমার শেষ ইচ্ছে কী?\n" +
                "আসামি : আমার স্ত্রীর হাতের রান্না খেতে চাই।\n" +
                "জেলার : তাতে মৃত্যুর আগে তৃপ্তি পাবে তুমি?\n" +
                "আসামি : না, তৃপ্তি পাব না। তবে ওর হাতের রান্না খেলেই মরতে ইচ্ছে হবে আমার।"));
        arrayList4.add(new Smsinformation(4,"16","","জেলসুপার : এবারে তুমি কেন এলে?\n" +
                "কয়েদি : একটা বিশ্রী কাশির জন্য 'চুরি করতে গিয়ে' আর অমনি লোকটা জেগে উঠেছিল।"));
        arrayList4.add(new Smsinformation(4,"17","","\n" +
                "জজ: একদম চুপ! এরপর কেউ টুঁ শব্দটি করলে কোর্ট থেকে বের করে দেওয়া হবে।\n" +
                "সঙ্গে সঙ্গে আসামি বিকট সুরে চেঁচাতে লাগল।"));
        arrayList4.add(new Smsinformation(4,"18","","জাজ : তুমি বলছ তুমি ক্ষুধার্ত ছিলে বলে হোটেল থেকে ক্যাশ ডাকাতি করেছ। কিন্তু হোটেলের খাবার ডাকাতি করাটাই স্বাভাবিক ছিল না?\n" +
                "ডাকাত : খেয়ে পয়সা না দেওয়াটা আমার জন্য অপমানজনক …. মহামান্য আদালত।"));
        arrayList4.add(new Smsinformation(4,"20","","জজ সাহেবঃ যখন এই স্বামী-স্ত্রীর মধ্যে ঝগড়া হচ্ছিল তখন কি তুমি সেখানে উপস্থিত ছিলে?\n" +
                "সাক্ষীঃ জী হ্যাঁ।\n" +
                "জজ সাহেবঃ তোমার এই ঝগড়া থেকে কি ধারনা হলো?\n" +
                "সাক্ষীঃ হুজুর আমি জীবনেও বিয়ে করব না ।"));
        arrayList4.add(new Smsinformation(4,"21","","জজঃ কী ব্যাপার, বারবার কোর্টে আসতে তোমার লজ্জা করে না?\n" +
                "আসামিঃ আমি তো হুজুর বছরে এক-দুইবার আসি। আপনি তো মাশআল্লাহ মাসের তিরিশ দিনই।"));
        arrayList4.add(new Smsinformation(4,"22","","জন্মবার্ষিকীতে একজন শতায়ু বৃদ্ধাকে জিজ্ঞেস করা হল তাঁর এই দীর্ঘ্য জীবনের গোপন রহস্য কী?\n" +
                "বৃদ্ধা বললেন, এখনই ঠিক বলা যাচ্ছে না। একটা ভিটামিন পিল কোম্পানি, একটা আয়ুর্বেদ কোম্পানি আর এ\u200Cকটা ফ্রুট জুস ফ্যাক্টরির সাথে দরদাম চলছে।"));
        arrayList4.add(new Smsinformation(4,"23","","জেল অফিসারঃ জেলখানার ভেতর যারা আছে তারা সবাই ভীষণ দুর্দান্ত চরিত্রের মানুষ। তুমি কন্ট্রোল করতে পারবেতো ?\n" +
                "চাকরি প্রার্থীঃ পারবনা মানে, বেশি তেড়িবেড়ি করলে ঘাড় ধরে বের করে দেব।"));
        arrayList4.add(new Smsinformation(4,"24","","জ্যোতিষীর কাছে গেছে ব্যাঙ।\n" +
                "জ্যোতিষী: খুব শিগগিরই তোমার একটা সুন্দরী মেয়ের সঙ্গে দেখা হবে। সে তোমার ব্যাপারে সবকিছু জানবে।\n" +
                "ব্যাঙ: সত্যি! তার সঙ্গে আমার কোথায় দেখা হবে? নিশ্চয়ই কোনো পার্টিতে!\n" +
                "জ্যোতিষী: না। মেয়েটির জীববিজ্ঞান ব্যবহারিক ক্লাসে!\n"));
        arrayList4.add(new Smsinformation(4,"25","","জাদরেল উকিল -আপনি বিয়ে করেছেন তো\n" +
                "-আজ্ঞে হ্যা করেছি ।\n" +
                "-কাকে বিয়ে করছেন ?\n" +
                "- একজন মেয়েকে\n" +
                "- রাবিস সেটাও বলতে হয় । কখনো শুনেছো কেউ কোন ছেলেকে বিয়ে করেছে ?\n" +
                "- আজ্ঞে হ্যা , আমার বোন করেছে ।"));
        arrayList4.add(new Smsinformation(4,"26","","জঙ্গলে এক চিতা বিড়ি খাচ্ছিলো :\n" +
                "এক ইঁদুর এসে বলে :\n" +
                "ভাই নেশা ছেড়ে দাও , আমার সাথে এসো , দেখ্ জঙ্গল কত সুন্দর !!\n" +
                "চিতা ইদুরের সাথে যেতে লাগলো !!\n" +
                "সামনে হাতি ড্রাগ নিচ্ছিলো !!\n" +
                "ইঁদুর হাতিকে ও একই কথা বললো .\n" +
                "এরপর হাতি ও ওদের সাথে চলতে শুরু করলো .\n" +
                "কিছুদূর এগিয়ে তারা দেখলো :\n" +
                "বাঘ হুইস্কি খাচ্ছে !!\n" +
                "ইঁদুর তাকে ও একই কথা বলার সাথে সাথে বাঘ হুইস্কির গ্লাস রেখে ইদুরকে দিলো কষিয়ে এক থাপ্পড় !!\n" +
                "হাতি তো অবাক !!\n" +
                "বেচারাকে মারলেন কেন ?\n" +
                "বাঘ বললো :এই শালা কালকে ও গাঁজা খাইয়া আমাকে জঙ্গলে ৩ ঘণ্টা ঘুরিয়েছিল"));
        arrayList4.add(new Smsinformation(4,"27","","ট্রাফিক পুলিশ : আমি হাত দেখালাম, তবু তুমি গাড়ি থামালে না কেন?\n" +
                "ড্রাইভার : আমি ভাবলাম, আপনি বুঝি আমাকে টা-টা দিচ্ছেন।"));
        arrayList4.add(new Smsinformation(4,"28","","ট্যুরিষ্ট : নদীতে নামতে পারি? কুমিরের ভয় নেই তো?\n" +
                "স্থানীয় লোক : নিশ্চিন্তে নামুন, এখন আর কুমিরের ভয় নাই। গত দু’বছরে সবগুলো কুমির হাঙরে খেয়ে ফেলেছে।"));
        arrayList4.add(new Smsinformation(4,"29","","ট্যাক্সি ঠিক করছেন এক ভদ্রমহিলা।\n" +
                "\n" +
                "ভদ্রমহিলা: এই ট্যাক্সি, যাবে?\n" +
                "\n" +
                "ট্যাক্সি ড্রাইভার: কোথায়?\n" +
                "\n" +
                "ভদ্রমহিলা: মার্কেটে। তুমি বাইরে অপেক্ষা করবে। কেনাকাটা শেষ হলে তোমাকে নিয়েই ফিরে আসব। চিন্তা করো না, তোমাকে টাকা বাড়িয়ে দেব।\n" +
                "\n" +
                "ট্যাক্সি ড্রাইভার: আপনার সঙ্গে কি আপনার স্বামীও যাবেন?\n" +
                "\n" +
                "ভদ্রমহিলা: হ্যাঁ। কিন্তু কেন?\n" +
                "\n" +
                "ট্যাক্সি ড্রাইভার: তাহলে ভাড়া বেশি দিতে হবে।\n" +
                "\n" +
                "ভদ্রমহিলা: কেন?\n" +
                "\n" +
                "ট্যাক্সি ড্রাইভার: কারণ, তাহলে আমাকে যাওয়ার সময় ব্যাংকের সামনে এবং ফেরার সময় হাসপাতালের সামনেও অপেক্ষা করতে হবে!"));
        arrayList4.add(new Smsinformation(4,"30","","ট্যাক্সি ও ড্রাইভার\n" +
                "এক মহিলা ট্যাক্সিতে বসে ট্যাক্সিড্রাইভারকে বলল:\n" +
                "\n" +
                "—প্রসূতি হাসপাতাল।\n" +
                "\n" +
                "ট্যাক্সি ছুটতে শুরু করল সব গাড়িকে পেছনে ফেলে। মহিলাটি বলল:\n" +
                "\n" +
                "—অত জোরে চালানোর দরকার নেই তো! আমি ওখানে চাকরি করি।"));
        arrayList4.add(new Smsinformation(4,"31","","ট্যাক্সি, পুলিশ ও ড্রাইভার\n" +
                "এক বৃদ্ধ গতিসীমা অতিক্রম করে অত্যন্ত দ্রুতগতিতে গাড়ি চালিয়ে যাচ্ছিলেন। ট্রাফিক পুলিশ তাঁকে আটক করল।\n" +
                "\n" +
                "পুলিশ: আপনি যদি এত দ্রুতগতিতে গাড়ি চালানোর পেছনে কোনো যুক্তিযুক্ত কারণ বলতে পারেন, তাহলে আপনাকে ছেড়ে দেব।\n" +
                "\n" +
                "বৃদ্ধ: আসলে, আমি একটু ভুলোমনা। আমি কোথায় যাচ্ছিলাম, সেটা ভুলে যাওয়ার আগেই আমি সেখানে পৌঁছতে চাই।\n" +
                "\n" +
                "পুলিশ: ঠিক আছে, আপনি যেতে পারেন।\n" +
                "\n" +
                "বৃদ্ধ: কোথায় যেন যাচ্ছিলাম?\n" +
                "\n" +
                "পুলিশ এলোপাতাড়িভাবে রাজপথ দিয়ে ছুটে যাচ্ছে একটি গাড়ি। আরোহীর সিটে বসে আছেন মিসেস শায়লা।\n" +
                "\n" +
                "মিসেস শায়লা: ও মাই গড! ড্রাইভার! তুমি আমাকে মারবার ফন্দি এঁটেছো নাকি?\n" +
                "\n" +
                "ড্রাইভার: ভয় পাবেন না ম্যাডাম, বেশি ভয় করলে আমার মতো চোখ বন্ধ করে বসে থাকুন!"));
        arrayList4.add(new Smsinformation(4,"32","","ট্যাক্সি ও দুই ড্রাইভার আড্ডা দিচ্ছে—\n" +
                "\n" +
                "কি রে, শুনলাম তোর নাকি চাকরি যায় যায় অবস্থা! আজকেও দেখি গাড়ি নিয়ে বের হয়েছিস! বসরে ক্যামনে হাত করলি?\n" +
                "\n" +
                "হে হে, ঘটনা আছে! চাকরি যাওনের কথা শুইনাই ইচ্ছা কইরা দামি গাড়িটার একটা হেডলাইট দিছিলাম ভাইঙ্গা!\n" +
                "\n" +
                "তারপর?\n" +
                "\n" +
                "তারপর আর কী! বস কইল আগামী ছয় মাসে হেডলাইট ভাঙা বাবদ যত খরচ পড়ে তত টাকা আমার বেতন থেইকা কাইটা রাখব। তাতে কী, ছয় মাসের জন্য তো চাকরিটা একদম পাক্কা!\n"));
        arrayList4.add(new Smsinformation(4,"33","","ট্রাফিকঃ আপনার ড্রাইভিং লাইসেন্স দেখি।\n" +
                "\n" +
                "চালকঃ কিন্তু স্যার, আমিতো খুব সাবধানে চালাচ্ছি, বেআইনি কোন কিছু করিনি।\n" +
                "\n" +
                "ট্রফিকঃ সেজন্যইতো সন্দেহ হচ্ছে।"));
        arrayList4.add(new Smsinformation(4,"34","","ট্রেনের দুই সহযাত্রীর মধ্যে কথা হচ্ছে।\n" +
                "\n" +
                "প্রথম যাত্রী: আপনি একটি বাইনোকুলার গলায় ঝুলিয়ে রেখেছেন। অথচ একবারও দেখলাম না, আপনি বাইনোকুলার দিয়ে বাইরে দূরের কোনো দৃশ্য দেখছেন। তাহলে সঙ্গে এটা আনলেন কেন?\n" +
                "\n" +
                "দ্বিতীয় যাত্রী: আহ্! এটা দূরের দৃশ্য দেখার জন্য আনিনি। ছুটি কাটাতে যার বাড়ি যাচ্ছি, তাকে দেখার জন্য এনেছি।\n" +
                "\n" +
                "প্রথম যাত্রী: কেন?\n" +
                "\n" +
                "দ্বিতীয় যাত্রী: কারণ, তিনি আমার দূরসম্পর্কের আত্মীয়"));
        arrayList4.add(new Smsinformation(4,"35","","ড্রাইভার পদে চাকরির জন্য মন্টু মিয়া গেছে সাক্ষাত্কার দিতে। সাক্ষাত্কার চলছে—\n" +
                "প্রশ্নকর্তা: আপনাকে আমার পছন্দ হয়েছে। চাকরিটা আপনাকে দেওয়া হবে। স্টার্টিং বেতন দেওয়া হবে দুই হাজার টাকা। আপনার কোনো সমস্যা নেই তো?\n" +
                "মন্টু মিয়া: না না স্যার, আমার কোনো সমস্যা নেই। স্টার্টিং বেতন ঠিক আছে, কিন্তু ড্রাইভিং বেতন কত সেটাও তো জানা দরকার মনে হয়।"));
        arrayList4.add(new Smsinformation(4,"36","","ডাক্তারের কাছে গিয়ে শফিক দেখল, চেম্বারের দরজায় বড় করে লেখা আছে, ‘প্রথমবার ৫০০ টাকা, এরপর ৩০০ টাকা।’ ২০০ টাকা বাঁচাতে সে মনে মনে একটা বুদ্ধি আঁটল।\n" +
                "ডাক্তারের রুমে ঢুকেই বলল, ‘ডাক্তার সাহেব, আবার এলাম। আমার অসুখ তো ভালো হলো না।’\n" +
                "ডাক্তার ভ্রু কুঁচকে তাকালেন। মনোযোগ দিয়ে পরীক্ষা-নিরীক্ষা করলেন। তারপর বললেন, ‘আগে যে ওষুধগুলো দিয়েছিলাম, সেগুলোই চলবে। এবার ঝটপট ৩০০ টাকা দিন।’"));
        arrayList4.add(new Smsinformation(4,"37","","ডাক্তারঃ ভয়ের কিছু নেই। চট করে করে আপনার দাঁতটা তুলে নিব।\n" +
                "রোগীঃ না না ডাক্তার সাহেব, আমার ভয় করছে। প্লিজ ডাক্তার সাহেব, আমি জন্ত্রনায় মারাই যাব, বড্ড ভয় করছে।\n" +
                "ডাক্তারঃ ঠিক আছে, আপনি খানিকটা ক্যান্ডি খেয়ে নিন। দেখবেন সাহস বেড়ে গেছে।\n" +
                "রোগীঃ ক্যান্ডি খেয়ে নিলো।\n" +
                "ডাক্তারঃ কি এখন সাহস বেড়েছে তো?\n" +
                "রোগীঃ নিশ্চয়ই বেড়েছে, এখন দেখি কোন শালা আমার দাঁত তুলতে আসে? দাতে হাত লাগাবেন তো এক ঘুষিতে নাক ফাটিয়ে দেবো !!!"));
        arrayList4.add(new Smsinformation(4,"38","","ডাক্তারের কাছে গেলেন বদরুল। বললেন, ‘নিজেকে যখন আয়নায় দেখি, তখন ভীষণ মেজাজ খারাপ হয়! কেন এমন হয়?’\n" +
                "\n" +
                "ডাক্তার বললেন, ‘আপনার দৃষ্টিশক্তি খুব ভালো।’"));
        arrayList4.add(new Smsinformation(4,"39","","ডাক্টরঃ আপনার ওজন দু-কিলো কমে গেছে কেন ?\n" +
                "মহিলাঃ ও..আজ মেক আপ করিনি তো তাই ।"));
        arrayList4.add(new Smsinformation(4,"40","","ডাক্তারের চেম্বারের সামনে দাঁড়িয়ে আছে এক তরুণ। কাছ দিয়েই যাচ্ছিল ঐ তরুণের এক বন্ধু। কথা হচ্ছে উভয়ের মধ্যে-\n" +
                "১ম জনঃ কি ব্যাপার, তুই এখানে দাঁড়িয়ে কি করছিস?\n" +
                "২য় জনঃ মেয়েদের দেখছি!\n" +
                "১ম জনঃ মানে?\n" +
                "২য় জনঃ ঐ দেখ, ডাক্তারের চেম্বারের দরজায় ঝুলানো আছে।\n" +
                "১ম জনঃ “মেয়েদের দেখার জন্য বিকাল ৪টা থেকে ৬টা।”\n" +
                "তাতে কী হয়েছে?\n" +
                "২য় জনঃ তাই তো দাঁড়িয়ে নির্দেশ পালন করছি ।"));
        arrayList4.add(new Smsinformation(4,"41","","ডাক্তার : তুমি পাগল হলে কিভাবে ????\n" +
                "পাগলঃ-পাগল কি হইছি সাধে!!!!!\n" +
                "আমি এক বিধবা মহিলারে বিয়ে করছিলাম। তার এক যুবতী মেয়ে ছিল। তাকে বিয়ে করল আমার বাবা। তো আমার মেয়ে হয়ে গেল আমার মা। এবং আমি হয়ে গেলাম আমার বাবার শশুড়।\n" +
                ".\n" +
                "তার ঘরে একটা মেয়ে হলো সে হলো আমার বোন। কিন্ত আমি তার নানীর জামাই। সে দিক থেকে সে আমার নাত্নীও।\n" +
                ".\n" +
                "এভাবে আমার একটা পোলা হইলো। তো আমার পোলা আমার বাপের শালা। আর আমি আমার পোলার ভাইগ্না।\n" +
                ".\n" +
                "ডাক্তারঃ চুপ কর শালা আমারেও তো পাগল বানাইয়া ছাড়বি\n"));
        arrayList4.add(new Smsinformation(4,"12","","রোগীঃ ডাক্তার সাব! বেশীদিন বাচোনের কোন উপায় আছে কি?\n" +
                "ডাক্তারঃ যান বিয়া করেন গিয়া।\n" +
                "রোগীঃ ক্যান? বিয়া করলে কি বেশিদিন বাচন যায়?\n" +
                "ডাক্তারঃ তা কইবার পারুম না। তয় এতডা কইতে পারে যে আপনে বিয়ার পর আর বেশিদিন বাচনের চেষ্টা করবেন না"));
        arrayList4.add(new Smsinformation(4,"13","","তিন অপরাধীকে পাঁচ বছরের জন্য কারাভোগের শাস্তি দেওয়া হয়েছে। বিচারক সদয় হয়ে তাদের একটা সুযোগ করে দিলেন। জেলখানায় সময় কাটানোর জন্য তারা চাইলে সঙ্গে কিছু নিতে পারবে। প্রথম অপরাধী সঙ্গে নিল একটা খাতা আর কলম। দ্বিতীয়জন সঙ্গে নিল একটা রেডিও। আর তৃতীয়জন নিল এক বাক্স সিগারেট।\n" +
                "পাঁচ বছর পর প্রথমজন যখন বেরিয়ে এল, তখন দেখা গেল, জেলখানায় তার সময় ভালোই কেটেছে। জেলের জীবন নিয়ে সে একটা উপন্যাস লিখে ফেলেছে। দ্বিতীয়জনও আছে বেশ ফুরফুরে মেজাজে। জেলখানায় গান শুনে তার চমৎকার সময় কেটেছে।\n" +
                "তৃতীয়জন বেরিয়ে এল বিধ্বস্ত অবস্থায়। চুল উসকোখুসকো, উন্মাদপ্রায় দশা। বাক্সভর্তি সিগারেট হাতে নিয়ে সে কাতরস্বরে বলল, ‘কারও কাছে একটা দেশলাই হবে?’"));
        arrayList4.add(new Smsinformation(4,"44","","তিন ইঁদুর নিজেদের বীরত্বের গল্প করছে।\n" +
                "প্রথম ইঁদুর: জানিস, সেদিন আমি এক বোতল ইঁদুর মারার বিষ খেয়ে ফেলেছি, অথচ আমার কিছুই হয়নি।\n" +
                "দ্বিতীয় ইঁদুর: কিছুদিন আগে আমি একটা ফাঁদে আটকা পড়ে গেছিলাম। ফাঁদটা ভেঙে বেরিয়ে এসেছি।\n" +
                "তৃতীয় ইঁদুর: তোরা গল্প কর, আমি আজ উঠি। বাড়ি ফিরে আবার পোষা বিড়ালটাকে খাবার দিতে হবে।"));
        arrayList4.add(new Smsinformation(4,"45","","তিন বন্ধু একটি মোটরসাইকেলে চড়ে রাস্তা দিয়ে যাচ্ছে। কিছুদূর যাওয়ার পর ট্রাফিক তাদের থামিয়ে দিয়ে বলল, ‘একটি মোটরসাইকেলে তিনজন ওঠা আইনত অপরাধ, এটা আপনারা জানেন না?’\n" +
                "‘জানি’, জবাব দিল একজন।\n" +
                "ট্রাফিক রেগে বলল, ‘জানেন তো একটি মোটরসাইকেলে তিনজন উঠেছেন কেন?’\n" +
                "‘জেনেশুনে অপরাধ করব না বলেই তো আমাদের একজনকে বাসায় রেখে আসতে যাচ্ছি, স্যার।’"));
        arrayList4.add(new Smsinformation(4,"46","","তিন ছেলেমেয়ের জন্য একটা মাত্র খেলনা কিনে বাড়ি ফিরলেন মকবুল। বললেন, ‘যে ঘরে একদম লক্ষ্মী হয়ে থাকে, তোমাদের আম্মুর সব কথা শোনে, একদম চিৎকার-চেঁচামেচি করে না, সে-ই খেলানাটা পাবে।’\n" +
                "\n" +
                "ছোট মেয়েটা বলে উঠল, ‘বললেই হয়, খেলনাটা তুমি রাখতে চাও।"));
        arrayList4.add(new Smsinformation(4,"47","","তিন বন্ধুর কথোপাকথন\n" +
                "১ম জন অন্ধ ,২য় জন লেংড়া ,৩য় জন ফকির]\n" +
                "তারা একটি পাহাড়ে উঠল শখ করে ...\n" +
                "অন্ধ : দেখ আকাশে কি সুন্দর চাঁদ উঠেছে !..\n" +
                "লেংড়া : তুই তো অন্ধ চাঁদ দেখছস কি করে ...\n" +
                "একটা লাথি মেরে পাহাড় থেকে ফেলে দেব ।\n" +
                "ফকির : ওই লেংড়া ওরে লাথি দে যত টাকা লাগে আমি দেব"));
        arrayList4.add(new Smsinformation(4,"48","","থানায় গিয়ে এক ভদ্রমহিলা ইন্সপেক্টরকে বললেন, ‘আমার স্বামী গতকাল আলু কিনতে বাজারে গেছে, এখনো ফেরেনি।’\n" +
                "ইন্সপেক্টরঃ আলুই রান্না করতে হবে এমন তো কোনো কথা নেই, অন্য সবজি রান্না করুন।"));
        arrayList4.add(new Smsinformation(4,"49","","থানায় ঢুকেই ভদ্রমহিলা রাগে ফেটে পড়লেন, ইন্সপেক্টর সাহেব, আমি আমার প্রতিবেশীর বিচার চাই। লোকটা একটা আস্ত বেয়াদব এবং ছোটলোক।\n" +
                "\n" +
                "ইন্সপেক্টর: কেন? কী করেছে সে?\n" +
                "\n" +
                "ভদ্রমহিলা: আমি যখনই তার বাড়িতে উঁকি দিই, দেখি সে-ও উঁকি দিয়ে আছে!\n"));
        arrayList4.add(new Smsinformation(4,"50","","দুই বন্ধুর দেখা হলো বহুদিন পর।\n" +
                "প্রথম বন্ধু: শুনলাম তুই একটা ব্যবসা চালু করেছিস, কী সৌভাগ্য তোর!\n" +
                "দ্বিতীয় বন্ধু: আরে না, খুবই ছোট আকারের ব্যবসা।\n" +
                "প্রথম বন্ধু: তবু, তুই একটা অফিসের বস। তা তোর অধীনে অফিসে কতজন কাজ করে?\n" +
                "দ্বিতীয় বন্ধু: মোট লোকের অর্ধেক।"));

        /**
         * 5
         */

        arrayList5.add(new Smsinformation(5,"1","","বাবাঃ আজ স্কুলের টিচার কী বললেন?\n" +
                "\n" +
                "ছেলেঃ বললেন তোমার জন্য একজন ভালো অংকের টিউটর রাখতে।\n" +
                "\n" +
                "বাবাঃ মানে?\n" +
                "\n" +
                "ছেলেঃ মানে তুমি হোমওয়ার্কের যে অংকগুলো করে দিয়েছিলে সব ভুল ছিল।"));
        arrayList5.add(new Smsinformation(5,"2","","ছেলে; বাবা, আমাদের বিজ্ঞানের স্যার বলেছেন, অক্সিজেন ছাড়া আমরা নিঃশ্বাস নিতে পারি না। কিন্তু অক্সিজেন আবিষ্কৃত হয়েছে ১৭৭০ সালে। তাহলে তার আগে মানুষ নিঃশ্বাস নিত কিভাবে?"));
        arrayList5.add(new Smsinformation(5,"3","","ছেলে : বাবা সরকার কী ? বাবা : আমি সংসার চালাই তাই আমি সরকারি দল। আর তোর মা খালি খালি ঘ্যান ঘ্যান করে। তাই তোর মা বিরোধী দল। তুই জণগন। তোর ছোট বোন দেশের ভবিষ্যৎ। আর আমাদের কাজের মেয়ে টুম্পা শোষিত শ্রেণী। একটু বাদে মামা ফোন করল। মামা : কীরে সবার খবর কী? ছেলে : সরকার ঘুমোচ্ছে। বিরোধীদল সুবিধামতো আছে। ভবিষ্যৎ কাঁদছে। শোষিত শ্রেণী শোষিত হচ্ছে। আর জণগন তাকিয়ে তাকিয়ে দেখছে।"));
        arrayList5.add(new Smsinformation(5,"4","","দুই কয়েদি পালিয়েছে জেল থেকে। আবার যখন তাদের আটক করা হলো, কারারক্ষক প্রশ্ন করলেন, ‘তোমরা জেল থেকে পালিয়েছিলে কেন?’\n" +
                "১ম কয়েদি: কারণ, জেলখানার খাবার খুবই জঘন্য। খাওয়া যায় না।\n" +
                "কারারক্ষক: কিন্তু তোমরা জেলের তালা ভাঙলে কী দিয়ে?\n" +
                "২য় কয়েদি: সকালের নাশতার রুটি দিয়ে!"));
        arrayList5.add(new Smsinformation(5,"5","","দুই চাপাবাজ\n" +
                "১ম চাপাবাজঃ আমি এত গরম চা খাই যে, কেতলি থেকে সোজা মুখে ঢেলে দেই!\n" +
                "২য় চাপাবাজঃ কি বলিস! আমি তো চা-পাতা, পানি, দুধ, চিনি মুখে দিয়ে চুলোয় বসে পড়ি!"));
        arrayList5.add(new Smsinformation(5,"6","","দুই চাপাবাজের মধ্যে আলাপ হচ্ছে-\n" +
                "প্রথম চাপাবাজ: জানিস মাঝে মাঝে ইচ্ছে হয় ফ্রান্সের আইফেল টাওয়ারটা কিনে ফেলি।\n" +
                "দ্বিতীয়চাপাবাজ: অত সহজ না বন্ধু! ওটা আমি বেচলেতো।"));
        arrayList5.add(new Smsinformation(5,"7","","দুয়ারে নতুন বছর। ভাগ্যের হালচালটা জেনে নিতে হাবলু হানা দিল এক জ্যোতিষবাবার আস্তানায়। বলল, ‘বাবা, কদিন হলো ডান হাতটা খুব চুলকাচ্ছে। কিসের লক্ষণ বলুন তো?’\n" +
                "জ্যোতিষবাবা: হুম্! তোর ওপর মঙ্গলের প্রভাব রয়েছে। আসছে বছর তোর হাতে প্রচুর টাকা আসবে।\n" +
                "হাবলু: বাবা, আমার বাঁ হাতের তালুও চুলকায়।\n" +
                "জ্যোতিষবাবা: বলিস কী? তোর তো বিদেশযাত্রা শুভ!\n" +
                "হাবলু: (খুশিতে গদগদ হয়ে) বাবা, আমার ডান পা’টাও কিন্তু একটু একটু চুলকাচ্ছে।\n" +
                "জ্যোতিষবাবা: দূর ব্যাটা, তোর চুলকানি আছে। ডাক্তার দেখা।"));
        arrayList5.add(new Smsinformation(5,"8","","দৃষ্টি প্রতিবন্ধী এক লোক তাঁর পোষা কুকুরটাকে নিয়ে রাস্তার ধার দিয়ে হাঁটছিলেন। কুকুরটার গলায় বাঁধা চেনের অপর মাথা তাঁর হাতে ধরা ছিল। হঠাৎ কুকুরটা একটি চলন্ত গাড়ির সামনে দিয়ে দৌড় লাগাল। দৃষ্টি প্রতিবন্ধী লোকটা কোনোমতে দুর্ঘটনা এড়িয়ে রাস্তার ওপারে পৌঁছলেন। পৌঁছেই তিনি পকেট থেকে কুকুরটাকে খাওয়ানোর জন্য বিস্কুট বের করলেন।\n" +
                "\n" +
                "পথচারী: এই বেয়াদব কুকুরটার জন্য আরেকটু হলেই মরতে বসেছিলেন। তাকে এখন বিস্কুট খাওয়াবেন!\n" +
                "\n" +
                "দৃষ্টি প্রতিবন্ধী: আমি আসলে চড় মারার জন্য ওর মাথাটা খুঁজছি!"));
        arrayList5.add(new Smsinformation(5,"9","","দুপুর বেলা জাদুঘর ঘুরতে ঘুরতে\n" +
                "ক্লান্ত হয়ে একটি চেয়ারে\n" +
                "বসে পড়লেন আবুল\n" +
                ".\n" +
                ".\n" +
                "..হায় হায় করে দৌড়ে এল জাদুঘরের কর্মীরা.\n" +
                ".\n" +
                ".\n" +
                "বলেলেন...\n" +
                ".\n" +
                ".\n" +
                "আরে করছেন কি করছেন কি???\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                "এইটা নবাব সিরাজউদ্দৌলার চেয়ার....\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ". আবুল: ভাই ,একটু বসতে দেন ..\n" +
                "সিরাজ ভাই আসলেই আমি উইঠা যাবো.."));
        arrayList5.add(new Smsinformation(5,"10","","ধানের বস্তাগুলো সমেত উল্টে গেছে এক চাষির মালগাড়ি। চাষিপুত্র আপ্রাণ চেষ্টা করছিল ভারী বস্তাগুলো মালগাড়িতে তুলে রাখতে। ‘হেইও হেইও’ করে বেশ টানাহেঁচড়ার পর বস্তার নেই কোনো নড়নচড়ন।\n" +
                "\n" +
                "দূর থেকে দৃশ্যটা দেখে এগিয়ে এলেন অপর এক চাষি। বললেন, কী হে ছোকরা, তুমি একলা মানুষ। অত ভারী বস্তা তো তুলতে পারবে না। পাশেই আমার বাড়ি। চলো, আমি বাড়ি যাচ্ছি খেতে। তুমিও আমার সঙ্গে খাবে, তারপর দুজন মিলে বস্তাগুলো তুলে ফেলব।’\n" +
                "\n" +
                "চাষিপুত্র বলল, না না চাচা, বাবা খুব রাগ করবেন।\n" +
                "\n" +
                "অপর চাষি: আরে চলো তো।\n" +
                "\n" +
                "অবশেষে চাষিপুত্র খেতে চলল। খাওয়া-দাওয়া শেষে চাষি বলল, ‘চলো দেখি, তোমার বস্তাগুলো তুলে দেইগে।\n" +
                "\n" +
                "চাষিপুত্র: ‘চাচা, বাবা খুব রাগ করবে।\n" +
                "\n" +
                "অপর চাষি: ‘আরে, তোমার বাবাকে আমার কথা বলো। তা, উনি এখন কোথায় আছেন?\n" +
                "\n" +
                "চাষিপুত্র: ‘বস্তাগুলোর নিচে!"));
        arrayList5.add(new Smsinformation(5,"11","","নতুন বছরের প্রথম দিন মালিক বলছেন চাকরকে, ‘গত বছর তুই বেশ ভালো কাজ করেছিস। এই নে ১০ হাজার টাকার চেক। এ বছর এমন ভালো কাজ দেখাতে পারলে আগামী বছর চেকে সই করে দেব!\n" +
                "\n" +
                "জোকস ২৪৮\n" +
                "নবীন বিশেষজ্ঞ ও প্রবীণ বিশেষজ্ঞের মধ্যে পার্থক্য কী?\n" +
                "উত্তর: নবীন বিশেষজ্ঞ কাজ জানে না আর প্রবীণ বিশেষজ্ঞ কাজ করে না।"));

        arrayList5.add(new Smsinformation(5,"12","","নিতাই বেজায় কৃপণ। একদিন তাঁর বাড়িতে হাজির হলেন তাঁর বন্ধু নারায়ণ।\n" +
                "নারায়ণ: কিরে নিতাই, তোর বাড়িতে এলাম, কিছু খাওয়াবি না?\n" +
                "নিতাই: কী খেতে চাস, বল। ঠান্ডা, না গরম?\n" +
                "নারায়ণ: নিয়ে আয়। ঠান্ডা গরম দুটাই খাব।\n" +
                "নিতাই হাঁক ছাড়লেন, ‘কই রে জগাই, ফ্রিজ থেকে এক গ্লাস ঠান্ডা পানি আর চুলা থেকে এক গ্লাস গরম পানি নিয়ে আয়!"));
        arrayList5.add(new Smsinformation(5,"13","","নতুন ইন্টার্ন চিকিৎসক হলো রনি। একজন রোগী এসে তার রোগের বর্ণনা দিয়ে জানতে চাইল, ডাক্তার সাহেব আমার কী হয়েছে?\n" +
                "\n" +
                "রনি জিজ্ঞেস করল, এ রকম রোগ কি আগেও আপনার হয়েছিল।\n" +
                "\n" +
                "—জি স্যার। জানুয়ারিতেও এমন হয়েছিল। স্যার বলেন না আমার কী হয়েছে।\n" +
                "\n" +
                "—বুঝতে পেরেছি, আপনার জানুয়ারিতে যেটা হয়েছিল, সেটাই আবার হয়েছে।"));
        arrayList5.add(new Smsinformation(5,"14","","নদীর দুই পাড়ে দাঁড়িয়ে থাকা দুই লোক চিৎকার করে কথা বলছে—\n" +
                "\n" +
                "- আমি ওই পাড়ে কী করে আসব?\n" +
                "\n" +
                "- গাধার মতো কথা বোলো না। তুমি তো ওই পাড়েই আছো।"));
        arrayList5.add(new Smsinformation(5,"15","","নির্বাচনে এক নেতা যখন ভোটে দাঁড়ালেন, প্রচুর পোস্টার ছাপালেন। প্রেসের লোক এসে বলল, ‘স্যার, এত পোস্টার ছাপালেন, বিলটা তো পাইলাম না।’ নেতা বললেন, ‘খাড়াও মিয়া, খালি সংসদে যাই, তারপর তো শুধু বিলই পাস করমু।’"));
        arrayList5.add(new Smsinformation(5,"16","","নির্বাচনে,এক লোকের খায়েশ হয়েছে তিনি সংসদ নির্বাচন করবেন। দাঁড়িয়েও গেলেন ভোটে। স্বতন্ত্র প্রার্থী হিসেবে। ভোট হলো। গণনা শেষে দেখা গেল, তিনি মাত্র তিনটি ভোট পেয়েছেন। লোকটির স্ত্রী তো রেগে আগুন। বলল, ‘আমি আগেই সন্দেহ করেছিলাম, তুমি নিশ্চয় অন্য কোনো মেয়েকে ভালোবাস। তা না হলে তৃতীয় ভোটটা দিল কে?’"));
        arrayList5.add(new Smsinformation(5,"17","","নির্বাচনের প্রার্থীকে জিজ্ঞেস করছেন সাংবাদিকঃ আপনি কেন নির্বাচনে দাঁড়িয়েছেন?\n" +
                "\n" +
                "-আপনি কি দেখতে পাচ্ছেন না, চারদিকে কী ঘটছে? সরকারি লোকেরা আমোদ-প্রমোদে মত্ত, দুর্নীতিতে ছেয়ে গেছে দেশ।\n" +
                "\n" +
                "-আপনি এর বিরুদ্ধে লড়ার জন্যই নির্বাচন করছেন?\n" +
                "\n" +
                "-পাগল নাকি! আমার কি আমোদ-প্রমোদ করতে শখ হয় না?"));
        arrayList5.add(new Smsinformation(5,"18","","নরকে বসে এক লোক শয়তানকে বলছে, আমি কি আমার স্ত্রীকে একটা কল করতে পারি? তোমার মোবাইলটা দিবে?\n" +
                "শয়তান মেবাইল দিলো, এবং লোকটা কয়েক মিনিট কথা বলে শয়তানকে মোবাইলটা ফিরিয়ে দিলো।\n" +
                "এরপর লোকটা বললো, তোমাকে ফোন কল বাবদ কত টাকা দিতে হবে?\n" +
                "তখন শয়তানের জবাবঃ লাগবে না। নরক থেকে নরক কলরেট টোটালি ফ্রি।"));
        arrayList5.add(new Smsinformation(5,"20","","পল্টুর অফিসে প্রথম দিনেই বড় কর্তার সঙ্গে কথা হচ্ছে—\n" +
                "বড় কর্তা: আপনি কম্পিউটারে কী কী কাজ জানেন?\n" +
                "পল্টু: স্যার, প্রায় সব ধরনের কাজই করতে পারি।\n" +
                "বড় কর্তা: আচ্ছা, আপনি এমএস অফিস জানেন?\n" +
                "পল্টু: আজই যেতে হবে! তাহলে ওই অফিসের ঠিকানাটা একবার বলে দিলেই আমি খুঁজে বের করতে পারব, স্যার।"));
        arrayList5.add(new Smsinformation(5,"21","","পোষ্ট অফিসে গিয়ে এক লোক পোস্টমাস্টারের দিকে একট খাম বাড়িয়ে দিল।\n" +
                ": দেখুন তো ভাই, ঠিক আছে কি না?\n" +
                ": আরও বিশ পয়সার টিকেট দিতে হবে। চিঠি ভারী হয়ে গেছে।\n" +
                ": টিকেট লাগালে তো আরো ভারী হয়ে যাবে।"));
        arrayList5.add(new Smsinformation(5,"22","","পত্রিকায় ‘লম্বা হোন’ বিজ্ঞাপন দেখে এক লোক গেল সেই অফিসে।\n" +
                "কর্মচারী বলল, আপনাকে আগামীকাল আসতে হবে। কারণ, আমাদের ফরমগুলো কে যেন আলমারির ওপরে উঠিয়ে রেখেছে। আলমারিটা একটু উঁচু তো। আমরা কেউ নাগাল পাচ্ছি না।"));
        arrayList5.add(new Smsinformation(5,"23","","প্রায় আঠার বছর কেইস চলার পর আসামি তার অপরাধ স্বীকার করল। বিচারক তার কাছে জানতে চাইল সে যদি দোষ স্বীকারই করবে তবে অযথা এতদিন সময় নিল কেন?\n" +
                "আসামি জবাব দিল : ‘আমি নিজেই কি নিশ্চিত ছিলাম নাকি? সব সাক্ষীসাবুদের পরই না বুঝতে পারলাম।"));
        arrayList5.add(new Smsinformation(5,"24","","প্রশ্নকর্তা: একটা প্লেনে ৫০টা ইট আছে, একটা ইট ফেলে দিলে থাকে কয়টা?\n" +
                "প্রার্থী: এটা তো সোজা। ৪৯টা।\n" +
                "প্রশ্নকর্তা: আচ্ছা, একটা ফ্রিজে হাতি রাখার তিনটা স্টেপ কী কী?\n" +
                "প্রার্থী: ফ্রিজটা খুলুন, হাতিটা ঢোকান, এরপর ফ্রিজের দরজা বন্ধ করে দিন।\n" +
                "প্রশ্নকর্তা: একটা ফ্রিজে একটা হরিণ রাখার চারটা স্টেপ কী কী?\n" +
                "প্রার্থী: ফ্রিজটা খুলুন, হাতিটা বের করুন, হরিণটা ঢোকান, এরপর ফ্রিজের দরজা বন্ধ করে দিন।\n" +
                "প্রশ্নকর্তা: বনে সিংহের আজকে জন্মদিন। সবাই এসেছে শুধু একজন ছাড়া। কে আসেনি এবং কেন?\n" +
                "প্রার্থী: হরিণ আসেনি। কারণ সে ফ্রিজে।\n" +
                "প্রশ্নকর্তা: এক বৃদ্ধা কুমির ভর্তি একটা খাল পার হলো কোনো ক্ষতি ছাড়াই, কীভাবে?\n" +
                "প্রার্থী: কারণ সব কুমির সিংহের জন্মদিনে গিয়েছে।\n" +
                "প্রশ্নকর্তা: শেষ প্রশ্ন, তার পরও বৃদ্ধা মারা গেলেন, কেন?\n" +
                "প্রার্থী: উমম…আমার মনে হয়, তিনি খালের পানিতে ডুবে গিয়েছিলেন?\n" +
                "প্রশ্নকর্তা: না, প্লেন থেকে যে ইটটা পড়ে গিয়েছিল, সেটা তার মাথায় পড়েছিল, আপনি এখন আসতে পারেন…।"));
        arrayList5.add(new Smsinformation(5,"25","","প্রশ্নকর্তা –লোহার মতো শক্ত কিন্তু গর্তে ভর্তি জিনিসটা কী?\n" +
                "চাকরী পার্থী —শিকল।"));
        arrayList5.add(new Smsinformation(5,"26","","প্রথম বন্ধুঃ জানিস, আমার মামার বাড়িতে এত বড় আম হয় যে দুটিতেই এক কেজি হয়ে যায়!\n" +
                "দ্বিতীয় বন্ধুঃ আরে তুই জানিস, আমার মামা বাড়িতে এত বড় বড় আম হয় যে চারটিতেই এক ডজন হয়ে যায়!"));
        arrayList5.add(new Smsinformation(5,"27","","পদা: কাল রাতে ঘরে চোর এসেছিল।\n" +
                "গদা: বলিস কী!\n" +
                "পদা: ঘুম ভেঙে গেলে তাকে জিজ্ঞেস করলাম, সে কী করছে? বলল, টাকা-পয়সা খুঁজছে।\n" +
                "গদা: তুই চোরটাকে ধরে পুলিশে দিস নাই?\n" +
                "পদা: না…\n" +
                "গদা: তবে?\n" +
                "পদা: আমিও তার সাথে টাকা-পয়সা খুঁজতে শুরু করছিলাম।"));
        arrayList5.add(new Smsinformation(5,"28","","পাপ্পুর হাতে আইফোন দেখে তার বান্ধবী বলল, ‘কী সুন্দর মোবাইল! কত দিয়ে কিনলে?’\n" +
                "পাপ্পু: দৌড় প্রতিযোগিতায় জিতেছি।\n" +
                "বান্ধবী: ওয়াও! কতজন দৌড়েছিল?\n" +
                "পাপ্পু: তিনজন পুলিশ, এক মোবাইল ফোন ব্যবসায়ী আর আমি।"));
        arrayList5.add(new Smsinformation(5,"29","","পুলিশকে বানর বলেছে বলে এক ব্যক্তির চার মাসের জেল হয়ে গেল। রায় হবার পর সে বিচারকের কাছে জানতে চাইল সে যদি পুলিশকে বানর না বলে বানরকে পুলিশ বলে তাহলেও কি তার শাস্তি হবে কিনা। বানররা নালিশ করতে পারে না, কাজেই বিচারক জানালেন যে, না, তার কোনো শাস্তি হবে না। এটা শুনেই সে ব্যক্তি পাশে দাঁড়ানো সকল পুলিশের দিকে তাকিয়ে বলতে লাগল- ‘বানর!বানর !!!’"));
        arrayList5.add(new Smsinformation(5,"30","","পুলিশ : কী ব্যাপার আজকে না চাইতে বখরা দিচ্ছিস?\n" +
                "ডাকাত : আইজ ভুলে আপনের বাড়িতে …"));
        arrayList5.add(new Smsinformation(5,"31","","পুলিশ : তোমার কুকুরের লাইসেন্স আছে?\n" +
                "ছেলে : নাহ ও তো গাড়ি চালাতেই পারে না।"));
        arrayList5.add(new Smsinformation(5,"32","","পুলিশ বলছে চোরকে, ‘লজ্জা করে না তোমার? এই নিয়ে তৃতীয়বার তুমি থানায় এলে!’\n" +
                "চোর: স্যার, আমি তো মাত্র তৃতীয়বার, আপনি যে প্রতিদিনই আসেন!"));
        arrayList5.add(new Smsinformation(5,"33","","পুলিশ বলছে হাবিলদারকে, ‘তুমি কি চোরটাকে ধরতে পেরেছ?’\n" +
                "হাবিলদার: না, স্যার। তবে চোরের ফিংগার প্রিন্ট সঙ্গে করে এনেছি।\n" +
                "পুলিশ: কোথায়, দেখি?\n" +
                "হাবিলদার: স্যার, আমার গালে!"));
        arrayList5.add(new Smsinformation(5,"34","","প্রচণ্ড গতিতে মোটরসাইকেল চালিয়ে যাচ্ছিল বল্টু। পথে ট্রাফিক পুলিশ তার পথ রোধ করে দাঁড়াল।\n" +
                "ট্রাফিক পুলিশ: তোমার মতো বেয়াড়াদের ধরতেই দিনভর এখানে দাঁড়িয়ে অপেক্ষা করি, বুঝলে বাছাধন?\n" +
                "বল্টু: সে জন্যই তো যত দ্রুত সম্ভব আপনার কাছে পৌঁছাতে চেষ্টা করছিলাম!"));
        arrayList5.add(new Smsinformation(5,"35","","প্রথম পুলিশঃ স্টেডিয়ামে দর্শকেরা যেন দাঙ্গা না বাধায় তা দেখার জন্য কনস্টেবল মালেক সাদা পোশাকে ছিল।\n" +
                "দ্বিতীয় পুলিশঃ তারপর?\n" +
                "প্রথম পুলিশঃ দাঙ্গা পুলিশের মার খেয়ে এখন সে হাসপাতালে।"));
        arrayList5.add(new Smsinformation(5,"36","","পুলিশ: এত রাতে রাস্তায় রাস্তায় ঘুরছেন। এর কোনো পরিষ্কার ব্যাখ্যা দিতে পারবেন?\n" +
                "অভিযুক্ত: ব্যাখ্যা দিতে পারলে তো স্ত্রীকে বলে বাসাতেই ঢুকে পড়তাম।"));
        arrayList5.add(new Smsinformation(5,"37","","পত্রিকায় ছাপা হয়েছে একটি চাকরির বিজ্ঞাপন: এই প্রতিষ্ঠানে উচ্চ বেতনে একজন জ্যোতিষী নিয়োগ দেওয়া হবে। এ ব্যাপারে কোথায়, কখন, কার সঙ্গে দেখা করতে হবে, তা নিশ্চয় তাঁর জানা আছে!"));
        arrayList5.add(new Smsinformation(5,"38","","পরীক্ষার হল থেকে এক ছেলে স্যারের অনুমতি নিয়ে টয়লেটে গেল। টয়লেটে আগেই একটা বই রাখা ছিল, প্রশ্নের উত্তর বের করে পড়ে আসতে আসতে অনেকক্ষণ দেরি হয়ে গেল। ফিরে আসতেই স্যার কষে ধমক লাগালেন।\n" +
                "\n" +
                "-কী ব্যাপার, টয়লেট থেকে আসতে এতক্ষণ লাগল কেন? আর কেউ যাবে না নাকি?\n" +
                "\n" +
                "-না স্যার, ওরা ভালো করেই পড়াশোনা করে এসেছে।"));
        arrayList5.add(new Smsinformation(5,"39","","পাগলাগারদে সব পাগল নাচানাচি করছিল। শুধু একজন বসে ছিল চুপ করে। অন্য পাগলেরা জিগ্যেস করল, ‘কী হে, তুমি বসে আছ কেন?\n" +
                "\n" +
                "সে উত্তর দিল, ‘দূর ব্যাটা, বিয়েবাড়িতে জামাই কখনো নাচে?"));
        arrayList5.add(new Smsinformation(5,"40","","পাগলা গারদ সংক্রান্ত\n" +
                "২১২ জন যাত্রী নিয়ে জেট বিমানটি ৩৫ হাজার ফুট উপরে। হঠাৎ বিমানের পাইলট অট্রহাসি হাসতে লাগল। মাইক্রোফোনে সে হাসি শোনা গেল। দ্রুত ককপিটে গিয়ে একজন যাত্রী জনতে চাইল, এমনভাবে কেন হাসছেন, ক্যাপ্টেন?\n" +
                "\n" +
                "- আমি ভাবছি, সবাই কী ভাববে, যখন পাগলাগারদের ডাক্তার, নার্স, পাহাদাররা টের পাবে পাবে যে আমি পালিয়ে এসেছি। হা -হা-হা।"));
        arrayList5.add(new Smsinformation(5,"41","","পাগলা গারদের এক ডাক্তার তিন পাগলের পরীক্ষা নিচ্ছেন। পরীক্ষায় পাস করলে তিনজনকে পাগলাগারদ থেকে মুক্তি দেওয়া হবে, কিন্তু ফেল করলেই পাঁচ বছরের জন্য তাদের আটকে দেওয়া হবে। ডাক্তার তিন পাগলকে একটা ফাঁকা, জলবিহীন সুইমিং পুলের সামনে নিয়ে গিয়ে ঝাঁপাতে বললেন। প্রথম পাগলটি তৎক্ষণাৎ তাতে ঝাঁপ দিয়ে পা ভেঙে ফেলল। দ্বিতীয় পাগলটিও ডাক্তারের নির্দেশমতো পুলে ঝাঁপ দিল এবং হাত ভেঙে ফেলল। তৃতীয় পাগলটি কিন্তু কোনোমতেই ঝাঁপ দিতে রাজি হলো না। ডাক্তারটি উচ্ছ্বসিত হয়ে বললেন, ‘আরে, তুমি তো কামাল করে দিয়েছ! যাও, তুমি মুক্ত। তবে একটা কথা বলো, তুমি পুলে ঝাঁপ দিলে না কেন?’\n" +
                "\n" +
                "নির্দ্বিধায় পাগলটি জবাব দিল, ‘দেখুন ডাক্তারবাবু, আমি সাঁতারটা একেবারে জানি না!"));
        arrayList5.add(new Smsinformation(5,"12","","পাগলা গারদে\n" +
                "পরিদর্শকঃ আপনারা কীভাবে বোঝেন যে একজন রোগী সুস্থ হয়েছে?\n" +
                "\n" +
                "ডাক্তারঃ প্রথমে আমরা রোগীদের বাথরুমে বাথটাবের কাছে নিয়ে যাই। বাথটাবটা পানি দিয়ে পূর্ণ করি। তাদের আমরা একটি চামচ, একটি মগ ও একটি কাপ দিই। তারপর তাদের বাথটাবটি পানিশূন্য করতে বলি। তাদের পানিশূন্য করার নিয়ম দেখেই আমরা বুঝি কে সুস্থ হয়েছে।\n" +
                "\n" +
                "পরিদর্শকঃ যদি তারা মগ দিয়ে পানি ফেলে দেয়। তাহলে নিশ্চয়ই আপনারা তাকে সুস্থ বলেন?\n" +
                "\n" +
                "ডাক্তারঃ না, যদি তারা সম্পূর্ণ সুস্থ হয়, তবে বাথটাবের পানি বের করার প্লাগ তুলে ফেলবে, তবেই আমরা ধরে নেই, সেই রোগী এখন সুস্থ হয়েছে। তা, আপনার জন্য কী এখানকার একটা রুম বুক করব?"));
        arrayList5.add(new Smsinformation(5,"13","","প্রতিবেশীর কুকুরটার চিৎকারে বিরক্ত এক দম্পতি। এক মাঝরাতে বিছানা থেকে উঠেই গেলেন বাড়ির কর্তা। বললেন, অনেক হয়েছে। আজ এর একটা বিহিত করতে হবে। বলেই হনহন করে বেরিয়ে গেলেন তিনি।\n" +
                "\n" +
                "কিছুক্ষণ পর ফিরলেন। স্ত্রী জিজ্ঞেস করলেন, কি হেনস্তা করে এলে, শুনি?\n" +
                "\n" +
                "কর্তা: কুকুরটাকে আমাদের বাড়িতে নিয়ে এসেছি। এবার বুঝুক, প্রতিবেশীর কুকুরের চিৎকার কেমন লাগে!"));
        arrayList5.add(new Smsinformation(5,"44","","পাশের বাসার কলেজপড়ুয়া ছেলেটিকে ডেকে এনেছেন এক বৃদ্ধ। নিজে পড়ালেখা জানেন না। ছেলেটার হাতে একটা বিস্কুট ধরিয়ে দিয়ে বললেন, বাবা, আমার হয়ে একটা কাজ করে দেবে?\n" +
                "\n" +
                "- কী কাজ? বলেন।\n" +
                "\n" +
                "- তেমন কিছু না, যদি একটা চিঠি লিখে দিতে!\n" +
                "\n" +
                "- আচ্ছা ঠিক আছে, বলেন কী লিখতে হবে।\n" +
                "\n" +
                "- এরপর ঘণ্টাখানেক ধরে লোকটার কথানুযায়ী পুরো চিঠিটাই লিখে ফেলল ছেলেটা।\n" +
                "\n" +
                "- চিঠি লেখা হলে সে লোকটাকে জিজ্ঞেস করল, আর কিছু? বৃদ্ধ মাথা চুলকে বললেন, হ্যাঁ, চিঠির নিচে লিখে দাও—জঘন্য হাতের লেখা আর বানান ভুলের জন্য মার্জনা করিবেন।"));
        arrayList5.add(new Smsinformation(5,"45","","পল্টু: এখানে চুল কাটাতে কত টাকা লাগে?\n" +
                "\n" +
                "নাপিত: ৪০ টাকা।\n" +
                "\n" +
                "পল্টু: আর শেভ করতে?\n" +
                "\n" +
                "নাপিত: ২০ টাকা।\n" +
                "\n" +
                "পল্টু: আমার মাথাটা একটু শেভ করে দিন!"));
        arrayList5.add(new Smsinformation(5,"46","","পচাদা নিজের দোকানের নতুন কর্মচারি বান্টাকে বলল আমি বাড়ি থেকে আসছি, কোন খদ্দের ফেরাবি না। যা চাইছে তা দোকানে না থাকলে অন্য কোম্পানির কিছু একটা দিয়ে বলবি আজকের মত চালিয়ে নিতে, কাল এনে দেবো।\n" +
                "\n" +
                "খদ্দের :ভাই টয়লেট পেপার আছে?\n" +
                "\n" +
                "বান্টা:না দাদা, শিরিষ কাগজ আছে, আজকের মত চালিয়ে নিন, কাল এনে দেবো ।"));
        arrayList5.add(new Smsinformation(5,"47","","পাপ্পু তার ডাইরীতে লিখছে……\n" +
                "আজ আমার আপুর বাচ্চা হবে ,\n" +
                "কেউ জানে না বাচ্চাটা ছেলে হবে না মেয়ে হবে ,\n" +
                "তাই আমিও জানি না……\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                "আমি মামা হব নাকি মামী হব ।"));
        arrayList5.add(new Smsinformation(5,"48","","পরীক্ষার আগের রাতে এক ছাত্র পয়সা দিয়ে টস করতেসে ---\n" +
                "-\n" +
                "যদি শাপলা আসে তাইলে ঘুমায়া যামু,\n" +
                "- যদি মানুষ আসে তাইলে টিভি দেখমু,\n" +
                "-\n" +
                "যদি খারায়া থাকে তাইলে গেইমস খেলমু,\n" +
                "আর\n" +
                ". . .\n" +
                "যদি পয়সাটা আকাশে ভাসে\n" +
                "তাইলে খোদার কসম......\n" +
                "সারা রাত পরমু !!!"));
        arrayList5.add(new Smsinformation(5,"49","","প্রেমিকা : তুমি বিয়ের সময় আমার বাবার কাছ থেকে কি কি যৌতুক নিবা?\n" +
                "প্রেমিক : বেশি কিছু না, শুধু বাথরুম বানানুর জন্য ৫০ হাজার টাকা নিব।\n" +
                "প্রেমিকা : কি? বাথরুম বানানোর জন্য!!!\n" +
                "প্রেমিক : হ্যা,\n" +
                "প্রেমিকা : কেন?\n" +
                "প্রেমিক: কারন.. বিয়ের পর তুমি যখন ঝগড়া করে বলবে...\n" +
                "আমার বাবা বিয়েতে ৫০ হাজার টাকে দিছে ভুলে গেছো নাকি?\n" +
                "\n" +
                "তখন আমি বাথরুমে গিয়ে বলবো তুমার বাপের টাকায় আমি মুতি।"));
        arrayList5.add(new Smsinformation(5,"50","","প্রেমিক–প্রেমিকার মধ্যে কথা হচ্ছে।\n" +
                "প্রেমিক: আমি বোধহয় তোমাকে বিয়ে করতে পারব না।\n" +
                "প্রেমিকা: কেন?\n" +
                "প্রেমিক: আমার বাসায় ব্যাপারটা মেনে নেবে না।\n" +
                "প্রেমিকা: কে কে আছে তোমার বাসায়?\n" +
                "প্রেমিক: আমার স্ত্রী আর দুই সন্তান"));

        /**
         * 6
         */

        arrayList6.add(new Smsinformation(6,"1","","ফায়ার সার্ভিস অফিসে একটা ফোন এল।\n" +
                "\n" +
                "-হ্যাঁলো, এটা কি ফায়ার সার্ভিস অফিস?\n" +
                "\n" +
                "-হ্যাঁ, বলুন।\n" +
                "\n" +
                "-দেখুন, মাত্র কিছুদিন হলো আমি আমার ফুলের বাগান করেছি। ছোট্ট সুন্দর বাগান, নানা জাতের ফুল ফুটেছে\n" +
                "\n" +
                "-আগুন লেগেছে কোথায়?\n" +
                "\n" +
                "-গোলাপের চারাগুলো খুব দামি, অর্ডার দিয়ে বিদেশ থেকে আনিয়েছি\n" +
                "\n" +
                "-কোথায় আগুন লেগেছে তা-ই বলুন, শিগগির।\n" +
                "\n" +
                "-তাই তো বলছি। আগুন লেগেছে আমার পাশের বাড়ি। আপনারা তো এক্ষুনি আসবেন। তাই আগে থেকেই অনুরোধ করছি, আগুন নেভানোর ফাঁকে একটু পানি ছিটিয়ে দিয়েন, অনেক দিন পানি দেওয়া যাচ্ছে না।"));
        arrayList6.add(new Smsinformation(6,"2","","ফ্ল্যাট বাড়ির তিন তলায় ছয় বছরের এক ছেলে কলিংবেল বাজাবার চেষ্টা করছে কিন্তু নাগাল পাচ্ছে না। এক ভদ্রলোক তা দেখে কলিংবেল টিপে জিজ্ঞেসা করলেন, আবার বাজাবো কি?\n" +
                "ছেলেটি বলল, আমি তো পালাব। আপনি কি করবেন তা আমি কি জানি?"));
        arrayList6.add(new Smsinformation(6,"3","","বাংলাদেশের গাড়িগুলোকে বিশ্বমানে পৌঁছে দিতে হলে কী করতে হবে?\n" +
                "-বিশ্বমানকে বাংলাদেশের মানে নামিয়ে আনতে হবে।\n"));
        arrayList6.add(new Smsinformation(6,"4","",": বল তো পৃথিবীর সবচেয়ে দ্রুতগামী মানুষ কে ?\n" +
                ": কেন আদম পৃথিবীতে প্রথম ছুটে এসেছেন, তিনিই তো।"));
        arrayList6.add(new Smsinformation(6,"5","","-বলুন তো, কাজুবাদাম আর স্বামীর মধ্যে সবচেয়ে বড় পার্থক্য কী?\n" +
                "-কাজুবাদাম চিবানো হয় পকেটে টাকা থাকলে, স্বামীকে চিবানো হয় টাকা না থাকলে।"));
        arrayList6.add(new Smsinformation(6,"6","","বাড়িতে অতিথি বেড়াতে এসেছে বাসায় ছোট বাচ্ছার সঙ্গে খাতির জমানোর চেষ্টা করছে-\n" +
                "অতিথি : কাছে এস বাবু আমাকে একটু চুমু দাও। তাহলে তোমাকে পাঁচ টাকা দেব।\n" +
                "বাবু : শুধু অষুধ খাওয়াতেই মা এর থেকে বেশি দেয়।"));
        arrayList6.add(new Smsinformation(6,"7","","বস : এ কী টাইপিষ্ট নিয়েছেন? সুন্দরী তাতে সন্দেহ নেই-কিন্তু প্রতিটি লাইনে এক গন্ডা ভুল। আপনাকে বলি নি, টাইপিষ্ট নেবার সময় গ্রামারের দিকে নজর রাখবেন।\n" +
                "ম্যানেজার : শুনতে ভুল হয়েছিল স্যার। আমি গ্ল্যামারের দিকে নজর রেখেছিলাম।"));
        arrayList6.add(new Smsinformation(6,"8","","বড় কর্তার সেদিন মেজাজ খুবই খারাপ। অফিসে ঢুকেই দেখলেন পিয়নটা হাতে কিছু কাগজ নিয়ে ঘুরে বেড়াচ্ছে।\n" +
                "বড় কর্তা: এই, কাজের কাজ তো কিছু করিস না। হাতে কী?\n" +
                "পিয়ন: স্যার চিঠি।\n" +
                "বড় কর্তা: কোন ছাগলের চিঠি?\n" +
                "পিয়ন: স্যার আপনার।\n" +
                "বড় কর্তা: কোন গাধা লিখেছে?\n" +
                "পিয়ন: স্যার আপনার বাবা!"));
        arrayList6.add(new Smsinformation(6,"9","","বসের সঙ্গে ফোনে কথা হচ্ছে কর্মচারীর।\n" +
                "কর্মচারী: স্যার, আজকে আমার শরীরটা খুব খারাপ। আজ অফিসে আসতে পারব না।\n" +
                "বস: শরীর খারাপ থাকলে আমি কী করি জানো? আমার প্রেমিকার সঙ্গে রিকশায় ঘুরে বেড়াই, বেশ ভালো লাগে। তুমিও চেষ্টা করে দেখতে পারো।\n" +
                "কিছুক্ষণ পর বসকে ফোন করলেন কর্মচারী। বললেন, ‘স্যার, আপনার বুদ্ধিটা বেশ কাজে লেগেছে। রিকশায় ঘুরে খুব ভালো লাগছে। আপনার প্রেমিকাও বেশ স্মার্ট, রিকশা ভাড়াটা সেই দেবে বলেছে…!"));
        arrayList6.add(new Smsinformation(6,"10","","বস বলছেন কর্মচারীকে, ‘আপনি কি মৃত্যুর পরের জনমে বিশ্বাস করেন?’\n" +
                "কর্মচারী: জি স্যার।\n" +
                "বস: হুমম্, করারই কথা। গতকাল আপনি মায়ের মৃত্যুবার্ষিকীর কথা বলে অফিস থেকে ছুটি নেওয়ার পর আপনার মা অফিসে এসেছিলেন, আপনার সঙ্গে দেখা করতে!"));
        arrayList6.add(new Smsinformation(6,"11","","বস-কর্মকর্তার মধ্যে কথা হচ্ছে—\n" +
                "কর্মকর্তা: স্যার, এবার আমার বেতনটা একটু বাড়িয়ে দিলে ভালো হতো।\n" +
                "বস: কেন?\n" +
                "কর্মকর্তা: গত সপ্তাহে বিয়ে করেছি। তাই আগের বেতনে দুজনের চলাটা বেশ কষ্ট হবে, স্যার।\n" +
                "বস: শুনুন, অফিসের বাইরের কোনো দুর্ঘটনার জন্য অফিস কোনোভাবেই দায়ী নয়। আর তার জন্য জরিমানা দিতেও অফিস রাজি নয়।"));
        arrayList6.add(new Smsinformation(6,"12","","বস : কী ব্যাপার অফিস ৯ টায় শুরু তুমি এসেছো ৫ টায়?\n" +
                "কর্মচারী : স্যার আজ বিয়ে করলাম যে।\n" +
                "বস : দেখ এই ঘটনা যেন আর না হয়।"));
        arrayList6.add(new Smsinformation(6,"13","","বস : এ কী! আজ অফিসের হিসাব মিলাবার শেষ তারিখ আর ক্যাশিয়ার নেই? কোথায় গেছেন তিনি?\n" +
                "কেরানি : তিনি গেছেন রেস খেলতে, স্যার।\n" +
                "বস : রেস খেলতে? আমি কি ঠিক শুনছি?\n" +
                "কেরানি : জি স্যার। যাবার আগে বলে গেছেন ক্যাশ মেলাবার এটাই তার শেষ সুযোগ।"));
        arrayList6.add(new Smsinformation(6,"14","","বড় বাবু টেলিফোন ধরে শুনলেন , অন্য দিক খুব বয়স্ক একজন লোক কাঁপা কাঁপা গলায় বলছেন – মাফ করবেন আপনাদের অফিসের সুভাষকে একটু ডেকে দেবেন?\n" +
                "কে বলেছেন ?- বড় বাবু জিজ্ঞেস করলেন\n" +
                "আমি ওর ঠাকুদা বলছি – জবাব এলো\n" +
                "বড় বাবু এবার গম্ভীর ভাবে বললেন- দুঃখিত সুভাষ আফিসে নেই । সে আপনাকে পোড়াতে গিয়েছে।"));
        arrayList6.add(new Smsinformation(6,"15","","বাড়িতে অতিথি এসেছেন। মা পল্টুকে ডেকে বললেন, ‘বাবা পল্টু, জলদি অতিথিদের জন্য বাইরে থেকে একটা কিছু নিয়ে এসো তো’।\n" +
                "দৌড়ে ঘর থেকে বেরিয়ে গেল পল্টু। কিছুক্ষণ পর ফিরল খালি হাতে।\n" +
                "মা: কী হলো? কী আনলে ওনাদের জন্য?\n" +
                "পল্টু: ট্যাক্সি! ওনারা যেন চটজলদি বাড়ি ফিরতে পারেন!"));
        arrayList6.add(new Smsinformation(6,"16","","বিচারক: আপনার অপরাধ?\n" +
                "অভিযুক্ত ব্যক্তি: আমি আমার ঈদের কেনাকাটা একটু আগেভাগে সেরে ফেলতে চেয়েছিলাম।\n" +
                "বিচারক: কতখানি আগে?\n" +
                "অভিযুক্ত ব্যক্তি: দোকান খোলার আগে।"));
        arrayList6.add(new Smsinformation(6,"17","","বিচারক আসামিকে:\n" +
                "—নকল টাকা বানিয়েছিলেন কেন?\n" +
                "—আসল টাকা বানাতে শিখিনি বলে।"));
        arrayList6.add(new Smsinformation(6,"18","","বিচারক : তোমার বয়স কত?\n" +
                "আসামি : সাত বছর\n" +
                "বিচারক : এটুকু বয়সেই পকেটমার শুরু করেছ?\n" +
                "আসামি : তা হলে আপনিই বলে দিন কত বছর বয়স থেকে শুরু করব?"));
        arrayList6.add(new Smsinformation(6,"20","","বিচারক : এক বছরের জেল না ১০ হাজার টাকা জরিমানা, কোনটা চাও তুমি?\n" +
                "আসামি : টাকাটাই দিন জনাব… ওটাই আমার বেশি প্রয়োজন।"));
        arrayList6.add(new Smsinformation(6,"21","","বিচারক : আপনি কি দোষী না নির্দোষ?\n" +
                "আসামি : রায় দেবার জন্য বেতন কি আপনি পান না আমি?\n"));
        arrayList6.add(new Smsinformation(6,"22","","বিচারক : তুমি দোকান থেকে নতুন স্যুট চুরি করলে কেন?\n" +
                "আসামি : ছেঁড়া জামাকাপড় পরে আপনার সামনে আসতে লজ্জা করবে তাই।"));
        arrayList6.add(new Smsinformation(6,"23","","বিচারক : তুমি পকেট মারতে গিয়ে ধরা পড়েছে। তোমার দোষ স্বীকারে আপত্তি আছে?\n" +
                "আসামি : আমি নিরপরাধ হুজুর। ধরা পড়ার জন্য আমি দায়ী নই। লোকটার পকেট এত ছোট ছিল যে, হাতটা টুকিয়ে আর বের করতে পারি না।"));
        arrayList6.add(new Smsinformation(6,"24","","বিচারক : আপনি বলেছেন- আপনার বন্ধুর সঙ্গে অবৈধ প্রণয় চলছিল বলে বউকে খুন করেছেন। কিন্তু আপনি আপনার বন্ধুকে খুন না করে বউকে খুন করলেন কেন?\n" +
                "আসামি : হুজুর আমার অনেক বন্ধু। সপ্তায় একজন করে বন্ধুকে মারার চেয়ে বউকে মারাই সহজ মনে হল তাই।"));
        arrayList6.add(new Smsinformation(6,"25","","বিচারক : রাত বারটার সময় খিড়কি দরজা দিয়ে তুমি এই মহিলার বাড়িতে ঢুকেছিলে কেন?\n" +
                "আসামি : ভেবেছিলাম, ওটা আমার নিজের বাড়ি।\n" +
                "বিচারক : বেশ, যদি তাই হয়, তা হলে এই মহিলাকে দেখে জানালা টপকে চৌবাচ্চার আড়ালে লুকাতে গেলে কেন?\n" +
                "আসামি : ভেবেছিলাম, ইনি আমার স্ত্রী।"));
        arrayList6.add(new Smsinformation(6,"26","","বিচারক : এই নিয়ে তোমাকে ছয় বার আমার কোর্টে আসতে হল। লজ্জায় তোমার মরে যাওয়া উচিত। কতখানি জানোয়ার হলে মানুষ এত শাস্তি পাওয়ার পরও আবার মদ খায়! গলায় দড়ি দিয়ে তোমার আত্মহত্যা করা উচিত।\n" +
                "আসামি : স্যার, কথাটা সত্যি। তবে আমিও তো আপনাকে এই আদালতে ছয় বার দেখেছি, কিন্তু সেজন্য আমি তো আপনাকে কোনোদিন এভাবে গালাগাল করি নি।"));
        arrayList6.add(new Smsinformation(6,"27","","বিচারক: তুমি কেন গাড়িটি চুরি করেছিলে?\n" +
                "আসামি: আমি দেখেছিলাম গাড়িটি একটি কবরখানার বাইরে দাঁড় করানো। তাই ভেবেছিলাম গাড়ির মালিকের আর এটা হয়তো দরকার নেই।"));
        arrayList6.add(new Smsinformation(6,"28","","বিচারক কাঠগড়ার দিকে তাকিয়ে: আপনি মুক্ত।\n" +
                "আসামি: মানে কী?\n" +
                "বিচারক: মানে হচ্ছে, আপনার বিরুদ্ধে যে অভিযোগ আনা হয়েছিল, তা প্রমাণ করা যায়নি।\n" +
                "আসামি: তার মানে লুটের টাকা আমি নিজের কাছেই রাখতে পারব?"));
        arrayList6.add(new Smsinformation(6,"29","","বিচারক : চুরি করার সময় একবারও তোমার স্ত্রী আর মেয়ের কথা মনে হয় নি?\n" +
                "চোর : হয়েছে, হুজুর। কিন্তু দোকানটায় শুধু ব্যাটাছেলের কাপড়ই ছিল।"));
        arrayList6.add(new Smsinformation(6,"30","","বিচারক : নিজ হাতে তুমি তোমার বাবা-মাকে গুলি করে মেরেছ। এ\u200Cটা স্বীকার করার পর তোমার আর কী বলার আছে ?\n" +
                "আসামী : এখন আমি এতিম। এতিমের অপরাধ মাফ করে দিন, হুজুর।"));
        arrayList6.add(new Smsinformation(6,"31","","বিচারপতিঃ আমি এবার এই মামলার রায় জানাব। আমি কথা বলার সময় কেউ কথা বলবেন না। বলতে চাইলে আদালতকক্ষ থেকে বের হয়ে যান।\n" +
                "আসামিঃ স্যার, আমি কথা বলতে চাই, আমাকে বের করে দিন।"));
        arrayList6.add(new Smsinformation(6,"32","","বিচারকঃ কী হে ? তুমি ওই বাড়ির তালা ভাঙ্গতে গেলে কেন ?\n" +
                "চোরঃ কি করব স্যার আমার কাছে যে চাবি গুলো ছিল তার একটা দিয়েও তালাটা খুলতে পাছিলাম না যে, তাই"));
        arrayList6.add(new Smsinformation(6,"33","","বিচারকঃ একমাত্র আজে বাজে নেশার কারণেই আজ তোমার এই দশা !\n" +
                "অভিযুক্তঃ ধন্যবাদ হুজুর। একমাত্র আপনিই আমার কোন দোষ দিলেন না।"));
        arrayList6.add(new Smsinformation(6,"34","","বিচারকঃ আপনার পেশা কী?\n" +
                "আসামিঃ জি, আমি তালা এক্সপার্ট।\n" +
                "বিচারকঃ তালা এক্সপার্ট! আপনার কাজ তো দিনে থাকার কথা। কিন্তু রাত দুটোয় আপনি সোনার দোকানে কী করছিলেন?\n" +
                "আসামিঃ ওদের গেটের তালাটা ঠিক আছে কিনা পরখ করে দেখছিলাম!"));
        arrayList6.add(new Smsinformation(6,"35","","বস: আমরা কাউকে চাকরি দেওয়ার ক্ষেত্রে মাত্র দুইটা নিয়ম মেনে চলি।\n" +
                "প্রার্থী: কী কী স্যার?\n" +
                "বস: আমাদের দ্বিতীয় নিয়ম হচ্ছে পরিষ্কার-পরিচ্ছন্নতা। আপনি কি এখানে আসার আগে রুমের বাইরে রাখা ম্যাটে জুতার তলা মুছে এসেছেন?\n" +
                "প্রার্থী: জি স্যার।\n" +
                "বস: আমাদের প্রথম নিয়ম হলো বিশ্বাসযোগ্যতা এবং আপনার অবগতির জন্য জানানো যাচ্ছে যে বাইরে কোনো ম্যাটই ছিল না! কাজেই আপনাকে বিশ্বাস করা যাচ্ছে না।"));
        arrayList6.add(new Smsinformation(6,"36","","বস: আপনি সাঁতার জানেন?\n" +
                "চাকরিপ্রার্থী: জি না।\n" +
                "বস: জাহাজের ক্যাপ্টেন পদে চাকরির জন্য আবেদন করেছেন, আর সাঁতার জানেন না?\n" +
                "চাকরিপ্রার্থী: কিছু মনে করবেন না স্যার। উড়োজাহাজের পাইলট কি উড়তে জানে?"));
        arrayList6.add(new Smsinformation(6,"37","","বিমানবাহিনীতে একটি ইন্টারভিউ।\n" +
                "–সেনাবাহিনী, নৌবাহিনী এবং বিমানবাহিনী- এই তিনটির মধ্যে বিমানবাহিনীকে পছন্দ করলে কেন?\n" +
                "–একমাত্র বিমানবাহিনীর যোদ্ধারাই পালানোর সময় ঘন্টায় ৫০০ মাইল বেগে পালাতে পারে।\n"));
        arrayList6.add(new Smsinformation(6,"38","","বাড়ির কর্ত্রী ছোকরা চাকরকে ডেকে ডেকে সারা হয়ে শেষ পর্যন্ত কানটা ধরে নিয়ে এলেন।\n" +
                "কর্ত্রীঃ ‘মুখে কথা নেই কেন? আমি এদিকে ডেকে ডেকে হয়রান!’ তবু ছোকরা চুপচাপ দাঁড়িয়ে থাকে, কোনো কিছু বলে না। রাগে কর্ত্রী কয়েক ঘা বসিয়ে দিলেন এবং আশা ছেড়ে বসে পড়লেন। তাঁর দুরবস্থা দেখে প্রতিবেশী গৃহিণী এগিয়ে এলেন।\n" +
                "প্রতিবেশী গৃহিণীঃ কথার জবাব দাও না কেন? তাহলে তো এত হাঙ্গামা হয় না।\n" +
                "এবার চাকর ছোকরা কাঁদো কাঁদো হয়ে মুখ খুলল, ‘কেমনে দিমু, মুহে মুহে জবাব দেওন যে বিবি সাহেব মানা কইরা দিছেন।"));
        arrayList6.add(new Smsinformation(6,"39","","বাবুর্চি রান্না করছিল।\n" +
                "গৃহকর্ত্রী ধমকে উঠলেন, এ কী, তুমি না ধুয়েই মাছ রান্না করছ!\n" +
                "–মাছ তো সারা জীবন পানিতেই ছিল মেমসাহেব, ওটা আবার ধোয়ার কী দরকার।"));
        arrayList6.add(new Smsinformation(6,"40","","বাড়ীর কর্তা(নতুন কাজের লোক কে): ঠিক আছে তুমি আজ থেকে কাজে লেগে যাও। প্রতিদিন ২০ টাকা করে পাবে । চার মাস পর থেকে ৪০ টাকা করে পাবে।\n" +
                "কাজের লোক: আমি তাহলে চার মাস পরেই আসবো।"));
        arrayList6.add(new Smsinformation(6,"41","",": বলো তো হাতি কোথায় খুঁজে পাওয়া যায়?\n" +
                ": হাতি কখনো খুঁজতে হয় না। হাতি এত বড় যে কখনো হারায় না।"));
        arrayList6.add(new Smsinformation(6,"12","","বিমান চলা অবস্থায় জানালা দিয়ে হাত কীভাবে বের করা যায় তা অবশ্য আমি এখনো জানি না।\n" +
                "আমেরিকার এক লোক, রাশিয়ার এক লোক এবং বাংলাদেশের এক লোক বিমােন করে যাচ্ছিলেন। রাশিয়ার লোকটি হাত বের করে বললেন, ‘আমরা এখন সাইবেরিয়ার ওপর দিয়ে যাচ্ছি।’ কেমন করে বুঝলেন জিজ্ঞেস করাতে উনি বললেন, কারণ বাইরে অনেক ঠান্ডা।\n" +
                "কিছুক্ষণ পর আমেরিকার লোকটি হাত বের করে বললেন, ‘আমরা এখন নিউইয়র্কের ওপর দিয়ে যাচ্ছি, কারণ এম্পায়ার স্টেট বিল্ডিংয়ের সঙ্গে গুঁতা খেয়ে আমার হাত ছিলে গেছে।’\n" +
                "শেষমেশ বাংলাদেশের জন হাত বের করে বললেন, ‘আমরা এখন গুলিস্তানের ওপর দিয়ে যাচ্ছি।’ বাকি সবাই জিজ্ঞেস করাতে উনি বললেন, ‘কারণ আমার হাতে একটা ক্যাসিও ঘড়ি ছিল, সেটা নাই হয়ে গেছে।"));
        arrayList6.add(new Smsinformation(6,"13","","বলুন দেখি, সত্য এবং মিথ্যার মধ্যে পার্থক্য কী?\n" +
                "সত্য বলে ফেললেই হয়। কিন্তু মিথ্যা বলার পর মনে রাখতে হয়!"));
        arrayList6.add(new Smsinformation(6,"44","","ব্যাঙ্ক ডাকাতি করে ডাকাত-দল চলে যাচ্ছে।\n" +
                "এক ডাকাত বলল, যাওয়ার আগে টাকাটা একবার গুনে নিলে হত না, ওস্তাদ?\n" +
                "সর্দার বলল, গোনার দরকার নেই। কাল পত্রিকা দেখলেই হবে।"));
        arrayList6.add(new Smsinformation(6,"45","","বাসস্ট্যান্ডে দাঁড়িয়ে ছিল এক তরুণ আর এক তরুণী।\n" +
                "\n" +
                "তরুণ: বাহ্, লিপস্টিকের রংটা বেশ।\n" +
                "\n" +
                "তরুণী: ধন্যবাদ।\n" +
                "\n" +
                "তরুণ: কানের দুলটাও খুব সুন্দর।\n" +
                "\n" +
                "তরুণী: ধন্যবাদ।\n" +
                "\n" +
                "তরুণ: হাতের চুড়িগুলো খুব মানিয়েছে।\n" +
                "\n" +
                "তরুণী: ধন্যবাদ ভাইয়া।\n" +
                "\n" +
                "তরুণ: বলছিলাম, তবু আপনাকে দেখতে একেবারেই ভালো দেখাচ্ছে না!"));
        arrayList6.add(new Smsinformation(6,"46","","বুড়িগঙ্গার ময়লা পানিতে এক লোককে নামতে দেখে সাংবাদিক প্রশ্ন করলেন, ‘ভাই, নদীতে কী করছেন?\n" +
                "\n" +
                "- গোসল করছি।-\n" +
                "\n" +
                "- কিন্তু নদীর পানি তো খুবই ময়লা।\n" +
                "\n" +
                "- সমস্যা নাই। সাবান দিয়ে গোসল করছি।"));
        arrayList6.add(new Smsinformation(6,"47","","বাবা: খোকা, পরীক্ষা কেমন দিলি?\n" +
                "\n" +
                "ছেলে: শুধু একটা উত্তর ভুল হয়েছে।\n" +
                "\n" +
                "বাবা: বাহ্! বাকিগুলো সঠিক হয়েছে?\n" +
                "\n" +
                "ছেলে: না, বাকি গুলোতে লিখতেই পারিনি।"));
        arrayList6.add(new Smsinformation(6,"48","","বাংলা ক্লাসে শিক্ষক এক ছাত্রকে দাঁড় করিয়ে জিজ্ঞাসা করলেন- বলতো ভাতের অভাব এটা কি হবে?\n" +
                "\n" +
                "ছাত্রটি জবাব দিতে না পারায় শিক্ষক তাকে অনেক বেত্রাঘাত করলেন এবং বললেন ভাতের অভাব - হাভাত।\n" +
                "\n" +
                "অতঃপর বিজ্ঞান ক্লাসে বিজ্ঞানের শিক্ষক ঐ ছাত্রটিকে জিজ্ঞাসা করলেন পানির অভাবে কি হয়?\n" +
                "\n" +
                "তখন ঐ ছাত্রটি ঝটপট করে জবাব দিল, স্যার হাঁপানি হয়।"));
        arrayList6.add(new Smsinformation(6,"49","","বন্ধুর নতুন বাসা ঘুরে ঘুরে দেখছিলেন রকিব। দেয়ালে একটা পিতলের থালা আর একটা হাতুড়ি ঝোলানো দেখে জিজ্ঞেস করলেন, এটা কী?\n" +
                "\n" +
                "বন্ধু বললেন, এটা একটা ‘কথা বলা ঘড়ি’।\n" +
                "\n" +
                "রকিব: তাই নাকি? দেখি তো কেমন কথা বলে?\n" +
                "\n" +
                "বন্ধু হাতুড়ি দিয়ে থালায় আঘাত করলেন, প্রচণ্ড শব্দ হলো। সঙ্গে সঙ্গে দেয়ালের ওপাশ থেকে প্রতিবেশী চিৎকার করে বললেন, নালায়েক! রাত ১০টার সময় কেউ এত জোরে শব্দ করে?"));
        arrayList6.add(new Smsinformation(6,"50","","বাড়ির সামনে প্রতিবেশী বাচ্চাগুলোকে খেলতে দেখে রহমান সাহেব বললেন, বাচ্চারা, খেলছ ভালো কথা। কিন্তু আমার গাড়িতে যেন বল না লাগে।\n" +
                "\n" +
                "এক বাচ্চা বলে উঠল, অবশ্যই আঙ্কেল, আপনার গাড়িটাই তো আমাদের গোলপোস্ট। আমরা গোল হতে দিলে তো!"));


        /**
         * 7
         */

        arrayList7.add(new Smsinformation(7,"1","","বল্টু স্কুল থেকে S.S.C. পরীক্ষার রেজাল্ট নিয়ে বাড়ি ফিরেছে..\n" +
                "বল্টুর বাবাঃ রেজাল্ট কি বল্টু?\n" +
                "বল্টুঃ বাবা, আমার এক ফ্রেন্ড B পাইছে, ওর বাবা ওরে Brazil- এ ঘুরতে নিয়া যাইবো!\n" +
                "বাবাঃ বুঝলাম, তোর রেজাল্ট কি....?\n" +
                "বল্টুঃআমার আরেক ফ্রেন্ড C পাইছে, ওর বাবা ওরে Canada-তে ঘুরতে নিয়া যাইবো..!!\n" +
                "বাবাঃ বুঝলাম, এইবার তোরটা বল....।\n" +
                "বল্টুঃ আমার আরেক বন্ধু A পাইছে ওর বাবা ওরে America- তে ঘুরতে নিয়া যাইবো...!!\n" +
                "বাবাঃ হারামজাদা..!!থাপ্পড় খাবি, নিজের রেজাল্টের খবর নাই?"));
        arrayList7.add(new Smsinformation(7,"2","","বল্টুঃ বাবা, আমারে তোমার France-এ ঘুরতে নিয়া যাইতে হইবো....!!!\n" +
                "কি বুঝলেন? বল্টু কি পাইছে বলেন তো দেখি?"));
        arrayList7.add(new Smsinformation(7,"3","","বল্টু: তুই তোর বউর সাথে ঝগড়া করিস?\n" +
                "\n" +
                "পল্টু: হ্যাঁ, করি। তবে প্রতিবার ঝগড়ার শেষে ও এসে হাঁটু গেড়ে আমার সামনে বসে পড়ে।\n" +
                "\n" +
                "বল্টু: বলিস কী! তারপর?\n" +
                "\n" +
                "পল্টু: তারপর মাথা ঝুঁকিয়ে বলে, ‘খাটের তলা থেকে বেরিয়ে আসো। আর মারব না।'"));
        arrayList7.add(new Smsinformation(7,"4","","বল্টু বাসে করে যাচ্ছিল। এক লোক পকেট থেকে লেবু বের করে নাকে শুকতে লাগল।\n" +
                "বল্টু : লেবু শুকছেন কেন..?\n" +
                "লোক : বমি না আসার জন্য। (কিছুক্ষন পরেই লোকটি বমি করে দিল।)\n" +
                "বল্টু : লেবু শুকেও বমি করে দিলেন..?\n" +
                "লোক : ভাই,"));
        arrayList7.add(new Smsinformation(7,"5","","স্যারঃ বলতো বল্টু কোন জিনিসটা জ্যান্ত থাকলে দাম কম কিন্তু মারা গেলে দাম বেড়ে যায়?\n" +
                "•\n" +
                "•\n" +
                "•\n" +
                "•\n" +
                "•\n" +
                "বল্টুঃ মুরগি স্যার।\n" +
                "•\n" +
                "•\n" +
                "•\n" +
                "•\n" +
                "•\n" +
                "স্যারঃ মুরগি!!!কিভাবে বুঝা আমারে।\n" +
                "•\n" +
                "•\n" +
                "•\n" +
                "•\n" +
                "•\n" +
                "বল্টুঃস্যার জ্যান্ত মুরগি কেজি ১৬০ টাকা।আর গ্রিল করা মরা মুরগি ৩২০ টাকা।"));
        arrayList7.add(new Smsinformation(7,"6","","শিক্ষকঃ বল্টু,বল সন্ধি\n" +
                "কাকে বলে???\n" +
                "বল্টুঃ স্যার, প্রথমটুকু পারি না………\n" +
                "শেষের টুকু পারি………।\n" +
                "শিক্ষকঃ মনে মনে বলছেন..(বল্টুর মতন খারাপ ছাত্র সন্ধি শেষের টুকু পারলেও ভাল) তাই তিনি বললেন, বল শেষের টুকুই বল।\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                "বল্টুঃ স্যার,\n" +
                "শেষেরটুকু হল………\n" +
                "তাকে সন্ধি বলে……"));
        arrayList7.add(new Smsinformation(7,"7","","স্যারঃ বলতো ভালবাসার ওজন কত কেজি?\n" +
                "বল্টু : ৮০ কেজি স্যার।\n" +
                "স্যারঃ হা হা হা..... কিভাবে?\n" +
                "বল্টু :'ভালবাসা হচ্ছে ২টি মনের মিলন, আর আমরা জানি যে...১মন=৪০ কেজি\n" +
                "সুতারাং,\n" +
                "২মন=৪০+৪০কেজি=৮০ কেজি।' (প্রমাণিত)"));
        arrayList7.add(new Smsinformation(7,"8","","স্যার: যদি একটা বাস ঘন্টায় ১০ কি.মি. যায়, তাহলে আমার বয়স কত?\n" +
                "সবাই তো অবাক এইটা কোনো প্রশ্ন হইলো।\n" +
                "পিছন খেকে বল্টু হাত তুললো।\n" +
                "স্যার: বল বল্টু।\n" +
                "বল্টু: আপনার বয়স ৫০।\n" +
                "স্যার: good, কিভাবে পারলি।\n" +
                "...\n" +
                "...\n" +
                "বলটু: স্যার আমাদের এলাকার হাফ পাগল আছে। তার বয়স ২৫ আর আপনি হয়লো ফুল পাগল তাই আপনার বয়স ৫০।।"));
        arrayList7.add(new Smsinformation(7,"9","","বল্টু এখন ভবিষ্যত বলে দেওয়ার ব্যাবসা শুরু করছে।।\n" +
                ".\n" +
                ".\n" +
                "তাই এক মেয়ে তার কাছে গিয়ে বলল..\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                "মেয়েঃ বাবা, আমাকে ভবিষ্যত্ দেখানো শিখিয়ে দিন।\n" +
                ".\n" +
                ".\n" +
                "বল্টুঃ- চোখ বন্ধ করে তোমার গাল আমার কাছে নিয়ে আসো বালিকা।\n" +
                ".\n" +
                ".\n" +
                "মেয়েঃ- না।\n" +
                ".\n" +
                ".\n" +
                "বল্টুঃ- কেন? . .\n" +
                "মেয়েঃ- আপনি আমাকে Kiss করবেন।\n" +
                "\n" +
                "বল্টুঃ- আরে বাহ, তুমি তো ভবিষ্যত্ দেখা শিখে গেছো।।।"));
        arrayList7.add(new Smsinformation(7,"10","","বল্টু গেছে এক কোল্ড ড্রিঙ্কস এর দোকানে সেখানে গিয়ে বলছে\n" +
                "-একটা পেপসি এর বোতল খুলো ভাই!!\n" +
                "দোকানদার খুলল\n" +
                ".\n" +
                "আবার বলল “একটা 7- Up ও খোল,\n" +
                "দোকানদার খুলল\n" +
                ".\n" +
                "আবার বলল “\n" +
                "একটা স্প্রাইট এর বোতল খুলো,\n" +
                "দোকানদার খুলল\n" +
                ".\n" +
                "আবার বলল\n" +
                "একটা মাউন্টেন ডিউ এর বোতল খুলো,\n" +
                "দোকানদার এখন রাগ হয়ে গেলো আর বলল “ আরে তুই খাবি কোনটা??\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                "বল্টু : ভাইজান খামুনা !\n" +
                "আমার এই বোতলের ঢাকনা খুলার ঠুস ঠুস আওয়াজ শুনতে খুব মজা লাগে"));
        arrayList7.add(new Smsinformation(7,"11","","মেয়ে:জানু একটা গান শোনাও Plz\n" +
                "বল্টু:কি গান??\n" +
                "মেয়ে:তোমার ইচ্ছা\n" +
                "বল্টু:তাইলে শোন...\n" +
                ".\n" +
                ".\n" +
                "তু খিচ মেরি ফটো\n" +
                "তর বাপে চালায় অটো,\n" +
                "তর চাচা চালায় হুন্ডা\n" +
                "তর চৌদ্দ গুষ্টি গুন্ডা!!\n" +
                "মেয়ে তো পুরাই"));
        arrayList7.add(new Smsinformation(7,"12","","বল্টু মারা গেছে...\n" +
                "বল্টু, র্স্বগতে গিয়ে দেখলো ওখানে প্রচুর ঘড়ি রাখা আছে।\n" +
                "বল্টু দেবদুত কে জিজ্ঞাসা করল, এখানে এত ঘড়ি কেন রাখা আছে?\n" +
                "দেবদুত : এগুলো হল মিথ্যা ঘড়ি, সবার জন্য একটা করে ঘড়ি আছে। যখনই কেউ মিথ্যা বলবে, তখনই তার ঘড়ি ঘুরবে।\n" +
                "বল্টু তখন একটা ঘড়ি দেখিয়ে জিজ্ঞাসা করল, এটা কার ঘড়ি, একদমই ঘোরেনি?\n" +
                "দেবদুত : এটা মাদার তেরেসার ঘড়ি, এটা কখনই ঘোরেনা। কারন উনি কখনো মিথ্যা বলেন না।\n" +
                "বল্টু তখন খুব উৎসাহিত হল এবং জিজ্ঞাসা করল : আচ্ছা, আমার ঘড়িটা কোথায়? এখানে দেখতে পাচ্ছিনা তো !!\n" +
                "দেবদুত : ওটা আমাদের অফিসে রাখা আছে, ওটা আমরা এখন টেবিল ফ্যান হিসাবে ব্যবহার করি"));
        arrayList7.add(new Smsinformation(7,"13","","শিক্ষক:- বল্টু, তুই কয়টা গান পারস ??\n" +
                "বল্টু:- ৪ টা গান পারি ।\n" +
                "শিক্ষক:- দেখি কি কি গান জানস, গা একটু\n" +
                "বল্টু:-\n" +
                "1' ® ও কালী, পকেট খালি, জানু তুই আমারে কি ভাবে ছ্যাকা দিলি??\n" +
                "2' ® ধাক ধাক কার নে লাগা, হালা রে ধইরা গালে 2 টা থাপ্পর লাগা!!\n" +
                "3' ® দিল তুহি হে বাতা, কেনো তোমার বাথরুমের বদনা ফাঁটা! ??\n" +
                "4' ® ধুম মা চালে ধুম মা চালে ধুম, কাল অবরোধ দিবো সেই লেভেলের ঘুম!!\n" +
                "শিক্ষক:- বেহুশ ....."));
        arrayList7.add(new Smsinformation(7,"14","","শিক্ষক:- বল্টু, বলত এই পৃথিবীতে মোট কয়টি দেশ?\n" +
                "বল্টু:- ১ টা স্যার।\n" +
                "শিক্ষক:- মানে !??\n" +
                "বল্টু:- জী স্যার , বাকি গুলিতো সব বিদেশ।\n" +
                "শিক্ষক:- বেহুশ ..."));
        arrayList7.add(new Smsinformation(7,"15","","মা : শুনলাম তুমি নাকি ইদানিং সিগারেট খাওয়া শুরু করেছো। কথাটা কি সত্যি ?\n" +
                "বল্টু : হ্যাঁ মা সত্যি কথা।\n" +
                "মা : শুনে ভালো লাগলো যে তুমি সত্যি কথা বলা শুরু করেছো। আচ্ছা যা খেয়েছো খেয়েছো, আর খেয়ো না। আচ্ছা আমি কি জানতে পারি যে, তুমি হঠাত্ সিগারেট খাওয়া শুরু করলে কেন ?\n" +
                "বল্টু : এটা আমি দেশ ও দশের কথা ভেবে খাওয়া শুরু করেছি মা।\n" +
                "মা : মানে ?\n" +
                "বল্টু : সিগারেট হলো দেশের শত্রু ঠিক কিনা বল ?\n" +
                "মা : হ্যাঁ ঠিক ।\n" +
                "বল্টু : সিগারেট হলো পরিবেশের শত্রু ঠিক কিনা বল ?\n" +
                "মা : হ্যাঁ ঠিক।\n" +
                "বল্টু : সিগারেট হলো যুব সমাজের শত্রু ঠিক কিনা বল ?\n" +
                "মা : হ্যাঁ ঠিক।\n" +
                "বল্টু : এই জন্যই তো এটাকে জ্বালিয়ে পুড়িয়ে নিঃশেষ করে ফেলছি।\n" +
                "ভাবছি, মদটাকেও এবার শেষ করা শুরু করব•\n" +
                "মা : দারা রে হতচ্ছারা আজ তোর এক দিন কি আমার এক দিন"));
        arrayList7.add(new Smsinformation(7,"16","","বল্টুঃ- দয়া করে তাড়াতাড়ি ৫০৬ নম্বর কক্ষে চলে আসুন।\n" +
                "হোটেল ম্যানেজারঃ কেন, সমস্যা কী?\n" +
                "বল্টুঃ- আমার স্ত্রী জানালা দিয়ে লাফ মেরে আত্মহত্যা করতে চাচ্ছে।\n" +
                "হোটেল ম্যানেজারঃ- আপনি স্বামী হয়ে কিছু করছেন না। আর আমি কী করতে পারি?\n" +
                "বল্টুঃ-আরে ভাই, এখন কথা বলার সময় নয়। আমার স্ত্রী কিছুতেই জানালা খুলতে পারছে না। জানালাটা খুলে দিয়ে যান।"));
        arrayList7.add(new Smsinformation(7,"17","","বসঃ যেদিন থেকে আমি তোকে চাকরি থেকে বরখাস্ত করেছি, সেদিন থেকে প্রতিদিন তুই আমার বাড়ির সামনে পায়খানা করিস! কারন কি? তোকে পুলিশে দেয়া উচিত!\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                "বল্টুঃ স্যার, আমি শুধু আপনাকে এতটুকু মনে করিয়ে দিতে চাই যে, বরখাস্ত করেছেন বলে আমি না খেয়ে মরে যাইনি!!!\n" +
                "\n" +
                "বল্টু এক পীর বাবার কাছে গিয়ে জিগ্যেস করল:\n" +
                "বল্টু :- আচ্ছা বাবা, বিয়ের আগে যদি প্রেমিক প্রেমিকা রাতে এক বিছানায় ঘুমায় তাহলে কি পাপ হয়???\n" +
                "`\n" +
                "`\n" +
                "পীর বাবা- ঘুমালে তো পাপ হয় না বৎস, কিন্তু সমস্যা হল তোরাতো ঘুমাস না!!!"));
        arrayList7.add(new Smsinformation(7,"18","","\n" +
                "বাচ্চারা শিক্ষা সফরে গেছে থানায়। বুলেটিন বোর্ডে “ওয়ান্টেড’ ক্রিমিনালদের এক গাদা ছবি ঝুলছে দেখে একটি ছোট মেয়ে পুলিশ অফিসারকে জিজ্ঞেস করল, তোমরা কি আসলেই ওদেরকে ধরতে চাও?\n" +
                "নিশ্চয়ই! পুলিশ অফিসার জানাল।\n" +
                "ছোট মেয়েটি আশ্চর্য হয়ে আবার জিজ্ঞেস করল, তাহলে ছবি তোলার সময়ই ওদের রেখে দিলে না কেন?"));
        arrayList7.add(new Smsinformation(7,"20","","বিয়ের তিন মাস পর দম্পতির সন্তান হলো.\n" +
                "স্বামী তো বিশাল ক্ষেপা ও চিন্তিত.\n" +
                "তো বউ তারে বলতেসে.\n" +
                "এই শুনো তোমার বিয়ের বয়স কত?\n" +
                "স্বামী- তিন মাস\n" +
                "আমার বিয়ের বয়স কত?\n" +
                "স্বামী-তিন মাস.\n" +
                "বাচ্চা বিয়ের কতদিন পর হইসে?\n" +
                "স্বামী- তিন মাস.\n" +
                "তো বল total কয় মাস হইলো???\n" +
                "স্বামী- আরে তাই তো total তো নয় মাস হয়.\n" +
                "আমি এতক্ষণ হুদাই তোমার চরিত্র নিয়ে চিন্তা করতেছিলাম"));
        arrayList7.add(new Smsinformation(7,"21","","বিদেশ থেকে দুবছর পর বাড়ি ফিরে হাসান দেখল তার বউয়ের কোলে ছয় মাসের একটা বাচ্চা।\n" +
                "হাসান বউকে বলল, এটা কার বাচ্চা?\n" +
                ": কার আবার, আমার।\n" +
                ": কী! বল, তার নাম বল! কে আমার এত সর্বনাশ করেছে!\n" +
                "- বউ চুপ।\n" +
                ": বল, কে সে? নিশ্চয়ই শয়তান জামাল!\n" +
                ": না\n" +
                ": তা হলে নিশ্চয়ই শয়তান জাফর!\n" +
                ": না, তাও না।\n" +
                ": তা হলে কে?\n" +
                ": তুমি শুধু তোমার বন্ধুদের কথাই বলছ আমার কি কোনো বন্ধু থাকতে পারে না।"));
        arrayList7.add(new Smsinformation(7,"22","","ভদ্রমহিলাঃ আজ একটা জরুরি কাজ আছে। সব রান্না একটার মধ্যে সেরে ফেলতে হবে।\n" +
                "বুয়াঃ বাড়িতে কোনো বড় পাতিল নেই তো! তাহলে রান্না একটার মধ্যে শেষ করব কীভাবে?"));
        arrayList7.add(new Smsinformation(7,"23","","ভোরবেলায় রাস্তায় গাড়ি থামিয়ে এক পুলিশ অফিসার সে গাড়ির চালককে জানাল যে, সে দিনের প্রথম সিটবেন্ট পরিধানরত গাড়ি চালক হিসেবে ‘নিরাপত্তা দিবস’ উপলক্ষে দশ হাজার টাকা পুরস্কার পেতে যাচ্ছে।\n" +
                "পুলিশ অফিসার সাধারণ কৌতুহলে জানতে চাইল-\n" +
                ": তুমি এই টাকা দিয়ে কী করবে?\n" +
                "চালক চোখ বুজে বলল-\n" +
                ": এই টাকা দিয়ে ড্রাইভিং টেস্ট দিয়ে আমি সত্যিকারের একটা লাইসেন্স নেব।"));
        arrayList7.add(new Smsinformation(7,"24","","ভক্তঃ সাধু বাবা, আমার বউ অনেক বিরক্ত করে...উদ্ধারের উপায় বলুন...\n" +
                "সাধুঃ আরে আহাম্মক উপায় থাকলে কি আর আমি গাছের নিছে বসে থাকি...?"));
        arrayList7.add(new Smsinformation(7,"25","","ভিক্ষুক :- স্যার, ২০টা টাকা দেন, কফি খাবো।\n" +
                "লোক :- কেন ? কফি তো ১০ টাকা কাপ।\n" +
                "ভিক্ষুক :- স্যার, সাথে গার্লফ্রেন্ড আছে তো, তাই।\n" +
                "লোক :- ভিক্ষুক হয়ে গার্লফ্রেন্ড ও বানিয়েছ।\n" +
                "ভিক্ষুক :- জ্বী না স্যার. গার্লফ্রেন্ডই বরং আমাকে ভিক্ষুক বানিয়েছে ।।"));
        arrayList7.add(new Smsinformation(7,"26","","মহিলা সেক্রেটারি তাঁর বসকে ‘স্যার, আপনার স্ত্রী সব সময় আমাকে সন্দেহের দৃষ্টিতে কেন দেখে?\n" +
                "বস: কারণ, তোমার আগে সে আমার সেক্রেটারি ছিল।"));
        arrayList7.add(new Smsinformation(7,"27","","মালিক অফিসে এসেই কর্মচারীকে ধমকালেন।\n" +
                "–হাসান সাহেব, কাল নাকি আপনি অফিস টাইমে মিস ডলিকে নিয়ে সিনেমায় গিয়েছিলেন? ওকে আমার সঙ্গে একবার দেখা করতে বলুন।\n" +
                "–কিন্তু স্যার, ও কি আপনার সাথে সিনেমা দেখতে যেতে রাজি হবে?"));
        arrayList7.add(new Smsinformation(7,"28","","ম্যানেজারঃ তুমি নাকি আলমিরার চাবি আবারও হারিয়েছ?\n" +
                "কেরানিঃ জ্বী স্যার।\n" +
                "ম্যানেজারঃ আগে একটা হারিয়েছিলে তাই এবার তালার সঙ্গে দুটো চাবিই তোমাকে দিয়েছিলাম ।\n" +
                "কেরানিঃ দুটোই হারাই নি স্যার ! একটা মাত্র হারিয়েছি ।\n" +
                "ম্যানেজারঃ তাহলে অন্যটা কোথায় ?\n" +
                "কেরানিঃ হারিয়ে যাবার ভয়ে আগে থেকেই সাবধান ছিলাম। তাই ওটা আলমিরার মধ্যেই সংরক্ষণ করে রেখেছিলাম ।"));
        arrayList7.add(new Smsinformation(7,"29","","ম্যাজিস্ট্রেট: গতবারও তোমাকে বলেছিলাম, আমি চাই না তুমি পুনরায় এখানে আসো।\n" +
                "চোর: স্যার, ঠিক এই কথাটাই আমিও পুলিশকে বলেছিলাম, বিশ্বাস করল না।"));
        arrayList7.add(new Smsinformation(7,"30","","ম্যাজিস্ট্রেট : বিশ টাকা পকেট মারার জন্য তোমাকে একশ টাকা জরিমানা করা হল।\n" +
                "পকেটমার : আমার কাছে মাত্র বিশ টাকা আছে, স্যার। বাকি টাকা এক্ষুনি এনে দিতে পারি, কিন্তু কিছুক্ষণের জন্য ছাড়তে হবে, স্যার।"));
        arrayList7.add(new Smsinformation(7,"31","","ম্যানেজার : তুমি বলছ তুমি এ যাবৎ দু-তিনটা হোটেলে বেয়ারার কাজ করেছ। তা কোনো প্রমাণট্রমাণ আছে কি?\n" +
                "বেয়ারা : স্যার, এই দেখুন, চারটা চামচ, তিনটা রুপার ডিশ, পাঁচটা এসট্রে আর একটা দামি তোয়ালে।"));
        arrayList7.add(new Smsinformation(7,"32","","মদন মিয়া বড়ই কৃপণ। একবার তিনি গেছেন কলা কিনতে।\n" +
                "মদন মিয়া: কি ভাই, এই ছোট্ট কলাটার দাম কত?\n" +
                "বিক্রেতা: তিন টাকা।\n" +
                "মদন মিয়া: দুই টাকায় দেবে কি না বলো?\n" +
                "বিক্রেতা: বলেন কি! কলার ছোকলার দামই তো দুই টাকা।\n" +
                "মদন মিয়া: এই নাও এক টাকা। ছোকলা রেখে আমাকে কলা দাও।"));
        arrayList7.add(new Smsinformation(7,"33","","মদন মিঞা ফলের দোকানদার। একদিন তাঁর দোকানে এলেন এক অদ্ভুত ক্রেতা।\n" +
                "ক্রেতা: আমাকে এক কেজি আপেল দিন তো। প্রতিটা আপেল আলাদা আলাদা প্যাকেটে দেবেন।\n" +
                "মদন মিঞা তা-ই করলেন।\n" +
                "ক্রেতা: হু, এবার আমাকে এক কেজি আম দিন। এ ক্ষেত্রেও প্রতিটা আম ভিন্ন ভিন্ন ঠোঙায় দেবেন।\n" +
                "মদন মিঞা তা-ই করলেন। ক্রেতা তখন দেখছিলেন, মদন মিঞার দোকানে আর কী কী আছে।\n" +
                "মদন চটজলদি দুই হাতে আঙুরগুলো আড়াল করে বললেন, ভাই, আমি আঙুর বিক্রি করি না!"));
        arrayList7.add(new Smsinformation(7,"34","","মদন: মনে রেখো, সততা ও জ্ঞান দ্বারাই জীবনে উন্নতি করা সম্ভব।\n" +
                "\n" +
                "মফিজ: কী রকম?\n" +
                "\n" +
                "মদন: যেমন ধরো, কাউকে যদি কথা দাও যে, তাকে টাকা ধার দেবে তাহলে অবশ্যই নিজের কথার ওপর দৃঢ় থাকবে এবং টাকাটা দেবে।\n" +
                "\n" +
                "মফিজ: আর জ্ঞানের ব্যাপারটা?\n" +
                "\n" +
                "মদন: কখনো ও রকম কথা কাউকে দেবে না।"));
        arrayList7.add(new Smsinformation(7,"35","","মালিক বলছে চাকর মদনকে, ‘মদন, আজ গাছে পানি দিয়েছিস?’\n" +
                "মদন: জি না হুজুর! বাইরে তো বৃষ্টি নেমেছে।\n" +
                "মালিক: তো কী হয়েছে? ছাতা মাথায় দিয়ে যা!"));
        arrayList7.add(new Smsinformation(7,"36","","মনিব : কুকুর মারা গেল আমার আর হাউমাউ করে কাঁদছিস তুই। এত কান্নাকাটির কী হল ?\n" +
                "চাকর : আমর কাম অনেক বাইড়া গেল সাহেব কইতে গেলে ও-ই তো সব পরিস্কার কইরা রাখত। চায়ের কাপ, থালা – বাসন সব তো ও-ই চাইটা-পুইটা সাফ করত। আমি শুধু ওকে তালিম দিতাম।"));
        arrayList7.add(new Smsinformation(7,"37","","মেয়ের বাবাঃ বেয়াই সাহেব আপনি তো বলেছিলেন আপনার ছেলে স্বর্ণর খাটে ঘুমায় কিন্তু এটা তো দেখছি কাঠের?\n" +
                "ছেলের বাবাঃ ঠিকই দেখছেন। এটা আমার বড় মেয়ের স্বর্ণর খাট। মেয়েটার বিয়ে হয়ে যাওয়ায় এই খাটে আমার ছেলেই ঘুমায়।\n" +
                "\n" +
                "মেয়ের বাবাঃ এই মাত্র মেয়েটার যে গান শুনলেন এর জন্য আমার বহু টাকা ব্যয় করতে হয়েছে।\n" +
                "ছেলের বাবাঃ হ্যাঁ, তা তো হবেই। নির্ঘাত প্রতিবেশীদের সঙ্গে মামলা লড়তে হয়েছে।"));
        arrayList7.add(new Smsinformation(7,"38","","মফস্বল শহরে বেড়াতে এসে একজন ট্যুরিষ্ট একটা রেস্তোরাঁয় ঢুকল। ঢুকে সে দুটো সিদ্ধ ডিম আর চায়ের অর্ডার দিল। খাওয়া শেষে তাকে বলা হল বিল পঁচিশ টাকা।\n" +
                "ট্যুরিষ্ট বলল, এত দাম ডিমের? তোমাদের এখানে কি ডিম পাওয়া যায় না?\n" +
                "ওয়েটার বলল, ডিম পাওয়া যায়, কিন্তু ট্যুরিষ্ট পাওয়া যায় না।"));
        arrayList7.add(new Smsinformation(7,"39","","মিসরের একটি পুরোন জিনিসের দোকানে এক পর্যটক ঢুকলেন। দোকানদার এগিয়ে এসে তাঁকে নানান জিনিস দেখাতে লাগল। সামনের একটি শো-কেসে একটি নর করোটি দেখতে পেয়ে পর্যটক জিঞ্জেস করলেন , “এই করোটি কার?” “এটি মহারানী ক্লিওপেট্রার,” সবিনয়ে জানালো দোকানদার । কিছুক্ষন বাদে ঘুরতে-ঘুরতে আর একটি খুলি চোখে পড়ল পর্যটকের। আগেরটির চেযে এই করোটি আকারে সামান্য ছোট। পর্যটক জিঞ্জেস করলেন , “এই করোটি কার?” দোকান দার বলল “এটি মহারানী ক্লিওপেট্রার হুজুর তবে এটা তাঁর ছোটবেলার করোটি।"));
        arrayList7.add(new Smsinformation(7,"40","","মুমূর্ষু এক রোগীর পাশে দাঁড়িয়ে আছেন ডাক্তার। মুখ বিষণ্ন করে বললেন, ‘বলতে বাধ্য হচ্ছি, আপনার শরীরের অবস্থা খুব খারাপ! এমন কেউ আছেন, যিনি আপনাকে দেখতে আসতে পারেন?’\n" +
                "\n" +
                "রোগী: অবশ্যই, আরেকজন ডাক্তার দেখতে পারেন।"));
        arrayList7.add(new Smsinformation(7,"41","","ম্যাডাম বলেছে, প্রতিটি প্রশ্নের উত্তর মুখস্থ করতে হবে। সৌরভ করছেও তা। সে বাসায় শিখেছে, আমাদের দেশের কয়েকটি ফলের নাম-আম, জাম, কাঁঠাল, লিচু, বরই প্রভৃতি। পরীক্ষায় প্রশ্ন এল, দেশের পাঁচটি ফলের নাম লেখো। সৌরভ হিসাব করে দেখল, ও শিখেছে ছয়টি ফলের নাম আর লিখতে হবে পাঁচটির নাম। তারপর সৌরভ লিখল-আমাদের দেশের পাঁচটি ফলের নাম হলোঃ\n" +
                "\n" +
                "১· আম, ২· জাম, ৩· কাঁঠাল, ৪· লিচু, ৫· প্রভৃতি!"));
        arrayList7.add(new Smsinformation(7,"12","","মিরাজ এবার দ্বিতীয় শ্রেণীতে পড়ে। ও একদিন ওর মাকে প্রশ্ন করছে—মা, আমার বড় মামাই বড়, নাকি নানাভাই বড়? ওর মায়ের উত্তর—তোমার নানাভাই বড়। কেন বলো তো? মিরাজ—তাহলে নানাভাইকে শুধু নানাভাই আর মামাকে বড় মামা বলি কেন?"));
        arrayList7.add(new Smsinformation(7,"13","","মা: মলি, আস্তে কথা বলো! চিৎকার চেঁচামেচি করে বাড়ি মাথায় তুলছ কেন? দেখো না রনি কেমন চুপচাপ খেলছে?\n" +
                "\n" +
                "মলি: এটা আমাদের খেলারই অংশ মা। খেলায় রনি আব্বু সেজেছে, যে কি না দেরি করে অফিস থেকে ফিরেছে। আর আমি তুমি সেজেছি!"));
        arrayList7.add(new Smsinformation(7,"44","","মুখ ঝামটা মেরে পাপিয়া বেগম তাঁর ছেলে পল্টুকে বললেন, ‘নালায়েক! তুই আবার ফেল করেছিস? পাশের বাসার রুমকীকে দেখ, কত্ত ভালো রেজাল্ট করেছে ও!’\n" +
                "\n" +
                "পল্টু: ওকে আর নতুন করে কী দেখব? পরীক্ষার হলে বসেও ওকে দেখছিলাম বলেই তো আজ এই দশা!"));
        arrayList7.add(new Smsinformation(7,"45","","মাঝবয়সী কড়া মেজাজের মহিলা : এই যে খোকা, তোমার মা কি জানেন যে তুমি সিগারেট টান?\n" +
                "ঠোঁটকাটা খোকা : আচ্ছা ম্যাডাম, আপনার স্বামী কি জানেন যে আপনি রাস্তাঘাটে অচেনা লোকদের সঙ্গে কথা বলেন?"));
        arrayList7.add(new Smsinformation(7,"46","","মদ্যপান করতে করতে চিৎকার করে কাঁদছিল এক মাতাল।\n" +
                "একজন জিজ্ঞেস করল,\n" +
                "‘কী, কাঁদছ কেন?’\n" +
                "মাতাল বলল,\n" +
                "‘যে মেয়েটাকে ভোলার জন্য পান করছি,\n" +
                "তার নাম মনে পড়ছে না!’"));
        arrayList7.add(new Smsinformation(7,"47","","মিনিট দশেক তাড়া করে গতিবিধি লঙ্ঘন করা এক ড্রাইভারকে থামাল ট্রাফিক পুলিশ, বলল, আমি থামতে বলা সত্ত্বেও কেন আপনি থামেননি?\n" +
                "এক মুহূর্ত ভেবে নিয়ে ড্রাইভার বলল, আসলে হয়েছে কি, গত সপ্তাহে আমার স্ত্রী এক ট্রাফিক পুলিশের সঙ্গে পালিয়ে গেছে। তো আপনাকে আমার পেছনে ছুটতে দেখে মনে হলো, আমার স্ত্রীকে ফেরত দিতেই আপনি আমার পিছু নিয়েছেন।"));
        arrayList7.add(new Smsinformation(7,"48","","মনা পাগলা ভোরবেলায় রিকশায় চড়ে যাচ্ছে দেখে অন্য এক লোক তাকে ডেকে বলল, কি ভাই, ভোরবেলায় রিকশায় চড়ে কোথায় যাচ্ছেন?\n" +
                "মনা পাগলা : মর্নিং ওয়াকে।\n" +
                "লোক : তাহলে রিকশায় চড়ে যাচ্ছেন কেন?\n" +
                "মনা পাগলা : ভাই, ঘুম থেকে উঠতে লেট হয়ে গেছে, তাই রিকশায় চড়ে যাচ্ছি।\n"));
        arrayList7.add(new Smsinformation(7,"49","","মুরগির বাচ্চা তার মাকে জিজ্ঞাসা করলো মা মানুষের জন্ম হলে তাদের নামকরণ হয়, কিন্তু আমাদের হয়না কেন?\n" +
                "▼\n" +
                "▼\n" +
                "▼\n" +
                "▼\n" +
                "মুরগির মা বললো বেটা আমাদের সমাজে নামকরণ হয় মরার পরে। যেমন>>> চিকেন তন্দুরি চিকেন বিরিয়ানি চিকেন পাকোড়া চিকেন চিলি চিকেন টিক্কা চিকেন রোল চিকেন কড়াই চিকেন ফ্রাইড চিকেন চাপ চিকেন স্যান্ডউইচ চিকেন স্যুপ চিকেন বার্গার ইত্যাদি [মুরগির বাচ্চা বেহুশ]\n"));
        arrayList7.add(new Smsinformation(7,"50","","যদি বলো আকাশে চার বিলিয়ন তারা আছে, সবাই বিশ্বাস করবে 'ইহাই বিশ্বাস'।\n" +
                "জোকস ৪২৩\n" +
                "\n" +
                " \n" +
                "\n" +
                "\n" +
                "কোন খানা খাওয়া যায়না?\n" +
                "উঃ কারখানা\n" +
                "\n" +
                "\n" +
                "জোকস ৪২৪\n" +
                "\n" +
                "\n" +
                "➢ কোন বরের বিয়ে হয়না?\n" +
                "উঃ খবরের"));

        /**
         * 8
         */
        arrayList8.add(new Smsinformation(8,"1","","রেগেমেগে অফিস থেকে বাড়ি ফিরলেন শফিক।\n" +
                "শফিকের স্ত্রী বললেন, ‘কী হলো? আজ এত চটে আছো কেন?’\n" +
                "শফিক: আর বোলো না। প্রতিদিন অফিসে যে কর্মচারীর ওপর রাগ ঝাড়ি, সে আজ অফিসে আসেনি। মেজাজটাই খারাপ হয়ে আছে!"));
        arrayList8.add(new Smsinformation(8,"2","","রেগে অগ্নিশর্মা হয়ে অফিসের বস তাঁর নতুন সেক্রেটারিকে বলছেন, আমার টেবিলে যে ধুলোর আস্তরণ ছিল, তা গেল কোথায়?\n" +
                "আমি তো সেই ধুলোর মধ্যে কয়েকটা টেলিফোন নম্বর লিখে রেখেছিলাম!"));
        arrayList8.add(new Smsinformation(8,"3","","রেলওয়য়েতে চাকরির ইন্টারভিউ হচ্ছে। একটি চটপটে ছেলেকে সবার পছন্দ হল। চেয়ারম্যান একটু বাজিয়ে নিতে চাইলেন।\n" +
                ": ধর, একটা দ্রুতগামী ট্রেন আসছে। হঠাৎ দেখলে লাইন ভাঙা। ট্রেনটা থামানো দরকার। কী করবে তুমি?\n" +
                ": লাল নিশান ওড়াব।\n" +
                ": যদি রাত হয়?\n" +
                ": লাল আলো দেখাব।\n" +
                ": লাল আলো যদি না থাকে?\n" +
                ": তা হলে আমার বোনকে ডাকব্\u200C\n" +
                ": বোনকে! তোমার বোন এসে কী করবে?\n" +
                ": কিছু করবে না। ওর অনেক দিনের শখ একটা ট্রেন-অ্যাক্সিডেন্ট দেখার।"));
        arrayList8.add(new Smsinformation(8,"4","","রোগী: প্রথমে সবচেয়ে খারাপ খবরটাই বলুন।\n" +
                "\n" +
                "চিকিৎসক: আপনার ক্যানসার হয়েছে।\n" +
                "\n" +
                "রোগী: দ্বিতীয়টা?\n" +
                "\n" +
                "চিকিৎসক: আপনার আলঝেইমারও হয়েছে।\n" +
                "\n" +
                "রোগী: যাক, আলঝেইমার তেমন কোনো রোগ না।"));
        arrayList8.add(new Smsinformation(8,"5","","রোগীঃ ডাক্তার সাহেব এই কয়দিন ধরে আমার খুব পায়খানা হচ্ছে। আমার অবস্থা খুবই খারাপ।\n" +
                "\n" +
                "ডাক্তারঃ আচ্ছা, একটু ধৈর্য ধরুন আমি আপনাকে পরিক্ষা করে দেখছি। এখন বলুনতো আপনার পায়খানা কেমন?\n" +
                "\n" +
                "রোগীঃ ডাক্তার সাহেব, কি যে বলবো গরীব মানুষের পায়খানা আর কেমন, দুটো বাঁশের লাঠি আর সামনে ছালা।"));
        arrayList8.add(new Smsinformation(8,"6","","লোকঃ অফিসার, আমার বাগানে একটা বোমা পড়ে আছে।\n" +
                "পুলিশ অফিসারঃ কোনো সমস্যা নেই। তিন দিনের মধ্যে কোনো দাবিদার না পাওয়া গেলে ওটা আপনি রেখে দিতে পারেন।"));
        arrayList8.add(new Smsinformation(8,"7","","১ম বন্ধুঃ তোকে মানা করার পরও তুই পাশের বাড়ীর মুরগী চুরি করলি, এখন যে তোকে জুতো দিয়ে মারা হবে। তোর মান-সম্মান কি আর থাকবে???\n" +
                "\n" +
                "২য় বন্ধুঃ ইস!!! আমার মান-সম্মান এত কম নাকি যে জুতো দিয়ে দু'চারটে মারলেই চলে যাবে???"));
        arrayList8.add(new Smsinformation(8,"8","","১ম বন্ধু : জানিস, আমার বাবা যদি একদিন অফিসে না যায় তাহলে কেউ অফিস করতেই পারবে না।\n" +
                "২য় বন্ধু : তাই নাকি? তোর বাবা বুঝি বড় অফিসার?\n" +
                "১ম বন্ধু : আমার বাবা অফিসের দারোয়ান।"));
        arrayList8.add(new Smsinformation(8,"9","","৩ জন মাতাল রাতে একটা গাড়িতে উঠল ড্রাইভার বোঝতে পারল যে তারা মাতাল!!\n" +
                "ড্রাইভার গাড়ির ইঞ্জিন চালু করল এবং সাথে সাথে বন্ধ করে ফেলল আর তাদেরকে বলল যে তারা নাকি গন্তব্যস্থলে পৌঁছে গেছে.\n" +
                "৩ মাতাল গাড়ি থেকে নামল\n" +
                "তারপরঃ\n" +
                "১ম মাতালঃ ধন্যবাদ.\n" +
                "২য় মাতালঃ নিন, ১০ টাকা বকশিস দিলাম। তখন\n" +
                "৩য় মাতাল ড্রাইভারকে দিল একটা থাপ্পর।\n" +
                "ড্রাইভার মনে করল যে লোকটা বোধ হয় মাতাল না, হয়ত সবকিছু বোঝে ফেলেছে. তবুও ড্রাইভার ভয়ে ভয়ে তাকে জিজ্ঞেস করলঃ থাপ্পর মারলেন কেন??\n" +
                "৩য় মাতালঃ শালা, এত স্পীডে কি কেউ গাড়ি চালায়! মাইরাই তো ফালাইছিলি"));
        arrayList8.add(new Smsinformation(8,"10","","৭ বছর বয়সি একটি ছেলে রাস্তা দিয়ে যাচ্ছে লুঙ্গি পরে, লুঙ্গির পেছনের দিকে খানিকটা ছেড়া তো ছেলেটি রাস্তা দিয়ে যাওয়ার সময় একদল কলেজ পড়ুয়া মেয়ে তার পেছনে। তারা প্রাইভেট পড়তে যাচ্ছে।\n" +
                "হটাৎ মেয়ে গুলোর নজর পড়ল ছেলেটার লুঙ্গির পেছনে ছেড়ার ওপর।\n" +
                "তখন মেয়েদের ভেতর থেকে একটি মেয়ে বলে উঠলো।\n" +
                "মেয়ে: এই ছেলে লুঙ্গিটা একটু ঘুরায়ে পরো।তোমার জয় বাংলা দেখা যায়।\n" +
                "ছেলে: আপা পেছোনে তো জয় বাংলা দেখা যাচ্ছে লুঙ্গি টা ঘুরয়ে পরলে সোনার বাংলা দেখা যাবে।"));
        arrayList8.add(new Smsinformation(8,"11","","৮ বছর বয়সের একটা ছেলে দোকানে গিয়ে বলল : মামু এক প্যাকেট বিড়ি দাও।\n" +
                "দোকানদার : কেন ? তুই কি বিড়ি খাস নাকি ।\n" +
                "।\n" +
                "।\n" +
                "।\n" +
                "ছেলে : আরে না। বিড়ি তো নিচ্ছি আমার ছোট ভাইয়ের জন্য। আমাকে একটা বেনসন দাও ।"));
        arrayList8.add(new Smsinformation(8,"12","","শাহীন গেছে পোস্ট অফিসে।\n" +
                "শাহীন: আমি এই চিঠিটা পোস্ট করতে চাই।\n" +
                "কর্মকর্তা: হুম। প্রাপকের ঠিকানা কী লিখব?\n" +
                "শাহীন: জাতীয় জাদুঘর।\n" +
                "কর্মকর্তা: কিছু মনে করবেন না, আপনি জাদুঘরে চিঠি পাঠাচ্ছেন কেন?\n" +
                "শাহীন: কারণ, আমি শুনেছি আপনারা চিঠি পৌঁছাতে অনেক দেরি করেন। যত দিনে চিঠিটা পৌঁছবে, তত দিনে চিঠির নিশ্চয়ই একটা প্রত্নতাত্ত্বিক মূল্য তৈরি হবে।"));
        arrayList8.add(new Smsinformation(8,"13","",": শোন, আমি বিদেশে মিউজিকের ওপর পড়ালেখা করেছি। যেকোনো কি-বোর্ডেই আমি সুর তুলতে পারি।\n" +
                ": তাই, এই নে আমার কম্পিউটারের কি-বোর্ড, এইটাতে সুর তুইলা দেখা।"));
        arrayList8.add(new Smsinformation(8,"14","","শিশু রাস্তার পাশে দাঁড়িয়ে কাঁদছে। এক পথিক তাকে জিজ্ঞাসা করছেন\n" +
                "\n" +
                "পথিক: বাবা, তুমি কাঁদছ কেন?\n" +
                "\n" +
                "শিশু: আমার একটা টাকা হারিয়ে গেছে উ-উ-উ\n" +
                "\n" +
                "পথিক: আচ্ছা আচ্ছা তুমি কেঁদো না বাবা। এই নাও আমি তোমাকে দুই টাকা দিলাম।\n" +
                "\n" +
                "(এবার শিশুটি আরও জোরে চিৎকার করে কান্না শুরু করে দিল )\n" +
                "\n" +
                "পথিক: কী হলো বাবা? আমি তো তোমাকে দুই টাকা দিলাম, এখন তুমি কাঁদছ কেন বাবা ?\n" +
                "\n" +
                "শিশু: আমার হারিয়ে যাওয়া টাকাটা থাকলে এখন আমার তিন টাকা হতো উ-উ-উ -"));
        arrayList8.add(new Smsinformation(8,"15","","শিক্ষকঃ তোমরা ওয়াদা কর যে, কখনোও সিগারেট পান করবে না।\n" +
                "ছাত্ররাঃ ওকে স্যার পান করবো না।\n" +
                "\n" +
                "শিক্ষকঃ মেয়েদের পিছে পিছে ঘুরবেনা।\n" +
                "ছাত্ররাঃ ওকে স্যার ঘুরবো না।\n" +
                "\n" +
                "শিক্ষকঃ ওদের কখনোও ডিস্টার্ব করবে না।\n" +
                "ছাত্ররাঃ ওকে স্যার, ডিস্টার্ব করবো না।\n" +
                "\n" +
                "শিক্ষকঃ দেশের জন্য জীবন কোরবান করবে।\n" +
                "ছাত্ররাঃ অবশ্যই স্যার, এই রকম জীবন রেখেই বা কি করবো, এর চেয়ে কুরবানি দিয়ে দেয়াই ভালো।"));
        arrayList8.add(new Smsinformation(8,"16","","স্কুলপড়ুয়া গাবলুকে ডাক্তার জিজ্ঞেস করলেন, ‘বাবু, পরীক্ষার কাগজ কোথায় তোমার?\n" +
                "\n" +
                "‘সে তো পরীক্ষার পরই স্কুলে জমা দিয়ে এসেছি আংকেল।’ গাবলুর জবাব।"));
        arrayList8.add(new Smsinformation(8,"17","","স্কুল পড়ুয়া দুই বন্ধুর পরীক্ষার শেষে স্কুল মাঠে দেখা-\n" +
                "\n" +
                "১ম বন্ধুঃ কী রে দোস্ত, পরীক্ষা কেমন হলো ?\n" +
                "\n" +
                "২য় বন্ধুঃ পরীক্ষা ভাল হয়নি রে দোস্ত ! তবে ৫ নম্বর নিশ্চিত পাবো ।\n" +
                "\n" +
                "১ম বন্ধুঃ কীভাবে ?\n" +
                "\n" +
                "২য় বন্ধুঃ পরিস্কার পরিচ্ছন্নতার জন্য ছিল ৫ নম্বর ! তাই আমি পরীক্ষার খাতায় কলমের একটা আচড়ও দেইনি ! তাই ৫ নম্বর নিশ্চিত পাবো ।\n" +
                "\n" +
                "১ম বন্ধু :- হায়! সর্বনাশ হয়েছে- আমি ও তো তোর মতো পরীক্ষার খাতায় কলমের একটা আচড়ও দেইনি !\n" +
                "\n" +
                "আমাদের দুই জনের খাতাই একই রকম দেখলে- টিচার মনে করবে না যে আমরা দুজনে নকল করেছি!"));
        arrayList8.add(new Smsinformation(8,"18","","এক ছাত্রকে পরীক্ষার হল থেকে টেনেহিঁচড়ে প্রধান শিক্ষকের কাছে নিয়ে গেলেন এক শিক্ষক। ছাত্রটি তখন পরীক্ষা দিচ্ছিল।\n" +
                "\n" +
                "প্রধান শিক্ষক জিজ্ঞেস করলেন, কেন তাকে ধরে আনা হয়েছে। বলা হলো, সে নকল করছিল।\n" +
                "\n" +
                "‘কী, এত বড় সাহস! নকল করছিল!’\n" +
                "\n" +
                "‘জি, স্যার! নকল করছিল।\n" +
                "\n" +
                "প্রশ্নে এসেছে, মানুষের বুকে হাড়ের সংখ্যা কত।\n" +
                "\n" +
                "স্যার, - সে পরীক্ষার হলে শার্ট খুলে বুকের হাড় গুনছিল।\n" +
                "\n" +
                "আজব মাস্টার বটে!!!!!"));
        arrayList8.add(new Smsinformation(8,"20","","শিক্ষক ছাত্র-ছাত্রীদের বলছেন কে কি হতে চায় ভবিষ্যতে-\n" +
                "\n" +
                "রানা; আমি পাইলট হতে চাই।\n" +
                "\n" +
                "সুমিত; আমি ডাক্তার হতে চাই।\n" +
                "..\n" +
                "..\n" +
                "..\n" +
                "..\n" +
                "দীপা; আমি একজন ভালো মা হতে চাই।\n" +
                "\n" +
                "সুমন; আমি দীপাকে সাহায্য করতে চাই।"));
        arrayList8.add(new Smsinformation(8,"21","","শিক্ষক: কাল রাতে কে কয়টা পর্যন্ত পড়েছিলি?\n" +
                "\n" +
                "আমিনা: স্যার আমি রাত ১২টা পর্যন্ত পড়েছিলাম\n" +
                "\n" +
                "শিক্ষক: কাল রাতে তো ৯টার পরে কারেন্ট ছিলনা, তা তুই পরলি কিভাবে?\n" +
                "\n" +
                "আমিনা:পড়ায় এত মনযোগ ছিলাম যে, কখন কারেন্ট চলে গেছে বুঝতেই পারি নি."));
        arrayList8.add(new Smsinformation(8,"22","","শামসুঃ কই আমার গিফট টা কই ?\n" +
                "দোকানদারঃ কিসের গিফট ?\n" +
                "\n" +
                "শামসুঃ ক্যান কালকে যে মিনারেল ওয়াটার কিনলাম। তার লগে যে ফ্রী গিফট আছিল।সেইটা কই ?\n" +
                "\n" +
                "দোকানদারঃ আরে না মিনারেল ওয়াটারের লগে কুনো ফ্রী গিফট আছিল না ।\n" +
                "\n" +
                "শামসুঃ আমারে টিউবলাইট পাইছো মিনারেল ওয়াটারের বোতলের গায়ে পরিস্কার বাংলায় লিখা আছে ১০০% ব্যাক্টেরিয়া ফ্রী ।"));
        arrayList8.add(new Smsinformation(8,"23","","সাপ্তাহিক বেতনের দিন। চেক পেয়ে কর্মচারী চিৎকার দিয়ে উঠল।\n" +
                ": আমাকে কম টাকা দেওয়া হল কেন?\n" +
                ": গত সপ্তাহে ভুলে তোমাকে বেশি টাকা দিয়েছিলাম। তখন তো কিছু বল নি।\n" +
                ": একটা ভুল নাহয় হয়ে গেছে, কিন্তু পরপর দু’টি ভুল তো আর হতে দিতে পারি না।"));
        arrayList8.add(new Smsinformation(8,"24","","সড়ক দুর্ঘটনা ঘটানোর অভিযোগে বাবুকে আদালতে হাজির করা হয়েছে—\n" +
                "বিচারক: কীভাবে ঘটালেন দুর্ঘটনাটা?\n" +
                "বাবু: কোন দুর্ঘটনা?\n" +
                "বিচারক: কেন, যে দুর্ঘটনাটির জন্য আপনি আদালতে?\n" +
                "বাবু: ওই সময় আমি জেগে থাকলে না হয় বলতে পারতাম। কিন্তু হুজুর, আমি তো ওই দুর্ঘটনার সময় ঘুমিয়ে ঘুমিয়ে গাড়ি চালাচ্ছিলাম।"));
        arrayList8.add(new Smsinformation(8,"25","","সোভিয়েত যুগের কথা। পরীক্ষা হচ্ছে যৌথ খামারের পরিচালকদের।\n" +
                "-আপনি যৌথ খামারের পরিচালক। আপনার জমিগুলোতে এবার ফলন হয়নি। কী কারণে এটা হতে পারে বলে আপনি মনে করেন?\n" +
                "-আবহাওয়া ভালো ছিল না, ফলনের উপযুক্ত পরিবেশ ছিল না।\n" +
                "-নতুন কিছু ভাবুন, জনাব ডিরেক্টর।\n" +
                "-হতে পারে, বীজই লাগাতে ভুলে গেছি।"));
        arrayList8.add(new Smsinformation(8,"26","","সাংবাদিকঃ সেকি! আপনার মা যে বললেন আপনার বয়স ত্রিশ।\n" +
                "নায়িকাঃ মা মিথ্যে বলেননি। তবে আমি গুনতে শিখেছিলাম ছয় বছর বয়সে।"));
        arrayList8.add(new Smsinformation(8,"27","","সর্দারজি মোটরসাইকেলে করে যাচ্ছিলেন। এমন সময় ট্রাফিক পুলিশ থামতে ইশারা করল।\n" +
                "সর্দারজি: দুঃখিত, স্যার, আমরা ইতিমধ্যেই তিনজন উঠে বসে আছি, আপনাকে ওঠানোর মতো জায়গা নেই!"));
        arrayList8.add(new Smsinformation(8,"28","","সরদার তাঁর বন্ধুকে বলছেন, ‘জানিস, আমি গোয়েন্দা উপন্যাস সব সময় মাঝামাঝি থেকে পড়া শুরু করি। তাতে মজাটা বেশি হয়।’\n" +
                "বন্ধু: কীভাবে?\n" +
                "সরদার: তখন শুধু উপন্যাসের শেষ না, শুরুটা জানারও কৌতূহল থাকে!"));
        arrayList8.add(new Smsinformation(8,"29","","সাইকিয়াট্রি ওয়ার্ডে, এক রোগী বসে আছে তো আছেই। হাত দুটি দিয়ে বেডের রেলিঙ ধরে আছে। অনেক চেষ্টার পরও নার্সরা তাকে সরাতে পারলেন না।\n" +
                "\n" +
                "রাউন্ডের সময় ডাক্তার বললেন, ‘আপনি এভাবে বসে আছেন কেন?’\n" +
                "\n" +
                "রোগী বললেন, ‘আমি তো ড্রাইভার! তাই গাড়িতে বসে আছি।’\n" +
                "\n" +
                "ডাক্তার বললেন, ‘তাই নাকি? তাহলে গাড়ি চালান না কেন?’\n" +
                "\n" +
                "রোগীর জবাব, ‘আরে পাগল, আপনি কোন দেশে আছেন? জানেন না, দেশে হরতাল চলতেছে। গাড়ি চালাইলেই আগুন!’"));
        arrayList8.add(new Smsinformation(8,"30","","স্টেশনমাস্টারকে বলছেন এক লোক, ‘ভাই, সিলেটের ট্রেনটা কখন ছাড়বে?’\n" +
                "\n" +
                "- সাড়ে আটটায়।\n" +
                "\n" +
                "- আর চট্টগ্রামেরটা?\n" +
                "\n" +
                "- এগারোটায়।\n" +
                "\n" +
                "- তাহলে ব্রাহ্মণবাড়িয়ার ট্রেনটা যাবে কখন?\n" +
                "\n" +
                "- এবার বিরক্ত হয়ে গেলেন স্টেশনমাস্টার ‘আরে এত ট্রেনের খবর নিচ্ছেন, আপনি যাবেন কোথায়?\n" +
                "\n" +
                "- না, মানে আমি রেললাইনটা পার হয়ে ওপাশের প্ল্যাটফর্মে যাব তো…"));
        arrayList8.add(new Smsinformation(8,"31","","সিনেমা হলে সিনেমা দেখতে গেছে এক লোক। সামনের সিটে দুই মহিলা এত জোরে গল্প করছিল যে বেচারা কোনো সংলাপই শুনতে পাচ্ছিল না। শেষমেশ না পেরে মহিলা দুজনের দৃষ্টি আকর্ষণ করে বললেন, এই যে, আমি কিছু শুনতে পাচ্ছি না।\n" +
                "\n" +
                "- শোনা উচিতও নয়। আমরা ব্যক্তিগত আলাপ করছি। মহিলাদের একজন জবাব দিলেন।"));
        arrayList8.add(new Smsinformation(8,"32","","স্কুলে বার্ষিক পরীক্ষা আরম্ভ হলো। পরীক্ষার হলে এক ছাত্রী জোরে জোরে কাঁদছে।\n" +
                "\n" +
                "শিক্ষকঃ তুমি কাঁদছ কেন?\n" +
                "\n" +
                "ছাত্রীঃ আমার রচনা কমন পড়েনি।\n" +
                "\n" +
                "শিক্ষকঃ কেন? কী এসেছে?\n" +
                "\n" +
                "ছাত্রীঃ এসেছে ‘ছাত্রজীবন’। স্যার, আমি তো ছাত্রী। ‘ছাত্রজীবন’ লিখব কীভাবে।"));
        arrayList8.add(new Smsinformation(8,"33","","স্কুলে বাচ্চাদের ইংরেজিটা নতুন পড়ানো শুরু হয়েছে।\n" +
                "\n" +
                "বল্টু ক্লাসের দরজায় দাঁড়িয়ে বললো, জুন আই কাম ইন স্যার?\n" +
                "\n" +
                "স্যার একটু ভ্যাবাচাকা খেয়ে বললেন, এই নতুন ইংরেজি কোত্থেকে আমদানি করলে?\n" +
                "\n" +
                "বল্টু বললো, কেনো স্যার, আপনিই তো বলতে বলেছিলেন!\n" +
                "\n" +
                "স্যার, আমি? আমি তো মে আই কাম ইন স্যার বলতে বলেছিলাম।\n" +
                "\n" +
                "বল্টু, স্যার, ওটাতো আপনি গত মাসে বলেছিলেন। মে মাস তো শেষ। আজ থেকে তো জুন মাস শুরু। স্যার অজ্ঞান!"));
        arrayList8.add(new Smsinformation(8,"34","","স্বামী : জানো আমার ক্ষমতা কত ?\n" +
                "স্ত্রী : একটু দেখাও তো।\n" +
                "স্বামী : ওই ট্রেনটা আসছে, ওটাকে আমি থামিয়ে দিতে পারি।\n" +
                "স্ত্রী : দাও তো দেখি।\n" +
                "স্বামী লাল কাপড় উড়িয়ে ট্রেন থামালেন।\n" +
                "গার্ড ট্রেন থেকে নেমে কারণ জানতে চাইলেন।\n" +
                "স্বামী : আমি স্ত্রীকে আমার ক্ষমতা দেখাচ্ছিলাম।\n" +
                "এ কথা শুনে গার্ড তার পেছনে সজোরে লাথি দিয়ে চলে গেলেন।\n" +
                "স্ত্রী : গার্ড তোমাকে লাথি দিল, তুমি কিছু বললে না ?\n" +
                "স্বামী : আমার ক্ষমতা আমি দেখিয়েছি, সে তার ক্ষমতা দেখাবেনা...?"));
        arrayList8.add(new Smsinformation(8,"35","","স্বামী: ও গো শুনছ, একটু পর আমার একজন বন্ধু আসবে।\n" +
                "স্ত্রী: গাধা, বোকার হদ্দ কোথাকার, করেছ কী? দেখো না ঘরের কী অবস্থা? ভাঙা ফুলদানি, কাচের প্লেট, ঝাড়ু ঘরজুড়ে সব ছড়িয়ে-ছিটিয়ে আছে।\n" +
                "স্বামী: এই জন্যই তো ওকে আসতে বলেছি। গর্দভটা বিয়ে করার কথা ভাবছে!"));
        arrayList8.add(new Smsinformation(8,"36","","স্বামীর সঙ্গে কথা প্রসঙ্গে স্ত্রী বলল, তুমি সত্যি বলছ ? আমি মরলে তুমি পাগল হয়ে যাবে ?\n" +
                ": হ্যাঁ সত্যি, স্বামী বলল। তুমি মরে গেলে আমি সত্যিই পাগল হয়ে যাব।\n" +
                ": থাক আর বোলো না । অনেক মিথ্যে কথা বলেছ । খুব জানি, আমি মরলে তুমি আবার বিয়ে করতে ছুটবে।\n" +
                "স্বামী বলল, পাগলে কী না করে"));
        arrayList8.add(new Smsinformation(8,"37","","স্বামী আর স্ত্রীর মধ্যে সারাদিন ঝগড়া চলে। অথচ তাদের পাশের ফ্ল্যাট থেকে সারা দিনরাত হাসির শব্দ শোনা যায়। স্বামী একদিন আর থাকতে না পেরে পাশের বাসার ভদ্রলোককে জিজ্ঞাসা করলেন,\n" +
                "আচ্ছা ভাই, আমাদের স্বামী স্ত্রীর মধ্যে সারাদিন ঝগড়া হয়, আর আপনাদের বাসা থেকে সবসময় হাসির আওয়াজ পাওয়া যায়।\n" +
                "আচ্ছা, আপনারা ঝগড়া না করে এত সুখে কি করে থাকেন বলুন তো?\n" +
                "পাশের বাসার ভদ্রলোক রেগে বললেন,\n" +
                "কে বলেছে আমরা সুখে আছি?\n" +
                "ঝগড়া করি না এটা কে বলল?\n" +
                "ইয়ে মানে… তাহলে যে আপনাদের বাসা থেকে সবসময় হাসির আওয়াজ শুনতে পাই…\n" +
                "আরে ধুর, আমার বউ এর সাথে সবসময় ঝগড়া লেগেই আছে। আর ঝগড়া হলেই ও হাতের কাছে যা পায় আমার দিকে ছুঁড়ে মারে।\n" +
                "আমার গায়ে লাগলে ও হাসে,\n" +
                "আর না লাগলে আমি হাসি।"));
        arrayList8.add(new Smsinformation(8,"38","","স্ত্রীঃ ও গো, শুনছ? তোমার না আজ চোখের ডাক্তারের কাছে যাওয়ার কথা, গিয়েছিলে?\n" +
                "স্বামীঃ গিয়েছিলাম তো!\n" +
                "স্ত্রীঃ তা ডাক্তার কী বললেন?\n" +
                "স্বামীঃ আরে ধুর! উনি বললেই আমি শুনব নাকি! তার নিজের চোখই তো আমার চেয়ে খারাপ!\n" +
                "স্ত্রীঃ কী করে বুঝলে?\n" +
                "স্বামীঃ দিনের বেলায়ও ব্যাটা টর্চ জ্বালিয়ে আমার চোখ দেখছিল!\n"));
        arrayList8.add(new Smsinformation(8,"39","","স্ত্রীঃ আচ্ছা, তুমি সব সময় অফিসে যাওয়ার সময় ব্যাগে আমার ছবি নিয়ে যাও কেন?\n" +
                "স্বামীঃ অফিসে যখন আমি কোন সমস্যায় পড়ি, তখন তোমার ছবিটি বের করে দেখলেই সব সমস্যার সমাধান হয়ে যায়, বুঝলে?\n" +
                "স্ত্রীঃ তাই নাকি! তাহলে দেখো, তোমার জন্য আমি কতটা সৌভাগ্যের!\n" +
                "স্বামীঃ হুম, আমার যখন সমস্যা আসে, তখন তোমার ছবি বের করে দেখি আর নিজেকে বলি, তোমার চেয়ে তো বড় কোনো সমস্যা হতে পারে না। আর সঙ্গে সঙ্গে ছোট সমস্যাগুলো আর আমার কাছে সমস্যা বলে মনে হয় না।"));
        arrayList8.add(new Smsinformation(8,"40","","সংসদে এক সরকারি এম.পি তার বক্তৃতার সময় এক গল্প বলল-\n" +
                "“এক বাবা তার তিন ছেলেকে ১০০ টাকা করে দিয়ে বলল যে এমন কিছুকিনে আনো যাতে ঘরটা পুরো ভর্তি হয়ে যায়...।\n" +
                "১ম ছেলে ১০০ টাকার খড় কিনে আনল কিন্তু ঘর পুরোপুরি ভর্তি করতে পারল না...\n" +
                "২য় ছেলে ১০০ টাকার তুলা কিনে আনল কিন্তু সেও ঘর পুরোপুরি ভর্তিকরতে পারল না...\n" +
                "৩য় ছেলে ৫টাকা দিয়ে একটা মোমবাতি কিনে আনল এবং রুমের মাঝে জ্বালাল। এতে পুরো ঘর সম্পূর্ণ আলোতে ভর্তি হয়ে গেলো।“\n" +
                "এম.পি আরও বলতে লাগলো যে\n" +
                "“আমাদের প্রধানমন্ত্রী হচ্ছেন ৩য় ছেলের মতো। যেদিন থেকে তিনি দায়িত্ব নিয়েছেন এই দেশের, এই দেশ উন্নতির আলোতে পূর্ণ হয়ে গিয়েছে.....।”\n" +
                "পিছন থেকে আওয়াজ আসলো\n" +
                "সেটা তো ঠিক আছে, কিন্তু বাকি ৯৫ টাকা কই গেলো?”"));
        arrayList8.add(new Smsinformation(8,"41","","সেলুনে চুল কাটাতে গেছেন এক নেতা।\n" +
                "নাপিত কাঁচি চালাতে চালাতে বলছেন, ‘স্যার, শুনলাম জনগণ নাকি আপনার ওপর খুব খ্যাপা। যেকোনো দিন আপনার বাড়িতে হামলা চালাইবে! আপনি নাকি জনগণের টাকা দিয়ে সম্পদের পাহাড় বানাইছেন…।’\n" +
                "নেতা ধমক দিকে বললেন, ‘এই ব্যাটা, চুপ থাক।’\n" +
                ".\n" +
                "পরদিন নাপিতের কাছে চুল কাটাতে এলেন এক সরকারি কর্মকর্তা।\n" +
                "নাপিত তাঁর চুল কাটতে কাটতে বললেন, ‘স্যার, দুদক নাকি আপনারে খুঁজতেছে! যেকোনো দিন ক্যাক কইরা ধইরা জেলে ঢুকায় দিব! আপনি নাকি দুর্নীতি করেন!’ ঘুষ ছাড়া কাজ করেন না।\n" +
                "সরকারি কর্মকর্তাও নাপিতকে ধমক দিয়ে চুপ করালেন।\n" +
                ".\n" +
                "কয়দিন বাদেই নাপিতের দোকান ঘেরাও করল পুলিশ। নাপিতকে আটক করে বলল,\n" +
                "‘এই ব্যাটা, তুই নাকি তোর কাস্টমারদের আজেবাজে কথা বলিস? তোর উদ্দেশ্য কী?’\n" +
                "নাপিত আমতা আমতা করে বললেন, ‘স্যার! এসব কথা বললে ভয়ে ওনাদের চুল খাড়া হয়ে যায়। আমার চুল কাটতে সুবিধা হয়। তাই বলি |"));
        arrayList8.add(new Smsinformation(8,"12","","ছোট্ট মিতু গেছে গোয়েন্দাদের অফিসে। দেয়ালে ‘ওয়ান্টেড’-এর তালিকায় টাঙানো অপরাধীদের ছবি দেখে সে গোয়েন্দা অফিসারকে প্রশ্ন করল, ‘তোমরা কি সত্যিই ওদের গ্রেপ্তার করতে চাও?’\n" +
                "গোয়েন্দা: অবশ্যই।\n" +
                "মিতু: তাহলে ছবি তোলার সময়ই আটকে রাখলে না কেন?!"));
        arrayList8.add(new Smsinformation(8,"13","","ছোট্ট ছেলেটা বাবার সঙ্গে চট্টগ্রাম যাবে বলে ট্রেনে উঠেছে । এটাই তার জীবনের প্রথম ট্রেন- ভ্রমণ। জানালা দিয়ে বাইরে তাকিয়ে ছিল সে। একটা বাড়ি পেছনের দিকে চলে গেল, চলে গেল একটা গাছ, একটা ল্যাম্পপোস্ট…অবাক হয়ে দেখছিল ছেলেটা। বাবাকে সে চোখ বড় বড় করে প্রশ্ন করল, ‘বাবা, চট্টগ্রাম কখন ট্রেনের কাছে এসে পৌঁছাবে?!"));
        arrayList8.add(new Smsinformation(8,"44","","ছুটিতে আমেরিকায় বেড়াতে গিয়েছিলেন জলিল। ফিরে আসার পর বন্ধু শফিক তাঁকে জিজ্ঞেস করলেন, ‘কিরে, আমেরিকানদের কথা বুঝতে কোনো সমস্যা হয়নি তো?’\n" +
                "জলিল: আমার কোনো সমস্যা হয়নি। সমস্যা যা হওয়ার ওদের হয়েছে!"));
        arrayList8.add(new Smsinformation(8,"45","","ছুটি কাটাতে মিসর গেছেন মফিজ। সেখানে পিরামিড দেখে ফেরার পর মফিজের এক বন্ধু জিজ্ঞেস করল, ‘কিরে, কেমন দেখলি পিরামিড?’\n" +
                "মফিজ: দূর! বড় বড় ত্রিভুজের জ্বালায় তো কিছু দেখাই যায় না!"));
        arrayList8.add(new Smsinformation(8,"46","","ছুটিতে ঢাকায় বেড়াতে এসেছেন এক আমেরিকান। ঢাকার ট্যাক্সিতে উঠে তিনি খুবই বিরক্ত।\n" +
                "আমেরিকান: আপনাদের এখানে ট্যাক্সি এত ধীরগতিতে চলে! রাস্তাগুলোও ভীষণ খারাপ। আমাদের ওখানে সাঁই সাঁই করে ট্যাক্সি চলে।\n" +
                "গন্তব্যে পৌঁছানোর পর মিটার দেখে আমেরিকানের চক্ষু চড়কগাছ! বললেন, ‘সে কি! মিটারে এত টাকা উঠল কী করে?’\n" +
                "ট্যাক্সি ড্রাইভার: আমার কী দোষ, স্যার? রাস্তা আমার দেশের হলে কী হবে, মিটার তো আপনার দেশের!"));
        arrayList8.add(new Smsinformation(8,"47","","ছুটি কাটাতে সৌদি আরব গেছেন আবুল। সেখান থেকে ফেরার পর আবুলের এক বন্ধু জিজ্ঞেস করল, ‘কিরে, কেমন দিন কাল কাটল?’\n" +
                "আবুল: দূর! সেখানের সবাই বিদেশী ভাষায় কথা বলে, শুধু আযানটা তারা আমাদের মতো বাংলায় দেয়!"));
        arrayList8.add(new Smsinformation(8,"48","","ছেলে: বাবা, প্রতিদিন একটা আপেল খেলে নাকি ডাক্তার থেকে দূরে থাকা যায়?\n" +
                "\n" +
                "বাবা: হুমম, যায় তো।\n" +
                "\n" +
                "ছেলে: তাহলে একটা আপেল দাও তো।\n" +
                "\n" +
                "বাবা: তুই না আপেল খেতে চাস না! আজ কী হলো হঠাৎ?\n" +
                "\n" +
                "ছেলে: ডাক্তার সাহেবের গাড়ির জানালা ভেঙে ফেলেছি তো!"));
        arrayList8.add(new Smsinformation(8,"49","","ছোট্ট বাবু রিমনকে দেখলাম পাশের বাসার বাবুটার সঙ্গে খেলছে। আমি রিমনের সঙ্গে কিছু কথা বলে দুষ্টুমি করলাম। তারপর বাসায় চলে এলাম।\n" +
                "\n" +
                "আজ দুপুরে গোসল করে বারান্দায় গেলাম কাপড় দিতে, এমন সময় পাশের বাসার বাবুটা আমাকে জিগ্যেস করল, ‘আপনি কি রিমনকে চিনেন?’\n" +
                "\n" +
                "আমি মাথা নাড়লাম।\n" +
                "\n" +
                "তারপর আমাকে হতভম্ব করে দিয়ে সে জিগ্যেস করল, ‘চিনলে বলেন তো রিমনের নাম কী?"));
        arrayList8.add(new Smsinformation(8,"50","","ছোট্ট বাবুকে প্রশ্ন করলেন মিস, ‘ছোট্ট বাবু, বলো তো দেখি, তোমার বাড়ির পাশের পুকুরে তিনটা হাঁস ভাসছে। যদি তুমি একটাকে শটগান দিয়ে গুলি করো, কটা থাকবে?\n" +
                "' বাবু খানিকটা ভেবে বললো,\n" +
                "‘উমম, তাহলে গুলির শব্দ শুনে সব উড়ে চলে যাবে, একটাও থাকবে না।'\n" +
                "মিস হেসে বললেন,\n" +
                "‘উঁহু, ছোট্ট বাবু, তিনটার মধ্যে একটাকে গুলি করলে বাকি থাকবে দুটো। কিন্তু তোমার চিন্তাধারা আমার পছন্দ হয়েছে।'\n" +
                "বাবু মুচকি হেসে বললো,\n" +
                "‘তাহলে মিস, আমি একটা প্রশ্ন করি। বলুন তো, আইসক্রীম পার্লার থেকে তিন মহিলা বের হয়েছে কোন আইসক্রীম কিনে। একজন আইসক্রীম কামড়ে খাচ্ছে, একজন চেটে খাচ্ছে, আরেকজন চুষে খাচ্ছে। এদের মধ্যে কে বিবাহিত?\n" +
                "' মিস খানিকটা ভেবে বললেন, ‘ইয়ে, মানে বাবু, আমার মনে হয় শেষের জন।'\n" +
                "বাবু বললো,\n" +
                "‘উঁহু মিস, এদের মধ্যে যার হাতে বিয়ের আঙটি আছে, সে-ই বিবাহিত, কিন্তু আপনার চিন্তাধারা আমার পছন্দ হয়েছে।'"));

/**
 * 9
 */

        arrayList9.add(new Smsinformation(9,"","1","ছেলে: দুই হাত পেছনে দিয়ে ভাবুকের মতো বাবার সামনে পায়চারি করছে দেখে বাবা বলছে, কি রে কি হয়েছে?\n" +
                "\n" +
                "ছেলে: ভাবছি ।\n" +
                "\n" +
                "বাবা: কি ভাবছিস?\n" +
                "\n" +
                "ছেলে: বাবা তুমি যেন ছোট বেলায় অংকে কত পেয়েছিলে?\n" +
                "\n" +
                "বাবা: ও এই কথা, ১০০ তে ০০ পেয়েছিলাম, কেন বলতো?\n" +
                "\n" +
                "ছেলে:তাই ভাবছি ইতিহাস কিভাবে ঘুরে ফিরে আসে।"));
        arrayList9.add(new Smsinformation(9,"","2","গৃহপরিচারিকার কাছে পল্টু আর সুমিকে রেখে বেড়াতে গেছেন ওদের বাবা-মা। গৃহপরিচারিকাকে বাবার চেয়ারে বসতে দেখে চেঁচিয়ে উঠল সুমি, ‘তুমি আমার বাবার চেয়ারে বসলে কেন?’\n" +
                "\n" +
                "গৃহপরিচারিকা: বাবা তো এখন বাসায় নেই। তা ছাড়া এখানে আমিই সবচেয়ে বড়, সুতরাং আমিই তোমাদের বস।\n" +
                "\n" +
                "পল্টু: তাহলে তুমি মায়ের চেয়ারে বসো।"));
        arrayList9.add(new Smsinformation(9,"","3","গার্লফ্রেন্ড : কথা দাও কখনো টাচ, কিস, আই লাভ ইউ বলা এগুলার জন্য আমাকে জোর করবা না।\n" +
                ",\n" +
                ",\n" +
                "বয়ফ্রেন্ড : যা বইন বাড়ি যা, তোর মা তোরে খুজতাসে।"));
        arrayList9.add(new Smsinformation(9,"","4","ঘড়ি চুরির কোনো জোরালো প্রমাণ না থাকায় বিচারক আসামিকে বেকসুর খালাস দিলেন।\n" +
                "ছাড়া পেয়েই আসামি বিচারকের কাছে জানতে চাইল, হুজুর, এবার ঘড়িটা ব্যবহার করতে পারব তো?\n"));
        arrayList9.add(new Smsinformation(9,"","5","চিঠি পোস্ট করতে পোস্ট অফিসে গেছে নিতু।\n" +
                "কর্মকর্তা: চিঠিটা যদি দ্রুত পৌঁছাতে চান, খরচ পড়বে ৪০ টাকা। আর যদি স্বাভাবিক নিয়মেই পাঠাতে চান, তাহলে খরচ পড়বে ৫ টাকা।\n" +
                "নিতু: সমস্যা নেই, আমার তেমন কোনো তাড়া নেই। প্রাপক তার জীবদ্দশায় চিঠিটা পেলেই হলো।\n" +
                "কর্মকর্তা: তাহলে আপনাকে ৪০ টাকাই দিতে হবে!"));
        arrayList9.add(new Smsinformation(9,"","6","চোর: জলদি, পুলিশ আসছে! জানালা দিয়ে লাফিয়ে পড়।\n" +
                "সহকারী: কিন্তু ওস্তাদ, আমরা যে এখন তের তলায় আছি।\n" +
                "চোর: দুর গাধা! এখন কি কুসংস্কার নিয়ে মাথা ঘামানোর সময়?"));
        arrayList9.add(new Smsinformation(9,"","7","চেয়ারম্যান: আমি যদি এবার চেয়ারম্যান হতে পারি তাহলে এই এলাকায় একটি ব্রিজ করে দিব।\n" +
                "\n" +
                "জনৈক ব্যাক্তি: এই গ্রামে তো কোনো খাল নেই, আপনি ব্রিজ করবেন কিভাবে?\n" +
                "\n" +
                "চেয়ারম্যান: প্রথমে খাল করব তারপর ব্রিজ করব!"));
        arrayList9.add(new Smsinformation(9,"","8","চিঠি পোস্ট করতে পোস্ট অফিসে গেছে নিতু।\n" +
                "কর্মকর্তা: চিঠিটা যদি দ্রুত পৌঁছাতে চান, খরচ পড়বে ৪০ টাকা। আর যদি স্বাভাবিক নিয়মেই পাঠাতে চান, তাহলে খরচ পড়বে ৫ টাকা।\n" +
                "নিতু: সমস্যা নেই, আমার তেমন কোনো তাড়া নেই। প্রাপক তার জীবদ্দশায় চিঠিটা পেলেই হলো।\n" +
                "কর্মকর্তা: তাহলে আপনাকে ৪০ টাকাই দিতে হবে!"));
        arrayList9.add(new Smsinformation(9,"","9","চোর: জলদি, পুলিশ আসছে! জানালা দিয়ে লাফিয়ে পড়।\n" +
                "সহকারী: কিন্তু ওস্তাদ, আমরা যে এখন তের তলায় আছি।\n" +
                "চোর: দুর গাধা! এখন কি কুসংস্কার নিয়ে মাথা ঘামানোর সময়?"));
        arrayList9.add(new Smsinformation(9,"","10","\n" +
                "চেয়ারম্যান: আমি যদি এবার চেয়ারম্যান হতে পারি তাহলে এই এলাকায় একটি ব্রিজ করে দিব।\n" +
                "\n" +
                "জনৈক ব্যাক্তি: এই গ্রামে তো কোনো খাল নেই, আপনি ব্রিজ করবেন কিভাবে?\n" +
                "\n" +
                "চেয়ারম্যান: প্রথমে খাল করব তারপর ব্রিজ করব!"));
        arrayList9.add(new Smsinformation(9,"","11","এখন আমার হাতে এক বোতল বিষ। \n" +
                "এত জ্বালা আমার সহ্য হয় না।\n" +
                "জানি এটা পাপ। এত যন্ত্রণা আর ভালো লাগে না।\n" +
                "তাই আমি যাচ্ছি .\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                "ইদুর মারতে।"));
        arrayList9.add(new Smsinformation(9,"","12","আব্বু বলেছে টার্গেট সব সময় বড় রাখতে হবে তাইতো আমি ভার্সিটির বড় আপুদের টার্গট করি"));
        arrayList9.add(new Smsinformation(9,"","13","আমি চোর ! আমার বাপ চোর ! আমার দাদা চোর ! আমার নানা চোর ! আমার ১৪ গোষ্টি চোর...!\n" +
                "..\n" +
                "..\n" +
                "..\n" +
                "..\n" +
                "আরে ভাই আস্তে পড়েন আশেপাশের মানুষ শুনলে তো ধইরা পিটাইবো...."));
        arrayList9.add(new Smsinformation(9,"","14","ইন্টারভিউ দিতে এসেছে এক চাকরিপ্রার্থী।\n" +
                ": আপনার বয়স কত?\n" +
                ": ত্রিশ বছর।\n" +
                ": আপনি কত বছর চাকরি করেছেন?\n" +
                ": পঁয়ত্রিশ বছর।\n" +
                ": এটা কী করে সম্ভব?\n" +
                ": ওভার টাইম করেছি, স্যার।"));
        arrayList9.add(new Smsinformation(9,"","15","ইন্টারভিউ বোর্ডে এক যুবককে প্রশ্ন করা হলো, বল তো “ডাক্তার আসিবার পূর্বে রোগী মারা গেল” এর ইংরেজি কি হবে?\n" +
                ": এটার ইংরেজি পারি না স্যার । আরবিটা পারি…\n" +
                ": আরবিটা পার? ঠিক আছে বল ।\n" +
                ": ইন্নালিল্লাহ ওয়া ইন্না ইলাইহে রাজিউন ।"));
        arrayList9.add(new Smsinformation(9,"","16",": ওগো মনে হচ্ছে ঘরে চোর ঢুকেছে, ওগো শুনছ? তুমি জেগে আছ?\n" +
                ": না।\n"));
        arrayList9.add(new Smsinformation(9,"","17","উকিল বলছেন আসামিকে, ‘এবারের মতো তোমাকে বেকসুর খালাস পাইয়ে দিলাম। কিন্তু এখন থেকে পাজি লোকজনের কাছ থেকে দূরে থাকার চেষ্টা করবে।’\n" +
                "আসামি: অবশ্যই স্যার। আমি অবশ্যই আপনার কাছ থেকে দূরে থাকার চেষ্টা করব।"));
        arrayList9.add(new Smsinformation(9,"","18","উকিল সাহেব হস্তদন্ত হয়ে বাড়ি ফিরলেন অনেক আগেই । উকিল গিন্নী অবাক হয়ে বললেন কোন দিকে চাঁদ উঠল আজ । এত সকাল সকাল সাহেব যে বাড়ী চলে এলেন । সে কথা পরে বলছি, উকিল সাহেব বললেন, আগে তোমার যাবতীয় কাপড় চোপড় আর গহনাগুলো শিগরীর তোমার বাপের বাড়ীতে রেখে আসোগে ।\n" +
                "আরো অবাক হয়ে গিন্নি বললেন, ওমা সে কি কেন ?\n" +
                "আজ এক অতি কুখ্যাত চোরকে বেকুসুর খালাস দিয়ে এসেছি। সে নাকি সন্ধার পর কৃতজ্ঞতা জানাতে হবে ।"));
        arrayList9.add(new Smsinformation(9,"","20","উকিলঃ সেকি ম্যাডাম ? আপনার স্বামী তো পাঁচ বছর আগে মারা গেছেন । তাহলে চার বছরের আর একটি দু’বছরের বাচ্চা এলো কোথা থেকে ?\n" +
                "ভদ্রমহিলা রাগের স্বরেঃ তা আমি তো বেঁচে আছি না কি?"));
        arrayList9.add(new Smsinformation(9,"","21","মদন: উকিল সাহেব, আমার প্রতিবেশী দস্তগীর সাহেবের কাছে আমি ৫০ হাজার টাকা পাই। কিছুদিন আগেই তিনি ধার নিয়েছিলেন। কিন্তু ফেরত দিচ্ছেন না। আমি এখন কী করতে পারি?\n" +
                "উকিল: আপনি যে তাঁর কাছে টাকা পান, কোনো প্রমাণ আছে?\n" +
                "মদন: না তো।\n" +
                "উকিল: ঠিক আছে। আপনি তাঁকে একটা চিঠি পাঠান। চিঠিতে লিখুন, ‘আপনার কাছে আমি যে এক লাখ টাকা পাই, সেটা জলদি ফেরত দিন।’\n" +
                "মদন: তাতে কি কোনো লাভ হবে? আমি তো এক লাখ না, ৫০ হাজার টাকা পাই। দস্তগীর চিঠির উত্তরে সেটাই লিখবে।\n" +
                "উকিল: ব্যস, সেটাই তো চাই। আপনার হাতে তখন একটা প্রমাণ থাকবে!"));
        arrayList9.add(new Smsinformation(9,"","22","উকিল: দুর্ঘটনার সময় আপনি কি সেখানে উপস্থিত ছিলেন?\n" +
                "প্রত্যক্ষদর্শী: হ্যাঁ।\n" +
                "উকিল: দুর্ঘটনাস্থল থেকে আপনি ঠিক কতটুকু দূরে ছিলেন?\n" +
                "প্রত্যক্ষদর্শী: ৪০ ফুট সাড়ে ছয় ইঞ্চি।\n" +
                "উকিল: হুমম্। এত নিশ্চিত হয়ে বললেন কী করে?\n" +
                "প্রত্যক্ষদর্শী: গজ ফিতা দিয়ে মেপে রেখেছিলাম।\n" +
                "উকিল: কেন?\n" +
                "প্রত্যক্ষদর্শী: কারণ, জানতাম, আপনার মতো কোনো একজন আমাকে এই প্রশ্নটা করবে!"));
        arrayList9.add(new Smsinformation(9,"","23","উকিল: মাননীয় আদালত, সব তথ্যের ভিত্তিতে এটা নিঃসন্দেহে প্রমাণিত যে, আমার মক্কেল জনাব ছক্কু সম্পূর্ণ নির্দোষ। ব্যাংক ডাকাতির সঙ্গে তিনি কোনোভাবেই জড়িত নন। অতএব, তাঁকে বেকসুর খালাস দেওয়া হোক।\n" +
                "বিচারক: তথ্য-প্রমাণের ভিত্তিতে জনাব ছক্কুকে বেকসুর খালাস দেওয়া হলো। জনাব ছক্কু, আপনার কি কিছু বলার আছে?\n" +
                "ছক্কু: আমাকে কি ব্যাংকের টাকাগুলো ফেরত দিতে হবে?"));
        arrayList9.add(new Smsinformation(9,"","24","উকিল: আপনার জন্মদিন কবে?\n" +
                "বিবাদী: ১৫ জুলাই।\n" +
                "উকিল: কোন বছর?\n" +
                "বিবাদী: প্রতিবছর!\n"));
        arrayList9.add(new Smsinformation(9,"","25","উকিল: আপনি বলতে চাইছেন, আপনার কিছুই মনে নেই?\n" +
                "সাক্ষী: জি না, কিছুই মনে নেই।\n" +
                "উকিল: কী কী ভুলে গেছেন, সেটা কি মনে আছে?"));
        arrayList9.add(new Smsinformation(9,"","26","উকিল: আপনার তিন সন্তান, তাই তো?\n" +
                "আসামি: হ্যাঁ।\n" +
                "উকিল: ছেলে কজন?\n" +
                "আসামি: একজনও নয়।\n" +
                "উকিল: মেয়ে কজন?"));
        arrayList9.add(new Smsinformation(9,"","27","উকিল: আপনার তিন সন্তান, তাই তো?\n" +
                "আসামি: হ্যাঁ।\n" +
                "উকিল: ছেলে কজন?\n" +
                "আসামি: একজনও নয়।\n" +
                "উকিল: মেয়ে কজন?"));
        arrayList9.add(new Smsinformation(9,"","28","উকিল : তা হলে ঐ ভদ্রলোক যখন রিভলবার হাতে আপনার দিকে এগিয়ে আসছিলেন তখন আপনার হাতে কিছুই ছিল না?\n" +
                "মক্কেল : ছিল, ঐ ভদ্রলোকের স্ত্রীই ছিল আমার হাতে কিন্তু রিভলবারের সামনে মেয়ে-মানুষ আর কী কাজে আসবে বলুন।"));
        arrayList9.add(new Smsinformation(9,"","29","ভদ্রলোক : আমি আপনার কাছে জানতে এসেছি আমার ডিভোর্স করার গ্রাউন্ড আছে কি না?\n" +
                "উকিল : আপনি কি বিবাহিত?\n" +
                "ভদ্রলোক : অবশ্যই।\n" +
                "উকিল : তা হলে গ্রাউন্ড আছে।"));
        arrayList9.add(new Smsinformation(9,"","30","এক মক্কেল দৌড়ে হাঁপাতে হাঁপাতে এসে উকিলের কক্ষে ঢুকে তাঁর সঙ্গে কথা বলছেন—\n" +
                "মক্কেল: উকিল সাহেব, আমি একটু আগে সিনেমা দেখতে গিয়েছিলাম। কিন্তু সেখানে তো সিনেমা দেখানোর বদলে জ্যান্ত মানুষ কাটা দেখাচ্ছে।\n" +
                "উকিল: তা আপনি সিনেমা দেখার জন্য কোন সিনেমা হলে গিয়েছিলেন, শুনি?\n" +
                "মক্কেল: কেন? সব থিয়েটারেই তো সিনেমা চলে। আমি যে থিয়েটারে গিয়েছিলাম, তার সামনে লেখা ছিল ‘অপারেশন থিয়েটার’।"));
        arrayList9.add(new Smsinformation(9,"","31","এক প্রখ্যাত উকিল বিচারকের চাকরি পেয়েও ফিরিয়ে দিলেন। তার যুক্তি- ‘ঘন্টার পর ঘন্টা জুড়ে আমি পাগল-ছাগলের প্রলাপ শুনতে রাজি নই।"));
        arrayList9.add(new Smsinformation(9,"","32","উকিল: আচ্ছা, আপনি ওই লোকের ঘড়ি চুরি করলেন কেন?\n" +
                "মক্কেল: না! আমি তো চুরি করিনি। তিনি নিজেই আমাকে ঘড়িটি দিয়েছেন।\n" +
                "উকিল: কী বলছেন! তিনি কখন আপনাকে ঘড়িটি দিলেন?\n" +
                "মক্কেল: কেন? আমি যখন আমার বন্দুকটি দেখালাম!"));
        arrayList9.add(new Smsinformation(9,"","33","উকিল : আপনি আপনার ভাইদের বিরুদ্ধে মামলা করতে চাচ্ছেন কেন?\n" +
                "বাদী : স্যার আমার বড় ভাই তার ঘরে তিনটা ছাগল পালে, মেজো ভাই তার ঘরে ২টা শুয়োর পালে, সেজো ভাই পালে চার ভেড়া … গন্ধে টিকতে পারি না।\n" +
                "উকিল : আপনার ঘরের জানালা খুলে রাখলেই পারেন।\n" +
                "বাদী : পাগল হয়েছেন। জানালা খুলি আর আমার পালা বাদুড়গুলি সব উড়ে যাক।"));
        arrayList9.add(new Smsinformation(9,"","34","উকিলবাবু : তোমার তাহলে চুরির মামলা? কিন্তু মামলার খরচ চালাবে কী করে?\n" +
                "মক্কেল : হুজুর টাকাপয়সা নেই। তবে একটা গরু আছে।\n" +
                "উকিলবাবুন : ঠিক আছে। তাহলে গরু বেচেই আমার ফি দেবে। তা পুলিশ তোমার নামে কোন জিনিস চুরির মামলা দিয়েছে।\n" +
                "মক্কেল : ওই গরু চুরির মামলা হুজুর।"));
        arrayList9.add(new Smsinformation(9,"","35","\n" +
                "উকিল : এই লোকটা কি আপনাকে জঘন্য গালাগালি করেছে?\n" +
                "বাদী : জি, স্যার ! আমাকে ও যে সব গালাগালি দিয়েছে তা ভদ্রলোকের সামনে বলার না।\n" +
                "উকিল : ঠিক আছে, আমরা সবাই আদালত কক্ষ থেকে বেরিয়ে যাচ্ছি, আপনি ওই গালাগালিগুলো জজ সাহেবকে শুনিয়ে দিন।"));
        arrayList9.add(new Smsinformation(9,"","36","উকিলঃ লোকটা দেখতে কেমন ছিল সেটা বলতে পারবেন?\n" +
                "সাক্ষীঃ সে ছিল মাঝারি গড়নের আর তার মুখে দাড়ি ছিল।\n" +
                "উকিলঃ আচ্ছা। এবার বলুন, সে পুরুষ না মহিলা?"));
        arrayList9.add(new Smsinformation(9,"","37","উকিলঃ আচ্ছা, ডাক্তার সাহেব, আপনি কতগুলো মৃত মানুষের ময়নাতদন্ত করেছেন?\n" +
                "সাক্ষীঃ সবগুলোই তো মৃত। জীবিত মানুষের ময়নাতদন্তে অনেক ঝামেলা।"));
        arrayList9.add(new Smsinformation(9,"","38","উকিলঃ দেখুন তো, এই ছবিটা চিনতে পারেন কি না?\n" +
                "সাক্ষীঃ আরে, এটা তো আমার ছবি।\n" +
                "উকিলঃ আচ্ছা, এবার বলুন, এই ছবিটা যখন তোলা হয় তখন কি আপনি সেখানে উপস্থিত ছিলেন?\n" +
                "জোকস ৮১\n" +
                "\n" +
                "\n" +
                "উকিলঃ আমার সব প্রশ্নের উত্তর অবশ্যই মুখে বলবে, ঠিক আছে?\n" +
                "সাক্ষীঃ আচ্ছা।\n" +
                "উকিলঃ এবার বলো, তুমি কোন স্কুলে পড়তে?\n" +
                "সাক্ষীঃ মুখে···।"));
        arrayList9.add(new Smsinformation(9,"","39","উকিলঃ তার মানে আপনি বলতে চাইছেন, ওটা কী জিনিস আপনি তা জানেন না?\n" +
                "সাক্ষীঃ হ্যাঁ।\n" +
                "উকিলঃ এমনকি ওটা দেখতে কেমন তাও জানেন না?\n" +
                "সাক্ষীঃ হ্যাঁ।\n" +
                "উকিলঃ তাহলে ওটা সম্পর্কে একটু বর্ণনা দিন তো।"));
        arrayList9.add(new Smsinformation(9,"","40","উকিলঃ আপনি সেখান থেকে কখন গিয়েছিলেন?\n" +
                "সাক্ষীঃ রাত আটটার দিকে।\n" +
                "উকিলঃ তার মানে, আপনি চলে যাওয়ার আগ পর্যন্ত সেখানেই ছিলেন, ঠিক কি না?"));
        arrayList9.add(new Smsinformation(9,"","41","\n" +
                "উকিলঃ যে লোকটা আপনার ওপর হামলা চালিয়েছিল সে দেখতে কেমন ছিল সেটা বলতে পারবেন?\n" +
                "সাক্ষীঃ না, সে মুখোশ পরে ছিল।\n" +
                "উকিলঃ মুখোশের নিচে কী ছিল?\n" +
                "সাক্ষীঃ আ··· তার মুখ।"));
        arrayList9.add(new Smsinformation(9,"","12","উকিলঃ আপনার সঙ্গে যে ছেলেটি থাকে তার বয়স কত হবে?\n" +
                "সাক্ষীঃ ৩৮ বা ৩৫ বছর হবে, সঠিক বলতে পারছি না।\n" +
                "উকিলঃ সে আপনার সঙ্গে কত দিন বসবাস করছে?\n" +
                "সাক্ষীঃ ৪৫ বছর ধরে।"));
        arrayList9.add(new Smsinformation(9,"","13","উকিলঃ তাহলে বলুন, দুর্ঘটনাটা কোথায় হয়েছিল?\n" +
                "সাক্ষীঃ ৪৯৯ মাইলফলকের কাছে।\n" +
                "উকিলঃ ৪৯৯ মাইলফলকটা কোথায়?\n" +
                "সাক্ষীঃ সম্ভবত ৪৯৮ আর ৫০০ মাইলফলকের মাঝামাঝি।"));
        arrayList9.add(new Smsinformation(9,"","44"," এক অফিসের বস কেবল বিবাহিত লোকদেরই নিয়োগ দেন। একদিন তাঁর বউ তাঁকে জিজ্ঞেস করল, ‘তুমি কেবল বিবাহিতদেরই নিয়োগ দাও কেন?’\n" +
                "স্বামী বললেন, ‘কারণ তারা সহজে বাসায় যেতে চায় না, ধমক সহ্য করে আর মুখ বন্ধ রাখতে জানে।’"));
        arrayList9.add(new Smsinformation(9,"","45","একদিন দুপুরে বসের মুড বেশ ভালো। সে কর্মচারীদের একের পর এক কৌতুক শোনাচ্ছিল। কর্মচারীরাও হাসছিল হো হো করে। শুধু হাসছিলেন না এক মহিলা।\n" +
                "বস বললেন, ‘কী ব্যাপার, তুমি হাসছ না কেন?’\n" +
                "মহিলা: আমি কাল চাকরি ছেড়ে দিচ্ছি। আমার অত হাসাহাসি না করলেও চলবে।"));
        arrayList9.add(new Smsinformation(9,"","46"," এক অফিসের কর্মচারীরা সবাই পৌঁছে যান একদম ঠিক সময়ে।\n" +
                "বসকে বললেন তাঁর এক বন্ধু, ‘তোমার কর্মচারীদের কী এমন জাদু করেছ যে তাঁরা এত সময়ানুবর্তী হয়ে গেল?’\n" +
                "বস হাসতে হাসতে বললেন, ‘জাদু না হে, আমার অফিসে একটা চেয়ার কম। সবাই সময়মতো পৌঁছাতে চেষ্টা করে, যেন দাঁড়িয়ে থাকতে না হয়!’"));
        arrayList9.add(new Smsinformation(9,"","47","এমপ্লয়মেন্ট এক্সচেঞ্জে হন্তদন্ত হয়ে ঢুকলেন এক লোক। বললেন, দয়া করে আমাকে এখনই একটা চাকরি খুঁজে দিন। আমার স্ত্রী ছাড়াও এগারো সন্তান!\n" +
                "চোখ বড় বড় করে কর্মকর্তা বললেনঃ এ ছাড়া আর কোন কাজটা আপনি করতে পারেন?"));
        arrayList9.add(new Smsinformation(9,"","48","একটি ফ্যাক্টরির মালিক বোঝাচ্ছিলেন তার শ্রমিকদের দশটার পর থেকে পাঁচটা পর্যন্ত এই সাত ঘন্টা খাটলে পর দিন পাঁচ টাকা মজুরি । তারপর যদি কেউ ওভার টাইম করে খাটতে চাও- তাহলে মাত্র পুরো তিন ঘন্টা খাটলে পুরো পাঁচ টাকা দেওয়া হবে । তাহলে স্যার আমরা শুধু ওভার টাইম খাটবো সেটাই ভালো ।"));
        arrayList9.add(new Smsinformation(9,"","49","একদিন এক ক্লার্ক তার এক বন্ধুকে বলল, জানিস কাল থেকে আমার অফিস দু সপ্তাহ ছুটি । বন্ধুটি জানতে চাইল, কি ভাবে? কাল থেকে আমি এক সপ্তাহ ছুটিতে যাচ্ছি। তার পরের সপ্তাহে আমার বস ছুটিতে যাচ্ছেন।"));
        arrayList9.add(new Smsinformation(9,"","50","একজন সহকারী অফিসে বহু পুরানো ফাইল লাট হয়ে পড়ে থাকতে দেখে সেই অফিসের অফিসার সচিবালায়ে উর্দ্ধতন কর্তৃপক্ষের কাছে নোট পাঠালেন ফাইল গুলো মিছে মিছে জায়গা জোড় করে আছে এগুলো পুরানো কাগজের দরে বেচে দেয়া যেতে পারে কি ?\n" +
                "সচিবালয় থেকে নোট এলো বেচে দিতে পারেন, তবে তার আগে প্রতোকটা ফাইলের তিনটে করে কপি করিয়ে রাখবেন।"));

        /**
         * 10
         */

        arrayList10.add(new Smsinformation(10,"","1","এক জেলখানা থেকে একজন বিপজ্জনক আসামি পালিয়ে গেছে। সঙ্গে সঙ্গে রাজ্যের সব থানায় বিভিন্ন অ্যাঙ্গেল থেকে তোলা আসামির চারটি ছবিসহ সতর্কবার্তা পাঠানো হয়। দিন তিনেকের মধ্যেই এক সীমান্তবর্তী থানা থেকে সংবাদ এল ছবির তিন জন আসামিই ধরা পড়েছে। একজনকে এখনো খুঁজে পাওয়া যায় নি।"));
        arrayList10.add(new Smsinformation(10,"","2","এক কয়েদি তার কয়েদখানার পোশাকটা এতটাই পছন্দ করত যে ছাড়া পাওয়ার পরও এটা সে সঙ্গে রেখে দেয়। আর এই পোশাকই নিশ্চিত করল তার আবার জেলে যাওয়া।\n" +
                "ওই কয়েদি তার প্রিয় পোশাকটি গায়ে চাপিয়েই শহরে ঘুরে বেড়াতে লাগল। শহরের সতর্ক বাসিন্দারা পুলিশে খবর দিল। পুলিশের কাছে সে স্বীকার করল, জেল থেকে বের হয়ে আসার সময় লুকিয়ে এই পোশাকটা সঙ্গে নিয়ে এসেছে সে। অতএব রাষ্ট্রীয় সম্পত্তি চুরি করার অভিযোগে আবার কয়েদখানায় বন্দী হলো লোকটা।\n"));
        arrayList10.add(new Smsinformation(10,"","3","একজন বিখ্যাত অভিনেতার ইন্টারভিউ চলছে।\n" +
                "প্রশ্নকর্তা: আপনি তো যেকোনো চরিত্রই ফুটিয়ে তুলতে পারেন?\n" +
                "অভিনেতা: হ্যাঁ, শুধু নিজের চরিত্রটাই ফুটিয়ে তুলতে পারি না।"));
        arrayList10.add(new Smsinformation(10,"","4","একটা বিয়ার কোম্পানিতে বিয়ারের স্বাদ পরীক্ষা করার জন্য লোক নিয়োগ দিচ্ছিল। বাজে চেহারার এক মাতাল ইন্টারভিউ দিতে এল। কোম্পানির ডিরেক্টর একে দেখেই বাতিলের খাতায় রেখে দিলেন। কিন্তু ইন্টারভিউ দিতে যখন এসেছে, তখন তো ইন্টারভিউ নিতেই হবে।\n" +
                "প্রথমে একগ্লাস বিয়ার দেয়া হলো। মাতাল সেটা একটু খেয়ে সব উপকরণের সঠিক নাম বলে দিল। ডিরেক্টরের ভুরু কুঁচকে গেল।\n" +
                "এবার আরেক গ্লাস দেয়া হলো। সেটাও ঠিক মতো বলে দিল।\n" +
                "ডিরেক্টর এবার তার সেক্রেটারিতে ইশারা দিয়ে কিছু করতে বললেন।\n" +
                "সেক্রেটারি গ্লাসে প্রস্রাব করে নিয়ে এসে মাতালের সামনে রাখল।\n" +
                "মাতাল একটু খেয়ে বলল: খুব সুন্দরী, বয়স ২৬ বছর, তিন মাসের প্রেগন্যান্ট। আমাকে চাকরিটা না দিলে আপনার স্ত্রীকে গিয়ে বলে দেব বাবাটা কে।"));
        arrayList10.add(new Smsinformation(10,"","5","ইন্টারভিউ বোর্ডে সর্দারজিকে প্রশ্ন করলেন এক প্রশ্নকর্তা, ‘কল্পনা করো তো, তুমি একটা ২০ তলা বাড়ির ১৫ তলায় আছ। এমন সময় ভীষণ আগুন লেগে গেল। সবাই ছোটাছুটি শুরু করল। তুমি কী করবে?’\n" +
                "সর্দারজি: আমি কল্পনা করা বন্ধ করব!"));
        arrayList10.add(new Smsinformation(10,"","6",": একতলা ও নয়তলা থেকে পড়ে যাওয়ার মধ্যে পার্থক্য কী?\n" +
                ": নয়তলা থেকে পড়ে গেলে চিৎকার হয় এ রকম: ‘আ—আ—আ—আ—বুম!’\n" +
                "আর একতলা থেকে পড়ে গেলে: ‘বুম! ‘আ—আ—আ—আ—বুম!"));
        arrayList10.add(new Smsinformation(10,"","7","এক কৃপণ গেছে পত্রিকায় বিজ্ঞপ্তি দিতে।\n" +
                "কৃপণ: ভাই, আমার বাবা মারা গেছেন। সবচেয়ে ছোট্ট একটা বিজ্ঞপ্তি দিতে কত টাকা লাগবে?\n" +
                "কর্মকর্তা: ১০০ টাকা।\n" +
                "কৃপণ: ওহ্! এত? আচ্ছা যাক, দিলাম না হয় ১০০ টাকা। লিখুন, ‘মফিজ সাহেব মারা গেছেন।’\n" +
                "কর্মকর্তা: স্যার, কমপক্ষে আট শব্দের হতে হবে।\n" +
                "কৃপণ: আচ্ছা, তাহলে লিখুন, ‘মফিজ সাহেব মারা গেছেন। একটি গাড়ি বিক্রয় হইবে।"));
        arrayList10.add(new Smsinformation(10,"","8","এক কিপটে গেছে চিরুনি কিনতে।\n" +
                "কিপটে: ভাই সাহেব, আমার একটা নতুন চিরুনি দরকার। পুরোনোটার একটা কাঁটা ভেঙে গেছে কিনা…।\n" +
                "দোকানদার: একটা কাঁটা ভেঙে গেছে বলে আবার নতুন চিরুনি কিনবেন কেন? ওতেই তো চুল আঁচড়ে নেওয়া যায়।\n" +
                "কিপটে: না রে, ভাই, ওটাই আমার চিরুনির শেষ কাঁটা ছিল যে!"));
        arrayList10.add(new Smsinformation(10,"","9","এক ভীষণ কৃপণ বলে পরিচিত, লোকের কাছে গিয়ে কিছু দান করতে বলল অনাথ আশ্রমের দু’জন লোক।\n" +
                "লোকটি বলল, আচ্ছা যান, আগামীকাল আমি পাঠিয়ে দেব।\n" +
                "পরদিন লোকটি রাস্তা থেকে ধরে এনে কয়েকটা অনাথ বালককে আশ্রমে পাঠিয়ে দিল।"));
        arrayList10.add(new Smsinformation(10,"","10","একবার মদন তার বন্ধু হারুনকে নিয়ে হাঁটতে বের হয়েছে।\n" +
                "গলির মোড়ের পানের দোকানের সামনে দাঁড়িয়ে মদন বলল, ‘দেখি, একটা পান দ্যান তো।’\n" +
                "পানের দোকানদার স্বভাবতই জানতে চাইলেন, ‘কী দিয়ে খাবেন?’\n" +
                "মদন বলল, ‘দাঁত দিয়ে।’\n" +
                ": না, আমি জানতে চাইলাম কীভাবে খাবেন?\n" +
                ": কেন, চিবিয়ে খাব। উত্তর দিল মদন।\n" +
                ": আরে, আমি বললাম সঙ্গে কী খান?\n" +
                ": তিনি হারুন খান, আমার বন্ধু।\n" +
                ": ভাই, আমি বললাম আপনি কী জর্দা খান?\n" +
                ": না, আমি মদন খান।"));
        arrayList10.add(new Smsinformation(10,"","11","এক মাসে বাসার ফোনবিল অস্বাভাবিকভাবে বেশি এল। বাসায় জরুরি মিটিং বসল।\n" +
                "বাবা বলল, ‘আমি গত মাসে বাসার ফোনটা একবারও ধরিনি। আমি সব ফোন করেছি অফিসের ফোন থেকে।’\n" +
                "তখন মা এসে বলল, ‘আমিও গত মাসে কোনো ফোন বাসা থেকে করেছি বলে মনে হয় না। আমার সমিতির অফিসের ফোনটাই আমি ব্যবহার করি।’\n" +
                "একমাত্র ছেলে এসে বলল, ‘আমার তো বাসা থেকে ফোন করার প্রশ্নই আসে না। কোম্পানি আমাকে মোবাইল বিল দেয়। আমি অফিসের সেই মোবাইল ব্যবহার করি।’\n" +
                "এরপর বাসার কাজের মেয়ে এসে বলল, ‘তাহলে তো কোনো সমস্যাই দেখি না। আমরা সবাই যার যার অফিসের ফোন ব্যবহার করি!"));
        arrayList10.add(new Smsinformation(10,"","12","এক বড় চাষীর একমাত্র মেয়েকে বিয়ে করার জন্য ঘটক এক দিনমজুরকে প্রস্তাব দিলেন।\n" +
                "প্রস্তাব শুনে দিনমজুর বলে, আমাকে কয়েকটা দিন সময় দিন। কিছু টাকাপয়সা রোজগার করে নিই।\n" +
                ": টাকাপয়সা রোজগারের চিন্তা তোমাকে করতে হবে না। সব সম্পদের মালিক তো তুমিই হবে। এমনকি বাপ হওয়ার জন্যও তোমার পাঁচ মাসের বেশি অপেক্ষা করতে হবে না। সবকিছু তৈরিই আছে।"));
        arrayList10.add(new Smsinformation(10,"","13","এক শিকারি বন্ধুদের আড্ডায় বসে বলছে, ‘জানিস, সেবার আফ্রিকার জঙ্গলে গিয়ে আমি কতগুলো রয়েল বেঙ্গল টাইগার মেরেছি?’\n" +
                "বন্ধুরা ভ্রু কুঁচকে বলে, ‘আফ্রিকার জঙ্গলে তো রয়েল বেঙ্গল টাইগারই নেই! তুই মারবি কোথা থেকে?’\n" +
                "শিকারি: আহ্! সব যদি আমি মেরেই ফেলি, তাহলে থাকবে কোথা থেকে?!"));
        arrayList10.add(new Smsinformation(10,"","14","একবার এক চোর রাতের বেলা নারকেলগাছে উঠেছে। উদ্দেশ্যে কী, সেটা তো আর বলে দিতে হবে না। তো নারকেল নিচে ফেলতেই জোরে শব্দ হলো। সেই শব্দে গৃহস্থ মানে গাছের মালিক গেল জেগে। চিত্কার করে জানতে চাইল, ‘কে রে?’\n" +
                "গাছের ওপর থেকে চোরটা উত্তর দিল, আমি মতিন।\n" +
                ": ওইখানে কী করিস?\n" +
                ": ঘাস কাটি।\n" +
                ": ওই ছাগল, নারকেলগাছে কি ঘাস আছে নাকি?\n" +
                ": নাই দেইখ্যাই তো নেমে আসতেছি।"));
        arrayList10.add(new Smsinformation(10,"","15","এক দুষ্ট ছেলে থানায় ফোন করেছে।\n" +
                "– এইটা কি পুলিশ স্টেশন?\n" +
                "– হ্যাঁ।\n" +
                "– আপনি কি পুলিশ?\n" +
                "– হ্যাঁ।\n" +
                "– আপনার থানায় বাথরুম আছে?\n" +
                "– হ্যাঁ।\n" +
                "– আপনার বাথরুমে কমোড আছে?\n" +
                "– হ্যাঁ, আছে।\n" +
                "– তাহলে কমোডের মধ্যে মাথা ঢুকাইয়া বইসা থাকেন। এই বলে ছেলেটি ফোন কেটে দিল।\n" +
                "\n" +
                "কিছুক্ষণ পর পুলিশ নাম্বার বের করে কলব্যাক করল। ছেলেটির বাবা ফোন ধরল।\n" +
                "পুলিশ অভিযোগ করল, আপনার ছেলে আমাকে কমোডে মাথা ঢুকিয়ে বসে থাকতে বলেছে।\n" +
                "– কতক্ষণ আগে বলেছে?\n" +
                "– এই ধরেন ১০ মিনিট।\n" +
                "– তাহলে এখন মাথা বের করে ফেলেন।"));
        arrayList10.add(new Smsinformation(10,"","16","এক পকেটমারের সঙ্গে জেলখানায় দেখা করতে গেছে অন্য এক পকেটমার বন্ধু।\n" +
                "দর্শনার্থী বন্ধু: বন্ধু, তুমি কোনো চিন্তা কোরো না। আজ সকালেই আমি উকিলের সঙ্গে দেখা করে এসেছি। উকিলকে নগদ ২০ হাজার টাকাও দিয়ে এসেছি।\n" +
                "কয়েদি বন্ধু: উকিল কী করলেন? টাকাগুলো পকেটে রেখে দিলেন?\n" +
                "দর্শনাথী বন্ধু: হু। অন্তত ওনারও তা-ই ধারণা।"));
        arrayList10.add(new Smsinformation(10,"","17","এক ভদ্রলোক এসে পুলিশকে জিজ্ঞেস করলেন, ‘আমি কি এখানে গাড়িটা পার্ক করতে পারি?’\n" +
                "না।\n" +
                "তাহলে এই গাড়িগুলো এখানে কেন?\n" +
                "যারা রেখেছে, তারা কেউ আমাকে জিজ্ঞেস করেনি।"));
        arrayList10.add(new Smsinformation(10,"","18","এক গাঁয়ের এক চোর একটা সাইকেল চুরি করে বাজারে নিয়ে গেল বিক্রি করতে। এক টাউট লোক এসে সাইকেলটা দরদাম করল কিছুক্ষণ। তারপর ‘দেখি তোমার সাইকেলটা কেমন চলে’ বলে সাইকেলে চড়ে প্যাডেল মেরে একেবারে হাওয়া।\n" +
                "শুকনো মুখে বাড়ি ফিরছিল চোর। গাঁয়ের যারা তাকে সাইকেল বিক্রি করতে নিয়েযেতে দেখেছিল তারা জানতে চাইল, কি, সাইকেল বিক্রি করে কত লাভ হলো?\n" +
                "চোর বলল, লাভ হয়নি; যে দরে কিনেছিলাম সেই দরেই বেচেছি।"));
        arrayList10.add(new Smsinformation(10,"","20","এক ভদ্রলোক তার খাটের নিচে একটি চোরকে লুকিয়ে থাকতে দেখে অবাক হলেন। চোরটিকে ধরতেই সে কেঁদে বলল, আমি চুরি করতে আসি নি। বন্ধুদের সাথে লুকোচুরি খেলতে এসে এখানে লুকিয়েছি।\n" +
                ": বন্ধুরা কই?\n" +
                ": পাশের বাড়িতে ডাকাতি করছে।"));
        arrayList10.add(new Smsinformation(10,"","21","এক গরিব ভদ্রলোকের ঘরে চোর এসে আঁতিপাঁতি করে খুঁজে নিয়ে যাবার মতো কিছুই পেল না। হতাশ হয়ে চোর যখন চলে যাচ্ছে, ভদ্রলোক শুয়ে শুয়ে বললেন, দরজাটা বন্ধ করে যেও।\n" +
                "চোর দীর্ঘশ্বাস ফেলে বলল, দরজা খোলা থাকলেও আপনার ঘরে কেউ ঢুকবে না।"));
        arrayList10.add(new Smsinformation(10,"","22","এক তরুন সাইকেলে চড়ে যাচ্ছিল। হঠাৎ তার সাইকেলের চেইনটা ছিঁড়ে গেল। কী করবে ভাবছে। এমন সময় তার বন্ধু গাড়ি চালিয়ে সেখানে উপস্থিত হল এবং বিপদগ্রস্থ বন্ধুকে সাইকেলে চড়তে বলে সাইকেলটা একটা সরু দড়ি দিয়ে গাড়ির পিছনের দিককার বাম্পারের সঙ্গে বেঁধে নিল। তারপর আস্তে আস্তে গাড়ি চালাতে লাগল। ইতোমধ্যে এক আধুনিকা একটি স্পোর্টস কার চালিয়ে তার গাড়িকে ওভারটেক করে চলে গেল। গাড়ি চালক বন্ধু সবকিছু ভুলে মেয়েটিকে পেছনে ফেলার চেষ্টায় স্পিড বাড়িয়ে দিল। সাইকেলে আরোহী বন্ধু বিপন্ন হয়ে ঘন ঘন বেল দিতে লাগল। এ অবস্থায় তারা একটি পুলিশ-বক্স অতিক্রম করল। সেই পুলিশ বক্স থেকে একজন পরবর্তী পুলিশ-বক্সে ফোন করল- হাইওয়ে দিয়ে একটি মেয়ে ষাট মাইল স্পিডে একটা টয়োটা চালাচ্ছে- ওকে ছেড়ে দাও। ওদের পেছনে আরেক ছোকরা ষাট মাইল স্পিডে সাইকেল তো চালাচ্ছেই-তারপরই সাইড দেবার জন্য ঘন ঘন বেল বাজাচ্ছে- ওই ছোকরাকে ধর।\n"));
        arrayList10.add(new Smsinformation(10,"","23","এক বাড়িতে ডাকাতি হয়েছে। প্রতিবেশীর বাড়িতে অনুসন্ধানের কাজে গেছেন গোয়েন্দা।\n" +
                "গোয়েন্দা: গত রাতে পাশের বাসা থেকে আপনারা কোনো শব্দ শুনতে পেয়েছেন?\n" +
                "প্রতিবেশী: নাহ্! গোলাগুলি, চিৎকার আর ওদের কুকুরটার চেঁচামেচির যন্ত্রণায় কিছু শোনাই যাচ্ছিল না!"));
        arrayList10.add(new Smsinformation(10,"","24","এক দোকানে আগুন লেগেছে। এটা দেখে গাবলু চিন্তা করল, দোকানের ভেতর আটকে পড়াদের উদ্ধার করতে হবে। যেই ভাবা সেই কাজ। গাবলু সোজা আগুন পেরিয়ে দোকানের ভেতর ঢুকে ছয়জনকে বাইরে বের করে আনল। কিছুক্ষণ পর পুলিশ এসে গাবলুকে ধরে নিয়ে গেল। পরে তার বন্ধু থানায় গিয়ে পুলিশকে জিজ্ঞেস করল, ‘গাবলু তো আগুন থেকে মানুষকে উদ্ধার করেছে। সে তো কোনো অপরাধ করেনি।’\n" +
                "কথা শুনে পুলিশ রেগে টং, ‘অপরাধ করেনি মানে? সে যাদের দোকান থেকে বাইরে নিয়ে এসেছে, সবাই ফায়ার সার্ভিসের কর্মী।’"));
        arrayList10.add(new Smsinformation(10,"","25","এক লোক মেলায় ঘোরাঘুরি করে এক জায়গায় এসে দেখল ভাগ্য গণনা করা হচ্ছে। মজা করার জন্য সেও সেখানে গেল। জ্যোতিষী লোকটিকে দেখেই বেশ বিজ্ঞের মতো বললেন, ‘আপনি দুই সন্তানের বাবা।’\n" +
                "‘হা-হা, আপনার কি তাই-ই ধারণা? আমার তিনজন সন্তান।’ লোকটি বলল।\n" +
                "জ্যোতিষী তখন হেসে বললেন, ‘হা-হা, আপনার কি তাই-ই ধারণা?"));
        arrayList10.add(new Smsinformation(10,"","26","এক ভদ্রলোক গেছেন ডাক্তারের কাছে। ব্যবস্থাপত্র দেওয়ার পর যথারীতি ফি দেওয়ার পালা। কিন্তু ভদ্রলোকের কাছে ক্যাশ নেই। আছে চেক। বাধ্য হয়ে চেক নিলেন ডাক্তার সাহেব।\n" +
                "সপ্তাহ খানেক পরে ডাক্তারের সঙ্গে ভদ্রলোকের দেখা।\n" +
                "ডাক্তার বললেন, ‘আপনি যে চেকটা দিয়ে এসেছিলেন, সেটা তো ব্যাংক থেকে ফেরত এসেছে।’\n" +
                "ভদ্রলোকের ত্বরিত জবাব, ‘আপনি কদিন আগে যে রোগের চিকিৎসা করলেন, সেটাও যে ফেরত এসেছে!"));
        arrayList10.add(new Smsinformation(10,"","27","এক ভদ্রলোকের স্ত্রী চিত্কার করে জানতে চাইছে—\n" +
                "ওগো শুনছ? তোমার না আজ চোখের ডাক্তারের কাছে যাওয়ার কথা। গিয়েছিলে?\n" +
                "ভদ্রলোক বললেন, গিয়েছিলাম তো।\n" +
                "—ডাক্তার কী বলল?\n" +
                "—দূর! ডাক্তারের নিজের চোখ আমার চেয়ে খারাপ।\n" +
                "—সেকি, কেন?\n" +
                "—আর বোলো না, দিনের বেলায় উনি টর্চ জ্বালিয়ে আমার চোখ দেখছিলেন! তুমিই বলো, আমার চেয়ে খারাপ না হলে ওনার টর্চ লাগে?"));
        arrayList10.add(new Smsinformation(10,"","28","এক বৃদ্ধ ডাক্তারের কাছে গিয়ে বললেন, ‘ডাক্তার সাহেব, আমার শরীর খুব খারাপ!’\n" +
                "\n" +
                "ডাক্তার তাকে খুঁটিয়ে খুঁটিয়ে দেখলেন। তারপর প্রেসক্রিপশন ধরিয়ে দিয়ে বললেন, ‘একটা সবুজ ক্যাপসুল আছে, সকালে উঠে সেটা এক মগ পানি দিয়ে গিলে ফেলবেন। হলুদ রঙের একটা আছে, সেটা খাবেন দুপুরের খাবারের পর। অবশ্যই সঙ্গে এক মগ পানি। আর লাল রঙেরটাও এক মগ পানি দিয়ে খাবেন রাতের বেলা।’\n" +
                "\n" +
                "বৃদ্ধ জিজ্ঞেস করলেন, ‘কিন্তু আমার সমস্যাটা কী?’\n" +
                "\n" +
                "ডাক্তার বললেন, ‘আপনি পানি কম খান।’"));
        arrayList10.add(new Smsinformation(10,"","29","এক পিচ্চি পাড়ার রড-সিমেন্টের দোকানে এসে বলল, আপনার দোকানে কাঁঠাল পাওয়া যায়?\n" +
                "দোকানি: না।\n" +
                "পরদিন ছেলে আবার জিজ্ঞেস করল, আপনার দোকানে কি কাঁঠাল পাওয়া যায়?\n" +
                "দোকানি বিরক্ত হয়ে বলল, না!\n" +
                "তার পরদিন ছেলেটা আবার: আচ্ছা আপনার দোকানে কি কাঁঠাল পাওয়া যায়?\n" +
                "এবার দোকানি গেল খেপে: ফাজলামি করিস, না? খবরদার…আবার যদি এই প্রশ্ন করিস, তাহলে তোর মুখ সেলাই করে দেব!\n" +
                "ছেলেটা পরদিন ঠিকই হাজির। বলল, আপনার দোকানে সুঁই আছে?\n" +
                "দোকানি: না।\n" +
                "ছেলে: সুতো?\n" +
                "দোকানি: না।\n" +
                "ছেলেটা: আচ্ছা, আপনার দোকানে কি কাঁঠাল পাওয়া যায়"));
        arrayList10.add(new Smsinformation(10,"","30","একটা লোক পাবলিক টয়লেটে বসে ছিল। হঠাৎ করে পাশের টয়লেট থেকে শব্দ আসলো,\n" +
                "“মিয়া ভাই কেমন আছেন?”\n" +
                "লোকটি অবাক হয়ে বলল,”\n" +
                "হ্যা আমি ভাল আছি।\n" +
                "আবার শব্দ আসলো,\n" +
                "“কি করছেন ভাই?”\n" +
                "সে চিন্তিত হয়ে উত্তর দিল,\n" +
                "”এইতো ভাই কমোড এ বসে আছি।”\n" +
                "পাশের টয়লেট থেকে আবার বলল,\n" +
                "”আমি কি আসতে পারি?”\n" +
                "লোকটি ঘাবড়ে গেল এবং বলল,\n" +
                "”না না না প্লিজ, আমি ব্যস্ত আছি”\n" +
                "আবার কন্ঠ শোনা গেল,\n" +
                "“আচ্ছাভাই আমি আপনাকে ৫ মিনিট পরে আবার ফোন দিচ্ছি, কোন গাধার বাচ্চা জানি আবার সব কথার উত্তর দিয়া আমার লগে ফাইজলামি করতাসে।"));
        arrayList10.add(new Smsinformation(10,"","31","এক যুবক বাড়ি ভাড়া খুঁজছে।\n" +
                "বাড়িওয়ালাঃ কাকে চাই?\n" +
                "বাড়ি ভাড়া হবে?\n" +
                "আপনি কি বিবাহিত?\n" +
                "না।\n" +
                "তাহলে যান। ব্যাচেলরদের কাছে আমি বাড়ি ভাড়া দেই না।\n" +
                "মালিক মুখের উপর দরজা বন্ধ করে দিলেন। যুবক আবার দরজায় নক করল।\n" +
                "আবার কী চাই?\n" +
                "শুনুন,...\n" +
                "ব্যাচেলররা যদি এতই খারাপ হয় তাহলে ব্যাচেলরদের কাছে মেয়ে বিয়ে দিতে চান কেন?"));
        arrayList10.add(new Smsinformation(10,"","32","এক বাড়িওয়ালা মফিজকে ফোন করে বলছেন- তাঁর বাসার কলিংবেলটা নষ্ট, মফিজ যেন জরুরি ভিত্তিতে এসে সেটা ঠিক করে দেন। একদিন যায়, দুদিন যায়। পুরো চারদিন পেরিয়ে যাওয়ার পরও মফিজের কোনো নামগন্ধ না পেয়ে বাড়িওয়ালা এবার রেগেমেগে মফিজকে আবার ফোন করলেন।\n" +
                "বাড়িওয়ালাঃ আপনাকে না চারদিন আগে বলেছি, বাসার কলিংবেলটা নষ্ট। আপনার তো দেখাই পাচ্ছি না।\n" +
                "মফিজঃ কী যে বলেন। আমি গিয়েছিলাম ঠিকই। কিন্তু অনেকক্ষণ বাসার বেল চেপে কারও কোনো সাড়াশব্দ না পেয়ে শেষে ফিরে এসেছি।"));
        arrayList10.add(new Smsinformation(10,"","33","একটি মেয়ে ও একটি ছেলের ফেসবুক চ্যাট করছে.....\n" +
                "মেয়ে : kemon achen?\n" +
                "ছেলে : ভাল। আপনি?\n" +
                "মেয়ে : valo achi. ki koren?\n" +
                "ছেলে : কিছু না। মোবাইল গুঁতাই। কেন..?\n" +
                "মেয়ে : Free thakle ekta kotha boltam.\n" +
                "ছেলে : জ্বী বলুন।\n" +
                "মেয়ে : kivabe bolbo bujhte parchi na.\n" +
                "ছেলে : আপনি বলুন। আমি কিছু মনে করবো না।\n" +
                "মেয়ে : bishoy ta apni kivabe niben shetai vabchi.\n" +
                "ছেলে : আমি খুব সহজ ভাবে নিব। প্লিজ বলুন। (ছেলেটি খুব এক্সাইটেড)\n" +
                "মেয়ে : na mane ami bolte chassi....\n" +
                "ছেলে : বলুন! প্লিজ বলুন..!\n" +
                "মেয়ে : I love you.\n" +
                ":\n" +
                "ছেলেটি তো অজ্ঞান হবার অবস্থা কিন্তু না, তা আর হল না। ছেলেটি রিপ্লাই দেওয়ার আগেই মেয়েটা আবার ম্যাসেজ দিল,\n" +
                "মেয়ে : eta ektu banglay likhe den na plzzzz.\n" +
                "Ekjon ke SMS korbo."));
        arrayList10.add(new Smsinformation(10,"","34","এক ছেলের সাথে এক মেয়ের রাস্তায় দেখা হল...!\n" +
                "ছেলে:- তোমার নাম কী ??\n" +
                ":\n" +
                "মেয়ে:- মুখে বলব নাকি পায়ে দেখাব ??\n" +
                ":\n" +
                "ছেলে:- মানে....!!\n" +
                ":\n" +
                "মেয়ে:- আমার নাম নুপূর তোমার নাম কী ??\n" +
                ":\n" +
                "ছেলে:- মুখে বলব নাকি করে দেখাব ??\n" +
                ":\n" +
                "মেয়ে:- মানে...!!!\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                "ছেলে:- আমার নাম সোহাগ"));
        arrayList10.add(new Smsinformation(10,"","35","এক লোক টয়লেটে বসে Facebook এ Chat করছিল,\n" +
                "হঠাত্ হাত থেকে মোবাইলটা কমোডে পড়ে গেল। অনেক খোজা খুজি করার পরও মোবাইলটা না পেয়ে, লোকটা টয়লেটে বসে বসে কাঁদছিল.....।\n" +
                "এমন সময় কমোডের ভিতর থেকে এক দৈত্য বের হয়ে লোকটা কে একটা মোবাইল দিয়ে বললো,\n" +
                "কেঁদো না বত্স্য এই নাও তোমার মোবাইল ....\n" +
                "মোবাইল দেখে লোকটা বললো:-\n" +
                "দেখুন আমি গরীব হতে পারি কিন্তু লোভী না, আমার সোনার_মোবাইল চাই না...\n" +
                "আমার পুরাতন মোবাইলটা আমাকে ফেরত দিন...\n" +
                "এই শুনে দৈত্যটা রেগে গিয়ে লোকটাকে একটা চড় দিয়ে বললো....\n" +
                "হারামজাদা এইটাই তোর পুরাতন মোবাইল.....\n" +
                "ভালো করে ধুয়ে দেখ......"));
        arrayList10.add(new Smsinformation(10,"","36","একটি মেয়ে ক্লাসে বসে ফেসবুক চালাচ্ছে\n" +
                "মেয়েটি Status Update করলো\n" +
                "ক্লাসে বসে ফেসবুক চালানোর মজাই আলাদা\n" +
                "সেই Status এ তার স্যার Comment করলো\n" +
                "এখুনি ক্লাস থেকে বেরিয়ে যাও\n" +
                "এবার স্যারের Comment এ মেয়েটির মা Like করলো আর Comment করলো\n" +
                "আজ বাড়িতে এসো তুমি বড়ো অপরাধ করেছো আজ বাড়ির সব কাজ তুমি করবে মায়ের এই Comment এ বাড়ির কাজের মেয়ে Like করলো এবং Comment করলো\n" +
                "আজ তাহলে আমি কাজে আসবো না আজ আমার ছুটি\n" +
                "কাজের মেয়ের এই Comment এ কাজের মেয়ের Boy friend like করলো আর Comment করলো চলো তাহলে Cinema দেখে আসি......."));
        arrayList10.add(new Smsinformation(10,"","37","এক চোর চুরি করতে গিয়ে সেলফি তুলে পোস্ট দিলো, এই মাত্র চুরি করতে ডুকলাম।\n" +
                "বাড়ীর মালিক চোরের পোস্টে লাইক দিয়ে কমেন্টস করল ভেবোনা আমরা ঘুমিয়ে পড়েছি, আমরা সবাই জাগ্রত আছি। পুলিশকে খবর দেওয়া হয়েছে, তারা গাড়ি নিয়ে আসছে। বাড়ীর মালিকের কমেন্টসে লাইক দিয়ে ইন্সপেক্টর আবুল কমেন্টস দিলেন গাড়ি নস্ট হয়ে গেছে ! আমরা হেঁটে হেঁটে আসতেছি !\n"));
        arrayList10.add(new Smsinformation(10,"","38","এক লোক তার বন্ধুদের কাছ থেকে প্রায়ই এটা-ওটা চেয়ে নিত। একদিন সে তার এক বন্ধুকে গর্ব করে বলছিল, আমার গায়ের শার্ট, প্যান্ট, জুতা, মোজা এমনকি টাই-টা পর্যন্ত আমার বন্ধুদের কাছ থেকে পাওয়া–বলতে পার শুধু গায়ের চামড়াটাই আমার। বন্ধুটি তার উপর আগে থেকেই রেগে ছিল। বলল, চামড়াটাও তোমার নয় বন্ধু, ওটা গণ্ডারের।\n"));
        arrayList10.add(new Smsinformation(10,"","39","এক পোলা খিচ্চা গাঁজা খাইছে। বাসায় বাপে যেনো না বুঝতে পারে তাই বাসায় ঢুইকাই তারাতারি ল্যাপটপ খুইলা টিপাটিপি করতে লাগলো।\n" +
                "ওর বাপে আইসা জিগায়, তুই কি গাঁজা খাইছোস?\n" +
                "..\n" +
                "..\n" +
                "..\n" +
                "পোলাঃ কই নাতো!!\n" +
                "বাপঃ হারামজাদা, তাইলে আমার ব্রীফকেস খুইলা ঘাটাঘাটি করতাছোস ক্যান?!"));
        arrayList10.add(new Smsinformation(10,"","40","একটি ব্রেইন গেমস..!! পারলে উত্তর দিন আপনার চাচাতো বোনের বড় ভাইয়ের মায়ের ভাসুরের বৌয়ের মেয়ের একমাত্র ভাইয়ের শশুরের সালার একমাত্র বোনের একমাত্র মেয়ে আপনার কি হবে????\n" +
                "১. খালা\n" +
                "২. তালতো বোন\n" +
                "৩. চাচাতো বোন\n" +
                "৪. বউ\n" +
                "৫. কোনটিই নয় দেখি কোন জিনিয়াস পারেন কিনা !"));
        arrayList10.add(new Smsinformation(10,"","41","একদিন আমাদের শামসু এবং তিনজন আগুনের মত সুন্দরী মেয়ে অফিসে চাকরির জন্য পরীক্ষা দিতে গেল।\n" +
                "অদ্ভুতভাবে তারা চারজন একই নাম্বার পেল পরীক্ষাতে। তো অফিস কর্মকর্তারা পরল মহা ফাপরে। .!চাকরি পাবে যে মাত্র একজন\n" +
                "তারা ঠিক করল একজন একজন করে ইন্টারভিউ নেয়া হবে তাদের। ইন্টারভিউতে একই প্রশ্ন করা হবে তাদের। প্রথমে ডাক পরল প্রথম মেয়েটির। ??’\n" +
                "তাকে জিজ্ঞেস করা হল ‘বুকের ও দুটো কি?\n" +
                "জিজ্ঞেস করার সাথে সাথে বাঘের মতো গর্জন ছাড়ল মেয়েটি। অফিস কর্মকর্তা ও তাদের চোদ্দ গুষ্টিকে দেখে নেবার কথা বলে দ্রুত রুম থেকে বেরিয়ে গেল সে।\n" +
                "দ্বিতীয় মেয়েকে একই প্রশ্ন করা হল।\n" +
                "দ্বিতীয় মেয়েটির মুখ লজ্জাতে লাল হয়ে গেল। কোন কথা না বলে রুম ত্যাগ করল সেও।\n" +
                "তৃতীয় মেয়েকেও একই প্রশ্ন করা হল।\n" +
                "তৃতীয় মেয়েটি আরও অগ্নিমূর্তি ধারণ করল। অফিস কর্মকর্তাদের গালি গালাজ করে চোদ্দ গুষ্টি উদ্ধার করে রুম থেকে বেরিয়ে গেল।\n" +
                "সবার শেষে এল শামসু।\n" +
                "বাকি তিন মেয়ের প্রতিক্রিয়া দেখে কর্মকর্তারা বিব্রত।\n" +
                "তবুও…,??’\n" +
                "জিজ্ঞেস করা হল তাকে\n" +
                "‘বুকের ও দুটো কি শামসু মুচকি হেসে জবাব দিল ‘স্যার\n" +
                ".\n" +
                ".\n" +
                ".\n" +
                "‘BOOK’ এর ‘O’ দুটো ভাওএল\n" +
                "(Vowel)"));
        arrayList10.add(new Smsinformation(10,"","12","কর্মচারী: স্যার, পাঁচ দিনের ছুটি চাই।\n" +
                "বস: কেন? মাত্রই তো তুমি ১০ দিন ছুটি কাটিয়ে ফিরলে।\n" +
                "কর্মচারী: স্যার আমার বিয়ে।\n" +
                "বস: বিয়ে করবে ভালো কথা। তো এত দিন ছুটি কাটালে, তখন বিয়ে করোনি কেন?\n" +
                "কর্মচারী: মাথা খারাপ? বিয়ে করে আমার সুন্দর ছুটির দিনগুলো নষ্ট করব নাকি?"));
        arrayList10.add(new Smsinformation(10,"","13","কর্মচারী: স্যার, একটা দিন ছুটি চাই।\n" +
                "বস: কেন? আবার কী?\n" +
                "কর্মচারী: স্যার, আমার দাদা…\n" +
                "বস: আবার দাদা? গত তিন মাসে তুমি চারবার দাদির মৃত্যুর কথা বলে ছুটি নিয়েছ।\n" +
                "কর্মচারী: স্যার, এবার আমার দাদার বিয়ে!\n"));
        arrayList10.add(new Smsinformation(10,"","44","কর্মচারী: স্যার, আমার একদিনের ছুটি দরকার।\n" +
                "বস: বছরে ৩৬৫ দিনে প্রতি সপ্তাহে দুদিন করে ৫২ সপ্তাহে আপনি সাপ্তাহিক ছুটি পান মোট ১০৪ দিন। বাকি রইল ২৬১ দিন। প্রতিদিন ১৬ ঘণ্টা আপনি অফিসের বাইরে কাটান। সে হিসাবে আপনি অফিসের বাইরে থাকেন মোট ১৭০ দিন। বাকি রইল ৯১ দিন। প্রতিদিন ৩০ মিনিট করে আপনাকে চা পানের বিরতি দেওয়া হয়। হিসাব অনুযায়ী, রইল বাকি ৬৮ দিন। প্রতিদিন এক ঘণ্টা করে আপনাকে দুপুরের খাবারের বিরতি দেওয়া হয়। রইল বাকি ২২ দিন। দুদিন আপনি অসুস্থতার জন্য ছুটি কাটান। রইল ২০ দিন। বছরে ১৯ দিন থাকে সরকারি ছুটি, রইল আর ১ দিন।\n" +
                "সেই একটা দিনও আপনি ছুটি কাটাতে চান?!"));
        arrayList10.add(new Smsinformation(10,"","45","কেরানী ম্যানেজাকে বলল, আমি এত দিন ধরে তিনজন লোকের কাজ এক জনে করেছি । আমার মাইনে বাড়াতে হবে ।\n" +
                "ম্যানেজারঃ মাইনে এখন বাড়ানো অসম্ভব। কিন্তু তুমি বাকি দুজনের নাম বল তাদের এক্ষুনি বরখাস্ত করব।"));
        arrayList10.add(new Smsinformation(10,"","46",": কমিশনার সাহেব বাসায় আছেন ?\n" +
                ": কেন ?\n" +
                ": আমার একটা চারিত্রিক সার্টিফিকেট দরকার |\n" +
                ": তিন মাস পরে আসেন, উনি নারীঘটিত কেসে ছয় মাসের জেলে আছেন |"));
        arrayList10.add(new Smsinformation(10,"","47","ক্যাপ্টেন: সৈনিক, আপনি কি সাঁতার জানেন?\n" +
                "সৈনিক: জানি, স্যার।\n" +
                "ক্যাপ্টেন: কোথায় সাঁতার শিখলেন?\n" +
                "সৈনিক: পানিতে স্যার।\n"));
        arrayList10.add(new Smsinformation(10,"","48","ক্রেতাঃ তাড়াতাড়ি একটা পলিথিন ব্যাগ দিন তো, এক্ষুনি আমাকে ট্রেন ধরতে হবে।\n" +
                "\n" +
                "বল্টুঃ মাফ করবেন, ট্রেন ধরার মতো এত বড় পলিথিন ব্যাগ আমার কাছে নেই।"));
        arrayList10.add(new Smsinformation(10,"","49","কুদ্দুস রাস্তা দিয়ে হেটে যাচ্ছে।\n" +
                "এক পিচ্চি তাকে প্রশ্ন করল,কয়টা বাজে?\n" +
                "-পৌনে তিনটা.\n" +
                "- তিনটা বাজলে আমার পাছায় একটা চুমো দিও। এই কথা বলেই পিচ্চি দিয়েছে দৌড়।\n" +
                "কুদ্দুস রেগে গিয়ে তার পিছে পিছে দৌড়াচ্ছে। পথে ববের সাথে ধাক্কা খেল।\n" +
                "বব- কিরে দোস্ত, দৌড়াস কেন ??\n" +
                "- আরে হালার পিচ্চি কয় তিনটা বাজলে ওর পাছায় চুমু খেতে...\n" +
                "- এই জন্য দৌড়াচ্ছিস!!\n" +
                "এত তাড়াহুড়া কিসের শুনি ??\n" +
                "তিনটা বাজতে এখনো দশ মিনিট বাকি !"));
        arrayList10.add(new Smsinformation(10,"","50","গ্রাম থেকে শহরে এসেছেন নিতাই। ঘুরতে ঘুরতে তিনি গেলেন ছবির দোকানে। দোকানে সারবেঁধে ঝোলানো আছে বাঁধাই করা হরেক রকম ছবি। গ্রামের দৃশ্যের ছবি, বাঘের ছবি, মোনালিসার ছবি…ইত্যাদি। ঘুরতে ঘুরতে একটা ছবির সামনে গিয়ে দাঁড়ালেন নিতাই। দোকানদারকে ডেকে বললেন, এই বিচ্ছিরি ছবিটার দাম ৫০০ ট্যাকা? এইডা তো আমি ফিরি দিলেও নিমু না!\n" +
                "দোকানদার বললেন, ছবি কই? ওটা তো আয়না!"));


        animFadein.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (animation == animFadein) {
//                    Toast.makeText(getApplicationContext(), "Animation Stopped",
//                            Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        parent.setOnTouchListener(new OnSwipeTouchListener(DetailsActivity.this) {

            @Override
            public void onClick() {
                super.onClick();
                // your on click here
            }

            @Override
            public void onDoubleClick() {
                super.onDoubleClick();
                // your on onDoubleClick here
            }

            @Override
            public void onLongClick() {
                super.onLongClick();
                // your on onLongClick here
            }

            @Override
            public void onSwipeUp() {
                super.onSwipeUp();
                // your swipe up here
            }

            @Override
            public void onSwipeDown() {
                super.onSwipeDown();
                // your swipe down here.
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                // your swipe left here.


                // start the animation
                parent.startAnimation(animFadein2);
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                // your swipe right here.
                parent.setVisibility(View.VISIBLE);

                // start the animation
                parent.startAnimation(animFadein);


            }
        });


        animFadein2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                try {
                    positon++;
                    serial.setText(arrayList.get(positon).getSerial());
                    serial1.setText(arrayList.get(positon).getSerial());
                    headline.setText(arrayList.get(positon).getTitle());
                    details.setText(arrayList.get(positon).getDetails());
                    parent.setVisibility(View.VISIBLE);


                    adsCount++;

                    if (adsCount==10)
                    {
                        IntersitalAds();
                        adsCount=0;
                    }


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    positon=0;
                    Log.i("123","error"+e.getMessage());
//                    serial.setText(arrayList.get(positon).getSerial());
//                    serial1.setText(arrayList.get(positon).getSerial());
//                    headline.setText(arrayList.get(positon).getTitle());
//                    details.setText(arrayList.get(positon).getDetails());
//                    parent.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (animation == animFadein) {
//                    Toast.makeText(getApplicationContext(), "Animation Stopped",
//                            Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animFadein.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                try {
                    positon--;
                    serial.setText(arrayList.get(positon).getSerial());
                    serial1.setText(arrayList.get(positon).getSerial());
                    headline.setText(arrayList.get(positon).getTitle());
                    details.setText(arrayList.get(positon).getDetails());
                    parent.setVisibility(View.VISIBLE);


                } catch (Exception e) {
                    e.printStackTrace();
                    positon=arrayList.size()-1;
                    serial.setText(arrayList.get(positon).getSerial());
                    serial1.setText(arrayList.get(positon).getSerial());
                    headline.setText(arrayList.get(positon).getTitle());
                    details.setText(arrayList.get(positon).getDetails());
                    parent.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (animation == animFadein) {
//                    Toast.makeText(getApplicationContext(), "Animation Stopped",
//                            Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void ads_setup()
    {
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        /**
         * Intersital
         */
        IntersitalAds();

    }


    public void IntersitalAds()
    {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        });
    }

}

