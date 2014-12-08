package com.codepath.apps.twitter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.codepath.apps.twitter.fragments.HomeTimelineFragment;
import com.codepath.apps.twitter.fragments.MentionsTimelineFragment;
import com.codepath.apps.twitter.fragments.TimelineFragment;
import com.codepath.apps.twitter.listeners.FragmentTabListener;

public class TimelineActivity extends FragmentActivity {

    private ActionBar actionBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        
        setupTabs();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.action_compose) {
            Intent i = new Intent(this, ComposeActivity.class);
            startActivityForResult(i, ComposeActivity.RESULT_COMPOSE_OK);
            return true;
        } else if (menuId == R.id.action_profile)  {
            Intent i = new Intent(this, ProfileActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("DEBUG", String.format("onActivityResult %d", resultCode));
        switch (resultCode) {
            case ComposeActivity.RESULT_COMPOSE_OK:
                // Fetch new post into stream
                getCurrentFragment().populateTimeline(-1);
                break;
            case ComposeActivity.RESULT_COMPOSE_CANCEL:
                // Do nothing
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
    
    private void setupTabs() {
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);
        
        Tab tab1 = actionBar
            .newTab()
            .setText(R.string.title_fragment_home)
            .setIcon(R.drawable.ic_action_home)
            .setTag("HomeTimelineFragment")
            .setTabListener(
                new FragmentTabListener<HomeTimelineFragment>(R.id.flTimeline, this, "HomeTimelineFragment", HomeTimelineFragment.class));
        actionBar.addTab(tab1);
        actionBar.selectTab(tab1);
        Tab tab2 = actionBar
            .newTab()
            .setText(R.string.title_fragment_mentions)
            .setIcon(R.drawable.ic_action_mention)
            .setTag("MentionTimelineFragment")
            .setTabListener(
                new FragmentTabListener<MentionsTimelineFragment>(R.id.flTimeline, this, "MentionTimelineFragment", MentionsTimelineFragment.class));
        actionBar.addTab(tab2);
    }
    
    private TimelineFragment getCurrentFragment() {
        FragmentManager fm = getSupportFragmentManager();
        TimelineFragment fragment = (TimelineFragment) fm.findFragmentByTag(actionBar.getSelectedTab().getTag().toString());
        return fragment;
    }
    
    public void onClickProfileImage(View v) {
        Intent i = new Intent(this, ProfileActivity.class);
        i.putExtra(ProfileActivity.KEY_USER_ID, v.getTag().toString());
        startActivity(i);
    }
}
