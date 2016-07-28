package codersofanarchy.platform;

import android.app.AlertDialog;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.ParcelUuid;
import android.util.Log;

/**
 * Created by Derek on 7/27/2016.
 */
public class CardPlatform extends Application {

    public static final String TAG = "CS";
    public static final ParcelUuid SERVICE_UUID = ParcelUuid.fromString("1234");
    public static final ParcelUuid CHARACTERISTIC_UUID = ParcelUuid.fromString("9999");

    public static boolean isAdvertising, hasBLESupport;

    private BluetoothAdapter adapter;
    private BluetoothManager manager;

    private static CardPlatform platform;

    public static CardPlatform getInstance() {
        return platform;
    }

    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "App Started");

        manager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        adapter = manager.getAdapter();

        if (adapter == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setTitle("No BLE Support");
            builder.setPositiveButton("Okay", null);
            builder.setMessage("This device does not have bluetooth support. Application will close.");
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            if (!adapter.isEnabled()) {
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(enableIntent);
            }
            hasBLESupport = true;
        }

        platform = this;

    }

    public BluetoothAdapter getAdapter() {
        return adapter;
    }

    public BluetoothManager getManager() {
        return manager;
    }

}
