package com.dr.sell.dateObj;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductInfo {
  @Id
  private String productId;
  private String productName;
  private double productPrice;
  private long productStock;
  private String productDescription;
  private String productIcon;
  private long productStatus;
  private long categoryType;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }


  public double getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(double productPrice) {
    this.productPrice = productPrice;
  }


  public long getProductStock() {
    return productStock;
  }

  public void setProductStock(long productStock) {
    this.productStock = productStock;
  }


  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }


  public String getProductIcon() {
    return productIcon;
  }

  public void setProductIcon(String productIcon) {
    this.productIcon = productIcon;
  }


  public long getProductStatus() {
    return productStatus;
  }

  public void setProductStatus(long productStatus) {
    this.productStatus = productStatus;
  }


  public long getCategoryType() {
    return categoryType;
  }

  public void setCategoryType(long categoryType) {
    this.categoryType = categoryType;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }

}
