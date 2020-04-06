/**
 *
 * @author 
 * Name: Joshua Gordon
 * Student Number: S0271470
 *
 */

package au.com.joshuagordon;

public class Building {
    
    //Address Fields
    private int unitNo;
    private int streetNo;
    private String address;
    private String suburb;
    private int postcode;
    private String country;
    
    //Building specifics
    
    /**
     * Building Constructor
     * @param unit Unit Number
     * @param strNo Street Number
     * @param addr Address
     * @param sub Suburb
     * @param postc Postcode
     * @param cntry Country
     */
    public Building(int unit, int strNo, String addr, String sub, 
            int postc, String cntry) {
        unitNo = unit;
        streetNo = strNo;
        address = addr;
        suburb = sub;
        postcode = postc;
        country = cntry;
    }
    
    /**
     * Building Constructor with no Unit Number
     * @param strNo Street Number
     * @param addr Address
     * @param sub Suburb
     * @param postc Postcode
     * @param cntry Country
     */
    public Building(int strNo, String addr, String sub, 
            int postc, String cntry) {
        unitNo = 0;
        streetNo = strNo;
        address = addr;
        suburb = sub;
        postcode = postc;
        country = cntry;
    }
    
    //Set and get methods
    /**
     * Set Unit Number
     * @param unit Unit Number
     */
    public void setUnitNo(int unit) { unitNo = unit; }

    /**
     * Get Unit Number
     * @return Unit Number
     */
    public int getUnitNo() { return unitNo; }

    /**
     * Set Street Number
     * @param strNo Street Number
     */
    public void setStreetNo(int strNo) { streetNo = strNo; }

    /**
     * Get Street Number
     * @return Street Number
     */
    public int getStreetNo() { return streetNo; }

    /**
     * Set Building Address
     * @param addr Building Address
     */
    public void setAddress(String addr) { address = addr; }

    /**
     * Get Building Address
     * @return Building Address
     */
    public String getAddress() { return address; }

    /**
     * Set Building Suburb
     * @param sub Building Suburb
     */
    public void setSuburb(String sub) { suburb = sub; }

    /**
     * Get Building Suburb
     * @return Building Suburb
     */
    public String getSuburb() { return suburb; }

    /**
     * Set Building Postcode
     * @param postc Building Postcode
     */
    public void setPostcode(int postc) { postcode = postc; }

    /**
     * Get Building Postcode
     * @return Building Postcode
     */
    public int getPostcode() { return postcode; }

    /**
     * Set Building Country
     * @param cntry Building Country
     */
    public void setCountry(String cntry) { country = cntry; }

    /**
     * Get Building Country
     * @return Building Country
     */
    public String getCountry() { return country; }
    
    /**
     * Return the object's values as concatenated String
     * @return Object Values
     */
    @Override
    public String toString() {
        if(unitNo == 0) {
            String data = "\n" + streetNo + " " + address + ",\n" + 
                    suburb + " " + postcode + " " + country + "\n";
            String underline = "";
            for(int i = 0; i < 55; i ++)
                underline += "-";
            return data + underline;
        }
        else {
            String data = "\n" + unitNo + "/" + streetNo + " " + address + ",\n" + 
                    suburb + " " + postcode + " " + country + "\n";
            String underline = "";
            for(int i = 0; i < 55; i ++)
                underline += "-";
            return data + underline;
        }
    }
}
