/**
 *
 * @author 
 * Name: Joshua Gordon
 * Student Number: S0271470
 *
 */

package au.com.joshuagordon;

public class Technician {
    
    private String firstName;
    private String lastName;
    private String phoneNumber;    
    
    /**
     * Technician Constructor
     * @param fn First Name of Technician
     * @param ln Last Name of Technician
     * @param pn Phone Number for Technician
     */
    public Technician(String fn, String ln, String pn) {
        firstName = fn;
        lastName = ln;
        phoneNumber = pn;
    }
    
    //Set and get methods
    /**
     * Set Technician's First Name
     * @param fn First Name
     */
    public void setFirstName(String fn) { firstName = fn; }

    /**
     * Get Technician's First Name
     * @return First Name
     */
    public String getFirstName() { return firstName; }

    /**
     * Set Technician's Last Name
     * @param ln Last Name
     */
    public void setLastName(String ln) { lastName = ln; }

    /**
     * Get Technician's Last Name
     * @return Last Name
     */
    public String getLastName() { return lastName; }

    /**
     * Set Technician's Phone Number
     * @param pn Phone Number
     */
    public void setPhoneNumber(String pn) { phoneNumber = pn; }

    /**
     * Get Technician's Phone Number
     * @return Phone Number
     */
    public String getPhoneNumber() { return phoneNumber; }
    
    //to String method
    /**
     * Return the object's values as concatenated String
     * @return Object Values
     */
    
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
    
    public String toString(int option) {
        switch(option) {
            case 1:
                String data = "\nName: " + firstName + " " + lastName + "\n" + 
                      "Phone Number: " + phoneNumber + "\n";
                String underline = "";
                for(int i = 0; i < 55; i ++)
                    underline += "-";
                return data + underline;
            default:
                return "";
        }
    }
}
