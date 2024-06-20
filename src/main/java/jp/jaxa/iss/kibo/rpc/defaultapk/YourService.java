package jp.jaxa.iss.kibo.rpc.defaultapk;

import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;
import jp.jaxa.iss.kibo.rpc.defaultapk.ReturnToGoal;
import jp.jaxa.iss.kibo.rpc.defaultapk.Patrol;

/**
 * Class meant to handle commands from the Ground Data System and execute them in Astrobee
 */

public class YourService extends KiboRpcService {
    private Patrol patrol = new Patrol();
    private ReturnToGoal returnToGoal = new ReturnToGoal();

    @Override
    protected void runPlan1(){
        // write your plan 1 here
        patrol.moveZone1();
        patrol.moveZone2();
        patrol.moveZone3();
        patrol.moveZone4();
        // check the position of target and use ReturnToGoal to move to center of the zone we want
        // then use moveTo to go to the exact position
        
    }

    @Override
    protected void runPlan2(){
        // write your plan 2 here
    }

    @Override
    protected void runPlan3(){
        // write your plan 3 here
    }

}

