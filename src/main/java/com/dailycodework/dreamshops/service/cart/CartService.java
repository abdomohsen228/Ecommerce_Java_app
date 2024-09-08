package com.dailycodework.dreamshops.service.cart;
import com.dailycodework.dreamshops.model.Cart;
import com.dailycodework.dreamshops.repository.CartItemRepository;
import com.dailycodework.dreamshops.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final AtomicLong cartIdGenerator = new AtomicLong(0);
    @Override
    public Cart getCart(Long id) {
        Cart cart =cartRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Cart not found"));
        //update
        BigDecimal totalAmount  = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);
    }
    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
    }

    @Override
    public Long initializeNewCart() {
        // Create a new empty Cart object
        Cart newCart = new Cart();

        // Generate a new unique Cart ID (this uses a cartIdGenerator to ensure unique IDs)
        Long newCartId = cartIdGenerator.incrementAndGet();

        // Set the generated unique ID to the newly created Cart object
        newCart.setId(newCartId);

        // Save the new cart in the database using cartRepository, and return the saved cart's ID
        return cartRepository.save(newCart).getId();
    }


}
