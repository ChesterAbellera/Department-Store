package com.example.app.model;

import java.util.List;
import java.util.Scanner;

// This GIT shit works, woohooooo!
public class MainApp {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Model model = Model.getInstance();

        int option;
        System.out.println("Greetings User! =D");
        System.out.println("What would you like to do ?");
        System.out.println("___________________________");
        System.out.println("Just to name a few of your options,");
        System.out.println("you can : ");
        System.out.println();
        System.out.println();

        do {
            System.out.println("1. Create a new Shop");
            System.out.println("2. Delete an existing Shop");
            System.out.println("3. Edit existing Shop(s)");
            System.out.println("4. View all Shops");
            System.out.println("5. Exit");
            System.out.println();

            System.out.print("Enter option: ");
            String line = keyboard.nextLine();
            option = Integer.parseInt(line);

            System.out.println("You chose option : " + option);
            System.out.println();
            switch (option) {
                case 1: {
                    System.out.println("Creating Shop");
                    System.out.println("_____________");
                    System.out.println();
                    createShop(keyboard, model);

                    break;
                }
                case 2: {
                    System.out.println("Deleting Shop");
                    System.out.println("_____________");
                    System.out.println();
                    deleteShop(keyboard, model);

                    break;
                }
                case 3: {
                    System.out.println("Editing Shop");
                    System.out.println("_______________");
                    System.out.println();
                    editShop(keyboard, model);
                    break;
                }
                case 4: {
                    System.out.println("Viewing all Shops");
                    System.out.println("_________________");
                    System.out.println();
                    viewShops(model);
                    break;
                }
            }
        } while (option != 5);
        System.out.println("Goodbye! =)");
    }
    
    
    
    
    /* --------------------------------------------------------------------- */




    // CODE TO CREATE A SHOP
    private static void createShop(Scanner keyb, Model mdl) {
        Shop s = readShop(keyb);
        if (mdl.addShop(s)) {
            System.out.println("Shop was successfully added!");
            System.out.println();
        } else {
            System.out.println("Shop not added to database.");
            System.out.println();
        }
        System.out.println();
    }

    // CODE TO DELETE AN EXISTING SHOP
    private static void deleteShop(Scanner keyboard, Model model) {
        System.out.println("Enter the ID of the Shop that you wish to delete :");
        int shopID = Integer.parseInt(keyboard.nextLine());
        Shop s;

        s = model.findShopByShopId(shopID);
        if (s != null) {
            if (model.removeShop(s)) {
                System.out.println("Shop Deleted");
                System.out.println();
            } else {
                System.out.println("Shop was not deleted");
                System.out.println();
            }
        } else {
            System.out.println("Shop not found");
            System.out.println();
        }
    }

    // CODE TO EDIT AN EXISTING SHOP IN THE TABLE
    private static void editShop(Scanner kb, Model m) {
        System.out.println("Enter the ID of the Shop that you wish to edit :");
        int shopID = Integer.parseInt(kb.nextLine());
        Shop s;

        s = m.findShopByShopId(shopID);
        if (s != null) {
            editShopDetails(kb, s);
            if (m.updateShop(s)) {
                System.out.println("Shop updated");
                System.out.println();
            } else {
                System.out.println("Shop was not updated");
                System.out.println();
            }
        } else {
            System.out.println("Shop not found");
            System.out.println();
        }
    }

    // CODE TO VIEW ALL EXISTING SHOPS IN TABLES
    private static void viewShops(Model mdl) {
        List<Shop> shops = mdl.getShops();
        System.out.println();
        if (shops.isEmpty()) {
            System.out.println("Sorry, there were no shops found in the database.");
        } else {
            System.out.printf("%-50s %-25s %-15s %-20s %-17s %16s %19s\n", "Address:", "Shop Manager Name:", "Phone Number:", "Date Opened:", "Url:", "Shop ID:", "Region Number:");
            System.out.println();
            for (Shop sh : shops) {
                System.out.printf("%-50s %-25s %-15s %-20s %-20s %11d %13d\n",
                        sh.getAddress(),
                        sh.getShopmanagername(),
                        sh.getPhonenumber(),
                        sh.getDateopened(),
                        sh.getUrl(),
                        sh.getShopID(),
                        sh.getRegionnumber()
                );
            }
            System.out.println();
            System.out.println();
            System.out.println();

        }
    }

    // CODE TO READ IN A NEW INPUT TO CREATE A NEW ROW OF DATA INTO THE TABLE
    private static Shop readShop(Scanner keyb) {
        String address, shopmanagername, phonenumber, dateopened, url;
        int shopID, regionnumber;
        String line;

        address = getString(keyb, "Enter Shop Address : ");
        shopmanagername = getString(keyb, "Enter Shop Manager Name : ");
        phonenumber = getString(keyb, "Enter Shop Phone Number : ");
        dateopened = getString(keyb, "Enter Shop Opening Date (YYYY-MM-DD) : ");
        url = getString(keyb, "Enter Shop URL Address : ");
        line = getString(keyb, "Enter Shop Region Number : ");
        regionnumber = Integer.parseInt(line);

        Shop sh = new Shop(
                address,
                shopmanagername,
                phonenumber,
                dateopened,
                url,
                regionnumber
        );

        return sh;
    }

    private static String getString(Scanner keyboard, String prompt) {
        System.out.println(prompt);
        return keyboard.nextLine();
    }

    // CODE TO UPDATE THE TABLE ACCOMPANIED BY A VIEW OF PREVIOUSLY STORED DETAILS
    private static void editShopDetails(Scanner keyb, Shop s) {
        String address, shopmanagername, phonenumber,  url, dateopened;
        int shopID, regionnumber;
        String line1, line2;

        address = getString(keyb, "Enter Shop Address [" + s.getAddress() + "]: ");
        shopmanagername = getString(keyb, "Enter Shop Manager Name [" + s.getShopmanagername() + "]: ");
        phonenumber = getString(keyb, "Enter Shop Phone Number [" + s.getPhonenumber() + "]: ");
        url = getString(keyb, "Enter Shop URL Address [" + s.getUrl() + "]: ");
        dateopened = getString(keyb, "Enter Shop Opening Date [" + s.getDateopened() + "]: ");
        line2 = getString(keyb, "Enter Shop Region [" + s.getRegionnumber() + "]: ");
        if (address.length() != 0) {
            s.setAddress(address);
        }
        if (shopmanagername.length() != 0) {
            s.setShopmanagername(shopmanagername);
        }
        if (phonenumber.length() != 0) {
            s.setPhonenumber(phonenumber);
        }
        if (url.length() != 0) {
            s.setUrl(url);
        }
        if (dateopened.length() != 0) {
            s.setDateopened(dateopened);
        }
        if (line2.length() != 0) {
            regionnumber = Integer.parseInt(line2);
            s.setRegionnumber(regionnumber);
        }

    }

}