package jp.jaxa.iss.kibo.rpc.defaultapk;

import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;
import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;

public class Patrol extends KiboRpcService{
    protected void moveZone1() {
        api.startMission();
        // open camera

        int rotation_angle_deg = 90;
        float rotation_angle_rad = rotation_angle_deg * (float) (Math.PI / 180);
        float x = (float) Math.sin(rotation_angle_rad / 2);
        float y = 0;
        float z = 0;
        float w = (float) Math.cos(rotation_angle_rad / 2);
        Quaternion orientation1 = new Quaternion(-x, y, z, w);
        Quaternion orientation2 = new Quaternion(x, y, z, w);
        Quaternion orientation3 = new Quaternion(0,0,0,1);
        Point one = new Point(10.5, -10.5, 4.97);
        Point two = new Point(10.8, -10.5, 4.97);
        Point three = new Point(11.2, -10.5, 4.97);
        Point four = new Point(11.2, -10.1, 4.97);
        Point five = new Point(11.2, -9.7, 4.97);
        Point six = new Point(11.235, -9.7, 5.3);
        Point seven = new Point(11.235, -9.3, 5.3);
        // Survey zone1 and move to zone2
        api.moveTo(one, orientation1, false);
        api.moveTo(two, orientation3, false);
        api.moveTo(three, orientation3, false);
        api.moveTo(four, orientation2,false);
        api.moveTo(five, orientation3, false);
        api.moveTo(six, orientation3, false);
        api.moveTo(seven, orientation3, false);
    }

    protected void moveZone2() {
        Quaternion orientation1 = new Quaternion(1,0,0,0);
        Quaternion orientation2 = new Quaternion(0,0,0,1);
        Point one = new Point(11.2, -9, 4.97);
        Point two = new Point(11.2, -8.7, 4.97);
        Point three = new Point(10.6, -8.7, 4.97);
        Point four = new Point(10.6, -9, 4.97);
        Point five = new Point(10.6, -9.3, 4.97);
        Point six = new Point(10.56, -8.3, 5.3);
        // Survey zone2 and move to zone3
        api.moveTo(one, orientation2, false);
        api.moveTo(two, orientation2, false);
        api.moveTo(three, orientation1, false);
        api.moveTo(four, orientation2,false);
        api.moveTo(five, orientation2, false);
        api.moveTo(six, orientation2, false);
    }

    protected void moveZone3() {
        Quaternion orientation1 = new Quaternion(1,0,0,0);
        Quaternion orientation2 = new Quaternion(0,0,0,1);
        Point one = new Point(10.6, -8, 4.97);
        Point two = new Point(10.6, -7.7, 4.97);
        Point three = new Point(11.2, -7.7, 4.97);
        Point four = new Point(11.2, -8, 4.97);
        Point five = new Point(11.2, -8.3, 4.97);
        Point six = new Point(11.235, -7.2, 5.3);
        // Survey zone3 and move to zone4
        api.moveTo(one, orientation2, false);
        api.moveTo(two, orientation2, false);
        api.moveTo(three, orientation1, false);
        api.moveTo(four, orientation2,false);
        api.moveTo(five, orientation2, false);
        api.moveTo(six, orientation2, false);
    }

    protected void moveZone4() {
        int rotation_angle_deg = 90;
        float rotation_angle_rad = rotation_angle_deg * (float) (Math.PI / 180);
        float x = (float) Math.sin(rotation_angle_rad / 2);
        float y = 0;
        float z = 0;
        float w = (float) Math.cos(rotation_angle_rad / 2);
        Quaternion orientation1 = new Quaternion(-x, y, z, w);
        Quaternion orientation2 = new Quaternion(1,0,0,0);
        Quaternion orientation3 = new Quaternion(0,0,0,1);
        Point one = new Point(11.2, -6.9, 4.97);
        Point two = new Point(10.6, -7.2, 4.97);
        Point three = new Point(10.6, -6.9, 4.97);
        Point four = new Point(10.6, -6.7, 4.97);
        Point five = new Point(10.7, -6.7607, 4.9654);
        Point six = new Point(10.943, -6.7607, 4.9654);
        // Survey zone4 and move to astronaut
        api.moveTo(one, orientation3, false);
        api.moveTo(two, orientation2, false);
        api.moveTo(three, orientation3, false);
        api.moveTo(four, orientation3,false);
        api.moveTo(five, orientation1, false);
        api.moveTo(six, orientation1, false);
        // check photo in astronaut hand

    }
}
