// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyer;
import frc.robot.subsystems.VIndexer;

public class Purge extends CommandBase {
  private final Conveyer conveyer;
  private final VIndexer vIndexer;
  public Purge(Conveyer c, VIndexer v) {
    conveyer = c;
    vIndexer = v;
    addRequirements(conveyer);
    addRequirements(vIndexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    vIndexer.purge();
    conveyer.purge();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
