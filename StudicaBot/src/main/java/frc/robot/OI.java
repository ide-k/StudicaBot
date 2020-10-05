/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * Add your docs here.
 */
public class OI {

    XboxController driver = new XboxController(0);

    private double deadzone(double input, double deadzone)
    {
        // impossible to return a value less than the deadzone
        return Math.abs(input) < deadzone ? 0.0 : input;
    }

    private double deadzoneTurn(double input, double deadzone)
    {
        // returns nothing if input is less than deadzone
        // else returns a scaled value of the input from -1 to 0 or 0 to 1
        if(Math.abs(input) > deadzone)
        {
            return 0.0;
        }
        else
        {
            if(input >= 0.0)
            {
                return ((1.0 - 0.0) / (1.0 - deadzone)) * (input - deadzone);
            }
            else
            {
                return -(((0.0 - (-1.0)) / (-deadzone - (-1.0))) * (-deadzone - input));
            }
        }
    }

    public double driveControl()
    {
        return getTrigger(Hand.kRight, driver) - getTrigger(Hand.kLeft, driver);
    }

    public double getTrigger(Hand h, XboxController cont)
    {
        return deadzone(cont.getTriggerAxis(h),0.17);
    }

    public double getJoystickY(XboxController xbox, Hand h, double deadzone) 
    {
        return deadzone(xbox.getY(h), deadzone);
    }

    public double getJoystickX(XboxController xbox, Hand h, double deadzone)
    {
        if(xbox.getPort == 0 && h == Hand.kLeft)
        {
            return deadzoneTurn(xbox.getX(h), deadzone);
        }
        return deadzone(xbox.getX(h), deadzone);
    }

}
