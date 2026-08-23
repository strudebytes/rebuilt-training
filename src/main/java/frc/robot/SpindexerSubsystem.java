package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SpindexerSubsystem extends SubsystemBase {
    TalonFX motor = new TalonFX(-1);

    private static final TalonFXConfiguration motorConfig = new TalonFXConfiguration();
    public SpindexerSubsystem() {
        motor.getConfigurator().apply(motorConfig);
        
    }

    public void moveMotorSpeed(double speed) {
        motor.set(speed);
    }

    public void start() {
        moveMotorSpeed(0.5);
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
        builder.addDoubleProperty("Angular Velocity (rps)", this::getVelocity , null);
        super.initSendable(builder);
    }

    static {
        motorConfig.CurrentLimits.StatorCurrentLimit = 80;
        motorConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        motorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; //TODO 
        
    }
}
