package frc.robot.launcher.hood;

import static edu.wpi.first.units.Units.Degrees;

import com.ctre.phoenix6.CANBus;

import edu.wpi.first.units.measure.Angle;

public class HoodConst {
    public static final CANBus LAUNCHER_BUS = new CANBus("launcher");
    public static final Angle MAX_PITCH = Degrees.of(73.606);
    public static final Angle MIN_PITCH = Degrees.of(0);
    public static final int HOOD_MOTOR_GEAR_RATIO = 24;
    public static final int MOTOR_ID = 0; // TODO: set this
}
