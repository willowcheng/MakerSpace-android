package me.willowcheng.makerspaceiot;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;


public class MainActivity extends MaterialNavigationDrawer {

    private MaterialAccount account;

    @Override
    public void init(Bundle savedInstanceState) {

        // add accounts
        account = new MaterialAccount(this.getResources(), "", "", null, R.mipmap.drawer_background);
        this.addAccount(account);


        // create sections
        this.addSection(newSection(getResources().getString(R.string.home_fragment), R.mipmap.ic_android_studio_grey600_48dp, new HomeFragment()).setSectionColor(getResources().getColor(R.color.deep_carmine_pink)));
        this.addSection(newSection(getResources().getString(R.string.account_fragment), R.mipmap.ic_account_grey600_48dp, new AccountFragment()).setSectionColor(getResources().getColor(R.color.sushi)));

        disableLearningPattern();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
