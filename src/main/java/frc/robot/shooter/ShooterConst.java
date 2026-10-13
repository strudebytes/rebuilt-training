package frc.robot.shooter;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import edu.wpi.first.units.measure.AngularVelocity;

public class ShooterConst {
    public static final int RIGHT_MOTOR_ID = -1;
    public static final int LEFT_MOTOR_ID = -1;
    public static final AngularVelocity MAX_SHOOTER_VELOCITY = RotationsPerSecond.of(70);
    public static final AngularVelocity MIN_SHOOTER_VELOCITY = RotationsPerSecond.of(-70);
}
