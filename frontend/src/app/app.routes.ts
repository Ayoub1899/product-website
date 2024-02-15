import { Routes } from '@angular/router';
import {ProductListComponent} from "./product-list/product-list.component";
import {AddProductComponent} from "./add-product/add-product.component";
import {EditProductComponent} from "./edit-product/edit-product.component";

export const routes: Routes = [
  {path: '', component:ProductListComponent, title:"Liste des produits"},
  {path: 'add', component:AddProductComponent, title:"Ajouter un produit"},
  {path:'edit-product/:productId', component:EditProductComponent, title:"Modifier un produit"}
];
