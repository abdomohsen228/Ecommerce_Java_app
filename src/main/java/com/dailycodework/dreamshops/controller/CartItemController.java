package com.dailycodework.dreamshops.controller;
import com.dailycodework.dreamshops.exceptions.ResourceNotFoundException;
import com.dailycodework.dreamshops.response.ApiResponse;
import com.dailycodework.dreamshops.service.cart.ICartItemService;
import com.dailycodework.dreamshops.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {
    private final ICartItemService cartItemService;
    private final ICartService cartService;


    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam(required = false) Long cartId,
                                                     @RequestParam Long productId,
                                                     @RequestParam Integer quantity) {
        try{
            if(cartId == null){
                cartId = cartService.initializeNewCart();
            }
            cartItemService.addItemToCart(cartId,productId,quantity);
            return ResponseEntity.ok(new ApiResponse("Add Item Success ", null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
//    @RequestParam is more flexible for optional parameters or when you expect multiple values to be passed in a single request.
//    @PathVariable is more appropriate when you want to identify a specific resource (like an item) directly in the URL path.
    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId,
                                                          @PathVariable Long itemId)
    {
        try{
            cartItemService.removeItemFromCart(cartId,itemId);
            return ResponseEntity.ok(new ApiResponse("Remove Item Success ", null));
        }
        catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
    // any id path in PathVariable , any data path  @RequestParam to extract from query

    @PutMapping("/cart/{cartId}/item/{itemId}/update")
    public ResponseEntity<ApiResponse> updateCartItemQuantity(@PathVariable Long cartId,
                                                              @PathVariable Long itemId,
                                                              @RequestParam Integer quantity )
    {
        try{
            cartItemService.updateCartItemQuantity(cartId,itemId,quantity);
            return ResponseEntity.ok(new ApiResponse("Update Item Success ", null));
        }
        catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

}


