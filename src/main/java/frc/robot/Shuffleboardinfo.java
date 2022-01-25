package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;

public class Shuffleboardinfo {
    private final ShuffleboardTab driverTab;

    private final NetworkTableEntry mIsTargetValid;
    
    private final NetworkTableEntry mKpSteer, mKpDrive;
    
    private static Shuffleboardinfo instance = null;

    private Shuffleboardinfo() {
        driverTab = Shuffleboard.getTab("driver");
        mIsTargetValid = driverTab.add("Valid Target?", false)
        .withPosition(0, 0).withSize(1, 1).withWidget(BuiltInWidgets.kBooleanBox).getEntry();
        mKpSteer = Shuffleboard.getTab("Limelight Aim").add("Steering KP", -0.05).getEntry();
        mKpDrive = Shuffleboard.getTab("Limelight Aim").add("Drive KP", -0.05).getEntry();
    }

    public static Shuffleboardinfo getInstance() {
        if (instance == null) {
            instance = new Shuffleboardinfo();
        }

        return instance;
    }

    public NetworkTableEntry getIsTargetValid() {
        return mIsTargetValid;
    }

    public NetworkTableEntry getKpSteer() {
        return mKpSteer;
    }

    public NetworkTableEntry getKpDrive() {
        return mKpDrive;
    }

    public void addAutoChooser(SendableChooser<Command> mAutoChooser) {
        driverTab.add("Autonomous Chooser", mAutoChooser).withWidget(BuiltInWidgets.kComboBoxChooser)
        .withPosition(1, 0).withSize(2, 1);
    }
}
