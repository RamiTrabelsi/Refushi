package com.refushi;


import java.util.ArrayList;

import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.refushi.adapter.MenuGridAdapter;
import com.refushi.external.RefushiFonts;
import com.refushi.external.RefushiManager;
import com.refushi.fragments.FragmentAbout;
import com.refushi.fragments.FragmentCategories;
import com.refushi.fragments.FragmentFavorites;
import com.refushi.fragments.FragmentHome;
import com.refushi.fragments.FragmentMaps;
import com.refushi.fragments.FragmentProfile;
import com.refushi.fragments.FragmentServices;
import com.refushi.fragments.FragmentSettings;
import com.refushi.model.MenuGridItem;

public class MainActivity extends AppCompatActivity {


	private DrawerLayout mDrawerLayout;
	private FrameLayout content_frame ;
	private GridView mDrawerGrid;
	private Toolbar toolbar ;
	private RefushiManager mManager ;
	private ActionBarDrawerToggle mDrawerToggle;

	private ArrayList<MenuGridItem> navDrawerItems;
	private MenuGridAdapter adapter;
	private TextView toolbar_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mManager = RefushiManager.getInstance(this);

		mDrawerLayout 	= (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerGrid 	= (GridView) findViewById(R.id.gridView);
		content_frame 	= (FrameLayout) findViewById(R.id.content_frame);
		toolbar_title 	= (TextView) findViewById(R.id.toolbar_title);

		//		mDrawerLayout.setScrimColor(Color.TRANSPARENT);

		toolbar_title.setTypeface(RefushiFonts.getPetitaMedium());

		setActionBar();

		navDrawerItems = new ArrayList<MenuGridItem>();

		navDrawerItems.add(new MenuGridItem("Home",R.drawable.menu_home));
		navDrawerItems.add(new MenuGridItem("Services",R.drawable.menu_services));
		navDrawerItems.add(new MenuGridItem("Categories",R.drawable.menu_categories));
		navDrawerItems.add(new MenuGridItem("Maps",R.drawable.menu_map));
		navDrawerItems.add(new MenuGridItem("Profile",R.drawable.menu_profile));
		navDrawerItems.add(new MenuGridItem("Favorites",R.drawable.menu_favorites));
		navDrawerItems.add(new MenuGridItem("Settings",R.drawable.menu_settings));
		navDrawerItems.add(new MenuGridItem("About",R.drawable.menu_about));


		adapter = new MenuGridAdapter(MainActivity.this, navDrawerItems);
		mDrawerGrid.setAdapter(adapter);

		switchFragment(new FragmentHome(), false);

		mDrawerGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				switch (position) {
				case 0:
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					switchFragment(new FragmentHome(), true);
					break;
				case 1:
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					switchFragment(new FragmentServices(), true);
					break;
				case 2:
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					switchFragment(new FragmentCategories(), true);
					break;
				case 3:
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					switchFragment(new FragmentMaps(), true);
					break;
				case 4:
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					switchFragment(new FragmentProfile(), true);
					break;
				case 5:
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					switchFragment(new FragmentFavorites(), true);
					break;
				case 6:
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					switchFragment(new FragmentSettings(), true);
					break;
				case 7:
					mDrawerLayout.closeDrawer(Gravity.LEFT);
					switchFragment(new FragmentAbout(), true);
					break;

				}


			}
		});


	}
	private void setActionBar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.bringToFront();

		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("");
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);


		mDrawerToggle = new ActionBarDrawerToggle(
				this,                  /* host Activity */
				mDrawerLayout,         /* DrawerLayout object */
				toolbar,  /* nav drawer icon to replace 'Up' caret */
				R.string.drawer_open,  /* "open drawer" description */
				R.string.drawer_close  /* "close drawer" description */
				) {


			//			@Override
			//			public boolean onOptionsItemSelected(MenuItem item) {
			//				if (item != null && item.getItemId() == android.R.id.home) {
			//					if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
			//						drawerLayout.closeDrawer(Gravity.LEFT);
			//					} else {
			//						drawerLayout.openDrawer(Gravity.RIGHT);
			//					}
			//				}
			//				return false;
			//			}


			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				//				linearLayout.removeAllViews();
				//				linearLayout.invalidate();
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				super.onDrawerSlide(drawerView, slideOffset);
				//				if (slideOffset > 0.6 && linearLayout.getChildCount() == 0){
				//					
				//				}
				//					viewAnimator.showMenuContent();
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}


		};


		mDrawerToggle.setDrawerIndicatorEnabled(true);
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}


	public void switchFragment(Fragment tab, boolean withAnimation) {

		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.content_frame);

		FragmentTransaction ft = fm.beginTransaction();
		if(withAnimation)
			ft.setCustomAnimations(R.anim.down_in, R.anim.down_out, R.anim.up_in, R.anim.up_out);

		if (fragment == null) {
			ft.add(R.id.content_frame, tab);

		} else {
			ft.replace(R.id.content_frame, tab);
		}
		ft.addToBackStack(null);
		ft.commit();

	}
	@Override
	protected void onResume() {
		super.onResume();
		this.registerReceiver(mManager.myConnectivityStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(mManager.myConnectivityStateReceiver);
	}


}
