import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class ProductService {

  products: Product[];

  constructor(private http: HttpClient) {
  }

  comments: Comment[] = [
    new Comment(1, 1, '2017-02-02 22:22:22', '张三', 3, '东西不错'),
    new Comment(2, 1, '2017-03-03 23:22:22', '李四', 4, '东西是不错'),
    new Comment(3, 1, '2017-04-04 21:22:22', '王五', 2, '东西挺不错'),
    new Comment(4, 2, '2017-05-05 20:22:22', '赵六', 4, '东西还不错'),
  ];

  getAllCategories(): string[] {
    return ['电子产品', '硬件设备', '图书'];
  }

  getProductS(): Observable<any> {
    return this.http.get('http://123.207.157.149:8060/products');
  }

  getProduct(id: number): Product {
    return this.products.find((product) => product.id == id);
  }

  getCommentsForProductId(id: number): Comment[] {
    return this.comments.filter((comment: Comment) => comment.productId == id);
  }
}

export class Product {
  constructor(public id: number,
              public title: String,
              public price: number,
              public rating: number,
              public desc: string,
              public categories: Array<String>) {

  }
}

export class Comment {
  constructor(public id: number,
              public productId: number,
              public timestamp: string,
              public user: string,
              public rating: number,
              public content: string) {
  }
}
