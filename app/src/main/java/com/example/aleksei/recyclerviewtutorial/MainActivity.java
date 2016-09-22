package com.example.aleksei.recyclerviewtutorial;

// По материалам
// http://stackoverflow.com/questions/29134094/recyclerview-horizontal-scroll-snap-in-center
// http://stackoverflow.com/questions/29134094/recyclerview-horizontal-scroll-snap-in-center/29171652#29171652

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    protected PageInfo[] pages;
    private HorizontalAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        pages = new PageInfo[]{ getFirstPage(), getSecondPage(), getThirdPage()};
        mAdapter = new HorizontalAdapter(pages);

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(horizontalLayoutManagaer);

        mRecyclerView.setAdapter(mAdapter);
    }



    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

        PageInfo[] pages;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public ImageView image;
            public TextView title;
            public TextView message;
            public TextView checkboxTitle;
            public CheckBox checkbox;
            public ScrollView scrollView;

            public MyViewHolder(View view) {
                super(view);
                image = (ImageView) view.findViewById(R.id.image);
                title = (TextView) view.findViewById(R.id.textTitle);
                message = (TextView) view.findViewById(R.id.textMessage);
                checkboxTitle = (TextView) view.findViewById(R.id.checkboxTitle);
                checkbox = (CheckBox) view.findViewById(R.id.checkbox);
                scrollView = (ScrollView) view.findViewById(R.id.scrollView);
            }
        }

        public HorizontalAdapter(PageInfo[] pages) {
            this.pages = pages;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_view, parent, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            PageInfo pageInfo = pages[position];
            if (holder.image != null) {
                holder.image.setImageResource(pageInfo.image);
            }
            holder.title.setText(pageInfo.title);
            holder.message.setText(pageInfo.message);
            holder.scrollView.post(new Runnable() {
                @Override
                public void run() {
                    holder.scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
        }

        @Override
        public int getItemCount() {
            return pages.length;
        }
    }

    protected PageInfo getFirstPage() {
        return new PageInfo(R.drawable.image_tutorial3,
                R.string.tutorial_page1_title,
                R.string.tutorial_page1_message);
    }

    protected PageInfo getSecondPage() {
        return new PageInfo(R.drawable.image_tutorial1,
                R.string.tutorial_page2_title,
                R.string.tutorial_page2_message);
    }

    protected PageInfo getThirdPage() {
        return new PageInfo(R.drawable.image_tutorial2,
                R.string.tutorial_page3_title,
                R.string.tutorial_page3_message);
    }
}
