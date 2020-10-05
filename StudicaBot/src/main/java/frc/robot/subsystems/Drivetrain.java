/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

  public CANSparkMax leftM;
  public CANSparkMax rightM;
  public CANSparkMax leftF;
  public CANSparkMax rightF;

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain(int leftMID, int rightMID, int leftFID, int rightFID, MotorType type) 
  {
    leftM = new CANSparkMax(leftMID, type);
    rightM = new CANSparkMax(rightMID, type);
    leftF = new CANSparkMax(leftFID, type);
    rightF = new CANSparkMax(rightFID, type);
  }

  public void arcadeDriveWF(double speed, double turn)
  {
    double left;
    double right;

    if (turn > 0) 
    {
      left = (speed) + ((Math.exp(Constants.TURN_WEIGHT_FACTOR * turn) * turn));
      right = (speed) + ((Math.exp(Constants.TURN_WEIGHT_FACTOR * turn) * -turn));
    } else if (turn < 0) 
    {
      left = (speed) + ((Math.exp(Constants.TURN_WEIGHT_FACTOR * -turn) * turn));
      right = (speed) + ((Math.exp(Constants.TURN_WEIGHT_FACTOR * -turn) * -turn));
    } else 
    {
      left = speed;
      right = speed;
    }

    this.tankDrive(left, right);
  }

  public void tankDrive(double left, double right)
  {
    leftM.set();
    rightM.set();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
