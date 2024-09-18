package org.unvime.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DaoApplication implements CommandLineRunner {

    private final menuGestion menu;

    @Autowired
    public DaoApplication(menuGestion menu) {  // Constructor injection
        this.menu = menu;
    }

    public static void main(String[] args) {
        SpringApplication.run(DaoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        menu.mostrarMenu();
    }
}
