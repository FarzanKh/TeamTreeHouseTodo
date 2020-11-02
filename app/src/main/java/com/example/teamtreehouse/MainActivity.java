package com.example.teamtreehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListFragment.OnListSelectedInterface {
    public static final String LIST_FRAGMENT = "list_fragment";
    public static final String VIEWPAGE_FRAGMENT = "viewpager_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragment savedFragment = (ListFragment) getFragmentManager().findFragmentByTag(LIST_FRAGMENT);
        if (savedFragment == null) {
            ListFragment fragment = new ListFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.placeHolder, fragment, LIST_FRAGMENT);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onListSelected(int index) {
        View fragmentContainer = findViewById(R.id.viewpager_fragment_container);
        if (fragmentContainer != null) {
            // Add viewpager fragment to the FrameLayout

            ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
            viewPagerFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.viewpager_fragment_container, viewPagerFragment, VIEWPAGE_FRAGMENT );
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else {
            ViewPagerFragment fragment = new ViewPagerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.placeHolder, fragment, VIEWPAGE_FRAGMENT);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }

}