/**
 * 
 */
package acsvrp.tools;

/**
 * @author ipanasiuk
 *
 */
public class AMath {
	
	public static double round(double value, int decimalPlace) {
	    double power_of_ten = 1;
	    while (decimalPlace-- > 0)
	       power_of_ten *= 10.0;
	    return Math.round(value * power_of_ten) / power_of_ten;
	    }	
}
