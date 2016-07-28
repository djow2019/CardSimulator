package codersofanarchy.platform;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * Created by Derek on 7/27/2016.
 */
public class CardGattClient extends Service {

    private BluetoothGatt connection;

    public void connectToDevice(BluetoothDevice device) {
        disconnect();
        connection = device.connectGatt(this, false, gattCallback);
    }

    public void disconnect() {
        if (connection != null) {
            connection.disconnect();
            connection.close();
        }
    }

    public void sendAction(int action) {
        if (connection == null || connection.getService(CardPlatform.SERVICE_UUID.getUuid()) == null) {
            return;
        }

        BluetoothGattCharacteristic c = connection.getService(CardPlatform.SERVICE_UUID.getUuid()).getCharacteristic(CardPlatform.CHARACTERISTIC_UUID.getUuid());
        byte[] value = null;
        try {
            value = "action_here".getBytes("UTF-8");
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e(CardPlatform.TAG, "Couldn't send charcteristic");
        }

        c.setValue(value);
        connection.writeCharacteristic(c);
    }

    private BluetoothGattCallback gattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);

            Log.d(CardPlatform.TAG, "Connection State Changed: " + getConnectionState(newState));

            if (status == BluetoothGatt.GATT_SUCCESS && newState == BluetoothProfile.STATE_CONNECTED) {
                gatt.discoverServices();
            } else if (status == BluetoothGatt.GATT_SUCCESS && newState == BluetoothProfile.STATE_DISCONNECTED) {
                Log.d(CardPlatform.TAG, "Connection success but state disconnected");
            } else if (status != BluetoothGatt.GATT_SUCCESS) {
                gatt.disconnect();
                gatt.close();
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorRead(gatt, descriptor, status);
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);
        }

        @Override
        public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
            super.onReliableWriteCompleted(gatt, status);
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            super.onReadRemoteRssi(gatt, rssi, status);
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);
        }

        private String getConnectionState(int state) {
            switch (state) {
                case BluetoothProfile.STATE_CONNECTED:
                    return "Connected";
                case BluetoothProfile.STATE_DISCONNECTED:
                    return "Disonnected";
                case BluetoothProfile.STATE_CONNECTING:
                    return "Connecting";
                case BluetoothProfile.STATE_DISCONNECTING:
                    return "Disconnecting";
                default:
                    return String.valueOf(state);
            }
        }
    };

    private IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        public CardGattClient getService() {
            return CardGattClient.this;
        }
    }

    public IBinder onBind(Intent intent) {
        return binder;
    }


}
