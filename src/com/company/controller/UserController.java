package com.company.controller;

import com.company.entities.Necklace;
import com.company.entities.Stone;
import com.company.repository.interfaces.IUserRepository;

import java.util.List;

public class UserController{
    private final IUserRepository repository;


    public UserController(IUserRepository repository) {
        this.repository = repository;
    }

    public String addStone(String name, int price, int weight){
        Stone stone1 = new Stone(name, price,weight);
        boolean created =repository.addStone(stone1);

        return (created ? "Some error has been occured":"The stone is added!");
    }

    public String deleteStone(int id){
        boolean deleted = repository.deleteStone(id);

        return(deleted ? "Some error has occured":"The stone is deleted");
    }

    public String getStoneById(int id){
        Stone stone =repository.getStoneById(id);
        return(stone==null?"Stone was not found!": stone.toString());
    }

    public String getNecklaceById(int id){
        String necklace = repository.getNecklaceById(id);
        return(necklace==null?"Necklace was not found!":necklace.toString());
    }

    public String  createNecklace(String name,  int weight, int price, int amountOfStones){
        Necklace necklace = new Necklace(name, weight,price);
        boolean created = repository.createNecklace(necklace,amountOfStones);
        return (created ? "Some error has been occured":"The necklace is created!");
    }

    public String deleteNecklace(int id){
        boolean deleted = repository.deleteNecklace(id);
        return (deleted?"Some error has been occured":"The necklace is deleted!");
    }

}
