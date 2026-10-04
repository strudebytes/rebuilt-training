package frc.robot.launcher.hood;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HoodSubsystem extends SubsystemBase {
    TalonFX motor = new TalonFX(HoodConst.MOTOR_ID, HoodConst.LAUNCHER_BUS);

    public HoodSubsystem() {
        motor.getConfigurator().apply(HoodConfig.motorConfig);
        motor.setPosition(HoodConst.MAX_PITCH);
    }

    // changes pitch of hood with max and min limits
    public void movePitch(Angle pitch) {
        Angle targetPitch =
                Rotations.of(
                        MathUtil.clamp(
                                pitch.in(Rotations),
                                HoodConst.MIN_PITCH.in(Rotations),
                                HoodConst.MAX_PITCH.in(Rotations)));
        motor.setControl(new MotionMagicVoltage(targetPitch));
    }

    // stows the hood
    public void stow() {
        movePitch(HoodConst.MAX_PITCH);
    }

    // gets pitch of motor
    public Angle getPitch() {
        return motor.getPosition().getValue();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        // TODO Auto-generated method stub
        builder.addDoubleProperty(
                "Hood Angle (degrees)",
                () -> getPitch().in(Degrees),
                (pitch) -> movePitch(Rotations.of(pitch)));
        super.initSendable(builder);
    }
}
