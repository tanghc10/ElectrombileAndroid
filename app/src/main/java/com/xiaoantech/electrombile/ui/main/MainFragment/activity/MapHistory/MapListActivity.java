package com.xiaoantech.electrombile.ui.main.MainFragment.activity.MapHistory;

import android.app.ListActivity;
import android.os.Bundle;

import com.xiaoantech.electrombile.R;
import com.xiaoantech.electrombile.widget.HistoryRouteCell;

/**
 * Created by yangxu on 2016/11/25.
 */

public class MapListActivity extends ListActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maplist);
//        setListAdapter();
    }

    static class Item{
        public static final int ITEM = 0;
        public static final int SECTION = 1;

        public final int type;
        public HistoryRouteCell cell;
        public int sectionPosition;
        public int listPosition;
        public Item(int type,HistoryRouteCell listViewCell){
            this.type = type;
            this.cell = listViewCell;
        }
    }

}
