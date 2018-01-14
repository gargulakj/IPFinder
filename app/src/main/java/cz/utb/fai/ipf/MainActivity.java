package cz.utb.fai.ipf;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import cz.utb.fai.ipf.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private PageHome mPageHome;
    private PageHistory mPageHistory;
    private ArrayList<String> mHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init()
    {
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mViewPager.setAdapter(new ViewPagerAdapter(this));
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(2);

        mTabLayout = (TabLayout)findViewById(R.id.tableLayout);
        // adds view pager into tab layout
        mTabLayout.setupWithViewPager(mViewPager);

        loadHistory();
        if( mHistory == null ) {
            mHistory = new ArrayList<String>(20);
        }
        mPageHome = new PageHome(this );
        mPageHistory = new PageHistory( this, mHistory );

    }

    private void saveHistory() {

        try {
            ObjectOutputStream outputStream= new ObjectOutputStream(openFileOutput( "history", Context.MODE_PRIVATE ));
            outputStream.writeObject( mHistory );
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadHistory()
    {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(openFileInput("history"));
            mHistory = (ArrayList<String>) inputStream.readObject();
            inputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addToHistory( String histText )
    {
        mHistory.add(0,histText);
        if( mHistory.size() > 50 )
        {
            mHistory.remove( mHistory.size() - 1 );
        }
        mPageHistory.notifyDataChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveHistory();
    }
}
