// package com.example.sparksupport.myecommerce.Config;

// import java.time.LocalDate;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import com.example.sparksupport.myecommerce.constants.Authority;
// import com.example.sparksupport.myecommerce.entity.Account;
// import com.example.sparksupport.myecommerce.entity.Product;
// import com.example.sparksupport.myecommerce.entity.Sale;
// import com.example.sparksupport.myecommerce.services.AccountService;
// import com.example.sparksupport.myecommerce.services.ProductService;
// import com.example.sparksupport.myecommerce.services.SaleService;

// @Component
// public class SeedData implements CommandLineRunner {

//     @Autowired
//     private ProductService productService;

//     @Autowired
//     private SaleService saleService;
//     @Autowired
//     private AccountService accountService;


//     @Override
//     public void run(String... args) throws Exception {

//         Product product1 = new Product("Lenovo","Branded Laptop", 45000.0, 50);
//         Product product2 = new Product("Acer","Low Budget Laptop", 25000.0, 70);
//         Product product3 = new Product("kokonutics","Govt Laptop", 15000.0, 35);
//         Product product4 = new Product("Predator","High End Laptop", 75000.0, 65);
//         Product product5 = new Product("Samsung","Performance Laptop", 65000.0, 355);


//         productService.addProduct(product1);
//         productService.addProduct(product2);
//         productService.addProduct(product3);
//         productService.addProduct(product4);
//         productService.addProduct(product5);


//         Sale sale1 = new Sale(product1, 30, LocalDate.of(2024, 6, 15));
//         Sale sale2 = new Sale(product2, 35, LocalDate.of(2024, 6, 20));
//         Sale sale3 = new Sale(product3, 25, LocalDate.of(2024, 6, 18));
//         Sale sale4 = new Sale(product4, 25, LocalDate.of(2024, 6, 18));
//         Sale sale5 = new Sale(product5, 200,LocalDate.of(2024, 6, 18));

//         saleService.addSale(sale1);
//         saleService.addSale(sale2);
//         saleService.addSale(sale3);
//         saleService.addSale(sale4);
//         saleService.addSale(sale5);


// Account account01 = new Account();
// Account account02 = new Account();

// account01.setEmail("user@user.com");
// account01.setPassword("pass987");
// account01.setAuthorities(Authority.USER.toString());
// accountService.save(account01);

// account02.setEmail("admin@sparksupport.com");
// account02.setPassword("pass123");
//  account02.setAuthorities(Authority.ADMIN.toString() +" "+Authority.USER.toString() );
// accountService.save(account02);



//     }


    
// }
