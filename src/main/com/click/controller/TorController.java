package main.com.click.controller;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.*;

public class TorController {

    final static Logger logger = Logger.getLogger(TorController.class);

    File torProfileDir = new File("C:\\Tor\\Browser\\TorBrowser\\Data\\Browser\\profile.default");
    FirefoxBinary binary = new FirefoxBinary(new File("C:\\Tor\\Browser\\firefox.exe"));

    FirefoxDriver driver = null;

    public void startBrowser() throws InterruptedException {
        FirefoxProfile torProfile = new FirefoxProfile(torProfileDir);
        torProfile.setPreference("webdriver.load.strategy", "unstable");
        try {
            binary.startProfile(torProfile, torProfileDir, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.socks", "127.0.0.1");
        profile.setPreference("network.proxy.socks_port", 9150);
        driver = new FirefoxDriver(profile);
    }

    public void closeTor() {
        Runtime rt = Runtime.getRuntime();

        try {
            rt.exec("taskkill /F /IM firefox.exe");
            while (processIsRunning("firefox.exe")) {
                Thread.sleep(100);
            }
            driver = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean processIsRunning(String process) {
        boolean processIsRunning = false;
        String line;
        try {
            Process proc = Runtime.getRuntime().exec("wmic.exe");
            BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            OutputStreamWriter oStream = new OutputStreamWriter(proc.getOutputStream());
            oStream.write("process where name='" + process + "'");
            oStream.flush();
            oStream.close();
            while ((line = input.readLine()) != null) {
                if (line.toLowerCase().contains("caption")) {
                    processIsRunning = true;
                    break;
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return processIsRunning;
    }

    public void makeLike(String link) throws InterruptedException {
        driver.get(link);
        Thread.sleep(10000);
        driver.findElement(By.className("rf-appreciation")).click();
    }

    public void viewPage(String link) throws InterruptedException {
        driver.get(link);
        Thread.sleep(5000);
    }
}
