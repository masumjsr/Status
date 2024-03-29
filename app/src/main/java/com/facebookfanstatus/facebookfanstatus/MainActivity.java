package com.facebookfanstatus.facebookfanstatus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crashlytics.android.Crashlytics;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.onesignal.OneSignal;


import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawer;
    private NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    private AppBarLayout appBarLayout;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private ViewFlipper viewFlipper;


    private RecyclerView recyclerViewHome;

    private AlertDialog alertDialog;




    private String arrayList [] =  {"ফেসবুক ফানি স্ট্যাটাস পর্ব-1","ফেসবুক ফানি স্ট্যাটাস পর্ব-2","ফেসবুক ফানি স্ট্যাটাস পর্ব-3","ফেসবুক ফানি স্ট্যাটাস পর্ব-4","ফেসবুক ফানি স্ট্যাটাস পর্ব-5",
            "ফেসবুক ফানি স্ট্যাটাস পর্ব-6","ফেসবুক ফানি স্ট্যাটাস পর্ব-7","ফেসবুক ফানি স্ট্যাটাস পর্ব-8","ফেসবুক ফানি স্ট্যাটাস পর্ব-9","ফেসবুক ফানি স্ট্যাটাস পর্ব-10"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);



        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();



/*
      DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("txt");

      databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot)
          {
              String text = dataSnapshot.getValue().toString();
              Toast.makeText(MainActivity.this, ""+text, Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError)
          {

          }
      });

*/


        recyclerViewHome = findViewById(R.id.recyclerHomeId);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(this));




        appBarLayout = findViewById(R.id.appbar);
        mToolbar = (Toolbar) findViewById(R.id.toolBar_home_page);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



        mCollapsingToolbar =findViewById(R.id.collpsing_home_page_id);

        viewFlipper=findViewById(R.id.view_fliper_allplayer_id);
        appBarLayout=findViewById(R.id.appbar);


        mDrawer =findViewById(R.id.drawerid);
        navigationView=findViewById(R.id.navigationid);
        toggle=new ActionBarDrawerToggle(this,mDrawer,R.string.open,R.string.close);

        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        mToolbar.setNavigationIcon(R.drawable.dower_icon);


        flipperimage();


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mCollapsingToolbar.setTitle("ফেসবুক ফানি স্ট্যাটাস");
                    mCollapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
                    isShow = true;
                } else if(isShow) {
                    mCollapsingToolbar.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });


        findViewById(R.id.imageLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network

                    String url = "https://play.google.com/store/apps/details?id=com.banglasms.allsms";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_LONG).show();
                }
                else

                {
                    Toast.makeText(getApplicationContext(), "Please Check Internet Connected", Toast.LENGTH_SHORT).show();
                    // connected = false;
                }
            }
        });


        findViewById(R.id.spokenLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network

                    String url = "https://play.google.com/store/apps/details?id=com.spoken_english_45_days.spoken_english_45_days";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_LONG).show();
                }
                else

                {
                    Toast.makeText(getApplicationContext(), "Please Check Internet Connected", Toast.LENGTH_SHORT).show();
                    // connected = false;
                }
            }
        });


        findViewById(R.id.dhadhaLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network

                    String url = "https://play.google.com/store/apps/details?id=com.fsit.bangladhadha";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_LONG).show();
                }
                else

                {
                    Toast.makeText(getApplicationContext(), "Please Check Internet Connected", Toast.LENGTH_SHORT).show();
                    // connected = false;
                }
            }
        });


        findViewById(R.id.uktiLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network

                    String url = "https://play.google.com/store/apps/details?id=com.fsit.ukti_bangla";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_LONG).show();
                }
                else

                {
                    Toast.makeText(getApplicationContext(), "Please Check Internet Connected", Toast.LENGTH_SHORT).show();
                    // connected = false;
                }
            }
        });


        findViewById(R.id.joks99).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network

                    String url = "https://play.google.com/store/apps/details?id=com.fsit.banglaJokes";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_LONG).show();
                }
                else

                {
                    Toast.makeText(getApplicationContext(), "Please Check Internet Connected", Toast.LENGTH_SHORT).show();
                    // connected = false;
                }
            }
        });
        findViewById(R.id.ssc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network

                    String url = "https://play.google.com/store/apps/details?id=com.funny.photo_app";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_LONG).show();
                }
                else

                {
                    Toast.makeText(getApplicationContext(), "Please Check Internet Connected", Toast.LENGTH_SHORT).show();
                    // connected = false;
                }
            }
        });


    }

    private void shareAppLink() {
        final String appPackageName = getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Download App");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello i am using ফেসবুক মজার স্ট্যাটাস app download it from play store."
                + " Check out the App at: https://play.google.com/store/apps/details?id=" + appPackageName);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }


    public void moreapps()
    {
        String url = "https://play.google.com/store/apps/developer?id=Friends+Software+IT";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
        Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_LONG).show();
    }
    public void update()
    {
        String url = "https://play.google.com/store/apps/details?id=com.facebookfanstatus.facebookfanstatus";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
        Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_LONG).show();
    }
    public void fivestar()
    {
        String url = "https://play.google.com/store/apps/details?id=com.facebookfanstatus.facebookfanstatus";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
        Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_LONG).show();
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();

        switch (id){

            case R.id.navaboutid:

                break;
            case R.id.navpolicyid:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://friendsoftwareit.blogspot.com/2020/02/funny-status.html")));
                break;
            case R.id.navshareid:
                shareAppLink();
                break;
            case R.id.navrateapp:

                fivestar();

                break;
            case R.id.navupdate:

                update();

                break;
            case R.id.navmoreapps:

                moreapps();

                break;


        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        int id = item.getItemId();

        if (id == R.id.aboutid) {
            return true;
        }

        if (id == R.id.privacypolicyid) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://friendsoftwareit.blogspot.com/2020/02/funny-status.html")));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public  void flipperimage(){
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.alert_dailog_design, viewGroup, false);

        dialogView.findViewById(R.id.plyStoreGo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network

                    fivestar();
                    Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_LONG).show();
                }
                else

                {
                    Toast.makeText(getApplicationContext(), "Please Check Internet Connected", Toast.LENGTH_SHORT).show();
                    // connected = false;
                }
            }
        });
        dialogView.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                alertDialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
                alertDialog.dismiss();
            }
        });

        builder.setView(dialogView);
        alertDialog = builder.create();
        alertDialog.show();


    }
}

