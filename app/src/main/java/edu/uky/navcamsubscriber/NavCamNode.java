package edu.uky.navcamsubscriber;

import org.ros.message.MessageListener;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Subscriber;
import android.util.Log;
import sensor_msgs.Image;

public class NavCamNode extends AbstractNodeMain {

    // ROS Topic name for simulation
    // static final String TOPIC_IMAGE = "/hw/cam_nav"

    // ROS Topic name
    static final String TOPIC_IMAGE = "/mgt/img_sampler/nav_cam/image_record";

    @Override
    public GraphName getDefaultNodeName() { return GraphName.of("nav_cam"); }

    @Override
    public void onStart(ConnectedNode connectedNode) {
        // Creating subscriber for ROS Topics and adding message listeners
        Subscriber<Image> subscriber = connectedNode.newSubscriber(TOPIC_IMAGE, Image._TYPE);
        subscriber.addMessageListener(new MessageListener<Image>() {
            @Override
            public void onNewMessage(Image message) {
                Log.i("LOG", "New image is here!");
            }
        });
    }
}
