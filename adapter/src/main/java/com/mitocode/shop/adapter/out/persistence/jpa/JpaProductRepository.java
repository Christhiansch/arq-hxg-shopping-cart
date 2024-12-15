package com.mitocode.shop.adapter.out.persistence.jpa;

import com.mitocode.shop.adapter.out.persistence.DemoProducts;
import com.mitocode.shop.application.port.out.persistence.ProductRepository;
import com.mitocode.shop.model.product.Product;
import com.mitocode.shop.model.product.ProductId;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@ConditionalOnProperty(name = "persistence", havingValue = "mysql")
@Repository
@RequiredArgsConstructor
public class JpaProductRepository implements ProductRepository {

    private final JpaProductSpringDataRepository springDataRepository;

    @PostConstruct
    private void createDemoProducts(){
        DemoProducts.DEMO_PRODUCTS.forEach(this::save);
    }

    @Override
    @Transactional
    public void save(Product product){
        springDataRepository.save(ProductMapper.toJpaEntity(product));
    }

    @Override
    @Transactional
    public Optional<Product> findById(ProductId productId){
        return springDataRepository.findById(productId.value()).flatMap(ProductMapper::toModelEntityOptional);
    }

    @Override
    public List<Product> findByNameOrDescription (String queryString){
        List<ProductJpaEntity> entities = springDataRepository.findByNameOrDescriptionLike("%" + queryString + "%");
        return ProductMapper.toModelEntities(entities);
    }

}
