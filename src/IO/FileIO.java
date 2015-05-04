/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author dee
 */
public class FileIO {

    /**
     * write string into a file
     * @param s the string to be written
     * @param file filename of the file
     * @throws IOException 
     */
    public void saveLog(String s, String file) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file))))) {
            out.print(s);
        }
    }

    /**
     * read string from a file
     * @param file filename of the file
     * @return string read from file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public String readLog(String file) throws FileNotFoundException, IOException {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            line = br.readLine();
        }
        return line;
    }

    /**
     * save object to a file
     * @param o object to be save
     * @param filename filename of the file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void saveObject(Object o, String filename) throws FileNotFoundException, IOException {
        try (FileOutputStream fout = new FileOutputStream(filename)) {
            ObjectOutputStream oout = new ObjectOutputStream(fout);
            oout.writeObject(o);
            oout.flush();
        }
    }

    /**
     * read object from file
     * @param filename filename of the file
     * @return object read from the file
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public Object getObject(String filename) throws IOException, ClassNotFoundException {
        Object o;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            o = ois.readObject();
        }
        return o;
    }
}
