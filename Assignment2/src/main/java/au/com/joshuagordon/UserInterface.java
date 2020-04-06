/**
 *
 * @author 
 * Name: Joshua Gordon
 * Student Number: S0271470
 *
 */

package au.com.joshuagordon;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Joshua Gordon
 */
public class UserInterface extends JFrame implements ActionListener, ItemListener {
    
    //Run the GUI
    public static void main(String[] args) {
        //Initialise and show gui.
        UserInterface gui = new UserInterface();
        gui.load(); //load database into application
        gui.setSize(750, 700);
        gui.setVisible(true);
    }
    
    //form panels to put inside overall panel
    private final JPanel mainFrame = new JPanel();
    private final JPanel form = new JPanel();
    private final JPanel outputFrame = new JPanel();
    private final JPanel sPanel = new JPanel();
    
    //panel for form sections
    private final JPanel buildingSection = new JPanel();
    private final JPanel installationSection = new JPanel();
    private final JPanel technicianSection = new JPanel();
    
    //Main buttons
    private final JButton submit = new JButton("Submit");
    private final JButton load = new JButton("Load");
    private final JButton save = new JButton("Save");
    private final JButton clear = new JButton("Clear");
    private final JButton exit = new JButton("Exit");
    
    //Section Headings
    private final JLabel buildingHeading = new JLabel("Building");
    private final JLabel installationHeading = new JLabel("Installation");
    private final JLabel technicianHeading = new JLabel("Technician");
    private final JLabel technicianFilterHeading = new JLabel("Filter");
    private final JLabel outputHeading = new JLabel("Output");
    
    //Label to explain what's required
    private final JLabel requiredLabel = new JLabel("(* = Required)");
    private final JLabel requiredLabelTwo = new JLabel("(* = Required)");
    private final JLabel requiredLabelThree = new JLabel("(* = Required)");
    
    //Font for section headings
    private final Font heading = new Font("Tahoma", Font.BOLD, 18);
    private final Font requiredLabelFont = new Font("Tahoma", Font.ITALIC, 11);
    
    /**
     * Building Section
     * 
     * Fields = street / unit number, street name, suburb, postcode, country
     * 
     */
    private final JLabel unitNoLabel = new JLabel("Unit Number                     ");
    private final JTextField unitNoInput = new JTextField(3);
    private final JLabel streetNoLabel = new JLabel("Street Number*                   ");
    private final JTextField streetNoInput = new JTextField(3);
    private final JLabel addressLabel = new JLabel("Address*  ");
    private final JTextField addressInput = new JTextField(12);
    private final JLabel suburbLabel = new JLabel("Suburb*    ");
    private final JTextField suburbInput = new JTextField(12);
    private final JLabel postcodeLabel = new JLabel("Postcode*                       ");
    private final JTextField postcodeInput = new JTextField(5);
    private final JLabel countryLabel = new JLabel("Country*  ");
    private final JTextField countryInput = new JTextField(12);
    
    /**
     * Installation Section
     * 
     * Fields = start date, end date, technician,
     * More fields = HP, zones, outlets
     * 
     */
    private final JLabel startDateLabel = new JLabel("Start Date*    ");
    private final JFormattedTextField startDateInput = 
            new JFormattedTextField(new SimpleDateFormat("dd/mm/yyyy"));
    private final JLabel endDateLabel = new JLabel("End Date*      ");
    private final JFormattedTextField endDateInput = 
            new JFormattedTextField(new SimpleDateFormat("dd/mm/yyyy"));
    private final JLabel hpLabel = new JLabel("Horse Power*");
    private final JSlider hpInput = new JSlider(JSlider.HORIZONTAL,
            50, 300, 100);
    private final JLabel zonesLabel = new JLabel("Zones*");
    private final JSlider zonesInput = new JSlider(JSlider.HORIZONTAL,
            1, 4, 2);
    private final JLabel outletsLabel = new JLabel("Outlets*");
    private final JSlider outletsInput = new JSlider(JSlider.HORIZONTAL,
            1, 10, 5);
        
    /**
     * Technician Section
     * 
     * Fields = first name, last name, phone number
     * 
     */
    private final JLabel existingTechLabel = new JLabel("Existing Technician");
    private final JComboBox existingTechs = new JComboBox();
    private final JLabel firstNameLabel = new JLabel("First Name*           ");
    private JTextField firstNameInput = new JTextField(12);
    private final JLabel lastNameLabel = new JLabel("Last Name*           ");
    private JTextField lastNameInput = new JTextField(12);
    private final JLabel phoneNumberLabel = new JLabel("Phone Number*   ");
    private JTextField phoneNumberInput = new JTextField(12);
    
    private final JLabel filterLabel = new JLabel("Technician");
    private final JComboBox filterBox = new JComboBox();
    
    /**
     * Display Section
     * 
     */
    private final JPanel outputPane = new JPanel();
    private final JTextArea buildingOutputPane = new JTextArea(17, 23);
    private final JTextArea installationOutputPane = new JTextArea(17, 23);
    private final JTextArea technicianOutputPane = new JTextArea(17, 23);
    
    //ArrayList to store created data.
    ArrayList<Building> buildings = new ArrayList<>();
    ArrayList<Installation> installations = new ArrayList<>();
    ArrayList<Technician> technicians = new ArrayList<>();
    
    private ArrayList<String> technicianNames = new ArrayList<>();
    private ArrayList<String> technicianNumbers = new ArrayList<>();
    
    private final String DELIMITER = ", "; //comma separated values
    private final String fileName = "data/database.txt";
    
    public enum CheckSymbolsType { PHONE, NUMBER };
    
    //GUI Constructor
    public UserInterface() {
        super("CQ Airconditioning Installers (Joshua Gordon - S0271470)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set close operation
        
        mainFrame.setLayout(new GridLayout(2, 1));
        add(mainFrame, BorderLayout.CENTER);
        
        form.setLayout(new GridLayout(1, 3)); //grid
        mainFrame.add(form, BorderLayout.CENTER); //add grid to contentpane, in center
        
        sPanel.setLayout(new GridLayout(1,3));
        add(sPanel, BorderLayout.SOUTH);
        
        //set heading fonts
        buildingHeading.setFont(heading);
        installationHeading.setFont(heading);
        technicianHeading.setFont(heading);
        technicianFilterHeading.setFont(heading);
        outputHeading.setFont(heading);
        
        //set fonts for required labels
        requiredLabel.setFont(requiredLabelFont);
        requiredLabelTwo.setFont(requiredLabelFont);
        requiredLabelThree.setFont(requiredLabelFont);
        
        //add headings to relevant sections
        buildingSection.add(buildingHeading);
        installationSection.add(installationHeading);
        technicianSection.add(technicianHeading);
        
        //add required label to all sections
        buildingSection.add(requiredLabel);
        installationSection.add(requiredLabelTwo);
        technicianSection.add(requiredLabelThree);
        
        //add form sections to form panel
        form.add(buildingSection);
        form.add(installationSection);
        form.add(technicianSection);
        
        //add buttons to south panel
        sPanel.add(submit);
        sPanel.add(load);
        sPanel.add(save);
        sPanel.add(clear);
        sPanel.add(exit);
        
        //Add listener to buttons
        submit.addActionListener(this);
        load.addActionListener(this);
        save.addActionListener(this);
        clear.addActionListener(this);
        exit.addActionListener(this);

        //Setup output area
        outputPane.setLayout(new GridLayout(1,3));
        
        buildingOutputPane.setEditable(false);
        installationOutputPane.setEditable(false);
        technicianOutputPane.setEditable(false);
        
        outputPane.add(buildingOutputPane);
        outputPane.add(installationOutputPane);
        outputPane.add(technicianOutputPane);
                
        outputFrame.add(outputHeading);
        outputFrame.add(new JScrollPane(outputPane,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
        mainFrame.add(outputFrame);
        
        //add fields to building sections
        buildingSection.add(unitNoLabel, BorderLayout.SOUTH);
        buildingSection.add(unitNoInput, BorderLayout.SOUTH);
        buildingSection.add(streetNoLabel, BorderLayout.SOUTH);
        buildingSection.add(streetNoInput, BorderLayout.SOUTH);
        buildingSection.add(addressLabel, BorderLayout.SOUTH);
        buildingSection.add(addressInput, BorderLayout.SOUTH);
        buildingSection.add(suburbLabel, BorderLayout.SOUTH);
        buildingSection.add(suburbInput, BorderLayout.SOUTH);
        buildingSection.add(postcodeLabel, BorderLayout.SOUTH);
        buildingSection.add(postcodeInput, BorderLayout.SOUTH);
        buildingSection.add(countryLabel, BorderLayout.SOUTH);
        buildingSection.add(countryInput, BorderLayout.SOUTH);

        //add fields to installation sections
        installationSection.add(startDateLabel);
        startDateInput.setColumns(8);
        installationSection.add(startDateInput);
        installationSection.add(endDateLabel);
        endDateInput.setColumns(8);
        installationSection.add(endDateInput);
        installationSection.add(hpLabel);
        hpInput.setMajorTickSpacing(50);
        hpInput.setMinorTickSpacing(25);
        hpInput.setPaintTicks(true);
        hpInput.setPaintLabels(true);
        installationSection.add(hpInput);
        installationSection.add(zonesLabel);
        zonesInput.setMajorTickSpacing(1);
        zonesInput.setMinorTickSpacing(1);
        zonesInput.setPaintTicks(true);
        zonesInput.setPaintLabels(true);
        installationSection.add(zonesInput);
        installationSection.add(outletsLabel);
        outletsInput.setMajorTickSpacing(2);
        outletsInput.setMinorTickSpacing(1);
        outletsInput.setPaintTicks(true);
        outletsInput.setPaintLabels(true);
        installationSection.add(outletsInput);

        // Add fields to Technician Section
        technicianSection.add(existingTechLabel);
        technicianSection.add(existingTechs);
        existingTechs.addItem("");
        existingTechs.addItemListener(this);        
        technicianSection.add(firstNameLabel);
        technicianSection.add(firstNameInput);
        technicianSection.add(lastNameLabel);
        technicianSection.add(lastNameInput);
        technicianSection.add(phoneNumberLabel);
        technicianSection.add(phoneNumberInput);
        
        // Add filter to technician section
        technicianSection.add(technicianFilterHeading);
        technicianSection.add(filterLabel);
        technicianSection.add(filterBox);
        filterBox.addItem("");
        filterBox.addItemListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Check which button is used, then redirect to method (or execute code)
        if(e.getSource() == submit) { submit(); }
        if(e.getSource() == load) { load(); } 
        if(e.getSource() == save) { save(); }
        if(e.getSource() == clear) { clear(); } 
        if(e.getSource() == exit) {
            System.exit(0); //No need to create method, one line of code.
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        //check the type of ItemEvent
        if(e.getStateChange() == ItemEvent.SELECTED) {
            if(e.getSource() == filterBox)
                display("" + filterBox.getSelectedItem());
            if(e.getSource() == existingTechs) {
                if(!e.getItem().equals(""))
                    prefillTech((Technician) e.getItem());
                else
                    prefillTech("");
            }
        }
    }
    
    public void submit() {
        //check that all fields have input, except sliders as they have defaults
        //unit number is ignored as it's not compulsory.
        if(     streetNoInput.getText().equals("") || 
                addressInput.getText().equals("") ||
                suburbInput.getText().equals("") ||
                postcodeInput.getText().equals("") ||
                countryInput.getText().equals("") ||
                startDateInput.getText().equals("") ||
                endDateInput.getText().equals("") ||
                firstNameInput.getText().equals("") ||
                lastNameInput.getText().equals("") ||
                phoneNumberInput.getText().equals("") ) {
            
            //Error Message when missing input/s
            JOptionPane.showMessageDialog(getContentPane(), 
                    "Please provide a value for all fields.", 
                    "Missing Inputs (Error Code: #1-0-1)", 
                    JOptionPane.ERROR_MESSAGE);
            
        } else {
            //to track whether or not the inputs have successfully validated
            int errorCount = 0;
            
            //Validate and set unit number.
            int unitNo = 0;
            //if unit number contains something ...
            if(!unitNoInput.getText().equals("")) {
                //... check that it only contains numbers
                if(unitNoInput.getText().matches("[0-9]+")) {
                    //now that we know it contains number, set the value
                    unitNo = Integer.parseInt(unitNoInput.getText());
                    //now check that it's a positive value
                    if(unitNo < 1) { 
                        //Display Error Message
                        JOptionPane.showMessageDialog(getContentPane(), 
                                "Unit Number Invalid - Must be greater than 0", 
                                "Invalid input (Error Code: #2-0-1)", 
                                JOptionPane.ERROR_MESSAGE);
                        //track errors to ensure program doesn't save invalid data
                        errorCount += 1;
                    }
                }
                else {
                    //Display Error Message
                    JOptionPane.showMessageDialog(getContentPane(), 
                            "Unit Number Invalid - Must only contain numbers", 
                            "Invalid input (Error Code: #2-0-2)", 
                            JOptionPane.ERROR_MESSAGE);
                    errorCount += 1;  
                }
            }
            
            //Validate and set street number
            int streetNo = 0;
            if(streetNoInput.getText().matches("[0-9]+")) {
                streetNo = Integer.parseInt(streetNoInput.getText());
                //check street number
                if(streetNo < 1) { 
                    //Display Error Message
                    JOptionPane.showMessageDialog(getContentPane(), 
                            "Street Number Invalid - Must be greater than 0", 
                            "Invalid input (Error Code: #2-1-0)", 
                            JOptionPane.ERROR_MESSAGE);
                    //track errors to ensure program doesn't save invalid data
                    errorCount += 1;            
                }
            }
            else {
                //Display Error Message
                JOptionPane.showMessageDialog(getContentPane(), 
                        "Street Number Invalid - Must only contain numbers", 
                        "Invalid input (Error Code: #2-1-2)", 
                        JOptionPane.ERROR_MESSAGE); 
                errorCount += 1;            
            }
            
            //Validate and set address
            String address = addressInput.getText();
            //checkSymbols method returns true if string contains symbols
            if(checkSymbols(address, CheckSymbolsType.NUMBER)) {
                JOptionPane.showMessageDialog(getContentPane(), 
                        "Address Invalid - Must not contain symbols", 
                        "Invalid input (Error Code: #2-2-0)", 
                        JOptionPane.ERROR_MESSAGE);
                //track errors to ensure program doesn't save invalid data
                errorCount += 1;             
            }
            else if(address.length() >= 100) {
                JOptionPane.showMessageDialog(getContentPane(), 
                        "Address Invalid - Must be less than 100 characters", 
                        "Invalid input (Error Code: #2-2-1)", 
                        JOptionPane.ERROR_MESSAGE);
                //track errors to ensure program doesn't save invalid data
                errorCount += 1;            
            }
            
            //Validate and set suburb
            String suburb = suburbInput.getText();
            if(checkSymbols(suburb, CheckSymbolsType.NUMBER)) {
                JOptionPane.showMessageDialog(getContentPane(), 
                        "Suburb Invalid - Must not contain symbols", 
                        "Invalid input (Error Code: #2-3-0)", 
                        JOptionPane.ERROR_MESSAGE);  
                //track errors to ensure program doesn't save invalid data
                errorCount += 1;                
            }
            else if(suburb.length() >= 50) {
                JOptionPane.showMessageDialog(getContentPane(), 
                        "Suburb Invalid - Must be less than 50 characters", 
                        "Invalid input (Error Code: #2-3-1)", 
                        JOptionPane.ERROR_MESSAGE);
                //track errors to ensure program doesn't save invalid data
                errorCount += 1;                  
            }
            
            //Validate and set postcode
            int postcode = 0;
            if(postcodeInput.getText().matches("[0-9]+")) {
                postcode = Integer.parseInt(postcodeInput.getText());
                
                if(postcode < 1000 || postcode > 99999 ) { //1000 to 99999
                    JOptionPane.showMessageDialog(getContentPane(), 
                            "Postcode Invalid - Must be between 4 to 5 digits", 
                            "Invalid input (Error Code: #2-4-0)", 
                            JOptionPane.ERROR_MESSAGE);
                    //track errors to ensure program doesn't save invalid data
                    errorCount += 1;  
                }
            }
            else {
                //Display Error Message
                JOptionPane.showMessageDialog(getContentPane(), 
                        "Postcode Invalid - Must only contain numbers", 
                        "Invalid input (Error Code: #2-4-1)", 
                        JOptionPane.ERROR_MESSAGE);        
                errorCount += 1;             
            }

            //Validate and set country
            String country = countryInput.getText();
            if(checkSymbols(country, CheckSymbolsType.NUMBER)) {
                JOptionPane.showMessageDialog(getContentPane(), 
                        "Country Invalid - Must not contain symbols", 
                        "Invalid input (Error Code: #2-5-0)", 
                        JOptionPane.ERROR_MESSAGE);  
                //track errors to ensure program doesn't save invalid data
                errorCount += 1;                
            }
            else if(country.length() >= 50) {
                JOptionPane.showMessageDialog(getContentPane(), 
                        "Country Invalid - Must be less than 50 characters", 
                        "Invalid input (Error Code: #2-5-1)", 
                        JOptionPane.ERROR_MESSAGE);
                //track errors to ensure program doesn't save invalid data
                errorCount += 1;                  
            }

            //dates already have validation thanks to JFormattedTextField
            String startDate = startDateInput.getText();
            String endDate = endDateInput.getText();
            
            //These don't need validation thanks to JSliders and default values
            int hp = hpInput.getValue();
            int zones = zonesInput.getValue();
            int outlets = outletsInput.getValue();

            String firstName = firstNameInput.getText();
            if(checkSymbols(firstName, CheckSymbolsType.NUMBER)) { //use method to check for symbols in String
                JOptionPane.showMessageDialog(getContentPane(), 
                        "First Name Invalid - Must not contain symbols", 
                        "Invalid input (Error Code: #2-6-0)", 
                        JOptionPane.ERROR_MESSAGE);  
                //track errors to ensure program doesn't save invalid data
                errorCount += 1;                
            }
            else if(firstName.length() >= 50) { //check length
                JOptionPane.showMessageDialog(getContentPane(),
                        "First Name Invalid - Must be less than 50 characters",
                        "Invalid input (Error Code: #2-6-1)",
                        JOptionPane.ERROR_MESSAGE);
                //track errors to ensure program doesn't save invalid data
                errorCount += 1;
            }
            
            String lastName = lastNameInput.getText();
            if(checkSymbols(lastName, CheckSymbolsType.NUMBER)) {
                JOptionPane.showMessageDialog(getContentPane(), 
                        "Last Name Invalid - Must not contain symbols", 
                        "Invalid input (Error Code: #2-7-0)", 
                        JOptionPane.ERROR_MESSAGE);  
                //track errors to ensure program doesn't save invalid data
                errorCount += 1;                
            }
            else if(lastName.length() >= 50) {
                JOptionPane.showMessageDialog(getContentPane(),
                        "Last Name Invalid - Must be less than 50 characters",
                        "Invalid input (Error Code: #2-7-1)",
                        JOptionPane.ERROR_MESSAGE);
                //track errors to ensure program doesn't save invalid data
                errorCount += 1;
            }
            
            String phoneNumber = phoneNumberInput.getText();
            if(checkSymbols(phoneNumber, CheckSymbolsType.PHONE)) {
                JOptionPane.showMessageDialog(getContentPane(), 
                        "Phone Number Invalid - Must not contain symbols", 
                        "Invalid input (Error Code: #2-8-0)", 
                        JOptionPane.ERROR_MESSAGE);  
                //track errors to ensure program doesn't save invalid data
                errorCount += 1;                
            }
            else if(phoneNumber.length() >= 25) {
                JOptionPane.showMessageDialog(getContentPane(),
                        "Phone Number Invalid - Must be less than 25 characters",
                        "Invalid input (Error Code: #2-8-1)",
                        JOptionPane.ERROR_MESSAGE);
                //track errors to ensure program doesn't save invalid data
                errorCount += 1;
            }
            for(Technician t : technicians) {
                if(firstName.equals(t.getFirstName()) &&
                   lastName.equals(t.getLastName()))
                    if(!phoneNumber.equals(t.getPhoneNumber())) {
                        JOptionPane.showMessageDialog(getContentPane(),
                                "Technician Already Exists - with different Phone Number",
                                "Invalid input (Error Code: #2-8-2)",
                                JOptionPane.ERROR_MESSAGE);
                        //track errors to ensure program doesn't save invalid data
                        errorCount += 1;
                    }
            }
            
            /*
             * Now that we've validated all fields, let's check for any errors
             * If error count is 0, then the data must be valid, so let's display
             * the values.
             */
            if(errorCount == 0) {
                //Check whether or not unit number was provided and invoke 
                //relevant constructor
                if(!unitNoInput.getText().equals("")) {
                    buildings.add(new Building(unitNo, streetNo, address, 
                            suburb, postcode, country));
                }
                else {
                    buildings.add(new Building(streetNo, address, 
                            suburb, postcode, country));
                }
                
                //Construct installation and add to ArrayList
                installations.add(new Installation(startDate, endDate, 
                        hp, zones, outlets));
                
                //Construct technician and add to ArrayList
                technicians.add(new Technician(firstName, lastName, 
                        phoneNumber));
                
                display("" + filterBox.getSelectedItem()); //Call method to display the data
                clear(); //Call method to clear the inputs
                save(); //Automatically save the data. 
                /*
                 * Because the save method is called whenever a user submits data
                 * and the data is loaded as soon as application launched, there 
                 * is minimal usage for the Load and Save buttons. The only reason
                 * the user will need the Load button is if they are editing the
                 * text file directly whilst the application is running (which works).
                 * 
                 * However, I know users generally like to be able to click Save,
                 * even where their information is auto-saved. For best practices,
                 * I will leave the Save button in the application for design reasons.
                 */
            }
        }
    }
    
    public void load() { //Method to load the data from database text file
        clearArrays(); //to avoid duplication
        
        try(BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String lineText = ""; //just one line of text at a time
            while((lineText = in.readLine()) != null) {
                //split line based on delimiter value
                String[] key = lineText.split(DELIMITER);
                
                //initialise values from split line
                int unitNo = Integer.parseInt(key[0]);
                int streetNo = Integer.parseInt(key[1]);
                String address = key[2];
                String suburb = key[3];
                int postcode = Integer.parseInt(key[4]);
                String country = key[5];
                
                //determine if unit number exists and evoke correct constructor
                if(unitNo != 0) {
                    buildings.add(new Building(unitNo, streetNo, address, 
                            suburb, postcode, country));
                }
                else {
                    buildings.add(new Building(streetNo, address, 
                            suburb, postcode, country));
                }
                
                //get installation data from split string, as we know index
                String startDate = key[6];
                String endDate = key[7];
                int hp = Integer.parseInt(key[8]);
                int zones = Integer.parseInt(key[9]);
                int outlets = Integer.parseInt(key[10]);
                
                //construct installation
                installations.add(new Installation(startDate, endDate, 
                        hp, zones, outlets));
                
                //extract technician details from split string
                String firstName = key[11];
                String lastName = key[12];
                String phoneNumber = key[13];
                
                //construct technician
                technicians.add(new Technician(firstName, lastName, 
                        phoneNumber));
            }
            display("" + filterBox.getSelectedItem()); //call display method to show constructed objects
            in.close();
        }
        catch(FileNotFoundException e) {
            //File will not be created until user first selects save button.
            //This error will show if database text file not found.
            JOptionPane.showMessageDialog(null,
                    "File Not Found, will create once data saved",
                    "Read Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException e) {
            e.printStackTrace(); //to help diagnose cause of exception if occurs.
        }
    }
    
    public void save() {
        try(FileWriter fileWriter = new FileWriter(fileName)) {
            String textToFile = ""; //initialise string for file writing
            
            //All arrays will be same length, use buildings to determine size
            for(int i = 0; i < buildings.size(); i++) { //iterate through elements
                //append data for each element to the output string with a delimiter ","
                textToFile += buildings.get(i).getUnitNo() + DELIMITER;
                textToFile += buildings.get(i).getStreetNo() + DELIMITER;
                textToFile += buildings.get(i).getAddress() + DELIMITER;
                textToFile += buildings.get(i).getSuburb() + DELIMITER;
                textToFile += buildings.get(i).getPostcode() + DELIMITER;
                textToFile += buildings.get(i).getCountry() + DELIMITER;
                textToFile += installations.get(i).getStartDate() + DELIMITER;
                textToFile += installations.get(i).getEndDate() + DELIMITER;
                textToFile += installations.get(i).getHorsePower()+ DELIMITER;
                textToFile += installations.get(i).getZones() + DELIMITER;
                textToFile += installations.get(i).getOutlets()+ DELIMITER;
                textToFile += technicians.get(i).getFirstName() + DELIMITER;
                textToFile += technicians.get(i).getLastName() + DELIMITER;
                textToFile += technicians.get(i).getPhoneNumber() + "\n";
                //last data piece uses linebreak instead of delimiter.
            }
            fileWriter.write(textToFile); //send string to text file.
        }
        catch(IOException e) {
            e.printStackTrace(); //help diagnose exceptions if & when they occur.
        }
    }
    
    public void clear() {
        //removing all entered text or returning to default
        unitNoInput.setText("");
        streetNoInput.setText("");
        addressInput.setText("");
        suburbInput.setText("");
        postcodeInput.setText("");
        countryInput.setText("");

        startDateInput.setText("");
        endDateInput.setText("");

        //reset to default values
        hpInput.setValue(100);
        zonesInput.setValue(2);
        outletsInput.setValue(5);
        
        firstNameInput.setText("");
        lastNameInput.setText("");
        phoneNumberInput.setText("");
        existingTechs.setSelectedIndex(0);
    }
    
    public void clearArrays() {
        //remove all elements from ArrayList, to avoid duplication between
        //submitting new elements and loading elements.
        buildings.clear();
        installations.clear();
        technicians.clear(); 
    }
    
    public void prefillTech(Technician t) {
        // Add technician data when existing technician selected.
        firstNameInput.setText(t.getFirstName());
        lastNameInput.setText(t.getLastName());
        phoneNumberInput.setText(t.getPhoneNumber());
        firstNameInput.setEditable(false);
        lastNameInput.setEditable(false);
        phoneNumberInput.setEditable(false);
    }
    
    public void prefillTech(String t) {
        // Removes prefill data when "" selected.
        firstNameInput.setText(t);
        lastNameInput.setText(t);
        phoneNumberInput.setText(t);
        firstNameInput.setEditable(true);
        lastNameInput.setEditable(true);
        phoneNumberInput.setEditable(true);
    }
    
    public void display(String selection) {
        String buildingOutput = ""; //String for building data
        String installationOutput = ""; //String for installation data
        String technicianOutput = ""; //String for technician data
        String underline = "";
        for(int i = 0; i < 55; i ++)
            underline += "-";
        
        if(!selection.equals("")) {
            //Loop through all elements and display only if matching technicians name.
            for(int lcv = 0; lcv < buildings.size(); lcv ++) {
                if(technicians.get(lcv).getFirstName().equals(selection)) {
                    buildingOutput += buildings.get(lcv).toString();
                    installationOutput += installations.get(lcv).toString();
                    technicianOutput += technicians.get(lcv).toString(1);
                }
            }
        }
        else {
            for(int lcv = 0; lcv < buildings.size(); lcv ++) {
                buildingOutput += buildings.get(lcv).toString();
                installationOutput += installations.get(lcv).toString();
                technicianOutput += technicians.get(lcv).toString(1);
            }
        }
        
        for(Technician t : technicians) {
            // Add first names to Technician Filter Box.
            if(!technicianNames.contains(t.getFirstName())) {
                technicianNames.add(t.getFirstName());
                filterBox.addItem(t.getFirstName());
            }
            
            // Add technicians to existing technicians list and selector.
            if(!technicianNumbers.contains(t.getPhoneNumber())) {
                technicianNumbers.add(t.getPhoneNumber());
                existingTechs.addItem(t);
            }
        }
        
        //add text to output pane. 
        buildingOutputPane.setText("Building\n" + underline + buildingOutput);
        installationOutputPane.setText("Installation\n" + underline + installationOutput);
        technicianOutputPane.setText("Technician\n" + underline + technicianOutput);
    }
        
    public boolean checkSymbols(String str, CheckSymbolsType variant) {
        switch(variant) {
            case NUMBER:
                return // Return true if str contains any of these symbols
                    str.contains("/") || str.contains("?") || str.contains("!") || 
                    str.contains("@") || str.contains("~") || str.contains("`") || 
                    str.contains("#") || str.contains("$") || str.contains("%") || 
                    str.contains("^") || str.contains("&") || str.contains("*") || 
                    str.contains("(") || str.contains(")") || str.contains("_") || 
                    str.contains("+") || str.contains("=") || str.contains("[") || 
                    str.contains("]") || str.contains("{") || str.contains("}") || 
                    str.contains("|") || str.contains(";") || str.contains(":") || 
                    str.contains("<") || str.contains(">") || 
                    str.contains("\\") || str.contains("\"") || str.contains("\'");
            case PHONE:
                return // Return true if str contains any of these symbols
                    str.contains("/") || str.contains("?") || str.contains("!") || 
                    str.contains("@") || str.contains("~") || str.contains("`") || 
                    str.contains("#") || str.contains("$") || str.contains("%") || 
                    str.contains("^") || str.contains("&") || str.contains("*") || 
                    str.contains("_") || str.contains("=") || str.contains("[") || 
                    str.contains(">") || str.contains("<") || str.contains("|") ||
                    str.contains("]") || str.contains("{") || str.contains("}") || 
                    str.contains(";") || str.contains(":") ||
                    str.contains("\\") || str.contains("\"") || str.contains("\'");
            default:
                return true; //should never happen, but to satisfy java
        }
    }
}
