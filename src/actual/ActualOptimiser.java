package actual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import common.Optimiser;
import common.TabularData;

public class ActualOptimiser implements Optimiser {

    public class Book implements Comparable {
        int ID;
        int value;

        public Book(int ID, int value) {
            this.ID = ID;
            this.value = value;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Book) {
                return Integer.compare(((Book) o).value, this.value);
            }
            return 0;
        }
    }

    public class Library implements Comparable<Library> {
        int numBooks;
        int numDays;
        int numShip;
        int totalVal;
        int ID;
        int numUnique;
        ArrayList<Book> books;
        ArrayList<Integer> bookIDs;

        public void calculateTotalVal() {
            totalVal = 0;
            for (Book b : books) {
                totalVal += b.value;
            }
        }

        public void calculateUniqueBooks() {
            ArrayList<Book> uniqueBooks = new ArrayList<Book>();
            for (Book b : books) {
                uniqueBooks.add(b);
            }
            for (Library l : libraries) {
                for (Book b : l.books) {
                    if (uniqueBooks.contains(b)) {
                        uniqueBooks.remove(b);
                    }
                }
            }
            numUnique = uniqueBooks.size();
        }

        public void sortBooks() {
            Collections.sort(books);
        }

        @Override
        public int compareTo(Library l) {
            return Integer.compare((int) (Math.pow(l.totalVal * l.numShip, .5) / Math.pow(l.numDays, 2)), (int) (Math.pow(this.totalVal
                    * this.numShip, .5) / Math.pow(this.numDays, 2)));
        }
    }

    int numDifferentBooks;
    int numLibraries;
    int maxDays;
    ArrayList<Integer> bookVals;
    ArrayList<Library> libraries;

    @Override
    public TabularData optimise(TabularData inputData) {
        init(inputData);
        inputData.clear();
        return algorithm1();
    }

    public void init(TabularData inputData) {
        libraries = new ArrayList<>();
        numDifferentBooks = inputData.getElementAsInt(0, 0);
        numLibraries = inputData.getElementAsInt(0, 1);
        maxDays = inputData.getElementAsInt(0, 2);
        bookVals = inputData.getRowAsIntList(1);
        for (int i = 2; i < (numLibraries * 2) + 2; i += 2) {
            Library l = new Library();

            l.numBooks = inputData.getElementAsInt(i, 0);
            l.numDays = inputData.getElementAsInt(i, 1);
            l.numShip = inputData.getElementAsInt(i, 2);
            l.bookIDs = inputData.getRowAsIntList(i + 1);
            l.books = new ArrayList<>();
            l.ID = libraries.size();

            for (int id : l.bookIDs) {
                l.books.add(new Book(id, bookVals.get(id)));
            }

            l.calculateTotalVal();
            l.sortBooks();
            libraries.add(l);
        }

        System.out.println(numDifferentBooks + " " + numLibraries + " " + maxDays);
        System.out.println();

    }

    @SuppressWarnings("unchecked")
    public TabularData algorithm1() {
        Collections.sort(libraries);
        ArrayList<Library> signupOrd = new ArrayList<>();
        int days = 0;
        int i = 0;
        while (days < maxDays && i < libraries.size()) {
            Library l = libraries.get(i++);
            int x = l.numDays;
            if (x + days <= maxDays) {
                days += x;
                signupOrd.add(l);
            } else {
                break;
            }
        }
        if (signupOrd.size() > 0) {
            i = signupOrd.get(0).numDays;
            int next = 1;
            ArrayList<Library> signedUp = new ArrayList<>();
            HashMap<Integer, HashSet<String>> booksShipped = new HashMap<>();
            HashSet<Integer> booksShippedIDs = new HashSet<>();
            signedUp.add(signupOrd.get(0));
            for (int d = i; d < maxDays; d++) {
                if (next < signupOrd.size()) {
                    if (d >= i + signupOrd.get(next).numDays) {
                        i += signupOrd.get(next).numDays;
                        signedUp.add(signupOrd.get(next++));
                    }
                }
                for (Library l : signedUp) {
                    int ableToShip = l.numShip;
                    int shipped = 0;
                    while (shipped < ableToShip && l.bookIDs.size() > 0) {
                        int id = l.bookIDs.remove(0);
                        if (!booksShippedIDs.contains(id)) {
                            if (!booksShipped.containsKey(l.ID)) {
                                booksShipped.put(l.ID, new HashSet<String>());
                            }
                            booksShipped.get(l.ID).add("" + id);
                            booksShippedIDs.add(id);
                            shipped++;
                        }
                    }
                }

                for (int j = signedUp.size() - 1; j >= 0; j--) {
                    if (!booksShipped.containsKey(signedUp.get(j).ID)) {
                        signedUp.remove(j);
                    }
                }
            }
            // Step 4: Profit?
            TabularData result = new TabularData();

            ArrayList<String> line1 = new ArrayList<>();
            line1.add("" + signedUp.size());
            result.addRow(line1);

            for (Library l : signedUp) {
                ArrayList<String> infoLine = new ArrayList<>();
                infoLine.add("" + l.ID);
                infoLine.add("" + booksShipped.get(l.ID).size());
                result.addRow(infoLine);

                ArrayList<String> bookLine = new ArrayList<>();
                bookLine.addAll(booksShipped.get(l.ID));
                result.addRow(bookLine);
            }

            return result;
        }

        return new TabularData();
    }
}
