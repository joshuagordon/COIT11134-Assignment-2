/**
 *
 * @author 
 * Name: Joshua Gordon
 * Student Number: S0271470
 *
 */

package au.com.joshuagordon;

public class Installation {
    
    private String startDate; 
    private String endDate;
    private int horsePower;
    private int qtyZones;
    private int qtyOutlets;
    
    /**
     * Installation Object Constructor
     * @param sd Start Date of Installation
     * @param ed End Date of Installation
     * @param hp Horse Power of Unit
     * @param zones Zones applicable to site
     * @param outlets Outlets available at site
     */
    public Installation(String sd, String ed, int hp, int zones, int outlets) {
        startDate = sd;
        endDate = ed;
        horsePower = hp;
        qtyZones = zones;
        qtyOutlets = outlets;
    }
    
    //Set and get methods
    /**
     * Set start date of installation
     * @param sd Start Date
     */
    public void setStartDate(String sd) { startDate = sd; }

    /**
     * Get start date of installation
     * @return Start Date
     */
    public String getStartDate() { return startDate; }

    /**
     * Set end date of installation
     * @param ed End Date
     */
    public void setEndDate(String ed) { endDate = ed; }

    /**
     * Get end date of installation
     * @return End Date
     */
    public String getEndDate() { return endDate; }

    /**
     * Set horse power
     * @param hp Horse Power
     */
    public void setHorsePower(int hp) { horsePower = hp; }

    /**
     * Get horse power
     * @return Horse Power
     */
    public int getHorsePower() { return horsePower; }

    /**
     * Set quantity of zones
     * @param zones Quantity of Zones
     */
    public void setZones(int zones) { qtyZones = zones; }

    /**
     * Get quantity of zones
     * @return Quantity of Zones
     */
    public int getZones() { return qtyZones; }

    /**
     * Set quantity of outlets
     * @param outlets Quantity of Outlets
     */
    public void setOutlets(int outlets) { qtyOutlets = outlets; }

    /**
     * Get quantity of outlets
     * @return Quantity of Outlets
     */
    public int getOutlets() { return qtyOutlets; }
    
    //to String method
    /**
     * Return the object's values as concatenated String
     * @return Object Values
     */
    @Override
    public String toString() {
        String data = "\nDate: " + startDate + "-" + endDate + ".\n" + 
                "HP: " + horsePower + ", Zones: " + qtyZones + ", Outlets: " + 
                qtyOutlets + "\n";
        String underline = "";
        for(int i = 0; i < 55; i ++)
            underline += "-";
        return data + underline;
    }
}
