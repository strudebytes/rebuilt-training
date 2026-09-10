package frc.robot.intake;

import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class IntakeConfig {
    public static final TalonFXConfiguration deployMotorConfig = new TalonFXConfiguration();
    public static final TalonFXConfiguration rollerMotorConfig = new TalonFXConfiguration();
    public static final double CURRENT_LIMIT = 80;

    static {
        deployMotorConfig.CurrentLimits.StatorCurrentLimit = CURRENT_LIMIT;
        deployMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        deployMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        deployMotorConfig.Feedback.SensorToMechanismRatio = IntakeConst.DEPLOY_MOTOR_GEAR_RATIO;
        deployMotorConfig.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        deployMotorConfig.SoftwareLimitSwitch.ForwardSoftLimitThreshold =
                IntakeConst.MAX_ANGLE.in(Rotations);
        deployMotorConfig.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        deployMotorConfig.SoftwareLimitSwitch.ReverseSoftLimitThreshold =
                IntakeConst.MIN_ANGLE.in(Rotations);
    }

    static {
        rollerMotorConfig.CurrentLimits.StatorCurrentLimit = CURRENT_LIMIT;
        rollerMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        rollerMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    }

    public static final double START_SPEED = 0.5;
    public static final double STOP_SPEED = 0;
    public static final double REVERSE_SPEED = -0.5;
}
