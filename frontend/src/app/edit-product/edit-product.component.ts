import {Component, OnInit} from '@angular/core';
import {Product} from "../product";
import {ProductService} from "../product.service";
import {ActivatedRoute, Router, RouterLink, RouterLinkActive} from "@angular/router";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";
import {AppComponent} from "../app.component";
import {NavbarComponent} from "../navbar/navbar.component";

@Component({
  selector: 'app-edit-product',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf,
    AppComponent,
    RouterLink,
    RouterLinkActive,
    NavbarComponent
  ],
  templateUrl: './edit-product.component.html',
  styleUrl: './edit-product.component.css'
})
export class EditProductComponent implements OnInit{

  product: Product = new Product();
  file!: Blob;
  editProductForm: FormGroup;
  image: FormControl ;
  name: FormControl  ;
  price: FormControl;

  quantity: FormControl;
  category: FormControl;

  constructor(
    private productService: ProductService,
    private router: Router,
    private route:ActivatedRoute
  ) {}

  ngOnInit(): void {

    this.creatFormControls();
    this.createForm()
    this.loadProduct();
  }

  createForm() {
    this.editProductForm = new FormGroup({
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

  loadProduct() {

    let id = this.route.snapshot.paramMap.get('productId');
    this.productService.getProductById(id).subscribe(
      data => {
        this.product = data;
        this.editProductForm.patchValue({
          name: this.product.name,
          price: this.product.price,
          quantity: this.product.quantity,
          category: this.product.category
        });
      },
      error => {
        console.log(error);
      }
    );
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
  goToProductList(){
    this.router.navigate(['']);
  }

  onSubmit(){
    console.log(this.product);
    this.updateProduct();
  }

  private updateProduct(){
    let id = this.route.snapshot.paramMap.get('productId');

    this.product.name = this.name.value;
    this.product.price = this.price.value;
    this.product.quantity = this.quantity.value;
    this.product.category = this.category.value;
    this.productService.updateProduct(id,this.product, this.file).subscribe(data =>{
        console.log(typeof data)
        this.goToProductList()
      },
      error => console.log(error)
    )


  }
}
