import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "./product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseURL="http://localhost:8082/api/v1/product";
  file: any;
  constructor(private httpClient: HttpClient) {}
  getAll() : Observable<Product[]>{
    return this.httpClient.get<Product[]>(`${this.baseURL}`+"/getAll");
  }

  getProductById(productId: any) : Observable<Product>{
    return this.httpClient.get<Product>(`${this.baseURL}/getById/${productId}`)
  }

  addProduct(product: Product, file: Blob): Observable<Object>{

    let formData = new FormData();
    formData.append('data', new Blob([JSON.stringify(product)], {
      type: "application/json"
    }));
    formData.append('file', file);

    let header = new HttpHeaders();
    header = header.append('enctype', 'multipart/form-data');
    header = header.append('Accept', '*/*');
    return this.httpClient.post(`${this.baseURL}/add`,
      formData,
      {headers : header}
    );

  }

  deleteProduct(id: string): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/delete/${id}`);
  }

  updateProduct(productId: any, product: Product, file: Blob): Observable<Object> {
    let formData = new FormData();
    formData.append('data',
      new Blob([JSON.stringify(product)],
        {
      type: "application/json"
    }));
    formData.append('file', file);

    let header = new HttpHeaders();
    header = header.append('enctype', 'multipart/form-data');
    header = header.append('Accept', '*/*');
    return this.httpClient.put(
      `${this.baseURL}/updateInformation/${productId}`,
      formData,
      { headers: header }
    );
  }



}
