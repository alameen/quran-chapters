/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quranchapters;

import java.io.File;
import java.io.InputStream;
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
public class RenameFiles {

    private static final String TITLE = "Select the directory containing the audio files";
    private static final int NO_QURAN_CHAPTER = 114;
    private static final String ERROR_MSG = "Number of files mismatch, ensure there are 114 files in the directory";
    private static final Logger logger = Logger.getLogger(RenameFiles.class.getSimpleName());
    private List<File> files;

    public static void main(String[] args) {
        RenameFiles r = new RenameFiles();
        r.chooseDirectory();
    }

    private void rename(File destinationDirectory) {
        InputStream inputStream = getClass().getClassLoader().
                getResourceAsStream("resource" + File.separator + "chapters.txt");
        
        
        //Read names of chapters
        int i = 0;
        String[] chapters  = new String[NO_QURAN_CHAPTER];
        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                chapters[i++] = scanner.nextLine();
            }
        }
        
        //The renaming happens here.
        int j = 0;
        for (File f : files) {
            String name = chapters[j++];
            f.renameTo(new File(destinationDirectory, name));
            logger.log(Level.INFO, "SET THE NAME --> {0}", name);
        }
        
        logger.log(Level.INFO, "{0} FILES RENAMED SUCCESSFULLY", NO_QURAN_CHAPTER);
    }
    
    private void chooseDirectory() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setDialogTitle(TITLE);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showOpenDialog(null);

        File directory = chooser.getSelectedFile();
        
        files = Arrays.asList(directory.listFiles());
        Collections.sort(files); //arrange files in ascending order.
        
        if (files.size() != NO_QURAN_CHAPTER) {
            throw new RuntimeException(ERROR_MSG);
        }
        
        rename(directory);
        
    }
    
}
