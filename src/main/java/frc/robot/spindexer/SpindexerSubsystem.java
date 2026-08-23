package frc.robot.spindexer;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SpindexerSubsystem extends SubsystemBase {
    TalonFX motor = new TalonFX(SpindexerConst.MOTOR_ID);

    public SpindexerSubsystem() {
        motor.getConfigurator().apply(SpindexerConfig.motorConfig);
    }

    /**
     * Sets speed of motor in fraction
     *
     * @param speed fraction from -1.0 to 1.0
     */
    public void moveMotorSpeed(double speed) {
        motor.set(speed);
    }

    /** Sets Motor Speed in fraction when starting */
    public void start() {
        moveMotorSpeed(SpindexerConfig.START_SPEED);
    }

    /** /** Sets Motor Speed in fraction when stopping */
    public void stop() {
        moveMotorSpeed(0);
    }

    /** Gets the Motor Speed */
    public double getSpeed() {
        return motor.get();
    }

    /** Gets the Angular Velocity */
    public double getVelocity() {
        return motor.getVelocity().getValueAsDouble();
    }

    /** Creates a Sendable for the Motor Speed and Angular Veloctiy for the Dashboard */
    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("Motor Speed (frac)", this::getSpeed, this::moveMotorSpeed);
        builder.addDoubleProperty("Angular Velocity (rps)", this::getVelocity, null);
        super.initSendable(builder);
    }
}
