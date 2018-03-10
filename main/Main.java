package main;

import java.awt.*;
import javax.swing.*;
import form.Form;

public class Main{
    public static void main(String[] args) {
        EventQueue.invokeLater( () -> {
            Form f = new Form();
        });
    }
}