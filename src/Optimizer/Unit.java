package Optimizer;

import java.util.ArrayList;

/**
 * Author: Jarrud Diercks, Zach Ellefson, Seema Mane, Benjamin Menning
 *
 * Date: 04/26/2016
 *
 * Course: CS 410 - 01, Spring 2016
 *
 * Assignment: Software Engineering Project
 *
 * Description: This program is a shelving space management program that
 * utilizes an SQLite relational database management system to allow users to
 * input and view information about stores and products, and then uses that
 * information to optimize the shelving space utilized for each product within a
 * store. It allows users to add and remove products or stores and also allows a
 * user to optimize the product layout for a store and display a report
 * containing the layout information.
 */
/**
 * This class contains a main method that allows a user to run the Optimizer
 * shelving space management program.
 *
 * @author Jarrud Diercks, Zach Ellefson, Seema Mane, Benjamin Menning
 * @version 04/26/2016
 */
public class Unit {

    ArrayList<Shelf> shelves = new ArrayList<Shelf>(4);
    int unitNumber;
    int shelfOne = 1;
    int shelfTwo = 2;
    int shelfThree = 3;
    int shelfFour = 4;
    double shelfHeight = 0;
    double tempHight = 0;
    double OneShelfSize = 2;

    public Unit(int unitNumber) {
        this.unitNumber = unitNumber;

    }

    public Unit() {
        shelves.add(new Shelf(unitNumber, shelfOne));
        shelves.add(new Shelf(unitNumber, shelfTwo));
        shelves.add(new Shelf(unitNumber, shelfThree));
        shelves.add(new Shelf(unitNumber, shelfFour));

    }

    public void fillShelf(ArrayList<Product> heightList) {
        for (Shelf shelf : shelves) {

            if (shelfHeight < 84) {
                shelfHeight = shelf.fillShelfByHeight(heightList, tempHight);
                if (shelfHeight < 0) {
                    break;
                }
                tempHight += (shelfHeight + OneShelfSize);

            }//end if
            else {
                break;
            }

        }//end for
        unitNumber += 1;
    }

    public ArrayList<Shelf> getShelves() {

        return shelves;

    }

    public void setShelves(ArrayList<Shelf> shelves) {
        this.shelves = shelves;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    @Override
    public String toString() {

        String returnString = "";

        for (Shelf shelf : shelves) {
            returnString += "\n\nUnit: " + unitNumber + "\n" + shelf;
        }

        return returnString;
    }
}
