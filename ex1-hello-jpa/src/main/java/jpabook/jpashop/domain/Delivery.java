package jpabook.jpashop.domain;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.*;

@Entity
public class Delivery extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;
    @OneToOne(mappedBy = "delivery")
    private Order order;
}
