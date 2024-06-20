package jp.jaxa.iss.kibo.rpc.defaultapk;

import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;
///*
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;
//*/
import java.util.ArrayList;
import java.util.List;

public class ImgProcessor extends KiboRpcService {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public Mat captItem() {
        Mat img0 = api.getMatNavCam();
        if (img0 == null || img0.empty()) {
            System.out.println("Could not open or find the image!");
            return new Mat();
        }

        // Apply thresholding
        Mat thresh = new Mat();
        Imgproc.threshold(img0, thresh, 250, 255, Imgproc.THRESH_BINARY);

        // Find contours
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(thresh, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        // Ensure hierarchy has data
        if (hierarchy.empty()) {
            System.out.println("Hierarchy is empty, no contours found.");
            return new Mat();
        }

        // Iterate through the contours based on hierarchy to find the outermost one
        int index = 0;
        Rect itemAreaRect = null;
        while (index >= 0) {
            Rect rect = Imgproc.boundingRect(contours.get(index));
            if (isItemArea(rect)) {
                itemAreaRect = rect;
                break;
            }
            index = (int) hierarchy.get(0, index)[0]; // Move to the next contour at the same hierarchy level
        }

        if (itemAreaRect != null) {
            // Define the source points for perspective transformation
            List<Point> srcPoints = new ArrayList<>();
            srcPoints.add(new Point(itemAreaRect.x, itemAreaRect.y));
            srcPoints.add(new Point(itemAreaRect.x + itemAreaRect.width, itemAreaRect.y));
            srcPoints.add(new Point(itemAreaRect.x, itemAreaRect.y + itemAreaRect.height));
            srcPoints.add(new Point(itemAreaRect.x + itemAreaRect.width, itemAreaRect.y + itemAreaRect.height));

            // Define the destination points based on the known size of the target item display area (27 cm x 15 cm)
            List<Point> dstPoints = new ArrayList<>();
            dstPoints.add(new Point(0, 0));
            dstPoints.add(new Point(27, 0));
            dstPoints.add(new Point(0, 15));
            dstPoints.add(new Point(27, 15));

            Mat srcMat = Converters.vector_Point2f_to_Mat(srcPoints);
            Mat dstMat = Converters.vector_Point2f_to_Mat(dstPoints);

            Mat perspectiveTransform = Imgproc.getPerspectiveTransform(srcMat, dstMat);

            Mat itemAreaTransformed = new Mat();
            Imgproc.warpPerspective(img0, itemAreaTransformed, perspectiveTransform, new Size(27, 15));

            // Now crop the 15 cm x 20 cm area from the transformed perspective
            Rect cropRect = new Rect(0, 0, 20, 15);
            Mat finalCroppedItemArea = new Mat(itemAreaTransformed, cropRect);

            return finalCroppedItemArea;
        } else {
            System.out.println("Item area not found!");
            return new Mat();
        }
    }

    private boolean isItemArea(Rect rect) {
        // Define the criteria for identifying the item area
        double aspectRatio = (double) rect.width / rect.height;
        double minAspectRatio = 1.7; // Example value, adjust as needed
        double maxAspectRatio = 2.0; // Example value, adjust as needed
        int minSize = 50; // Example value, adjust as needed
        int maxSize = 300; // Example value, adjust as needed

        return aspectRatio >= minAspectRatio && aspectRatio <= maxAspectRatio &&
                rect.width >= minSize && rect.height >= minSize &&
                rect.width <= maxSize && rect.height <= maxSize;
    }
}
