import java.util.*;
import java.io.*;
import java.nio.charset.Charset;

import org.apache.commons.io.*;

/**
 * This program will set up an interface which allows
 * the user to easily run an Augmented Reality Sandbox
 * 
 * @author Hunter Brzozowiec
 *
 */
public class AugmentedRealitySandbox {
	public static void main(String[] args)
	{
		System.out.println( "Welcome to the Augmented Reality Sandbox");
		
		while( true )
		{
			System.out.println("Select what you want to do from the list below:"); //This is the interface that gives the users options on what
			System.out.println("1.) Align the kinect sensor");                     //they want to run.
			System.out.println("2.) Align the projector");
			System.out.println("3.) Calibrate the kinect and projector");
			System.out.println("4.) Open the BoxLayout.txt file");
			System.out.println("5.) Set sandbox to Sandbox");
			System.out.println("6.) Set sandbox to Gravity Blanket");
			System.out.println("7.) Set sandbox to Electric Blanket");
			System.out.println("8.) Run the AR Sandbox");
			System.out.println("9.) Run the AR Sandbox with waster simulation");
			System.out.println("10.) Exit");

			System.out.print("Selection: ");
			Scanner in = new Scanner( System.in); //This allows me to get an input from the user
			int input = in.nextInt();
			switch( input ) //This switch() statement analyzes the user's input
			{
				//if the user inputed "1" the program starts a process with the KinectSensorAlignment.sh file
				case 1:
				{
					System.out.println("Starting Kinect sensor alignment");
					try //I needed to "try" this so I did not get any exceptions.  My program will terminate without this.
					{
						String command = new String("/home/nichols/KinectSensorAlignment.sh");//this is a shell script file I created which has the
						ProcessBuilder pb = new ProcessBuilder(command);                      //code necessary to run the Kinect sensor alignment
						Process p = pb.start();  //this inputs the code from the shell script file into terminal
						p.waitFor(); //waits for the process to terminate before the the program advances through my code
					}
					catch( Throwable e ) //This catches exceptions and allows my program not to terminate when they are thrown
					{
						e.printStackTrace();
					}
					break;
				}
				//If the user inputed "2" my program starts a process with the ProjectorAlignmentTool.sh file
				//This code functions similarly with case "1" but this case runs the projector alignment
				case 2:
				{
					System.out.println("Starting projector alignment");
					try
					{
						String command = new String("/home/nichols/ProjectorAlignmentTool.sh");
						ProcessBuilder pb = new ProcessBuilder(command);
						Process p = pb.start();
						p.waitFor();
					}
					catch( Throwable e )
					{
						e.printStackTrace();
					}
					break;
				}
				//If the user inputed "3" my program starts a process with the CalibrationProcess.sh file
				//This code functions similarly as case "1" and "2" but this case runs the calibration process
				case 3:
				{
					System.out.println("Starting calibration process");
					try
					{
						String command = new String("/home/nichols/CalibrationProcess.sh");
						ProcessBuilder pb = new ProcessBuilder(command);
						Process p = pb.start();
						p.waitFor();
					}
					catch( Throwable e )
					{
						System.out.print(e);
						e.printStackTrace();
					}
					break;
				}
				case 4:
				{
					System.out.println("Opening boxLayout.txt");
					try
					{
						String command = new String("/home/nichols/openBoxLayout.sh");
						ProcessBuilder pb = new ProcessBuilder(command);
						pb.redirectErrorStream(true);
						Process p = pb.start();
						p.waitFor();
					}
					catch( Throwable e)
					{
						System.out.println(e);
						e.printStackTrace();
					}
					break;
				}
				//If the user inputed "5" my program will rewrite the boxLayout.txt file which adjusts the Kinect's intrinsic calibration parameters 
				//to have the Kinect be aligned with the sandbox.
				case 5:
				{
					System.out.println("Setting Sandbox to sandbox depth");
					String sandbox = "(0.0283242, -0.0220612, 0.999355), -122.775" //This is the set parameters that will run the sandbox software
							  		+ "\n ( -44.4654, -11.7236, -122.634)"         //calibrated for the sandbox
							  		+	"\n ( 29.2158, -14.3384, -122.154)"
							  		+ "\n ( -42.969, 24.2686, -122.989)"
							  		+ "\n ( 30.7089, 22.3629, -122.224)";
					try
					{
						//the code in this "try" statement allows me to rewrite the contents of the boxLayout.txt file
						File boxLayout = new File("/home/nichols/src/SARndbox-1.6/etc/SARndbox-1.6/BoxLayout.txt"); //directory of the boxLayout.txt file
						FileWriter fw = new FileWriter(boxLayout);
						BufferedWriter out = new BufferedWriter(fw);
						out.write(sandbox); //this writes the new contents into the boxLayout.txt file
						out.flush();
						out.close();//This method closes the file and saves it.  When I start the sandbox, the software will read the new contents of
									//boxLayout.txt
					}
					catch(Throwable e)
					{
						e.printStackTrace();
					}
					
					break;
				}
				//If the user inputed "6" my program will rewrite the boxLayout.txt file which adjusts the Kinect's intrinsic calibration parameters
				//to have the Kinect be aligned with the blanket, similar to case "4"
				case 6:
				{
					System.out.println("Setting Sandbox to Gravity Blanket");
					String gravity = "(0.0283242, -0.0220612, 0.999355), -100.775"//These parameters will allow the sandbox software to project a red
								  + "\n ( -44.4654, -11.7236, -100.634)"          //color depth image onto the blanket.  These are already calibrated
								  +	"\n ( 29.2158, -14.3384, -100.154)"           //and aligned with the software.
								  + "\n ( -42.969, 24.2686, -100.989)"
								  + "\n ( 30.7089, 22.3629, -100.224)";
					try //This serves a similar purpose as the "try" statement in case "4" but this rewrites the 
					{	//boxLayout.txt file with the gravity parameters
						File boxLayout = new File("/home/nichols/src/SARndbox-1.6/etc/SARndbox-1.6/BoxLayout.txt");
						FileWriter fw = new FileWriter(boxLayout);
						BufferedWriter out = new BufferedWriter(fw);
						out.write(gravity);
						out.flush();
						out.close();
					}
					catch(Throwable e)
					{
						e.printStackTrace();
					}
					break;
				}
				//If the user inputed "7" my program will rewrite the boxLayout.txt file which adjusts the Kinect's Intrinsic calibration parameters
				//to have the Kinect be aligned with the blanket, similar to case "4" and "5"
				case 7:
				{
					System.out.println("Setting Sandbox to Electric Blanket");
					String electricity = "(0.0283242, -0.0220612, 0.999355), -95.775"//These parameters will allow the sandbox software to project a green
					  		+ "\n ( -44.4654, -11.7236, -95.634)"					 //color depth image onto the blanket.  These are already calibrated
					  		+	"\n ( 29.2158, -14.3384, -95.154)"					 //and aligned with the software.
					  		+ "\n ( -42.969, 24.2686, -95.989)"
					  		+ "\n ( 30.7089, 22.3629, -95.224)";
					try //This servers a similar purpose as the "try" statement in case "4" and "5" but this rewrites the
					{   //boxLayout.txt file with the electricity blanket model
						File boxLayout = new File("/home/nichols/src/SARndbox-1.6/etc/SARndbox-1.6/BoxLayout.txt");
						FileWriter fw = new FileWriter(boxLayout);
						BufferedWriter out = new BufferedWriter(fw);
						out.write(electricity);
						out.flush();
						out.close();
					}
					catch(Throwable e)
					{
						e.printStackTrace();
					}	
					break;
				}
				//If the user inputed "8" my program starts a process with the ARSandboxRunner.sh file
				//This case functions similarly with case "1" "2" and "3"
				case 8:
				{
					System.out.println("Starting AR Sandbox");
					try
					{
						String command = new String("/home/nichols/ARSandboxRunner.sh");
						ProcessBuilder pb = new ProcessBuilder(command);
						pb.redirectErrorStream(true);
						Process p = pb.start();
						p.waitFor();
					}
					catch( Throwable e )
					{
						System.out.println(e);
						e.printStackTrace();
					}
					break;
				}
				case 9:
				{
					System.out.println("Starting AR Sandbox with water simulation");
					try
					{
						String command = new String("/home/nichols/ARSandboxRunnerWater.sh");
						ProcessBuilder pb = new ProcessBuilder(command);
						pb.redirectErrorStream(true);
						Process p = pb.start();
						p.waitFor();
					}
					catch( Throwable e )
					{
						System.out.println(e);
						e.printStackTrace();
					}
					break;
				}
				//If the user inputed "9" my program will close and my interface will turn off.
				case 10:
				{
					System.out.println("Exiting AR Sandbox");
					return;
				}

			}
		}
	}		
}