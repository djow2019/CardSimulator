package codersofanarchy.cardsimulator.GameTables;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import codersofanarchy.cardsimulator.R;

/**
 * Created by kylecrews on 7/27/16.
 */
public class SinglePlayerTable extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_player_table);

        ActionBar actionBar = getActionBar();
        actionBar.hide();
    }
}
