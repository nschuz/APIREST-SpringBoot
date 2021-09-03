package com.api.init.rest;

import com.api.init.dao.ProductsDAO;
import com.api.init.entitys.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductREST {
    //public responsentitny nos regresa objetos
    //Tipo generico producto

    @Autowired
    private ProductsDAO productsDAO;


    //Nos regresa un json
    @GetMapping
    public ResponseEntity<List<Product>> getProduct(){
        List<Product> products= productsDAO.findAll();
        //Product product = new Product();
        //product.setId(1);
       // product.setName("Producto 1");
        //return ResponseEntity.ok();
        return  ResponseEntity.ok(products);
    }

    @RequestMapping(value="{productId}" )//products/{productsid}->products/1
    public ResponseEntity<Product> getProductById(@PathVariable("productId") long productId){
     Optional<Product> oneProduct = productsDAO.findById(productId);
     if(!oneProduct.isPresent()){
         return  ResponseEntity.noContent().build();
     }else{
         return  ResponseEntity.ok(oneProduct.get());
     }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct (@RequestBody Product product ){
      Product newProduct =  productsDAO.save(product);
      return ResponseEntity.ok( newProduct);
    }

    @DeleteMapping(value="{productId}")
    public ResponseEntity<Product> deleteProduct (@PathVariable("productId") long productId ){
        productsDAO.deleteById(productId);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<Product> putProduct (@RequestBody Product product ){
        Optional<Product> oneProduct = productsDAO.findById(product.getId());
        if(!oneProduct.isPresent()){
            return  ResponseEntity.noContent().build();
        }else{
            Product updateProduct = oneProduct.get();
            updateProduct.setName(product.getName());
            productsDAO.save(updateProduct);
            return ResponseEntity.ok(updateProduct);
        }
    }



    //@GetMapping
    //@RequestMapping(value = "/hello" , method= RequestMethod.GET)
    public String hello (){
        return "Hello World";
    }

}
