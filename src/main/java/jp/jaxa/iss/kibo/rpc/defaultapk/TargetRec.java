package jp.jaxa.iss.kibo.rpc.defaultapk;

import org.opencv.core.Mat;

import gov.nasa.arc.astrobee.Kinematics;
import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;

public class TargetRec extends KiboRpcService {
    public Kinematics pose;
    public Mat image;

    public TargetRec(Kinematics pose, Mat image) {
        this.pose = pose;
        this.image = image;
    }
}
