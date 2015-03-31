package com.example.app.model;

import java.util.List;
import java.util.Scanner;

// This GIT stuff works, woohooooo!
public class MainApp {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Model model = Model.getInstance();

        int option;
        System.out.println("Greetings, User! =D");
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
            System.out.println("4. View all existing Shops");
            System.out.println();
            System.out.println("5. Create a new Region");
            System.out.println("6. Delete an existing Region");
            System.out.println("7. Edit existing Region(s)");
            System.out.println("8. View all existing Regions");
            System.out.println();
            System.out.println("9. Exit");
            System.out.println();

            System.out.print("Enter option: ");
            String line = keyboard.nextLine();
            option = Integer.parseInt(line);

            System.out.println("You chose option : " + option);
            System.out.println();
            switch (option) {
                case 1: {
                    System.out.println("--- Creating Shop ---");
                    System.out.println("_____________");
                    System.out.println();
                    createShop(keyboard, model);

                    break;
                }
                case 2: {
                    System.out.println("--- Deleting Shop ---");
                    System.out.println("_____________");
                    System.out.println();
                    deleteShop(keyboard, model);

                    break;
                }
                case 3: {
                    System.out.println("--- Editing Shop ---");
                    System.out.println("_______________");
                    System.out.println();
                    editShop(keyboard, model);
                    break;
                }
                case 4: {
                    System.out.println("--- Viewing all Shops ---");
                    System.out.println("_________________");
                    System.out.println();
                    viewShops(model);
                    break;
                }
                case 5: {
                    System.out.println("--- Creating Region ---");
                    System.out.println("_____________");
                    System.out.println();
                    createRegion(keyboard, model);

                    break;
                }
                case 6: {
                    System.out.println("--- Deleting Region ---");
                    System.out.println("_____________");
                    System.out.println();
                    deleteRegion(keyboard, model);

                    break;
                }
                case 7: {
                    System.out.println("--- Editing Region ---");
                    System.out.println("_______________");
                    System.out.println();
                    editRegion(keyboard, model);
                    break;
                }
                case 8: {
                    System.out.println("--- Viewing all Regions ---");
                    System.out.println("_________________");
                    System.out.println();
                    viewRegions(model);
                    break;
                }
            }
        } while (option != 9);
        System.out.println("Goodbye! =)");
    }

    /* --------------------------------------------------------------------- */
    private static void createShop(Scanner keyb, Model mdl) {
        Shop s = readShop(keyb);
        if (mdl.addShop(s)) {
            System.out.println("Shop was successfully added!");
            System.out.println();
        } else {
            System.out.println("*** Shop not added to database ***");
            System.out.println();
        }
        System.out.println();
    }

    private static void deleteShop(Scanner keyboard, Model model) {

        try {
            System.out.println("Enter the ID of the Shop that you wish to delete :");
            int shopID = Integer.parseInt(keyboard.nextLine());
            Shop s;

            s = model.findShopByShopId(shopID);
            if (s != null) {
                if (model.removeShop(s)) {
                    System.out.println("Shop was deleted successfully!");
                    System.out.println();
                } else {
                    System.out.println("*** Shop was not deleted ***");
                    System.out.println();
                }
            } else {
                System.out.println("*** Sorry, there were no shops found with that specific ID ***");
                System.out.println();
            }
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("*** Number format exception: " + e.getMessage() + " ***");
            System.out.println();
        }
    }

    private static void editShop(Scanner kb, Model m) {

        try {
            System.out.println("Enter the ID of the Shop that you wish to edit :");
            int shopID = Integer.parseInt(kb.nextLine());
            Shop s;

            s = m.findShopByShopId(shopID);
            if (s != null) {
                editShopDetails(kb, s);
                if (m.updateShop(s)) {
                    System.out.println("Shop details were updated successfully!");
                    System.out.println();
                } else {
                    System.out.println("*** Shop was not updated ***");
                    System.out.println();
                }
            } else {
                System.out.println("*** Sorry, there were no shops found that specific ID ***");
                System.out.println();
            }
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("*** Number format exception: " + e.getMessage() + " ***");
            System.out.println();
        }
    }

    private static void viewShops(Model mdl) {
        List<Shop> shops = mdl.getShops();
        System.out.println();
        if (shops.isEmpty()) {
            System.out.println("*** Sorry, there were no shops found in the database. ***");
        } else {
            System.out.printf("%-50s %-25s %-15s %-20s %-35s %-15s %-15s\n", "Shop Address:", "Shop Manager Name:", "Phone Number:", "Date Opened:", "Url:", "Shop ID:", "Region Number:");
            System.out.println();
            for (Shop sh : shops) {
                System.out.printf("%-50s %-25s %-15s %-20s %-35s %-15d %-15d\n",
                        sh.getShopAddress(),
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

    private static Shop readShop(Scanner keyb) {
        String shopaddress, shopmanagername, phonenumber, dateopened, url;
        int regionnumber;
        String line;

        shopaddress = getString(keyb, "Enter Shop Address : ");
        shopmanagername = getString(keyb, "Enter Shop Manager Name : ");
        phonenumber = getString(keyb, "Enter Shop Phone Number : ");
        dateopened = getString(keyb, "Enter Shop Opening Date (YYYY-MM-DD) : ");
        url = getString(keyb, "Enter Shop URL Address : ");
        line = getString(keyb, "Enter Shop Region Number : ");
        regionnumber = Integer.parseInt(line);

        Shop sh = new Shop(
                shopaddress,
                shopmanagername,
                phonenumber,
                dateopened,
                url,
                regionnumber
        );

        return sh;
    }

    private static void editShopDetails(Scanner keyb, Shop s) {
        String shopaddress, shopmanagername, phonenumber, url, dateopened;
        int regionnumber;
        String line2;

        shopaddress = getString(keyb, "Enter Shop Address [" + s.getShopAddress() + "]: ");
        shopmanagername = getString(keyb, "Enter Shop Manager Name [" + s.getShopmanagername() + "]: ");
        phonenumber = getString(keyb, "Enter Shop Phone Number [" + s.getPhonenumber() + "]: ");
        url = getString(keyb, "Enter Shop URL Address [" + s.getUrl() + "]: ");
        dateopened = getString(keyb, "Enter Shop Opening Date [" + s.getDateopened() + "]: ");
        line2 = getString(keyb, "Enter Shop Region [" + s.getRegionnumber() + "]: ");
        if (shopaddress.length() != 0) {
            s.setShopAddress(shopaddress);
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

    /* REGIONS SECTION !!! */
    private static void createRegion(Scanner keyb, Model mdl) {
        Region r = readRegion(keyb);
        if (mdl.addRegion(r)) {
            System.out.println("Region was successfully added!");
            System.out.println();
        } else {
            System.out.println("*** Region was not added to database ***");
            System.out.println();
        }
        System.out.println();
    }

    private static void deleteRegion(Scanner keyboard, Model model) {
        try {
            System.out.println("Enter the Region Number of the Region that you wish to delete :");
            int regionnumber = Integer.parseInt(keyboard.nextLine());
            Region r;

            r = model.findRegionByRegionNumber(regionnumber);
            if (r != null) {
                if (model.removeRegion(r)) {
                    System.out.println("Region was deleted successfully!");
                    System.out.println();
                } else {
                    System.out.println("*** Region was not deleted ***");
                    System.out.println();
                }
            } else {
                System.out.println("*** Sorry, there were no regions found with that specific ID ***");
                System.out.println();
            }
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("*** Number format exception: " + e.getMessage() + " ***");
            System.out.println();
        }
    }

    private static void editRegion(Scanner kb, Model m) {
        try {
            System.out.println("Enter the Region Number of the Region that you wish to edit :");
            int regionnumber = Integer.parseInt(kb.nextLine());
            Region r;

            r = m.findRegionByRegionNumber(regionnumber);
            if (r != null) {
                editRegionDetails(kb, r);
                if (m.updateRegion(r)) {
                    System.out.println("Region updated");
                    System.out.println();
                } else {
                    System.out.println("*** Region was not updated ***");
                    System.out.println();
                }
            } else {
                System.out.println("*** Region not found ***");
                System.out.println();
            }
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("*** Number format exception: " + e.getMessage() + " ***");
            System.out.println();
        }
    }

    private static void viewRegions(Model mdl) {
        List<Region> regions = mdl.getRegions();
        System.out.println();
        if (regions.isEmpty()) {
            System.out.println("Sorry, there were no regions found in the database.");
        } else {
            System.out.printf("%-25s %-35s %-25s %-25s %-35s\n", "Region Number:", "Region Name:", "Region Manager Name:", "Phone Number:", "Region Email Address");
            System.out.println();
            for (Region re : regions) {
                System.out.printf("%-25d %-35s %-25s %-25s %-35s\n",
                        re.getRegionnumber(),
                        re.getRegionname(),
                        re.getRegionalmanager(),
                        re.getPhonenumber(),
                        re.getEmail()
                );
            }
            System.out.println();
            System.out.println();
            System.out.println();

        }
    }

    private static Region readRegion(Scanner keyb) {
        String regionname, regionalmanager, phonenumber, email;

        regionname = getString(keyb, "Enter Region Name : ");
        regionalmanager = getString(keyb, "Enter Region Manager Name : ");
        phonenumber = getString(keyb, "Enter Region Phone Number : ");
        email = getString(keyb, "Enter Region Email Address : ");

        Region re = new Region(
                regionname,
                regionalmanager,
                phonenumber,
                email
        );

        return re;
    }

    private static void editRegionDetails(Scanner keyb, Region r) {
        String regionname, regionalmanager, phonenumber, email;

        regionname = getString(keyb, "Enter Region Name [" + r.getRegionname());
        regionalmanager = getString(keyb, "Enter Region Manager Name [" + r.getRegionalmanager() + "]: ");
        phonenumber = getString(keyb, "Enter Region Phone Number [" + r.getPhonenumber() + "]: ");
        email = getString(keyb, "Enter Region Email Address [" + r.getEmail() + "]: ");

        if (regionname.length() != 0) {
            r.setRegionname(regionname);
        }
        if (regionalmanager.length() != 0) {
            r.setRegionalmanager(regionalmanager);
        }
        if (phonenumber.length() != 0) {
            r.setPhonenumber(phonenumber);
        }
        if (email.length() != 0) {
            r.setEmail(email);
        }

    }

    private static String getString(Scanner keyboard, String prompt) {
        System.out.println(prompt);
        return keyboard.nextLine();
    }
}