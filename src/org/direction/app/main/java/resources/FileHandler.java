package org.direction.app.main.java.resources;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    //private static final String dir = "Direction/Data/";
    //private static String res = dir;

    public static boolean fileExists(String fileName){
        File file = new File(fileName.trim());
        return file.exists();
    }

    public static String readFile(String file) throws Exception{
        String text = "", line;

        if(!fileExists(file)){
            throw new Exception("The " + file + " does not exists...");
        }

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                text += line + "\n";
            }

            bufferedReader.close();
        } catch(Exception ex) {
            System.out.println("Couldn't read records...");
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return text;
    }

    public static Object inflate(String file){

        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
            return is.readObject();
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.print(ex.getMessage());
            return null;
        }
    }

    public static void makeit(String file) {
        File theDir = new File("/Direction/Data");
        if (!theDir.exists()) {

            try {
                theDir.mkdirs();
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.close();
            } catch (Exception ex) {
                System.out.println("Some error occured while setting up the application...");
                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }
    }

    public static boolean deflate(String file,Object object){
        if(!new File(file).exists()){
            System.out.println("Files does not exists... Making the directories");
            makeit(file);
        }
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
            System.out.println(new File(file).getAbsoluteFile());
            os.writeObject(object);
            os.close();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();;
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
