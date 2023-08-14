package edu.uky.navcamsubscriber;

// FFmpeg (Never really figured out how to use)
//import com.arthenica.mobileffmpeg.Config;
//import com.arthenica.mobileffmpeg.FFmpeg;

// For ROS Service
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.ros.android.RosService;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

import java.net.URI;

// For GS Service
/*
import org.json.JSONException;
import org.json.JSONObject;

import gov.nasa.arc.astrobee.android.gs.MessageType;
import gov.nasa.arc.astrobee.android.gs.StartGuestScienceService;
*/
// Using ROS Service
public class NavCamService extends RosService {
    // Texts for the notification
    private static final String NOTIFICATION_TITLE = "NavCam Stream";
    private static final String NOTIFICATION_TICKER = "Waiting for images...";

    // IP Address ROS Master and Hostname
    private static final URI ROS_MASTER_URI = URI.create("http://llp:11311");
    private static final String ROS_HOSTNAME = "hlp";

    private NodeConfiguration nodeConfiguration;

    // ROS-Android Node
    private NavCamNode navCamNode = null;
    private boolean isNodeExecuting = false;
    private boolean isNodeStopping = false;

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
        navCamNode = new NavCamNode();

        // Setting configurations for ROS-Android Node
        nodeConfiguration = NodeConfiguration.newPublic(ROS_HOSTNAME);
        nodeConfiguration.setMasterUri(ROS_MASTER_URI);

        nodeMainExecutor.execute(navCamNode, nodeConfiguration);
        Log.i("LOG", "NODE EXECUTING!");
        isNodeExecuting = true;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /*
    Have to comment out to build project
    For some reason there is an issue with these two methods
    @Override
    public void onDestory() {
        // Stopping service
        m_nodeMainExecutorService.stopSelf();
        isNodeExecuting = false;
        Log.i("LOG", "ONDESTORY FINISHED!");
        super.onDestroy();
    }

    @Override
    private static void putOptExtra(Intent intent, String key, String value) {
        if (intent.hasExtra(key))
            return;
        intent.putExtra(key, value);
    }
    */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ACTION_START.equals(intent.getAction())) {
            intent.setAction(ACTION_START);
        }

        //putOptExtra(intent, EXTRA_NOTIFICATION_TICKER, NOTIFICATION_TICKER);
        //putOptExtra(intent, EXTRA_NOTIFICATION_TITLE, NOTIFICATION_TITLE);

        return super.onStartCommand(intent, flags, startId);
    }
}
// Using GS Service
/*
public class NavCamStreamService extends StartGuestScienceService {
    //
    public static final String TAG = "NavCamImmage";

    private NavCamNode NavCamSubcriber = null;

    @Override
    public void onGuestScienceCustomCmd(String command) {
        sendRecievedCommand("info");
        try {
            JSONObject j_obj = new JSONObject(command);
            String str_cmd = j_obj.getString("name");
            JSONObject jResult = new JSONObject();

            switch (str_cmd) {
                case "MakeVideo":
                    // Execute Image Stitching
                    // I never figured out how to work FFmpeg
                    // Ideally a function would be called here
                    break;
                default:
                    // Inform and stop execution
                    sendData(MessageType.JSON, "data", "ERROR: Unrecognized command");
                    return;
            }
        } catch (JSONException e) {
            sendData(MessageType.JSON, "data", "ERROR parsing JSON");
        } catch (Exception ex) {
            sendData(MessageType.JSON, "data", "ERROR: Unrecognized command");
        }
    }

    // This function is called when the GS manager starts your apk.
    // Put all of your start up code in here.
    @Override
    public void onGuestScienceStart() {

    // Inform the GS Manager and the GDS that the app has been started.
    sendStarted("info");
    Log.i(TAG, "onGuestScienceStart: Nav cam apk has been started");
    }

    @Override
    public void onGuestScienceStop() {
        // Stop the API (Not sure if I need this)
        //api.shutdownFactory();

        // Inform the GS manager and the GDS that this app stopped.
        sendStopped("info");

        // Destroy all connection with the GS Manager.
        terminate();
    }
    // Make video function
    // Images from the topic will be stitched together/ concatenated to make a video
    // input .jpg (image) to output .mp4 (video)
}
*/