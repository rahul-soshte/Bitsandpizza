package com.example.hunter.bitsandpizza;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;




public class MainActivity extends Activity {
    private ShareActionProvider shareActionProvider;
    private String[] titles;
    private ListView drawerList;
private DrawerLayout drawerLayout;
private int currentPosition=0;

    private ActionBarDrawerToggle drawerToggle;

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            //Code to run when the item gets clicked
            selectItem(position);
            //Set the action bar title



        }
    };
    private void setActionBarTitle(int position){
        String title;
        if(position==0)
        {
            title=getResources().getString(R.string.app_name);


        }else
        {
            title=titles[position];

        }
        getActionBar().setTitle(title);

    }
    private void selectItem(int position)
    {
        currentPosition=position;

        Fragment fragment;
        switch (position)
        {
            case 1:fragment = new PizzaMaterialFragment();
                break;
            case 2:fragment=new PastaFragment();
                break;
            case 3:fragment=new StoresFragment();
                break;
            case 4:fragment=new TopFragment();
                break;
            default:fragment=new TopFragment();
                break;

        }
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame,fragment,"visible_fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        //Set the action bar title
         setActionBarTitle(position);
     //Close the Drawer
        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(drawerList);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titles=getResources().getStringArray(R.array.titles);
        drawerList=(ListView)findViewById(R.id.drawer);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);

        drawerList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,titles));

        drawerList.setOnItemClickListener(new DrawerItemClickListener());
//Display the correct fragment
        if(savedInstanceState!=null)
        {
            currentPosition = savedInstanceState.getInt("position");
            setActionBarTitle(currentPosition);
        }else{
            selectItem(0);
        }
        //Create the Action Bar Toggle
        drawerToggle =new ActionBarDrawerToggle(this,drawerLayout,R.string.open_drawer,R.string.close_drawer) { //Called when a drawer has settled in a completely closed date
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener()
        {
            public void onBackStackChanged()
            {
                FragmentManager fragMan=getFragmentManager();
                Fragment fragment=fragMan.findFragmentByTag("visible_fragment");
                if(fragment instanceof TopFragment)
                {
                    currentPosition=0;

                }
                if(fragment instanceof PizzaMaterialFragment)
                {
                    currentPosition=1;
                }
                if(fragment instanceof PastaFragment)
                {
                    currentPosition=2;
                }
                if(fragment instanceof StoresFragment)
                {
                    currentPosition=3;
                }
                setActionBarTitle(currentPosition);
                drawerList.setItemChecked(currentPosition,true);

            }
        });

    }
@Override
protected void onPostCreate(Bundle savedInstanceState)
{
    super.onPostCreate(savedInstanceState);
    drawerToggle.syncState();
}
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);

    }

    //Called whenever we call InvalidatOptionsMenu()
@Override
public boolean onPrepareOptionsMenu(Menu menu)
{
    //If the drawer is open,hide actions items related to action view
    boolean drawerOpen=drawerLayout.isDrawerOpen(drawerList);
    menu.findItem(R.id.action_share).setVisible(!drawerOpen);
    return super.onPrepareOptionsMenu(menu);

}
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Inflate the menu;this add items to the action bar if its present
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.action_share);
        shareActionProvider=(ShareActionProvider)menuItem.getActionProvider();
setIntent("this is example text");
        return super.onCreateOptionsMenu(menu);
    }
    private void setIntent(String text)
    {

        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,text);
        shareActionProvider.setShareIntent(intent);


    }
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("position",currentPosition);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        switch(item.getItemId())
        {
            case R.id.action_create_order:
               //Code to run when the Create Order item is clicked
                Intent intent =new Intent(this,OrderActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                //Code to run when the settings item is clicked
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
