package com.eduardoproject.atdc.resources;


import com.eduardoproject.atdc.entities.Category;
import com.eduardoproject.atdc.entities.Product;
import com.eduardoproject.atdc.services.InvoiceService;
import com.eduardoproject.atdc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products/{id}/invoice")
public class InvoiceResource {

    @Value("${invoice-path}")
    private String root;

    @Value("${invoice-directory}")
    private String fileDirectory;

    @Autowired
    private ProductService productService;

    private Product product;

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Product> upload(@PathVariable Integer id, @RequestParam MultipartFile file) {

        product = productService.findById(id);

        String aux = root+"/"+fileDirectory+"/"+invoiceService.saveFile(file);

        product.setInvoicePath(aux);

        productService.update(id, product);

        return ResponseEntity.ok().body(product);
    }

}
