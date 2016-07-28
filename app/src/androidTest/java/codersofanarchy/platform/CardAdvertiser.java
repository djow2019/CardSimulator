package codersofanarchy.platform;

import android.app.AlertDialog;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Derek on 7/27/2016.
 */
public class CardAdvertiser extends Service {

    private AdvertiseSettings settings;
    private AdvertiseData data;
    private BluetoothLeAdvertiser advertiser;
    private BluetoothAdapter adapter;

    public void onCreate() {
        super.onCreate();

        adapter = CardPlatform.getInstance().getAdapter();

        if(!adapter.isMultipleAdvertisementSupported()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Advertising Error");
            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    stopSelf();
                }
            });
            builder.setMessage("Advertising Not Supported. Closing.");
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        advertiser = adapter.getBluetoothLeAdvertiser();

        AdvertiseSettings.Builder builder = new AdvertiseSettings.Builder();
        builder.setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_BALANCED);
        builder.setConnectable(true);
        builder.setTimeout(0);
        builder.setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH);
        settings = builder.build();

        AdvertiseData.Builder builder2 = new AdvertiseData.Builder();
        builder2.setIncludeDeviceName(true);
        builder2.addServiceUuid(CardPlatform.UUID);
        data = builder2.build();

    }
    public void startAdvertising() {
        advertiser.startAdvertising(settings, data, advertiserCallback);
    }

    public void stopAdvertising() {
        advertiser.stopAdvertising(advertiserCallback);
    }

    private AdvertiseCallback advertiserCallback = new AdvertiseCallback() {
        @Override
        public void onStartSuccess(AdvertiseSettings settingsInEffect) {
            super.onStartSuccess(settingsInEffect);
            Log.d(CardPlatform.TAG, "Advertising Success");
            CardPlatform.isAdvertising = true;
        }

        @Override
        public void onStartFailure(int errorCode) {
            super.onStartFailure(errorCode);
            Log.d(CardPlatform.TAG, "Advertising Failed");
            switch (errorCode) {
                case AdvertiseCallback.ADVERTISE_FAILED_ALREADY_STARTED: {
                    Log.d(CardPlatform.TAG, "Advertise Failed: Already started");
                    break;
                }
                case AdvertiseCallback.ADVERTISE_FAILED_DATA_TOO_LARGE: {
                    Log.d(CardPlatform.TAG, "Advertise Failed: Data too large");
                    break;
                }
                case AdvertiseCallback.ADVERTISE_FAILED_INTERNAL_ERROR: {
                    Log.d(CardPlatform.TAG, "Advertise Failed: Internal Error");
                    break;
                }
                case AdvertiseCallback.ADVERTISE_FAILED_TOO_MANY_ADVERTISERS: {
                    Log.d(CardPlatform.TAG, "Advertise Failed: Too many Advertisers");
                    break;
                }
            }
        }
    };

    private IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        public CardAdvertiser getService() {
            return CardAdvertiser.this;
        }
    }

    public IBinder onBind(Intent intent) {
        return binder;
    }

}
