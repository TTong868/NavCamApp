package org.ros.android;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;

import org.ros.node.NodeMainExecutor;

public abstract class RosService extends Service {
    public static final String ACTION_START = NodeMainExecutorService.ACTION_START;
    public static final String EXTRA_NOTIFICATION_TICKER = NodeMainExecutorService.EXTRA_NOTIFICATION_TICKER;
    public static final String EXTRA_NOTIFICATION_TITLE = NodeMainExecutorService.EXTRA_NOTIFICATION_TITLE;
    public static final String EXTRA_MASTER_URI = "org.ros.android.EXTRA_MASTER_URI";
    public static final String EXTRA_HOSTNAME = "org.ros.android.EXTRA_HOSTNAME";

    private final NodeMainExecutorServiceConnection m_nodeMainExecutorServiceConnection =
            new NodeMainExecutorServiceConnection();
    protected NodeMainExecutorService m_nodeMainExecutorService;

    protected abstract void init(NodeMainExecutor nodeMainExecutor);

    String m_notificationTicker = null;
    String m_notificationTitle = null;

    private final class NodeMainExecutorServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            m_nodeMainExecutorService = ((NodeMainExecutorService.LocalBinder) binder).getService();
            init();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

    }

    protected void init() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                init(m_nodeMainExecutorService);
                return null;
            }
        }.execute();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(ACTION_START)) {
            final String uri = intent.getStringExtra(EXTRA_MASTER_URI);
            m_notificationTicker = intent.getStringExtra(EXTRA_NOTIFICATION_TICKER);
            m_notificationTitle = intent.getStringExtra(EXTRA_NOTIFICATION_TITLE);
            bindNodeExecutorService();
        }
        return START_STICKY;
    }

    protected void bindNodeExecutorService() {
        Intent intent = new Intent(this, NodeMainExecutorService.class);
        intent.setAction(ACTION_START);
        intent.putExtra(EXTRA_NOTIFICATION_TICKER, m_notificationTicker);
        intent.putExtra(EXTRA_NOTIFICATION_TITLE, m_notificationTitle);
        startService(intent);
        bindService(intent, m_nodeMainExecutorServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService(m_nodeMainExecutorServiceConnection);
    }
}