package frc.robot.shooter;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.launcher.hood.HoodConst;

public class ShooterSubsystem extends SubsystemBase {
    TalonFX rightLeaderMotor = new TalonFX(ShooterConst.RIGHT_MOTOR_ID, HoodConst.LAUNCHER_BUS);
    TalonFX leftFollowerMotor = new TalonFX(ShooterConst.LEFT_MOTOR_ID, HoodConst.LAUNCHER_BUS);
    AngularVelocity targetAngularVelocity = RotationsPerSecond.of(0);
    boolean shooterEnabled = true;

    public ShooterSubsystem() {
        rightLeaderMotor.getConfigurator().apply(ShooterConfig.motorConfig);
        leftFollowerMotor.getConfigurator().apply(ShooterConfig.motorConfig);
        leftFollowerMotor.setControl(
                new Follower(rightLeaderMotor.getDeviceID(), MotorAlignmentValue.Opposed));
    }

    // sets the angular velocity of the motor in rotations per second
    public void moveAngularVelocity(AngularVelocity speed) {
        if (shooterEnabled) {
            targetAngularVelocity =
                    RotationsPerSecond.of(
                            MathUtil.clamp(
                                    speed.in(RotationsPerSecond),
                                    ShooterConst.MIN_SHOOTER_VELOCITY.in(RotationsPerSecond),
                                    ShooterConst.MAX_SHOOTER_VELOCITY.in(RotationsPerSecond)));
            rightLeaderMotor.setControl(new MotionMagicVelocityVoltage(targetAngularVelocity));
        }
    }

    // stops the frickin motors
    public void stop() {
        moveAngularVelocity(RotationsPerSecond.of(0));
    }

    // enables shooter
    public void enable() {
        shooterEnabled = true;
    }

    // disables shooter; moveangularvelocity can't be set
    public void disable() {
        shooterEnabled = false;
    }

    // gets the fricken velocity in frickin rps
    public AngularVelocity getAngularVelocity() {
        return rightLeaderMotor.getVelocity().getValue();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        // TODO Auto-generated method stub
        builder.addDoubleProperty(
                "Current Velocity (rps): ",
                () -> getAngularVelocity().in(RotationsPerSecond),
                null);
        builder.addDoubleProperty(
                "Velocity Set Point: ",
                () -> targetAngularVelocity.in(RotationsPerSecond),
                (targetAngularVelocity) ->
                        moveAngularVelocity(RotationsPerSecond.of(targetAngularVelocity)));
        builder.addBooleanProperty(
                "Shooter Enabled?: ",
                () -> shooterEnabled,
                (enable) -> {
                    if (enable) enable();
                    else disable();
                });
        super.initSendable(builder);
    }
}
