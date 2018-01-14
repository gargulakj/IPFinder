package cz.utb.fai.ipf;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by J on 13.01.2018.
 */

public class PageHistory {
    private ListView mListView;
    private Activity mActivity;
    private ArrayList<String> mHistory;
    ArrayAdapter<String> mAdapter;

    public PageHistory(Activity activity, ArrayList<String> History )
    {
        mActivity = activity;
        mHistory = History;
        mListView = (ListView)activity.findViewById(R.id.list_history);

        mAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_1, mHistory );
        mListView.setAdapter( mAdapter );
    }

    public void notifyDataChanged()
    {
        mAdapter.notifyDataSetChanged();
    }
}
