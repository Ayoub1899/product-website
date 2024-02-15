import {Component, OnInit} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Product} from "../product";
import {ProductService} from "../product.service";
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import { HttpClientModule } from '@angular/common/http';
import {AppComponent} from "../app.component";
import {NavbarComponent} from "../navbar/navbar.component";



@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive, NgOptimizedImage, ReactiveFormsModule, HttpClientModule, AppComponent, NavbarComponent],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent implements OnInit{

  product: Product = new Product();
  file!:Blob;
  addProductForm: FormGroup;
  image: FormControl ;
  name: FormControl  ;
  price: FormControl;
  quantity: FormControl;
  category: FormControl;

  createForm() {
    this.addProductForm = new FormGroup({
      name:this.name,
      image: this.image,
      price: this.price,
      quantity:this.quantity,
      category:this.category
    });
  }
  creatFormControls(){
    this.name = new FormControl('')
    this.image = new FormControl('')
    this.price = new FormControl('')

    this.quantity = new FormControl('')
    this.category = new FormControl('')


  }






  constructor(private productService: ProductService, private router: Router) {}
  ngOnInit(): void {
    this.creatFormControls();
    this.createForm()

  }


  onChangeFileField(event:any){
    var reader = new FileReader();

    reader.onload = (event: any) => {
      this.product.image = event.target.result;

    };

    reader.onerror = (event: any) => {
      console.log("File could not be read: " + event.target.error.code);
    };

    reader.readAsDataURL(event.target.files[0]);
    this.file = event.target.files[0];
  }

  private saveProduct(){
    this.product.name = this.name.value;
    this.product.price = this.price.value;
    this.product.quantity = this.quantity.value;
    this.product.category = this.category.value;
    this.productService.addProduct(this.product, this.file)
      .subscribe(data =>{
        this.goToProductList()
      },
      error => console.log(error)
    )

  }

  goToProductList(){
    this.router.navigate(['']);
  }

  onSubmit(){
    console.log(this.product);
    this.saveProduct();
  }

}
