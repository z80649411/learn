import {Component, OnInit} from '@angular/core';
import {Product, ProductService} from '../shared/product.service';
import {FormControl} from '@angular/forms';
import 'rxjs/add/operator/debounceTime';


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  imgUrl = 'http://placehold.it/320x150';
  products: Product[];
  keyword: string;
  titleFilter: FormControl = new FormControl();

  constructor(private productService: ProductService) {
    this.titleFilter.valueChanges.debounceTime(500).subscribe(value => this.keyword = value);
  }

  ngOnInit() {
    this.productService.getProductS().subscribe(data => {
      this.products = data;
    }, error2 => {
    }, () => {
    });
  }


}

