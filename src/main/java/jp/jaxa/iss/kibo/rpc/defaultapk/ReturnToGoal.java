package jp.jaxa.iss.kibo.rpc.defaultapk;

import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;
import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;

public class ReturnToGoal extends KiboRpcService {
    protected void zone3() {
        int rotation_angle_deg = 90;
        float rotation_angle_rad = rotation_angle_deg * (float) (Math.PI / 180);
        float x = (float) Math.sin(rotation_angle_rad / 2);
        float y = 0;
        float z = 0;
        float w = (float) Math.cos(rotation_angle_rad / 2);
        Quaternion orientation1 = new Quaternion(-x, y, z, w);
        Quaternion orientation2 = new Quaternion(0,0,0,1);
        Point one = new Point(11.235,-7.2,5.3);
        Point two = new Point(10.925 ,-7.925, 4.97);
        // move to center of the zone
        api.moveTo(one, orientation1, false);
        api.moveTo(two, orientation2, false);
    }

    protected void zone2() {
        zone3();
        Quaternion orientation = new Quaternion(0,0,0,1);
        Point one = new Point(11.235, -8.3, 4.62);
        Point two = new Point(10.925, -8.975, 4.97);
        // move to center of the zone
        api.moveTo(one, orientation, false);
        api.moveTo(two, orientation, false);
    }

    protected void zone1() {
        zone2();
        Quaternion orientation = new Quaternion(0,0,0,1);
        Point one = new Point(11.235, -9.3, 5.3);
        Point two = new Point(10.925, -9.85, 4.97);
        // move to center of the zone
        api.moveTo(one, orientation, false);
        api.moveTo(two, orientation, false);
    }
}
