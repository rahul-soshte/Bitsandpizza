package com.example.hunter.bitsandpizza;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.ShareActionProvider;



public class MainActivity extends Activity {
private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
