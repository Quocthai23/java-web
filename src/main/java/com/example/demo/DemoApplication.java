package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(ProductRepository productRepository) {
		return args -> {
			// Add sample products
			productRepository.save(new Product(
				"Áo sơ mi trắng", 
				"áo", 
				250000.0, 
				15, 
				"Áo sơ mi cotton 100% màu trắng, thoải mái và thông thoáng",
				"https://via.placeholder.com/300x300?text=White+Shirt"
			));
			
			productRepository.save(new Product(
				"Quần jeans xanh", 
				"quần", 
				450000.0, 
				20, 
				"Quần jeans xanh đậm, chất liệu bền, form slimfit",
				"https://via.placeholder.com/300x300?text=Blue+Jeans"
			));
			
			productRepository.save(new Product(
				"Giày thể thao Nike", 
				"giày", 
				1200000.0, 
				10, 
				"Giày thể thao Nike chính hãng, đế êm, phù hợp chạy bộ",
				"https://via.placeholder.com/300x300?text=Nike+Shoes"
			));
			
			productRepository.save(new Product(
				"Áo khoác da", 
				"áo", 
				890000.0, 
				8, 
				"Áo khoác da PU cao cấp, ấm áp và thời trang",
				"https://via.placeholder.com/300x300?text=Leather+Jacket"
			));
			
			productRepository.save(new Product(
				"Quần tây đen", 
				"quần", 
				550000.0, 
				12, 
				"Quần tây lịch sự, phù hợp công sở, chất vải tốt",
				"https://via.placeholder.com/300x300?text=Black+Pants"
			));
			
			productRepository.save(new Product(
				"Dây thắt lưng", 
				"phụ kiện", 
				150000.0, 
				25, 
				"Dây thắt lưng da cao cấp với khóa kim loại",
				"https://via.placeholder.com/300x300?text=Belt"
			));

			System.out.println("✅ Dữ liệu mẫu đã được thêm vào H2 database!");
		};
	}
}
