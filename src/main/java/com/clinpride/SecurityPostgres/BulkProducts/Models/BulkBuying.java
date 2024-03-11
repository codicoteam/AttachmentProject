package com.clinpride.SecurityPostgres.BulkProducts.Models;

import com.clinpride.SecurityPostgres.FurniturePackage.Models.PackageModels;
import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@Builder
@Document(collection = "bulkbuying")
public class BulkBuying {
        @Id
        private String id;
        private String bulkSalesPrice;
        private String bulkRecommendation;
        private Boolean bulkPurchasable;
        private Boolean showBulkBuying;
       private String  totalPricePerQuantity;
       private int totalGoods;
       private String totalCostPrice;
       private double totalCostPrices;
        private int packageQuantity;
        private double bulkBuyingPrice;//new
        private double bulkBuyingDiscount; // new
        private String content;// new
        private String status;
        private String dateNow;
        private String regularPrice;
        private String bulkBuyingName;
        private String bulkBuyingDescription;
        private int bulkBuyingLikes;
        private String tip;
        private int ratings;
        int initialOrder = 1;
        private String wholesaleEmail;
        private String wholesaleName;
        private String wholesalePhone;
        private List<String> rowMaterials;
        private String bulkLocation;
        private String explainRowMaterials;
        private List<BulkBuying.Category> bulkBuyingCategories;
        private List<String> colors;
        private List<BulkBuying.Image> images;
        private List<BulkBuying.bulkBuyingAttr> bulkBuyingAttributes;
        private List<BulkBuying.review> reviews;
        private String bulkBuyingWarranty;
    public BulkBuying() {
        this.dateNow = LocalDate.now().toString();
    }
        // Constructors, getters, and setters
        @Data
        public static class Category {
            private String categoryName;
            private String imageUrl;
        }
        @Data
        public static class Image {
            private String imageUrl;

        }
        @Data
        public static class bulkBuyingAttr {
            private String capacity; //new
            private String color;
            private String type;
            private String feature;
        }
        @Data
        public static class review {
            private Boolean showReview;
            private String username;
            private String reviewReason;
            private String color;
            private Integer ratings;
            private String dateNow;
            public review() {
                this.dateNow = LocalDate.now().toString();
            }
        }
}
