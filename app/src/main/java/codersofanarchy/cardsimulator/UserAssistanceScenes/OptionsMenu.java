package codersofanarchy.cardsimulator.UserAssistanceScenes;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import codersofanarchy.cardsimulator.R;

/**
 * Created by kylecrews on 7/27/16.
 */
public class OptionsMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayer_table);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

    }

}
