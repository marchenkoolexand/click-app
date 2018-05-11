package main.com.click;

import main.com.click.controller.LinksManager;
import main.com.click.controller.TorController;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int VIEW = 1;
    private static final int LIKE = 2;

    final static Logger logger = Logger.getLogger(Main.class);

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        logger.info("Click app has been started!");
        TorController controller = new TorController();
        LinksManager manager = new LinksManager();
        List<String> links = manager.getLinks();
        Scanner sc = new Scanner(System.in);
        printSelectMenu();
        int mode = sc.nextInt();
        printConfigMenu();
        int likesOrViews = sc.nextInt();
        switch (mode) {
            case VIEW:
                logger.info("Running in View mode!");
                while (likesOrViews > 0) {
                    controller.startBrowser();
                    for (String link : links) {
                        logger.info("View page: " + link);
                        controller.viewPage(link);
                    }
                    controller.closeTor();
                    likesOrViews--;
                    logger.info(likesOrViews + " left.");
                }

            case LIKE:
                logger.info("Running in Like mode!");
                while (likesOrViews > 0) {
                    controller.startBrowser();
                    for (String link : links) {
                        logger.info("Click Like button on page: " + link);
                        controller.makeLike(link);
                    }
                    controller.closeTor();
                    likesOrViews--;
                    logger.info(likesOrViews + " left.");
                }
        }
        logger.info("Click app finish");
    }

    private static void printSelectMenu() {
        System.out.println("Select run mode:");
        System.out.println("1) Generate views");
        System.out.println("2) Generate likes");
    }

    private static void printConfigMenu() {
        System.out.println("Please enter how many likes or views, you want:");
    }
}
