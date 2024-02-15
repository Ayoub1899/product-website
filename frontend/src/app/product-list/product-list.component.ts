import {Component, OnInit} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {ProductService} from "../product.service";
import {FormsModule} from "@angular/forms";
import {RouterLink, RouterLinkActive} from "@angular/router";
import { HttpClientModule } from '@angular/common/http';
import {AppComponent} from "../app.component";
import {NgxPaginationModule} from "ngx-pagination";
import {NavbarComponent} from "../navbar/navbar.component";


@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterLink,
    RouterLinkActive,
    NgOptimizedImage,
    HttpClientModule,
    AppComponent,
    NgxPaginationModule,
    NavbarComponent
  ],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit{

  products: any[];
  p:number = 1;
  itemsPerPage:number = 8
  totalProducts:any;

  constructor(private productService: ProductService) {}

  ngOnInit(): void{
    this.getAll();
  }

  private getAll(){
    this.productService.getAll().subscribe(data=>{
      console.log(data)
      this.products = data
      this.totalProducts = data.length
    })
  }

  deleteProduct(id: string){
    this.productService.deleteProduct(id).subscribe( data => {
      console.log(data);
      alert("Produit supprimé !")
      this.getAll();
    })
  }

}
