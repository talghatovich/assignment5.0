package com.company;

import com.company.controller.UserController;
import com.company.repository.UserRepository;
import com.company.repository.interfaces.IUserRepository;

import java.util.Scanner;

public class MyApplication {
    private final UserController controller;
    private final Scanner sc;

    public MyApplication(IUserRepository repository){
        this.controller =new UserController(repository);
        this.sc = new Scanner(System.in);
    }


    public void start(){
            while(true){
                System.out.println();
                System.out.println("Welcome to My Application");
                System.out.println("Select option:");
                System.out.println("1. Add stone");
                System.out.println("2. Delete stone");
                System.out.println("3. Create necklace");
                System.out.println("4. Delete necklace");
                System.out.println("5. See necklace ");
                System.out.println("6. See stone");
                System.out.println();
                try {
                    System.out.println("Enter option (1-6): ");
                    int option = sc.nextInt();
                    if (option == 1){
                        addStone();
                    }else if(option == 2){
                        deleteStone();
                    }else if(option == 3){
                        createNecklace();
                    }else if(option == 4){
                        deleteNecklace();
                    }else if(option == 5){
                        seeNecklace();
                    }else if(option == 6){
                        seeStone();
                    }else{
                        break;
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    sc.next();
                }
                System.out.println("*********************");
            }
    }

    private void addStone(){
        System.out.println("Please enter the name of stone: ");
        String name = sc.next();
        System.out.println("Please enter the price(in tenge): ");
        int price = sc.nextInt();
        System.out.println("Please enter the weight(in karats): ");
        int weight = sc.nextInt();

        String response =controller.addStone(name, price, weight);
        System.out.println(response);
    }

    private void deleteStone(){
        System.out.println("Enter the id of the stone you want to delete.");
        int id = sc.nextInt();
        System.out.println(controller.deleteStone(id));

    }

    private void createNecklace(){
        System.out.println("Please, enter the name of necklace: ");
        String name = sc.next();
        System.out.println("Please, enter the weight of necklace: ");
        int weight = sc.nextInt();
        System.out.println("Please, enter the price of necklace: ");
        int price = sc.nextInt();
        System.out.println("How many stones you want in the necklace?: ");
        int amountOfStones= sc.nextInt();
        String response = controller.createNecklace(name,weight,price,amountOfStones);
        System.out.println(response);
    }

    private void deleteNecklace(){
        System.out.println("Enter the id of the necklace you want to delete.");
        int id = sc.nextInt();
        System.out.println(controller.deleteNecklace(id));
    }

    private void seeStone(){
        System.out.println("Please, enter the id of stone: ");

        int id = sc.nextInt();
        String response = controller.getStoneById(id);
        System.out.println(response);
    }

    private void seeNecklace(){
        System.out.println("Please, enter the id of necklace");
        int id = sc.nextInt();
        System.out.println(controller.getNecklaceById(id));
    }
}


