package frc.robot.launcher.feeder;

import com.ctre.phoenix6.controls.CoastOut;
import com.ctre.phoenix6.controls.StaticBrake;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeederSubsystem extends SubsystemBase {
    TalonFX motor = new TalonFX(FeederConst.MOTOR_ID, FeederConst.FEEDER_BUS);

    public FeederSubsystem() {
        motor.getConfigurator().apply(FeederConfig.motorConfig);
    }

    /**
     * set speed of motor in fraction
     *
     * @param speed fraction = -1.0 to 1.0
     */
    public void moveMotorSpeed(double speed) {
        motor.set(speed);
    }

    /** set motor speed in fraction when starting */
    public void start() {
        moveMotorSpeed(FeederConfig.START_SPEED);
    }

    /** sets motor speed with control request */
    public void coast() {
        motor.setControl(new CoastOut());
    }

    /** sets the motor to brake with control request */
    public void brake() {
        motor.setControl(new StaticBrake());
    }

    /** get motor speed */
    public double getSpeed() {
        return motor.get();
    }

    /** Creates a Sendable for Motor Speed */
    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("Motor Speed (frac)", this::getSpeed, this::moveMotorSpeed);
        super.initSendable(builder);
    }
}
