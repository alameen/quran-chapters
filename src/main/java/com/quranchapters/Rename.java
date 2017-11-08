/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quranchapters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author aim
 */
public class Rename {

    private static final String TITLE = "Select the directory containing the audio files";

    public static void main(String[] args) {
        Rename r = new Rename();
        r.nameFiles();
    }

    private void nameFiles() {
        InputStream inputStream = getClass().getClassLoader().
                getResourceAsStream("resource" + File.separator + "chapters.txt");
        
        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
        
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setDialogTitle(TITLE);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showOpenDialog(null);

        File directory = chooser.getSelectedFile();
        List<String> audioFiles = new ArrayList<>(Arrays.asList(directory.list()));
        Collections.sort(audioFiles);

        audioFiles.stream().forEach((s) -> {
            System.out.println(s);
        });
    }
}
