package me.willowcheng.makerspace;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.telly.mrvector.MrVector;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;


public class MainActivity extends MaterialNavigationDrawer {

    private MaterialAccount account;

    @Override
    public void init(Bundle savedInstanceState) {

        // add accounts
        account = new MaterialAccount(this.getResources(), "", "", null, me.willowcheng.makerspace.R.drawable.drawer_background);
        this.addAccount(account);


        // create sections
        this.addSection(newSection(getResources().getString(me.willowcheng.makerspace.R.string.project_fragment), MrVector.inflate(getResources(), me.willowcheng.makerspace.R.drawable.ic_project), new ProjectFragment()).setSectionColor(getResources().getColor(me.willowcheng.makerspace.R.color.deep_carmine_pink)));
        this.addSection(newSection(getResources().getString(me.willowcheng.makerspace.R.string.account_fragment), MrVector.inflate(getResources(), me.willowcheng.makerspace.R.drawable.ic_account), new AccountFragment()).setSectionColor(getResources().getColor(me.willowcheng.makerspace.R.color.deep_carmine_pink)));

        disableLearningPattern();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(me.willowcheng.makerspace.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == me.willowcheng.makerspace.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
