package frc.robot.launcher.hood;

import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class HoodConfig {
    public static final TalonFXConfiguration motorConfig = new TalonFXConfiguration();
    public static final double CURRENT_LIMIT = 80;

    static {
        motorConfig.CurrentLimits.StatorCurrentLimit = CURRENT_LIMIT;
        motorConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        motorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        motorConfig.Feedback.SensorToMechanismRatio = HoodConst.HOOD_MOTOR_GEAR_RATIO;
        motorConfig.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        motorConfig.SoftwareLimitSwitch.ForwardSoftLimitThreshold =
                HoodConst.MAX_PITCH.in(Rotations);
        motorConfig.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        motorConfig.SoftwareLimitSwitch.ReverseSoftLimitThreshold =
                HoodConst.MIN_PITCH.in(Rotations);
    }
}
