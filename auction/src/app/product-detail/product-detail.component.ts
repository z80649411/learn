import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Comment, Product, ProductService} from '../shared/product.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  product: Product;
  comments: Comment[];
  newRating: number = 5;
  newComment: string = '';
  isCommentHidden: boolean = true;

  constructor(private routeInfo: ActivatedRoute, private productService: ProductService) {
  }

  ngOnInit() {
    let productId: number = this.routeInfo.snapshot.params['productId'];
    this.product = this.productService.getProduct(productId);
    this.comments = this.productService.getCommentsForProductId(productId);
  }

  addComment() {
    let comment: Comment = new Comment(0, this.product.id, new Date().toISOString(), 'dr', this.newRating, this.newComment);
    this.comments.push(comment);
    let sum = this.comments.reduce((sum, comment) => sum + comment.rating, 0);
    this.product.rating = sum / this.comments.length;
    this.newRating = 5;
    this.isCommentHidden = true;
    this.newComment = null;
  }

  setNewRating(rating: number) {
    this.newRating = rating;
  }
}
