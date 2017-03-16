package com.kartojam.dabar.papildoma;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by Ignas on 2017.03.16.
 */
public class Papildoma {
    private Connection connection;
    Scanner sc = new Scanner(System.in);

    public Papildoma() {

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/praktika",
                    "root",
                    "");
        } catch (Exception klaida) {
            System.out.println(klaida);
        }

    }

    public void pasisveikink() {

        System.out.println("Sveiki, as galiu pakeisti, istrinti arba atvaizduoti duomenis is Aktoriu lenteles");
    }

    public void paklausk() {
        System.out.println("Ar norite pakeisti ar trinti duomenis ar atvaizduoti duomenis is Aktoriu lenteles?");
        System.out.println("Pakeisti: 1");
        System.out.println("Trinti: 2");
        System.out.println("Atvaizduoti: 3");
    }

    public void nuspresk() {

        int skaicius = sc.nextInt();
        switch (skaicius) {
            case 1:
                System.out.println("Jus pasirinkote ivesti duomenis");
                prideti();
                break;
            case 2:
                System.out.println("Jus pasirinkote istrinti duomenis");
                System.out.println("Kuri irasa norite istrinti pagal aktoriaus ID?");
                istrinti();
            case 3:
                System.out.println("Jus pasirinkote atvaizduoti duomenis");
                atvaizduoti();
        }
    }

    private void prideti() {

        System.out.println("Lentele: Aktoriai");
        try {

            String insertTableSQL = "INSERT INTO `aktoriai`" + "(`Vardas`,`Pavarde`,`Amzius`) VALUES" + "(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
            System.out.println("Iveskite varda");
            String Name = sc.next();
            System.out.println("Iveskite pavarde");
            String Surname = sc.next();
            System.out.println("Iveskite amziu");
            int Phone = sc.nextInt();
            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, Surname);
            preparedStatement.setInt(3, Phone);
            preparedStatement.executeUpdate();

        } catch (Exception klaida) {
            System.out.println(klaida);

        }
    }

    private void istrinti() {

        try {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM `aktoriai` WHERE `aktoriai`.`id` = " + "?" +";");
            System.out.println("Ivesti ID aktoriaus kuri norite istrinti");
            int id = sc.nextInt();
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception klaida) {
            System.out.println(klaida);

        }
    }

        private void atvaizduoti() {

        System.out.println("Lentele: Aktoriai");
        try {
            Statement statement = connection.createStatement();
            statement.execute("SELECT * from `aktoriai`");
            ResultSet resultSet = statement.executeQuery("SELECT * from `aktoriai`");
            while (resultSet.next()) {
                System.out.print(" >< ");
                System.out.print(resultSet.getInt("id"));
                System.out.print(" >< ");
                System.out.print(resultSet.getString("Vardas"));
                System.out.print(" >< ");
                System.out.print(resultSet.getString("Pavarde"));
                System.out.print(" >< ");
                System.out.print(resultSet.getInt("Amzius"));
                System.out.println();

            }
        } catch (Exception klaida) {
            System.out.println(klaida);

        }
    }
}


