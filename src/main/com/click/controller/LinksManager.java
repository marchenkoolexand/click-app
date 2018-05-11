package main.com.click.controller;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class LinksManager {

    final static Logger logger = Logger.getLogger(LinksManager.class);

    private List<String> stringList = new ArrayList<String>();
    private String pathToLinks = "resources/links.txt";

    public LinksManager() throws FileNotFoundException {
        this.stringList = readLinkFromFile();
    }


    private List<String> readLinkFromFile() throws FileNotFoundException {
        logger.info("Reading links from file, path: "+ pathToLinks);
        File file = new File(pathToLinks);
        Scanner input = new Scanner(file);
        List<String> list = new ArrayList<String>();
        while (input.hasNextLine()) {
            list.add(input.nextLine());
        }
        Collections.shuffle(list);
        return list;
    }

    public List<String> getLinks() {
        return stringList;
    }

    public String getRandomLink() {
        int minimum = 0;
        int maximum = 5;
        Random rand = new Random();
        int randomNum = minimum + rand.nextInt((maximum - minimum) + 1);
        System.out.println("Return link with index " + randomNum);
        return stringList.get(randomNum);
    }

}
