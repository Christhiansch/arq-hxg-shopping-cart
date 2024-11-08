package com.mitocode.shop.adapter.out.persistence.jpa;

import com.mitocode.shop.adapter.out.persistence.DemoProducts;
import com.mitocode.shop.application.port.out.persistence.ProductRepository;
import com.mitocode.shop.model.product.Product;
import com.mitocode.shop.model.product.ProductId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class JpaProductRepository implements ProductRepository {

    private final EntityManagerFactory entityManagerFactory;

    public JpaProductRepository(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
        createDemoProducts();
    }

    private void createDemoProducts(){
        DemoProducts.DEMO_PRODUCTS.forEach(this::save);
    }

    @Override
    public void save(Product product){
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            entityManager.merge(ProductMapper.toJpaEntity(product));
            entityManager.getTransaction().commit();;
        }
    }

    @Override
    public Optional<Product> findById(ProductId productId){
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            ProductJpaEntity jpeEntity = entityManager.find(ProductJpaEntity.class, productId.value());
            return ProductMapper.toModelEntityOptional(jpeEntity);
        }
    }

    @Override
    public List<Product> findByNameOrDescription (String queryString){
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            TypedQuery<ProductJpaEntity> query =
                    entityManager
                            .createQuery(
                                    "from ProductJpaEntity where name like :query or description like :query",
                                    ProductJpaEntity.class)
                            .setParameter("query", "%" + queryString + "%");

            List<ProductJpaEntity> entities =  query.getResultList();
            return ProductMapper.toModelEntities(entities);
        }
    }

}
