package com.vku.nmcuong_lvnhuy_19it2.myappchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {
   FirebaseAuth firebaseAuth;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");
        firebaseAuth = FirebaseAuth.getInstance();
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);
        //default
        actionBar.setTitle("Home");
        HomeFragment fragment1 = new HomeFragment();
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.content,fragment1,"");
        ft1.commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                  switch (item.getItemId()) {
                      case R.id.nav_home:
                          actionBar.setTitle("Home");
                          HomeFragment fragment1 = new HomeFragment();
                          FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                          ft1.replace(R.id.content,fragment1,"");
                          ft1.commit();
                          return true;
                      case R.id.nav_profile:
                          actionBar.setTitle("Profile");
                          ProfileFragment fragment2 = new ProfileFragment();
                          FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                          ft2.replace(R.id.content,fragment2,"");
                          ft2.commit();
                          return true;
                      case R.id.nav_user:
                          actionBar.setTitle("Users");
                          UserFragment fragment3 = new UserFragment();
                          FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                          ft3.replace(R.id.content,fragment3,"");
                          ft3.commit();
                          return true;
                      case R.id.nav_chat:
                          actionBar.setTitle("Chats");
                        ChatListFragment fragment4 = new ChatListFragment();
                          FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                          ft4.replace(R.id.content,fragment4,"");
                          ft4.commit();
                          return true;
                  }
                    return false;
                }
            };

    private void checkUserStatus() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null)
        {

        }
        else {
            startActivity(new Intent(DashboardActivity.this,MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        checkUserStatus();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout){
            firebaseAuth.signOut();
            checkUserStatus();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();
    }
}