package frc.robot.intake;

import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    TalonFX deployMotor = new TalonFX(IntakeConst.DEPLOY_MOTOR_ID);
    TalonFX rollerMotor = new TalonFX(IntakeConst.ROLLER_MOTOR_ID);

    public IntakeSubsystem() {
        deployMotor.getConfigurator().apply(IntakeConfig.deployMotorConfig);
        rollerMotor.getConfigurator().apply(IntakeConfig.rollerMotorConfig);
        deployMotor.setPosition(IntakeConst.MAX_ANGLE);
    }

    public void moveMotorSpeed(double speed) {
        rollerMotor.set(speed);
    }

    public void moveAngle(Angle angle) {
        Angle targetAngle =
                Rotations.of(
                        MathUtil.clamp(
                                angle.in(Rotations),
                                IntakeConst.MIN_ANGLE.in(Rotations),
                                IntakeConst.MAX_ANGLE.in(Rotations)));
        deployMotor.setControl(new MotionMagicVoltage(targetAngle));
    }

    // moves intake up
    public void moveUp() {
        moveAngle(IntakeConst.MAX_ANGLE);
    }

    // moves intake down
    public void moveDown() {
        moveAngle(IntakeConst.MIN_ANGLE);
    }

    // turns on intake's rollers
    public void rollersOn() {
        moveMotorSpeed(IntakeConfig.START_SPEED);
    }

    // turns off intake's rollers
    public void rollersOff() {
        moveMotorSpeed(IntakeConfig.STOP_SPEED);
    }

    // intake's rollers go in reverse
    public void rollersReverse() {
        moveMotorSpeed(IntakeConfig.REVERSE_SPEED);
    }

    // method that turns on rollers and moves intake down
    public void deploy() {
        rollersOn();
        moveDown();
    }

    // method that turns off rollers and moves intake up
    public void stow() {
        rollersOff();
        moveUp();
    }

    // gets angle of the deploy motor
    public Angle getAngle() {
        return deployMotor.getPosition().getValue();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        // TODO Auto-generated method stub
        builder.addDoubleProperty(
                "intakeAngle (rotations)",
                () -> getAngle().in(Rotations),
                (angle) -> moveAngle(Rotations.of(angle)));
        super.initSendable(builder);
    }
}
