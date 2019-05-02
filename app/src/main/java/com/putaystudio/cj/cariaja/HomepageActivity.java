package com.putaystudio.cj.cariaja;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.putaystudio.cj.cariaja.SessionManager.SessionManager;

import org.w3c.dom.Text;

public class HomepageActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar homeToolbar;
    SessionManager sessionManager;
    private TextView mTextMessage;

    private boolean loadFragment(android.support.v4.app.Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.navigation_dashboard:
                    fragment = new HistoryFragment();
                    break;
            }

            return  loadFragment(fragment);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_profile)
        {
            startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
        }else if(id == R.id.action_pengaturan)
        {
            Toast.makeText(this, "Menu File", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_logout)
        {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            sessionManager.setImageKey("");
            sessionManager.setKeyId("");
            sessionManager.setKeyEmail("");
            sessionManager.setKeyNama("");
            sessionManager.setIsLogin(false);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        homeToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.HomeToolbar);
        setSupportActionBar(homeToolbar);
        sessionManager = new SessionManager(getApplicationContext());

        loadFragment(new HomeFragment());

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
