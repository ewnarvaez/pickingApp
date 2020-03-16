package com.willer.pickingapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.willer.pickingapp.ui.Order.OrderFragment;
import com.willer.pickingapp.ui.searchClient.SearchFragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class BottomNavigationActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private OrderFragment orderFragment;
    private SearchFragment searchFragment;
//    private UpdateFragment updateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        orderFragment = new OrderFragment();
        searchFragment = new SearchFragment();
//        updateFragment = new UpdateFragment();

        bottomNavigationView = findViewById(R.id.nav_view);
        frameLayout = findViewById(R.id.frameLayout);
        setFragment(searchFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {

                case R.id.navigation_search:
                    setFragment(searchFragment);
                    menuItem.setChecked(true);
                    getSupportActionBar().show();
                    break;

                case R.id.navigation_order:
                    setFragment(orderFragment);
                    menuItem.setChecked(true);
                    getSupportActionBar().hide();
                    break;

//                    case R.id.navigation_update:
//                        setFragment(updateFragment);
//                        menuItem.setChecked(true);
//                        break;

            }
            return false;
        });
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}
