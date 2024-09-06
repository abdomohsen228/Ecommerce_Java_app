package com.dailycodework.dreamshops.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Set;


@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalAmount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> items;

    public void addItem(CartItem cartItem) {
        cartItem.setCart(this);
        this.items.add(cartItem);
        updateTotalAmount();
    }

    public void removeItem(CartItem itemToRemove) {
        this.items.remove(itemToRemove);
        itemToRemove.setCart(null);
        updateTotalAmount();
    }

    private void updateTotalAmount() {
        this.totalAmount = items.stream()
                .map(item -> {
                    BigDecimal unitPrice = item.getUnitPrice();
                    int quantity = item.getQuantity();
                    if (unitPrice == null) {
                        return BigDecimal.ZERO;
                    }
                    return unitPrice.multiply(BigDecimal.valueOf(quantity));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
