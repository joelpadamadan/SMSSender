/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smsender;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Joel
 */
public class Global {

       public ArrayList ReadFile(String FileName) {
        File file = new File(FileName);
        ArrayList list = new ArrayList();
        list.clear();
        if (file.exists()) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String content = "";
                while ((content = br.readLine()) != null) {
                    list.add(content);
                }
                fr.close();
                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        return list;
    }

    public boolean check(String filename, String content) {
        File file = new File(filename);
        try (FileReader fr = new FileReader(file)) {
            BufferedReader br = new BufferedReader(fr);
            String temp = "";
            while ((temp = br.readLine()) != null) {
                if (content.equals(temp)) {
                    return true;
                }
            }
            br.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return false;

    }

    public void rewriteContact(String filename, Object obj[]) {
        try {
            File temp = new File(filename);
            temp.delete();
            temp.createNewFile();
            FileWriter fr = new FileWriter(temp.getAbsoluteFile());
            BufferedWriter br = new BufferedWriter(fr);
            for (int i = 0; i < obj.length; i++) {
                br.write((String) obj[i]);
                br.newLine();
            }
            br.close();
            fr.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void AppendTofile(String filename, String content) {
        File Contact = new File(filename);
        if (!Contact.exists()) {
            try {
                Contact.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        try (FileWriter fw = new FileWriter(Contact, true);
                BufferedWriter bw = new BufferedWriter(fw);) {
            bw.write(content);
            bw.newLine();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }


    }
}
