package frc.robot.spindexer;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class SpindexerConfig {
    public static final TalonFXConfiguration motorConfig = new TalonFXConfiguration();
    public static final double CURRENT_LIMIT = 80;
    public static final double START_SPEED = 0.5;

    static {
        motorConfig.CurrentLimits.StatorCurrentLimit = CURRENT_LIMIT;
        motorConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        motorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; // TODO
    }
}
