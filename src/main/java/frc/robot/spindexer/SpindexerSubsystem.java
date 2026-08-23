package frc.robot.spindexer;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SpindexerSubsystem extends SubsystemBase {
    TalonFX motor = new TalonFX(SpindexerConst.MOTOR_ID);

    public SpindexerSubsystem() {
        motor.getConfigurator().apply(SpindexerConfig.motorConfig);
    }

    public void moveMotorSpeed(double speed) {
        motor.set(speed);
    }

    public void start() {
        moveMotorSpeed(SpindexerConfig.START_SPEED);
    }

    public void stop() {
        moveMotorSpeed(0);
    }

    public double getSpeed() {
        return motor.get();
    }

    public double getVelocity() {
        return motor.getVelocity().getValueAsDouble();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("Motor Speed (frac)", this::getSpeed, this::moveMotorSpeed);
        builder.addDoubleProperty("Angular Velocity (rps)", this::getVelocity, null);
        super.initSendable(builder);
    }
}
